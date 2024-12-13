/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws;

import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.DokumenDAO;
import etanah.dao.EtappHakmilikSambunganDAO;
import etanah.dao.EtappPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.InfoWartaDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPengambilanHakmilikDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodPenyerah;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.TugasanUtiliti;
import etanah.model.ambil.InfoWarta;
import etanah.model.ambil.PermohonanEtapp;
import etanah.model.ambil.PermohonanEtappHakmilik;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.model.etapp.EtappHakmilikSambungan;
import etanah.model.etapp.EtappPermohonan;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.service.ambil.MyEtappService;
import etanah.view.etanahContextListener;
import etanah.view.etapp.ws.form.HakmilikPermohonanMyEtaPP;
import etanah.view.etapp.ws.form.LampiranMyEtaPP;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek4MyEtaPP;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek8MyEtaPP;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
public class MyEtappPengambilanServices {

    private static Injector injector = etanahContextListener.getInjector();
    DokumenDAO dokumenDAO = injector.getInstance(DokumenDAO.class);
    KodDokumenDAO kodDokumenDAO = injector.getInstance(KodDokumenDAO.class);
    KandunganFolderDAO kandunganFolderDAO = injector.getInstance(KandunganFolderDAO.class);
    HakmilikPermohonanDAO hakmilikPermohonanDAO = injector.getInstance(HakmilikPermohonanDAO.class);
    KodUrusanDAO kodUrusanDAO = injector.getInstance(KodUrusanDAO.class);
    FolderDokumenDAO folderDokumenDAO = injector.getInstance(FolderDokumenDAO.class);
    HakmilikDAO hakmilikDAO = injector.getInstance(HakmilikDAO.class);
    PermohonanDAO permohonanDAO = injector.getInstance(PermohonanDAO.class);
    KodNegeriDAO kodNegeriDAO = injector.getInstance(KodNegeriDAO.class);
    KodPenyerahDAO kodPenyerahDAO = injector.getInstance(KodPenyerahDAO.class);
    PenggunaDAO penggunaDAO = injector.getInstance(PenggunaDAO.class);
    KodCawanganDAO kodCawanganDAO = injector.getInstance(KodCawanganDAO.class);
    MyEtappService myEtappService = injector.getInstance(MyEtappService.class);
    InfoWartaDAO infoWartaDAO = injector.getInstance(InfoWartaDAO.class);
    KodDaerahDAO kodDaerahDAO = injector.getInstance(KodDaerahDAO.class);
    EtappPermohonanDAO etappPermohonanDAO = injector.getInstance(EtappPermohonanDAO.class);
    KodUOMDAO kodUOMDAO = injector.getInstance(KodUOMDAO.class);
    PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO = injector.getInstance(PermohonanPengambilanHakmilikDAO.class);
    EtappHakmilikSambunganDAO etappHakmilikSambunganDAO = injector.getInstance(EtappHakmilikSambunganDAO.class);
    MyeTappIntegrationFlowService myeTappIntegrationFlowService = injector.getInstance(MyeTappIntegrationFlowService.class);
    InfoAudit infoAudit = new InfoAudit();
    private GeneratorIdPermohonan idGenerator = injector.getInstance(GeneratorIdPermohonan.class);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public String daftarPermohonanSek4BorangA(MaklumatPermohonanSek4MyEtaPP maklumatPermohonanSek4Form, List<HakmilikPermohonanMyEtaPP> listHakmilikPermohonanMyEtaPPForm, LampiranMyEtaPP mmkPaper, List<LampiranMyEtaPP> attachment) throws ParseException, IOException {
        RefPeringkat ref = new RefPeringkat();
        KodCawangan caw = kodCawanganDAO.findById(maklumatPermohonanSek4Form.getKod_daerah_pengambilanEtaPP());
        Permohonan mohon = createMohon(maklumatPermohonanSek4Form, caw);
        createMohonHakmilik(mohon.getIdPermohonan(), listHakmilikPermohonanMyEtaPPForm);
        createFolderDok(mohon.getIdPermohonan(), attachment);
        createDokumen(mmkPaper, mohon.getIdPermohonan());
        EtappPermohonan etapp = myEtappService.saveEtapp(maklumatPermohonanSek4Form, mohon);

        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
//            mohonEtapp.setPermohonan(mohon);
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }
        mohonEtapp.setNoFailJkptg(maklumatPermohonanSek4Form.getNo_fail_jkptgEtaPP());
        mohonEtapp.setTrhMohon(sdf.parse(maklumatPermohonanSek4Form.getTarikh_permohonanEtaPP()));
        myEtappService.savePermohonanEtapp(mohonEtapp);
        myeTappIntegrationFlowService.completeTask(ref.SEK4_DAFTAR_BA_MMK, mohon, mohon.getInfoAudit().getDimasukOleh(), etapp.getDaerah());
        return mohon.getIdPermohonan();
    }

    public void maklumatWartaBorangB(String idPermohonan, String tarikhWarta, String noWarta, List<LampiranMyEtaPP> attachment) throws ParseException, IOException {
        RefPeringkat ref = new RefPeringkat();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        InfoWarta warta = myEtappService.saveInfoWarta(noWarta, tarikhWarta, mohon, ref.SEK4_TERIMA_WARTA);
        saveInfoWarta(warta);
        createFolderDok(idPermohonan, attachment);
        EtappPermohonan etapp = myEtappService.findByidPermohonan(mohon.getIdPermohonan());
        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }
        mohonEtapp.setNoWartaA(noWarta);
        mohonEtapp.setTrhWartaA(sdf.parse(tarikhWarta));
        myEtappService.savePermohonanEtapp(mohonEtapp);
        myeTappIntegrationFlowService.completeTask(ref.SEK4_TERIMA_WARTA, mohon, mohon.getInfoAudit().getDimasukOleh(), etapp.getDaerah());
    }

    public String daftarPermohonanSek8(MaklumatPermohonanSek8MyEtaPP maklumatPermohonan, List<HakmilikPermohonanMyEtaPP> listHakmilikPermohonanMyEtaPPForm, LampiranMyEtaPP digitalcharting, List<LampiranMyEtaPP> attachment) throws ParseException {
        RefPeringkat ref = new RefPeringkat();
        KodCawangan caw = kodCawanganDAO.findById(maklumatPermohonan.getKod_daerah_pengambilanEtaPP());
        Permohonan mohon = createMohonSek8(maklumatPermohonan, caw);
        createMohonHakmilik(mohon.getIdPermohonan(), listHakmilikPermohonanMyEtaPPForm);
        createFolderDok(mohon.getIdPermohonan(), attachment);
        createDokumen(digitalcharting, mohon.getIdPermohonan());

        EtappPermohonan etapp = myEtappService.saveEtappSek8(maklumatPermohonan, mohon);
        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }
        mohonEtapp.setNoFailJkptg(maklumatPermohonan.getNo_fail_jkptgEtaPP());
        mohonEtapp.setTrhMohon(sdf.parse(maklumatPermohonan.getTarikh_permohonanEtaPP()));
        myEtappService.savePermohonanEtapp(mohonEtapp);

        return mohon.getIdPermohonan();
    }

    public void borangCdanMMK(String idPermohonan, LampiranMyEtaPP mmkPaper, List<LampiranMyEtaPP> attachment) throws IOException {
        RefPeringkat ref = new RefPeringkat();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        createDokumen(mmkPaper, idPermohonan);
        createFolderDok(idPermohonan, attachment);
        EtappPermohonan etapp = myEtappService.findByidPermohonan(mohon.getIdPermohonan());
        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }

        myeTappIntegrationFlowService.completeTask(ref.SEK8_DAFTAR_BORANGC, mohon, mohon.getInfoAudit().getDimasukOleh(), etapp.getDaerah());
    }

    public void endorsBorangDMaklumatWarta(String idPermohonan, String tarikhWarta, String noWarta, List<LampiranMyEtaPP> attachment) throws ParseException, IOException {
        RefPeringkat ref = new RefPeringkat();
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        InfoWarta warta = myEtappService.saveInfoWarta(noWarta, tarikhWarta, mohon, ref.SEK8_ENDORS_BD);
        saveInfoWarta(warta);
        createFolderDok(idPermohonan, attachment);
        EtappPermohonan etapp = myEtappService.findByidPermohonan(mohon.getIdPermohonan());
        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }
        mohonEtapp.setNoWartaC(noWarta);
        mohonEtapp.setTrhWartaC(sdf.parse(tarikhWarta));
        myEtappService.savePermohonanEtapp(mohonEtapp);
        myeTappIntegrationFlowService.completeTask(ref.SEK8_ENDORS_BD, mohon, mohon.getInfoAudit().getDimasukOleh(), etapp.getDaerah());
    }

    public void endorsBorangK(String idPermohonan, String idHakmilik, String tarikhWarta, String noWarta, List<LampiranMyEtaPP> attchment) throws ParseException, IOException {
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        RefPeringkat ref = new RefPeringkat();
        EtappPermohonan etapp = myEtappService.findByidPermohonan(mohon.getIdPermohonan());

        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }
        mohonEtapp.setNoWartaC(noWarta);
        mohonEtapp.setTrhWartaC(sdf.parse(tarikhWarta));
        myEtappService.savePermohonanEtapp(mohonEtapp);
        PermohonanEtappHakmilik mohonEtappHakmilik = new PermohonanEtappHakmilik();
        mohonEtappHakmilik.setIdHakmilik(idHakmilik);
        mohonEtappHakmilik.setIdPermohonan(idPermohonan);
        myEtappService.savePermohonanEtappHakmilk(mohonEtappHakmilik);
        myeTappIntegrationFlowService.completeTask(ref.SEK8_ENDORS_BK, mohon, mohon.getInfoAudit().getDimasukOleh(), etapp.getDaerah());

    }

    public void sijilPembebasanUkur(String idPermohonan, String tarikh, String noPU, List<LampiranMyEtaPP> attchment) throws ParseException, IOException {
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        RefPeringkat ref = new RefPeringkat();
        EtappPermohonan etapp = myEtappService.findByidPermohonan(mohon.getIdPermohonan());

        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }
        mohonEtapp.setTrhPu(sdf.parse(tarikh));
        mohonEtapp.setNoPu(noPU);
        myEtappService.savePermohonanEtapp(mohonEtapp);
        myeTappIntegrationFlowService.completeTask(ref.SEK8_SPU, mohon, mohon.getInfoAudit().getDimasukOleh(), etapp.getDaerah());

    }

    public void maklumanHantarPU(String idPermohonan, String tarikhHantar, String noRujukan, List<LampiranMyEtaPP> attchment) throws ParseException, IOException {
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        RefPeringkat ref = new RefPeringkat();
        EtappPermohonan etapp = myEtappService.findByidPermohonan(mohon.getIdPermohonan());

        PermohonanEtapp mohonEtapp = myEtappService.findMohonEtappByidPermohonan(mohon.getIdPermohonan());
        if (mohonEtapp != null) {
        } else {
            mohonEtapp = new PermohonanEtapp();
            mohonEtapp.setIdPermohonan(mohon.getIdPermohonan());
            mohonEtapp.setInfoAudit(setIA());
        }
        mohonEtapp.setTrhHantarPu(sdf.parse(tarikhHantar));
        mohonEtapp.setNoRujukanPU(noRujukan);
        myEtappService.savePermohonanEtapp(mohonEtapp);
        myeTappIntegrationFlowService.completeTask(ref.SEK8_MHPU, mohon, mohon.getInfoAudit().getDimasukOleh(), etapp.getDaerah());

    }

    private Permohonan createMohon(MaklumatPermohonanSek4MyEtaPP maklumatPermohonanSek4Form, KodCawangan caw) {
        Permohonan mohon = new Permohonan();
        KodUrusan kodurusan = kodUrusanDAO.findById("ESK4");
//        kodurusan.setKod("ESK4");
        String idPermohonan = idGenerator.generate("04", caw, kodurusan);
        mohon.setCawangan(caw);
        mohon.setKodUrusan(kodurusan);
        mohon.setIdPermohonan(idPermohonan);
        mohon.setPenyerahNama("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat1("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat2("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat3("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat4("Integrasi Sistem MyEtapp");
        mohon.setPenyerahPoskod("");
//        mohon.setPenyerahNegeri(kodNegeriDAO.findById(kodNegeriP));
        KodPenyerah kodP = kodPenyerahDAO.findById("09");
        mohon.setKodPenyerah(kodP);
        mohon.setInfoAudit(setIA());

        FolderDokumen fd = new FolderDokumen();
        fd.setInfoAudit(setIA());
        fd.setTajuk(idPermohonan);
        saveFolder(fd);
        mohon.setFolderDokumen(fd);
        saveMohon(mohon);

        return mohon;
    }

    public InfoAudit setIA() {
//        InfoAudit ia = new InfoAudit();
        Pengguna p = penggunaDAO.findById("portal");
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(p);
        return infoAudit;
    }

    @Transactional
    private void saveFolder(FolderDokumen fd) {
        folderDokumenDAO.save(fd);
    }

    @Transactional
    private void saveMohon(Permohonan mohon) {
        permohonanDAO.save(mohon);
    }

    private void createMohonHakmilik(String idPermohonan, List<HakmilikPermohonanMyEtaPP> listHakmilikPermohonanMyEtaPPForm) {
        for (HakmilikPermohonanMyEtaPP p : listHakmilikPermohonanMyEtaPPForm) {
            etanah.model.Hakmilik hakmilik = hakmilikDAO.findById(p.getId_hakmilikEtaPP());
            if (hakmilik != null) {
                KodUOM kodUomAmbil = null;
                HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                if (StringUtils.isNotEmpty(p.getKod_luas_ambilEtaPP())) {
                    KodUOM kodUomAsal = myEtappService.findByKodUOMMyEtapp(p.getKod_luas_ambilEtaPP());
                }
                if (StringUtils.isNotEmpty(p.getKod_luas_ambilEtaPP())) {
                    kodUomAmbil = myEtappService.findByKodUOMMyEtapp(p.getKod_luas_ambilEtaPP());
                    mohonHakmilik.setKodUnitLuas(kodUomAmbil != null ? kodUomAmbil : null);

                }
                mohonHakmilik.setHakmilik(hakmilik);
                mohonHakmilik.setCawangan(permohonanDAO.findById(idPermohonan).getCawangan());
                mohonHakmilik.setHakmilik(hakmilik);
                mohonHakmilik.setPermohonan(permohonanDAO.findById(idPermohonan));
                mohonHakmilik.setHakmilikAmbil("A");
                if (StringUtils.isNotEmpty(p.getLuas_ambilEtaPP())) {
                    mohonHakmilik.setLuasTerlibat(new BigDecimal(p.getLuas_ambilEtaPP()));
                }
//                mohonHakmilik.setNoLot(noLotBaru);
                mohonHakmilik.setInfoAudit(setIA());
                saveMohonHakmilik(mohonHakmilik);
                PermohonanPengambilanHakmilik pp = new PermohonanPengambilanHakmilik();
                pp.setHakmilikPermohonan(mohonHakmilik);
                pp.setInfoAudit(setIA());
                pp.setKodUnitLuas(kodUomAmbil != null ? kodUomAmbil : null);
                if (StringUtils.isNotEmpty(p.getLuas_ambilEtaPP())) {
                    pp.setLuasAmbil(new BigDecimal(p.getLuas_ambilEtaPP()));
                }
                savePermohonanPengambilanHakmilik(pp);
            }
        }
    }

    @Transactional
    private void saveInfoWarta(InfoWarta warta) {
        infoWartaDAO.saveOrUpdate(warta);
    }

    @Transactional
    private void saveMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        hakmilikPermohonanDAO.save(mohonHakmilik);
    }

    private void createFolderDok(String idPermohonan, List<LampiranMyEtaPP> attachment) {
        for (LampiranMyEtaPP a : attachment) {
            Dokumen dokumen = createDokumen(a, idPermohonan);
        }
    }

    private Dokumen createDokumen(LampiranMyEtaPP a, String idPermohonan) {
        Dokumen dokumen = myEtappService.uploadDokumen(a.getKodDokumenEtaPP(), a.getFilenameEtaPP(), a.getBytesEtaPP(), a.getFilenameEtaPP(), a.getDocTypeEtaPP(), "", idPermohonan);
        return dokumen;
    }

    private Permohonan createMohonSek8(MaklumatPermohonanSek8MyEtaPP maklumatPermohonan, KodCawangan kodcaw) {
        Permohonan mohon = new Permohonan();
        KodUrusan kodurusan = kodUrusanDAO.findById("ESK8");
//        kodurusan.setKod("ESK4");
        String idPermohonan = idGenerator.generate("04", kodcaw, kodurusan);
        mohon.setCawangan(kodcaw);
        mohon.setKodUrusan(kodurusan);
        mohon.setIdPermohonan(idPermohonan);
        mohon.setPenyerahNama("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat1("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat2("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat3("Integrasi Sistem MyEtapp");
        mohon.setPenyerahAlamat4("Integrasi Sistem MyEtapp");
        mohon.setPenyerahPoskod("");
//        mohon.setPenyerahNegeri(kodNegeriDAO.findById(kodNegeriP));
        KodPenyerah kodP = kodPenyerahDAO.findById("09");
        mohon.setKodPenyerah(kodP);
        mohon.setInfoAudit(setIA());
        FolderDokumen fd = new FolderDokumen();
        fd.setInfoAudit(setIA());
        fd.setTajuk(idPermohonan);
        saveFolder(fd);
        mohon.setFolderDokumen(fd);
        saveMohon(mohon);

        return mohon;
    }

    @Transactional
    private void savePermohonanPengambilanHakmilik(PermohonanPengambilanHakmilik pp) {
        permohonanPengambilanHakmilikDAO.saveOrUpdate(pp);
    }

    public void hakmilikSambunganEtapp(String idPermohonan, String idHakmilik, List<HakmilikSambunganFormEtapp> senaraiHakmilikSambungan) throws Exception {
        etanah.model.Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
                       EtappPermohonan etapp = myEtappService.findByidPermohonan(permohonan.getIdPermohonan());

        NumberFormat df = NumberFormat.getInstance();
        df.setMaximumIntegerDigits(7);
        df.setMinimumIntegerDigits(7);
        df.setGroupingUsed(false);
        for (HakmilikSambunganFormEtapp h : senaraiHakmilikSambungan) {
            EtappHakmilikSambungan etappHakmilikSambungan = new EtappHakmilikSambungan();
            KodUOM kodLuas = kodUOMDAO.findById(h.getKodLuas());
            etappHakmilikSambungan.setHakmilik(hakmilik);
            etappHakmilikSambungan.setPermohonan(permohonan);
            etappHakmilikSambungan.setKodLuas(kodLuas);
            etappHakmilikSambungan.setLuas(new BigDecimal(h.getLuas()));
            etappHakmilikSambungan.setNoLot(h.getNoLot());
            etappHakmilikSambungan.setNoPelan(h.getNoPelanPA());
            Integer a = Integer.parseInt(h.getNoLot());
            String kodseksyen = hakmilik.getSeksyen() != null ? hakmilik.getSeksyen().getSeksyen() : "000";
            String filename = "04" + hakmilik.getDaerah().getKod() + hakmilik.getBandarPekanMukim().getbandarPekanMukim() + kodseksyen + df.format(Integer.valueOf(h.getNoLot()));
            String path = myEtappService.simpanPelan(permohonan.getIdPermohonan(), h.getPelan(), h.getMimeType(), filename);
            etappHakmilikSambungan.setPelan(path);
            saveEtappHakmilikSambungan(etappHakmilikSambungan);
        }

//        myeTappIntegrationFlowService.deleteTugasanTable(permohonan);
        RefPeringkat ref = new RefPeringkat();
//        myeTappIntegrationFlowService.insertTugasanTable(permohonan, ref.SEK8_TERIMA_HS, "portal");
                myeTappIntegrationFlowService.completeTask(ref.SEK8_TERIMA_HS, permohonan, permohonan.getInfoAudit().getDimasukOleh(), etapp.getDaerah());

    }

    @Transactional
    private void saveEtappHakmilikSambungan(EtappHakmilikSambungan etappHakmilikSambungan) {
        etappHakmilikSambunganDAO.save(etappHakmilikSambungan);
    }

}
