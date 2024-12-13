/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PortalTransaksiDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikSebelum;
import etanah.model.InfoAudit;
import etanah.model.KodCaraBayaran;
import etanah.model.KodUOM;
import etanah.model.PortalTransaksi;
import etanah.model.SejarahCaraBayaran;
import etanah.model.SejarahDokumenKewangan;
import etanah.model.SejarahDokumenKewanganBayaran;
import etanah.model.Transaksi;
import etanah.view.etanahContextListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 */
public class CukaiOnlineServices {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    PortalTransaksiDAO portalTransaksiDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PenggunaDAO pguna;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    TerimaBayaranServices bayar;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;

    public String updateTransaction(String idHakmilik, BigDecimal jumlahBayaran, String idPengguna, String jenisTrans) {
        PortalTransaksi portalTransaksi = new PortalTransaksi();
        portalTransaksi.setAkaun(hakmilikDAO.findById(idHakmilik).getAkaunCukai());
        portalTransaksi.setAmaun(jumlahBayaran);
        portalTransaksi.setHakmilik(hakmilikDAO.findById(idHakmilik));
        portalTransaksi.setIdPengguna(idPengguna);
        portalTransaksi.setJenisTrans(jenisTrans);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna.findById("portal"));
        ia.setTarikhMasuk(new Date());
        portalTransaksi.setInfoAudit(ia);
        portalTransaksi = save(portalTransaksi);
        return portalTransaksi.getIdTransaksi().toString();
    }

    public String updateCukaiAccount(String idHakmilik, BigDecimal bd,
            String PAYMENT_TRANS_ID, String PAYMENT_DATETIME,
            String PAYMENT_MODE, String idPengguna, String TRANS_ID, String FPX) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date convertedDate = df.parse(PAYMENT_DATETIME);
//        Date convertedDate = new Date();
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna.findById("portal"));
        info.setTarikhMasuk(new Date());
        Hakmilik hakmilik;
        Akaun akaun;
        hakmilik = findHakmilikAktif(idHakmilik);
        //hakmilikDAO.findById(idHakmilik);
        if (hakmilik != null) {
            akaun = findAkaunAktifbyIdHakmilik(hakmilik.getIdHakmilik());
        } else {
            akaun = findAkaunAktif(idHakmilik);
            hakmilik = akaun.getHakmilik();
        }

        PortalTransaksi portalTransaksi = new PortalTransaksi();
