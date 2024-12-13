/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

//import com.google.inject.Inject;
import etanah.view.pembangunan.validator.*;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.model.*;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.dao.*;
import etanah.service.*;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.HakmilikService;
import etanah.view.strata.CommonService;
import etanah.report.ReportUtil;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import org.hibernate.Session;

public class CompletePBBValidator implements StageListener {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    private AkaunDAO akaunDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodAkaunDAO kodAkaunDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    HakmilikPermohonan hp;
    @Inject
    HakmilikService hakmilikServ;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    Pengguna pengguna;
    FolderDokumen fd;
    private static final Logger LOG = Logger.getLogger(CompletePBBValidator.class);
    private String idHakmilik;
    private Hakmilik hakmilik;
    private Hakmilik hakmilikSTRATA;
    private Hakmilik hakmilikSTRATA2;
    private HakmilikPermohonan hakmilikSTRATA3;
    private Hakmilik NoBukuSTRATA;
    private Hakmilik hakmilikDaerahMukim;
    private Hakmilik hakmilikNoBukuSTRATA;
    private Hakmilik highestNoBukuStrata;
    private KodHakmilik kodHakmilik;
    private List<Hakmilik> listHighestNoBukuSTRATA = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilikDaerahMukim = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiHakmilikSTRATA = new ArrayList<Hakmilik>();
    private List<Hakmilik> nobukup = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiBukuSTRATA;
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    private PermohonanRujukanLuar permohonanRujLuar;
    private List<PermohonanBangunan> pBangunanProvisionalBlock;
    private String stage;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t1 = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        
        if (mohon.getKodUrusan().getKod().equals("PHPC") || mohon.getKodUrusan().getKod().equals("PHPP")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
        } else {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
        }
        
        if (mohonFasa.getKeputusan().getKod().equals("L")) {
            String dokumenPath = conf.getProperty("document.path");
            Dokumen e = null;
            Dokumen f = null;
            Dokumen g = null;
            Dokumen h = null;
            Dokumen i = null;
            Dokumen j = null;
            Dokumen k = null;
            Dokumen b = null;
            Dokumen t = null;
            String idFolder = "";
            Permohonan permohonan = context.getPermohonan();
            String idPermohonan = permohonan.getIdPermohonan();

            idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
            FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
            String[] params = null;
            String[] values = null;
            String[] params2 = null;
            String[] values2 = null;
            String[] values3 = null;
            String[] value = null;
            String path2 = "";
            String path3 = "";
            String path4 = "";
            String path5 = "";
            String path6 = "";
            String path8 = "";
            String path10 = "";
            String path11 = "";
            String path12 = "";
            String path13 = "";
            String path15 = "";
            String gen2 = "";
            String gen3 = "";
            String gen4 = "";
            String gen5 = "";
            String gen6 = "";
            String gen8 = "";
            String gen9 = "";
            String gen10 = "";
            String gen11 = "";
            String gen12 = "";
            String gen13 = "";
            List<HakmilikPermohonan> hkm = permohonan.getSenaraiHakmilik();
            KodDokumen kd2 = new KodDokumen();
            KodDokumen kd3 = new KodDokumen();
            KodDokumen kd4 = new KodDokumen();
            KodDokumen kd5 = new KodDokumen();
            KodDokumen kd6 = new KodDokumen();
            KodDokumen kd7 = new KodDokumen();
            KodDokumen kd8 = new KodDokumen();
            KodDokumen kd10 = new KodDokumen();
            KodDokumen kd11 = new KodDokumen();
            KodDokumen kd12 = new KodDokumen();
            KodDokumen kd13 = new KodDokumen();
            KodDokumen kd14 = new KodDokumen();
            KodDokumen kd15 = new KodDokumen();

            //Hakmilik hkinduk = hakmilikDAO.findById(hkm.get(1).getHakmilik().getIdHakmilikInduk());
            List<HakmilikPermohonan> hk = strService.findIdHakmilikSortAsc(idPermohonan);
            Hakmilik hkinduk = hakmilikDAO.findById(hk.get(1).getHakmilik().getIdHakmilikInduk());
            LOG.info("--Hakmilik--::" + hk.get(1).getHakmilik().getIdHakmilik());
            LOG.info("--Hakmilik hkinduk--::" + hkinduk);
            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
            values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};

            gen2 = "STRB2K5_MLK.rdf";
            gen3 = "STRB3K5_MLK.rdf";

