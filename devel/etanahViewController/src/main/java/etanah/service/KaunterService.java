package etanah.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.kodHasilConfig;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodDokumen;
import etanah.model.KodKadarBayaran;
import etanah.model.KodKategoriBayaran;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.UrusanDokumen;
import etanah.util.MonetaryUtil;
import etanah.view.kaunter.TransaksiValue;
import etanah.view.stripes.pengambilan.share.form.FeeSek4Form;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Map;

public class KaunterService {

    private final static Logger LOG = Logger.getLogger(KaunterService.class);
    private final static boolean debug = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private KodTransaksiDAO kodTransaksiDAO;
    @Inject
    private MonetaryUtil monetaryUtil;
    @Inject
    kodHasilConfig hasilCfg;
    final HashMap<BigDecimal, String> JENIS_KADAR_CONSENT = new HashMap<BigDecimal, String>();
    @Inject
    private etanah.Configuration conf;

    String CONSENT_KOD_URUSAN1;

    public KaunterService() {
        JENIS_KADAR_CONSENT.put(new BigDecimal(50), "Pertanian");
        JENIS_KADAR_CONSENT.put(new BigDecimal(100), "Bangunan Kediaman");
        JENIS_KADAR_CONSENT.put(new BigDecimal(200), "Bangunan Perniagaan");
        JENIS_KADAR_CONSENT.put(new BigDecimal(300), "Perindustrian");
    }

