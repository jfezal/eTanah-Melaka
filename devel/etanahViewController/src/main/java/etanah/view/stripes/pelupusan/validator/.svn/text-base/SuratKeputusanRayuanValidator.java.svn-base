/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
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
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author Afham
 */
public class SuratKeputusanRayuanValidator implements StageListener  {
    
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
    private static final Logger LOGGER = Logger.getLogger(SuratKeputusanRayuanValidator.class);
    private static Pengguna pengguna;
    private String stage;
    private FasaPermohonan fasaPermohonan ;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan p = context.getPermohonan();
        pengguna = context.getPengguna();
        KodUrusan ku = p.getKodUrusan();
        String kodNegeri = conf.getProperty("kodNegeri");
        
        if (p != null) {
            try {

                String gen1 = "";
                String gen2 = "";
                String code1 = "";
                String code2 = "";
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{p.getIdPermohonan()};
                String path = "";
                String path2 = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;

                FolderDokumen fd = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());
                List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                LOGGER.info("list task size: " + l.size());
                
                if(kodNegeri.equalsIgnoreCase("04")) {	// melaka
                	generateRptMelaka(l, ku, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
                } else if(kodNegeri.equalsIgnoreCase("05")) {	// ns
                	
                }

            } catch (WorkflowException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }
   
    private void generateRptMelaka(List<Task> l, KodUrusan ku, String gen1, String code1, KodDokumen kd, FolderDokumen fd, Dokumen d,
    									String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2, 
    									String dokumenPath) {
    	
    	
        for (Task t : l) {
        	if(t.getSystemAttributes().getStage() != null) {
        		stage = t.getSystemAttributes().getStage();
                LOGGER.info(stage);
                //Stage Rayuan Pengurangan Premium (RAYK)
                if(p.getKodUrusan().getKod().equals("RAYK")){
                          if (stage.contentEquals("12RekodKeputusan")) {
                              
                              if (p.getKeputusan() != null) {
                                  if (p.getKeputusan().getKod().equals("L")) {
                                      gen1 = "DISSrtKpsnMMKNRAYKL_MLK.rdf";
                                      code1 = "SKM";
                                      kd = kodDokumenDAO.findById(code1);
                                      LOGGER.info(kd);
                                      d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                      path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                      reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                      updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                                  }
                                  else if (p.getKeputusan().getKod().equals("T")) {

                                      gen1 = "DISSrtKpsnMMKNG_MLK.rdf";
                                      code1 = "SKM";
                                      kd = kodDokumenDAO.findById(code1);
                                      LOGGER.info(kd);
                                      d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                      path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                      reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                      updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                                  }
                              }

                    }             
                }
                

        	}
        	else {
        		// do nothing
        	}
        }
    }
    
   @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
          Permohonan p = context.getPermohonan() ;
          if(p != null){
              if(p.getKeputusan() != null){
                  proposedOutcome = p.getKeputusan().getKod() ;
              }
              
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}