            if (mohon.getKodUrusan().getKod().equals("PBBS")
                    || mohon.getKodUrusan().getKod().equals("PBBD")
                    || mohon.getKodUrusan().getKod().equals("PBS")) {
                kodHakmilik = hakmilikServ.getKodHakmilik(hkinduk.getIdHakmilik());
                senaraiHakmilikSTRATA = hakmilikServ.getDaerahMukimbyIDHakmilik(hkinduk.getIdHakmilik());
                Permohonan mohonHTB = strService.getidMohonHTBByIDSblm(idPermohonan, "HTB");
                String noBukuBr = null;
                Date trhRuj = null;
                if (mohonHTB != null) {
                    List<PermohonanRujukanLuar> mrl = strService.findPermohonanList(mohonHTB.getIdPermohonan(), "FL");
                    Integer buku = strService.maxNoBuku("T");
                    for (PermohonanRujukanLuar mr : mrl) {
                        if (mr.getNoRujukan() != null) {
                            noBukuBr = mr.getNoRujukan();
                        }
                        if (mr.getTarikhRujukan() != null) {
                            trhRuj = mr.getTarikhRujukan();
                        }
                    }
                }
                for (Hakmilik hm : senaraiHakmilikSTRATA) {
                    if (hm.getNoBukuDaftarStrata() == null) {
                        hm.setNoBukuDaftarStrata(noBukuBr);
                        hm.setTarikhDaftar(trhRuj);
                        hakmilikDAO.saveOrUpdate(hm);
                    }
                }

            }
            if (mohon.getKodUrusan().getKod().equals("PHPP")
                    || mohon.getKodUrusan().getKod().equals("PHPC")) {
                LOG.info("--PHPP/PHPC--::");
                for (int vm = 0; vm < hk.size(); vm++) {
                    if (hk.get(vm).getHakmilik().getNoBukuDaftarStrata() == null) {
                        LOG.info("xde no buku strata");
                    } else {
                        String noBuku = hk.get(vm).getHakmilik().getNoBukuDaftarStrata();
                        Date trhDaftar = hk.get(vm).getHakmilik().getTarikhDaftar();

                        for (int V = 0; V < hk.size(); V++) {
                            Hakmilik nobuku = hk.get(V).getHakmilik();
                            LOG.info(nobuku);
                            nobuku.setNoBukuDaftarStrata(noBuku);
                            nobuku.setTarikhDaftar(trhDaftar);
                            hakmilikDAO.saveOrUpdate(nobuku);
                        }
                    }
                }
            }
            if (mohon.getKodUrusan().getKod().equals("PSBS")) {
                LOG.info("--PSBS--::");
                String idHakmilikInduk = hkinduk.getIdHakmilik();
                nobukup = hakmilikServ.getIDHakmilikByInduk(idHakmilikInduk);
                LOG.info("nobukup " + nobukup.size());

                LOG.info("HK" + hk.size());
                for (int v = 0; v < nobukup.size(); v++) {
                    if (nobukup.get(v).getNoBukuDaftarStrata().isEmpty()) {
                        LOG.info("xde no buku strata");
                    } else {
                        String noBuku = nobukup.get(v).getNoBukuDaftarStrata();
                        Date trhDaftar = nobukup.get(v).getTarikhDaftar();
                        for (int vm = 0; vm < hk.size(); vm++) {
                            LOG.info("--Hakmilik--::" + hk.get(vm).getHakmilik().getIdHakmilik());
                            LOG.info("jumlh bit" + hk.get(vm).getHakmilik().getIdHakmilik().length());
                            Integer pnjg = hk.get(vm).getHakmilik().getIdHakmilik().length();

                            if (pnjg >= 18) {

                                Hakmilik nobuku = hk.get(vm).getHakmilik();
                                LOG.info(nobuku);
                                nobuku.setNoBukuDaftarStrata(noBuku);
                                nobuku.setTarikhDaftar(trhDaftar);
                                hakmilikDAO.saveOrUpdate(nobuku);
                            }
                        }
                    }
                }
            }

            LOG.info("--Generating 3K--::");
            kd3.setKod("3K");
            f = saveOrUpdateDokumen(fd, kd3, hkinduk.getIdHakmilik(), context);
            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            LOG.info("::Path To save :: " + path3);
            LOG.info("::Report Name ::" + gen3);
            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
            LOG.info("--Generated 3K--::");

            if (!(mohon.getKodUrusan().getKod().equals("PHPC") || mohon.getKodUrusan().getKod().equals("PHPP"))) {

                LOG.info("--Generating 2K--::");
                kd2.setKod("2K");
                e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                LOG.info("::Path To save :: " + path2);
                LOG.info("::Report Name ::" + gen2);
                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                LOG.info("--Generated 2K--::");

                pBangunanProvisionalBlock = strService.findByIDPermohonanByProvisional(permohonan.getIdPermohonan());
                LOG.info("---pBangunanProvisionalBlock---" + pBangunanProvisionalBlock.size());

            }