    public List<KodUrusan> findKodUrusanJabatan(String kodJabatan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery(
                    "from etanah.model.KodUrusan u where u.jabatan.id = :kodJabatan");
            q.setString("kodJabatan", kodJabatan);
            return q.list();
        } finally {
            //session.close();
        }

    }

    public List<UrusanDokumen> getUrusanDokumen(List<String> senaraiKodUrusan) {
        return getUrusanDokumen(senaraiKodUrusan, 'X');
    }

    /**
     * Get the list of merged UrusanDokumen for given list of KodUrusan.
     *
     * @param senaraiKodUrusan
     * @return
     */
    public List<UrusanDokumen> getUrusanDokumen(List<String> senaraiKodUrusan, char kategoriPemohon) {
        if (kategoriPemohon != 'X' && kategoriPemohon != 'I' && kategoriPemohon != 'O' && kategoriPemohon != 'P') {
            throw new IllegalArgumentException("Unsupported kategori pemohon: " + kategoriPemohon);
        }

        if (debug) {
            for (String kod : senaraiKodUrusan) {
                LOG.debug("getUrusanDokumen-" + kod);
            }
        }
        long t = System.currentTimeMillis();
        String hql = "select ud from etanah.model.UrusanDokumen ud "
                + "inner join fetch ud.kodDokumen "
                + "where ud.kodUrusan.id in (:senaraiKodUrusan) "
                + (kategoriPemohon == 'X' ? "" : " and (ud.kategoriPemohon = 'X' or ud.kategoriPemohon = :kategoriPemohon)")
                + " and ud.aktif = 'Y' "
                + "order by ud.wajib desc, ud.perluDisah desc, ud.kodDokumen.id asc";
        Session s = sessionProvider.get();
        Query q = s.createQuery(hql);
        q.setParameterList("senaraiKodUrusan", senaraiKodUrusan);
        if (kategoriPemohon != 'X') {
            q.setCharacter("kategoriPemohon", kategoriPemohon);
        }
        List<UrusanDokumen> lud = q.list();

        if (debug) {
            LOG.debug("KaunterService:senaraiKodUrusan:lud.size=" + lud.size());
            LOG.debug("getUrusanDokumen took " + (System.currentTimeMillis() - t) + "ms");
        }

        if (senaraiKodUrusan.size() == 1) {
            return lud;
        }

        List<UrusanDokumen> merged = new ArrayList<UrusanDokumen>();
        UrusanDokumen last = null;
        for (int i = 0; i < lud.size(); i++) {
            UrusanDokumen ud = lud.get(i);
            if (last != null && last.getKodDokumen().getKod().equals(ud.getKodDokumen().getKod())) {
                // concat all catatan
                String c = ud.getCatatan();
                if (c != null) {
                    last.setCatatan("\n" + last.getCatatan().concat(c));
                }
                // check if any document is stronger
                if (last.getWajib() == 'T' && ud.getWajib() == 'Y') {
                    last.setWajib('Y');
                }
                if (last.getPerluDisah() == 'T' && ud.getPerluDisah() == 'Y') {
                    last.setWajib('Y');
                }
                // caj
                if (ud.getCaj() != null) {
                    if (last.getCaj() == null) {
                        last.setCaj(ud.getCaj());
                    } else if (last.getCaj().compareTo(ud.getCaj()) > 0) {
                        last.setCaj(ud.getCaj());
                        last.setKodTransaksi(ud.getKodTransaksi());
                    }
                }
            } else {
                if (last != null) {
                    merged.add(last);
                }
                last = ud;
            }
            // add the last one
            if (i == lud.size() - 1) {
                merged.add(last);
            }
        }

        // sort by mandatory
        Collections.sort(merged, new Comparator<UrusanDokumen>() {
            /**
             * Sorting where Y < T
             *
             * @param o1
             * @param o2
             * @return
             */
            @Override
            public int compare(UrusanDokumen o1, UrusanDokumen o2) {
                return o2.getWajib() - o1.getWajib();
            }
        });

        return merged;
    }

    public List<KodUrusan> findAllUrusanByJabatan() {
        if (debug) {
            LOG.debug("getting list of urusan");
        }
        Session s = sessionProvider.get();
        List<KodUrusan> u = s.createQuery("select u from KodUrusan u "
                + "left join fetch u.jabatan j "
                + "where u.urusanKaunter = 'Y' and u.aktif = 'Y' "
                + "order by u.jabatan.nama asc, u.kod asc ") //.setCacheable(true)
                .list();

        return u;
    }

    public List<KodDokumen> getKodDokumenNotRequired(ArrayList<String> senaraiKodUrusan) {
        String hql = "select kd from KodDokumen kd where kd not in "
                + "(select d from UrusanDokumen ud "
                + "inner join ud.kodDokumen d "
                + "where ud.kodUrusan.id in (:senaraiKodUrusan)) "
                + "order by kd.nama";

        return sessionProvider.get().createQuery(hql).
                setParameterList("senaraiKodUrusan", senaraiKodUrusan).list();
    }

    public List<KodUrusan> findCarianUrusan(String kodSerah) {
        String hql = "select u from KodUrusan u where u.kodPerserahan.kod = :kod";
        return sessionProvider.get().createQuery(hql).
                setParameter("kod", kodSerah).list();
//		Session s = sessionProvider.get();
//		List<KodUrusan> u = s.createQuery(
//				"select u from KodUrusan u " + "where u.kodPerserahan.kod = :kod ")
//				.list();
//		return u;
    }

    /**
     * Adapted from SPTB PL/SQL
     *
     * @return list of transactions to be charged for given code
     */
    @SuppressWarnings("unchecked")
    public ArrayList<TransaksiValue> calculateFee(KodUrusan kodUrusan,
            BigDecimal nilai, // input at SPOC
            Date tarikh, // input at SPOC
            String kategori, // input at SPOC
            ArrayList<Integer> senaraiBilKategori, // input at SPOC (either kategori or senaraiBilKategori will be passed)
            String teks1, // input at SPOC
            List<HakmilikPermohonan> senaraiHakmilikPermohonan,
            List<String> senaraiHakmilikBersiri) {

        //BigDecimal caj = BigDecimal.ZERO;
        KodKategoriBayaran kg = kodUrusan.getKategoriBayaran();
        int bilHakmilik = 0;
        char milikDaerah = 'X';
        /*Hakmilik Daerah atau Pendaftar, X for not applicable*/
        for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
            HakmilikPermohonan h = senaraiHakmilikPermohonan.get(i);
            if (h.getHakmilik() != null && h.getHakmilik().getIdHakmilik() != null) {
                // get sampling for the first record only
                if (i == 0) {
                    Hakmilik hm = hakmilikDAO.findById(h.getHakmilik().getIdHakmilik());
                    if (hm != null) {
                        h.setHakmilik(hm);
                        milikDaerah = hm.getKodHakmilik().getMilikDaerah();
                    }
                }
                bilHakmilik++;
            }
        }
        if (senaraiHakmilikBersiri != null) {
            bilHakmilik += senaraiHakmilikBersiri.size();
        }
        if (debug) {
            LOG.debug("bilHakmilik=" + bilHakmilik);
        }

        // get sampling for the first record only
        if (milikDaerah == 'X' && bilHakmilik > 0 && senaraiHakmilikBersiri != null
                && senaraiHakmilikBersiri.size() > 0) {
            Hakmilik hm = hakmilikDAO.findById(senaraiHakmilikBersiri.get(0));
            if (hm != null) {
                milikDaerah = hm.getKodHakmilik().getMilikDaerah();
            } else {
                LOG.warn("Couldn't find idHakmilik " + senaraiHakmilikBersiri.get(0));
            }
        }
        if (debug) {
            LOG.debug("milikDaerah=" + milikDaerah);
        }

        // just set hakmilik to minimum 1 in case need to charge
        if (bilHakmilik == 0) {
            bilHakmilik = 1;
        }

        Session s = sessionProvider.get();

        ArrayList<TransaksiValue> listTransaksi = new ArrayList<TransaksiValue>();

        if (kg == null) {
            /* flat rate */
            BigDecimal c = kodUrusan.getCaj();
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setKuantiti(bilHakmilik);
            if (c != null) {
                t.setAmaun(c);
            } else {
                t.setAmaun(BigDecimal.ZERO);
            }

            listTransaksi.add(t);
            return listTransaksi;
        }

        switch (kg.getKod()) {

            case 0: /*flat rate*/ {
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(1);
                BigDecimal c = kodUrusan.getCaj();
                if (c != null) {
                    t.setAmaun(c);
                } else {
                    t.setAmaun(BigDecimal.ZERO);
                }
                listTransaksi.add(t);

                break;

            }
            case 1: /* kategori 1 = nilaian harga tanah */ {
                if (nilai == null) {
                    throw new RuntimeException("Amaun Transaksi tidak dinyatakan untuk pengiraaan");
                }

                StringBuilder q = new StringBuilder("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah) "
                        + "and kb.nilaiMinimum <= :nilaiHarta and (kb.nilaiMaksimum >= :nilaiHarta "
                        + "or kb.nilaiMaksimum is null)");
                if (kategori != null && kategori.trim().length() > 0) {
                    q.append(" and kb.kategori = :kategori");
                }

                Query query = s.createQuery(q.toString()) //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
                        .setString("kodUrusan", kodUrusan.getKod()).setCharacter("milikDaerah", milikDaerah).setBigDecimal("nilaiHarta", nilai);
                if (kategori != null && kategori.trim().length() > 0) {
                    query.setString("kategori", kategori);
                }
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();
                if (kb.size() != 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                }
                KodKadarBayaran k = kb.get(0);
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                if (k.getPeratus() == 'Y') {
                    BigDecimal kadar = monetaryUtil.roundToRinggit(k.getKadar().divide(new BigDecimal(100)).multiply(nilai));
//                    if (k.getKadarMinimum() != null && kadar.compareTo(k.getKadarMinimum()) < 0){
//                        kadar = k.getKadarMinimum();
//                    } else if (k.getKadarMaksimum() != null && kadar.compareTo(k.getKadarMaksimum()) < 0){
//                        kadar = k.getKadarMaksimum();
//                    }
                    t.setAmaun(kadar);
                } else {
                    t.setAmaun(k.getKadar());
                }
                t.setKuantiti(1);
                listTransaksi.add(t);

                // for urusan pendaftaran, fine calculation
                if (kodUrusan.getJabatan().getKod().equals("16")) {
                    // fine (denda), if tarikh penyaksian is not null (to enable calculation, set tarikh penyaksian)
                    if (tarikh != null) {
                        BigDecimal fine = calculateFine(tarikh, t.getAmaun());
                        if (fine.compareTo(BigDecimal.ZERO) > 0) {
                            // create a new transaction
                            TransaksiValue t2 = new TransaksiValue();
                            t2.setKodUrusan(kodUrusan.getKod());
                            t2.setNamaUrusan("Denda Lewat");
                            //TODO , multiply with hakmilik ?
                            if (kodUrusan.getKod().equals("GD")
                                    || kodUrusan.getKod().equals("GDL")) {
                                fine = fine.multiply(new BigDecimal(bilHakmilik));
                                t2.setKuantiti(bilHakmilik);
                            }
                            t2.setAmaun(fine);
                            t2.setKodTransaksi(t.getKodTransaksi());
                            listTransaksi.add(t2);
                        }
                    }
                }

                break;
            }
            case 2: /* kategori 2 = based on count of Hakmilik */ {
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah) and kb.aktif ='Y' "
                        + "order by kb.bilHakmilik").setString("kodUrusan", kodUrusan.getKod()).setCharacter("milikDaerah", milikDaerah).list();
                if (debug) {
                    LOG.debug("kb.size()=" + kb.size());
                }
                if (kb.size() < 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                }
                int lastKadar = 0;
                BigDecimal c = BigDecimal.ZERO;
                for (int i = 1; i <= bilHakmilik; i++) {
                    for (; lastKadar < kb.size(); lastKadar++) {
                        if (debug) {
                            LOG.debug("lastKadar=" + lastKadar);
                        }
                        KodKadarBayaran k = kb.get(lastKadar);
                        if (k.getBilHakmilik() == i) {
                            break;
                        }
                    }
                    if (lastKadar >= kb.size()) {
                        lastKadar = kb.size() - 1;
                    }
                    c = c.add(kb.get(lastKadar).getKadar());
                }
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setAmaun(c);
                t.setKuantiti(bilHakmilik);
                listTransaksi.add(t);
                // for urusan pendaftaran, fine calculation
                if (kodUrusan.getJabatan().getKod().equals("16")) {
                    // fine (denda), if tarikh penyaksian is not null (to enable calculation, set tarikh penyaksian)
                    if (tarikh != null) {
                        BigDecimal fine = calculateFine(tarikh, t.getAmaun());
                        if (fine.compareTo(BigDecimal.ZERO) > 0) {
                            // create a new transaction
                            TransaksiValue t2 = new TransaksiValue();
                            t2.setKodUrusan(kodUrusan.getKod());
                            t2.setNamaUrusan("Denda Lewat");
                            //TODO , multiply with hakmilik ?
                            if (kodUrusan.getKod().equals("GD")
                                    || kodUrusan.getKod().equals("GDL")) {
//                                fine = fine.multiply( new BigDecimal(bilHakmilik));
                                t2.setKuantiti(bilHakmilik);
                            }
                            t2.setAmaun(fine);
                            t2.setKodTransaksi(t.getKodTransaksi());
                            listTransaksi.add(t2);
                        }
                    }
                }

                break;
            }
            case 3: /*  kategori 3 = lump sum bil_hm */ {
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah)").setString("kodUrusan", kodUrusan.getKod()).setCharacter("milikDaerah", milikDaerah).list();
                if (kb.size() < 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                }
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                if (kb.size() < 1) {
                    t.setAmaun(BigDecimal.ZERO);
                } else {
                    t.setAmaun(kb.get(0).getKadar());
                }
                listTransaksi.add(t);

                break;
            }
            case 4: /* hisham: data for SPTB kategori 4 look suspicious, wonder if being used*/ {
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah)").setString("kodUrusan", kodUrusan.getKod()).setCharacter("milikDaerah", milikDaerah).list();
                if (kb.size() < 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                }
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                if (kb.size() < 1) {
                    t.setAmaun(BigDecimal.ZERO);
                } else {
                    t.setAmaun(kb.get(0).getKadar().multiply(nilai));
                }
                t.setKuantiti(1);
                listTransaksi.add(t);

                break;
            }
            case 5: {
                /* hisham: couldnt find any sptb code for kategori 5, maybe in other states? */
                break;
            }
            case 6: /* based on kategori (eg. consent)*/ {
                if (kategori == null) {
                    throw new RuntimeException("kategori nil diberikan untuk kategori bayaran 6!");
                }
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah) "
                        + "order by kb.bilHakmilik").setString("kodUrusan", kodUrusan.getKod()).setCharacter("milikDaerah", milikDaerah).list();
                BigDecimal c = null;
                for (KodKadarBayaran b : kb) {
                    if (b.getKategori() != null && kategori.trim().equalsIgnoreCase(b.getKategori())) {
                        c = b.getKadar();
                        c = c.multiply(new BigDecimal(bilHakmilik)); // for every lot
                    }
                }
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setAmaun(c);
                t.setKuantiti(bilHakmilik);
                listTransaksi.add(t);

                break;
            }
            case 7: //kategori 7 = kadar strata mengikut bil. petak
            {
                if (nilai == null) {
                    throw new RuntimeException("Bil. petak tidak dinyatakan untuk pengiraaan");
                }

                StringBuilder q = new StringBuilder("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and kb.nilaiMinimum <= :bilPetak and (kb.nilaiMaksimum >= :bilPetak "
                        + "or kb.nilaiMaksimum is null)");

                Query query = s.createQuery(q.toString()) //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
                        .setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("bilPetak", nilai);
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();
                if (kb.size() != 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                            + " tidak tepat!");
                }
                //added  by Murali
                BigDecimal c = new BigDecimal(BigInteger.ZERO);
                KodKadarBayaran k = kb.get(0);
                LOG.debug("KOD URUSAN = " + kodUrusan.getKod());
                if (kodUrusan.getKod().equals("PKBK"))
                {   
                    LOG.debug("MASUK SINI");
                    c = k.getKadar();
                    
                } else {
                    if (nilai.intValue() > 30) {
                        c = nilai.multiply(new BigDecimal(10));
                    } else {
                        c = k.getKadar();
                    }
                }
                //End
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setAmaun(c);
                t.setKuantiti(nilai.intValue()); // jumlah petak
                listTransaksi.add(t);

                break;
            }

            case 8: //kategori 8 = lelong melaka
            {

                TransaksiValue dep = new TransaksiValue();
                BigDecimal a = new BigDecimal(100);
                int b = bilHakmilik;
                if (bilHakmilik == 1) {
                    LOG.info("-----bil = 1----");
                    a = new BigDecimal(100);
                } else if (bilHakmilik > 1) {
                    LOG.info("-----bil > 1----");
                    int c = 100;
                    for (int i = 0; i < (bilHakmilik - 1); i++) {
                        c += 100;
                    }
                    LOG.info("----- c : " + c);
                    a = new BigDecimal(c);
                }
                LOG.info("---jum : " + a + "-----");
                dep.setKuantiti(1);
                dep.setKodUrusan(kodUrusan.getKod());
                dep.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                dep.setNamaUrusan("Permohonan Perintah Jual");
                dep.setAmaun(BigDecimal.valueOf(500));
                listTransaksi.add(dep);
                dep = new TransaksiValue();
                dep.setKuantiti(1);
                dep.setKodUrusan(kodUrusan.getKod());
                dep.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                dep.setNamaUrusan("Notis");
                dep.setAmaun(BigDecimal.valueOf(50));
                listTransaksi.add(dep);
                dep = new TransaksiValue();
                dep.setKuantiti(b);
                dep.setKodUrusan(kodUrusan.getKod());
                dep.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                dep.setNamaUrusan("Siasatan");
                dep.setAmaun(a);
                listTransaksi.add(dep);

                break;
            }

            case 11: //kategori 11 = lelong n9
            {

                TransaksiValue dep = new TransaksiValue();
                int b = bilHakmilik;
                dep.setKuantiti(b);
                dep.setKodUrusan(kodUrusan.getKod());
                dep.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                dep.setNamaUrusan("Permohonan Perintah Jual");
                dep.setAmaun(BigDecimal.valueOf(40));
                listTransaksi.add(dep);

//                List<String> listIDHakmilik = new ArrayList<String>();
//                LOG.info("senaraiHakmilikPermohonan.size : " + senaraiHakmilikPermohonan.size());
//                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
//                    listIDHakmilik.add(hp.getHakmilik().getIdHakmilik());
//                }
//                Session session = sessionProvider.get();
//                List<Pihak> listPihak = session.createQuery("select DISTINCT hp.pihak from HakmilikPihakBerkepentingan hp where hp.hakmilik.idHakmilik in (:listIDHakmilik) "
//                        + "and hp.aktif = 'Y'").setParameterList("listIDHakmilik", listIDHakmilik).list();
//                LOG.info("listPihak : " + listPihak.size());
//                dep = new TransaksiValue();
//                dep.setKuantiti(listPihak.size());
//                dep.setKodUrusan(kodUrusan.getKod());
//                dep.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
//                dep.setNamaUrusan("Notis Siasatan");
//                dep.setAmaun(BigDecimal.valueOf(10 * listPihak.size()));
//                listTransaksi.add(dep);
//                dep = new TransaksiValue();
//                dep.setKuantiti(1);
//                dep.setKodUrusan(kodUrusan.getKod());
//                dep.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
//                dep.setNamaUrusan("Surat Kuasa Wakil");
//                dep.setAmaun(BigDecimal.valueOf(20));
//                listTransaksi.add(dep);
                break;
            }

            case 10: /* HASIL ANSURAN CUKAI IN MELAKA, CREDIT INTO AK.AMANAH */ {
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(1);
                BigDecimal c = kodUrusan.getCaj();
                if (c != null) {
                    t.setAmaun(c);
                } else {
                    t.setAmaun(BigDecimal.ZERO);
                }
                listTransaksi.add(t);

                // bayaran deposit
                if (BigDecimal.ZERO.compareTo(nilai) < 0) {
                    TransaksiValue dep = new TransaksiValue();
                    dep.setKodUrusan(kodUrusan.getKod());
                    dep.setKodTransaksi(hasilCfg.getProperty("depositAnsuranCukaiTanah"));
                    dep.setNamaUrusan("Deposit Ansuran Cukai Tanah");
                    dep.setAmaun(nilai);
                    listTransaksi.add(dep);
                }

                break;

            }

            case 13: {
                /* consent melaka: based on kod_syarat or bandar/pekan/mukim */
                final String CONSENT_KOD_URUSAN = "PPTGM";
                final String CONSENT_KOD_URUSAN1 = "PLPT";

                // based on simulasi consent Melaka -- START 12/08/2014 ----------------------------------------------------
                if (senaraiHakmilikBersiri.size() > 0) {
                    bilHakmilik += senaraiHakmilikBersiri.size();
                    ArrayList<HakmilikPermohonan> listHp = new ArrayList<HakmilikPermohonan>();
                    if (senaraiHakmilikBersiri != null && senaraiHakmilikBersiri.size() > 0) {
                        for (String h : senaraiHakmilikBersiri) {
                            if (h == null || h.trim().length() == 0) {
                                continue;  // filter out null entries
                            }
                            HakmilikPermohonan hp = new HakmilikPermohonan();
                            Hakmilik hm = new Hakmilik();
                            hm.setIdHakmilik(h);
                            hp.setHakmilik(hm);
                            listHp.add(hp);
                        }
                    }
                    senaraiHakmilikPermohonan = listHp;
                }
                // -- FINISH ---------------------------------------------------------------------------------------------------------------------
                if (kodUrusan.getKod().equals("PLPT")) {
                    Query q = s.createQuery("from KodKadarBayaran kdb where kdb.kodUrusan.id = \'" + kodUrusan.getKod()
                            + "\' and kdb.kategori = :kodSyaratNyata");
                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                        Hakmilik h = hp.getHakmilik();
                        if (h == null || h.getIdHakmilik() == null || h.getIdHakmilik().trim().length() == 0) {
                            continue;
                        }
                        h = hakmilikDAO.findById(h.getIdHakmilik());
                        if (h == null) {
                            throw new RuntimeException("Hakmilik " + hp.getHakmilik().getIdHakmilik() + " tidak wujud!");
                        }
                        if (h.getSyaratNyata().getKategoriHasil() != null) {
                            String kodSyaratNyata = h.getSyaratNyata().getKategoriHasil().getNama();
                            Object o = q.setString("kodSyaratNyata", kodSyaratNyata).uniqueResult();
                            if (o != null) { // kadar exist
                                KodKadarBayaran kdb = (KodKadarBayaran) o;
                                boolean found = false;
                                for (TransaksiValue v : listTransaksi) { // look for same
                                    if (v.getAmaun().equals(kdb.getKadar())) {
                                        found = true;
                                        v.setKuantiti(v.getKuantiti() + 1);
                                        // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                        v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                        break;
                                    }
                                }
                                if (!found) {
                                    TransaksiValue tv = new TransaksiValue();
                                    tv.setKuantiti(1);
                                    tv.setAmaun(kdb.getKadar());
                                    tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                    tv.setKodUrusan(kodUrusan.getKod());
                                    tv.setNamaUrusan(kdb.getKategori());
                                    tv.setSenaraiHakmilik(h.getIdHakmilik());
                                    listTransaksi.add(tv);
                                }
                            } else { // kadar not exist, based on Bandar/Pekan/Mukim. Refer to Meeting Consent at Melaka on 16/6/11 chaired by Pn.Ria
                                String kodBPM = h.getBandarPekanMukim().getNama();
                                String[] results = kodBPM.split(" ");
                                o = q.setString("kodSyaratNyata", results[0].trim().toUpperCase()).uniqueResult();
                                if (o == null) {
                                    throw new RuntimeException("Kadar untuk " + results[0] + " tidak dijumpai");
                                } else {
                                    KodKadarBayaran kdb = (KodKadarBayaran) o;
                                    boolean found = false;
                                    for (TransaksiValue v : listTransaksi) { // look for same
                                        if (v.getAmaun().equals(kdb.getKadar())) {
                                            found = true;
                                            v.setKuantiti(v.getKuantiti() + 1);
                                            // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                            v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        TransaksiValue tv = new TransaksiValue();
                                        tv.setKuantiti(1);
                                        tv.setAmaun(kdb.getKadar());
                                        tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                        tv.setKodUrusan(kodUrusan.getKod());
                                        tv.setNamaUrusan(JENIS_KADAR_CONSENT.get(kdb.getKadar()));
                                        tv.setSenaraiHakmilik(h.getIdHakmilik());
                                        listTransaksi.add(tv);

                                    }
                                }
                            }
                        } else {
                            String Syarat = h.getSyaratNyata().getSyarat();
//                    String kodSyaratNyata = h.getSyaratNyata().getKategoriHasil().getNama();
                            String kodSyaratNyata = h.getKategoriTanah().getNama();
                            Object o = q.setString("kodSyaratNyata", kodSyaratNyata).uniqueResult();
                            if (o != null) { // kadar exist
                                KodKadarBayaran kdb = (KodKadarBayaran) o;
                                boolean found = false;
                                for (TransaksiValue v : listTransaksi) { // look for same
                                    if (v.getAmaun().equals(kdb.getKadar())) {
                                        found = true;
                                        v.setKuantiti(v.getKuantiti() + 1);
                                        // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                        v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                        break;
                                    }
                                }
                                if (!found) {
                                    TransaksiValue tv = new TransaksiValue();
                                    tv.setKuantiti(1);
                                    tv.setAmaun(kdb.getKadar());
                                    tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                    tv.setKodUrusan(kodUrusan.getKod());
                                    tv.setNamaUrusan(kdb.getKategori());
                                    tv.setSenaraiHakmilik(h.getIdHakmilik());
                                    listTransaksi.add(tv);
                                }
                            } else { // kadar not exist, based on Bandar/Pekan/Mukim. Refer to Meeting Consent at Melaka on 16/6/11 chaired by Pn.Ria
                                String kodBPM = h.getBandarPekanMukim().getNama();
                                String[] results = kodBPM.split(" ");
                                o = q.setString("kodSyaratNyata", results[0].trim().toUpperCase()).uniqueResult();
                                if (o == null) {
                                    throw new RuntimeException("Kadar untuk " + results[0] + " tidak dijumpai");
                                } else {
                                    KodKadarBayaran kdb = (KodKadarBayaran) o;
                                    boolean found = false;
                                    for (TransaksiValue v : listTransaksi) { // look for same
                                        if (v.getAmaun().equals(kdb.getKadar())) {
                                            found = true;
                                            v.setKuantiti(v.getKuantiti() + 1);
                                            // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                            v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        TransaksiValue tv = new TransaksiValue();
                                        tv.setKuantiti(1);
                                        tv.setAmaun(kdb.getKadar());
                                        tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                        tv.setKodUrusan(kodUrusan.getKod());
                                        tv.setNamaUrusan(JENIS_KADAR_CONSENT.get(kdb.getKadar()));
                                        tv.setSenaraiHakmilik(h.getIdHakmilik());
                                        listTransaksi.add(tv);

                                    }
                                }
                            }

                        }
                    }

                } else {
                    Query q = s.createQuery("from KodKadarBayaran kdb where kdb.kodUrusan.id = \'" + CONSENT_KOD_URUSAN
                            + "\' and kdb.kategori = :kodSyaratNyata");
                    for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                        Hakmilik h = hp.getHakmilik();
                        if (h == null || h.getIdHakmilik() == null || h.getIdHakmilik().trim().length() == 0) {
                            continue;
                        }
                        h = hakmilikDAO.findById(h.getIdHakmilik());
                        if (h == null) {
                            throw new RuntimeException("Hakmilik " + hp.getHakmilik().getIdHakmilik() + " tidak wujud!");
                        }
                        if (h.getSyaratNyata().getKategoriHasil() != null) {
                            String kodSyaratNyata = h.getSyaratNyata().getKategoriHasil().getNama();
                            Object o = q.setString("kodSyaratNyata", kodSyaratNyata).uniqueResult();
                            if (o != null) { // kadar exist
                                KodKadarBayaran kdb = (KodKadarBayaran) o;
                                boolean found = false;
                                for (TransaksiValue v : listTransaksi) { // look for same
                                    if (v.getAmaun().equals(kdb.getKadar())) {
                                        found = true;
                                        v.setKuantiti(v.getKuantiti() + 1);
                                        // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                        v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                        break;
                                    }
                                }
                                if (!found) {
                                    TransaksiValue tv = new TransaksiValue();
                                    tv.setKuantiti(1);
                                    tv.setAmaun(kdb.getKadar());
                                    tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                    tv.setKodUrusan(kodUrusan.getKod());
                                    tv.setNamaUrusan(kdb.getKategori());
                                    tv.setSenaraiHakmilik(h.getIdHakmilik());
                                    listTransaksi.add(tv);
                                }
                            } else { // kadar not exist, based on Bandar/Pekan/Mukim. Refer to Meeting Consent at Melaka on 16/6/11 chaired by Pn.Ria
                                String kodBPM = h.getBandarPekanMukim().getNama();
                                String[] results = kodBPM.split(" ");
                                o = q.setString("kodSyaratNyata", results[0].trim().toUpperCase()).uniqueResult();
                                if (o == null) {
                                    throw new RuntimeException("Kadar untuk " + results[0] + " tidak dijumpai");
                                } else {
                                    KodKadarBayaran kdb = (KodKadarBayaran) o;
                                    boolean found = false;
                                    for (TransaksiValue v : listTransaksi) { // look for same
                                        if (v.getAmaun().equals(kdb.getKadar())) {
                                            found = true;
                                            v.setKuantiti(v.getKuantiti() + 1);
                                            // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                            v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        TransaksiValue tv = new TransaksiValue();
                                        tv.setKuantiti(1);
                                        tv.setAmaun(kdb.getKadar());
                                        tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                        tv.setKodUrusan(kodUrusan.getKod());
                                        tv.setNamaUrusan(JENIS_KADAR_CONSENT.get(kdb.getKadar()));
                                        tv.setSenaraiHakmilik(h.getIdHakmilik());
                                        listTransaksi.add(tv);

                                    }
                                }
                            }
                        } else {
                            String Syarat = h.getSyaratNyata().getSyarat();
//                    String kodSyaratNyata = h.getSyaratNyata().getKategoriHasil().getNama();
                            String kodSyaratNyata = h.getKategoriTanah().getNama();
                            Object o = q.setString("kodSyaratNyata", kodSyaratNyata).uniqueResult();
                            if (o != null) { // kadar exist
                                KodKadarBayaran kdb = (KodKadarBayaran) o;
                                boolean found = false;
                                for (TransaksiValue v : listTransaksi) { // look for same
                                    if (v.getAmaun().equals(kdb.getKadar())) {
                                        found = true;
                                        v.setKuantiti(v.getKuantiti() + 1);
                                        // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                        v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                        break;
                                    }
                                }
                                if (!found) {
                                    TransaksiValue tv = new TransaksiValue();
                                    tv.setKuantiti(1);
                                    tv.setAmaun(kdb.getKadar());
                                    tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                    tv.setKodUrusan(kodUrusan.getKod());
                                    tv.setNamaUrusan(kdb.getKategori());
                                    tv.setSenaraiHakmilik(h.getIdHakmilik());
                                    listTransaksi.add(tv);
                                }
                            } else { // kadar not exist, based on Bandar/Pekan/Mukim. Refer to Meeting Consent at Melaka on 16/6/11 chaired by Pn.Ria
                                String kodBPM = h.getBandarPekanMukim().getNama();
                                String[] results = kodBPM.split(" ");
                                o = q.setString("kodSyaratNyata", results[0].trim().toUpperCase()).uniqueResult();
                                if (o == null) {
                                    throw new RuntimeException("Kadar untuk " + results[0] + " tidak dijumpai");
                                } else {
                                    KodKadarBayaran kdb = (KodKadarBayaran) o;
                                    boolean found = false;
                                    for (TransaksiValue v : listTransaksi) { // look for same
                                        if (v.getAmaun().equals(kdb.getKadar())) {
                                            found = true;
                                            v.setKuantiti(v.getKuantiti() + 1);
                                            // v.setAmaun(kdb.getKadar().add(v.getAmaun())); ONLY UPDATED AT LAST * with kuantiti
                                            v.setSenaraiHakmilik(v.getSenaraiHakmilik() + "," + h.getIdHakmilik());
                                            break;
                                        }
                                    }
                                    if (!found) {
                                        TransaksiValue tv = new TransaksiValue();
                                        tv.setKuantiti(1);
                                        tv.setAmaun(kdb.getKadar());
                                        tv.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                                        tv.setKodUrusan(kodUrusan.getKod());
                                        tv.setNamaUrusan(JENIS_KADAR_CONSENT.get(kdb.getKadar()));
                                        tv.setSenaraiHakmilik(h.getIdHakmilik());
                                        listTransaksi.add(tv);

                                    }
                                }
                            }

                        }
                    }
                }

                // TODO hakmilik bersiri
                // update amount based on kuantiti
                for (TransaksiValue tv : listTransaksi) {
                    tv.setAmaun(tv.getAmaun().multiply(new BigDecimal(tv.getKuantiti())));
                }

                break;
            }
            case 60: /*pembangunan - PPCS melaka*/ {
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.kod = :kodUrusan "
                        + "and kb.milikDaerah = 'X' ").setString("kodUrusan", kodUrusan.getKod()).list();
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                BigDecimal c = BigDecimal.ZERO;
                BigDecimal a = BigDecimal.ZERO;
                for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
                    HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                    if (hp.getHakmilik() != null && hp.getHakmilik().getIdHakmilik() != null) {
                        Hakmilik hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                        for (int j = 0; j < kb.size(); j++) {
                            KodKadarBayaran kkb = kb.get(j);
                            if (kkb.getKategori().equals("BANDAR") && (hakmilik.getBandarPekanMukim().getNama().contains("BANDAR") || hakmilik.getBandarPekanMukim().getNama().contains("Bandar"))) {
                                c = kkb.getKadar();
                            } else if (kkb.getKategori().equals("PEKAN") && (hakmilik.getBandarPekanMukim().getNama().contains("PEKAN") || hakmilik.getBandarPekanMukim().getNama().contains("Pekan"))) {
                                c = kkb.getKadar();
                            } else if (kkb.getKategori().equals("MUKIM") && (hakmilik.getBandarPekanMukim().getNama().contains("MUKIM") || hakmilik.getBandarPekanMukim().getNama().contains("Mukim"))) {
                                c = kkb.getKadar();
                            }

                        }
                        a = a.add(c);
                    }
                }
                t.setAmaun(a);
                listTransaksi.add(t);
                break;
            }
            case 61: /*pembangunan - TSKKT melaka*/ {
                kategori = "Y";
                if (kategori == null) {
                    throw new RuntimeException("kategori nil diberikan untuk kategori tukar syarat!");
                }
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                BigDecimal luas = BigDecimal.ZERO;
                BigDecimal a = BigDecimal.ZERO;
                List<KodKadarBayaran> kb = null;
                for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
                    HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                    if (hp.getHakmilik() != null && hp.getHakmilik().getIdHakmilik() != null) {
                        Hakmilik hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                        if (hakmilik.getKodUnitLuas().getKod().equals("M")) {
                            luas = hakmilik.getLuas().divide(new BigDecimal(10000));
                        } else if (hakmilik.getKodUnitLuas().getKod().equals("H")) {
                            luas = hakmilik.getLuas();
                        } else {
                            LOG.error("Kod Unit Luas tidak tepat!");
                            throw new RuntimeException("Kod Unit Luas tidak tepat!");
                        }
//                        if (kategori.equals("T")) {
//                            kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
//                                    + "kb.kodUrusan.kod = :kodUrusan "
//                                    + "and kb.milikDaerah = 'X' "
//                                    + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
//                        }
                        if (kategori.equals("Y")) {
                            kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                                    + "kb.kodUrusan.kod = :kodUrusan "
                                    + "and kb.milikDaerah = 'X' "
                                    + "and kb.kategori = :kategori "
                                    + "and kb.nilaiMinimum <= :luas "
                                    + "and (kb.nilaiMaksimum >= :luas "
                                    + "or kb.nilaiMaksimum is null)").setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("luas", luas).setString("kategori", kategori).list();
                        }
                        if (kb.size() != 1) {
                            LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                            throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                        }
                        KodKadarBayaran k = kb.get(0);
                        a = a.add(k.getKadar());
                    }
                }
                t.setAmaun(a);
                listTransaksi.add(t);
                break;
            }

            case 62: /*pembangunan - PPCS,PPCB,PYTN,SBMS N9*/ {
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                BigDecimal luas = BigDecimal.ZERO;
                BigDecimal a = BigDecimal.ZERO;
                BigDecimal sum = BigDecimal.ZERO;
                String bpm;
                for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
                    HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                    if (hp.getHakmilik() != null && hp.getHakmilik().getIdHakmilik() != null) {
                        Hakmilik hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                        if (hakmilik.getKodUnitLuas().getKod().equals("M")) {
                            luas = hakmilik.getLuas().divide(new BigDecimal(10000));
                        } else if (hakmilik.getKodUnitLuas().getKod().equals("H")) {
                            luas = hakmilik.getLuas();
                        } else {
                            LOG.error("Kod Unit Luas tidak tepat!");
                            throw new RuntimeException("Kod Unit Luas tidak tepat!");
                        }
                    }
                    sum = sum.add(luas);
                }

                HakmilikPermohonan hm = senaraiHakmilikPermohonan.get(0);
                Hakmilik h = hakmilikDAO.findById(hm.getHakmilik().getIdHakmilik());
                if (h.getBandarPekanMukim().getNama().contains("Mukim") || (h.getBandarPekanMukim().getNama().contains("MUKIM"))) {
                    bpm = "MUKIM";
                } else {
                    bpm = "BANDAR";
                }

                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.kod = :kodUrusan "
                        + "and kb.milikDaerah = 'X' "
                        + "and kb.kategori = :bpm "
                        + "and kb.nilaiMinimum <= :luas "
                        + "and (kb.nilaiMaksimum >= :luas "
                        + "or kb.nilaiMaksimum is null)").setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("luas", sum).setString("bpm", bpm).list();
                if (kb.size() != 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                }
                KodKadarBayaran k = kb.get(0);
                a = k.getKadar();
                if (luas.doubleValue() >= 20 && k.getKategori().equals("BANDAR")) {
                    List<KodKadarBayaran> kkb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'X' "
                            + "and kb.kategori = 'KHAS'").setString("kodUrusan", kodUrusan.getKod()).list();
                    if (kkb.size() != 1) {
                        LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                        throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    }
                    KodKadarBayaran b = kkb.get(0);
