/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LaporanTanahDAO;
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
import etanah.view.stripes.pelupusan.disClass.DisReport;
import etanah.view.stripes.pengambilan.AcqReport;
import etanah.view.stripes.pelupusan.validator.ReportValidator;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oracle.bpel.services.workflow.task.model.Task;


/**
 *
 * @author nordiyana
 */
public class ReportpengambilanValidator implements StageListener {
     @Inject
    etanah.Configuration conf;
    @Inject
    SyerValidationService syerService;
    @Inject
    ValidationService validationService;
    @Inject
    TabManager tabManager;
    @Inject
    KodDokumenDAO kodDokumenDAO;
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
    private static final Logger LOGGER = Logger.getLogger(ReportpengambilanValidator.class);
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
            
                String gen1 = "";
                String gen2 = "";
                String code1 = "";
                String code2 = "";
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{permohonan.getIdPermohonan()};
                String path = "";
                String path2 = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen dokumen = null;
                KodDokumen kodDok = null;

                FolderDokumen folderDok = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                //                List<Task> task = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
                
                int numUrusan = this.getListUrusan(permohonan);
                if(numUrusan>0){
                    generateReport(stage,numUrusan,kodNegeri, kodUrusan, gen1, code1, folderDok, dokumen, path, permohonan, params, values, gen2, code2, path2, dokumenPath);
                }
        }
    }
    
    /**
     * 
     * @author Shazwan
     * @version 1.0 
     * date 11/1/2012
     */
    private int getListUrusan(Permohonan permohonan) {
        
        int numUrusan = permohonan.getKodUrusan().getKod().equals("PB")?1:
                        0;        
        return numUrusan;
        
    }
    private void generateReport(String stage,int numUrusan,String kodNegeri, KodUrusan kodUrusan, String gen1, String code1, FolderDokumen folderDok, Dokumen dokumen,
                                String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2,String dokumenPath) {
        
        AcqReport acqReport = new AcqReport();        
        HashMap reportMap = new HashMap(acqReport.getReportMap(numUrusan,stage,kodNegeri,p));        
        
        System.out.println("THIS IS REPORTMAP.SIZE AFTER DIVIDE BY TWO :"+reportMap.size()/2);
        
        for(int i=1;i<=reportMap.size()/2;i++){
            //reportMap.get("name"+String.valueOf(i));
            //dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("kod"+String.valueOf(i)).toString()), p.getIdPermohonan());
            dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("reportKod"+String.valueOf(i)).toString()), p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(dokumen.getIdDokumen());
            reportUtil.generateReport(reportMap.get("reportName"+String.valueOf(i)).toString(), params, values, dokumenPath + path, pengguna);
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
            ia.setTarikhKemaskini(new java.util.Date());
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
        return proposedOutcome;
    }
    
}
