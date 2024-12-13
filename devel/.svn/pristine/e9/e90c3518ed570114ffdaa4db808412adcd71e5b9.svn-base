/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8bc.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.ref.pengambilan.sek8bc.RefPeringkat;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.acq.integrationflow.Sek8BCIntegrationFlowService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class LaporanTanahPTGValidator implements StageListener {
    @Inject
    Sek8BCIntegrationFlowService sek8BCIntegrationFlowService;
    private static final Logger LOG = Logger.getLogger(LaporanTanahPTGValidator.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    ReportUtil reportUtil;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
     @Inject
    SemakDokumenService semakDokumenService;
    
    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        
        String namaReport[] = new String[2];
        String kodReport[] = new String[2];
        namaReport[0] = "ACQLT_PTG_MLK.rdf";
        namaReport[1] = "ACQLTAPT_PTG_MLK.rdf";
        kodReport[0] = "LTPG";
        kodReport[1] = "LTPG";

        List<HakmilikPermohonan> hakmilikPermohonan = hakmilikPermohonanService.findHakmilikByIdMohon(context.getPermohonan().getIdPermohonan());

        if (hakmilikPermohonan.size() > 1) {
            namaReport = new String[1];
            kodReport = new String[1];
            namaReport[0] = "ACQLTAPT_PTG_MLK.rdf";
            kodReport[0] = "LTPG";

        } else {
            namaReport = new String[1];
            kodReport = new String[1];
            namaReport[0] = "ACQLT_PTG_MLK.rdf";
            kodReport[0] = "LTPG";

        }

        for (int i = 0; i < namaReport.length; i++) {
            janaDokumen(context.getPermohonan(), context.getPengguna(), namaReport[i], kodReport[i]);
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
    }
    
     public void janaDokumen(Permohonan permohonan, Pengguna pengguna, String namaReport, String kodReport) {
        try {
            LOG.info("------------generate report for Kod Dokumen --------------:::" + kodReport + "(" + namaReport + ")");
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;
            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            String reportName = "";
            kd = kodDokumenDAO.findById(kodReport);
            reportName = namaReport;
            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan(), pengguna);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());

            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna pengguna) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        if (!etanah.util.StringUtils.isBlank(kd.getNama())) {
            doc.setTajuk(kd.getNama());
        }
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

}
