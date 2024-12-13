/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp;

import com.Ostermiller.util.Base64;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.Configuration;
import etanah.dao.AgensiHakmilikDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.EtappPermohonanDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PihakDAO;
import etanah.model.Akaun;
import etanah.model.Permohonan;  //1.ni refer table MOHON
import etanah.model.PermohonanRujukanLuar;   //2.ni refer table MOHON_RUJ_LUAR
import etanah.model.HakmilikPermohonan;   //3.ni refer table MOHON_HAKMILIK
import etanah.model.PermohonanKertas;   //4.ni refer table MOHON_KERTAS
import etanah.model.PermohonanKertasKandungan;  //5.ni refer table MOHON_KERTAS_KANDUNGAN
import etanah.model.Dokumen;   //6.ni refer table DOKUMEN
import etanah.model.DokumenKewangan;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodDokumen;
import etanah.model.KodUOM;
import etanah.model.Hakmilik;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Pihak;
import etanah.model.etapp.AgensiHakmilik;
import etanah.model.etapp.EtappLog;
import etanah.model.etapp.EtappPermohonan;
import etanah.model.etapp.TBLINTPPTDERAFMMK;
import etanah.model.etapp.TBLINTPPTMAKLUMATPENGAMBILAN;
import etanah.model.etapp.TBLPPTINTDERAFMMKTAJUK;
import etanah.model.etapp.TBLINTPPTWARTA;
import etanah.model.etapp.TBLINTPPTHAKMILIK;
import etanah.model.etapp.TBLINTPPTDOKUMEN;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.PengambilanMMKService;
import etanah.service.PengambilanService;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import etanah.view.etapp.htp.ws.HakmilikAgensi;
import etanah.view.etapp.ws.Tblintpptmaklumatpengambilan;
import etanah.view.strata.InitiateTaskService;
import etanah.workflow.WorkFlowService;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.activation.DataHandler;
import org.hibernate.Query;
import org.hibernate.Session;

//tblintpptmaklumatpengambilan
/**
 *
 * @author ahmad.marzuki
 */
public class PengambilanEtanahService { //start class PengambilanEtanahService

    @Inject
    AgensiHakmilikDAO agensiHakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    private GeneratorIdPermohonan idGenerator;
    @Inject
    Configuration conf;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EtappPermohonanDAO etappPermohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    InitiateTaskService initiateService;
    @Inject
    PengambilanMMKService ambilMMKService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
//    private String TUJUAN;
//    private String PERIHALPERMOHONAN;
//    private String PERIHALTANAH;
//    private String PERIHALPEMOHON;
//    private String ANGGARANPAMPASAN;
//    private String ULASANTEKNIKAL;
//    private String PANDANGANYB;
//    private String PANDANGANPT;
//    private String PERAKUANPT;
//    private String PERAKUANPTG;
    StringBuffer daerah;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public void setMohon(Tblintpptmaklumatpengambilan t) throws Exception { //start setMohon
        //////1. start class Permohonan //////

        EtappPermohonan e = findByNofail(t.getNo_fail_jkptg());
        if (e != null) {
        } else {
            e = new EtappPermohonan();
            String kodUrusan = null;
            if (t.getFlag_proses().equals("BorangC")) {
                kodUrusan = "ESK8";
            } else if (t.getFlag_proses().equals("BorangA")) {
                kodUrusan = "ESK4";
            }
            KodNegeri kN = kodNegeriDAO.findById("17");
            KodUrusan kodUrusan1 = kodUrusanDAO.findById(kodUrusan);
//            KodCawangan caw = kodCawanganDAO.findById("01");
            KodCawangan caw = kodCawanganDAO.findById(t.getKod_daerah_pengambilan());
            if (caw == null) {
                caw = kodCawanganDAO.findById("00");
            }
            Permohonan mohon = new Permohonan();//class model Permohonan
            mohon.setIdPermohonan(idGenerator.generate(conf.getKodNegeri(), caw, kodUrusan1));
            mohon.setPenyerahNama("MyEtapp");
            mohon.setPenyerahAlamat1(null);
            mohon.setPenyerahAlamat2(null);
            mohon.setPenyerahAlamat3(null);
            mohon.setPenyerahAlamat4(null);
            mohon.setPenyerahPoskod(null);
            mohon.setPenyerahNegeri(null);
            mohon.setPenyerahNoTelefon1(null);
            mohon.setSebab(t.getTujuan());  //column SBB
            mohon.setInfoAudit(setInfoAudit());
            mohon.setKodUrusan(kodUrusan1);
            mohon.setPenyerahNoPengenalan("X");
            mohon.setPenyerahNegeri(kN);
            mohon.setIdWorkflow(kodUrusan1.getIdWorkflow());
            mohon.setCawangan(caw);
            mohon.setFolderDokumen(setForlderDokumen("MyEtapp-" + t.getNo_fail_jkptg()));
            String kodCaw = caw.getKod() + ",00";
            WorkFlowService.initiateTask(mohon.getKodUrusan().getIdWorkflow(),
                    mohon.getIdPermohonan(), kodCaw, setInfoAudit().getDimasukOleh().getIdPengguna(),
                    mohon.getKodUrusan().getNama());

            mohon = savePermohonan(mohon);
            Pemohon pe = new Pemohon();
            Pihak pi = new Pihak();
            pi.setNama(t.getNama_agensi());

            pi.setInfoAudit(setInfoAudit());
            pi = savePihak(pi);
            pe.setPihak(pi);
            pe.setNama(t.getNama_agensi());
            pe.setCawangan(mohon.getCawangan());
            pe.setPermohonan(mohon);
            pe.setInfoAudit(setInfoAudit());
            pe = savePemohon(pe);
            e.setMohon(mohon);
            e.setNoFail(t.getNo_fail_jkptg());
            e.setInfoAudit(setInfoAudit());
            e = saveEtappPermohonan(e);
        }
    } //end setMohon