            LOG.info("--Bayaran5F--::");
            kd8.setKod("STR5F");
            gen8 = "STRBayaran5F_MLK.rdf";
            k = saveOrUpdateDokumen(fd, kd8, permohonan.getIdPermohonan(), context);
            path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
            LOG.info("::Path To save :: " + path8);
            LOG.info("::Report Name ::" + gen8);
            syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path8);
            reportUtil.generateReport(gen8, params, values, dokumenPath + path8, peng);
            updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
            LOG.info("--Generated Bayaran5F--::");

            //gen notis 5f
            LOG.info("--Generating 5F--::");
            kd4.setKod("5F");
            gen4 = "STRB5F_MLK.rdf";
            g = saveOrUpdateDokumen(fd, kd4, permohonan.getIdPermohonan(), context);
            path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
            LOG.info("::Path To save :: " + path4);
            LOG.info("::Report Name ::" + gen4);
            syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path4);
            reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
            updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());

            LOG.info("--Generating Sijil MC--::");
            kd5.setKod("SMC");
            gen5 = "STRSijilMC_MLK1.rdf";
            g = saveOrUpdateDokumen(fd, kd5, permohonan.getIdPermohonan(), context);
            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
            LOG.info("::Path To save :: " + path5);
            LOG.info("::Report Name ::" + gen5);
            syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
            reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
            updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());

            if (mohon.getKodUrusan().getKod().equals("PHPC") || mohon.getKodUrusan().getKod().equals("PHPP")) {

                gen2 = "STRB2KPH_MLK.rdf";

                LOG.info("--Generating 2K--::");
                kd2.setKod("2K");
                e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
                path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
                LOG.info("::Path To save :: " + path2);
                LOG.info("::Report Name ::" + gen2);
                reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
                updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
                LOG.info("--Generated 2K--::");

            }
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, StageContext context) {
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod() + "(" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_SULIT);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    @Transactional
    private void saveHakmilik(Hakmilik hakmilik) {
        hakmilikDAO.saveOrUpdate(hakmilik);

    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String outcome = "";
        Pengguna peng = context.getPengguna();
        Permohonan mohon = context.getPermohonan();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        try {
            if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                    || mohon.getKodUrusan().getKod().equals("PSBS") || mohon.getKodUrusan().getKod().equals("PHPC")
                    || mohon.getKodUrusan().getKod().equals("PHPP") || mohon.getKodUrusan().getKod().equals("PBS")) {
                updateStatusHakmilik(mohon, infoAudit, proposedOutcome);
            }

            outcome = checkstageID(context);
            if (outcome != null && !outcome.equals("")) {
                proposedOutcome = outcome;
                LOG.info(" ******** outcome condition***********:" + outcome);
            }
            LOG.info(" ******** outcome ***********:" + outcome);
            context.addMessage(mohon.getIdPermohonan() + " - Urusan telah selesai. ");
        } catch (WorkflowException ex) {
            LOG.info(" ******Exception** outcome ***********:" + outcome);
            LOG.error(ex.getMessage());
            return proposedOutcome;
        }
        LOG.info(" ******** proposedOutcome ***********:" + proposedOutcome);
        return proposedOutcome;
