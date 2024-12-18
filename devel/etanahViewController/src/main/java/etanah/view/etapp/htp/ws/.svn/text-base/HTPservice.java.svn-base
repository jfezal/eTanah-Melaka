/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.htp.ws;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import etanah.dao.AgensiHakmilikDAO;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewangan;
import etanah.model.Transaksi;
import etanah.model.etapp.AgensiHakmilik;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.service.AkaunService;
import etanah.model.HakmilikSebelum;
import etanah.view.etapp.PengambilanEtanahService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import etanah.model.Pengguna;
import etanah.view.etanahContextListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 */
public class HTPservice {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    PengambilanEtanahService ambilService;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    AkaunService akaunService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AgensiHakmilikDAO agensiHakmilikDAO;
    @Inject
    Provider<Session> sessionProvider;
    PenggunaDAO penggunaDAO = injector.getInstance(PenggunaDAO.class);
    BigDecimal cukaiSemasaTanah = BigDecimal.ZERO;
    BigDecimal tunggakanCukaiTanah = BigDecimal.ZERO;
    BigDecimal dendaLewatSemasa = BigDecimal.ZERO;
    BigDecimal remisyen = BigDecimal.ZERO;
    BigDecimal notis6a = BigDecimal.ZERO;
    BigDecimal jumlah = BigDecimal.ZERO;
    BigDecimal jumlahCukaiSemasa = BigDecimal.ZERO;
    BigDecimal jumlahTunggakan = BigDecimal.ZERO;
    BigDecimal cukaiSemasaParit = BigDecimal.ZERO;
    BigDecimal tunggakanCukaiParit = BigDecimal.ZERO;
    BigDecimal tunggakanDendaLewat = BigDecimal.ZERO;
    BigDecimal kreditDebit = BigDecimal.ZERO;