    public void setMohon(String noFail, String flag, String s) throws Exception { //start setMohon
        //////1. start class Permohonan //////

        EtappPermohonan e = findByNofail(noFail);
        if (e != null) {
        } else {
            e = new EtappPermohonan();
            String kodUrusan = null;
            if (flag.equals("BorangC")) {
                kodUrusan = "ESK8";
            } else if (flag.equals("BorangA")) {
                kodUrusan = "ESK4";
            }
            KodNegeri kN = kodNegeriDAO.findById("17");
            KodUrusan kodUrusan1 = kodUrusanDAO.findById(kodUrusan);
            KodCawangan caw = kodCawanganDAO.findById("01");
            Permohonan mohon = new Permohonan();//class model Permohonan
            mohon.setIdPermohonan(idGenerator.generate(conf.getKodNegeri(), caw, kodUrusan1));
            mohon.setPenyerahNoPengenalan("X");
            mohon.setPenyerahNama("MyeTapp");
            mohon.setPenyerahAlamat1(null);
            mohon.setPenyerahAlamat2(null);
            mohon.setPenyerahAlamat3(null);
            mohon.setPenyerahAlamat4(null);
            mohon.setPenyerahPoskod(null);
            mohon.setPenyerahNegeri(null);
            mohon.setPenyerahNoTelefon1(null);
            mohon.setSebab("");  //column SBB
            mohon.setInfoAudit(setInfoAudit());
            mohon.setPenyerahNoPengenalan("X");
            mohon.setPenyerahNegeri(kN);
            mohon.setIdWorkflow(kodUrusan1.getIdWorkflow());
            mohon.setKodUrusan(kodUrusan1);
            mohon.setCawangan(caw);
            mohon.setFolderDokumen(setForlderDokumen("MyEtapp-" + noFail));
            String kodCaw = caw.getKod() + ",00";
            WorkFlowService.initiateTask(mohon.getKodUrusan().getIdWorkflow(),
                    mohon.getIdPermohonan(), kodCaw, setInfoAudit().getDimasukOleh().getIdPengguna(),
                    mohon.getKodUrusan().getNama());

            mohon = savePermohonan(mohon);

            e.setMohon(mohon);
            e.setNoFail(noFail);
            e.setInfoAudit(setInfoAudit());
            e = saveEtappPermohonan(e);
        }
    } //end setMohon

    public void setMohonRujLuar(String noFail) { //end setMohonRujLuar
        TBLPPTINTDERAFMMKTAJUK tblpptintderafmmktajuk = findTBLPPTINTDERAFMMKTAJUK(noFail);;
        TBLINTPPTWARTA tblintpptwarta = findTBLINTPPTWARTA(noFail);
        TBLINTPPTMAKLUMATPENGAMBILAN tblintpptmaklumatpengambilan = findTBLINTPPTMAKLUMATPENGAMBILAN(noFail);
        EtappPermohonan e = findByNofail(noFail);
        PermohonanRujukanLuar ruj = pengambilanService.findByIdMohonRujLuar(e.getMohon().getIdPermohonan());
        if (ruj != null) {
        } else {
            ruj = new PermohonanRujukanLuar();  //class model PermohonanRujukanLuar

        }
        ruj.setPermohonan(e.getMohon());
        if (tblpptintderafmmktajuk != null) {

            ruj.setNamaSidang(tblpptintderafmmktajuk.getKeteranganSidang());
            ruj.setNoSidang(null); //column NO_SIDANG tak jumpe la method dia as in word kosong
            ruj.setTarikhSidang(tblpptintderafmmktajuk.getTarikhSidang());

        }
        if (tblintpptmaklumatpengambilan != null) {
            ruj.setNoRujukan(tblintpptmaklumatpengambilan.getNoRujSuratKJP() != null ? tblintpptmaklumatpengambilan.getNoRujSuratKJP() : "TIDAK DINYATAKAN");
            ruj.setTarikhRujukan(tblintpptmaklumatpengambilan.getTarikhSuratKJP());
            ruj.setCatatan(null);   //column CATATAN tak jumpe la method dia as in word kosong
        }
        if (tblintpptwarta != null) {

            ruj.setTarikhDisampai(new Date());
            ruj.setTarikhLulus(tblintpptwarta.getTarikhWarta());
            ruj.setItem(tblintpptwarta.getNoWarta());
            ruj.setInfoAudit(setInfoAudit());
        }
        ruj.setNoFail(tblpptintderafmmktajuk.getNoFailJKPTG());
        KodRujukan kodruj = kodRujukanDAO.findById("NF");
        ruj.setCawangan(e.getMohon().getCawangan());
        ruj.setKodRujukan(kodruj);
        ruj.setInfoAudit(setInfoAudit());
        ruj = savePermohonanRujukanLuar(ruj);
//        if (tblpptintderafmmktajuk.getFlagProses().equals("BorangC") || tblpptintderafmmktajuk.getFlagProses().equals("BorangA")) {
//            setPermohonanKertas(tblpptintderafmmktajuk.getNoFailJKPTG());
//        }//////2.end class PermohonanRujukanLuar //////

    }  //end setMohonRujLuar

