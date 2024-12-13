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
 * @author afham
 */
public class UrusanRayuanValidator implements StageListener  {
    
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
    private static final Logger LOGGER = Logger.getLogger(UrusanRayuanValidator.class);
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
                	generateRptNS(l, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
                }

            } catch (WorkflowException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }
    
    /**
     * 
     * @param l
     * @param gen1
     * @param code1
     * @param kd
     * @param fd
     * @param d
     * @param path
     * @param p
     * @param params
     * @param values
     * @param gen2
     * @param code2
     * @param path2
     * @param dokumenPath
     * @author Mohd Hairudin Mansur
     * @version 25042011
     */
    private void generateRptMelaka(List<Task> l, KodUrusan ku, String gen1, String code1, KodDokumen kd, FolderDokumen fd, Dokumen d,
    									String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2, 
    									String dokumenPath) {
    	
    	
        for (Task t : l) {
        	if(t.getSystemAttributes().getStage() != null) {
        		stage = t.getSystemAttributes().getStage();
                LOGGER.info(stage);
                //Stage Rayuan Pengurangan Premium (RAYK)
                if(p.getKodUrusan().getKod().equals("RAYK")){
                    //Report for Draf MMKN PTD
                  if (stage.contentEquals("04SemakMMKN")) {
                        gen1 = "DISKMMKNRAYKPTD_MLK.rdf";
                        code1 = "RMN";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                        //Report for Draf MMKN PTG
                     if(stage.contentEquals("09SemakanMMKN")){
                                gen1 = "DISKMMKNRAYKPTG_MLK.rdf";
                                code1 = "RMN";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                      if(stage.contentEquals("14SediaSuratTolak")){
                                gen1 = "DISSRAYG_MLK.rdf";
                                code1 = "STP";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                      
                         if(stage.contentEquals("16SediaSuratBorang5A")){
                                gen1 = "DISSRAYKL_MLK.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                                
                                gen1 = "DISB5A_MLK.rdf";
                                code1 = "N5A";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                         
                          if (stage.contentEquals("12RekodKeputusan")) {
                              fasaPermohonan = plpservice.findMohonFasaByIdMohonIdPengguna(p.getIdPermohonan(), "12RekodKeputusan");
                              if (fasaPermohonan.getKeputusan() != null) {
                                  if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                                      gen1 = "DISSrtKpsnMMKNRAYKL_MLK.rdf";
                                      code1 = "SKM";
                                      kd = kodDokumenDAO.findById(code1);
                                      LOGGER.info(kd);
                                      d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                      path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                      reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                      updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                                  }
                                  else if (fasaPermohonan.getKeputusan().getKod().equals("T")) {

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
                //Rayuan Lanjut Tempoh Bayaran RAYL
                 if(p.getKodUrusan().getKod().equals("RAYL")){
                    //Report for Draf MMKN PTD
                  if (stage.contentEquals("04SemakMMKN")) {
                        gen1 = "DISKMMKNRAYLPTD_MLK.rdf";
                        code1 = "RMN";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                        //Report for Draf MMKN PTG
                     if(stage.contentEquals("09SemakanMMKN")){
                                gen1 = "DISKMMKNRAYLPTG_MLK.rdf";
                                code1 = "RMN";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                      if(stage.contentEquals("14SediaSuratTolak")){
                                gen1 = "DISSRAYG_MLK.rdf";
                                code1 = "STP";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
               
                        if(stage.contentEquals("16SediaSuratLulus")){
                                gen1 = "DISSRAYLL_MLK.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                        
                  
                }
                 
                    if(p.getKodUrusan().getKod().equals("RAYA")){
                    //Report for Draf MMKN PTD
                 
                      if(stage.contentEquals("02SediaSuratLulus")){
                                gen1 = "DISSRAYAL_MLK.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                      
                        if(stage.contentEquals("05SediaSuratBatal")){
                                gen1 = "DISSAPRAYA_MLK.rdf";
                                code1 = "SBA";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                }

        	}
        	else {
        		// do nothing
        	}
        }
    }
    
    
    /**
     * 
     * @param l
     * @param gen1
     * @param code1
     * @param kd
     * @param fd
     * @param d
     * @param path
     * @param p
     * @param params
     * @param values
     * @param gen2
     * @param code2
     * @param path2
     * @param dokumenPath
     * @author Mohd Hairudin Mansur
     * @version 25042011
     */
    private void generateRptNS(List<Task> l, String gen1, String code1, KodDokumen kd, FolderDokumen fd, Dokumen d, 
			String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2, 
			String dokumenPath) {
    	
    	for (Task t : l) {
    		if(t.getSystemAttributes().getStage() != null) {
    			stage = t.getSystemAttributes().getStage();
                LOGGER.info(stage);
                //Rayuan Lanjut Tempoh Bayaran Kuasa PTD
                if(p.getKodUrusan().getKod().equals("RAYL")){
                       if(stage.contentEquals("01KemasukanKertasRingkas")){
                                gen1 = "DISKRRAYLPTDH_NS.rdf";
                                code1 = "KRPTD";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       
                       if(stage.contentEquals("03SemakSyorKertas")){
                                gen1 = "DISKRRAYLPTD_NS.rdf";
                                code1 = "KRPTD";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       if(stage.contentEquals("05TerimaKeputusanL")){
                                gen1 = "DISSRAYLPTDL_NS.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       if(stage.contentEquals("07TerimaKeputusanT")){
//                                gen1 = "DISSRAYG_NS.rdf";
                                gen1 = "DISSRAYLPTDL_NS.rdf";
                                code1 = "STT";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    
                }
                //Rayuan Lanjut Tempoh Bayaran Kuasa PTG
                 if(p.getKodUrusan().getKod().equals("RLPTG")){
                    if(stage.contentEquals("01SediaKertasRingkas")){
                                //gen1 = "DISKRRAYLPTG_NS.rdf";
                                gen1 = "DISKRRAYLPTGH_NS.rdf";
                                code1 = "KRPTG";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       
                       if(stage.contentEquals("04TerimaKertas")){
                                gen1 = "DISKRRAYLPTG_NS.rdf";
                                code1 = "KRPTG";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                         if(stage.contentEquals("06SemakSyorKertas")){
                                gen1 = "DISKRRAYLPTG_NS.rdf";
                                code1 = "KRPTG";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                         
                         if(stage.contentEquals("11SediaKertasPTG")){
                                gen1 = "DISKRRAYLPTG_NS.rdf";
                                code1 = "KRPTG";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                           if(stage.contentEquals("14TerimaKeputusanL")){
                                gen1 = "DISKRRAYLPTG_NS.rdf";
                                code1 = "KKPTG";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                         
                       if(stage.contentEquals("16SediaSuratLulus")){
//                                gen1 = "DISSRAYLPTGL_NS.rdf";
                                gen1 = "DISSRAYLPTDL_NS.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       if(stage.contentEquals("20SediaSuratTolak")){
//                                gen1 = "DISSRAYG_NS.rdf";
                                gen1 = "DISSRAYLPTDL_NS.rdf";
                                code1 = "STT";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                }
                 //Rayuan Pengurangan Premium
                 if(p.getKodUrusan().getKod().equals("RYKN")){
                    
                             if(stage.contentEquals("01SediaKertasRingkas")){
                                gen1 = "DISKMMKNRAYK_NS.rdf";
                                code1 = "MMK";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       
                       if(stage.contentEquals("08JanaCetakKertas")){
                                gen1 = "DISKMMKNRAYK_NS.rdf";
                                code1 = "MMK";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       if(stage.contentEquals("16SediaSuratLulus")){
                                gen1 = "DISSRAYKPTGL_NS.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                       if(stage.contentEquals("20SediaSuratTolak")){
                                gen1 = "DISSRAYG_NS.rdf";
                                code1 = "STT";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
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
