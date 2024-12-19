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
import etanah.service.PengambilanService;
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
public class UrusanBahanBatuanReportValidator implements StageListener {

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
    PengambilanService pengambilanService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private static final Logger LOGGER = Logger.getLogger(UrusanBahanBatuanReportValidator.class);
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

                if (kodNegeri.equalsIgnoreCase("04")) {	// melaka
                    generateRptMelaka(l, ku, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
                } else if (kodNegeri.equalsIgnoreCase("05")) {	// ns
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

        senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        senaraiRujukanLuar = plpservice.getSenaraiRujLuarByIDPermohonanAgensiADUN(p.getIdPermohonan(), "ADN");
        for (Task t : l) {
            if (t.getSystemAttributes().getStage() != null) {
                stage = t.getSystemAttributes().getStage();
                LOGGER.info(stage);
                //Semua batuan untuk report jabatan teknikal and Adun
                if (stage.contentEquals("kenalpasti_jabatan_teknikal")) {

                    gen1 = "DISSUT_MLK.rdf";
                    code1 = "SUT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    if (senaraiRujukanLuar.size() > 0) {
                        gen2 = "DISSMUYB_MLK.rdf";
                        code2 = "SUA";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                } else if (stage.contentEquals("05ASediaSuratJabTek")) {
                    gen1 = "DISSUT_MLK.rdf";
                    code1 = "SUT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    gen2 = "DISSMUYB_MLK.rdf";
                    code2 = "SUA";
                    kd = kodDokumenDAO.findById(code2);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                } else if (stage.contentEquals("14PmlhanPsrta")) {

                    gen1 = "DISSUT_MLK.rdf";
                    code1 = "SUT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    if (senaraiRujukanLuar.size() > 0) {
                        gen2 = "DISSMUYB_MLK.rdf";
                        code2 = "SUA";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                //Semua batuan untuk surat syor penolakan
                if (stage.contentEquals("semak_surat_tolak")) {
                    gen1 = "DISSMTPTD_MLK.rdf";
                    code1 = "SMT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }

                //Semua batuan untuk laporan tanah
                if (p.getKodUrusan().getKod().equalsIgnoreCase("LPSP")) {
                    if (stage.contentEquals("laporan_tanah")) {
                        gen1 = "DISLTLPSP_MLK.rdf";
//                                code1 = "LTPD";
                        code1 = "LT";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                } else {
                    if (stage.contentEquals("laporan_tanah")) {
                        if (p.getKodUrusan().getKod().equals("PBPTD")
                                || p.getKodUrusan().getKod().equals("PBPTG")
                                || p.getKodUrusan().getKod().equals("MMMCL") 
                                || p.getKodUrusan().getKod().equals("PBMT") 
                                || p.getKodUrusan().getKod().equals("PPBB")) {
                            gen1 = "DISLTPLPS_MLK_PBMT.rdf";
//                                code1 = "LTPD";
                            code1 = "LT";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        } else {
                            gen1 = "DISLTPPBB_MLK.rdf";
//                                code1 = "LTPD";
                            code1 = "LT";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    }
                }

//                     if(stage.contentEquals("laporan_tanah")){
////                             gen1 = "DISLTPPBB_MLK.rdf";
//                               gen1 = "DISLTPLPS_MLK.rdf";
////                                code1 = "LTPD";
//                                code1 = "LT";
//                                kd = kodDokumenDAO.findById(code1);
//                                LOGGER.info(kd);
//                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
//                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
//                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                        } Comment kerana persamaan nama stage ID;
                //Untuk draf jkbb (PPBB)
                if (stage.contentEquals("sedia_dokumen")) {
//                        gen1 = "DISRJKBBPTG_MLK.rdf";
                    gen1 = "DISRJKBBPTGPPBB_MLK.rdf";
                    code1 = "JKBB";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("sedia_dokumen2")) {
//                        gen1 = "DISRJKBBPTG_MLK.rdf";
                    gen1 = "DISRKMPTGPPBB_MLK.rdf";
                    code1 = "RPYAB";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                //Untuk draf PTD (PBPTD)
                if (p.getKodUrusan().getKod().equalsIgnoreCase("PBPTD")) {
                    if (stage.contentEquals("semak_draf_rencana_ptd2")) {
                        gen1 = "DISRJKBBPTD_MLK.rdf";
//                            code1 = "JKBB";
                        code1 = "RPPTD";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                if (stage.contentEquals("perakuan_draf_rencana_ptd")) {
                    gen1 = "DISRJKBBPTD_MLK.rdf";
                    code1 = "JKBB";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                //Untuk draf PTG (PBPTG)
                if (stage.contentEquals("semakan_rencana2")) {
                    gen1 = "DISRJKBBPTG_MLK.rdf";
//                        code1 = "JKBB";
                    code1 = "RPPTG";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }


                //Untuk keluar surat wang cagaran tidak dipulangkan
                if (stage.contentEquals("sedia_surat_tolak")) {
                    gen1 = "DISWC_MLK.rdf";
//                                code1 = "SWC";
                    code1 = "SWCTP";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                //Untuk surat lulus atau tolak based on keputusan (PBPTD)
//                    if (p.getKodUrusan().getKod().equals("PBPTD")) {
//                        if (p.getKeputusan() != null) {
                if (stage.contentEquals("sedia_surat_lulus")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        gen1 = "DISSKpsnLPBPTD_MLK.rdf";
                        code1 = "SL";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        gen2 = "DISSKpsnGPBPTD_MLK.rdf";
                        code2 = "STP";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                }
                if (stage.contentEquals("sedia_surat_lulustolak")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        gen1 = "DISSKpsnLPBPTD_MLK.rdf";
                        code1 = "SL";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        gen2 = "DISSKpsnGPBPTD_MLK.rdf";
                        code2 = "STP";
                        kd = kodDokumenDAO.findById(code2);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                }
//                        }
//
//                    }
                //Untuk surat lulus atau tolak based on keputusan (PPBB)
//                    if (p.getKodUrusan().getKod().equals("PPBB")) {
//
//                        if (p.getKeputusan() != null) {
                if (stage.contentEquals("sedia_surat_kelulusan")) {
                    LOGGER.info("KPSN----" + p.getKeputusan().getKod());
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        gen1 = "DISSrtKpsnMMKNPBPTGL_MLK.rdf";
                        code1 = "SKM";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {

                        gen2 = "DISSrtKpsnMMKNPBPTGG_MLK.rdf";
                        code2 = "SKM";
                        kd = kodDokumenDAO.findById(code2);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                }
                if (stage.contentEquals("semak_surat_kelulusan")) {
                    LOGGER.info("KPSN----" + p.getKeputusan().getKod());
                    if (p.getKeputusan().getKod().equalsIgnoreCase("A")) {
                        gen1 = "DISSKpsnLPPBB_MLK.rdf";
                        code1 = "SL";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("XA")) {

                        gen2 = "DISSKpsnG_MLK.rdf";
                        code2 = "STP";
                        kd = kodDokumenDAO.findById(code2);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                }

                if (stage.contentEquals("15SediaSurat")) {
                    LOGGER.info("KPSN----" + p.getKeputusan().getKod());
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
//                                    gen1 = "DISSLPSPL_MLK.rdf";
                        gen1 = "DISSKpsnLLPSP_MLK.rdf";
                        code1 = "SL";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {

                        gen2 = "DISSLPSPG_MLK.rdf";
                        code2 = "STP";
                        kd = kodDokumenDAO.findById(code2);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                }
//                        }   
//                    }

                //Untuk surat lulus atau tolak based on keputusan (PBPTG)
                if (p.getKodUrusan().getKod().equals("PBPTG")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equalsIgnoreCase("A")) {
                            if (stage.contentEquals("semak_surat_lulus")) {
                                gen1 = "DISSLulusPPBB_MLK.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            }
                        } else if (p.getKeputusan().getKod().equalsIgnoreCase("XA")) {
                            if (stage.contentEquals("semak_surat_tolak")) {
                                gen2 = "DISSGagalPPBB_MLK.rdf";
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
                }
                if (p.getKodUrusan().getKod().equals("PRMP")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equalsIgnoreCase("A")) {
                            if (stage.contentEquals("sedia_surat_kelulusan")) {
                                gen1 = "DISSPRMPL_MLK.rdf";
                                code1 = "SL";
                                kd = kodDokumenDAO.findById(code1);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            }
                        } else if (p.getKeputusan().getKod().equalsIgnoreCase("XA")) {
                            if (stage.contentEquals("semak_surat_tolak")) {
                                gen2 = "DISSPRMPG_MLK.rdf";
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
                }



            } else {
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
            if (t.getSystemAttributes().getStage() != null) {
                stage = t.getSystemAttributes().getStage();
                LOGGER.info(stage);

            } else {
                // do nothing
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        if (context.getStageName().equals("kenalpasti_jabatan_teknikal")) {
            Permohonan permohonan = context.getPermohonan();
            senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
            if (permohonan.getKodUrusan().getKod().equals("PTSP") || permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("MLCRG") || permohonan.getKodUrusan().getKod().equals("PBGSA")) {
                senaraiRujukanLuar = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi1(permohonan.getIdPermohonan());
            } else {
                senaraiRujukanLuar = plpservice.getSenaraiRujLuarByIDPermohonanAgensiADUN(permohonan.getIdPermohonan(), "ADN");
            }

            if (!permohonan.getKodUrusan().getKod().equals("PPBB") && !permohonan.getKodUrusan().getKod().equals("PBPTD") && !permohonan.getKodUrusan().getKod().equals("PBPTG") && !permohonan.getKodUrusan().getKod().equals("PTSP") && !permohonan.getKodUrusan().getKod().equals("PHLP") && !permohonan.getKodUrusan().getKod().equals("PJLB") && !permohonan.getKodUrusan().getKod().equals("MPJLB") && !permohonan.getKodUrusan().getKod().equals("LMCRG") && !permohonan.getKodUrusan().getKod().equals("MLCRG") && !permohonan.getKodUrusan().getKod().equals("PBGSA") && !permohonan.getKodUrusan().getKod().equals("LSTP") && !permohonan.getKodUrusan().getKod().equals("LPJH")) {
                if (senaraiRujukanLuar.isEmpty()) {
                    context.addMessage("Sila pilih ADUN kawasan berkenaan untuk diminta ulasan.");
                    return null;

                } else if (senaraiRujukanLuar.size() > 1) {
                    context.addMessage("Sila pilih satu ADUN untuk satu kawasan sahaja");
                    return null;
                }
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