    public void setHakmilikPermohonan(TBLINTPPTHAKMILIK tblintppthakmilik) { //start setHakmilikPermohonan
        //////3.start class HakmilikPermohonan //////

        EtappPermohonan e = findByNofail(tblintppthakmilik.getNoFailJKPTG());
        Hakmilik hakmilik = hakmilikDAO.findById(tblintppthakmilik.getIdHakmilik());
        if (hakmilik != null) {
        } else {
            throw new RuntimeException("Hakmilik tidak wujud");
        }
        HakmilikPermohonan hak = pengambilanService.findByIdHakmilikIdPermohonan(e.getMohon().getIdPermohonan(), hakmilik.getIdHakmilik());
        if (hak != null) {
        } else {
            hak = new HakmilikPermohonan();
        }
        hak.setHakmilik(hakmilik);
        hak.setLuasTerlibat(new BigDecimal(tblintppthakmilik.getLuasAmbil()));   //column LUAS
        KodUOM kodu = kodUOMDAO.findById(tblintppthakmilik.getKodLuasAmbil());
        if (kodu != null) {
            hak.setKodUnitLuas(kodu);   //column KOD_UOM
        }
        KodHakmilik kodh = kodHakmilikDAO.findById(tblintppthakmilik.getKodUnitHakmilik());
        if (kodh != null) {
            hak.setKodHakmilik(kodh);  //column KOD_HAKMILIK
        }
        hak.setNoLot(tblintppthakmilik.getNoLot());    //column NO_LOT
        hak.setPermohonan(e.getMohon());
        hak.setInfoAudit(setInfoAudit());
        saveMohonHakmilik(hak);

    }

    public void setPermohonanKertas(String noFail) { //start setPermohonanKertas
        //////4.start class PermohonanKertas //////

        EtappPermohonan e = findByNofail(noFail);
        TBLPPTINTDERAFMMKTAJUK tblpptintderafmmktajuk = findTBLPPTINTDERAFMMKTAJUK(noFail);
        PermohonanKertas Pkertas = findByIDMohon(e.getMohon().getIdPermohonan());

        List<TBLINTPPTDERAFMMK> noRujUPT = findMMKdrafKand1(noFail, "RUJUKAN");

        if (Pkertas != null) {
        } else {
            Pkertas = new PermohonanKertas();
        }

        //column CATATAN tak jumpe la method dia as in word kosong
//        if (tblpptintderafmmktajuk.getTajuk() != null) {
//            Pkertas.setTajuk(tblpptintderafmmktajuk.getTajuk());
//        } else {
//            Permohonan mohon = permohonanDAO.findById(String.valueOf(e.getIdEtMohon()));
//            String ku = mohon.getKodUrusan().getKod();
//            if (ku == "ESK4") {
//                Pkertas.setTajuk("PERMOHONAN PENGAMBILAN TANAH DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.");
//            } else if (ku == "ESK8") {
//                Pkertas.setTajuk("PERMOHONAN PENGAMBILAN TANAH SEKSYEN 8 NEGERI MELAKA DI BAWAH SEKSYEN 8 AKTA PENGAMBILAN TANAH 1960.");
//            }
//        }
        Pkertas.setTajuk(tblpptintderafmmktajuk.getTajuk());
        Pkertas.setTarikhSidang(tblpptintderafmmktajuk.getTarikhSidang());
        Pkertas.setTempatSidang(tblpptintderafmmktajuk.getTempatSidang());
//        Pkertas.setNomborRujukan(null);  //column CATATAN tak jumpe la method dia as in word kosong
        Pkertas.setInfoAudit(setInfoAudit());
        Pkertas.setCawangan(e.getMohon().getCawangan());
        Pkertas.setPermohonan(e.getMohon());
        Pkertas = savePermohonanKertas(Pkertas);
//        setPermohonanKertasKandungan(noFail, Pkertas);

        //////4.end class PermohonanKertas //////
    } //end setPermohonanKertas

