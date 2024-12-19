/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.plpt.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.PelupusanService;
import etanah.service.SemakDokumenService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.NotisService;
import etanah.service.common.ValidationService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisReport;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Shazwan 1 January 2012
 */
public class ReportV2Validator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    SyerValidationService syerService;
    @Inject
    ValidationService validationService;
    @Inject
    TabManager tabManager;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    NotisService notisService;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PelupusanService plpservice;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList ;
    private static final Logger LOGGER = Logger.getLogger(ReportV2Validator.class);
    private static Pengguna pengguna;
    private String stage;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        pengguna = context.getPengguna();
        stage = context.getStageName();
        KodUrusan kodUrusan = permohonan.getKodUrusan();

        String kodNegeri = conf.getProperty("kodNegeri");

        if (permohonan != null) {

          
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            FolderDokumen folderDok = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            //                List<Task> task = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());


           
                generateReport(folderDok, path, permohonan, params, values, dokumenPath);
        }
    }

    /**
     * 
     * @author Shazwan
     * @version 1.0 
     * date 11/1/2012
     */


    private void generateReport(  FolderDokumen folderDok,
            String path, Permohonan p, String[] params, String[] values,  String dokumenPath) {
        HashMap reportMap = new HashMap();
        reportMap.put("reportName1", "DISLPPLPS_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "LPE");

        System.out.println("THIS IS REPORTMAP.SIZE AFTER DIVIDE BY TWO :" + reportMap.size() / 2);

        for (int i = 1; i <= reportMap.size() / 2; i++) {
            //reportMap.get("name"+String.valueOf(i));
            //dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("kod"+String.valueOf(i)).toString()), p.getIdPermohonan());
           Dokumen dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("reportKod" + String.valueOf(i)).toString()), p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(dokumen.getIdDokumen());
            reportUtil.generateReport(reportMap.get("reportName" + String.valueOf(i)).toString(), params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen(), reportUtil.getContentType());
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
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOGGER.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOGGER.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            if(!doc.getKodDokumen().getKod().equals("RENC")){
                  ia.setTarikhKemaskini(new java.util.Date());
            }          
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama() + "-" + id);
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
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
