package etanah.view.kaunter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.google.inject.Inject;
import com.google.inject.Provider;

import etanah.Configuration;
import etanah.kodHasilConfig;
import etanah.dao.AkaunDAO;
import etanah.dao.EtappPerintahDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodAkaunDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPerintahDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodWarganegaraDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.model.Alamat;
import etanah.model.DokumenKewangan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.Pemohon;
import etanah.model.Permit;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanKelompok;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.StatusTanahLepasPengambilan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.sequence.GeneratorIdKelompok;
import etanah.sequence.GeneratorNoAkaun;
import etanah.service.PelupusanService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.etapp.PesakaService;
import java.math.BigInteger;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * STATELESS class.
 *
 * @author hishammk
 *
 */
public class UrusanPostProcess {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PermohonanTuntutanBayarDAO mohonTuntutBayarDAO;
    @Inject
    KodAkaunDAO kodAkaunDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    kodHasilConfig hasilCfg;
    @Inject
    GeneratorNoAkaun generatorNoAkaun;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    GeneratorIdKelompok idKelompokGenerator;
    @Inject
    Configuration configuration;
    @Inject
    KodJenisPihakBerkepentinganDAO kodPihakDAO;
    @Inject
    EtappPerintahDAO etappPerintahDAO;
    @Inject
    PesakaService pesakaService;
    @Inject
    KodPerintahDAO kodPerintahDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisPengenalanDAO kodPengenalanDAO;
    @Inject
    KodWarganegaraDAO kodWarganegaraDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(UrusanPostProcess.class);
    private static final boolean DEBUG = LOG.isDebugEnabled();

    public void performPostProcess(UrusanValue uv, Permohonan p, List<Hakmilik> listHakmilik,
            List<TransaksiValue> atv, InfoAudit ia) {
    }