//                    a = ((sum.subtract(new BigDecimal(10))).divide(b.getNilaiMinimum()).multiply(b.getKadar())).add(new BigDecimal(1000));
                    int z = ((sum.subtract(new BigDecimal(10))).divide(b.getNilaiMinimum())).intValue();
                    BigDecimal w = new BigDecimal(z).multiply(b.getKadar());
                    double x = (sum.subtract(new BigDecimal(10))).remainder(b.getNilaiMinimum()).doubleValue();
                    BigDecimal bal = BigDecimal.ZERO;
                    if (x > 0.0 && x < 5.0) {
                        bal = b.getKadar();
                    } else if (x > 5.0) {
                        LOG.error("Pengiraan Kadar tidak tepat!");
                        throw new RuntimeException("Pengiraan Kadar tidak tepat!");
                    } else if (x == 0.0) {
                        LOG.info("Tiada Baki.");
                    }
                    a = (w.add(bal)).add(new BigDecimal(1000));
                }
                t.setAmaun(a);
                listTransaksi.add(t);
                break;
            }
            case 63: /* pembangunan - TSPTD,TSPTG,TSMMK N9 */ {
                if (kategori == null) {
                    throw new RuntimeException("kategori nil diberikan untuk kategori tukar syarat!");
                }
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                List<KodKadarBayaran> kb = null;
                BigDecimal a = BigDecimal.ZERO;
                int b = bilHakmilik;
                if (kategori.equals("TSLK")) {
                    kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'X' "
                            + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                } else if (kategori.equals("TSSKP")) {
                    kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'X' "
                            + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                } else if (kategori.equals("TSSKB")) {
                    kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'X' "
                            + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                } else if (kategori.equals("TSSKI")) {
                    kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'X' "
                            + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                } else if (kategori.equals("TSPSN")) {
                    kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'X' "
                            + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                }
                if (kb.size() != 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                }
                KodKadarBayaran k = kb.get(0);
                a = k.getKadar().multiply(new BigDecimal(b));
                t.setAmaun(a);
                listTransaksi.add(t);
                break;

            }
            case 64: /* pembangunan - TSPSS N9 */ {
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                BigDecimal luas = BigDecimal.ZERO;
                BigDecimal a = BigDecimal.ZERO;
                String bpm;
                for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
                    HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                    if (hp.getHakmilik() != null && hp.getHakmilik().getIdHakmilik() != null) {
                        Hakmilik hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                        if (hakmilik.getKodUnitLuas().getKod().equals("M")) {
                            luas = hakmilik.getLuas().divide(new BigDecimal(10000));
                        } else if (hakmilik.getKodUnitLuas().getKod().equals("H")) {
                            luas = hakmilik.getLuas();
                        } else {
                            LOG.error("Kod Unit Luas tidak tepat!");
                            throw new RuntimeException("Kod Unit Luas tidak tepat!");
                        }
                        if (hakmilik.getBandarPekanMukim().getNama().contains("Mukim") || (hakmilik.getBandarPekanMukim().getNama().contains("MUKIM"))) {
                            bpm = "MUKIM";
                        } else {
                            bpm = "BANDAR";
                        }

                        List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                                + "kb.kodUrusan.kod = :kodUrusan "
                                + "and kb.milikDaerah = 'X' "
                                + "and kb.kategori = :bpm "
                                + "and kb.nilaiMinimum <= :luas "
                                + "and (kb.nilaiMaksimum >= :luas "
                                + "or kb.nilaiMaksimum is null)").setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("luas", luas).setString("bpm", bpm).list();
                        if (kb.size() != 1) {
                            LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                            throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                        }
                        KodKadarBayaran k = kb.get(0);
                        a = a.add(k.getKadar());
                        if (luas.doubleValue() >= 91 && k.getKategori().equals("BANDAR")) {
                            List<KodKadarBayaran> kkb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                                    + "kb.kodUrusan.kod = :kodUrusan "
                                    + "and kb.milikDaerah = 'X' "
                                    + "and kb.kategori = 'KHAS'").setString("kodUrusan", kodUrusan.getKod()).list();
                            if (kkb.size() != 1) {
                                LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                                throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                            }
                            KodKadarBayaran b = kkb.get(0);
                            a = a.add((luas.subtract(new BigDecimal(10))).divide(b.getNilaiMinimum()).multiply(b.getKadar()));
                        }
                    }
                }
                t.setAmaun(a);
                listTransaksi.add(t);
                break;
            }
            case 65: /*pembangunan - TSPSS melaka*/ {
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.kod = :kodUrusan "
                        + "and kb.milikDaerah = 'X' "
                        + "and kb.kategori is null").setString("kodUrusan", kodUrusan.getKod()).list();
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                BigDecimal a = BigDecimal.ZERO;
                for (int j = 0; j < kb.size(); j++) {
                    KodKadarBayaran kkb = kb.get(j);
                    a = kkb.getKadar().multiply(new BigDecimal(bilHakmilik));
                }
                t.setAmaun(a);
                listTransaksi.add(t);
                break;
            }
            case 66: /*pembangunan - TSBSN TSKSN TSPSN melaka*/ {
                boolean byKategoriBangunan = false;
                if (milikDaerah == 'Y') {
                    kategori = "PTD";
                } else if (milikDaerah == 'T') {
                    kategori = "PTG";
                }
                for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
                    HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                    LOG.debug("hp:" + hp.getHakmilik().getIdHakmilik());
                    Hakmilik hak = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    if (hak.getKategoriTanah().getKod().equals("2") || hak.getKategoriTanah().getKod().equals("3")) {
                        byKategoriBangunan = true;
                    }
                }

                List<KodKadarBayaran> kb = new ArrayList<KodKadarBayaran>();
                if (byKategoriBangunan) {
                    kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'Y' "
                            + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                } else {
                    kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.milikDaerah = 'X' "
                            + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                }

                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                BigDecimal a = BigDecimal.ZERO;
                for (int j = 0; j < kb.size(); j++) {
                    KodKadarBayaran kkb = kb.get(j);
                    a = kkb.getKadar().multiply(new BigDecimal(bilHakmilik));
                }
                t.setAmaun(a);
                listTransaksi.add(t);
                break;
            }
            case 14: /*pengambilan - 31BC N9*/ {
                TransaksiValue t = new TransaksiValue();
                t.setKuantiti(1);
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                BigDecimal B = BigDecimal.ZERO;
                BigDecimal FixFee = BigDecimal.valueOf(1000);
                BigDecimal FixFeex = BigDecimal.valueOf(10000);
                BigDecimal BilPihak = BigDecimal.ZERO;
                BigDecimal BilPihak2 = BigDecimal.ZERO;
                BigDecimal jumTuanTanah = BigDecimal.ZERO;
                BigDecimal group4 = BigDecimal.ZERO;
                BigDecimal Default = BigDecimal.TEN;
                BigDecimal amount = BigDecimal.valueOf(0.00);
                BigDecimal TotalAmount = BigDecimal.valueOf(0.00);
                int counter1 = 5;
                int rate1 = 100;
                int ratex1 = 1000;
                int ratex2 = 2000;
                int ratex3 = 10;
                System.out.println("senaraiHakmilik" + senaraiHakmilikPermohonan.size());
                for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
                    HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                    LOG.debug("hp:" + hp.getHakmilik().getIdHakmilik());
                    Hakmilik hak = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    LOG.debug("hakmilik :" + hak.getIdHakmilik());
//                    List<HakmilikPihakBerkepentingan> listPihak = hak.getSenaraiPihakBerkepentingan();
//                    List<HakmilikPihakBerkepentingan> listPihak = (List<HakmilikPihakBerkepentingan>) s.createQuery("from HakmilikPihakBerkepentingan hp where "
//                            + "hp.hakmilik.idHakmilik = :hakmilik and "
//                            + "hp.aktif = 'Y'").setString("hakmilik", hak.getIdHakmilik()).list();

                    List<HakmilikPihakBerkepentingan> listPihak = s.createQuery("select distinct hp.pihak.idPihak from HakmilikPihakBerkepentingan hp where "
                            + "hp.hakmilik.idHakmilik = :hakmilik and "
                            + "hp.aktif = 'Y' and hp.jenis.kod = 'PM'").setString("hakmilik", hak.getIdHakmilik()).list();

                    System.out.println("list pihak sql " + listPihak.size());
                    BilPihak = new BigDecimal(listPihak.size());
                    System.out.println("Bilangan Pihak 1 : " + BilPihak);

                    BilPihak2 = BilPihak2.add(BilPihak);
                    LOG.info("BilPihak2 --> " + BilPihak2);

                }
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and kb.kategori = :kategori "
                        + "and kb.nilaiMinimum <= :BilPihak2 and (kb.nilaiMaksimum >= :BilPihak2 "
                        + "or kb.nilaiMaksimum is null)").setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("BilPihak2", BilPihak2).setString("kategori", kategori).list();
                System.out.println("kb list" + kb.size());
                if (kb.size() != 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                }
                KodKadarBayaran k = kb.get(0);
                LOG.info("BilPihak2 luar loop --> " + BilPihak2);
                if (kategori.equals("KEPERLUAN AWAM")) {
                    if (1 <= BilPihak2.intValue() && BilPihak2.intValue() <= 10) {
                        amount = FixFee;
                    } else if (10 < BilPihak2.intValue()) {
                        B = BilPihak2.subtract(Default);
                        BigDecimal group1;
                        amount = B.multiply(k.getKadar());
                        amount = amount.add(FixFee);

                    }
                    TotalAmount = TotalAmount.add(amount);
                } else if (kategori.equals("BUKAN KEPERLUAN AWAM")) {
                    if (1 <= BilPihak2.intValue() && BilPihak2.intValue() <= 10) {
                        amount = FixFeex;
                    } else if (10 < BilPihak2.intValue() && BilPihak2.intValue() <= 15) {
                        B = BilPihak2.subtract(Default);
                        amount = B.multiply(k.getKadar());
                        amount = amount.add(FixFeex);
                    } else if (15 < BilPihak2.intValue() && BilPihak2.intValue() <= 20) {
                        B = BilPihak2.subtract(k.getNilaiMinimum());
                        BigDecimal group1 = new BigDecimal(rate1);
                        group1 = group1.multiply(new BigDecimal(counter1));
                        amount = B.multiply(k.getKadar());
                        amount = amount.add(FixFeex);
                    } else if (20 < BilPihak2.intValue()) {
//                        B = BilPihak2.subtract(k.getNilaiMinimum());
//                        BigDecimal group1 = new BigDecimal(ratex1);
//                        group1 = group1.multiply(new BigDecimal(counter1));
//                        BigDecimal group2 = new BigDecimal(ratex2);
//                        group2 = group2.multiply(new BigDecimal(counter1));
//                        amount = B.multiply(k.getKadar()).add(group1).add(group2);
//                        amount = amount.add(FixFeex);

                        BigDecimal group1 = FixFeex;
//                        BigDecimal group1 = new BigDecimal(ratex3);
//                        group1 = group1.multiply(FixFeex);
                        BigDecimal group2 = new BigDecimal(counter1);
                        group2 = group2.multiply(FixFee);
                        BigDecimal group3 = new BigDecimal(counter1);
                        group3 = group3.multiply(new BigDecimal(ratex2));
                        if (21 <= BilPihak2.intValue()) {
                            B = BilPihak2.subtract(k.getNilaiMinimum());
                            group4 = B.add(BigDecimal.ONE);
                            group4 = group4.multiply(k.getKadar());
                        }
                        BigDecimal group5 = BigDecimal.ZERO;
                        group5 = group1.add(group2.add(group3));
                        amount = group5.add(group4);
                    }
                    TotalAmount = TotalAmount.add(amount);
                }
                t.setAmaun(TotalAmount);
                listTransaksi.add(t);
                break;

            }

            case 15: /*pengambilan - deposit untuk Bantahan Mahkamah*/ {
                if (nilai == null) {
                    throw new RuntimeException("Sila masukkan jumlah bayaran");
                }
                BigDecimal c = nilai;
                BigDecimal d = new BigDecimal(3000);

                if (c.compareTo(d) == -1 || c.compareTo(d) == 0) {
                    TransaksiValue t = new TransaksiValue();
                    t.setKodUrusan(kodUrusan.getKod());
                    t.setNamaUrusan(kodUrusan.getNama());
                    t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                    t.setAmaun(c);
                    listTransaksi.add(t);
                } else {
                    LOG.error("Sila masukkan jumlah deposit untuk " + kodUrusan.getKod() + " kurang dari RM 3000");
                    throw new RuntimeException("Sila masukkan jumlah deposit untuk" + kodUrusan.getKod() + " kurang dari RM 3000");
                }
                break;
            }

            case 101: /*pengambilan - 31BC N9 x Awam*/ {
                TransaksiValue t = new TransaksiValue();
                t.setKuantiti(1);
                BigDecimal B = BigDecimal.ZERO;
                BigDecimal FixFee = BigDecimal.valueOf(10000);
                BigDecimal BilPihak = BigDecimal.ZERO;
                BigDecimal Default = BigDecimal.TEN;
                BigDecimal amount = BigDecimal.valueOf(0.00);
                BigDecimal TotalAmount = BigDecimal.valueOf(0.00);
                List<HakmilikPihakBerkepentingan> listPihak;
                int counter1 = 5;
                int rate1 = 1000;
                int rate2 = 2000;
                for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
                    System.out.println("senaraiHakmilik" + senaraiHakmilikPermohonan.size());
                    HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(i);
                    System.out.println("hp:" + hp.getHakmilik().getIdHakmilik());
                    Hakmilik hak = hp.getHakmilik();
                    System.out.println("hakmilik :" + hak.getIdHakmilik());
                    listPihak = hak.getSenaraiPihakBerkepentingan();
                    System.out.println("list" + hak.getSenaraiPihakBerkepentingan().size());
                    BilPihak = new BigDecimal(listPihak.size());
                    System.out.println("Bilangan Pihak 2 : " + BilPihak);
                    List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.id = :kodUrusan "
                            + "and kb.nilaiMinimum <= :BilPihak and (kb.nilaiMaksimum >= :BilPihak "
                            + "or kb.nilaiMaksimum is null)").setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("BilPihak", BilPihak).list();
                    System.out.println("kb list" + kb.size());
                    if (kb.size() != 1) {
                        LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                        throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    }
                    KodKadarBayaran k = kb.get(0);

                    if (1 <= BilPihak.intValue() && BilPihak.intValue() <= 10) {
                        amount = FixFee;
                    } else if (10 < BilPihak.intValue() && BilPihak.intValue() <= 15) {
                        B = BilPihak.subtract(Default);
                        amount = B.multiply(k.getKadar());
                        amount = amount.add(FixFee);
                    } else if (15 < BilPihak.intValue() && BilPihak.intValue() <= 20) {
                        B = BilPihak.subtract(k.getNilaiMinimum());
                        BigDecimal group1 = new BigDecimal(rate1);
                        group1 = group1.multiply(new BigDecimal(counter1));
                        amount = B.multiply(k.getKadar());
                        amount = amount.add(FixFee);
                    } else if (20 < BilPihak.intValue()) {
                        B = BilPihak.subtract(k.getNilaiMinimum());
                        BigDecimal group1 = new BigDecimal(rate1);
                        group1 = group1.multiply(new BigDecimal(counter1));
                        BigDecimal group2 = new BigDecimal(rate2);
                        group2 = group2.multiply(new BigDecimal(counter1));
                        amount = B.multiply(k.getKadar()).add(group1).add(group2);
                        amount = amount.add(FixFee);

                    }
                    TotalAmount = TotalAmount.add(amount);
                }
                t.setAmaun(TotalAmount);
                listTransaksi.add(t);
                break;
            }

            case 12: /*For RLPS- Pelupusan*/ {
                if (nilai == null) {
                    throw new RuntimeException("Sila masukkan jumlah bayaran");
                }
                BigDecimal c = nilai;
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setAmaun(c);
                listTransaksi.add(t);
                break;
            }
            case 77: /*For RYKN- Pelupusan*/ {

                LOG.info("ID Permohonan Sebelum >> " + teks1);
                PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                List<PermohonanTuntutanKos> mohonTuntutKosList = (List<PermohonanTuntutanKos>) s.createQuery("from PermohonanTuntutanKos ptk where "
                        + "ptk.permohonan.idPermohonan = :teks1"
                        + " and ptk.kodTuntut.kod = 'DISPRM'").setString("teks1", teks1).list();
                if (mohonTuntutKosList.isEmpty()) {
                    throw new RuntimeException("Id Permohonan Tidak Wujud");
                } else {
                    mohonTuntutKos = mohonTuntutKosList.get(0);
                    LOG.info("Amaun Premium :" + mohonTuntutKos.getAmaunTuntutan());
                    BigDecimal c = mohonTuntutKos.getAmaunTuntutan();
                    TransaksiValue t = new TransaksiValue();
                    t.setKodUrusan(kodUrusan.getKod());
                    t.setNamaUrusan("1/3 Nilaian Premium");
                    t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                    double divideTotal = 0.00;
                    divideTotal = 1.00 / 3.00;
                    divideTotal = divideTotal * (Double.parseDouble(c.toString()));
                    BigDecimal total = new BigDecimal(divideTotal);
                    double totalDouble = Double.parseDouble(total.toString());
                    DecimalFormat df = new DecimalFormat("#.##");
                    LOG.info("Double = " + df.format(totalDouble));
                    total = BigDecimal.valueOf(Double.parseDouble(df.format(totalDouble)));
                    LOG.info("Total = " + total);
                    t.setAmaun(total);
                    listTransaksi.add(t);
                    break;
                }

            }
            case 78: /*For MMMCL- Pelupusan*/ {
                LOG.info("ID Permohonan Sebelum >> " + teks1);
                if (milikDaerah == 'Y') {
                    kategori = "PTD";
                } else if (milikDaerah == 'T') {
                    kategori = "PTG";
                }
                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.kod = :kodUrusan "
                        + "and kb.milikDaerah = 'X' "
                        + "and kb.kategori = :kategori").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                TransaksiValue t = new TransaksiValue();
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan("Fi Pendaftaran Urusan.");
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                t.setKuantiti(senaraiHakmilikPermohonan.size());
                BigDecimal a = BigDecimal.ZERO;
                for (int j = 0; j < kb.size(); j++) {
                    KodKadarBayaran kkb = kb.get(j);
                    a = kkb.getKadar().multiply(new BigDecimal(bilHakmilik));
                }
                t.setAmaun(a);
                listTransaksi.add(t);

                //72454 : Based on comment 13.08.14 (Widiaastuty Sofyan)
                TransaksiValue t1 = new TransaksiValue();
                t1.setKodUrusan(kodUrusan.getKod());
                t1.setNamaUrusan(kodUrusan.getNama());
                t1.setKodTransaksi(kodTransaksiDAO.findById("72454").getKod());
                t1.setKuantiti(1);
                t1.setAmaun(new BigDecimal(50));

                listTransaksi.add(t1);
                break;
            }
            case 79: /*For LMCRG- Pelupusan*/ {
                List<KodKadarBayaran> kb1 = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.kod = :kodUrusan "
                        + "and kb.kategori = :kategori ").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                if (!kb1.isEmpty()) {
                    if (kb1.size() != 1) {
                        LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                        throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    }
                    KodKadarBayaran k = kb1.get(0);
                    BigDecimal c = k.getKadar();
                    TransaksiValue t = new TransaksiValue();
                    t.setKodUrusan(kodUrusan.getKod());
                    t.setNamaUrusan(kodUrusan.getNama());
                    t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                    t.setAmaun(c);
                    listTransaksi.add(t);
                    break;
                } else {
                    throw new RuntimeException("Sila pilih jenis lesen untuk permohonan");
                }

            }
            case 80: /*For MLCRG- Pelupusan*/ {
                List<KodKadarBayaran> kb1 = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.kod = :kodUrusan "
                        + "and kb.kategori = :kategori ").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                if (!kb1.isEmpty()) {
                    if (kb1.size() != 1) {
                        LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                        throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    }
                    KodKadarBayaran k = kb1.get(0);
                    BigDecimal c = k.getKadar();
                    TransaksiValue t = new TransaksiValue();
                    t.setKodUrusan(kodUrusan.getKod());
                    t.setNamaUrusan(kodUrusan.getNama());
                    t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                    t.setAmaun(c);
                    listTransaksi.add(t);
                    break;
                } else {
                    throw new RuntimeException("Sila pilih jenis lesen untuk permohonan");
                }

            }
            case 99:/*For PBMT- Pelupusan*/ {
                LOG.info("ID Kelompok >> " + teks1);
                List<KodKadarBayaran> kb1 = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.kod = :kodUrusan "
                        + "and kb.kategori = :kategori "
                        + "and (kb.nilaiMaksimum >= :nilai or kb.nilaiMaksimum is null)").setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("nilai", nilai).setString("kategori", kategori).list();
                if (!kb1.isEmpty()) {
                    List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                            + "kb.kodUrusan.kod = :kodUrusan "
                            + "and kb.kategori = :kategori "
                            + "and (kb.nilaiMinimum <= :nilai "
                            + "and (kb.nilaiMaksimum >= :nilai or kb.nilaiMaksimum is null) )").setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("nilai", nilai).setString("kategori", kategori).list();
                    if (kb.size() != 1) {
                        LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                        throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    }
                    KodKadarBayaran k = kb.get(0);
                    BigDecimal c = k.getKadar();
                    TransaksiValue t = new TransaksiValue();
                    t.setKodUrusan(kodUrusan.getKod());
                    t.setNamaUrusan(kodUrusan.getNama());
                    t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
                    t.setAmaun(c);
                    listTransaksi.add(t);
                    break;
                } else {
                    throw new RuntimeException("Luas sudah melebihi kadar yang ditetapkan. Sila isikan luas semula");
                }

            }

            case 89:/*For rAYUAN sbms*/ {
                TransaksiValue t = new TransaksiValue();
                t.setKuantiti(1);
                t.setKodUrusan(kodUrusan.getKod());
                t.setNamaUrusan(kodUrusan.getNama());
                t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());

                List<KodKadarBayaran> kb = (List<KodKadarBayaran>) s.createQuery("from KodKadarBayaran kb where "
                        + "kb.kodUrusan.id = :kodUrusan "
                        + "and kb.kategori = :kategori ").setString("kodUrusan", kodUrusan.getKod()).setString("kategori", kategori).list();
                System.out.println("kb list" + kb.size());
                if (kb.size() != 1) {
                    LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                    throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() + " tidak tepat!");
                }
                KodKadarBayaran k = kb.get(0);

                t.setAmaun(k.getKadar());
                listTransaksi.add(t);

            }

        }

        //LOG.info("jumlah caj=" + t.getAmaun());
        return listTransaksi;
    }

    /*
     @SuppressWarnings("unchecked")
     <<<<<<< .mine
     public BigDecimal calculateFee(KodUrusan kodUrusan,
     BigDecimal nilai,
     String kategori,
     public ArrayList<TransaksiValue> calculateFee(KodUrusan kodUrusan,
     BigDecimal nilai, // input at SPOC
     Date tarikh, // input at SPOC
     String kategori, // input at SPOC
     >>>>>>> .r10734
     List<HakmilikPermohonan> senaraiHakmilikPermohonan,
     List<String> senaraiHakmilikBersiri){
     <<<<<<< .mine
     if (debug) LOG.debug("nilai=" + nilai);
     BigDecimal caj = BigDecimal.ZERO;
     if (debug) LOG.debug("nilaiHarta=" + nilai);
     //BigDecimal caj = BigDecimal.ZERO;
     >>>>>>> .r10734
     KodKategoriBayaran kg = kodUrusan.getKategoriBayaran();
     int bilHakmilik = 0;
     char milikDaerah = 'X'; /*Hakmilik Daerah atau Pendaftar, X for not applicable
     for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++){
     HakmilikPermohonan h = senaraiHakmilikPermohonan.get(i);
     if (h.getHakmilik() != null && h.getHakmilik().getIdHakmilik() != null){
     // get sampling for the first record only
     if (i == 0){
     Hakmilik hm = hakmilikDAO.findById(h.getHakmilik().getIdHakmilik());
     if (hm != null){
     h.setHakmilik(hm);
     milikDaerah = hm.getKodHakmilik().getMilikDaerah();
     }
     }
     bilHakmilik++;
     }
     }
     if (senaraiHakmilikBersiri != null){
     bilHakmilik += senaraiHakmilikBersiri.size();
     }
     if (debug) LOG.debug("bilHakmilik=" + bilHakmilik);

     // get sampling for the first record only
     if (milikDaerah == 'X' && bilHakmilik > 0 && senaraiHakmilikBersiri != null &&
     senaraiHakmilikBersiri.size() > 0){
     Hakmilik hm = hakmilikDAO.findById(senaraiHakmilikBersiri.get(0));
     if (hm != null) {
     milikDaerah = hm.getKodHakmilik().getMilikDaerah();
     } else{
     LOG.warn("Couldn't find idHakmilik " + senaraiHakmilikBersiri.get(0));
     }
     }
     if (debug) LOG.debug("milikDaerah=" + milikDaerah);

     // just set hakmilik to minimum 1 in case need to charge
     if (bilHakmilik == 0) bilHakmilik = 1;

     Session s = sessionProvider.get();

     ArrayList<TransaksiValue> listTransaksi = new ArrayList<TransaksiValue>();

     TransaksiValue t = new TransaksiValue();
     t.setKodUrusan(kodUrusan.getKod());
     t.setNamaUrusan(kodUrusan.getNama());
     t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());

     if (kg == null){  flat rate
     BigDecimal c = kodUrusan.getCaj();
     if (c != null) t.setAmaun(c);
     else t.setAmaun(BigDecimal.ZERO);

     listTransaksi.add(t);
     return listTransaksi;
     }

     switch (kg.getKod()){

     case 0 : flat rate
     {
     t.setKuantiti(1);
     BigDecimal c = kodUrusan.getCaj();
     if (c != null) t.setAmaun(c);
     else t.setAmaun(BigDecimal.ZERO);
     listTransaksi.add(t);

     break;

     }
     case 1 :  kategori 1 = nilaian harga tanah
     {
     if (nilai == null) throw new RuntimeException("Amaun Transaksi tidak dinyatakan untuk pengiraaan");

     StringBuilder q = new StringBuilder("from KodKadarBayaran kb where " +
     "kb.kodUrusan.id = :kodUrusan " +
     "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah) " +
     "and kb.nilaiMinimum <= :nilaiHarta and (kb.nilaiMaksimum >= :nilaiHarta " +
     "or kb.nilaiMaksimum is null)");
     if (kategori != null && kategori.trim().length() > 0){
     q.append(" and kb.kategori = :kategori");
     }


     Query query = s.createQuery(q.toString())
     //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
     .setString("kodUrusan", kodUrusan.getKod())
     .setCharacter("milikDaerah", milikDaerah)
     .setBigDecimal("nilaiHarta", nilai);
     if (kategori != null && kategori.trim().length() > 0){
     q.append(" and kb.kategori = :kategori");
     }
     List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();
     if (kb.size() != 1){
     LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     }
     KodKadarBayaran k = kb.get(0);
     if (k.getPeratus() == 'Y'){
     <<<<<<< .mine
     caj = monetaryUtil.roundToRinggit(k.getKadar().divide(new BigDecimal(100)).multiply(nilai));
     t.setAmaun(monetaryUtil.roundToRinggit(k.getKadar().divide(new BigDecimal(100)).multiply(nilai)));
     >>>>>>> .r10734
     } else{
     t.setAmaun(k.getKadar());
     }
     t.setKuantiti(1);
     listTransaksi.add(t);

     break;
     }
     case 2 :  kategori 2 = based on count of Hakmilik
     {
     List<KodKadarBayaran> kb = (List<KodKadarBayaran>)
     s.createQuery("from KodKadarBayaran kb where " +
     "kb.kodUrusan.id = :kodUrusan " +
     "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah) " +
     "order by kb.bilHakmilik")
     .setString("kodUrusan", kodUrusan.getKod())
     .setCharacter("milikDaerah", milikDaerah).list();
     if (debug) LOG.debug("kb.size()=" + kb.size());
     if (kb.size() < 1){
     LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     }
     int lastKadar = 0;
     BigDecimal c = BigDecimal.ZERO;
     for (int i = 1; i <= bilHakmilik; i++){
     for (; lastKadar < kb.size(); lastKadar++){
     if (debug) LOG.debug("lastKadar=" + lastKadar);
     KodKadarBayaran k = kb.get(lastKadar);
     if (k.getBilHakmilik() == i) break;
     }
     if (lastKadar >= kb.size()) lastKadar = kb.size() - 1;
     c = c.add(kb.get(lastKadar).getKadar());
     }
     t.setAmaun(c);
     t.setKuantiti(bilHakmilik);
     listTransaksi.add(t);

     break;
     }
     case 3:   kategori 3 = lump sum bil_hm
     {
     List<KodKadarBayaran> kb = (List<KodKadarBayaran>)
     s.createQuery("from KodKadarBayaran kb where " +
     "kb.kodUrusan.id = :kodUrusan " +
     "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah)")
     .setString("kodUrusan", kodUrusan.getKod())
     .setCharacter("milikDaerah", milikDaerah).list();
     if (kb.size() < 1){
     LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     }
     if (kb.size() < 1){
     t.setAmaun(BigDecimal.ZERO);
     } else{
     t.setAmaun(kb.get(0).getKadar());
     }
     listTransaksi.add(t);

     break;
     }
     case 4:  hisham: data for SPTB kategori 4 look suspicious, wonder if being used
     {
     List<KodKadarBayaran> kb = (List<KodKadarBayaran>)
     s.createQuery("from KodKadarBayaran kb where " +
     "kb.kodUrusan.id = :kodUrusan " +
     "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah)")
     .setString("kodUrusan", kodUrusan.getKod())
     .setCharacter("milikDaerah", milikDaerah).list();
     if (kb.size() < 1){
     LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     }
     if (kb.size() < 1){
     t.setAmaun(BigDecimal.ZERO);
     } else{
     <<<<<<< .mine
     caj = kb.get(0).getKadar().multiply(nilai);
     }
     t.setAmaun(kb.get(0).getKadar().multiply(nilai));
     }
     t.setKuantiti(1);
     listTransaksi.add(t);

     >>>>>>> .r10734
     break;
     }
     case 5:
     {
     hisham: couldnt find any sptb code for kategori 5, maybe in other states?
     break;
     }
     case 6:  based on kategori (eg. consent)
     {
     if (kategori == null){
     throw new RuntimeException("kategori nil diberikan untuk kategori bayaran 6!");
     }
     List<KodKadarBayaran> kb = (List<KodKadarBayaran>)
     s.createQuery("from KodKadarBayaran kb where " +
     "kb.kodUrusan.id = :kodUrusan " +
     "and (kb.milikDaerah = 'X' or kb.milikDaerah = :milikDaerah) " +
     "order by kb.bilHakmilik")
     .setString("kodUrusan", kodUrusan.getKod())
     .setCharacter("milikDaerah", milikDaerah).list();
     BigDecimal c = null;
     for (KodKadarBayaran b : kb){
     if (b.getKategori() != null && kategori.trim().equalsIgnoreCase(b.getKategori())){
     c = b.getKadar();
     c = c.multiply(new BigDecimal(bilHakmilik)); // for every lot
     }
     }
     t.setAmaun(c);
     t.setKuantiti(bilHakmilik);
     listTransaksi.add(t);

     break;
     }

     case 7 :  kategori 7 = kadar strata mengikut bil. petak
     {
     if (nilai == null) throw new RuntimeException("Bil. petak tidak dinyatakan untuk pengiraaan");

     StringBuilder q = new StringBuilder("from KodKadarBayaran kb where " +
     "kb.kodUrusan.id = :kodUrusan " +
     "and kb.nilaiMinimum <= :bilPetak and (kb.nilaiMaksimum >= :bilPetak " +
     "or kb.nilaiMaksimum is null)");

     Query query = s.createQuery(q.toString())
     //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
     .setString("kodUrusan", kodUrusan.getKod())
     .setBigDecimal("bilPetak", nilai);
     List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();
     if (kb.size() != 1){
     LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod() +
     " tidak tepat!");
     }
     KodKadarBayaran k = kb.get(0);
     //			caj = k.getKadar().multiply(nilai);
     break;
     }
     case 10:  HASIL ANSURAN CUKAI IN MELAKA, CREDIT INTO AK.AMANAH
     {
     t.setKuantiti(1);
     BigDecimal c = kodUrusan.getCaj();
     if (c != null) t.setAmaun(c);
     else t.setAmaun(BigDecimal.ZERO);
     listTransaksi.add(t);

     // bayaran deposit
     if (BigDecimal.ZERO.compareTo(nilai) < 0){
     TransaksiValue dep = new TransaksiValue();
     dep.setKodUrusan(kodUrusan.getKod());
     dep.setKodTransaksi(hasilCfg.getProperty("depositAnsuranCukaiTanah"));
     dep.setNamaUrusan("Deposit Ansuran Cukai Tanah");
     dep.setAmaun(nilai);
     listTransaksi.add(dep);
     }

     }
     }

     // for urusan pendaftaran, fine calculation
     if (kodUrusan.getJabatan().getKod().equals("16")) {
     // fine (denda), if tarikh penyaksian is not null (to enable calculation, set tarikh penyaksian)
     if (tarikh != null){
     BigDecimal fine = calculateFine(tarikh, t.getAmaun());
     if (fine.compareTo(BigDecimal.ZERO) > 0){
     // create a new transaction
     TransaksiValue t2 = new TransaksiValue();
     t2.setKodUrusan(kodUrusan.getKod());
     t2.setNamaUrusan("Denda Lewat");
     t2.setAmaun(fine);
     // TODO get proper kods for this!
     t2.setKodTransaksi(t.getKodTransaksi());
     listTransaksi.add(t2);
     }
     }
     }

     LOG.info("jumlah caj=" + t.getAmaun());

     return listTransaksi;
     }
     */
    public BigDecimal calculateFine(Date tarikhSaksi, BigDecimal fee) {
        Calendar today = Calendar.getInstance();
        if (debug) {
            LOG.debug(today.get(Calendar.DAY_OF_MONTH) + "/"
                    + today.get(Calendar.MONTH) + "/"
                    + today.get(Calendar.YEAR));
        }

        // check if within the first 3 months
        Calendar deadline = Calendar.getInstance(); // GET THE DEFAULT TIMEZONE
        deadline.setTime(tarikhSaksi);
        deadline.set(Calendar.HOUR_OF_DAY, 0);
        deadline.set(Calendar.MINUTE, 0);
        deadline.set(Calendar.SECOND, 0);
        deadline.add(Calendar.MONTH, 3);
        if (debug) {
            LOG.debug(deadline.get(Calendar.DAY_OF_MONTH) + "/"
                    + deadline.get(Calendar.MONTH) + "/"
                    + deadline.get(Calendar.YEAR));
        }

        if (today.before(deadline)) {
            if (debug) {
                LOG.debug("No fine since before 3 months");
            }
            return BigDecimal.ZERO;
        }

        // calculate for every 3 months
        BigDecimal fine = null;
        for (int i = 1; i < 5; i++) {
            deadline.add(Calendar.MONTH, 3);
            if (debug) {
                LOG.debug(deadline.get(Calendar.DAY_OF_MONTH) + "/"
                        + deadline.get(Calendar.MONTH) + "/"
                        + deadline.get(Calendar.YEAR));
            }
            if (today.before(deadline)) {
                return fine = fee.multiply(new BigDecimal(i));
            }
        }

        // maximum fine
        return fine = fee.multiply(new BigDecimal(5));
    }

    //added by murali for urusan PBS on 10/08/2012
    @SuppressWarnings("unchecked")
    public ArrayList<TransaksiValue> calculateFeePBS(KodUrusan kodUrusan,
            BigDecimal nilai, // input at SPOC
            BigDecimal nilai1, // input at SPOC
            Date tarikh, // input at SPOC
            String kategori, // input at SPOC
            ArrayList<Integer> senaraiBilKategori, // input at SPOC (either kategori or senaraiBilKategori will be passed)
            String teks1, // input at SPOC
            List<HakmilikPermohonan> senaraiHakmilikPermohonan,
            List<String> senaraiHakmilikBersiri) {

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            nilai = new BigDecimal(1);
        }

        LOG.debug("--kodUrusan--:" + kodUrusan);
        LOG.debug("--nilai--:" + nilai);
        LOG.debug("--nilai1--:" + nilai1);
        LOG.debug("--tarikh--:" + tarikh);
        LOG.debug("--kategori--:" + kategori);

        //BigDecimal caj = BigDecimal.ZERO;
        KodKategoriBayaran kg = kodUrusan.getKategoriBayaran();
        int bilHakmilik = 0;
        char milikDaerah = 'X';
        /*Hakmilik Daerah atau Pendaftar, X for not applicable*/
        for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
            HakmilikPermohonan h = senaraiHakmilikPermohonan.get(i);
            if (h.getHakmilik() != null && h.getHakmilik().getIdHakmilik() != null) {
                // get sampling for the first record only
                if (i == 0) {
                    Hakmilik hm = hakmilikDAO.findById(h.getHakmilik().getIdHakmilik());
                    if (hm != null) {
                        h.setHakmilik(hm);
                        milikDaerah = hm.getKodHakmilik().getMilikDaerah();
                    }
                }
                bilHakmilik++;
            }
        }
        if (senaraiHakmilikBersiri != null) {
            bilHakmilik += senaraiHakmilikBersiri.size();
        }
        if (debug) {
            LOG.debug("--bilHakmilik--:" + bilHakmilik);
        }

        // get sampling for the first record only
        if (milikDaerah == 'X' && bilHakmilik > 0 && senaraiHakmilikBersiri != null
                && senaraiHakmilikBersiri.size() > 0) {
            Hakmilik hm = hakmilikDAO.findById(senaraiHakmilikBersiri.get(0));
            if (hm != null) {
                milikDaerah = hm.getKodHakmilik().getMilikDaerah();
            } else {
                LOG.warn("Couldn't find idHakmilik " + senaraiHakmilikBersiri.get(0));
            }
        }
        if (debug) {
            LOG.debug("--milikDaerah--:" + milikDaerah);
        }

        // just set hakmilik to minimum 1 in case need to charge
        if (bilHakmilik == 0) {
            bilHakmilik = 1;
        }

        Session s = sessionProvider.get();

        ArrayList<TransaksiValue> listTransaksi = new ArrayList<TransaksiValue>();

        if (kg == null) {
            /* flat rate */
            BigDecimal c = kodUrusan.getCaj();
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setKuantiti(bilHakmilik);
            if (c != null) {
                t.setAmaun(c);
            } else {
                t.setAmaun(BigDecimal.ZERO);
            }

            listTransaksi.add(t);
            return listTransaksi;
        } else {

            if (nilai == null || nilai1 == null) {
                throw new RuntimeException("Bil. petak tidak dinyatakan untuk pengiraaan");
            }

            StringBuilder q = new StringBuilder("from KodKadarBayaran kb where "
                    + "kb.kodUrusan.id = :kodUrusan "
                    + "and kb.nilaiMinimum <= :bilPetak and (kb.nilaiMaksimum >= :bilPetak "
                    + "or kb.nilaiMaksimum is null)");

            Query query = s.createQuery(q.toString()) //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
                    .setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("bilPetak", nilai);
            List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();
            if (kb.size() != 1) {
                LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                        + " tidak tepat!");
                throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                        + " tidak tepat!");
            }

            LOG.debug("--amount calculating--:");

            LOG.debug("--kekal nilai1 calculating--:");
            LOG.debug("--nilai1--:" + nilai1);
            BigDecimal kekal = new BigDecimal(BigInteger.ZERO);
            KodKadarBayaran k = kb.get(0);
            if (nilai1.intValue() > 30) {
                kekal = nilai1.multiply(new BigDecimal(10));
            } else {
                kekal = new BigDecimal(300);
            }
            LOG.debug("--kekal nilai1 calculated--:" + kekal);

            LOG.debug("--sementara nilai calculating--:");
            LOG.debug("--nilai--:" + nilai);

            String kodNegeri = conf.getProperty("kodNegeri");
            BigDecimal sementara = new BigDecimal(BigInteger.ZERO);
            BigDecimal c = new BigDecimal(BigInteger.ZERO);
            if (kodNegeri.equals("04")) {
                /*
                 if (nilai1.intValue() > 0) {
                 sementara = nilai.multiply(k.getKadar());
                 LOG.debug("--nilai > 0--:" + sementara);
                 } */
                sementara = nilai1.add(nilai);
                if (sementara.intValue() < 30) {
                    c = k.getKadar();
                } else {
                    c = sementara.multiply(new BigDecimal(10));
                }

            } else {
                LOG.info("----SEREMBAN----::");
                if (nilai1.intValue() > 0) {
                    //ida update 17/10/13
                    // sementara = nilai.multiply(k.getKadar());
                    sementara = new BigDecimal(0);
                    //sementara = nilai.multiply(new BigDecimal(500));
                    LOG.debug("--nilai > 0--:" + sementara);
                    LOG.debug("--calculating total of sementara and kekal--:");
                    c = kekal.add(sementara);
                }
            }
            LOG.debug("--calculated total of sementara and kekal--:" + c);
            LOG.debug("--Quantity of sementara and kekal--:" + nilai.intValue() + nilai1.intValue());
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setAmaun(c);
            if (kodNegeri.equals("04")) {
                t.setKuantiti(nilai.intValue() + nilai1.intValue()); // jumlah petak
            } else {
                t.setKuantiti(nilai1.intValue()); // jumlah petak kekal
                t.setPosition(bilHakmilik);

            }
            listTransaksi.add(t);
        }

        return listTransaksi;
    }
    //added by murali for urusan PBS on 10/08/2012

    @SuppressWarnings("unchecked")
    public ArrayList<TransaksiValue> calculateFeePBTS(KodUrusan kodUrusan,
            BigDecimal nilai, // input at SPOC
            BigDecimal nilai1,// input at SPOC
            BigDecimal nilai2,// input at SPOC
            Date tarikh, // input at SPOC
            String kategori, // input at SPOC
            ArrayList<Integer> senaraiBilKategori, // input at SPOC (either kategori or senaraiBilKategori will be passed)
            String teks1, // input at SPOC
            List<HakmilikPermohonan> senaraiHakmilikPermohonan,
            List<String> senaraiHakmilikBersiri) {

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            nilai = new BigDecimal(1);
        }

        LOG.debug("--kodUrusan--:" + kodUrusan);
        LOG.debug("--nilai--:" + nilai);
        LOG.debug("--nilai1--:" + nilai1);
        LOG.debug("--nilai2--:" + nilai2);
        LOG.debug("--tarikh--:" + tarikh);
        LOG.debug("--kategori--:" + kategori);

        //BigDecimal caj = BigDecimal.ZERO;
        KodKategoriBayaran kg = kodUrusan.getKategoriBayaran();
        int bilHakmilik = 0;
        char milikDaerah = 'X';
        /*Hakmilik Daerah atau Pendaftar, X for not applicable*/
        for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
            HakmilikPermohonan h = senaraiHakmilikPermohonan.get(i);
            if (h.getHakmilik() != null && h.getHakmilik().getIdHakmilik() != null) {
                // get sampling for the first record only
                if (i == 0) {
                    Hakmilik hm = hakmilikDAO.findById(h.getHakmilik().getIdHakmilik());
                    if (hm != null) {
                        h.setHakmilik(hm);
                        milikDaerah = hm.getKodHakmilik().getMilikDaerah();
                    }
                }
                bilHakmilik++;
            }
        }
        if (senaraiHakmilikBersiri != null) {
            bilHakmilik += senaraiHakmilikBersiri.size();
        }
        if (debug) {
            LOG.debug("--bilHakmilik--:" + bilHakmilik);
        }

        // get sampling for the first record only
        if (milikDaerah == 'X' && bilHakmilik > 0 && senaraiHakmilikBersiri != null
                && senaraiHakmilikBersiri.size() > 0) {
            Hakmilik hm = hakmilikDAO.findById(senaraiHakmilikBersiri.get(0));
            if (hm != null) {
                milikDaerah = hm.getKodHakmilik().getMilikDaerah();
            } else {
                LOG.warn("Couldn't find idHakmilik " + senaraiHakmilikBersiri.get(0));
            }
        }
        if (debug) {
            LOG.debug("--milikDaerah--:" + milikDaerah);
        }

        // just set hakmilik to minimum 1 in case need to charge
        if (bilHakmilik == 0) {
            bilHakmilik = 1;
        }

        Session s = sessionProvider.get();

        ArrayList<TransaksiValue> listTransaksi = new ArrayList<TransaksiValue>();

        if (kg == null) {
            /* flat rate */
            BigDecimal c = kodUrusan.getCaj();
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setKuantiti(bilHakmilik);
            if (c != null) {
                t.setAmaun(c);
            } else {
                t.setAmaun(BigDecimal.ZERO);
            }

            listTransaksi.add(t);
            return listTransaksi;
        } else {

            if (nilai == null || nilai1 == null || nilai2 == null) {
                throw new RuntimeException("Bil. petak tidak dinyatakan untuk pengiraaan");
            }

            StringBuilder q = new StringBuilder("from KodKadarBayaran kb where "
                    + "kb.kodUrusan.id = :kodUrusan "
                    + "and kb.nilaiMinimum <= :bilPetak and (kb.nilaiMaksimum >= :bilPetak "
                    + "or kb.nilaiMaksimum is null)");

            Query query = s.createQuery(q.toString()) //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
                    .setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("bilPetak", nilai2);
            List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();
            if (kb.size() != 1) {
                LOG.error("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                        + " tidak tepat!");
                throw new RuntimeException("Kadar Bayaran untuk Kod Urusan " + kodUrusan.getKod()
                        + " tidak tepat!");
            }

            LOG.debug("--amount calculating--:");

            LOG.debug("--kekal nilai1 calculating--:");
            LOG.debug("--nilai1--:" + nilai1);
            BigDecimal kekal = new BigDecimal(BigInteger.ZERO);
            KodKadarBayaran k = kb.get(0);
            if (nilai1.intValue() > 30) {
                kekal = nilai1.multiply(new BigDecimal(10));
            } else {
                kekal = new BigDecimal(300);
            }
            LOG.debug("--kekal nilai1 calculated--:" + kekal);

            LOG.debug("--sementara nilai calculating--:");
            LOG.debug("--nilai--:" + nilai);

            String kodNegeri = conf.getProperty("kodNegeri");
            LOG.info("----kodNegeri--------::" + kodNegeri);
            BigDecimal sementara = new BigDecimal(BigInteger.ZERO);
            if (kodNegeri.equals("04")) {
                LOG.info("----MELAKA--------::");
                if (nilai.intValue() > 0 && nilai.intValue() < 21) {
                    //sementara = k.getKadar();
                    sementara = new BigDecimal(10000);
                } else if (nilai.intValue() >= 21 && nilai.intValue() < 41) {
                    //sementara = k.getKadar();
                    sementara = new BigDecimal(20000);
                    LOG.debug("--nilai > 41--:" + sementara);
                } else if (nilai.intValue() >= 41) {
                    //sementara = nilai.multiply(k.getKadar());
                    sementara = nilai.multiply(new BigDecimal(500));
                    LOG.debug("--nilai > 41 X 500--:" + sementara);
                }
            } else {
                LOG.info("----SEREMBAN----::");
                if (nilai.intValue() > 0) {
                    //sementara = nilai.multiply(k.getKadar());
                    //ida update 17/10/13
                    // sementara = nilai.multiply(new BigDecimal(10));
                    sementara = new BigDecimal(0);
                    LOG.debug("--nilai > 0--:" + sementara);
                }
            }
            LOG.debug("--sementara nilai calculated--:" + sementara);

            LOG.debug("--petak tanah nilai2 calculating--:");
            LOG.debug("--nilai2--:" + nilai2);
            BigDecimal tanah = new BigDecimal(BigInteger.ZERO);
            if (kodNegeri.equals("04")) {
                LOG.info("----MELAKA--------::");

            } else {
//                LOG.info("----SEREMBAN----::");
//                if (nilai2.intValue() > 30) {
//                    LOG.debug("--nilai2 >30 / kadar value--:" + k.getKadar());
//                    tanah = nilai2.multiply(k.getKadar());
//                    LOG.debug("--nilai2 > 30--:" + tanah);
//                } else {
//                    LOG.debug("--nilai2 < 30 / kadar value--:" + k.getKadar());
//                    tanah = k.getKadar();
//                    LOG.debug("--nilai2 < 30--:" + tanah);
//                }
            }

            BigDecimal c = new BigDecimal(BigInteger.ZERO);
            LOG.debug("--calculating total of sementara / kekal / Tanah--:");
            c = kekal.add(sementara).add(tanah);
            LOG.debug("--calculated total of sementara and kekal and Tanah--:" + c);
            LOG.debug("--Quantity of sementara and kekal--:" + (nilai.intValue() + nilai1.intValue() + nilai2.intValue()));

            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setAmaun(c);
            if (kodNegeri.equals("04")) {
                t.setKuantiti(nilai.intValue() + nilai1.intValue() + nilai2.intValue()); // jumlah petak
            } else {
                // t.setKuantiti(nilai1.intValue() + nilai2.intValue()); // jumlah petak
                t.setKuantiti(nilai1.intValue());
            }
            listTransaksi.add(t);
        }

        return listTransaksi;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<TransaksiValue> calculateFeeMlkKos(KodUrusan kodUrusan,
            BigDecimal nilai, // input at SPOC              
            Date tarikh, // input at SPOC
            String kategori, // input at SPOC
            ArrayList<Integer> senaraiBilKategori, // input at SPOC (either kategori or senaraiBilKategori will be passed)
            String teks1, // input at SPOC
            List<HakmilikPermohonan> senaraiHakmilikPermohonan,
            List<String> senaraiHakmilikBersiri) {
        //BigDecimal caj = BigDecimal.ZERO;
        KodKategoriBayaran kg = kodUrusan.getKategoriBayaran();
        int bilHakmilik = 0;
        char milikDaerah = 'X';
        /*Hakmilik Daerah atau Pendaftar, X for not applicable*/
        for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
            HakmilikPermohonan h = senaraiHakmilikPermohonan.get(i);
            if (h.getHakmilik() != null && h.getHakmilik().getIdHakmilik() != null) {
                // get sampling for the first record only
                if (i == 0) {
                    Hakmilik hm = hakmilikDAO.findById(h.getHakmilik().getIdHakmilik());
                    if (hm != null) {
                        h.setHakmilik(hm);
                        milikDaerah = hm.getKodHakmilik().getMilikDaerah();
                    }
                }
                bilHakmilik++;
            }
        }
        if (senaraiHakmilikBersiri != null) {
            bilHakmilik += senaraiHakmilikBersiri.size();
        }
        if (debug) {
            LOG.debug("--bilHakmilik--:" + bilHakmilik);
        }

        // get sampling for the first record only
        if (milikDaerah == 'X' && bilHakmilik > 0 && senaraiHakmilikBersiri != null
                && senaraiHakmilikBersiri.size() > 0) {
            Hakmilik hm = hakmilikDAO.findById(senaraiHakmilikBersiri.get(0));
            if (hm != null) {
                milikDaerah = hm.getKodHakmilik().getMilikDaerah();
            } else {
                LOG.warn("Couldn't find idHakmilik " + senaraiHakmilikBersiri.get(0));
            }
        }
        if (debug) {
            LOG.debug("--milikDaerah--:" + milikDaerah);
        }

        // just set hakmilik to minimum 1 in case need to charge
        if (bilHakmilik == 0) {
            bilHakmilik = 1;
        }

        Session s = sessionProvider.get();

        ArrayList<TransaksiValue> listTransaksi = new ArrayList<TransaksiValue>();

        if (kg == null) {
            /* flat rate */
            BigDecimal c = kodUrusan.getCaj();
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setKuantiti(bilHakmilik);
            if (c != null) {
                t.setAmaun(c);
            } else {
                t.setAmaun(BigDecimal.ZERO);
            }

            listTransaksi.add(t);
            return listTransaksi;
        } else {

            if (nilai == null) {
                throw new RuntimeException("Bil. petak tidak dinyatakan untuk pengiraaan");
            }

            StringBuilder q = new StringBuilder("from KodKadarBayaran kb where "
                    + "kb.kodUrusan.id = :kodUrusan "
                    + "and kb.nilaiMinimum <= :bilPetak and (kb.nilaiMaksimum >= :bilPetak "
                    + "or kb.nilaiMaksimum is null)");

            Query query = s.createQuery(q.toString()) //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
                    .setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("bilPetak", nilai);
            List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();

            BigDecimal c = new BigDecimal(BigInteger.ZERO);
            c = nilai.multiply(getKodByKat(kodUrusan.getKod(), "3").getKadar());
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setAmaun(c);
            t.setKuantiti(nilai.intValue()); // jumlah petak
            listTransaksi.add(t);
        }

        return listTransaksi;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<TransaksiValue> calculateFeeMlk(KodUrusan kodUrusan,
            BigDecimal nilai, // input at SPOC              
            Date tarikh, // input at SPOC
            String kategori, // input at SPOC
            ArrayList<Integer> senaraiBilKategori, // input at SPOC (either kategori or senaraiBilKategori will be passed)
            String teks1, // input at SPOC
            List<HakmilikPermohonan> senaraiHakmilikPermohonan,
            List<String> senaraiHakmilikBersiri,
            int katgTanah) {

        //BigDecimal caj = BigDecimal.ZERO;
        KodKategoriBayaran kg = kodUrusan.getKategoriBayaran();
        int bilHakmilik = 0;
        char milikDaerah = 'X';
        /*Hakmilik Daerah atau Pendaftar, X for not applicable*/
        for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
            HakmilikPermohonan h = senaraiHakmilikPermohonan.get(i);
            if (h.getHakmilik() != null && h.getHakmilik().getIdHakmilik() != null) {
                // get sampling for the first record only
                if (i == 0) {
                    Hakmilik hm = hakmilikDAO.findById(h.getHakmilik().getIdHakmilik());
                    if (hm != null) {
                        h.setHakmilik(hm);
                        milikDaerah = hm.getKodHakmilik().getMilikDaerah();
                    }
                }
                bilHakmilik++;
            }
        }
        if (senaraiHakmilikBersiri != null) {
            bilHakmilik += senaraiHakmilikBersiri.size();
        }
        if (debug) {
            LOG.debug("--bilHakmilik--:" + bilHakmilik);
        }

        // get sampling for the first record only
        if (milikDaerah == 'X' && bilHakmilik > 0 && senaraiHakmilikBersiri != null
                && senaraiHakmilikBersiri.size() > 0) {
            Hakmilik hm = hakmilikDAO.findById(senaraiHakmilikBersiri.get(0));
            if (hm != null) {
                milikDaerah = hm.getKodHakmilik().getMilikDaerah();
            } else {
                LOG.warn("Couldn't find idHakmilik " + senaraiHakmilikBersiri.get(0));
            }
        }
        if (debug) {
            LOG.debug("--milikDaerah--:" + milikDaerah);
        }

        // just set hakmilik to minimum 1 in case need to charge
        if (bilHakmilik == 0) {
            bilHakmilik = 1;
        }

        Session s = sessionProvider.get();

        ArrayList<TransaksiValue> listTransaksi = new ArrayList<TransaksiValue>();

        if (kg == null) {
            /* flat rate */
            BigDecimal c = kodUrusan.getCaj();
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setKuantiti(bilHakmilik);
            if (c != null) {
                t.setAmaun(c);
            } else {
                t.setAmaun(BigDecimal.ZERO);
            }

            listTransaksi.add(t);
            return listTransaksi;
        } else {

            if (nilai == null) {
                throw new RuntimeException("Bil. petak tidak dinyatakan untuk pengiraaan");
            }

            StringBuilder q = new StringBuilder("from KodKadarBayaran kb where "
                    + "kb.kodUrusan.id = :kodUrusan "
                    + "and kb.nilaiMinimum <= :bilPetak and (kb.nilaiMaksimum >= :bilPetak "
                    + "or kb.nilaiMaksimum is null)");

            Query query = s.createQuery(q.toString()) //"and :nilaiHarta between kb.nilaiMaksimum and kb.nilaiMinimum")
                    .setString("kodUrusan", kodUrusan.getKod()).setBigDecimal("bilPetak", nilai);
            List<KodKadarBayaran> kb = (List<KodKadarBayaran>) query.list();
            BigDecimal c = new BigDecimal(BigInteger.ZERO);
            String katg = katgTanah + "";
//            for (int i = 0; i < senaraiHakmilikPermohonan.size(); i++) {
//                HakmilikPermohonan h = senaraiHakmilikPermohonan.get(i);
//                if (h.getHakmilik() != null && h.getHakmilik().getIdHakmilik() != null) {
//                    // get sampling for the first record only
//                    if (i == 0) {
//                        Hakmilik hm = hakmilikDAO.findById(h.getHakmilik().getIdHakmilik());
//                        if (hm != null) {
//                            katg = hm.getKategoriTanah().getKod();
//                        }
//                    }
//                }
//            }
            BigDecimal petaks = new BigDecimal(BigInteger.ZERO);
            if (katg.equals("2")) { //kediaman                
                petaks = getKodByKat(kodUrusan.getKod(), "8").getNilaiMaksimum();
                if (nilai.intValue() <= petaks.intValue()) {
                    c = getKodByKat(kodUrusan.getKod(), "8").getKadar();
                }
                if (nilai.intValue() > petaks.intValue()) {
                    c = nilai.multiply(getKodByKat(kodUrusan.getKod(), "9").getKadar());
                }
            }
            if (katg.equals("3")) { //perindustrian               
                petaks = getKodByKat(kodUrusan.getKod(), "4").getNilaiMaksimum();
                if (nilai.intValue() <= petaks.intValue()) {
                    c = getKodByKat(kodUrusan.getKod(), "4").getKadar();
                }
                if (nilai.intValue() > petaks.intValue()) {
                    c = nilai.multiply(getKodByKat(kodUrusan.getKod(), "5").getKadar());
                }
            }
            if (katg.equals("1")) { //perniagaan               
                petaks = getKodByKat(kodUrusan.getKod(), "6").getNilaiMaksimum();
                if (nilai.intValue() <= petaks.intValue()) {
                    c = getKodByKat(kodUrusan.getKod(), "6").getKadar();
                }
                if (nilai.intValue() > petaks.intValue()) {
                    c = nilai.multiply(getKodByKat(kodUrusan.getKod(), "7").getKadar());
                }
            }
            if (katg.equals("4")) { //pembangunan bercampur               
                petaks = getKodByKat(kodUrusan.getKod(), "10").getNilaiMaksimum();
                if (nilai.intValue() <= petaks.intValue()) {
                    c = getKodByKat(kodUrusan.getKod(), "10").getKadar();
                }
                if (nilai.intValue() > petaks.intValue()) {
                    c = nilai.multiply(getKodByKat(kodUrusan.getKod(), "11").getKadar());
                }
            }
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(kodUrusan.getKod());
            t.setNamaUrusan(kodUrusan.getNama());
            t.setKodTransaksi(kodUrusan.getKodTransaksi().getKod());
            t.setAmaun(c);
            t.setKuantiti(nilai.intValue()); // jumlah petak
            listTransaksi.add(t);
        }
        return listTransaksi;
    }

    public KodKadarBayaran getKodByKat(String kod, String kategori) {
        String query = "SELECT b FROM etanah.model.KodKadarBayaran b where b.kodUrusan.kod = :kod and b.kategori = :kategori";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod);
        q.setString("kategori", kategori);
        return (KodKadarBayaran) q.uniqueResult();
    }

    public List<TransaksiValue> calculateFeeACQ(KodUrusan ku, BigDecimal nilaiJPPH, String kategori, Long jumlahTDK, ArrayList<HakmilikPermohonan> listHp, ArrayList<String> listBersiri) {
        ArrayList<TransaksiValue> listTransaksi = new ArrayList<TransaksiValue>();
        BigDecimal deposit = new BigDecimal(BigInteger.ZERO);
        List<Hakmilik> listHakmilik = new ArrayList<Hakmilik>();
        for (HakmilikPermohonan mh : listHp) {
            listHakmilik.add(mh.getHakmilik());
        }
        if (ku.getKod().equals("SEK4")) {
            FeeSek4Form form = calculateFeeSek4(listHakmilik, jumlahTDK, kategori);
            TransaksiValue t = new TransaksiValue();
            t.setKodUrusan(ku.getKod());
            t.setNamaUrusan("Bayaran Fee Permohonan ");
            KodTransaksi fee = kodTransaksiDAO.findById("72454");
            t.setKodTransaksi(fee.getKod());
            t.setAmaun(form.getFeePermohonan());
            t.setKuantiti(form.getPb().intValue()); // jumlah petak
            t.setPosition(2);
            listTransaksi.add(t);
            if(kategori.equals("1")){
            deposit = new BigDecimal(BigInteger.ZERO);
            }else{
            deposit = calculateDeposit50((nilaiJPPH!=null?nilaiJPPH:new BigDecimal(BigInteger.ZERO)));
            
            }
            TransaksiValue t2 = new TransaksiValue();
            t2.setKodUrusan(ku.getKod());
            t2.setNamaUrusan("Deposit 50% Urusan Pengambilan");
            //40041
            KodTransaksi depo = kodTransaksiDAO.findById("40041");
            t2.setKodTransaksi(depo.getKod());
            t2.setAmaun(deposit);
            t2.setKuantiti(1); // jumlah petak
            t2.setPosition(1);
            listTransaksi.add(t2);
        }

//         TransaksiValue t = new TransaksiValue();
//            t.setKodUrusan(ku.getKod());
//            t.setNamaUrusan(ku.getNama());
//            t.setKodTransaksi(ku.getKodTransaksi().getKod());
//            t.setAmaun(feePermohonan.add(deposit));
//            t.setKuantiti(1); // jumlah petak
//            listTransaksi.add(t);
        return listTransaksi;
    }

    private Long getTotalPBListHakmilik(List<Hakmilik> listHp) {
        String query = "SELECT count(b) FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik in (:list) and b.jenis.kod in ('PM')"
                + " and b.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("list", listHp);
        return (Long) q.uniqueResult();
    }

    private FeeSek4Form calculateFeeSek4(List<Hakmilik> listHakmilik, Long jumlahTDK, String kategori) {
        FeeSek4Form form = new FeeSek4Form();
        BigDecimal feePermohonan = new BigDecimal(BigInteger.ZERO);;
        Long pb = getTotalPBListHakmilik(listHakmilik);
        Long totalPB = pb + (jumlahTDK!=null?jumlahTDK:0);
        if (kategori.equals("3")) {
            if (totalPB <= 10) {
                feePermohonan = new BigDecimal(10000);
            } else if (11 <= totalPB && totalPB <= 15) {
                feePermohonan = new BigDecimal(10000).add(new BigDecimal((totalPB - 10) * 1000));
            } else if (16 <= totalPB && totalPB <= 20) {
                feePermohonan = new BigDecimal(10000 + 5000).add(new BigDecimal((totalPB - 15) * 2000));
            } else if (totalPB >= 21) {
                feePermohonan = new BigDecimal(10000 + 5000 + 10000).add(new BigDecimal((totalPB - 20) * 3000));
            }
        } else if(kategori.equals("2")) {
            if (totalPB <= 10) {
                feePermohonan = new BigDecimal(1000);
            } else if (totalPB >= 11) {
                feePermohonan = new BigDecimal(1000).add(new BigDecimal((totalPB - 10) * 100));
            }
        }else{
        feePermohonan = new BigDecimal(BigInteger.ZERO);
        }
        Map<BigDecimal, Long> value = new HashMap<BigDecimal, Long>();
        value.put(feePermohonan, totalPB);
        form.setFeePermohonan(feePermohonan);
        form.setPb(totalPB);
        return form;
    }

    private BigDecimal calculateDeposit50(BigDecimal nilaiJPPH) {
        BigDecimal deposit = new BigDecimal(BigInteger.ZERO);
        deposit = nilaiJPPH.divide(new BigDecimal(2));
        return deposit;
    }
}
