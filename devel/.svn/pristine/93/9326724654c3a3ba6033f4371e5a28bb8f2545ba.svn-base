/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import etanah.dao.HakmilikDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import org.hibernate.Session;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.service.PembangunanService;

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
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanKos;
import etanah.report.ReportUtil;
import etanah.service.DevIntegrationService;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author nursyazwani
 */
public class SuratKeputusanValidator implements StageListener {

    private static final Logger LOGGER = Logger.getLogger(SuratKeputusanValidator.class);
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
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    private static Pengguna pengguna;
    private String stage;
    @Inject
    DevIntegrationService dis;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan p = context.getPermohonan();
        pengguna = context.getPengguna();
        String kodNegeri = conf.getProperty("kodNegeri");

        if (p != null) {
            try {

                String gen1 = "";
                String gen2 = "";
                String gen3 = "";
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
                    generateRptMelaka(l, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
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
    private void generateRptMelaka(List<Task> l, String gen1, String code1, KodDokumen kd, FolderDokumen fd, Dokumen d,
            String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2,
            String dokumenPath) {

        for (Task t : l) {
            if (t.getSystemAttributes().getStage() != null) {
                stage = t.getSystemAttributes().getStage();
                LOGGER.info(stage);
                if (stage.contentEquals("cetaksuratkpsnmmk")) {
                    if (p.getKeputusan().getKod().equals("L")) {
                        //                        Need report Surat Lulus MMKN
                        gen1 = ".rdf";
                        code1 = "SKMMK";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equals("T")) {
                        //                        Need report Surat Tolak MMKN
                        gen2 = ".rdf";
                        code2 = "SKMMK";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                // Added for RNS
                if (stage.contentEquals("rekodkpsncetaksurat")) {
                    if (p.getKeputusan().getKod().equals("NB")) {
                        //                        Need report Surat Premium Baru
                        gen1 = "DEVSrtKpsnRNSPTG_MLK.rdf";
                        code1 = "SRNSG";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equals("NK")) {
                        //                        Need report Surat Makluman - Tiada perubahan
                        gen2 = "DEVSrtTlkRNSPTG_MLK.rdf";
                        code2 = "STRNG";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                //Added for RPP
                if (stage.contentEquals("cetaksuratkelulusanb5a") || stage.contentEquals("semaksuratkelulusanb5a")) {
                    if (p.getKeputusan().getKod().equals("L")) {
                        //                        Need report Surat Premium Baru
                        gen1 = "DEVSrtLulusRPP_MLK.rdf";
                        code1 = "SLRY";
//                        gen1 = "DEVSrtKpsnRPPPTD_MLK.rdf";
//                        code1 = "SRPPD";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("SBMS")) {
                            gen2 = "DEVN5A_MLK.rdf";
                            code2 = "N5A";
                            kd = kodDokumenDAO.findById(code2);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        } else if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS")
                                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSN")
                                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKKT")
                                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKSN")) {

                            gen2 = "DEVN7G_MLK.rdf";
                            code2 = "NTS7G";
                            kd = kodDokumenDAO.findById(code2);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    } else if (p.getKeputusan().getKod().equals("T")) {
                        //                        Need report Surat Makluman - Tiada perubahan
                        gen2 = "DEVSrtTolakRPP_MLK.rdf";
                        code2 = "STRPP";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }

                //Added for RNS
                if (stage.contentEquals("sediasuratkelulusanb5a")) {
                    if (p.getKeputusan().getKod().equals("NB")) {
                        //                        Need report Surat Premium Baru
                        gen1 = "DEVSrtKpsnRNSPTD_MLK.rdf";
                        code1 = "SRNSD";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("SBMS")) {
                            gen2 = "DEVN5A_MLK.rdf";
                            code2 = "N5A";
                            kd = kodDokumenDAO.findById(code2);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        } else if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS")
                                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSN")
                                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKKT")
                                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSKSN")) {

                            gen2 = "DEVN7G_MLK.rdf";
                            code2 = "NTS7G";
                            kd = kodDokumenDAO.findById(code2);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    } else if (p.getKeputusan().getKod().equals("NK")) {
                        //                        Need report Surat Makluman - Tiada perubahan
                        gen2 = "DEVSrtTolakRNS_MLK.rdf";
                        code2 = "STRNS";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
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
                if (stage.contentEquals("cetaksuratkpsnmmk")) {
                    if (p.getKeputusan().getKod().equals("L")) {
                        if (p.getKodUrusan().getKod().equals("RPP")) {
                            LOGGER.info("Inside RPP");
                            gen1 = "DEVSrtRPP_NS.rdf";
                            code1 = "SRPPP";

                            PermohonanTuntutan permohonanTuntut;
                            permohonanTuntut = devService.findDokumenRayuan(p.getPermohonanSebelum().getIdPermohonan());
                            String kodDoc = permohonanTuntut.getKodTransaksiTuntut().getKod();

                            LOGGER.info("Id Permohonan Terdahulu : " + p.getPermohonanSebelum().getIdPermohonan() + "Kod Doc Generate : " + kodDoc);
                            if (kodDoc.equals("DEVN5A")) {
                                String gen3 = "DEVN5A_NS.rdf";
                                String code3 = "5AA";
                                kd = kodDokumenDAO.findById(code3);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            } else if (kodDoc.equals("DEVN7G")) {
                                String gen3 = "DEVBorang7G_NS.rdf";
                                String code3 = "N7G";
                                kd = kodDokumenDAO.findById(code3);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            }

                            String genmmk = "DEVKertasMMK_NS_R.rdf";
                            String codemmk = "MMK";
                            kd = kodDokumenDAO.findById(codemmk);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(genmmk, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        } else if (p.getKodUrusan().getKod().equals("RPS")) {
                            LOGGER.info("Inside RPS");
                            gen1 = "DEVSrtRPS_NS.rdf";
                            code1 = "SRPSE";

                            PermohonanTuntutan permohonanTuntut;
                            permohonanTuntut = devService.findDokumenRayuan(p.getPermohonanSebelum().getIdPermohonan());
                            String kodDoc = permohonanTuntut.getKodTransaksiTuntut().getKod();

                            LOGGER.info("Id Permohonan Terdahulu : " + p.getPermohonanSebelum().getIdPermohonan() + "Kod Doc Generate : " + kodDoc);
                            if (kodDoc.equals("DEVN5A")) {
                                String gen3 = "DEVN5A_NS.rdf";
                                String code3 = "5AA";
                                kd = kodDokumenDAO.findById(code3);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            } else if (kodDoc.equals("DEVN7G")) {
                                String gen3 = "DEVBorang7G_NS.rdf";
                                String code3 = "N7G";
                                kd = kodDokumenDAO.findById(code3);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            }
                        } else if (p.getKodUrusan().getKod().equals("RLTB")) {
                            LOGGER.info("Inside RLTB");
                            gen1 = "DEVSrtRLTB_NS.rdf";
                            code1 = "RBHS1";

                            PermohonanTuntutan permohonanTuntut;
                            permohonanTuntut = devService.findDokumenRayuan(p.getPermohonanSebelum().getIdPermohonan());
                            String kodDoc = permohonanTuntut.getKodTransaksiTuntut().getKod();

                            LOGGER.info("Id Permohonan Terdahulu : " + p.getPermohonanSebelum().getIdPermohonan() + "Kod Doc Generate : " + kodDoc);
                            if (kodDoc.equals("DEVN5A")) {
                                String gen3 = "DEVN5A_NS.rdf";
                                String code3 = "5AA";
                                kd = kodDokumenDAO.findById(code3);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            } else if (kodDoc.equals("DEVN7G")) {
                                String gen3 = "DEVBorang7G_NS.rdf";
                                String code3 = "N7G";
                                kd = kodDokumenDAO.findById(code3);
                                LOGGER.info(kd);
                                d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                                path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                                reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                            }
                        } else {
                            gen1 = "DEVSrtMintaDok_NS.rdf";
                            code1 = "SMDOK";
                        }
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equals("T")) {
                        gen2 = "DEVSrtTolak_NS.rdf";
                        code2 = "STLK";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                if (stage.contentEquals("cetaksuratkpsn3bln")) {
                    if (p.getKeputusan().getKod().equals("L")) {
                        gen1 = "DEVSrtRLTB_3B_NS.rdf";
                        code1 = "SLTTB";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        String gen3 = "DEVKertasMMK_NS_R_3B.rdf";
                        String code3 = "KPTD";
                        kd = kodDokumenDAO.findById(code3);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKeputusan().getKod().equals("T")) {
                        gen2 = "DEVSrtTolak_NS.rdf";
                        code2 = "SLTBG";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                if (stage.contentEquals("cetaksuratkpsn6bln")) {
                    if (p.getKeputusan().getKod().equals("L")) {
                        gen1 = "DEVSrtRLTB_6B_NS.rdf";
                        code1 = "SLTEB";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        String gen3 = "DEVKertasMMK_NS_R_6B.rdf";
                        String code3 = "KPTG";
                        kd = kodDokumenDAO.findById(code3);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen3, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        PermohonanTuntutan permohonanTuntut;
                        permohonanTuntut = devService.findDokumenRayuan(p.getPermohonanSebelum().getIdPermohonan());
                        String kodDoc = permohonanTuntut.getKodTransaksiTuntut().getKod();

                        LOGGER.info("Id Permohonan Terdahulu : " + p.getPermohonanSebelum().getIdPermohonan() + "Kod Doc Generate : " + kodDoc);
                        if (kodDoc.equals("DEVN5A")) {
                            String gen4 = "DEVN5A_NS_R.rdf";
                            String code4 = "5AA";
                            kd = kodDokumenDAO.findById(code4);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen4, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        } else if (kodDoc.equals("DEVN7G")) {
                            String gen5 = "DEVBorang7G_NS_R.rdf";
                            String code5 = "N7G";
                            kd = kodDokumenDAO.findById(code5);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen5, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    } else if (p.getKeputusan().getKod().equals("T")) {
                        gen2 = "DEVSrtTolak_NS.rdf";
                        code2 = "SLTBG";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                // Added on 16-12-2011
                if (stage.contentEquals("cetaksrtkpsnmmkbyrnhmilik") || stage.contentEquals("cetaksrtkpsnptdbyrnhmilik")
                        || stage.contentEquals("cetaksrtkpsnptgbyrnhmilik") || stage.contentEquals("cetaksrtkpsnmmktolak")
                        || stage.contentEquals("cetaksrtkpsntolak")) {

                    if (p.getKeputusan().getKod().equals("L")) {
                        gen1 = "DEVSrtKpsnLulus_NS.rdf";
                        //gen1 = "DEVSrtKpsnMintaByrn_NS.rdf";
                        code1 = "SL";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    } else if (p.getKeputusan().getKod().equals("T")) {
                        gen2 = "DEVSrtTolak_NS.rdf";
                        code2 = "STLK";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }


            } else {
                // do nothing
            }
        }
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
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        try {
            if ((permohonan.getKodUrusan().getKod().equals("RPP") && context.getStageName().equals("semaksuratkelulusanb5a"))
                    || (permohonan.getKodUrusan().getKod().equals("RNS") && context.getStageName().equals("cetaksuratkelulusanb5a"))) {

                LOGGER.info("----RPP && semaksuratkelulusanb5a / RNS && cetaksuratkelulusanb5a-----:" + permohonan.getIdPermohonan());
                PermohonanTuntutanKos mohonantuntutkosSBLM = devService.findMohonTuntutKosByPremium(permohonan.getPermohonanSebelum().getIdPermohonan());
                PermohonanTuntutanKos mohonantuntutkos = devService.findMohonTuntutKosByPremium(permohonan.getIdPermohonan());
                LOGGER.info("----mohonantuntutkosSBLM-----" + mohonantuntutkosSBLM);
                LOGGER.info("----mohonantuntutkos-----" + mohonantuntutkos);
                if (mohonantuntutkosSBLM != null && mohonantuntutkos != null) {
                    mohonantuntutkosSBLM.setAmaunTuntutan(mohonantuntutkos.getAmaunTuntutan());
                    devService.simpanPermohonanTuntutanKos(mohonantuntutkosSBLM);
                }
            }
            if (context.getPermohonan().getKodUrusan().getKod().equals("PPCS")) {
                KodUrusan kod = new KodUrusan();
                if (context.getStageName().equals("cetaksrtkpsnmmktolak")) {
                    if (context.getStageName().equals("rekodkpsnmmk")) {
                        if (permohonan.getKeputusan().getKod().equals("T")) {
                            kod = dis.findKodUrusan("PSB");
                        }
                        dis.intRegKelulusan(kod, pengguna, dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "16", "T", context.getStageName());
                    }
//                    proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                }
            }
            if ((permohonan.getKodUrusan().getKod().equals("RPP") || permohonan.getKodUrusan().getKod().equals("RPS")) && context.getStageName().equals("cetaksuratkpsnmmk")) {
                proposedOutcome = checkstageID(context);
            }
            //validate generate doc for RLTB
            if (permohonan.getKodUrusan().getKod().equals("RLTB") && (context.getStageName().equals("cetaksuratkpsn3bln") || context.getStageName().equals("cetaksuratkpsn6bln") || context.getStageName().equals("cetaksuratkpsnmmk"))) {                
                if (context.getStageName().equals("cetaksuratkpsn3bln")) {
                    boolean generated = false;
                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SLTTB") || kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SLTBG")) {
                            LOGGER.info("found!!" + kandunganFolder.getDokumen().getKodDokumen().getKod());
                            generated = true;
                            break;
                        }
                    }
                    if (!generated) {
                        context.addMessage("Sila jana dokumen terlebih dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (context.getStageName().equals("cetaksuratkpsn6bln")) {
                    boolean generated = false;
                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SLTEB") || kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SLTBG")) {
                            LOGGER.info("found!!" + kandunganFolder.getDokumen().getKodDokumen().getKod());
                            generated = true;
                            break;
                        }
                    }
                    if (!generated) {
                        context.addMessage("Sila jana dokumen terlebih dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }
                } else if (context.getStageName().equals("cetaksuratkpsnmmk")) {
                    boolean generated = false;
                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("RBHS1") || kandunganFolder.getDokumen().getKodDokumen().getKod().equals("STLK")) {
                            LOGGER.info("found!!" + kandunganFolder.getDokumen().getKodDokumen().getKod());
                            generated = true;
                            break;
                        }
                    }
                    if (!generated) {
                        context.addMessage("Sila jana dokumen terlebih dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }
                }
                proposedOutcome = checkstageID(context);
            }//validate generate doc for RPP 
            else if (permohonan.getKodUrusan().getKod().equals("RPP") && (context.getStageName().equals("cetaksuratkpsnmmk"))) {                         
                    boolean generated = false;
                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SRPPP") || kandunganFolder.getDokumen().getKodDokumen().getKod().equals("STLK")) {
                            LOGGER.info("found!!" + kandunganFolder.getDokumen().getKodDokumen().getKod());
                            generated = true;
                            break;
                        }
                    }
                    if (!generated) {
                        context.addMessage("Sila jana dokumen terlebih dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }                
                proposedOutcome = checkstageID(context);
            }//validate generate doc for RPS 
            else if (permohonan.getKodUrusan().getKod().equals("RPS") && (context.getStageName().equals("cetaksuratkpsnmmk"))) {                      
                    boolean generated = false;
                    for (KandunganFolder kandunganFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {

                        if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("SRPSE") || kandunganFolder.getDokumen().getKodDokumen().getKod().equals("STLK")) {
                            LOGGER.info("found!!" + kandunganFolder.getDokumen().getKodDokumen().getKod());
                            generated = true;
                            break;
                        }
                    }
                    if (!generated) {
                        context.addMessage("Sila jana dokumen terlebih dahulu : " + permohonan.getIdPermohonan());
                        return null;
                    }                
                proposedOutcome = checkstageID(context);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        System.out.println("Proposed outcome -->" + proposedOutcome);
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
        String outcome = "";
        try {
            outcome = checkstageID(context);
        } catch (WorkflowException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
        proposedOutcome = outcome;
        return proposedOutcome;
    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOGGER.info(stage);
            if (stage.contentEquals("cetaksuratkpsnmmk")) {
                if (permohonan.getKeputusan().getKod().equals("T")) {
                    //value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                    value = "T";
                    return value;
                } else if (permohonan.getKeputusan().getKod().equals("L")) {
                    value = "L";
                    return value;
                }
            }
            if (stage.contentEquals("cetaksuratkpsn3bln")) {
                if (permohonan.getKeputusan().getKod().equals("T")) {
                    value = "T";
                    //return value;
                } else if (permohonan.getKeputusan().getKod().equals("L")) {
                    value = "L";
                    //return value;
                }
            }
            if (stage.contentEquals("cetaksuratkpsn6bln")) {
                if (permohonan.getKeputusan().getKod().equals("T")) {
                    value = "T";
                    //return value;
                } else if (permohonan.getKeputusan().getKod().equals("L")) {
                    value = "L";
                    //return value;
                }
            }
        }
        return value;
    }

    public static Pengguna getPengguna() {
        return pengguna;
    }

    public static void setPengguna(Pengguna pengguna) {
        SuratKeputusanValidator.pengguna = pengguna;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