    public void performPostProcess(UrusanPermohonan uv, List<Hakmilik> listHakmilik,
            DokumenKewangan dk, List<TransaksiValue> atv, InfoAudit ia) {

        Permohonan p = uv.getPermohonan();

        // 21/06/2013 Ansuran xperlu akaun amanah & akaun deposit
        /*if ("BACT".equals(uv.getKodUrusan())) { // Hasil permohonan bayaran ansuran
         String kodAnsuran = hasilCfg.getProperty("depositAnsuranCukaiTanah");
         String kodAkaun = hasilCfg.getProperty("akaunAnsuranDeposit");

         // find which the tx for deposit
         TransaksiValue tv = null;
         for (TransaksiValue t : atv) {
         if (t.utkUrusan != uv) {
         continue;
         }

         if (kodAnsuran.equals(t.kodTransaksi)) {
         tv = t;
         break;
         }
         }

         // create Ak.Amanah and set credit the tx
         Akaun akAmanah = new Akaun();
         KodCawangan caw = p.getCawangan();
         akAmanah.setCawangan(caw);
         akAmanah.setNoAkaun(generatorNoAkaun.generate(configuration.getProperty("kodNegeri"), caw, akAmanah));
         if (listHakmilik.size() == 0) {
         throw new RuntimeException("Tiada Hakmilik untuk permohonan ansuran");
         }
         Hakmilik hm = listHakmilik.get(0);
         akAmanah.setHakmilik(hm);
         akAmanah.setKodAkaun(kodAkaunDAO.findById(kodAkaun));
         akAmanah.setPermohonan(p);
         akAmanah.setStatus(kodStatusAkaunDAO.findById("A"));
         akAmanah.setPemegang(hm.getAkaunCukai().getPemegang());
         akAmanah.setAmaunMatang(hm.getAkaunCukai().getBaki());
         akAmanah.setBilCetakPenyata(0);
         akAmanah.setBaki(tv.amaun.multiply(new BigDecimal(-1))); // kredit
         akAmanah.setInfoAudit(ia);
         akaunDAO.save(akAmanah);

         //tv.akaunKredit = akAmanah;
         // credit the amount from the txn
         for (Transaksi txn : dk.getSenaraiTransaksi()) {
         if (kodAnsuran.equals(txn.getKodTransaksi().getKod())) {
         txn.setAkaunKredit(akAmanah);
         }
         }

         } else */
        if ("JPGD".equals(uv.getKodUrusan()) || "JPGPJ".equals(uv.getKodUrusan())) { // Perintah Jual Pentadbir Sebab Gadaian, for LELONG

            // only if permohonanSebelum being set
            Permohonan pSblm = p.getPermohonanSebelum();
            if (pSblm != null && ("PPTL".equals(pSblm.getKodUrusan().getKod())
                    || "PPJP".equals(pSblm.getKodUrusan().getKod()) || "PJTA".equals(pSblm.getKodUrusan().getKod()))) {

                // copy from PermohonanPihak Penggadai to Pemohon, compare with ID Hakmilik involved
                List<PermohonanPihak> listPihak = pSblm.getSenaraiPihak();
                if (listPihak != null && listPihak.size() > 0) {
                    List<Pemohon> newList = new ArrayList<Pemohon>();
                    for (PermohonanPihak pp : listPihak) {
                        if ("PG".equals(pp.getJenis().getKod())) {
                            continue; // only for PEMILIK yg menggadai
                        }
                        String idHakmilik = pp.getHakmilik().getIdHakmilik();
                        for (Hakmilik h : listHakmilik) {
                            if (h.getIdHakmilik().equals(idHakmilik)) {
                                Pemohon p2 = new Pemohon();
                                p2.setCawangan(ia.getDimasukOleh().getKodCawangan());
                                p2.setPermohonan(p);
                                p2.setPihak(pp.getPihak());
                                p2.setNama(pp.getNama());
                                p2.setAlamat(pp.getAlamat());
                                p2.setAlamatSurat(pp.getAlamatSurat());
                                p2.setJenisPengenalan(pp.getJenisPengenalan());
                                p2.setNoPengenalan(pp.getNoPengenalan());
                                p2.setPihakCawangan(pp.getPihakCawangan());
                                p2.setSyerPembilang(pp.getSyerPembilang());
                                p2.setSyerPenyebut(pp.getSyerPenyebut());
                                p2.setJenis(pp.getJenis());
                                //p2.setKaitan(pp.getKaitan());
                                //p2.setPekerjaan(pp.getPekerjaan());
                                //p2.setPendapatan(pp.getPendapatan());
                                //p2.setPendapatanLain(pp.getPendapatanLain());
                                //p2.setStatusKahwin(pp.getStatusKahwin());
                                //p2.setUmur(pp.getUmur());
                                p2.setInfoAudit(ia);
                                p2.setHakmilik(h);
                                newList.add(p2);
                            }
                        }
                    }
                    if (newList.size() > 0) {
                        p.setSenaraiPemohon(newList);
                    }
                }

                // copy from Lelong Pihak to PermohonanPihak Pemilik
                List<Lelongan> listLelong = sessionProvider.get().createQuery("select l from Lelongan l where "
                        + "l.permohonan.id = :idSblm and l.kodStatusLelongan.id = 'SL' and l.hakmilikPermohonan.hakmilik in (:list)").setParameterList("list", listHakmilik).setString("idSblm", pSblm.getIdPermohonan()).list();

                if (listLelong.size() > 0) {
                    String kod = null;
                    List<PermohonanPihak> listPP = sessionProvider.get().createQuery("select p from PermohonanPihak p where "
                            + "p.permohonan.id = :idSblm and p.jenis.kod not in ('PG')").setString("idSblm", pSblm.getIdPermohonan()).list();
                    String idPermohonan = "";
                    if (pSblm.getPermohonanSebelum() != null) {
                        idPermohonan = pSblm.getPermohonanSebelum().getIdPermohonan();
                        List<PermohonanAsal> listPap = sessionProvider.get().createQuery("select pa from PermohonanAsal pa "
                                + "where pa.idPermohonan.idPermohonan = :idPermohonan").setString("idPermohonan", idPermohonan).list();
                        if (listPap.isEmpty()) {
                            idPermohonan = pSblm.getPermohonanSebelum().getIdPermohonan();
                        } else {
                            idPermohonan = listPap.get(0).getIdPermohonanAsal().getIdPermohonan();
                        }
                    }
                    List<PermohonanAtasPerserahan> listPap = sessionProvider.get().createQuery("select pap from PermohonanAtasPerserahan pap "
                            + "where pap.permohonan.idPermohonan = :idPermohonan").setString("idPermohonan", idPermohonan).list();
                    if (listPap.size() > 0) {
                        PermohonanAtasPerserahan pap = listPap.get(0);
                        if (pap.getUrusan().getKodUrusan().getKod().equals("GD") || pap.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                            kod = "PM";
                        }
                        if (pap.getUrusan().getKodUrusan().getKod().equals("GDPJ")) {
                            kod = "PJ";
                        }
                        if (pap.getUrusan().getKodUrusan().getKod().equals("GDPJK")) {
                            kod = "PJK";
                        }
                        if (pap.getUrusan().getKodUrusan().getKod().equals("PMG")) {
                            String idSasar = pap.getUrusan().getIdPerserahan();
                            List<PermohonanHubungan> listHu = sessionProvider.get().createQuery("select hu from PermohonanHubungan hu "
                                    + "where hu.permohonanSasaran.idPermohonan = :idSasar").setString("idSasar", idSasar).list();
                            if (listHu.size() > 0) {
                                PermohonanHubungan ph = listHu.get(0);
                                if (ph.getPermohonanSumber().getKodUrusan().getKod().equals("GD") || pap.getUrusan().getKodUrusan().getKod().equals("GDWM")) {
                                    kod = "PM";
                                }
                                if (ph.getPermohonanSumber().getKodUrusan().getKod().equals("GDPJ")) {
                                    kod = "PJ";
                                }
                                if (ph.getPermohonanSumber().getKodUrusan().getKod().equals("GDPJK")) {
                                    kod = "PJK";
                                }
                            }
                        }
                    }

                    if (listPP.size() > 0) {
                        kod = listPP.get(0).getJenis().getKod();
                    } else {
                        kod = "PM";
                    }
                    System.out.println("listLelong : " + listLelong.size());
                    List<PermohonanPihak> newList = new ArrayList<PermohonanPihak>();
                    // n9
                    if ("05".equals(configuration.getProperty("kodNegeri"))) {
                        LOG.info("----N9----");
                        for (Lelongan l : listLelong) {
                            String idHakmilik = l.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                            for (Hakmilik h : listHakmilik) {
                                if (h.getIdHakmilik().equals(idHakmilik)) {
                                    PermohonanPihak p2 = new PermohonanPihak();
                                    p2.setCawangan(ia.getDimasukOleh().getKodCawangan());
                                    p2.setPermohonan(p);
                                    p2.setPihak(l.getPembida());
                                    p2.setNama(l.getPembida().getNama());
                                    p2.setNoPengenalan(l.getPembida().getNoPengenalan());
                                    p2.setJenisPengenalan(l.getPembida().getJenisPengenalan());
                                    Alamat alamat = new Alamat();
                                    try {
                                        alamat.setAlamat1(l.getPembida().getAlamat1() != null ? l.getPembida().getAlamat1() : "");
                                        alamat.setAlamat2(l.getPembida().getAlamat2() != null ? l.getPembida().getAlamat2() : "");
                                        alamat.setAlamat3(l.getPembida().getAlamat3() != null ? l.getPembida().getAlamat3() : "");
                                        alamat.setAlamat4(l.getPembida().getAlamat4() != null ? l.getPembida().getAlamat4() : "");
                                        alamat.setNegeri(l.getPembida().getNegeri());
                                        alamat.setPoskod(l.getPembida().getPoskod());
                                    } catch (Exception ex) {
                                        LOG.error(ex);
                                    }
                                    p2.setAlamat(alamat);
                                    //p2.setPihakCawangan(pp.getPihakCawangan());
                                    p2.setHakmilik(h);
                                    p2.setJenis(kodPihakDAO.findById(kod));
                                    //p2.setKaitan(pp.getKaitan());

                                    //added by nur.aqilah
                                    //syerPenyebut & syerPembilang
                                    int syerPembilang = 0;
                                    int syerPenyebut = 0;

                                    List<Pemohon> findPemohon = sessionProvider.get().createQuery("select p from Pemohon p where "
                                            + "p.permohonan.idPermohonan = :idPermohonan").setString("idPermohonan", p.getIdPermohonan()).list();

                                    for (Pemohon pp : findPemohon) {
                                        syerPembilang = syerPembilang + pp.getSyerPembilang();
                                        syerPenyebut = pp.getSyerPenyebut();
                                    }
                                    if (syerPembilang % syerPenyebut == 0) {
                                        int jumlahSyer = 0;
                                        jumlahSyer = syerPembilang / syerPenyebut;
                                        p2.setSyerPembilang(jumlahSyer);
                                        p2.setSyerPenyebut(jumlahSyer);
                                    } else {
                                        p2.setSyerPembilang(syerPembilang);
                                        p2.setSyerPenyebut(syerPenyebut);
                                    }
                                    //TODO p2.setSyerPenyebut(pp.getSyerPenyebut());
                                    p2.setInfoAudit(ia);
                                    newList.add(p2);
                                    break;
                                }
                            }
                        }
                    }

                    //MLK
                    if ("04".equals(configuration.getProperty("kodNegeri"))) {
                        LOG.info("----MLK----");
                        for (Lelongan l : listLelong) {
                            Long idLelong = l.getIdLelong();
                            List<Pembida> senaraiPembida = sessionProvider.get().createQuery("select pembida from Pembida pembida "
                                    + "where pembida.lelong.idLelong = :idLelong and pembida.kodStsPembida.kod in ('BJ')").setLong("idLelong", idLelong).list();
                            String idHakmilik = l.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                            for (Pembida pembida : senaraiPembida) {
                                for (Hakmilik h : listHakmilik) {
                                    if (h.getIdHakmilik().equals(idHakmilik)) {
                                        PermohonanPihak p2 = new PermohonanPihak();
                                        p2.setCawangan(ia.getDimasukOleh().getKodCawangan());
                                        p2.setPermohonan(p);
                                        p2.setPihak(pembida.getPihak());
                                        p2.setNama(pembida.getPihak().getNama());
                                        p2.setNoPengenalan(pembida.getPihak().getNoPengenalan());
                                        p2.setJenisPengenalan(pembida.getPihak().getJenisPengenalan());

                                        Alamat alamat = new Alamat();
                                        try {
                                            alamat.setAlamat1(pembida.getPihak().getAlamat1() != null ? pembida.getPihak().getAlamat1() : "");
                                            alamat.setAlamat2(pembida.getPihak().getAlamat2() != null ? pembida.getPihak().getAlamat2() : "");
                                            alamat.setAlamat3(pembida.getPihak().getAlamat3() != null ? pembida.getPihak().getAlamat3() : "");
                                            alamat.setAlamat4(pembida.getPihak().getAlamat4() != null ? pembida.getPihak().getAlamat4() : "");
                                            alamat.setNegeri(pembida.getPihak().getNegeri());
                                            alamat.setPoskod(pembida.getPihak().getPoskod());
                                        } catch (Exception ex) {
                                            LOG.error(ex);
                                        }
                                        p2.setAlamat(alamat);
                                        //p2.setPihakCawangan(pp.getPihakCawangan());
                                        p2.setHakmilik(h);
                                        p2.setJenis(kodPihakDAO.findById(kod));

                                        //added by nur.aqilah
                                        //syerPenyebut & syerPembilang
                                        int syerPembilang = 0;
                                        int syerPenyebut = 0;

                                        List<Pemohon> findPemohon = sessionProvider.get().createQuery("select p from Pemohon p where "
                                                + "p.permohonan.idPermohonan = :idPermohonan").setString("idPermohonan", p.getIdPermohonan()).list();

                                        for (Pemohon pp : findPemohon) {
                                            syerPembilang = syerPembilang + pp.getSyerPembilang();
                                            syerPenyebut = pp.getSyerPenyebut();
                                        }
                                        if (syerPembilang % syerPenyebut == 0) {
                                            int jumlahSyer = 0;
                                            jumlahSyer = syerPembilang / syerPenyebut;
                                            p2.setSyerPembilang(jumlahSyer);
                                            p2.setSyerPenyebut(jumlahSyer);
                                        } else {
                                            p2.setSyerPembilang(syerPembilang);
                                            p2.setSyerPenyebut(syerPenyebut);
                                        }
                                        //p2.setKaitan(pp.getKaitan());
                                        //p2.setDalamanNilai1(pp.getDalamanNilai1());
                                        p2.setInfoAudit(ia);
                                        newList.add(p2);
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (newList.size() > 0) {
                        p.setSenaraiPihak(newList);
                    }
                }

            }

        } //Renew LPS For Pelupusan
        else if ("RLPS".equals(uv.kodUrusan)) {
            LOG.info("Renew Lesen Baru");
            String kodTuntut = "DISR4A";
            String kodTransaksi = "72499";
            String idPermit = "";
            //       KodTuntut tuntut = kodTuntutDAO.findById("DIS4RA") ;
            //        LOG.info("Nama : " + tuntut.getNama()) ;
            PermohonanTuntutanKos mohonTuntut = new PermohonanTuntutanKos();
            PermohonanTuntutanBayar mohonTuntutBayar = new PermohonanTuntutanBayar();
            mohonTuntut.setInfoAudit(ia);
            mohonTuntut.setPermohonan(p);
            mohonTuntut.setItem("Bayaran Pembaharuan LPS");
            mohonTuntut.setCawangan(p.getCawangan());
            mohonTuntut.setAmaunTuntutan(atv.get(0).getAmaun());
            mohonTuntut.setKodTransaksi(kodTransaksiDAO.findById(kodTransaksi));
            mohonTuntut.setKodTuntut(kodTuntutDAO.findById(kodTuntut));
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntut);

            mohonTuntutBayar.setAmaun(atv.get(0).getAmaun());
            mohonTuntutBayar.setInfoAudit(ia);
            mohonTuntutBayar.setPermohonanTuntutanKos(mohonTuntut);
            mohonTuntutBayar.setTarikhBayar(new java.util.Date());
            mohonTuntutBayar.setDokumenKewangan(dk);
            mohonTuntutBayarDAO.save(mohonTuntutBayar);
            Permit permit = pelupusanService.findIdPermitByNoPermit(uv.getTeks1());
            if (permit != null) {
                List<PermitSahLaku> tempList = pelupusanService.findPermitSahLakuByIdPermit(permit.getIdPermit());
                PermitSahLaku permitSahLaku = new PermitSahLaku();
                permitSahLaku.setPermit(permit);
                permitSahLaku.setPermohonan(p);
                permitSahLaku.setInfoAudit(ia);
                permitSahLaku.setKodCawangan(p.getCawangan());
                permitSahLakuDAO.save(permitSahLaku);
            }

        } else if ("RYKN".equals(uv.kodUrusan)) {
            LOG.info("Bayaran 1/3 Premium");
            String kodTuntut = "DISPRM";
            String kodTransaksi = "12107";

            //       KodTuntut tuntut = kodTuntutDAO.findById("DIS4RA") ;
            //        LOG.info("Nama : " + tuntut.getNama()) ;
            PermohonanTuntutanKos mohonTuntutForPBMT = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(uv.getTeks1(), "DISPRM");
            PermohonanTuntutanKos mohonTuntut = new PermohonanTuntutanKos();
            PermohonanTuntutanBayar mohonTuntutBayar = new PermohonanTuntutanBayar();
            mohonTuntut.setInfoAudit(ia);
            mohonTuntut.setPermohonan(p);
            mohonTuntut.setItem("Bayaran 1/3 Premium");
            mohonTuntut.setCawangan(p.getCawangan());
            if (mohonTuntutForPBMT != null) {
                if (mohonTuntutForPBMT.getAmaunTuntutan() != null) {
                    mohonTuntut.setAmaunTuntutan(mohonTuntutForPBMT.getAmaunTuntutan());
                }
            }
            mohonTuntut.setAmaunSebenar(atv.get(0).getAmaun());
//            mohonTuntut.setAmaunTuntutan(atv.get(0).getAmaun());
            mohonTuntut.setKodTransaksi(kodTransaksiDAO.findById(kodTransaksi));
            mohonTuntut.setKodTuntut(kodTuntutDAO.findById(kodTuntut));
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntut);

//            if(mohonTuntutForPBMT != null){
//                BigDecimal b = mohonTuntutForPBMT.getAmaunTuntutan() ;
//                mohonTuntutForPBMT.setAmaunTuntutan(b.subtract(atv.get(0).getAmaun()));
//                pelupusanService.simpanPermohonanTuntutanKos1(mohonTuntut);
//            }
            mohonTuntutBayar.setAmaun(atv.get(0).getAmaun());
            mohonTuntutBayar.setInfoAudit(ia);
            mohonTuntutBayar.setPermohonanTuntutanKos(mohonTuntut);
            mohonTuntutBayar.setTarikhBayar(new java.util.Date());
            mohonTuntutBayar.setDokumenKewangan(dk);
            mohonTuntutBayarDAO.save(mohonTuntutBayar);

        } else if ("PBMT".equals(uv.kodUrusan)) {
            if (uv.getTeks1() != null) {
                LOG.info("Check id kelompok");
                String idP = StringUtils.strip(uv.getTeks1());
                Permohonan pTemp = new Permohonan();
                pTemp = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idP);
                if (pTemp != null) {
                    if (pTemp.getCatatan().equals("K")) { //Kelompok
                        List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                        listMohonKelompok = pelupusanService.findMohonKelompokByIdMohonIndukJenisKelompok(idP, "K");
                        if (listMohonKelompok.size() > 0) {
                            PermohonanKelompok pk = listMohonKelompok.get(0);
                            PermohonanKelompok permohonanKelompok = new PermohonanKelompok();
                            permohonanKelompok.setInfoAudit(ia);
                            permohonanKelompok.setPermohonanInduk(pk.getPermohonanInduk());
                            permohonanKelompok.setPermohonan(p);
                            permohonanKelompok.setJenisKelopok("K");
                            String sequenceIdKelompok = idKelompokGenerator.generate(configuration.getProperty("kodNegeri"), ia.getDimasukOleh().getKodCawangan(), permohonanKelompok);
                            permohonanKelompok.setIdMohonKelompok(sequenceIdKelompok);
                            pelupusanService.simpanPermohonanKelompok(permohonanKelompok);
                            LOG.info("Penambahan Permohonan Untuk Berkelompok");
                        } else {
                            PermohonanKelompok permohonanKelompok = new PermohonanKelompok();
                            permohonanKelompok.setInfoAudit(ia);
                            permohonanKelompok.setPermohonanInduk(pTemp);
                            permohonanKelompok.setPermohonan(p);
                            permohonanKelompok.setJenisKelopok("K");
                            String sequenceIdKelompok = idKelompokGenerator.generate(configuration.getProperty("kodNegeri"), ia.getDimasukOleh().getKodCawangan(), permohonanKelompok);
                            permohonanKelompok.setIdMohonKelompok(sequenceIdKelompok);
                            pelupusanService.simpanPermohonanKelompok(permohonanKelompok);
                            LOG.info("Penambahan Pertama Permohonan Untuk Berkelompok");
                        }
                    }
                }
            }

        } else if (("TMAME".equals(uv.kodUrusan)) || ("TMAMF".equals(uv.kodUrusan)) || ("TMAMT".equals(uv.kodUrusan))) {
            Permohonan mohon = uv.getPermohonan();
            mohon.setSebab("Pesaka");
            ia = mohon.getInfoAudit();
            ia.setDiKemaskiniOleh(penggunaDAO.findById("portal"));
            ia.setTarikhKemaskini(new Date());
            mohon = pesakaService.saveMohon(mohon);
        } else if ("SEK4".equals(uv.kodUrusan)) {
            Permohonan mohon = uv.getPermohonan();
            BigDecimal deposit = new BigDecimal(BigInteger.ZERO);
            BigDecimal feePermohonan = new BigDecimal(BigInteger.ZERO);
            for (TransaksiValue tv : atv) {
                if (tv.getPosition() == 1) {
                    deposit = tv.amaun;
                }
                if (tv.getPosition() == 2) {
                    feePermohonan = tv.getAmaun();
                }
            }

            PermohonanPengambilan mohonAmbil = pengambilanAPTService.findPermohonanPengambilanByIdMH(mohon.getIdPermohonan());
            if (mohonAmbil == null) {
                mohonAmbil = new PermohonanPengambilan();
            }
            mohonAmbil.setPermohonan(mohon);
            mohonAmbil.setInfoAudit(mohon.getInfoAudit());
            mohonAmbil.setKatPengambilan(uv.kategori);
            mohonAmbil.setNilaiJPPH(uv.nilaiJPPH);
            mohonAmbil.setDeposit(deposit);
            mohonAmbil.setFeePermohonan(feePermohonan);
            pengambilanAPTService.saveOrUpdatePermohonanPengambilan(mohonAmbil);
            for (int i = 0; i < uv.getJumlahTDK().intValue(); i++) {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                mohonHakmilik.setPermohonan(mohon);
                mohonHakmilik.setInfoAudit(mohon.getInfoAudit());
                pengambilanAPTService.saveOrUpdateMohonHakmilik(mohonHakmilik);
            }
            //TODO
            // uv.nilaiJPPH;
            //uv.kategori;
            // uv.jumlahTDK;
            //to do
            //pemohon, mohon ambil hakmilik, mohon_lepas_ambil
        } else if ("831".equals(uv.kodUrusan)) {
            Permohonan mohon = uv.getPermohonan();
            BigDecimal deposit = new BigDecimal(BigInteger.ZERO);
            BigDecimal feePermohonan = new BigDecimal(BigInteger.ZERO);
            PermohonanPengambilan sek4 = pengambilanAPTService.findPermohonanPengambilanByIdMH(uv.getIdPermohonanSebelum());
            PermohonanPengambilan mohonAmbil = pengambilanAPTService.findPermohonanPengambilanByIdMH(mohon.getIdPermohonan());
            if (mohonAmbil == null) {
                mohonAmbil = new PermohonanPengambilan();
            }
            mohonAmbil.setPermohonan(mohon);
            mohonAmbil.setInfoAudit(mohon.getInfoAudit());
            if (sek4 != null) {
                mohonAmbil.setKatPengambilan(sek4.getKatPengambilan());
                mohonAmbil.setNilaiJPPH(sek4.getNilaiJPPH());
                mohonAmbil.setDeposit(sek4.getDeposit());
                mohonAmbil.setFeePermohonan(sek4.getFeePermohonan());
                mohonAmbil.setTujuanPermohonan(sek4.getTujuanPermohonan());
                mohonAmbil.setKedesakan(sek4.getKedesakan());
            }
            pengambilanAPTService.saveOrUpdatePermohonanPengambilan(mohonAmbil);

            List<HakmilikPermohonan> listHp = pengambilanAPTService.findHakmilikPermohonan(uv.getIdPermohonanSebelum());
            for (HakmilikPermohonan hp : listHp) {
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                if (hp.getHakmilik() != null) {
                    mohonHakmilik.setHakmilik(hp.getHakmilik());
                }
                mohonHakmilik.setPermohonan(mohon);
                mohonHakmilik.setNoLot(hp.getNoLot());
                mohonHakmilik.setLuasTerlibat(hp.getLuasTerlibat());
                mohonHakmilik.setBandarPekanMukimBaru(hp.getBandarPekanMukimBaru());
                mohonHakmilik.setKodUnitLuas(hp.getKodUnitLuas());
                mohonHakmilik.setLot(hp.getLot());
                // mohonHakmilik.setKodDUN(hp.getKodDUN());
                mohonHakmilik.setCawangan(hp.getCawangan());
                //mohonHakmilik.setKodKawasanParlimen(hp.getKodKawasanParlimen());
//                mohonHakmilik.getluas
                mohonHakmilik.setInfoAudit(mohon.getInfoAudit());
                pengambilanAPTService.saveOrUpdateMohonHakmilik(mohonHakmilik);

                //COPY DATA SEK4 INTO MOHON AMBIL HAKMILIK
                PermohonanPengambilanHakmilik permohonanPengambilanHakmilik = pengambilanAPTService.findPermohonanPengambilanByIdMH(hp.getId());

                if (permohonanPengambilanHakmilik != null) {
                    PermohonanPengambilanHakmilik pph = new PermohonanPengambilanHakmilik();

                    pph.setKodUnitLuas(permohonanPengambilanHakmilik.getKodUnitLuas());
                    pph.setHakmilikPermohonan(hakmilikPermohonanDAO.findById(mohonHakmilik.getId()));
                    pph.setLuasAmbil(permohonanPengambilanHakmilik.getLuasAmbil());
                    pph.setInfoAudit(mohon.getInfoAudit());
                    pengambilanAPTService.saveOrUpdatehakmilikpermohonan(pph);
                }

            }
            //COPY DATA SEK4 INTO MOHON AMBIL HAKMILIK
//            List<PermohonanPengambilanHakmilik> pengambilanHakmilik = pengambilanAPTService.findListPermohonanPengambilanByIdMohon(uv.getIdPermohonanSebelum());
//            for (PermohonanPengambilanHakmilik permohonanPengambilanHakmilik : pengambilanHakmilik) {
//                PermohonanPengambilanHakmilik pph = new PermohonanPengambilanHakmilik();
//
//                pph.setKodUnitLuas(permohonanPengambilanHakmilik.getKodUnitLuas());
//                pph.setHakmilikPermohonan(mohonHakmilik.);
//                pph.setLuasAmbil(permohonanPengambilanHakmilik.getLuasAmbil());
//                pph.setInfoAudit(mohon.getInfoAudit());
//                pengambilanAPTService.saveOrUpdatehakmilikpermohonan(pph);
//
//            }
            //COPY DATA SEK4 INTO PEMOHON
            Pemohon pemohon = pengambilanAPTService.findPermohonByIdMHN(uv.getIdPermohonanSebelum());
            Pemohon pemohonBaru = pengambilanAPTService.findPermohonByIdMHN(uv.permohonan.getIdPermohonan());
            if (pemohonBaru == null) {
                if (pemohon != null) {
                    Pemohon p1 = new Pemohon();
                    p1.setPermohonan(mohon);
                    p1.setPihak(pemohon.getPihak());
                    p1.setCawangan(pemohon.getCawangan());
                    p1.setInfoAudit(mohon.getInfoAudit());

                    pengambilanAPTService.saveOrUpdatePemohon(p1);
                }
            }

            //copy DATA SEK4 INTO MOHON LEPAS AMBIL
            StatusTanahLepasPengambilan lepasPengambilan = pengambilanAPTService.findLpsAmbilByIdMHN(uv.getIdPermohonanSebelum());
            StatusTanahLepasPengambilan stlp = new StatusTanahLepasPengambilan();
            if (lepasPengambilan != null) {

                stlp.setIdPermohonan(mohon);
                stlp.setKodStatusTanahLepasPengambilan(lepasPengambilan.getKodStatusTanahLepasPengambilan());
                stlp.setCawangan(lepasPengambilan.getCawangan());
                stlp.setInfoAudit(mohon.getInfoAudit());
            }
            pengambilanAPTService.saveOrUpdateLpsAmbil(stlp);

            //COPY DATA SEK4 INTO MOHON RUJ LUAR
            PermohonanRujukanLuar permohonanRujLuar = pengambilanAPTService.findRujLuarByIdMHN(uv.getIdPermohonanSebelum());
            PermohonanRujukanLuar prl = new PermohonanRujukanLuar();
            if (permohonanRujLuar != null) {

                prl.setCawangan(permohonanRujLuar.getCawangan());
                prl.setPermohonan(mohon);
                prl.setKodRujukan(permohonanRujLuar.getKodRujukan());
                prl.setNoRujukan(permohonanRujLuar.getNoRujukan());
                prl.setTarikhRujukan(permohonanRujLuar.getTarikhRujukan());
                prl.setInfoAudit(permohonanRujLuar.getInfoAudit());

            }
            pengambilanAPTService.saveOrUpdateRujLuar(prl);

        }
//        else if ("TMAME".equals(uv.kodUrusan)) {
//            EtappPerintah etappPerintah = new EtappPerintah();
//            String ic = null;
//            for (DokumenValue dv : uv.getSenaraiDokumenSerahan()) {
//                if (dv.getKodDokumen().equals("BE")) {
//                    LOG.info("dv.noRujukan : " + dv.noRujukan);
//                    if (dv.noRujukan != null) {
//                        etappPerintah = etappPerintahDAO.findById(dv.noRujukan);
//                        if (etappPerintah != null) {
//                            for (String s : uv.getHakmilikPermohonan()) {
////                                PermohonanRujukanLuar mohonRuj = new PermohonanRujukanLuar();
////                                mohonRuj.setPermohonan(uv.getPermohonan());
////                                mohonRuj.setKodRujukan(kodRujukanDAO.findById("FL"));
////                                mohonRuj.setKodPerintah(kodPerintahDAO.findById("M"));
////                                mohonRuj.setNamaSidang(etappPerintah.getTmpBicara());
////                                mohonRuj.setNoSidang(null);
////                                mohonRuj.setTarikhSidang(etappPerintah.getTarikhPerintah());
////                                mohonRuj.setNoRujukan(etappPerintah.getNoFail());
////                                mohonRuj.setNoFail(etappPerintah.getNoFail());
////                                mohonRuj.setInfoAudit(pesakaService.getInfoAudit());
////                                mohonRuj.setCawangan(uv.getPermohonan().getCawangan());
////                            if (dv.noRujukan != null) {
////                                EtappHakmilik etappHakmilik = pesakaService.findHakmilik(dv.noRujukan, s);
////                                List<EtappBorangE> listBorangE = etappHakmilik.getListEtappBorangE();
////                                for (EtappBorangE be : listBorangE) {
////
//////                                Pihak crPihak = pesakaService.findPihak(be.getNamaWaris(), be.getNoKpWaris());
//////                                if (crPihak != null) {
//////                                    Pihak pihakE = crPihak;
//////                                }
//////                                else {
////                                    Pihak pihakE = new Pihak();
////                                    pihakE.setNama(be.getNamaWaris());
////                                    if (be.getNoKpWaris().length() == 12) {
////                                        ic = pesakaService.tukarIC(be.getNoKpWaris());
////                                        LOG.info("ic = " + ic);
////                                        pihakE.setNoPengenalan(ic);
////                                    } else {
////                                        pihakE.setNoPengenalan(be.getNoKpWaris());
////                                    }
////                                    KodJenisPengenalan kpE = pesakaService.findKP(be.getJenisPengenalan());
////                                    pihakE.setJenisPengenalan(kpE);
////                                    pihakE.setWargaNegara(be.getKewarganaan() != null ? kodWarganegaraDAO.findById(be.getKewarganaan()) : null);
////                                    pihakE.setAlamat1(be.getAlamat1());
////                                    pihakE.setAlamat2(be.getAlamat2());
////                                    pihakE.setAlamat3(be.getAlamat3());
////                                    pihakE.setAlamat4(be.getBandar());
////                                    pihakE.setPoskod(be.getPoskod());
////                                    pihakE.setNegeri(kodNegeriDAO.findById(be.getNegeri()));
////                                    pihakE.setInfoAudit(pesakaService.getInfoAudit());
////                                    pihakE = pesakaService.savePihak(pihakE);
//////                                }
////
////                                    String query = "SELECT b from etanah.model.etapp.EtappBorangH b WHERE b.etappBorangE =:idBe";
////                                    Query q = sessionProvider.get().createQuery(query);
////                                    q.setLong("idBe", be.getIdEtBe());
////                                    List<EtappBorangH> bh = (List<EtappBorangH>) q.list();
////
////                                    LOG.info("bh list : " + bh.size());
////
////                                    if (bh.size() > 0) {
//////                                    for (EtappBorangH bhe : bh) {
////                                        for (int n = 0; n < bh.size(); n++) {
////                                            EtappBorangH bhe = bh.get(n);
////
////                                            LOG.info("bh : bh size > 0, n = " + n);
////
////                                            Pihak pihakH = new Pihak();
////                                            pihakH.setNama(bhe.getNamaWaris());
////                                            if (bhe.getNoKpWaris().length() == 12) {
////                                                ic = pesakaService.tukarIC(bhe.getNoKpWaris());
////                                                LOG.info("ic = " + ic);
////                                                pihakH.setNoPengenalan(ic);
////                                            } else {
////                                                pihakH.setNoPengenalan(bhe.getNoKpWaris());
////                                            }
////                                            pihakH.setWargaNegara(bhe.getKewarganaan() != null ? kodWarganegaraDAO.findById(bhe.getKewarganaan()) : null);
////                                            KodJenisPengenalan kpH = pesakaService.findKP(bhe.getJenisPengenalan());
////                                            pihakH.setJenisPengenalan(kpH);
////
////                                            pihakH.setAlamat1(bhe.getAlamat1());
////                                            pihakH.setAlamat2(bhe.getAlamat2());
////                                            pihakH.setAlamat3(bhe.getAlamat3());
////                                            pihakH.setAlamat4(bhe.getBandar());
////                                            pihakH.setPoskod(bhe.getPoskod());
////                                            pihakH.setNegeri(kodNegeriDAO.findById(bhe.getNegeri()));
////                                            pihakH.setInfoAudit(pesakaService.getInfoAudit());
////                                            pihakH = pesakaService.savePihak(pihakH);
////
////                                            PermohonanPihak mohonPihak2 = new PermohonanPihak();
////                                            mohonPihak2.setPermohonan(uv.getPermohonan());
////                                            mohonPihak2.setWargaNegara(bhe.getKewarganaan() != null ? kodWarganegaraDAO.findById(bhe.getKewarganaan()) : null);
////                                            mohonPihak2.setPihak(pihakH);
////                                            mohonPihak2.setNama(bhe.getNamaWaris());
////                                            mohonPihak2.setJenisPengenalan(pihakH.getJenisPengenalan());
////                                            if (bhe.getNoKpWaris().length() == 12) {
////                                                ic = pesakaService.tukarIC(bhe.getNoKpWaris());
////                                                LOG.info("ic = " + ic);
////                                                mohonPihak2.setNoPengenalan(ic);
////                                            } else {
////                                                mohonPihak2.setNoPengenalan(bhe.getNoKpWaris());
////                                            }
////                                            if (bhe.getUmur() != null) {
////                                                mohonPihak2.setUmur(Integer.parseInt(bhe.getUmur()));
////                                            } else {
////                                                int age = bhe.getNoKpWaris().length();
////                                                LOG.info("length ic : " + age);
////                                                if (age == 12 || age == 14) {
////                                                    int umur = pesakaService.kiraUmur(bhe.getNoKpWaris());
////                                                    mohonPihak2.setUmur(umur);
////                                                } else {
////                                                    mohonPihak2.setUmur(null);
////                                                }
////                                            }
////                                            Alamat alamat2 = new Alamat();
////                                            AlamatSurat alamat3 = new AlamatSurat();
////                                            try {
////                                                alamat2.setAlamat1(bhe.getAlamat1() != null ? bhe.getAlamat1() : "");
////                                                alamat2.setAlamat2(bhe.getAlamat2() != null ? bhe.getAlamat2() : "");
////                                                alamat2.setAlamat3(bhe.getAlamat3() != null ? bhe.getAlamat3() : "");
////                                                alamat2.setAlamat4(bhe.getBandar() != null ? bhe.getBandar() : "");
////                                                alamat2.setNegeri(kodNegeriDAO.findById(bhe.getNegeri()));
////                                                alamat2.setPoskod(bhe.getPoskod());
////
////                                                alamat3.setAlamatSurat1(bhe.getAlamat1() != null ? bhe.getAlamat1() : "");
////                                                alamat3.setAlamatSurat2(bhe.getAlamat2() != null ? bhe.getAlamat2() : "");
////                                                alamat3.setAlamatSurat3(bhe.getAlamat3() != null ? bhe.getAlamat3() : "");
////                                                alamat3.setAlamatSurat4(bhe.getBandar() != null ? bhe.getBandar() : "");
////                                                alamat3.setNegeriSurat(kodNegeriDAO.findById(bhe.getNegeri()));
////                                                alamat3.setPoskodSurat(bhe.getPoskod());
////                                            } catch (Exception ex) {
////                                                LOG.error(ex);
////                                            }
////                                            mohonPihak2.setAlamat(alamat2);
////                                            mohonPihak2.setAlamatSurat(alamat3);
////                                            mohonPihak2.setCawangan(uv.getPermohonan().getCawangan());
////
////                                            mohonPihak2.setSyerBersama('Y');
////                                            if ((n + 1) == bh.size()) {
////                                                mohonPihak2.setSyerPembilang((Integer.parseInt(be.getBasimati())));
////                                                mohonPihak2.setSyerPenyebut((Integer.parseInt(be.getBbsimati())));
////                                            } else {
////                                            }
////
////                                            // mohonPihak.setJumlahPembilang(Integer.parseInt(be.getBasimati()));
////                                            // mohonPihak.setJumlahPenyebut(Integer.parseInt(be.getBbsimati()));
////                                            mohonPihak2.setHakmilik(be.getEtappHakmilik().getHakmilik());
////                                            mohonPihak2.setJenis(kodPihakDAO.findById("PA"));
////                                            mohonPihak2.setInfoAudit(pesakaService.getInfoAudit());
////                                            mohonPihak2 = pesakaService.saveMohonPihak(mohonPihak2);
////
////                                            PermohonanPihakHubungan pihakHbgn = new PermohonanPihakHubungan();
////                                            pihakHbgn.setPihak(mohonPihak2);
////                                            pihakHbgn.setNama(be.getNamaWaris());
////                                            pihakHbgn.setJenisPengenalan(pihakE.getJenisPengenalan());
////                                            pihakHbgn.setWargaNegara(be.getKewarganaan() != null ? kodWarganegaraDAO.findById(be.getKewarganaan()) : null);
////                                            if (be.getNoKpWaris().length() == 12) {
////                                                ic = pesakaService.tukarIC(be.getNoKpWaris());
////                                                LOG.info("ic = " + ic);
////                                                pihakHbgn.setNoPengenalan(ic);
////                                            } else {
////                                                pihakHbgn.setNoPengenalan(be.getNoKpWaris());
////                                            }
////                                            if (be.getUmur() != null) {
////                                                pihakHbgn.setUmur(Integer.parseInt(be.getUmur()));
////                                            } else {
////                                                int age = be.getNoKpWaris().length();
////                                                LOG.info("length ic : " + age);
////                                                if (age == 12 || age == 14) {
////                                                    int umur = pesakaService.kiraUmur(be.getNoKpWaris());
////                                                    pihakHbgn.setUmur(umur);
////                                                } else {
////                                                    pihakHbgn.setUmur(null);
////                                                }
////                                            }
////                                            pihakHbgn.setAlamat1(be.getAlamat1() != null ? be.getAlamat1() : "");
////                                            pihakHbgn.setAlamat2(be.getAlamat2() != null ? be.getAlamat2() : "");
////                                            pihakHbgn.setAlamat3(be.getAlamat3() != null ? be.getAlamat3() : "");
////                                            pihakHbgn.setAlamat4(be.getBandar() != null ? be.getBandar() : "");
////                                            pihakHbgn.setNegeri(kodNegeriDAO.findById(be.getNegeri()));
////                                            pihakHbgn.setPoskod(be.getPoskod());
////                                            pihakHbgn.setCawangan(uv.getPermohonan().getCawangan());
////                                            pihakHbgn.setSyerPembilang(Integer.parseInt(be.getBasimati()));
////                                            pihakHbgn.setSyerPenyebut(Integer.parseInt(be.getBbsimati()));
////                                            // mohonPihak.setJumlahPembilang(Integer.parseInt(be.getBasimati()));
////                                            // mohonPihak.setJumlahPenyebut(Integer.parseInt(be.getBbsimati()));
////                                            pihakHbgn.setKaitan("PENERIMA AMANAH");
////                                            pihakHbgn.setInfoAudit(pesakaService.getInfoAudit());
////                                            pihakHbgn = pesakaService.saveMohonPihakHbgn(pihakHbgn);
////
////                                        }
////                                    } else {
////
////                                        LOG.info(" bh list : size = 0");
////
////                                        PermohonanPihak mohonPihak = new PermohonanPihak();
////                                        mohonPihak.setPermohonan(uv.getPermohonan());
////                                        mohonPihak.setPihak(pihakE);
////                                        mohonPihak.setWargaNegara(be.getKewarganaan() != null ? kodWarganegaraDAO.findById(be.getKewarganaan()) : null);
////                                        mohonPihak.setNama(be.getNamaWaris());
////                                        mohonPihak.setJenisPengenalan(pihakE.getJenisPengenalan());
////                                        if (be.getNoKpWaris().length() == 12) {
////                                            ic = pesakaService.tukarIC(be.getNoKpWaris());
////                                            LOG.info("ic = " + ic);
////                                            mohonPihak.setNoPengenalan(ic);
////                                        } else {
////                                            mohonPihak.setNoPengenalan(be.getNoKpWaris());
////                                        }
////                                        if (be.getUmur() != null) {
////                                            mohonPihak.setUmur(Integer.parseInt(be.getUmur()));
////                                        } else {
////                                            int age = be.getNoKpWaris().length();
////                                            LOG.info("length ic : " + age);
////                                            if (age == 12 || age == 14) {
////                                                int umur = pesakaService.kiraUmur(be.getNoKpWaris());
////                                                mohonPihak.setUmur(umur);
////                                            } else {
////                                                mohonPihak.setUmur(null);
////                                            }
////                                        }
////                                        Alamat alamat = new Alamat();
////                                        AlamatSurat alamatSurat = new AlamatSurat();
////                                        try {
////                                            alamat.setAlamat1(be.getAlamat1() != null ? be.getAlamat1() : "");
////                                            alamat.setAlamat2(be.getAlamat2() != null ? be.getAlamat2() : "");
////                                            alamat.setAlamat3(be.getAlamat3() != null ? be.getAlamat3() : "");
////                                            alamat.setAlamat4(be.getBandar() != null ? be.getBandar() : "");
////                                            alamat.setNegeri(kodNegeriDAO.findById(be.getNegeri()));
////                                            alamat.setPoskod(be.getPoskod());
////
////                                            alamatSurat.setAlamatSurat1(be.getAlamat1() != null ? be.getAlamat1() : "");
////                                            alamatSurat.setAlamatSurat2(be.getAlamat2() != null ? be.getAlamat2() : "");
////                                            alamatSurat.setAlamatSurat3(be.getAlamat3() != null ? be.getAlamat3() : "");
////                                            alamatSurat.setAlamatSurat4(be.getBandar() != null ? be.getBandar() : "");
////                                            alamatSurat.setNegeriSurat(kodNegeriDAO.findById(be.getNegeri()));
////                                            alamatSurat.setPoskodSurat(be.getPoskod());
////                                        } catch (Exception ex) {
////                                            LOG.error(ex);
////                                        }
////                                        mohonPihak.setAlamat(alamat);
////                                        mohonPihak.setAlamatSurat(alamatSurat);
////                                        mohonPihak.setCawangan(uv.getPermohonan().getCawangan());
////                                        mohonPihak.setSyerPembilang(Integer.parseInt(be.getBasimati()));
////                                        mohonPihak.setSyerPenyebut(Integer.parseInt(be.getBbsimati()));
////                                        mohonPihak.setSyerBersama('T');
////                                        // mohonPihak.setJumlahPembilang(Integer.parseInt(be.getBasimati()));
////                                        // mohonPihak.setJumlahPenyebut(Integer.parseInt(be.getBbsimati()));
////                                        mohonPihak.setHakmilik(be.getEtappHakmilik().getHakmilik());
////                                        mohonPihak.setJenis(kodPihakDAO.findById("PM"));
////                                        mohonPihak.setInfoAudit(pesakaService.getInfoAudit());
////                                        mohonPihak = pesakaService.saveMohonPihak(mohonPihak);
////
////                                    }
////                                }
//
////                            }
////                                mohonRuj.setHakmilik(hakmilikDAO.findById(s));
////                                mohonRuj = pesakaService.saveMohonRuj(mohonRuj);
////
////                                //simati
////                                HakmilikPihakBerkepentingan simati = pesakaService.findSimati(s, etappPerintah.getNoKpSimati().substring(0, 6));
////                                Pemohon pemohon = new Pemohon();
////                                pemohon.setCawangan(uv.getPermohonan().getCawangan());
////                                pemohon.setPermohonan(uv.getPermohonan());
////                                pemohon.setPihak(simati.getPihak());
////                                pemohon.setInfoAudit(pesakaService.getInfoAudit());
////                                pemohon.setHakmilik(hakmilikDAO.findById(s));
//////                        pemohon.setJenisPemohon("X");
////                                pemohon.setJenis(simati.getJenis());
////                                int age = simati.getNoPengenalan().length();
////                                LOG.info("length ic : " + age);
////                                if (age == 12 || age == 14) {
////                                    int umur = pesakaService.kiraUmur(simati.getNoPengenalan());
////                                    pemohon.setUmur(umur);
////                                } else {
////                                    pemohon.setUmur(null);
////                                }
////                                pemohon.setSyerPembilang(simati.getSyerPembilang());
////                                pemohon.setSyerPenyebut(simati.getSyerPenyebut());
////                                pemohon.setNama(simati.getNama());
////                                pemohon.setJenisPengenalan(simati.getJenisPengenalan());
////                                pemohon.setNoPengenalan(simati.getNoPengenalan());
////                                Alamat alamatP = new Alamat();
////                                AlamatSurat alamatPS = new AlamatSurat();
////                                try {
////                                    alamatP.setAlamat1(simati.getAlamat1() != null ? simati.getAlamat1() : "");
////                                    alamatP.setAlamat2(simati.getAlamat2() != null ? simati.getAlamat2() : "");
////                                    alamatP.setAlamat3(simati.getAlamat3() != null ? simati.getAlamat3() : "");
////                                    alamatP.setAlamat4(simati.getAlamat4() != null ? simati.getAlamat4() : "");
////                                    alamatP.setNegeri(simati.getNegeri());
////                                    alamatP.setPoskod(simati.getPoskod());
////
////                                    alamatPS.setAlamatSurat1(simati.getAlamat1() != null ? simati.getAlamat1() : "");
////                                    alamatPS.setAlamatSurat2(simati.getAlamat2() != null ? simati.getAlamat2() : "");
////                                    alamatPS.setAlamatSurat3(simati.getAlamat3() != null ? simati.getAlamat3() : "");
////                                    alamatPS.setAlamatSurat4(simati.getAlamat4() != null ? simati.getAlamat4() : "");
////                                    alamatPS.setNegeriSurat(simati.getNegeri());
////                                    alamatPS.setPoskodSurat(simati.getPoskod());
////                                } catch (Exception ex) {
////                                    LOG.error(ex);
////                                }
////
////                                pemohon.setAlamat(alamatP);
////                                pemohon.setAlamatSurat(alamatPS);
////                                pemohon = pesakaService.savePemohon(pemohon);
//
//                                Permohonan mohon = uv.getPermohonan();
//
////                                if (mohon.getCatatan() != null) {
////
//////                            HakmilikPermohonan hakmhn = hakmilikPermohonanDAO.findById(Long.valueOf(mohon.getCatatan()));           //  haqqiem modified
////                                    String query = "SELECT hp from etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan =:idmohon";
////                                    Query q = sessionProvider.get().createQuery(query);
////                                    q.setString("idmohon", mohon.getCatatan());
////                                    HakmilikPermohonan hakmhn = (HakmilikPermohonan) q.uniqueResult();
////
//////                        hakmhn.setPermohonan(permohonanDAO.findById(mohon.getCatatan()));
////                                    hakmhn.setHakmilik(hakmilikDAO.findById(s));
////                                    ia = hakmhn.getInfoAudit();
////                                    ia.setDiKemaskiniOleh(penggunaDAO.findById("portal"));
////                                    ia.setTarikhKemaskini(new java.util.Date());
////
////                                    hakmhn = pesakaService.saveHakmilikPermohonan(hakmhn);
////                                }
//                            }
//                        }
//
//                        Permohonan mohon = uv.getPermohonan();
//                        mohon.setSebab("Pesaka");
//                        ia = mohon.getInfoAudit();
//                        ia.setDiKemaskiniOleh(penggunaDAO.findById("portal"));
//                        ia.setTarikhKemaskini(new Date());
//                        mohon = pesakaService.saveMohon(mohon);
//                    } else {
//                    }
//                }
//            }
//        } else if ("TMAMT".equals(uv.kodUrusan)) {
//            EtappPerintah etappPerintah = new EtappPerintah();
//
//            String ic = null;
//
//            for (DokumenValue dv : uv.getSenaraiDokumenSerahan()) {
//                if (dv.getKodDokumen().equals("BT")) {
//                    LOG.info("dv.noRujukan : " + dv.noRujukan);
//                    if (dv.noRujukan != null) {
//                        etappPerintah = etappPerintahDAO.findById(dv.noRujukan);
//                        if (etappPerintah != null) {
//                            for (String s : uv.getHakmilikPermohonan()) {
////                                PermohonanRujukanLuar mohonRuj = new PermohonanRujukanLuar();
////                                mohonRuj.setPermohonan(uv.getPermohonan());
////                                mohonRuj.setKodRujukan(kodRujukanDAO.findById("FL"));
////                                mohonRuj.setKodPerintah(kodPerintahDAO.findById("M"));
////                                mohonRuj.setNamaSidang(etappPerintah.getTmpBicara());
////                                mohonRuj.setNoSidang(null);
////                                mohonRuj.setTarikhSidang(etappPerintah.getTarikhPerintah());
////                                mohonRuj.setNoRujukan(etappPerintah.getNoFail());
////                                mohonRuj.setNoFail(etappPerintah.getNoFail());
////                                mohonRuj.setInfoAudit(pesakaService.getInfoAudit());
////                                mohonRuj.setCawangan(uv.getPermohonan().getCawangan());
////                            if (dv.noRujukan != null) {
////                                EtappHakmilik etappHakmilik = pesakaService.findHakmilik(dv.noRujukan, s);
////                                List<EtappBorangE> listBorangE = etappHakmilik.getListEtappBorangE();
////                                for (EtappBorangE be : listBorangE) {
////
//////                                Pihak crPihak = pesakaService.findPihak(be.getNamaWaris(), be.getNoKpWaris());
//////                                if (crPihak != null) {
//////                                    Pihak pihakE = crPihak;
//////                                }
//////                                else {
////                                    Pihak pihakE = new Pihak();
////                                    pihakE.setNama(be.getNamaWaris());
////                                    if (be.getNoKpWaris().length() == 12) {
////                                        ic = pesakaService.tukarIC(be.getNoKpWaris());
////                                        LOG.info("ic = " + ic);
////                                        pihakE.setNoPengenalan(ic);
////                                    } else {
////                                        pihakE.setNoPengenalan(be.getNoKpWaris());
////                                    }
////                                    KodJenisPengenalan kpE = pesakaService.findKP(be.getJenisPengenalan());
////                                    pihakE.setJenisPengenalan(kpE);
////                                    pihakE.setWargaNegara(be.getKewarganaan() != null ? kodWarganegaraDAO.findById(be.getKewarganaan()) : null);
////                                    pihakE.setAlamat1(be.getAlamat1());
////                                    pihakE.setAlamat2(be.getAlamat2());
////                                    pihakE.setAlamat3(be.getAlamat3());
////                                    pihakE.setAlamat4(be.getBandar());
////                                    pihakE.setPoskod(be.getPoskod());
////                                    pihakE.setNegeri(kodNegeriDAO.findById(be.getNegeri()));
////                                    pihakE.setInfoAudit(pesakaService.getInfoAudit());
////                                    pihakE = pesakaService.savePihak(pihakE);
//////                                }
////
////                                    String query = "SELECT b from etanah.model.etapp.EtappBorangH b WHERE b.etappBorangE =:idBe";
////                                    Query q = sessionProvider.get().createQuery(query);
////                                    q.setLong("idBe", be.getIdEtBe());
////                                    List<EtappBorangH> bh = (List<EtappBorangH>) q.list();
////
////                                    LOG.info("bh list : " + bh.size());
////
////                                    if (bh.size() > 0) {
//////                                    for (EtappBorangH bhe : bh) {
////                                        for (int n = 0; n < bh.size(); n++) {
////                                            EtappBorangH bhe = bh.get(n);
////
////                                            LOG.info("bh : bh size > 0, n = " + n);
////
////                                            Pihak pihakH = new Pihak();
////                                            pihakH.setNama(bhe.getNamaWaris());
////                                            if (bhe.getNoKpWaris().length() == 12) {
////                                                ic = pesakaService.tukarIC(bhe.getNoKpWaris());
////                                                LOG.info("ic = " + ic);
////                                                pihakH.setNoPengenalan(ic);
////                                            } else {
////                                                pihakH.setNoPengenalan(bhe.getNoKpWaris());
////                                            }
////                                            pihakH.setWargaNegara(bhe.getKewarganaan() != null ? kodWarganegaraDAO.findById(bhe.getKewarganaan()) : null);
////                                            KodJenisPengenalan kpH = pesakaService.findKP(bhe.getJenisPengenalan());
////                                            pihakH.setJenisPengenalan(kpH);
////                                            pihakH.setAlamat1(bhe.getAlamat1());
////                                            pihakH.setAlamat2(bhe.getAlamat2());
////                                            pihakH.setAlamat3(bhe.getAlamat3());
////                                            pihakH.setAlamat4(bhe.getBandar());
////                                            pihakH.setPoskod(bhe.getPoskod());
////                                            pihakH.setNegeri(kodNegeriDAO.findById(bhe.getNegeri()));
////                                            pihakH.setInfoAudit(pesakaService.getInfoAudit());
////                                            pihakH = pesakaService.savePihak(pihakH);
////
////                                            PermohonanPihak mohonPihak2 = new PermohonanPihak();
////                                            mohonPihak2.setPermohonan(uv.getPermohonan());
////                                            mohonPihak2.setWargaNegara(bhe.getKewarganaan() != null ? kodWarganegaraDAO.findById(bhe.getKewarganaan()) : null);
////                                            mohonPihak2.setPihak(pihakH);
////                                            mohonPihak2.setNama(bhe.getNamaWaris());
////                                            mohonPihak2.setJenisPengenalan(pihakH.getJenisPengenalan());
////                                            if (bhe.getNoKpWaris().length() == 12) {
////                                                ic = pesakaService.tukarIC(bhe.getNoKpWaris());
////                                                LOG.info("ic = " + ic);
////                                                mohonPihak2.setNoPengenalan(ic);
////                                            } else {
////                                                mohonPihak2.setNoPengenalan(bhe.getNoKpWaris());
////                                            }
////                                            if (bhe.getUmur() != null) {
////                                                mohonPihak2.setUmur(Integer.parseInt(bhe.getUmur()));
////                                            } else {
////                                                int age = bhe.getNoKpWaris().length();
////                                                LOG.info("length ic : " + age);
////                                                if (age == 12 || age == 14) {
////                                                    int umur = pesakaService.kiraUmur(bhe.getNoKpWaris());
////                                                    mohonPihak2.setUmur(umur);
////                                                } else {
////                                                    mohonPihak2.setUmur(null);
////                                                }
////                                            }
////                                            Alamat alamat2 = new Alamat();
////                                            AlamatSurat alamat3 = new AlamatSurat();
////                                            try {
////                                                alamat2.setAlamat1(bhe.getAlamat1() != null ? bhe.getAlamat1() : "");
////                                                alamat2.setAlamat2(bhe.getAlamat2() != null ? bhe.getAlamat2() : "");
////                                                alamat2.setAlamat3(bhe.getAlamat3() != null ? bhe.getAlamat3() : "");
////                                                alamat2.setAlamat4(bhe.getBandar() != null ? bhe.getBandar() : "");
////                                                alamat2.setNegeri(kodNegeriDAO.findById(bhe.getNegeri()));
////                                                alamat2.setPoskod(bhe.getPoskod());
////
////                                                alamat3.setAlamatSurat1(bhe.getAlamat1() != null ? bhe.getAlamat1() : "");
////                                                alamat3.setAlamatSurat2(bhe.getAlamat2() != null ? bhe.getAlamat2() : "");
////                                                alamat3.setAlamatSurat3(bhe.getAlamat3() != null ? bhe.getAlamat3() : "");
////                                                alamat3.setAlamatSurat4(bhe.getBandar() != null ? bhe.getBandar() : "");
////                                                alamat3.setNegeriSurat(kodNegeriDAO.findById(bhe.getNegeri()));
////                                                alamat3.setPoskodSurat(bhe.getPoskod());
////                                            } catch (Exception ex) {
////                                                LOG.error(ex);
////                                            }
////                                            mohonPihak2.setAlamat(alamat2);
////                                            mohonPihak2.setAlamatSurat(alamat3);
////                                            mohonPihak2.setCawangan(uv.getPermohonan().getCawangan());
////
////                                            mohonPihak2.setSyerBersama('Y');
////                                            if ((n + 1) == bh.size()) {
////                                                mohonPihak2.setSyerPembilang((Integer.parseInt(be.getBasimati())));
////                                                mohonPihak2.setSyerPenyebut((Integer.parseInt(be.getBbsimati())));
////                                            } else {
////                                            }
////
////                                        // mohonPihak.setJumlahPembilang(Integer.parseInt(be.getBasimati()));
////                                            // mohonPihak.setJumlahPenyebut(Integer.parseInt(be.getBbsimati()));
////                                            mohonPihak2.setHakmilik(be.getEtappHakmilik().getHakmilik());
////                                            mohonPihak2.setJenis(kodPihakDAO.findById("PA"));
////                                            mohonPihak2.setInfoAudit(pesakaService.getInfoAudit());
////                                            mohonPihak2 = pesakaService.saveMohonPihak(mohonPihak2);
////
////                                            PermohonanPihakHubungan pihakHbgn = new PermohonanPihakHubungan();
////                                            pihakHbgn.setPihak(mohonPihak2);
////                                            pihakHbgn.setNama(be.getNamaWaris());
////                                            pihakHbgn.setJenisPengenalan(pihakE.getJenisPengenalan());
////                                            pihakHbgn.setWargaNegara(be.getKewarganaan() != null ? kodWarganegaraDAO.findById(be.getKewarganaan()) : null);
////                                            if (bhe.getNoKpWaris().length() == 12) {
////                                                ic = pesakaService.tukarIC(bhe.getNoKpWaris());
////                                                LOG.info("ic = " + ic);
////                                                pihakHbgn.setNoPengenalan(ic);
////                                            } else {
////                                                pihakHbgn.setNoPengenalan(bhe.getNoKpWaris());
////                                            }
////                                            if (be.getUmur() != null) {
////                                                pihakHbgn.setUmur(Integer.parseInt(be.getUmur()));
////                                            } else {
////                                                int age = be.getNoKpWaris().length();
////                                                LOG.info("length ic : " + age);
////                                                if (age == 12 || age == 14) {
////                                                    int umur = pesakaService.kiraUmur(be.getNoKpWaris());
////                                                    pihakHbgn.setUmur(umur);
////                                                } else {
////                                                    pihakHbgn.setUmur(null);
////                                                }
////                                            }
////                                            pihakHbgn.setAlamat1(be.getAlamat1() != null ? be.getAlamat1() : "");
////                                            pihakHbgn.setAlamat2(be.getAlamat2() != null ? be.getAlamat2() : "");
////                                            pihakHbgn.setAlamat3(be.getAlamat3() != null ? be.getAlamat3() : "");
////                                            pihakHbgn.setAlamat4(be.getBandar() != null ? be.getBandar() : "");
////                                            pihakHbgn.setNegeri(kodNegeriDAO.findById(be.getNegeri()));
////                                            pihakHbgn.setPoskod(be.getPoskod());
////                                            pihakHbgn.setCawangan(uv.getPermohonan().getCawangan());
////                                            pihakHbgn.setSyerPembilang(Integer.parseInt(be.getBasimati()));
////                                            pihakHbgn.setSyerPenyebut(Integer.parseInt(be.getBbsimati()));
////                                        // mohonPihak.setJumlahPembilang(Integer.parseInt(be.getBasimati()));
////                                            // mohonPihak.setJumlahPenyebut(Integer.parseInt(be.getBbsimati()));
////                                            pihakHbgn.setKaitan("PENERIMA AMANAH");
////                                            pihakHbgn.setInfoAudit(pesakaService.getInfoAudit());
////                                            pihakHbgn = pesakaService.saveMohonPihakHbgn(pihakHbgn);
////
////                                        }
////                                    } else {
////
////                                        LOG.info(" bh list : size = 0");
////
////                                        PermohonanPihak mohonPihak = new PermohonanPihak();
////                                        mohonPihak.setPermohonan(uv.getPermohonan());
////                                        mohonPihak.setPihak(pihakE);
////                                        mohonPihak.setWargaNegara(be.getKewarganaan() != null ? kodWarganegaraDAO.findById(be.getKewarganaan()) : null);
////                                        mohonPihak.setNama(be.getNamaWaris());
////                                        mohonPihak.setJenisPengenalan(pihakE.getJenisPengenalan());
////                                        if (be.getNoKpWaris().length() == 12) {
////                                            ic = pesakaService.tukarIC(be.getNoKpWaris());
////                                            LOG.info("ic = " + ic);
////                                            mohonPihak.setNoPengenalan(ic);
////                                        } else {
////                                            mohonPihak.setNoPengenalan(be.getNoKpWaris());
////                                        }
////                                        if (be.getUmur() != null) {
////                                            mohonPihak.setUmur(Integer.parseInt(be.getUmur()));
////                                        } else {
////                                            int age = be.getNoKpWaris().length();
////                                            LOG.info("length ic : " + age);
////                                            if (age == 12 || age == 14) {
////                                                int umur = pesakaService.kiraUmur(be.getNoKpWaris());
////                                                mohonPihak.setUmur(umur);
////                                            } else {
////                                                mohonPihak.setUmur(null);
////                                            }
////                                        }
////                                        Alamat alamat = new Alamat();
////                                        AlamatSurat alamatSurat = new AlamatSurat();
////                                        try {
////                                            alamat.setAlamat1(be.getAlamat1() != null ? be.getAlamat1() : "");
////                                            alamat.setAlamat2(be.getAlamat2() != null ? be.getAlamat2() : "");
////                                            alamat.setAlamat3(be.getAlamat3() != null ? be.getAlamat3() : "");
////                                            alamat.setAlamat4(be.getBandar() != null ? be.getBandar() : "");
////                                            alamat.setNegeri(kodNegeriDAO.findById(be.getNegeri()));
////                                            alamat.setPoskod(be.getPoskod());
////
////                                            alamatSurat.setAlamatSurat1(be.getAlamat1() != null ? be.getAlamat1() : "");
////                                            alamatSurat.setAlamatSurat2(be.getAlamat2() != null ? be.getAlamat2() : "");
////                                            alamatSurat.setAlamatSurat3(be.getAlamat3() != null ? be.getAlamat3() : "");
////                                            alamatSurat.setAlamatSurat4(be.getBandar() != null ? be.getBandar() : "");
////                                            alamatSurat.setNegeriSurat(kodNegeriDAO.findById(be.getNegeri()));
////                                            alamatSurat.setPoskodSurat(be.getPoskod());
////                                        } catch (Exception ex) {
////                                            LOG.error(ex);
////                                        }
////                                        mohonPihak.setAlamat(alamat);
////                                        mohonPihak.setAlamatSurat(alamatSurat);
////                                        mohonPihak.setCawangan(uv.getPermohonan().getCawangan());
////                                        mohonPihak.setSyerPembilang(Integer.parseInt(be.getBasimati()));
////                                        mohonPihak.setSyerPenyebut(Integer.parseInt(be.getBbsimati()));
////                                        mohonPihak.setSyerBersama('T');
////                                    // mohonPihak.setJumlahPembilang(Integer.parseInt(be.getBasimati()));
////                                        // mohonPihak.setJumlahPenyebut(Integer.parseInt(be.getBbsimati()));
////                                        mohonPihak.setHakmilik(be.getEtappHakmilik().getHakmilik());
////                                        mohonPihak.setJenis(kodPihakDAO.findById("PM"));
////                                        mohonPihak.setInfoAudit(pesakaService.getInfoAudit());
////                                        mohonPihak = pesakaService.saveMohonPihak(mohonPihak);
////
////                                    }
////                                }
//
////                            }
////                                mohonRuj.setHakmilik(hakmilikDAO.findById(s));
////                                mohonRuj = pesakaService.saveMohonRuj(mohonRuj);
////
////                                //simati
////                                HakmilikPihakBerkepentingan simati = pesakaService.findSimati(s, etappPerintah.getNoKpSimati().substring(0, 6));
////                                Pemohon pemohon = new Pemohon();
////                                pemohon.setCawangan(uv.getPermohonan().getCawangan());
////                                pemohon.setPermohonan(uv.getPermohonan());
////                                pemohon.setPihak(simati.getPihak());
////                                pemohon.setInfoAudit(pesakaService.getInfoAudit());
////                                pemohon.setHakmilik(hakmilikDAO.findById(s));
//////                        pemohon.setJenisPemohon("X");
////                                pemohon.setJenis(simati.getJenis());
////                                int age = simati.getNoPengenalan().length();
////                                LOG.info("length ic : " + age);
////                                if (age == 12 || age == 14) {
////                                    int umur = pesakaService.kiraUmur(simati.getNoPengenalan());
////                                    pemohon.setUmur(umur);
////                                } else {
////                                    pemohon.setUmur(null);
////                                }
////                                pemohon.setSyerPembilang(simati.getSyerPembilang());
////                                pemohon.setSyerPenyebut(simati.getSyerPenyebut());
////                                pemohon.setNama(simati.getNama());
////                                pemohon.setJenisPengenalan(simati.getJenisPengenalan());
////                                pemohon.setNoPengenalan(simati.getNoPengenalan());
////                                Alamat alamatP = new Alamat();
////                                AlamatSurat alamatPS = new AlamatSurat();
////                                try {
////                                    alamatP.setAlamat1(simati.getAlamat1() != null ? simati.getAlamat1() : "");
////                                    alamatP.setAlamat2(simati.getAlamat2() != null ? simati.getAlamat2() : "");
////                                    alamatP.setAlamat3(simati.getAlamat3() != null ? simati.getAlamat3() : "");
////                                    alamatP.setAlamat4(simati.getAlamat4() != null ? simati.getAlamat4() : "");
////                                    alamatP.setNegeri(simati.getNegeri());
////                                    alamatP.setPoskod(simati.getPoskod());
////
////                                    alamatPS.setAlamatSurat1(simati.getAlamat1() != null ? simati.getAlamat1() : "");
////                                    alamatPS.setAlamatSurat2(simati.getAlamat2() != null ? simati.getAlamat2() : "");
////                                    alamatPS.setAlamatSurat3(simati.getAlamat3() != null ? simati.getAlamat3() : "");
////                                    alamatPS.setAlamatSurat4(simati.getAlamat4() != null ? simati.getAlamat4() : "");
////                                    alamatPS.setNegeriSurat(simati.getNegeri());
////                                    alamatPS.setPoskodSurat(simati.getPoskod());
////                                } catch (Exception ex) {
////                                    LOG.error(ex);
////                                }
////
////                                pemohon.setAlamat(alamatP);
////                                pemohon.setAlamatSurat(alamatPS);
////                                pemohon = pesakaService.savePemohon(pemohon);
//
//                                Permohonan mohon = uv.getPermohonan();
//
////                                if (mohon.getCatatan() != null) {
////
//////                            HakmilikPermohonan hakmhn = hakmilikPermohonanDAO.findById(Long.valueOf(mohon.getCatatan()));           //  haqqiem modified
////                                    String query = "SELECT hp from etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan =:idmohon";
////                                    Query q = sessionProvider.get().createQuery(query);
////                                    q.setString("idmohon", mohon.getCatatan());
////                                    HakmilikPermohonan hakmhn = (HakmilikPermohonan) q.uniqueResult();
////
//////                        hakmhn.setPermohonan(permohonanDAO.findById(mohon.getCatatan()));
////                                    hakmhn.setHakmilik(hakmilikDAO.findById(s));
////                                    ia = hakmhn.getInfoAudit();
////                                    ia.setDiKemaskiniOleh(penggunaDAO.findById("portal"));
////                                    ia.setTarikhKemaskini(new java.util.Date());
////
////                                    hakmhn = pesakaService.saveHakmilikPermohonan(hakmhn);
////                                }
//                            }
//                        }
//
//                        Permohonan mohon = uv.getPermohonan();
//                        mohon.setSebab("Pesaka");
//                        ia = mohon.getInfoAudit();
//                        ia.setDiKemaskiniOleh(penggunaDAO.findById("portal"));
//                        ia.setTarikhKemaskini(new Date());
//                        mohon = pesakaService.saveMohon(mohon);
//                    } else {
//                    }
//                }
//            }
//        } else if ("TMAMF".equals(uv.kodUrusan)) {
//            EtappPerintah etappPerintah = new EtappPerintah();
//
//            String ic = null;
//            for (DokumenValue dv : uv.getSenaraiDokumenSerahan()) {
//                if (dv.getKodDokumen().equals("BF")) {
//                    if (dv.noRujukan != null) {
//                        etappPerintah = etappPerintahDAO.findById(dv.noRujukan);
//                        if (etappPerintah != null) {
//                            for (String s : uv.getHakmilikPermohonan()) {
////                                PermohonanRujukanLuar mohonRuj = new PermohonanRujukanLuar();
////                                mohonRuj.setPermohonan(uv.getPermohonan());
////                                mohonRuj.setKodRujukan(kodRujukanDAO.findById("FL"));
////                                mohonRuj.setKodPerintah(kodPerintahDAO.findById("M"));
////                                mohonRuj.setNamaSidang(etappPerintah.getTmpBicara());
////                                mohonRuj.setNoSidang(null);
////                                mohonRuj.setTarikhSidang(etappPerintah.getTarikhPerintah());
////                                mohonRuj.setNoRujukan(etappPerintah.getNoFail());
////                                mohonRuj.setNoFail(etappPerintah.getNoFail());
////                                mohonRuj.setInfoAudit(pesakaService.getInfoAudit());
////                                mohonRuj.setCawangan(uv.getPermohonan().getCawangan());
////                                EtappHakmilik etappHakmilik = pesakaService.findHakmilik(dv.noRujukan, s);
////                                List<EtappBorangF> listBorangF = etappHakmilik.getListEtappBorangF();
////                                for (int n = 0; n < listBorangF.size(); n++) {
////
////                                    EtappBorangF bf = listBorangF.get(n);
////
////                                    Pihak pihak = new Pihak();
////                                    pihak.setNama(bf.getNamaWaris());
////                                    if (bf.getNoKpWaris().length() == 12) {
////                                        ic = pesakaService.tukarIC(bf.getNoKpWaris());
////                                        LOG.info("ic = " + ic);
////                                        pihak.setNoPengenalan(ic);
////                                    } else {
////                                        pihak.setNoPengenalan(bf.getNoKpWaris());
////                                    }
////                                    pihak.setWargaNegara(bf.getKewarganaan() != null ? kodWarganegaraDAO.findById(bf.getKewarganaan()) : null);
////                                    KodJenisPengenalan kp = pesakaService.findKP(bf.getJenisPengenalan());
////                                    pihak.setJenisPengenalan(kp);
////                                    pihak.setAlamat1(bf.getAlamat1());
////                                    pihak.setAlamat2(bf.getAlamat2());
////                                    pihak.setAlamat3(bf.getAlamat3());
////                                    pihak.setAlamat4(bf.getBandar());
////                                    pihak.setPoskod(bf.getPoskod());
////                                    pihak.setNegeri(kodNegeriDAO.findById(bf.getNegeri()));
////                                    pihak.setInfoAudit(pesakaService.getInfoAudit());
////                                    pihak = pesakaService.savePihak(pihak);
////
////                                    PermohonanPihak mohonPihak = new PermohonanPihak();
////                                    mohonPihak.setPermohonan(uv.getPermohonan());
////                                    mohonPihak.setPihak(pihak);
////                                    mohonPihak.setWargaNegara(bf.getKewarganaan() != null ? kodWarganegaraDAO.findById(bf.getKewarganaan()) : null);
////                                    mohonPihak.setNama(bf.getNamaWaris());
////                                    mohonPihak.setJenisPengenalan(pihak.getJenisPengenalan());
////                                    if (bf.getNoKpWaris().length() == 12) {
////                                        ic = pesakaService.tukarIC(bf.getNoKpWaris());
////                                        LOG.info("ic = " + ic);
////                                        mohonPihak.setNoPengenalan(ic);
////                                    } else {
////                                        mohonPihak.setNoPengenalan(bf.getNoKpWaris());
////                                    }
////                                    if (bf.getUmur() != null) {
////                                        mohonPihak.setUmur(Integer.parseInt(bf.getUmur()));
////                                    } else {
////                                        int age = bf.getNoKpWaris().length();
////                                        LOG.info("length ic : " + age);
////                                        if (age == 12 || age == 14) {
////                                            int umur = pesakaService.kiraUmur(bf.getNoKpWaris());
////                                            mohonPihak.setUmur(umur);
////                                        } else {
////                                            mohonPihak.setUmur(null);
////                                        }
////                                    }
////                                    AlamatSurat alamatSurat = new AlamatSurat();
////                                    Alamat alamat = new Alamat();
////                                    mohonPihak.setAlamat(alamat);
////                                    try {
////                                        alamat.setAlamat1(bf.getAlamat1() != null ? bf.getAlamat1() : "");
////                                        alamat.setAlamat2(bf.getAlamat2() != null ? bf.getAlamat2() : "");
////                                        alamat.setAlamat3(bf.getAlamat3() != null ? bf.getAlamat3() : "");
////                                        alamat.setAlamat4(bf.getBandar() != null ? bf.getBandar() : "");
////                                        alamat.setNegeri(kodNegeriDAO.findById(bf.getNegeri()));
////                                        alamat.setPoskod(bf.getPoskod());
////
////                                        alamatSurat.setAlamatSurat1(bf.getAlamat1() != null ? bf.getAlamat1() : "");
////                                        alamatSurat.setAlamatSurat2(bf.getAlamat2() != null ? bf.getAlamat2() : "");
////                                        alamatSurat.setAlamatSurat3(bf.getAlamat3() != null ? bf.getAlamat3() : "");
////                                        alamatSurat.setAlamatSurat4(bf.getBandar() != null ? bf.getBandar() : "");
////                                        alamatSurat.setNegeriSurat(kodNegeriDAO.findById(bf.getNegeri()));
////                                        alamatSurat.setPoskodSurat(bf.getPoskod());
////                                    } catch (Exception ex) {
////                                        LOG.error(ex);
////                                    }
////                                    mohonPihak.setAlamatSurat(null);
////                                    mohonPihak.setCawangan(uv.getPermohonan().getCawangan());
////
////                                    mohonPihak.setSyerBersama('Y');
////                                    if ((n + 1) == listBorangF.size()) {
////                                        mohonPihak.setSyerPembilang((Integer.parseInt(etappHakmilik.getBasimati())));
////                                        mohonPihak.setSyerPenyebut((Integer.parseInt(etappHakmilik.getBbsimati())));
////                                    } else {
////                                    }
//////                                mohonPihak.setSyerPembilang(Integer.parseInt(etappHakmilik.getBasimati()));
//////                                mohonPihak.setSyerPenyebut(Integer.parseInt(etappHakmilik.getBbsimati()));
////                                    // mohonPihak.setJumlahPembilang(Integer.parseInt(be.getBasimati()));
////                                    // mohonPihak.setJumlahPenyebut(Integer.parseInt(be.getBbsimati()));
////                                    mohonPihak.setHakmilik(bf.getEtappHakmilik().getHakmilik());
////                                    mohonPihak.setJenis(kodPihakDAO.findById(bf.getJenisPihakKepentingan()));
////                                    mohonPihak.setInfoAudit(pesakaService.getInfoAudit());
////                                    mohonPihak = pesakaService.saveMohonPihak(mohonPihak);
////
////                                }
////                                mohonRuj.setHakmilik(hakmilikDAO.findById(s));
////                                mohonRuj = pesakaService.saveMohonRuj(mohonRuj);
////
////                                //simati
////                                HakmilikPihakBerkepentingan simati = pesakaService.findSimati(s, etappPerintah.getNoKpSimati().substring(0, 6));
////                                Pemohon pemohon = new Pemohon();
////                                pemohon.setCawangan(uv.getPermohonan().getCawangan());
////                                pemohon.setPermohonan(uv.getPermohonan());
////                                pemohon.setPihak(simati.getPihak());
////                                pemohon.setInfoAudit(pesakaService.getInfoAudit());
////                                pemohon.setHakmilik(hakmilikDAO.findById(s));
////                                Alamat alamatP = new Alamat();
////                                AlamatSurat alamatPS = new AlamatSurat();
////                                try {
////                                    alamatP.setAlamat1(simati.getAlamat1() != null ? simati.getAlamat1() : "");
////                                    alamatP.setAlamat2(simati.getAlamat2() != null ? simati.getAlamat2() : "");
////                                    alamatP.setAlamat3(simati.getAlamat3() != null ? simati.getAlamat3() : "");
////                                    alamatP.setAlamat4(simati.getAlamat4() != null ? simati.getAlamat4() : "");
////                                    alamatP.setNegeri(simati.getNegeri());
////                                    alamatP.setPoskod(simati.getPoskod());
////
////                                    alamatPS.setAlamatSurat1(simati.getAlamat1() != null ? simati.getAlamat1() : "");
////                                    alamatPS.setAlamatSurat2(simati.getAlamat2() != null ? simati.getAlamat2() : "");
////                                    alamatPS.setAlamatSurat3(simati.getAlamat3() != null ? simati.getAlamat3() : "");
////                                    alamatPS.setAlamatSurat4(simati.getAlamat4() != null ? simati.getAlamat4() : "");
////                                    alamatPS.setNegeriSurat(simati.getNegeri());
////                                    alamatPS.setPoskodSurat(simati.getPoskod());
////                                } catch (Exception ex) {
////                                    LOG.error(ex);
////                                }
////
////                                pemohon.setAlamat(alamatP);
////                                pemohon.setAlamatSurat(alamatPS);
//////                        pemohon.setJenisPemohon("X");
////                                int age = simati.getNoPengenalan().length();
////                                LOG.info("length ic : " + age);
////                                if (age == 12 || age == 14) {
////                                    int umur = pesakaService.kiraUmur(simati.getNoPengenalan());
////                                    pemohon.setUmur(umur);
////                                } else {
////                                    pemohon.setUmur(null);
////                                }
////                                pemohon.setJenis(simati.getJenis());
////                                pemohon.setSyerPembilang(simati.getSyerPembilang());
////                                pemohon.setSyerPenyebut(simati.getSyerPenyebut());
////                                pemohon.setNama(simati.getNama());
////                                pemohon.setJenisPengenalan(simati.getJenisPengenalan());
////                                pemohon.setNoPengenalan(simati.getNoPengenalan());
////                                pemohon.setAlamatSurat(simati.getAlamatSurat());
////                                pemohon = pesakaService.savePemohon(pemohon);
//
//                                Permohonan mohon = uv.getPermohonan();
//
////                                if (mohon.getCatatan() != null) {
////
//////                            HakmilikPermohonan hakmhn = hakmilikPermohonanDAO.findById(Long.valueOf(mohon.getCatatan()));
////                                    String query = "SELECT hp from etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan =:idmohon";
////                                    Query q = sessionProvider.get().createQuery(query);
////                                    q.setString("idmohon", mohon.getCatatan());
////                                    HakmilikPermohonan hakmhn = (HakmilikPermohonan) q.uniqueResult();
//////                        hakmhn.setPermohonan(permohonanDAO.findById(mohon.getCatatan()));
////                                    hakmhn.setHakmilik(hakmilikDAO.findById(s));
////                                    ia = hakmhn.getInfoAudit();
////                                    ia.setDiKemaskiniOleh(penggunaDAO.findById("portal"));
////                                    ia.setTarikhKemaskini(new java.util.Date());
////
////                                    hakmhn = pesakaService.saveHakmilikPermohonan(hakmhn);
////                                }
//                            }
//                        }
//
//                        Permohonan mohon = uv.getPermohonan();
//                        mohon.setSebab("Pesaka");
//                        ia = mohon.getInfoAudit();
//                        ia.setDiKemaskiniOleh(penggunaDAO.findById("portal"));
//                        ia.setTarikhKemaskini(new Date());
//                        mohon = pesakaService.saveMohon(mohon);
//                    } else {
//                    }
//                }
//
//            }
//
//        }
    }
}