    public void setPermohonanKertasKandungan(String noFail, PermohonanKertas kertas) { //start setPermohonanKertasKandungan
        //////5.start class PermohonanKertasButiran //////
        List<TBLINTPPTDERAFMMK> listTblintderafmmk = findtblintderafmmk(noFail);
        EtappPermohonan e = findByNofail(noFail);
//        PermohonanKertasKandungan PkertasKdgn = new PermohonanKertasKandungan();
//        for (TBLINTPPTDERAFMMK tblintderafmmk : listTblintderafmmk) {
//            PkertasKdgn.setKertas(kertas);
////      PkertasKdgn.setIdKandungan(tblintderafmmk.getIdTblKpsn());
//            // PkertasKdgn.setSubtajuk(tblpptkeputusanmmk.getFlagKeputusanMMK());
//            PkertasKdgn.setKandungan(tblintderafmmk.getKeteranganMMK());
//            PkertasKdgn.setCawangan(kertas.getCawangan());
//
//            PkertasKdgn.setInfoAudit(setInfoAudit());
//            PkertasKdgn = savePermohonanKertasKandungan(PkertasKdgn);
//        }

        Permohonan permohonan = permohonanDAO.findById(e.getMohon().getIdPermohonan());

        List<TBLINTPPTDERAFMMK> noRujUPT = findMMKdrafKand1(e.getNoFail(), "RUJUKAN");
        if (noRujUPT != null) {
            for (int u = 0; u < noRujUPT.size(); u++) {
                if (noRujUPT.get(u).getKeteranganMMK() != null) {

                    PermohonanKertasKandungan noRujukanMMKN = new PermohonanKertasKandungan();
                    noRujukanMMKN.setKandungan(noRujUPT.get(u).getKeteranganMMK());
                    noRujukanMMKN.setKertas(kertas);
                    noRujukanMMKN.setCawangan(permohonan.getCawangan());
                    noRujukanMMKN.setBil(0);
                    noRujukanMMKN.setSubtajuk("0");
                    noRujukanMMKN.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(noRujukanMMKN);
                }
            }
        }

        if (listTblintderafmmk != null) {
            List<TBLINTPPTDERAFMMK> tujuanList = findMMKdrafKand(e.getNoFail(), "TUJUAN");
            if (tujuanList != null) {

                for (int i = 0; i < tujuanList.size(); i++) {

                    PermohonanKertasKandungan PKKTujuan = new PermohonanKertasKandungan();
                    PKKTujuan.setKertas(kertas);
                    PKKTujuan.setCawangan(permohonan.getCawangan());
                    PKKTujuan.setBil(1);
                    PKKTujuan.setSubtajuk("1." + (i + 1));
                    PKKTujuan.setKandungan(tujuanList.get(i).getKeteranganMMK());
                    PKKTujuan.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(PKKTujuan);
//                        }
                }
            }

            List<TBLINTPPTDERAFMMK> permohonanList = findMMKdrafKand(e.getNoFail(), "PERIHALPERMOHONAN");
            if (permohonanList != null) {

                for (int i = 0; i < permohonanList.size(); i++) {
//                        for (TBLINTPPTDERAFMMK permohonanLIST : permohonanList) {

                    PermohonanKertasKandungan PKKPermohonan = new PermohonanKertasKandungan();
                    PKKPermohonan.setKertas(kertas);
                    PKKPermohonan.setCawangan(permohonan.getCawangan());
                    PKKPermohonan.setBil(21);
                    PKKPermohonan.setSubtajuk("2.1." + (i + 1));
                    PKKPermohonan.setKandungan(permohonanList.get(i).getKeteranganMMK());
                    PKKPermohonan.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(PKKPermohonan);
                }
            }

            List<TBLINTPPTDERAFMMK> tanahList = findMMKdrafKand1(e.getNoFail(), "TANAH");
            if (tanahList != null) {

                for (int i = 0; i < tanahList.size(); i++) {
//
                    PermohonanKertasKandungan PKKTanah = new PermohonanKertasKandungan();
                    PKKTanah.setKertas(kertas);
                    PKKTanah.setCawangan(permohonan.getCawangan());
                    PKKTanah.setBil(22);
                    PKKTanah.setSubtajuk("2.2." + (i + 1));
                    PKKTanah.setKandungan(tanahList.get(i).getKeteranganMMK());
                    PKKTanah.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(PKKTanah);
                }
            }

            List<TBLINTPPTDERAFMMK> pemohonList = findMMKdrafKand(e.getNoFail(), "PERIHALPEMOHON");
            if (pemohonList != null) {

                for (int i = 0; i < pemohonList.size(); i++) {

                    PermohonanKertasKandungan PKKPemohon = new PermohonanKertasKandungan();
                    PKKPemohon.setKertas(kertas);
                    PKKPemohon.setCawangan(permohonan.getCawangan());
                    PKKPemohon.setBil(23);
                    PKKPemohon.setSubtajuk("2.3." + (i + 1));
                    PKKPemohon.setKandungan(pemohonList.get(i).getKeteranganMMK());
                    PKKPemohon.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(PKKPemohon);
                }
            }

            List<TBLINTPPTDERAFMMK> pampasanList = findMMKdrafKand(e.getNoFail(), "ANGGARANPAMPASAN");
            if (pampasanList != null) {

                for (int i = 0; i < pampasanList.size(); i++) {

                    PermohonanKertasKandungan PKKPampasan = new PermohonanKertasKandungan();
                    PKKPampasan.setKertas(kertas);
                    PKKPampasan.setCawangan(permohonan.getCawangan());
                    PKKPampasan.setBil(24);
                    PKKPampasan.setSubtajuk("2.4." + (i + 1));
                    PKKPampasan.setInfoAudit(setInfoAudit());
                    PKKPampasan.setKandungan(pampasanList.get(i).getKeteranganMMK());
                    savePermohonanKertasKandungan(PKKPampasan);
                }
            }

            List<TBLINTPPTDERAFMMK> ulasanList = findMMKdrafKand(e.getNoFail(), "TEKNIKAL");
            if (ulasanList != null) {

                for (int i = 0; i < ulasanList.size(); i++) {

                    PermohonanKertasKandungan PKKUTeknikal = new PermohonanKertasKandungan();
                    PKKUTeknikal.setKertas(kertas);
                    PKKUTeknikal.setCawangan(permohonan.getCawangan());
                    PKKUTeknikal.setBil(3);
                    PKKUTeknikal.setSubtajuk("3." + (i + 1));
                    PKKUTeknikal.setKandungan(ulasanList.get(i).getKeteranganMMK());
                    PKKUTeknikal.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(PKKUTeknikal);
                }
            }
            List<TBLINTPPTDERAFMMK> YBList = findMMKdrafKand(e.getNoFail(), "PANDANGANYB");
            if (YBList != null) {

                for (int i = 0; i < YBList.size(); i++) {

                    PermohonanKertasKandungan PKKPandanganYB = new PermohonanKertasKandungan();
                    PKKPandanganYB.setKertas(kertas);
                    PKKPandanganYB.setCawangan(permohonan.getCawangan());
                    PKKPandanganYB.setBil(4);
                    PKKPandanganYB.setSubtajuk("4." + (i + 1));
                    PKKPandanganYB.setInfoAudit(setInfoAudit());
                    PKKPandanganYB.setKandungan(YBList.get(i).getKeteranganMMK());
                    savePermohonanKertasKandungan(PKKPandanganYB);
                }
            }

            List<TBLINTPPTDERAFMMK> pandanganPT = findMMKdrafKand(e.getNoFail(), "PANDANGANPT");
            if (pandanganPT != null) {

                PermohonanKertasKandungan PKKPandanganPT = new PermohonanKertasKandungan();
                PKKPandanganPT.setKertas(kertas);
                PKKPandanganPT.setCawangan(permohonan.getCawangan());
                PKKPandanganPT.setBil(5);
                PKKPandanganPT.setSubtajuk("5.1");
                PKKPandanganPT.setInfoAudit(setInfoAudit());
                PKKPandanganPT.setKandungan("Pentadbir Tanah " + permohonan.getCawangan().getName() + ", (Pengarah Jabatan Ketua Pengarah Tanah dan Galian, Melaka) setelah meneliti permohonan ini berpendapat bahawa pengambilan tanah untuk tapak yang dimaksudkan bolehlah diteruskan, memandangkan kepada faktor-faktor berikut:");
                savePermohonanKertasKandungan(PKKPandanganPT);

                for (int i = 0; i < pandanganPT.size(); i++) {

                    PKKPandanganPT = new PermohonanKertasKandungan();
                    PKKPandanganPT.setKertas(kertas);
                    PKKPandanganPT.setCawangan(permohonan.getCawangan());
                    PKKPandanganPT.setBil(51);
                    PKKPandanganPT.setSubtajuk("5.1." + (i + 1));
                    PKKPandanganPT.setInfoAudit(setInfoAudit());
                    PKKPandanganPT.setKandungan(pandanganPT.get(i).getKeteranganMMK());
                    savePermohonanKertasKandungan(PKKPandanganPT);

                }
            }
            List<TBLINTPPTDERAFMMK> PerakuanPT = findMMKdrafKand(e.getNoFail(), "PERAKUANPT");
            if (PerakuanPT != null) {

                PermohonanKertasKandungan PKKPerakuanPT = new PermohonanKertasKandungan();
                PKKPerakuanPT.setKertas(kertas);
                PKKPerakuanPT.setCawangan(permohonan.getCawangan());
                PKKPerakuanPT.setBil(6);
                PKKPerakuanPT.setSubtajuk("6.1");
                PKKPerakuanPT.setKandungan("Pentadbir Tanah " + permohonan.getCawangan().getName() + ", (Pengarah Jabatan Ketua Pengarah Tanah dan Galian, Melaka) dengan ini memperakukan supaya:");
                PKKPerakuanPT.setInfoAudit(setInfoAudit());
                savePermohonanKertasKandungan(PKKPerakuanPT);

                for (int i = 0; i < PerakuanPT.size(); i++) {

                    PKKPerakuanPT = new PermohonanKertasKandungan();
                    PKKPerakuanPT.setKertas(kertas);
                    PKKPerakuanPT.setCawangan(permohonan.getCawangan());
                    PKKPerakuanPT.setBil(61);
                    PKKPerakuanPT.setSubtajuk("6.1." + (i + 1));
                    PKKPerakuanPT.setKandungan(PerakuanPT.get(i).getKeteranganMMK());
                    PKKPerakuanPT.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(PKKPerakuanPT);
                }
            }
            List<TBLINTPPTDERAFMMK> PerakuanPTG = findMMKdrafKand(e.getNoFail(), "ULASANPENGARAH");
            if (PerakuanPTG != null) {

                for (int i = 0; i < PerakuanPTG.size(); i++) {

                    PermohonanKertasKandungan PKKPerakuanPTG = new PermohonanKertasKandungan();
                    PKKPerakuanPTG.setKertas(kertas);
                    PKKPerakuanPTG.setCawangan(permohonan.getCawangan());
                    PKKPerakuanPTG.setBil(7);
                    PKKPerakuanPTG.setSubtajuk("7." + (i + 1));
                    PKKPerakuanPTG.setKandungan(PerakuanPTG.get(i).getKeteranganMMK());
                    PKKPerakuanPTG.setInfoAudit(setInfoAudit());
                    savePermohonanKertasKandungan(PKKPerakuanPTG);
                }
            }
//                }
//            }

        }
        //////5.end class PermohonanKertasButiran //////
    }  //end setPermohonanKertasKandungan