    public MaklumatCukai setMaklumatCukai(MaklumatCukai maklumatCukai) {
        cukaiSemasaTanah = BigDecimal.ZERO;
        tunggakanCukaiTanah = BigDecimal.ZERO;
        dendaLewatSemasa = BigDecimal.ZERO;
        remisyen = BigDecimal.ZERO;
        notis6a = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        jumlahCukaiSemasa = BigDecimal.ZERO;
        jumlahTunggakan = BigDecimal.ZERO;
        cukaiSemasaParit = BigDecimal.ZERO;
        tunggakanCukaiParit = BigDecimal.ZERO;
        tunggakanDendaLewat = BigDecimal.ZERO;
        kreditDebit = BigDecimal.ZERO;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        etanah.model.Hakmilik hakmilik = hakmilikDAO.findById(maklumatCukai.getIdhakmilik());
        if (hakmilik == null) {
            throw new RuntimeException("hakmilik tidak dikenali");
        }
        Akaun akaun = hakmilik.getAkaunCukai();
        if (akaun == null) {
            throw new RuntimeException("Akaun tidak dikenali");
        }
        int currYear = Integer.parseInt(sdf1.format(new Date()));
        Pengguna pengguna = penggunaDAO.findById("admin");
        hakmilik.setInfoAudit(setIA(pengguna));

        for (Transaksi trans : akaun.getSenaraiTransaksiDebit()) {
            String kodTrans = trans.getKodTransaksi().getKod();
            int recThn = trans.getTahunKewangan() != 0 ? trans.getTahunKewangan() : 0;

            if (kodTrans.equals("61401")) {
                cukaiSemasaTanah = cukaiSemasaTanah.add(trans.getAmaun());//cukaisemasaTanah
                jumlah = jumlah.add(cukaiSemasaTanah);
            } else if (kodTrans.equals("61402")) {
                tunggakanCukaiTanah = tunggakanCukaiTanah.add(trans.getAmaun());//tunggakan dari tahun cukai tanah
                jumlah = jumlah.add(tunggakanCukaiTanah);
            } else if (kodTrans.equals("76152") && String.valueOf(recThn).equals(sdf1.format(new Date()))) {
                dendaLewatSemasa = dendaLewatSemasa.add(trans.getAmaun());//dendaSemasa
                jumlah = jumlah.add(dendaLewatSemasa);
            } else if (kodTrans.equals("76152") && !String.valueOf(recThn).equals(sdf1.format(new Date()))) {
                tunggakanDendaLewat = tunggakanDendaLewat.add(trans.getAmaun());//tunggakan denda lewat
                jumlah = jumlah.add(tunggakanDendaLewat);
//            } else if (kodTrans.equals("99000") || kodTrans.equals("99001")
//                    || kodTrans.equals("99002") || kodTrans.equals("99003") || kodTrans.equals("99030") ) {
//                remisyen = remisyen.add(trans.getAmaun());//remisyen
                // aig.setKodRemisyen(kodTrans);
            } else if (kodTrans.equals("61018") || kodTrans.equals("72457") || kodTrans.equals("99011") ) {
                notis6a = notis6a.add(trans.getAmaun());//notis 6A
            } else if (kodTrans.equals("61601")) {
                cukaiSemasaParit = cukaiSemasaParit.add(trans.getAmaun());//cukai Semasa Parit
                jumlah = jumlah.add(cukaiSemasaParit);
            } else if (kodTrans.equals("61602")) {
                tunggakanCukaiParit = tunggakanCukaiParit.add(trans.getAmaun());//tunggakan Cukai Parit
                jumlah = jumlah.add(tunggakanCukaiParit);
            } else if (kodTrans.equals("61611")) {
            }
        }
        for (Transaksi trans : akaun.getSenaraiTransaksiKredit()) {
            String kodTrans = trans.getKodTransaksi().getKod();
//            int recThn = trans.getTahunKewangan() != 0 ? trans.getTahunKewangan() : 0;

            if (kodTrans.equals("99000") || kodTrans.equals("99001")
                    || kodTrans.equals("99002") || kodTrans.equals("99003") || kodTrans.equals("99030")) {
                remisyen = remisyen.add(trans.getAmaun());//remisyen
            }
        }
//        maklumatCukai.setIdHakmilik(akaun.getHakmilik().getIdHakmilik());
//        maklumatCukai.setBayaranLain("");
//        maklumatCukai.setCukaiKeneBayar(jumlah.toString());
//        maklumatCukai.setCukaiParit(cukaiSemasaParit.toString());
//        maklumatCukai.setCukaiPerluBayar("");
//        maklumatCukai.setCukaiTaliAir("0");
//        maklumatCukai.setDenda(dendaLewatSemasa.toString());
//        maklumatCukai.setNoAkaun(akaun.getNoAkaun());
//        maklumatCukai.setPengecualian("");
//        maklumatCukai.setPengurangan("");
//        maklumatCukai.setTahun(String.valueOf(currYear));
//        maklumatCukai.setTunggakan(tunggakanCukaiTanah.toString());
        maklumatCukai.setIdhakmilik(akaun.getHakmilik().getIdHakmilik());
        maklumatCukai.setCukai_tali_air(cukaiSemasaParit.toString());
        maklumatCukai.setCukai_tanah(cukaiSemasaTanah.toString());
        maklumatCukai.setDenda_lewat_semasa(dendaLewatSemasa.toString());
        maklumatCukai.setJumlahCukai(akaun.getBaki().toString());
        maklumatCukai.setNotis6a(notis6a.toString());
        maklumatCukai.setRemisyen(remisyen.toString());
        maklumatCukai.setTahun(String.valueOf(currYear));
        maklumatCukai.setTunggakan_cukai_tanah(tunggakanCukaiTanah.toString());
        maklumatCukai.setTunggakan_denda_lewat(tunggakanDendaLewat.toString());
        maklumatCukai.setTunggakan_tali_air(tunggakanCukaiParit.toString());
        return maklumatCukai;
    }

//    public MaklumatBayaran getMaklumatBayaran(MaklumatBayaran maklumatBayaran) {
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
//        etanah.model.Hakmilik hakmilik = hakmilikDAO.findById(maklumatBayaran.getIdHakmilik());
//        if (hakmilik == null) {
//            throw new RuntimeException("hakmilik tidak dikenali");
//        }
//
//        Akaun akaun = hakmilik.getAkaunCukai();
//        if (akaun == null) {
//            throw new RuntimeException("Akaun tidak dikenali");
//        }
//
//        Pengguna pengguna = penggunaDAO.findById("admin");
//        hakmilik.setInfoAudit(setIA(pengguna));
////        MaklumatBayaran maklumatBayaran = new MaklumatBayaran();
//        try {
//            DokumenKewangan dokKew = null;
//            CaraBayaran caraBayaran = null;
////            String resit = null;
//            int size = akaun.getSenaraiTransaksiKredit().size();
//            List<Transaksi> trans = akaun.getSenaraiTransaksiKredit();
//            for (int i = 0; i< size ; i++){ 
//                
//                                //        maklumatBayaran.se tra
//                if (dokKew == null) {
//                    dokKew = trans.get(i).getDokumenKewangan();
////                    resit = trans.getDokumenKewangan().getIdDokumenKewangan();
//                }
//                if (dokKew.getSenaraiBayaran() != null) {
//                    caraBayaran = dokKew.getSenaraiBayaran().get(0).getCaraBayaran();
//                }
//
////            String resit = dokKew.getIdDokumenKewangan();
//                maklumatBayaran.setIdCaraBayar(caraBayaran != null ? String.valueOf(caraBayaran.getKodCaraBayaran().getKodMyEtapp()) : "");
//                maklumatBayaran.setIdHakmilik(akaun.getHakmilik().getIdHakmilik());
//                maklumatBayaran.setJumlahBayaran(dokKew != null ? String.valueOf(dokKew.getAmaunBayaran()) : "");
//                maklumatBayaran.setNamaBank(caraBayaran.getBank() != null ? caraBayaran.getBank().getNama() + " " + caraBayaran.getBankCawangan() : "");
//                maklumatBayaran.setNoAkaun(akaun.getNoAkaun());
//                maklumatBayaran.setNoResit(dokKew.getIdDokumenKewangan());
////            maklumatBayaran.setNoRujBayaran(dokKew != null && StringUtils.isNotEmpty(dokKew.getNoRujukanManual()) ? String.valueOf(dokKew.getNoRujukanManual()) : "");
//                maklumatBayaran.setNoRujBayaran(caraBayaran != null ? String.valueOf(caraBayaran.getNoRujukan()) : "");
//                maklumatBayaran.setTahun(dokKew != null ? String.valueOf(sdf1.format(dokKew.getTarikhTransaksi())) : "");
//                maklumatBayaran.setTarikhBayaran(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
//                maklumatBayaran.setTarikhResit(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
//                maklumatBayaran.setTarikhTerimaBayaran(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
//               
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(HTPservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return maklumatBayaran;
//    }

//working but get list transaksi!!!
//    
//    public MaklumatBayaran[] getMaklumatBayaran(MaklumatBayaran maklumatBayaran1) {
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
//        etanah.model.Hakmilik hakmilik = hakmilikDAO.findById(maklumatBayaran1.getIdHakmilik());
//        if (hakmilik == null) {
//            throw new RuntimeException("hakmilik tidak dikenali");
//        }
//
//        Akaun akaun = hakmilik.getAkaunCukai();
//        if (akaun == null) {
//            throw new RuntimeException("Akaun tidak dikenali");
//        }
//
//        Pengguna pengguna = penggunaDAO.findById("admin");
//        hakmilik.setInfoAudit(setIA(pengguna));
//        MaklumatBayaran[] maklumatBayaran = new MaklumatBayaran[akaun.getSenaraiTransaksiKredit().size()];
//        try {
//            DokumenKewangan dokKew = null;
//            CaraBayaran caraBayaran = null;
////            String resit = null;
//
//            int i = 0;
//            for (Transaksi trans : akaun.getSenaraiTransaksiKredit()) {
//                //        maklumatBayaran.se tra
//                if (trans.getDokumenKewangan() != null) {
//                    dokKew = trans.getDokumenKewangan();
////                    resit = trans.getDokumenKewangan().getIdDokumenKewangan();
//                }
//                if (dokKew.getSenaraiBayaran() != null) {
//                    caraBayaran = dokKew.getSenaraiBayaran().get(0).getCaraBayaran();
//                }
//                MaklumatBayaran maklumatBayaran2 = new MaklumatBayaran();
////            String resit = dokKew.getIdDokumenKewangan();
//                maklumatBayaran2.setIdCaraBayar(caraBayaran != null ? String.valueOf(caraBayaran.getKodCaraBayaran().getKodMyEtapp()) : "");
//                maklumatBayaran2.setIdHakmilik(akaun.getHakmilik().getIdHakmilik());
////                maklumatBayaran2.setJumlahBayaran(dokKew != null ? String.valueOf(dokKew.getAmaunBayaran()) : "");
//                maklumatBayaran2.setJumlahBayaran(trans != null ? String.valueOf(trans.getAmaun()) : "");
//                maklumatBayaran2.setNamaBank(caraBayaran.getBank() != null ? caraBayaran.getBank().getNama() + " " + caraBayaran.getBankCawangan() : "");
//                maklumatBayaran2.setNoAkaun(akaun.getNoAkaun());
//                maklumatBayaran2.setNoResit(dokKew.getIdDokumenKewangan());
////            maklumatBayaran.setNoRujBayaran(dokKew != null && StringUtils.isNotEmpty(dokKew.getNoRujukanManual()) ? String.valueOf(dokKew.getNoRujukanManual()) : "");
//                maklumatBayaran2.setNoRujBayaran(caraBayaran != null ? String.valueOf(caraBayaran.getNoRujukan()) : "");
////                maklumatBayaran2.setTahun(dokKew != null ? String.valueOf(sdf1.format(dokKew.getTarikhTransaksi())) : "");
//                maklumatBayaran2.setTahun(trans != null ? String.valueOf(trans.getUntukTahun()) : "");
//                maklumatBayaran2.setTarikhBayaran(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
//                maklumatBayaran2.setTarikhResit(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
//                maklumatBayaran2.setTarikhTerimaBayaran(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
//                maklumatBayaran[i] = maklumatBayaran2;
//                i++;
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(HTPservice.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return maklumatBayaran;
//    }
    
    //shida:: ubah utk dpt maklumat bayaran ikut list resit
    public MaklumatBayaran[] getMaklumatBayaran(MaklumatBayaran maklumatBayaran1) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        etanah.model.Hakmilik hakmilik = hakmilikDAO.findById(maklumatBayaran1.getIdHakmilik());
        if (hakmilik == null) {
            throw new RuntimeException("hakmilik tidak dikenali");
        }

        Akaun akaun = hakmilik.getAkaunCukai();
        if (akaun == null) {
            throw new RuntimeException("Akaun tidak dikenali");
        }

        Pengguna pengguna = penggunaDAO.findById("admin");
        hakmilik.setInfoAudit(setIA(pengguna));
        List<DokumenKewangan> dkList = ambilService.findDokKew(akaun.getNoAkaun());
        MaklumatBayaran[] maklumatBayaran = new MaklumatBayaran[dkList.size()];
        try {
            DokumenKewangan dokKew = null;
            CaraBayaran caraBayaran = null;
//            String resit = null;

            int i= 0 ;
//            for (Transaksi trans : akaun.getSenaraiTransaksiKredit()) {
            for ( DokumenKewangan dok : dkList ) {
                //        maklumatBayaran.se tra
                if (dok != null) {
                    dokKew = dok;
//                    resit = trans.getDokumenKewangan().getIdDokumenKewangan();
                }
                if (dokKew.getSenaraiBayaran() != null) {
                    caraBayaran = dokKew.getSenaraiBayaran().get(0).getCaraBayaran();
                }
                MaklumatBayaran maklumatBayaran2 = new MaklumatBayaran();
//            String resit = dokKew.getIdDokumenKewangan();
                maklumatBayaran2.setIdCaraBayar(caraBayaran != null ? String.valueOf(caraBayaran.getKodCaraBayaran().getKodMyEtapp()) : "");
                maklumatBayaran2.setIdHakmilik(akaun.getHakmilik().getIdHakmilik());
                maklumatBayaran2.setJumlahBayaran(dokKew != null ? String.valueOf(dokKew.getAmaunBayaran()) : "");
//                maklumatBayaran2.setJumlahBayaran(trans != null ? String.valueOf(trans.getAmaun()) : "");
                maklumatBayaran2.setNamaBank(caraBayaran.getBank() != null ? caraBayaran.getBank().getNama() + " " + caraBayaran.getBankCawangan() : "");
                maklumatBayaran2.setNoAkaun(akaun.getNoAkaun());
                maklumatBayaran2.setNoResit(dokKew.getIdDokumenKewangan());
//            maklumatBayaran.setNoRujBayaran(dokKew != null && StringUtils.isNotEmpty(dokKew.getNoRujukanManual()) ? String.valueOf(dokKew.getNoRujukanManual()) : "");
                maklumatBayaran2.setNoRujBayaran(caraBayaran != null ? String.valueOf(caraBayaran.getNoRujukan()) : "");
                maklumatBayaran2.setTahun(dokKew != null ? String.valueOf(sdf1.format(dokKew.getTarikhTransaksi())) : "");
//                maklumatBayaran2.setTahun(trans != null ? String.valueOf(trans.getUntukTahun()) : "");
                maklumatBayaran2.setTarikhBayaran(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
                maklumatBayaran2.setTarikhResit(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
                maklumatBayaran2.setTarikhTerimaBayaran(dokKew != null ? formatDate(dokKew.getTarikhTransaksi()) : "");
                maklumatBayaran[i] = maklumatBayaran2;
                i++;
            }
        } catch (ParseException ex) {
            Logger.getLogger(HTPservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maklumatBayaran;
    }


    public String updateHakmilikAgensi(HakmilikAgensi hakmilikAgensi) {



        //check idHakmilik exist x
        List<AgensiHakmilik> ha = ambilService.findAgensiById(hakmilikAgensi.getIdHakmilik());
        if (ha != null) {
            for (int i = 0; i < ha.size(); i++) {

                AgensiHakmilik agenHak = agensiHakmilikDAO.findById(ha.get(i).getIdAgensiHakmilik());

                if (agenHak != null) {
                    agenHak.setAktif('T');
                    agenHak.setInfoAudit(ambilService.KemaskiniInfoAudit());
                    agenHak = ambilService.saveAgensiHakmilik(agenHak);

                }
            }

        }


        AgensiHakmilik ah = new AgensiHakmilik();
        ah.setHakmilik(hakmilikDAO.findById(hakmilikAgensi.getIdHakmilik()));
        ah.setAktif('Y');
        ah.setKegunaanTanah(hakmilikAgensi.getKegunaanTanah());
        ah.setKodAgensi(hakmilikAgensi.getKodAgensi());
        ah.setKodKementerian(hakmilikAgensi.getKodKementerian());
        ah.setNamaAgensi(hakmilikAgensi.getNamaAgensi());
        ah.setInfoAudit(ambilService.setInfoAudit());
        ah.setNamaKementerian(hakmilikAgensi.getNamaKementerian());
        ah = ambilService.saveAgensiHakmilik(ah);


        //check status hakmilik if batal find hak bru 

        List<HakmilikSebelum> newHak = ambilService.findNewHakByHakSblm(hakmilikAgensi.getIdHakmilik());
        if (newHak != null) {

            for (int r = 0; r < newHak.size(); r++) {
                AgensiHakmilik ahak = ambilService.findAktifAgensiById(hakmilikAgensi.getIdHakmilik());

                AgensiHakmilik ah2 = new AgensiHakmilik();
                ah2.setHakmilik(hakmilikDAO.findById(newHak.get(r).getHakmilik().getIdHakmilik()));
                ah2.setAktif('Y');
                ah2.setKegunaanTanah(ahak.getKegunaanTanah());
                ah2.setKodAgensi(ahak.getKodAgensi());
                ah2.setKodKementerian(ahak.getKodKementerian());
                ah2.setNamaAgensi(ahak.getNamaAgensi());
                ah2.setInfoAudit(ambilService.setInfoAudit());
                ah2.setNamaKementerian(ahak.getNamaKementerian());
                ah2 = ambilService.saveAgensiHakmilik(ah2);
            }
        }

        return "";
    }

    public String formatDate(Date s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date;
        if (s == null) {
            date = null;
        } else {
            date = sdf.format(s);
        }

        return date;
    }

    public InfoAudit setIA(Pengguna p) {
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(p);
        return ia;
    }
}