//        return null;
    }

    public void updateStatusHakmilik(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
        info.setDiKemaskiniOleh(info.getDimasukOleh());
        info.setTarikhKemaskini(new java.util.Date());
        String kod = permohonan.getKodUrusan().getKod();
        KodStatusHakmilik kodStatusHakmilik;
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        senaraiPermohonanRujukanLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);
        LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
        }
        FasaPermohonan mohonFasa = new FasaPermohonan();
        //mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        if (kod.equals("PHPC") || kod.equals("PHPP")) {
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan1");
        } else {
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        }
        if (mohonFasa.getKeputusan().getKod().equals("L")) {
            List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
                if (hakmilikPermohonan.getHakmilik().getIdHakmilikInduk() != null) {
                    hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    if (hakmilik != null) {
                        List<HakmilikPihakBerkepentingan> hp = strService.findPMAktif(hakmilik.getIdHakmilik());
                        if (kod.equals("PBBS") || kod.equals("PBBD")
                                || kod.equals("PSBS") || kod.equals("PBS")) {
                            hakmilik.setNoVersiIndeksStrata(1);
                            hakmilik.setVersion(1);
                            if (hakmilik.getIdHakmilikInduk() != null) {
                                hakmilik.setInfoAudit(info);
                                kodStatusHakmilik = new KodStatusHakmilik();
                                kodStatusHakmilik = kodStatusHakmilikDAO.findById("D");
                                hakmilik.setKodStatusHakmilik(kodStatusHakmilik);

                                if (hakmilik.getKodKategoriBangunan().getKod().equals("B") || hakmilik.getKodKategoriBangunan().getKod().equals("L") || hakmilik.getKodKategoriBangunan().getKod().equals("PL") ) {
                                    Akaun ak = strService.findAkaunHM(hakmilik.getIdHakmilik());
                                    if (ak == null) {
                                        Akaun kewAkaun = new Akaun();
                                        kewAkaun.setNoAkaun(hakmilik.getIdHakmilik());
                                        kewAkaun.setHakmilik(hakmilik);
                                        if (hp.size() > 0) {
                                            kewAkaun.setPemegang(hp.get(0).getPihak());
                                        }
                                        kewAkaun.setKodAkaun(kodAkaunDAO.findById("AC"));
                                        kewAkaun.setCawangan(hakmilik.getCawangan());
                                        kewAkaun.setBaki(BigDecimal.ZERO);
                                        kewAkaun.setAmaunMatang(BigDecimal.ZERO);
                                        kewAkaun.setBilCetakPenyata(0);
                                        kewAkaun.setStatus(kodStatusAkaunDAO.findById("A"));
                                        kewAkaun.setInfoAudit(info);
                                        kewAkaun.setHantarBil('T');
                                        akaunDAO.save(kewAkaun);
                                    }
                                }
                            }
                        }
                        if (kod.equals("PHPP") || kod.equals("PHPC")) {
                            kodStatusHakmilik = new KodStatusHakmilik();
                            if (hakmilik.getKodStatusHakmilik().getKod().equals("D")) {
                                kodStatusHakmilik = kodStatusHakmilikDAO.findById("B");
                                hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
                                
                                if (hakmilik.getKodKategoriBangunan().getKod().equals("B") || hakmilik.getKodKategoriBangunan().getKod().equals("L") || hakmilik.getKodKategoriBangunan().getKod().equals("PL") ) {
                                    Akaun ak = strService.findAkaunHM(hakmilik.getIdHakmilik());
                                    if (ak != null) {
                                        ak.setStatus(kodStatusAkaunDAO.findById("B"));
                                        ak.setInfoAudit(info);
                                        ak.setHantarBil('T');
                                        akaunDAO.save(ak);
                                    }
                                }
                            }
                            if (hakmilik.getKodStatusHakmilik().getKod().equals("T")) {
                                kodStatusHakmilik = kodStatusHakmilikDAO.findById("D");
                                hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
                                
                                if (hakmilik.getKodKategoriBangunan().getKod().equals("B") || hakmilik.getKodKategoriBangunan().getKod().equals("L") || hakmilik.getKodKategoriBangunan().getKod().equals("PL") ) {
                                    Akaun ak = strService.findAkaunHM(hakmilik.getIdHakmilik());
                                    if (ak == null) {
                                        Akaun kewAkaun = new Akaun();
                                        kewAkaun.setNoAkaun(hakmilik.getIdHakmilik());
                                        kewAkaun.setHakmilik(hakmilik);
                                        if (hp.size() > 0) {
                                            kewAkaun.setPemegang(hp.get(0).getPihak());
                                        }
                                        kewAkaun.setKodAkaun(kodAkaunDAO.findById("AC"));
                                        kewAkaun.setCawangan(hakmilik.getCawangan());
                                        kewAkaun.setBaki(BigDecimal.ZERO);
                                        kewAkaun.setAmaunMatang(BigDecimal.ZERO);
                                        kewAkaun.setBilCetakPenyata(0);
                                        kewAkaun.setStatus(kodStatusAkaunDAO.findById("A"));
                                        kewAkaun.setInfoAudit(info);
                                        kewAkaun.setHantarBil('T');
                                        akaunDAO.save(kewAkaun);
                                    }
                                }
                            }
                        }
                    }
                    strService.simpanHakmilik(hakmilik);
                }
            }
        }
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(" ******** stage ***********:" + stage);

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SFUS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PFUS")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBD")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHPC")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSBS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBS")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPPP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PKKR")) {
                FasaPermohonan mohonFasa = null;
//                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SFUS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PFUS")) {
//                    mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
//                }
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPPP")) {
                    mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "semaksijil");
                } else {
                    mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
                }
                Pengguna peng = context.getPengguna();

                LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
                if (mohonFasa != null) {
//                    try {
//                        WorkFlowService.withdrawTask(l.get(0).getSystemAttributes().getTaskId());
//                    } catch (StaleObjectException ex) {
//                        java.util.logging.Logger.getLogger(CompletePBBValidator.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    permohonan.setKeputusan(mohonFasa.getKeputusan());
                    permohonan.setKeputusanOleh(peng);
                    permohonan.setTarikhKeputusan(new Date());
                    permohonan.setStatus("SL");
                    strService.updateMohon(permohonan);

                }
            }
            value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
        }
        return value;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