    public void setDokumen(TBLINTPPTDOKUMEN tblintpptdokumen, byte[] b, String content) throws Exception { //start setDokumen
        //////6.start class Dokumen //////
        EtappPermohonan e = findByNofail(tblintpptdokumen.getNoFailJKPTG());
        KandunganFolder kf = new KandunganFolder();

        Dokumen doc = new Dokumen();
        KodDokumen kodd = kodDokumenDAO.findById(tblintpptdokumen.getJenisFail());
        if (kodd != null) {
        } else {
            kodd = kodDokumenDAO.findById("ETP");
        }
        // final InputStream inn = data.getInputStream();
        // byte[] byteArray = org.apache.commons.io.IOUtils.toByteArray(inn);
        DMSUtil dmsUtil = new DMSUtil();
        byte[] bb = Base64.decodeToBytes(content);
        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(e.getMohon()));
        ByteArrayInputStream in = new ByteArrayInputStream(bb);
        fileUtil.saveFile(tblintpptdokumen.getNamaDokumen(), in);
        String fizikalPath = dmsUtil.getFizikalPath(e.getMohon()) + File.separator + tblintpptdokumen.getNamaDokumen();
        doc.setKodDokumen(kodd);  //column KOD_DOKUMEN
        doc.setTajuk(tblintpptdokumen.getTajukDokumen());
        doc.setNoRujukan(null);  //column NO_RUJ tak jumpe la method dia
        doc.setNamaFizikal(fizikalPath);
        doc.setFormat(tblintpptdokumen.getJenisFormatFail());
        doc.setPerihal(tblintpptdokumen.getKandungan());
        doc.setInfoAudit(setInfoAudit());
        doc.setNoVersi("0");
        kf.setFolder(e.getMohon().getFolderDokumen());
        kf.setDokumen(doc);
        kf.setInfoAudit(setInfoAudit());
        kf = saveKandunganFolder(kf);
        doc = saveDokumen(doc);
        //////6.end class Dokumen //////
    } //end setDokumen

    //start class pemohon
    public void setPemohon(String noFail) {

        TBLINTPPTMAKLUMATPENGAMBILAN tblintpptmaklumatpengambilan = findTBLINTPPTMAKLUMATPENGAMBILAN(noFail);
        EtappPermohonan e = findByNofail(noFail);

        Pemohon pemohon = pengambilanService.findPemohon(e.getMohon().getIdPermohonan());
        if (pemohon != null) {
            //pemohon ad da...
        } else {
            pemohon = new Pemohon();  //class model Pemohon

            pemohon.setPermohonan(e.getMohon());
            pemohon.setCawangan(e.getMohon().getCawangan());

            Pihak pihak = pengambilanService.findPihak(tblintpptmaklumatpengambilan.getNamaKementerian());
            if (pihak != null) {

                Pihak phk = pihakDAO.findById(pihak.getIdPihak());
                pemohon.setPihak(phk);

            } else {

                Pihak pihak2 = new Pihak();
                KodJenisPengenalan kjp = kodJenisPengenalanDAO.findById("X");
                pihak2.setJenisPengenalan(kjp);
                pihak2.setNama(tblintpptmaklumatpengambilan.getNamaKementerian());
                pihak2.setAlamat1(tblintpptmaklumatpengambilan.getAlamat1());
                pihak2.setAlamat2(tblintpptmaklumatpengambilan.getAlamat2());
                pihak2.setAlamat3(tblintpptmaklumatpengambilan.getAlamat3());
                pihak2.setAlamat4(tblintpptmaklumatpengambilan.getAlamat4());
                pihak2.setPoskod(tblintpptmaklumatpengambilan.getPoskod());
                KodNegeri negeri = kodNegeriDAO.findById(tblintpptmaklumatpengambilan.getKodNegeri());
                pihak2.setNegeri(negeri);
                pihak2.setEmail(tblintpptmaklumatpengambilan.getEmail());
                pihak2.setNoTelefon1(tblintpptmaklumatpengambilan.getNoTel());
                pihak2.setInfoAudit(setInfoAudit());
                savePihak(pihak);

//            Pihak newpihak = pengambilanService.findPihak(tblintpptmaklumatpengambilan.getNamaKementerian());
                Pihak phk2 = pihakDAO.findById(pihak.getIdPihak());
                pemohon.setPihak(phk2);
            }

            pemohon.setInfoAudit(setInfoAudit());
            savePemohon(pemohon);
        }
    }

    public InfoAudit setInfoAudit() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = penggunaDAO.findById("portal");
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        return ia;
    }

    public InfoAudit KemaskiniInfoAudit() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = penggunaDAO.findById("portal");
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new Date());

        return ia;
    }

    @Transactional
    public Permohonan savePermohonan(Permohonan mohon) {
        return permohonanDAO.saveOrUpdate(mohon);
    }

    @Transactional
    public EtappPermohonan saveEtappPermohonan(EtappPermohonan e) {
        return etappPermohonanDAO.saveOrUpdate(e);
    }

    public EtappPermohonan findByNofail(String noFail) {
        String query = "select p from etanah.model.etapp.EtappPermohonan p where p.noFail =:noFail";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        return (EtappPermohonan) q.uniqueResult();
    }

    @Transactional
    public HakmilikPermohonan saveMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        return hakmilikPermohonanDAO.saveOrUpdate(mohonHakmilik);
    }

    @Transactional
    public Dokumen saveDokumen(Dokumen d) {
        return dokumenDAO.saveOrUpdate(d);
    }

    @Transactional
    public PermohonanKertasKandungan savePermohonanKertasKandungan(PermohonanKertasKandungan pk) {
        return permohonanKertasKandunganDAO.saveOrUpdate(pk);
    }

    @Transactional
    public PermohonanKertas savePermohonanKertas(PermohonanKertas kertas) {
        return permohonanKertasDAO.saveOrUpdate(kertas);
    }

    @Transactional
    public PermohonanRujukanLuar savePermohonanRujukanLuar(PermohonanRujukanLuar prl) {
        return permohonanRujukanLuarDAO.saveOrUpdate(prl);
    }

    private TBLPPTINTDERAFMMKTAJUK findTBLPPTINTDERAFMMKTAJUK(String noFail) {
        String query = "select p from etanah.model.etapp.TBLPPTINTDERAFMMKTAJUK p where p.noFailJKPTG =:noFail";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        return (TBLPPTINTDERAFMMKTAJUK) q.uniqueResult();
    }

    private PermohonanKertas findByIDMohon(String idPermohonan) {
        String query = "select p from etanah.model.PermohonanKertas p where p.permohonan.idPermohonan =:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    private TBLINTPPTWARTA findTBLINTPPTWARTA(String noFail) {
        String query = "select p from etanah.model.etapp.TBLINTPPTWARTA p where p.noFailJKPTG =:noFail";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        return (TBLINTPPTWARTA) q.uniqueResult();
    }

    private TBLINTPPTMAKLUMATPENGAMBILAN findTBLINTPPTMAKLUMATPENGAMBILAN(String noFail) {
        String query = "select p from etanah.model.etapp.TBLINTPPTMAKLUMATPENGAMBILAN p where p.noFailJKPTG =:noFail";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        return (TBLINTPPTMAKLUMATPENGAMBILAN) q.uniqueResult();
    }

    private List<TBLINTPPTDERAFMMK> findtblintderafmmk(String noFail) {
        String query = "select p from etanah.model.etapp.TBLINTPPTDERAFMMK p where p.noFailJKPTG =:noFail";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        return q.list();
    }

    @Transactional
    private FolderDokumen setForlderDokumen(String tajuk) {
        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk(tajuk);
        fd.setInfoAudit(setInfoAudit());
        return folderDokumenDAO.saveOrUpdate(fd);
    }

    @Transactional
    private KandunganFolder saveKandunganFolder(KandunganFolder kf) {
        return kandunganFolderDAO.saveOrUpdate(kf);
    }

    @Transactional
    private Pemohon savePemohon(Pemohon p) {

        return pemohonDAO.saveOrUpdate(p);
    }

    @Transactional
    private Pihak savePihak(Pihak pi) {
        return pihakDAO.saveOrUpdate(pi);
    }

    EtappPermohonan findByidMohon(String idPermohonan) {
        String query = "select p from etanah.model.etapp.EtappPermohonan p where p.mohon.idPermohonan =:idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("idPermohonan", idPermohonan);
        return (EtappPermohonan) q.uniqueResult();
    }

    public void initiateREG(String no_fail_jkptg, String flag) throws Exception {
        EtappPermohonan tp = findByNofail(no_fail_jkptg);
        String kodUrusan = null;
        if (flag.equals("WartaS8")) {
            kodUrusan = "ABT-D";
        } else if (flag.equals("BorangK")) {
            kodUrusan = "ABTKB";
        }
        KodNegeri kN = kodNegeriDAO.findById("17");
        KodUrusan kodUrusan1 = kodUrusanDAO.findById(kodUrusan);
        KodCawangan caw = kodCawanganDAO.findById("01");
        Permohonan mohon = new Permohonan();//class model Permohonan
        mohon.setIdPermohonan(idGenerator.generate(conf.getKodNegeri(), caw, kodUrusan1));
        mohon.setPermohonanSebelum(tp.getMohon());
        mohon.setPenyerahNoPengenalan("X");
        mohon.setPenyerahNama("MyeTapp");
        mohon.setPenyerahAlamat1(null);
        mohon.setPenyerahAlamat2(null);
        mohon.setPenyerahAlamat3(null);
        mohon.setPenyerahAlamat4(null);
        mohon.setPenyerahPoskod(null);
        mohon.setPenyerahNegeri(null);
        mohon.setPenyerahNoTelefon1(null);
        mohon.setSebab("");  //column SBB
        mohon.setInfoAudit(setInfoAudit());
        mohon.setPenyerahNoPengenalan("X");
        mohon.setPenyerahNegeri(kN);
        mohon.setIdWorkflow(kodUrusan1.getIdWorkflow());
        mohon.setKodUrusan(kodUrusan1);
        mohon.setCawangan(caw);
        mohon.setFolderDokumen(setForlderDokumen("MyEtapp-" + no_fail_jkptg));
        String kodCaw = caw.getKod() + ",00";
        WorkFlowService.initiateTask(mohon.getKodUrusan().getIdWorkflow(),
                mohon.getIdPermohonan(), kodCaw, setInfoAudit().getDimasukOleh().getIdPengguna(),
                mohon.getKodUrusan().getNama());

        mohon = savePermohonan(mohon);
        for (HakmilikPermohonan hpl : mohon.getPermohonanSebelum().getSenaraiHakmilik()) {
            HakmilikPermohonan hak = new HakmilikPermohonan();
            hak.setPermohonan(mohon);
            hak.setHakmilik(hpl.getHakmilik());
            hak.setLuasTerlibat(hpl.getLuasTerlibat());   //column LUAS

            hak.setKodUnitLuas(hpl.getKodUnitLuas());   //column KOD_UOM

            hak.setKodHakmilik(hpl.getKodHakmilik());  //column KOD_HAKMILIK
            hak.setInfoAudit(setInfoAudit());
            hak.setNoLot(hpl.getNoLot());
            hak = saveMohonHakmilik(hak);
        }
        initiateService.setMohonRujukanLuar(mohon, no_fail_jkptg, "FL");

    }

    List<KandunganFolder> findDokByKodDokumen(long folderId, String kod) {
        String query = "select p from etanah.model.KandunganFolder p where p.folder.folderId =:folderId and p.dokumen.kodDokumen.kod=:kod";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setLong("folderId", folderId);
        q.setString("kod", kod);
        return q.list();
    }

    @Transactional
    public AgensiHakmilik saveAgensiHakmilik(AgensiHakmilik ah) {
        return agensiHakmilikDAO.saveOrUpdate(ah);
    }

    public List<TBLINTPPTDERAFMMK> findMMKdrafKand(String noFail, String item) {
        String query = "SELECT b FROM etanah.model.etapp.TBLINTPPTDERAFMMK b where b.noFailJKPTG = :noFail and b.jenisMaklumatMMK= :item";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noFail", noFail);
        q.setString("item", item);
        return q.list();
    }

    public List<TBLINTPPTDERAFMMK> findMMKdrafKand1(String noFail, String item) {
        String query = "SELECT b FROM etanah.model.etapp.TBLINTPPTDERAFMMK b where b.noFailJKPTG = :noFail and b.jenisMaklumatMMK like :item";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noFail", noFail);
        q.setString("item", "%" + item + "%");
        return q.list();
    }

    HakmilikUrusan FindTarikhEndorsan(String idPermohonan, String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select hu from etanah.model.HakmilikUrusan hu where hu.idPerserahan =:idPermohonan and hu.hakmilik.idHakmilik =:idHakmilik");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikUrusan) q.uniqueResult();
    }

    FolderDokumen FindNoJilid(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select fd from etanah.model.FolderDokumen fd where fd.tajuk = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (FolderDokumen) q.uniqueResult();
    }

    List<FolderDokumen> FindListNoJilid(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select fd from etanah.model.FolderDokumen fd where fd.tajuk = :idPermohonan order by fd.folderId desc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    FasaPermohonan findMohonFasaByIdMohonIdPengguna(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public FasaPermohonan findFasaPermohonanKedesakan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '19MaklumEndBrgD'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    FasaPermohonan findMohonFasaByIdMohonIdPengguna2(String idPermohonan, String idAliranMhonFasa) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan and fp.idAliran = :idAliranMhonFasa");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliranMhonFasa", idAliranMhonFasa);
        return (FasaPermohonan) q.uniqueResult();
    }

    TBLINTPPTMAKLUMATPENGAMBILAN FindMaklumatPengambilan(String noFail) {
        String query = "select p from etanah.model.etapp.TBLINTPPTMAKLUMATPENGAMBILAN p where p.noFailJKPTG =:noFail";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("noFail", noFail);
        return (TBLINTPPTMAKLUMATPENGAMBILAN) q.uniqueResult();
    }

    @Transactional
    public FasaPermohonan saveMohonFasa(FasaPermohonan fp) {
        return fasaPermohonanDAO.saveOrUpdate(fp);
    }

    public List<AgensiHakmilik> findAgensiById(String idHakmilik) {
        String query = "select p from etanah.model.etapp.AgensiHakmilik p where p.hakmilik.idHakmilik =:idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("idHakmilik", idHakmilik);
//        return (AgensiHakmilik) q.uniqueResult();
        return q.list();
    }

    public AgensiHakmilik findAktifAgensiById(String idHakmilik) {
        String query = "select p from etanah.model.etapp.AgensiHakmilik p where p.hakmilik.idHakmilik =:idHakmilik and p.aktif = 'Y' ";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("idHakmilik", idHakmilik);
        return (AgensiHakmilik) q.uniqueResult();
    }

    public List<HakmilikSebelum> findNewHakByHakSblm(String idHakmilik) {
        String query = "select p from etanah.model.HakmilikSebelum p where p.hakmilikSebelum =:idHakmilik ";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<DokumenKewangan> findDokKew(String akaun) {
        String query = "select p from etanah.model.DokumenKewangan p where p.akaun =:akaun ";
        Query q = sessionProvider.get().createQuery(query);
        // Query q = s.createQuery("from etanah.model.LogPenggunaApplikasi p where p.idPguna =:idPguna order by p.idPgunaAppLog asc");
        q.setString("akaun", akaun);
        return q.list();
    }

    public List<EtappLog> findLog(String item) {

        String query = "Select p FROM etanah.model.etapp.EtappLog p WHERE p.urusan LIKE :item Order By p.id_etapp_log DESC";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("item", "%" + item + "%");
        return (List<EtappLog>) q.list();
    }
}   //end class PengambilanEtanahService