//        portalTransaksi = portalTransaksiDAO.findById(Long.valueOf(TRANS_ID));
        String noresit = "";
        try {
            portalTransaksi = findByNoresit(TRANS_ID, "CU");
//             portalTransaksi = findByNoFpx(FPX);
            if (portalTransaksi == null) {

                if (portalTransaksi == null) {
                    portalTransaksi = new PortalTransaksi();
                    portalTransaksi.setAkaun(akaun);
                    portalTransaksi.setHakmilik(hakmilik);
                    portalTransaksi.setIdPengguna(idPengguna);
                    portalTransaksi.setJenisTrans("CU");
                }

                if (StringUtils.isEmpty(portalTransaksi.getNoFpx())) {
                    BayarValue ba = bayar.terimaBayaran(akaun.getNoAkaun(), akaun.getCawangan().getKod(), bd, PAYMENT_TRANS_ID, TRANS_ID, "cukai", convertedDate);
                    noresit = ba.getIdKewdok();
                    portalTransaksi.setAmaun(bd);
                    portalTransaksi.setIdTransAsal(PAYMENT_TRANS_ID);
//                    portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
                    portalTransaksi.setNoResit(TRANS_ID);
                    portalTransaksi.setNoFpx(FPX);
                    portalTransaksi.setTrhResit(convertedDate);
                    portalTransaksi.setIdKewDok(noresit);
                    portalTransaksi.setInfoAudit(info);
//        portalTransaksi.setIdTransAsal(idPengguna);
                    save(portalTransaksi);
                }
            } else {
                noresit = portalTransaksi.getIdKewDok();
            }
        } catch (org.hibernate.NonUniqueResultException d) {
            noresit = "";
        }

        return noresit;

    }

    /*
     AGPYT
     kew_dokumen kaunter 99
     cara_bayar EP
     kew_dokumen_bayar
     kew_trans   String kodStatus = "T"; (kodCaw, kodTrans, amaunTrans,
     idMohon, idKewDok, kodStatus, accountNo,
     akaunKutip, utkThn, kntt);
    
     lapor_pnyata_pmungut   generateLaporPnyataPmungut(idKewDok, idKewTrans, idKewDokBayar,
     amaunTrans);
    
     update kew_akaun
     */
    @Transactional
    public PortalTransaksi save(PortalTransaksi p) {
        return portalTransaksiDAO.saveOrUpdate(p);
    }

    List<AccountInfo> checkAccount(String accountNo, String idHakmilik) {
        String statusAkaun = null;
        Date tarikhMasuk = null;
        boolean flagBatal = false;
        List<HakmlikInfo> listHakmilikInfo = null;
        List<AccountInfo> list = new ArrayList<AccountInfo>();
        String query = "SELECT a FROM etanah.model.Akaun a"
                + " where a.noAkaun = :accountNo "
                + "or a.hakmilik.idHakmilik = :idHakmilik ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("accountNo", accountNo);
        q.setString("idHakmilik", idHakmilik);

        for (int i = 0; i < q.list().size(); i++) {
            listHakmilikInfo = new ArrayList<HakmlikInfo>();
            Akaun a = (Akaun) q.list().get(i);
            idHakmilik = a.getHakmilik().getIdHakmilik();
            accountNo = a.getNoAkaun();
            statusAkaun = a.getStatus().getKod();
            if (statusAkaun.equals("A")) {
                break;
            }
        }
        if (statusAkaun.equals("B") || statusAkaun.equals("F")) {
            flagBatal = true;
        }
        if (!flagBatal) {
            HakmlikInfo aci = new HakmlikInfo();
            aci.setNoAkaun(accountNo);
            aci.setIdHakmilik(idHakmilik);
            listHakmilikInfo.add(aci);
        }
        if (flagBatal) {
            listHakmilikInfo = findHakmilikValid(accountNo, idHakmilik);
            if (listHakmilikInfo.isEmpty()) {
                AccountInfo aig = new AccountInfo();
                aig.setIdHakmilik(idHakmilik);
                aig.setNoAkaun(accountNo);
                aig.setAkaunBatal(true);
                list.add(aig);
            }
        }

        for (HakmlikInfo ahi : listHakmilikInfo) {

            AccountInfo aig = new AccountInfo();
            String query2 = "SELECT a FROM etanah.model.Akaun a"
                    + " where (a.noAkaun = :accountNo "
                    + "or a.hakmilik.idHakmilik = :idHakmilik) and a.status.kod = 'A'";
            Session session1 = injector.getProvider(Session.class).get();
            Query q2 = session1.createQuery(query2);
            q2.setString("accountNo", ahi.getNoAkaun());
            q2.setString("idHakmilik", ahi.getIdHakmilik());
            Akaun aa = (Akaun) q2.uniqueResult();
            aig.setNoAkaun(aa.getNoAkaun());
            aig.setIdHakmilik(aa.getHakmilik().getIdHakmilik());
            aig.setNamaPembayar(aa.getPemegang().getNama());
            aig.setIcPemilik(aa.getPemegang().getNoPengenalan());
            aig.setNamaPemilik(aa.getHakmilik().getSenaraiPihakBerkepentingan() != null ? aa.getHakmilik().getSenaraiPihakBerkepentingan().get(0).getPihak().getNama() : "");

            String alamat = trimAlamat(aa.getPemegang().getSuratAlamat1(), aa.getPemegang().getSuratAlamat2(),
                    aa.getPemegang().getSuratAlamat3(), aa.getPemegang().getSuratAlamat4(), aa.getPemegang().getSuratPoskod());

            aig.setAlamat(alamat);

            aig.setJenisHakmilik(aa.getHakmilik().getKodHakmilik().getNama());
            aig.setLokaliti(aa.getHakmilik().getBandarPekanMukim().getNama());

            BigDecimal bakiAkaun = aa.getBaki();
            String searchCriteria, status;

            if (bakiAkaun.equals(BigDecimal.ZERO) || isNegative(bakiAkaun)) { // already paid for that accountNo
                searchCriteria = "no_akaun_kr";
                status = "TELAH BAYAR";
            } else { // have to pay cukai tanah
                searchCriteria = "no_akaun_dt";
                status = "BELUM BAYAR";

            }
            List<Transaksi> trans = new ArrayList<Transaksi>();
            if (status.equals("TELAH BAYAR")) {
                trans = new ArrayList<Transaksi>();
                trans = aa.getSenaraiTransaksiKredit();
            } else {
                trans = new ArrayList<Transaksi>();
                trans = aa.getSenaraiTransaksiDebit();
            }

            BigDecimal cukaiSemasaTanah = BigDecimal.ZERO.setScale(2);;
            BigDecimal tunggakanCukaiTanah = BigDecimal.ZERO.setScale(2);;
            BigDecimal dendaLewatSemasa = BigDecimal.ZERO.setScale(2);;
            BigDecimal remisyen = BigDecimal.ZERO.setScale(2);;
            BigDecimal notis6a = BigDecimal.ZERO.setScale(2);;
            BigDecimal jumlah = BigDecimal.ZERO.setScale(2);;
            BigDecimal jumlahCukaiSemasa = BigDecimal.ZERO.setScale(2);;
            BigDecimal jumlahTunggakan = BigDecimal.ZERO.setScale(2);;
            BigDecimal cukaiSemasaParit = BigDecimal.ZERO.setScale(2);;
            BigDecimal tunggakanCukaiParit = BigDecimal.ZERO.setScale(2);;
            BigDecimal tunggakanDendaLewat = BigDecimal.ZERO.setScale(2);;
            BigDecimal kreditDebit = BigDecimal.ZERO.setScale(2);;

            String idKewDok = "";
            String utkThn = "";
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
            int currYear = Integer.parseInt(sdf1.format(new Date()));

            for (Transaksi t : aa.getSenaraiTransaksiDebit()) {
                String kodTrans = t.getKodTransaksi().getKod();
                System.out.println("KodTrans" + kodTrans);
                // int recThn = !StringUtils.isNullOrEmpty(rs.getString(5)) ? Integer.parseInt(rs.getString(5)) : 0;

                if (kodTrans.equals("61401")) {
                    cukaiSemasaTanah = cukaiSemasaTanah.add(t.getAmaun());//cukaisemasaTanah
                } else if (kodTrans.equals("61402")) {
                    tunggakanCukaiTanah = tunggakanCukaiTanah.add(t.getAmaun());//tunggakan dari tahun cukai tanah
                } else if (kodTrans.equals("76152") && t.getUntukTahun() == currYear) {
                    dendaLewatSemasa = dendaLewatSemasa.add(t.getAmaun());//dendaSemasa
                } else if (kodTrans.equals("76152") && t.getUntukTahun() != currYear) {
                    tunggakanDendaLewat = tunggakanDendaLewat.add(t.getAmaun());//tunggakan denda lewat
                } else if (kodTrans.equals("99000") || kodTrans.equals("99001")
                        || kodTrans.equals("99002") || kodTrans.equals("99003")) {
                    remisyen = remisyen.add(t.getAmaun());//remisyen
                    // aig.setKodRemisyen(kodTrans);
                } else if (kodTrans.equals("72457")) {
                    notis6a = notis6a.add(t.getAmaun());//notis 6A
                }
            }
            BigDecimal cukaiSemasaTanahKredit = BigDecimal.ZERO.setScale(2);;
            BigDecimal tunggakanCukaiTanahKredit = BigDecimal.ZERO.setScale(2);;
            BigDecimal dendaLewatSemasaKredit = BigDecimal.ZERO.setScale(2);;
            BigDecimal tunggakanDendaLewatKredit = BigDecimal.ZERO.setScale(2);;
            BigDecimal othersKredit = BigDecimal.ZERO.setScale(2);;

            for (Transaksi t : aa.getSenaraiTransaksiKredit()) {
                String kodTrans = t.getKodTransaksi().getKod();
                if (kodTrans.equals("61401")) {
                    cukaiSemasaTanahKredit = cukaiSemasaTanahKredit.add(t.getAmaun());//cukaisemasaTanah
                } else if (kodTrans.equals("61402")) {
                    tunggakanCukaiTanahKredit = tunggakanCukaiTanahKredit.add(t.getAmaun());//tunggakan dari tahun cukai tanah
                } else if (kodTrans.equals("76152")) {
                    tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.add(t.getAmaun());//tunggakan denda lewat
                } else if (kodTrans.equals("72457")) {
                    notis6a = notis6a.add(t.getAmaun());//notis 6A
                } else if (kodTrans.equals("61601")) {
                    cukaiSemasaParit = cukaiSemasaParit.add(t.getAmaun());//cukai Semasa Parit
                } else if (kodTrans.equals("61602")) {
                    tunggakanCukaiParit = tunggakanCukaiParit.add(t.getAmaun());//tunggakan Cukai Parit
                } else if (kodTrans.equals("61611")) {
                } else {
                    othersKredit = othersKredit.add(t.getAmaun());
                }
            }

            if (bakiAkaun.equals(new BigDecimal(BigInteger.ZERO)) || isNegative(bakiAkaun)) { // already paid for that accountNo
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                try {
                    aig.setTarikhBayaran(sdf.format(tarikhMasuk));
                } catch (Exception e) {
                    aig.setTarikhBayaran("");
                }
                aig.setNoResit(idKewDok);
            }
            jumlahCukaiSemasa = cukaiSemasaTanah.add(cukaiSemasaParit);
            jumlahTunggakan = tunggakanCukaiTanah.add(tunggakanCukaiParit);
            BigDecimal grandCukaiSemasaTanah = BigDecimal.ZERO.setScale(2);;
            BigDecimal grandTunggakanCukaiTanah = BigDecimal.ZERO.setScale(2);;
            BigDecimal grandDendaLewatSemasa = BigDecimal.ZERO.setScale(2);;
            BigDecimal grandTunggakanDendaLewat = BigDecimal.ZERO.setScale(2);;
            BigDecimal grandBaki = BigDecimal.ZERO.setScale(2);;

            grandCukaiSemasaTanah = jumlahCukaiSemasa.subtract(cukaiSemasaTanahKredit);
            grandTunggakanCukaiTanah = jumlahTunggakan.subtract(tunggakanCukaiTanahKredit);
            if (tunggakanDendaLewatKredit.compareTo(BigDecimal.ZERO) > 0) {
                if (tunggakanDendaLewat.compareTo(tunggakanDendaLewatKredit) > 0) {
                    tunggakanDendaLewat = tunggakanDendaLewat.subtract(tunggakanDendaLewatKredit);
                    grandTunggakanDendaLewat = tunggakanDendaLewat;
                } else {
                    tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.subtract(tunggakanDendaLewat);
                    tunggakanDendaLewat = BigDecimal.ZERO;
                    grandTunggakanDendaLewat = tunggakanDendaLewat;
                    if (tunggakanDendaLewatKredit.compareTo(dendaLewatSemasa) > 0) {
                        tunggakanDendaLewatKredit = tunggakanDendaLewatKredit.subtract(dendaLewatSemasa);
                        grandDendaLewatSemasa = BigDecimal.ZERO;
                    } else {
                        grandDendaLewatSemasa = dendaLewatSemasa.subtract(tunggakanDendaLewatKredit);

                    }

                }

            } else {
                grandDendaLewatSemasa = dendaLewatSemasa;
                grandTunggakanDendaLewat = tunggakanDendaLewat;
            }
            if (othersKredit.compareTo(BigDecimal.ZERO) > 0) {
                grandTunggakanCukaiTanah = grandTunggakanCukaiTanah.subtract(othersKredit);
            }
            grandBaki = grandCukaiSemasaTanah.add(grandTunggakanCukaiTanah).add(grandDendaLewatSemasa).add(grandTunggakanDendaLewat);
            System.out.println("GRANDBAKI IS A" + grandBaki);
//            aig.setCukaiSemasaTanah(cukaiSemasaTanah);//cukaisemasaTanah
//            aig.setTunggakanCukaiTanah(tunggakanCukaiTanah);//tunggakan dari tahun cukai tanah
//            aig.setDendaLewatSemasa(dendaLewatSemasa);//dendaSemasa
//            aig.setTunggakanDendaLewat(tunggakanDendaLewat); //tunggakan denda lewat
            aig.setCukaiSemasaTanah(grandCukaiSemasaTanah);//cukaisemasaTanah
            aig.setTunggakanCukaiTanah(grandTunggakanCukaiTanah);//tunggakan dari tahun cukai tanah
            aig.setDendaLewatSemasa(grandDendaLewatSemasa);//dendaSemasa
            aig.setTunggakanDendaLewat(grandTunggakanDendaLewat); //tunggakan denda lewat
            aig.setRemisyen(remisyen); //remisyen
            aig.setNotis6a(notis6a);//notis 6A
            aig.setCukaiSemasaParit(cukaiSemasaParit); //cukai Semasa Parit
            aig.setTunggakanCukaiParit(tunggakanCukaiParit);//tunggakan Cukai Parit
            aig.setKodCaw(aa.getHakmilik().getCawangan().getKod());
            // aig.setKodCawValue(aa.getHakmilik().getCawangan().getName());
            aig.setSyaratNyata(aa.getHakmilik().getSyaratNyata() != null ? aa.getHakmilik().getSyaratNyata().getSyarat() : "");
            if (aig.getKodCaw().equals("00")) {
                aig.setJenisPegangan("Hakmilik Pejabat Tanah dan Galian ");
            } else {
                aig.setJenisPegangan("Hakmilik Pejabat Tanah dan Daerah");
            }

//         aig.setCukaiSemasa(cukaiSemasa);
//         aig.setTunggakanCukai(tunggakanCukai);
//         aig.setDendaLewat(dendaLewat);
//         aig.setRemisyen(remisyen);
//         aig.setNotis6a(notis6a);
            aig.setJumlahCukaiSemasa(grandCukaiSemasaTanah);
            aig.setJumlahTunggakan(grandTunggakanCukaiTanah);
            aig.setJumlahBayaran(bakiAkaun);
            aig.setStatusBayaran(status);
            aig.setKreditDebit(kreditDebit);

            aig.setBakiAkaun(bakiAkaun);

            // additional fields for bil cukai
            aig.setDaerah(aa.getHakmilik().getDaerah().getNama());
            aig.setNoHakmilik(aa.getHakmilik().getNoHakmilik());
            aig.setBandarpekanmukim(aa.getHakmilik().getBandarPekanMukim().getNama());
            aig.setTahun(utkThn);
            aig.setNoLot(aa.getHakmilik().getLot().getNama() + " " + aa.getHakmilik().getNoLot());
            KodUOM unitLuas = aa.getHakmilik().getKodUnitLuas();
            String luas = "";
            if (unitLuas != null) {
                luas = unitLuas.getNama();
            }

            aig.setKeluasan(aa.getHakmilik().getLuas() + " " + luas);
            aig.setKategori(aa.getHakmilik().getKategoriTanah() != null ? aa.getHakmilik().getKategoriTanah().getNama() : "");
            aig.setKegunaan(aa.getHakmilik().getKegunaanTanah() != null ? aa.getHakmilik().getKegunaanTanah().getNama() : "");
            aig.setTunggakanTahun(utkThn); // will decide later
            aig.setLebihan(BigDecimal.ZERO.setScale(2)); // will decide later
            List<SejarahCukai> listSejarahCukai = new ArrayList<SejarahCukai>();
            aig.setKodCukaiSemasa("61401");
            aig.setKodTunggakanCukai("61402");
            aig.setKodDendaLewat("76152");
            aig.setKodTunggakanDendaLewat("76152");
            aig.setKodCukaiSemasaParit("61601");
            aig.setKodTunggakanCukaiParit("61602");
//                    aig.setKodKreditDebit("61611");
            aig.setKodNotis6a("72457");
            aig.setKodCarianpersendirian("72488");

            for (SejarahDokumenKewangan sdk : listSejarahDokumenKewangan(aa.getNoAkaun())) {
                for (SejarahDokumenKewanganBayaran sdkb : sdk.getSenaraiBayaran()) {
                    SejarahCukai sj = new SejarahCukai();
                    sj.setAmaun(String.valueOf(sdkb.getAmaun()));
                    SejarahCaraBayaran sejcaraBayar = sdkb.getCaraBayaran() != null ? sdkb.getCaraBayaran() : null;
                    KodCaraBayaran kcb = sejcaraBayar != null ? sejcaraBayar.getKodCaraBayaran() : null;
                    sj.setKaedahBayaran(kcb != null ? kcb.getNama() : "");
                    sj.setNoResit(sdkb.getDokumenKewangan() != null ? sdkb.getDokumenKewangan().getIdDokumenKewangan() : "");
//                        sj.setPusatKutipan(sdkb.getDokumenKewangan().getKodAgensiKutipanCaw() != null ? sdkb.getDokumenKewangan().getKodAgensiKutipanCaw().getNama() : "Kaunter " + sdkb.getDokumenKewangan().getInfoAudit().getDimasukOleh().getKodCawangan().getName());
                    sj.setTahun(sdkb.getDokumenKewangan().getTarikhTransaksi() != null ? String.valueOf(sdkb.getDokumenKewangan().getTarikhTransaksi().getYear()) : "");
                    sj.setTarikh(sdkb.getInfoAudit().getTarikhMasuk());
                    listSejarahCukai.add(sj);

                }
            }
            aig.setListSejarahCukai(listSejarahCukai);
            list.add(aig);
        }
        return list;
    }

    private List<SejarahDokumenKewangan> listSejarahDokumenKewangan(String accountNo) {
        String query = "SELECT a FROM etanah.model.SejarahDokumenKewangan a"
                + " where a.akaun.noAkaun = :accountNo ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("accountNo", accountNo);
        return q.list();
    }

    private String trimAlamat(String alamat1, String alamat2, String alamat3,
            String alamat4, String poskod) {
        String completeAddress = "";

        String fragments[] = {alamat1, alamat2, alamat3, alamat4, poskod};

        for (String frag : fragments) {
            if (frag != null && !(frag.equals(""))) {
                // remove comma (,) from the fragments if have
                if (frag.charAt(frag.length() - 1) == ',') {
                    frag = frag.substring(0, frag.length() - 1);
                }

                // trim it to make it nice
                frag.trim();

                // combines all
                if (completeAddress.equals("")) {
                    completeAddress += frag;
                } else {
                    completeAddress += ", " + frag;
                }
            }
        }

        return completeAddress;
    }

    private List<HakmlikInfo> findHakmilikValid(String accountNo, String idHakmilik) {
        List<HakmlikInfo> listHakmilik = new ArrayList<HakmlikInfo>();

        String query = "SELECT hs FROM etanah.model.HakmilikSebelum hs, etanah.model.Akaun ak"
                + " where hs.hakmilikSebelum = :idHakmilik "
                + " and hs.hakmilik.kodStatusHakmilik.kod = 'D' "
                + " and ak.hakmilik.idHakmilik = hs.hakmilik.idHakmilik "
                + " and ak.status.kod = 'A'";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        for (int i = 0; i < q.list().size(); i++) {
            HakmilikSebelum hakmilikSBLM = (HakmilikSebelum) q.list().get(i);
            HakmlikInfo info = new HakmlikInfo();
            info.setIdHakmilik(hakmilikSBLM.getHakmilik().getIdHakmilik());
            info.setNoAkaun(hakmilikSBLM.getHakmilik().getAkaunCukai().getNoAkaun());
            listHakmilik.add(info);

        }

        if (listHakmilik.isEmpty()) {
            String query2 = "SELECT hs FROM etanah.model.HakmilikAsal hs, etanah.model.Akaun ak"
                    + " where hs.hakmilikAsal = :idHakmilik "
                    + " and hs.hakmilik.kodStatusHakmilik.kod = 'D' "
                    + " and ak.hakmilik.idHakmilik = hs.hakmilik.idHakmilik "
                    + " and ak.status.kod = 'A'";
            Session session2 = injector.getProvider(Session.class).get();
            Query q2 = session2.createQuery(query2);
            q2.setString("idHakmilik", idHakmilik);
            for (int i = 0; i < q2.list().size(); i++) {
                HakmilikAsal hakmilikASAL = (HakmilikAsal) q2.list().get(i);
                HakmlikInfo info = new HakmlikInfo();
                info.setIdHakmilik(hakmilikASAL.getHakmilik().getIdHakmilik());
                info.setNoAkaun(hakmilikASAL.getHakmilik().getAkaunCukai().getNoAkaun());
                listHakmilik.add(info);

            }
        }
        return listHakmilik;
    }

    private PortalTransaksi findByNoresit(String PAYMENT_TRANS_ID, String jenisTrans) {
        String query = "SELECT a FROM etanah.model.PortalTransaksi a"
                + " where a.noResit = :noResit and a.jenisTrans = :jenisTrans";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("jenisTrans", jenisTrans);
        q.setString("noResit", PAYMENT_TRANS_ID);
        return (PortalTransaksi) q.uniqueResult();
    }

    private List<PortalTransaksi> findByNoresitPukal(String PAYMENT_TRANS_ID, String jenisTrans) {
        String query = "SELECT a FROM etanah.model.PortalTransaksi a"
                + " where a.noResit = :noResit and a.jenisTrans = :jenisTrans";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("jenisTrans", jenisTrans);
        q.setString("noResit", PAYMENT_TRANS_ID);
        return q.list();
    }

    private PortalTransaksi findByNoFpx(String noFpx) {
        String query = "SELECT a FROM etanah.model.PortalTransaksi a"
                + " where a.noFpx = :noFpx ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
//        q.setString("accountNo", accountNo);
        q.setString("noFpx", noFpx);
        return (PortalTransaksi) q.uniqueResult();
    }

    public static boolean isNegative(BigDecimal b) {
        boolean tru = false;
        if (b.signum() == -1) {
            tru = true;
        } else {
            tru = false;
        }
        return tru;
    }

    public String updateCukaiAccountPukal(List<AkaunForm> listAkaunForm, BigDecimal bd,
            String PAYMENT_TRANS_ID, String PAYMENT_DATETIME,
            String PAYMENT_MODE, String idPengguna, String TRANS_ID, String FPX) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date convertedDate = df.parse(PAYMENT_DATETIME);
