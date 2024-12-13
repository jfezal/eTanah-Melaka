
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.logging.Level;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author w.fairul
 */
public class SemakPindaanValidator implements StageListener {

    @Inject
    ReportValidator reportValidator;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    private String stage;
    private static Pengguna pengguna;
    @Inject
    ReportUtil reportUtil;
    private static final Logger LOG = Logger.getLogger(SemakPindaanValidator.class);
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan p = context.getPermohonan();
        pengguna = context.getPengguna();
        KodUrusan ku = p.getKodUrusan();
        if (p != null) {
            try {

                String gen1 = "";
                String code1 = "";
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{p.getIdPermohonan()};
                String path = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;

                FolderDokumen fd = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());

                List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                for (Task t : l) {
                    stage = t.getSystemAttributes().getStage();
                    LOG.info(stage);
                    if (stage.contentEquals("rekod_keputusan_mmkn")) {
                        gen1 = "DISSrtkeputusanMMKNPTG_MLK.rdf";
                        LOG.info(gen1);
                        code1 = "SKM";
                        kd = kodDokumenDAO.findById(code1);
                        LOG.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
            } catch (WorkflowException ex) {
                LOG.error(ex.getMessage());
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String outcome = "";
        try {
            outcome = checkstageID(context);
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        proposedOutcome = outcome;
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

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
             LOG.info(stage);
            if (stage.contentEquals("pindaan_draf_mmkn")) {
                value = "SN";
                return value;
            }
            if (stage.contentEquals("rekod_keputusan_mmkn")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }
            if (stage.contentEquals("salinan_surat_keputusan1")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }
            if (stage.contentEquals("arahan_tugas")) {
                value = permohonan.getKeputusan().getKod();
                LOG.info(value);
                return value;
            }
            if (stage.contentEquals("rekod_keputusan_mmkn2")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }
            if (stage.contentEquals("salinan_surat_keputusan2_1")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }
             if(permohonan.getKodUrusan().getKod().equalsIgnoreCase("PLPS"))
             {
              if (stage.contentEquals("g_charting_keputusan")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }
             }
        }

        return value;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
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
