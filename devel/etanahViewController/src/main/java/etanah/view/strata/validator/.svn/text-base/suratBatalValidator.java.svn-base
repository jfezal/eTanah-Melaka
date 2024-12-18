
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class suratBatalValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    private TaskDebugService tds;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
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
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    private String idHakmilik;
    private Permohonan mohonRMHS;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(suratBatalValidator.class);
    private Hakmilik hakmilik;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        LOG.debug("----generating STRSPembatalanKelulusan_NS----:");
        Pengguna pengguna = context.getPengguna();
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        String idFolder = "";
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        KodDokumen kd2 = new KodDokumen();
        String[] params = null;
        String[] values = null;
        params = new String[]{"p_id_mohon"};
        values = new String[]{idPermohonan.trim()};
        String gen = "";
        gen = "STRSPembatalanKelulusan_NS.rdf";
        String path = "";
        kd2.setKod("SBTL");
        e = saveOrUpdateDokumen(fd, kd2, idPermohonan, context);
        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
        LOG.info("::Path To save :: " + path);
        LOG.info("::Report Name ::" + gen);
        reportUtil.generateReport(gen, params, values, dokumenPath + path, peng);
        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
        LOG.debug("----generated STRSPembatalanKelulusan_NS----:");
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
        doc.setNoVersi("1.0");
        doc.setTajuk("Surat Pembatalan Kelulusan Permohonan Pecah Bahagi Bangunan");
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

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        FasaPermohonan mohonFasa = new FasaPermohonan();
        Permohonan permohonan = context.getPermohonan();
        try {
               
                KodUrusan kod = kodUrusanDAO.findById("PBBB");
                
                Pengguna peng = (Pengguna) context.getPengguna();
                peng = (Pengguna) context.getPengguna();
                
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                String[] name = {"idHakmilik"};
                Object[] value = {idHakmilik};
                List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
                
                LOG.info(kod.getNama());
                LOG.info(permohonan.getFolderDokumen());
                Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, permohonan);
                PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                permohonanRujLuar.setCawangan(peng.getKodCawangan());
                permohonanRujLuar.setPermohonan(mohonReg);
                //ida update 29/04/2013
                permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                permohonanRujLuar.setNoRujukan("1");

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
    
        public String getIdHakmilik() {
        return idHakmilik;
    }
        public Hakmilik getHakmilik() {
        return hakmilik;
    }
}
