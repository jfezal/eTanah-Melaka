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
 * @author wan.fairul
 * @modify Mohd Hairudin Mansur 29042011
 */
public class ReportValidator implements StageListener {

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
    private static final Logger LOGGER = Logger.getLogger(ReportValidator.class);
    private static Pengguna pengguna;
    private String stage;

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
                /*
                 *	at this stage, app will use AdunValidator to generateReport
                 * 
                 * if (stage.contentEquals("kenalpasti_jabatan_teknikal")) {
                    gen1 = "DISSMUYB_MLK.rdf";
                    code1 = "SUA";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    gen2 = "DISSUT_MLK.rdf";
                    code2 = "SUT";
                    kd = kodDokumenDAO.findById(code2);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }*/
                
                if (stage.contentEquals("tolak_ringkas")) {
                	gen1 = "DISMB_MLK.rdf";
                    code1 = "MINB";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                
                if (stage.contentEquals("semak_surat_tolak")) {
                	if(p.getKodUrusan().getKod().equals("PBMT")) {
                		gen1 = "DISSrtMaklumanTolakPTD_MLK.rdf";
                	} else if(p.getKodUrusan().getKod().equals("PLPS")) {
                		gen1 = "DISSMTPLPSPTD_MLK.rdf";
                	} else {
                		gen1 = "DISSrtMaklumanTolakPTD_MLK.rdf";
                	}
                    
                    code1 = "SMT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                
                if (stage.contentEquals("g_charting_permohonan")) {
                	if(p.getKodUrusan().getKod().equals("PBMT")) {
                		gen1 = "DISLPPLPS_MLK.rdf";
                	} else if(p.getKodUrusan().getKod().equals("PLPS")) {
                		gen1 = "DISLPPLPS_MLK.rdf";
                	} else {
                		gen1 = "DISLPPLPS_MLK.rdf";
                	}
                    
                    code1 = "LPE";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                
                if (stage.contentEquals("laporan_tanah")) {
                	if(p.getKodUrusan().getKod().equals("PBMT")) {
                		gen1 = "DISLTPLPS_MLK.rdf";
                	} else if(p.getKodUrusan().getKod().equals("PLPS")) {
                		gen1 = "DISLTPLPS_MLK.rdf";
                	} 
                        else if(p.getKodUrusan().getKod().equals("PPTR")) {
                		gen1 = "DISLTPPTR_MLK.rdf";
                	}         
                            else {
                		gen1 = "DISLTPLPS_MLK.rdf";
                	}
                    
                    code1 = "LTPD";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                
                if (p.getKodUrusan().getKod().equals("PBMT")) {
                    if (stage.contentEquals("semak_draf_mmkn2")) {
                        gen1 = "DISKMMKNPBMTPTD_MLK.rdf";
                        code1 = "RMN";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                    if (stage.contentEquals("semak_draf_mmkn3_3")) {
                        gen1 = "DISKMMKNPBMTPTG_MLK.rdf";
                        code1 = "RMN";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                
                if (p.getKodUrusan().getKod().equals("PLPS")) {
                    if (stage.contentEquals("semak_draf_mmkn2")) {
                        gen1 = "DISKMMKNPLPSandLPSPPTD_MLK.rdf";
                        code1 = "RMN";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                    if (stage.contentEquals("semak_draf_mmkn3_3")) {
                        gen1 = "DISKMMKNPLPSandLPSPPTG_MLK.rdf";
                        code1 = "RMN";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                if (!p.getKodUrusan().getKod().equals("LMCRG")) {
                        if (stage.contentEquals("rekod_keputusan_mmkn")) {
                            if(p.getKodUrusan().getKod().equals("PBMT")) {
                                    gen1 = "DISSKpsnMMKNPLPS_MLK.rdf";
                            } else if(p.getKodUrusan().getKod().equals("PLPS")) {
                                    gen1 = "DISSKpsnMMKNPLPS_MLK.rdf";
                            } else {
                                    gen1 = "DISSKpsnMMKNPLPS_MLK.rdf";
                            }

                        LOGGER.info(gen1);
                        code1 = "SKM";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                
                
                if (stage.contentEquals("semak_surat_tolak2")) {
                	if(p.getKodUrusan().getKod().equals("PBMT")) {
                		gen1 = "DISSPLPSG_MLK.rdf";
                	} else if(p.getKodUrusan().getKod().equals("PLPS")) {
                		gen1 = "DISSPLPSG_MLK.rdf";
                	} else {
                		gen1 = "DISSPLPSG_MLK.rdf";
                	}
                	
                    code1 = "STP";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                
                if (stage.contentEquals("semak_lulus_borang_5a")) {
                    gen1 = "DISSrtKelulusan_MLK.rdf";
                    //code1 = "SL";
                    code1 = "N5A";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    
                    /*gen2 = "DIS_PTK_Borang5A.rdf";
                    code2 = "N5A";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());*/
                }
                
                if (stage.contentEquals("semak_surat_kelulusan")) {
                    gen1 = "DISSPLPSL_MLK.rdf";
                    code1 = "SL";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                // LPS 
                    if (p.getKodUrusan().getKod().equals("PLPS")) {
                        if (stage.contentEquals("semak_borang_4a")) {
                            gen1 = "DISB4AePLPS_MLK.rdf";
                            code1 = "4A";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                            gen2 = "DISBL1e_MLK.rdf";
                            code2 = "L1e";
                            kd = kodDokumenDAO.findById(code2);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                        
                         if (stage.contentEquals("tandatangan_borang_4a")) {
                            gen1 = "DISB4AePLPS_MLK.rdf";
                            code1 = "4A";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                         }
                    }
                //Untuk borang 4Ce Bahan Batuan PTD and Pelan P1e
                    if (p.getKodUrusan().getKod().equals("PBPTD")) {
                        if (stage.contentEquals("rekodbayaran_sediapermit")) {
                            gen1 = "DISBrg4Ce_MLK.rdf";
                            code1 = "4Ce";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                            gen2 = "DIS_BorangP1e_MLK.rdf";
                            code2 = "P1e";
                            kd = kodDokumenDAO.findById(code2);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                          if (stage.contentEquals("tandatangan_permit")) {
                            gen1 = "DISBrg4Ce_MLK.rdf";
                            code1 = "4Ce";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    }
                  //Untuk borang 4Ce Bahan Batuan KM and Pelan P1e
                    if (p.getKodUrusan().getKod().equals("PPBB")) {
                        if (stage.contentEquals("semak_borang_4c")) {
                            gen1 = "DISBrg4Ce_MLK.rdf";
                            code1 = "4Ce";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                            gen2 = "DIS_BorangP1e_MLK.rdf";
                            code2 = "P1e";
                            kd = kodDokumenDAO.findById(code2);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                        if (stage.contentEquals("tandatangan_borang_4c")) {
                            gen1 = "DISBrg4Ce_MLK.rdf";
                            code1 = "4Ce";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                        
                    }
                   //Untuk borang 4Ce Bahan Batuan PTG and Pelan P1e
                    if (p.getKodUrusan().getKod().equals("PBPTG")) {
                        if (stage.contentEquals("semak_permit")) {
                            gen1 = "DISBrg4Ce_MLK.rdf";
                            code1 = "4Ce";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                            gen2 = "DIS_BorangP1e_MLK.rdf";
                            code2 = "P1e";
                            kd = kodDokumenDAO.findById(code2);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                        
                       if (stage.contentEquals("tandatangan_permit")) {
                            gen1 = "DISBrg4Ce_MLK.rdf";
                            code1 = "4Ce";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    }
                    
                 //Lesen dan Permit Borang 4B and Pelan L2e
                    if (p.getKodUrusan().getKod().equals("LPSP")) {
                        if (stage.contentEquals("19SemakPermit")) {
                            gen1 = "DISBrg4Be_MLK.rdf";
                            code1 = "4Be";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                            gen2 = "DIS_BorangL2e.rdf";
                            code2 = "L2e";
                            kd = kodDokumenDAO.findById(code2);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    }
                    
                    //L1e - Borang 4A
                    //L2e - Borang 4B
                    //P1e - Borang 4C
                    //P2e - Borang 4D
            
                    if (p.getKodUrusan().getKod().equals("LMCRG")) {
                       
                        if (stage.contentEquals("rekod_keputusan_mmkn")) {
                            if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                                gen1 = "DISSLMCRGL_MLK.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                                gen2 = "DISSLMCRGG_MLK.rdf";
                                code2 = "STP";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                            }
                        }
                    }
                  
                /*
                 * melaka does not produce new license - user PAT 27052011
                if(ku.getKod().equals("RLPS")){
                    if(stage.contentEquals("sedia_borang_4a")){
                    gen1 = "DISBrg4Ae_MLK.rdf";
                    code1 = "4A";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }*/
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
                if (stage.contentEquals("kenalpasti_jabatan_teknikal")) {
//                    gen2 = "DISSuratUlasanTeknikal_NS.rdf";
                    gen2 = "DISSUTPBMT1_NS.rdf";
                    code2 = "SUT";
                    kd = kodDokumenDAO.findById(code2);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("sedia_surat_peringatan")) {
//                    gen2 = "DISUlanganUlasanTeknikal_NS.rdf";
                    gen2 = "DISSUTPBMT2_NS.rdf";
                    code2 = "PER";
                    kd = kodDokumenDAO.findById(code2);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("sedia_surat_tolak")) {
//                    gen1 = "DISSuratMaklumanTolakMLK.rdf";
                    gen1 = "DISSMTPTD_NS.rdf";
                    code1 = "SMT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_draf_jktd1")) {
//                    gen1 = "DISDrafJKTD_NS.rdf";
                    gen1 = "DISKJKTDPTD_NS.rdf";
                    code1 = "JKTD";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_draf_jktd2")) {
                    gen1 = "DISDrafJKTD_NS.rdf";
                    code1 = "JKTD";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_draf_mmk1")) {
                    gen1 = "DISKMMKNPTD_NS.rdf";
                    code1 = "MMKS";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_draf_mmk2")) {
                    gen1 = "DISKertasMMKNPTD_NS.rdf";
                    code1 = "MMKS";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_huraian_syor")) {
                    gen1 = "DISKMMKNPTG_NS.rdf";
                    code1 = "MMKS";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_huraian_syor2_1")) {
                    gen1 = "DISKMMKNPKPTG_NS.rdf";
                    code1 = "MMKS";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_huraian_syor3")) {
                    gen1 = "DISKertasMMKNPTG_NS.rdf";
                    code1 = "MMKS";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("sedia_kertas_makluman"))) {
                    gen1 = "DISKertasMakluman.rdf";
                    code1 = "MKLM";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("tandatangan_5a")) {
                    gen1 = "DISSuratKelulusan_MLK.rdf";
                    //code1 = "SL";
                    code1 = "N5A";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    
                    /*gen2 = "DIS_PTK_Borang5A.rdf";
                    code2 = "N5A";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());*/
                }
                if ((stage.contentEquals("g_penyediaan_pu_pt"))) {
//                    gen1 = "DISSuratIringanPU.rdf";
                    gen1 = "DISBPU_NS.rdf";
                    code1 = "SIPU";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    
//                    gen2 = "DISPermohonanUkur.rdf";
                    gen2 = "DISSIPU.rdf";
                    code2 = "PU";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("semak_draf_ptd1"))) {
                    gen1 = "DISKPPindaPTD_NS.rdf";
                    code1 = "KPTD";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("semak_draf_ptd2"))) {
                    gen1 = "DISKertasPTD_PindaLuas.rdf";
                    code1 = "KPTD";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("sedia_srt_byrn_tmbhn"))) {
//                    gen1 = "DISSrtBayaranTambahan.rdf";
                    gen1 = "DISSTBT_NS.rdf";
                    code1 = "STPT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("semak_draf_mmk2_1"))) {
//                    gen1 = "DISKertasMMK_PindaLuas.rdf";
                    gen1 = "DISKMMKNPKPTD_NS.rdf";
                    code1 = "MMKS";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("semak_draf_mmk2_2")) || (stage.contentEquals("semak_huraian_syor2_3"))) {
                    gen1 = "DISKertasMMK_PindaLuas.rdf";
                    code1 = "MMKS";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("sedia_srt_byrn_tmbhn2"))) {
                    gen1 = "DISSrtBayaranTambahan.rdf";
                    code1 = "STPT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("semak_surat_keputusan"))) {
                    gen1 = "DISSrtKeputusanLPS.rdf";
                    code1 = "SKPN";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if ((stage.contentEquals("semak_borang_4a"))) {
                    gen1 = "DISBorang4A.rdf";
                    code1 = "4A";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
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