//        Date convertedDate = new Date();
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pguna.findById("portal"));
        info.setTarikhMasuk(new Date());

        List<PortalTransaksi> l = new ArrayList<PortalTransaksi>();
        PortalTransaksi portalTransaksi = new PortalTransaksi();
        String noresit = "";
        try {
            l = findByNoresitPukal(PAYMENT_TRANS_ID, "CU");
//             portalTransaksi = findByNoFpx(FPX);
            if (l.isEmpty()) {
                for (AkaunForm form : listAkaunForm) {
                    Hakmilik hakmilik;
                    Akaun akaun;
                    hakmilik = hakmilikDAO.findById(form.getNoAccount());
                    if (hakmilik != null) {
                        akaun = hakmilik.getAkaunCukai();
                    } else {
                        akaun = akaunDAO.findById(form.getNoAccount());
                        hakmilik = akaun.getHakmilik();
                    }

                    portalTransaksi = new PortalTransaksi();
                    portalTransaksi.setAkaun(akaun);
                    portalTransaksi.setHakmilik(hakmilik);
                    portalTransaksi.setIdPengguna(idPengguna);
                    portalTransaksi.setJenisTrans("CU");

                    if (StringUtils.isEmpty(portalTransaksi.getNoFpx())) {
                        BayarValue ba = bayar.terimaBayaran(akaun.getNoAkaun(), akaun.getCawangan().getKod(), form.getAmaunt(), PAYMENT_TRANS_ID, FPX, "cukai", convertedDate);
                        if (StringUtils.isNotBlank(noresit)) {
                            noresit = noresit + ", " + ba.getIdKewdok();
                        } else {
                            noresit = ba.getIdKewdok();
                        }
                        portalTransaksi.setAmaun(form.getAmaunt());
                        portalTransaksi.setNoFpx(PAYMENT_TRANS_ID);
                        portalTransaksi.setNoResit(PAYMENT_TRANS_ID);
                        portalTransaksi.setNoFpx(FPX);
                        portalTransaksi.setTrhResit(convertedDate);
                        portalTransaksi.setIdKewDok(noresit);
                        portalTransaksi.setInfoAudit(info);
//        portalTransaksi.setIdTransAsal(idPengguna);
                        save(portalTransaksi);
                    }
                }
            } else {
                noresit = portalTransaksi.getIdKewDok();
            }
        } catch (org.hibernate.NonUniqueResultException d) {
            noresit = "";
        }

        return noresit;

    }

    private Hakmilik findHakmilikAktif(String idHakmilik) {
        Hakmilik ha = new Hakmilik();
        String query = "SELECT a FROM etanah.model.Hakmilik a"
                + " where a.idHakmilik = :idHakmilik and a.kodStatusHakmilik.kod = 'D' ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        ha = (Hakmilik) q.uniqueResult();
        return ha;

    }

    private Akaun findAkaunAktif(String noAkaun) {
        Akaun akaun = new Akaun();
        String query = "SELECT a FROM etanah.model.Akaun a"
                + " where a.akaun.noAkaun = :accountNo and a.status.kod= 'A' ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("accountNo", noAkaun);
        akaun = (Akaun) q.uniqueResult();
        return akaun;
    }

    private Akaun findAkaunAktifbyIdHakmilik(String idHakmilik) {
        Akaun akaun = new Akaun();
        String query = "SELECT a FROM etanah.model.Akaun a"
                + " where a.hakmilik.idHakmilik = :idHakmilik and a.status.kod= 'A' ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        akaun = (Akaun) q.uniqueResult();
        return akaun;
    }

}
