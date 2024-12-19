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
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Notifikasi;
import org.hibernate.Session;
import etanah.model.KodPeranan;
//import etanah.model.KodRujukan;
//import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import java.util.Date;
import etanah.service.PembangunanService;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKategoriAgensi;
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
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
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
public class KemasukanPermohonanNotification implements StageListener {

    private static final Logger LOGGER = Logger.getLogger(KemasukanPermohonanNotification.class);
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
    GenerateIdPermohonan generateIdPerserahanWorkflow;
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
    KodKategoriAgensiDAO kodKategoriAgensiDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private static Pengguna pengguna;
    private String stage;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private List<HakmilikPihakBerkepentingan> senaraipihak;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    private boolean lanjutanTempoh;

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraipihak() {
        return senaraipihak;
    }

    public void setSenaraipihak(List<HakmilikPihakBerkepentingan> senaraipihak) {
        this.senaraipihak = senaraipihak;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan p = context.getPermohonan();
        pengguna = context.getPengguna();
        KodUrusan ku = p.getKodUrusan();
        List<PermohonanRujukanLuar> jt = devService.findUlasanJabatanTek(p.getIdPermohonan());
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
                    generateRptMelaka(l, ku, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath, jt);
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
            String dokumenPath, List<PermohonanRujukanLuar> jt) {

        for (Task t : l) {
            if (t.getSystemAttributes().getStage() != null) {
                stage = t.getSystemAttributes().getStage();
                LOGGER.info(stage);
                if (stage.contentEquals("kemasukan")) {

                    if (jt.size() > 0) {
                        if (check4ADUN(jt)) {

                            gen1 = "DEVSMUYB_MLK.rdf";
                            code1 = "SUA";
                            kd = kodDokumenDAO.findById(code1);
                            LOGGER.info(kd);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        }

                        gen2 = "DEVSUT_MLK.rdf";
                        code2 = "SUT";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                if (stage.contentEquals("semak_surat_tolak")) {
                    gen1 = "DISSrtMaklumanTolakPTD_MLK.rdf";
                    code1 = "SMT";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_draf_mmkn2")) {
                    gen1 = "DISKertasMMKNPTD_MLK.rdf";
                    code1 = "RMN";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_draf_mmkn3_3")) {
                    gen1 = "DISKertasMMKNPTG_MLK.rdf";
                    code1 = "RMN";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("rekod_keputusan_mmkn")) {
                    gen1 = "DISSrtKpsnMMKN_MLK.rdf";
                    LOGGER.info(gen1);
                    code1 = "SKM";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_surat_tolak2")) {
                    gen1 = "DISSrtTolakPTD_MLK.rdf";
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
                    gen1 = "DISSrtKelulusanLPS_MLK.rdf";
                    code1 = "SL";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("semak_borang_4a")) {
                    gen1 = "DISBrg4Ae_MLK.rdf";
                    code1 = "4A";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
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
                if (stage.contentEquals("kemasukan")) {
                    if (p.getKodUrusan().getKod().equalsIgnoreCase("TSPTD") || p.getKodUrusan().getKod().equalsIgnoreCase("TSPTG")
                            || p.getKodUrusan().getKod().equalsIgnoreCase("TSMMK") || p.getKodUrusan().getKod().equalsIgnoreCase("SBMS")
                            || p.getKodUrusan().getKod().equalsIgnoreCase("PSBT") || p.getKodUrusan().getKod().equalsIgnoreCase("TSPSS")
                            || p.getKodUrusan().getKod().equalsIgnoreCase("SBPS")) {

                        gen2 = "DEVSrtJPPH_NS.rdf";
                        code2 = "JPPH";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
//                    else {
//                        gen2 = "DEVSUT_NS.rdf";
//                        code2 = "SUT";
//                        kd = kodDokumenDAO.findById(code2);
//                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
//                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
//                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                    }

                    if (p.getKodUrusan().getKod().equalsIgnoreCase("PPCS") || p.getKodUrusan().getKod().equalsIgnoreCase("TSPTG")
                            || p.getKodUrusan().getKod().equalsIgnoreCase("TSMMK") || p.getKodUrusan().getKod().equalsIgnoreCase("SBMS")
                            || p.getKodUrusan().getKod().equalsIgnoreCase("PSMT") || p.getKodUrusan().getKod().equalsIgnoreCase("TSPSS")
                            || p.getKodUrusan().getKod().equalsIgnoreCase("SBPS")) {

                        String value = "";
                        BigDecimal luas = BigDecimal.ZERO;
                        BigDecimal luasMax = BigDecimal.ZERO;
                        BigDecimal limit = new BigDecimal(40);
                        //Permohonan permohonan = p.getIdPermohonan();
                        HakmilikPermohonan hakmilik;

                        List<HakmilikPermohonan> hm = devService.findHakmilikPermohonanByIdPermohonan(p.getIdPermohonan());
                        for (int ad1 = 0; ad1 < hm.size(); ad1++) {

                            hakmilik = hm.get(ad1);

                            if (ad1 == 0) {
                                luasMax = hakmilik.getHakmilik().getLuas();
                            } else if (luasMax.compareTo(hakmilik.getHakmilik().getLuas()) < 0) {
                                luasMax = hakmilik.getHakmilik().getLuas();
                            }
                        }

                        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
                            hakmilik = hm.get(ad1);
                            if (hakmilik.getHakmilik().getKodUnitLuas().getKod().equals("M")) {

                                luas = luasMax.divide(new BigDecimal(10000));

                                if (luas.compareTo(limit) >= 0) {
                                    value = "LD";
                                } else {
                                    value = "NM";
                                }
                            } else if (hakmilik.getHakmilik().getKodUnitLuas().getKod().equals("H")) {

                                luas = luasMax;

                                if (luas.compareTo(limit) >= 0) {
                                    value = "LD";
                                } else {
                                    value = "NM";
                                }
                            }
                        }

                        if (value.equalsIgnoreCase("LD")) {
                            gen2 = "DEVSUT_JKTL_NS.rdf";
                            code2 = "SUT";
                            kd = kodDokumenDAO.findById(code2);
                            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                            path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                        }
                    }
                }

                if (stage.contentEquals("sedia_surat_peringatan")) {
                    gen2 = "DISUlanganUlasanTeknikal_NS.rdf";
                    code2 = "PER";
                    kd = kodDokumenDAO.findById(code2);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
                if (stage.contentEquals("sedia_surat_tolak")) {
                    gen1 = "DISSuratMaklumanTolakMLK.rdf";
                    code1 = "SMT";
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
                    gen1 = "DISSuratIringanPU.rdf";
                    code1 = "SIPU";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    gen2 = "DISPermohonanUkur.rdf";
                    code2 = "PU";
                    kd = kodDokumenDAO.findById(code1);
                    LOGGER.info(kd);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
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
                    gen1 = "DISSrtBayaranTambahan.rdf";
                    code1 = "STPT";
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
//      doc.setTajuk(kd.getKod() + "-" + id);
        doc.setTajuk(kd.getKod() + "-" + kd.getNama());
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

        String kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equalsIgnoreCase("04")) {	// melaka
            Notifikasi n = new Notifikasi();
            n.setTajuk("Makluman Permohonan Modul Pembangunan");
            n.setMesej(context.getPermohonan().getKodUrusan().getNama() + "ID Permohonan : " + context.getPermohonan().getIdPermohonan() + " telah dihantar kepada Penolong Pegawai Tanah Kanan (Pembangunan) untuk tindakan selanjutnya.");
            Pengguna p = context.getPengguna();
            n.setCawangan(p.getKodCawangan());
            ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
//        list.add(kodPerananDAO.findById("53"));
//        list.add(kodPerananDAO.findById("9"));
            list.add(kodPerananDAO.findById("9")); //melaka - penolong pentadbir
            list.add(kodPerananDAO.findById("71"));//melaka - pembantu tadbir tertinggi
            list.add(kodPerananDAO.findById("225")); //melaka - pentadbir tanah
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new Date());
            n.setInfoAudit(ia);
            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
            initiate(context, proposedOutcome);
            context.addMessage("Makluman kepada Penolong Pentadbir/Pembantu Tadbir Tertinggi dan Pentadbir Tanah telah dihantar.");
        } else {
            initiate(context, proposedOutcome);
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    void initiate(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        boolean comparePTD = false;
        boolean comparePTG = false;
//        idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
//        String[] name = {"idHakmilik"};
//        Object[] value = {idHakmilik};
//        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
        List<HakmilikPermohonan> senaraiHP = permohonan.getSenaraiHakmilik();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikPTD = new ArrayList<Hakmilik>();
        List<Hakmilik> senaraiHakmilikPNDFTR = new ArrayList<Hakmilik>();
        for (int i = 0; i < senaraiHP.size(); i++) {
            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(i);
            Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            //simpan kod_bpm_baru --- request by GIS
            simpanKodBpmBaru(hp, h);
            //------
            senaraiHakmilik.add(h);
            if (permohonan.getKodUrusan().getKod().equals("SBMS") || permohonan.getKodUrusan().getKod().equals("PLPT")) {
                lanjutanTempoh = permohonan.getSebab().equals("Y");
                if (lanjutanTempoh) {
                    KodUrusan k = kodUrusanDAO.findById("PSPM");
                    List<Hakmilik> listHL = new ArrayList<Hakmilik>();
                    listHL.add(h);
                    generateIdPerserahanWorkflow.generateIdPerserahanWorkflow_Integration(k, peng, listHL, permohonan);

                }
            }
            if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("PN") || h.getKodHakmilik().getKod().equals("HSD")) {
                comparePTG = true;
                senaraiHakmilikPNDFTR.add(h);
            } else {
                comparePTD = true;
                senaraiHakmilikPTD.add(h);
            }
        }
        if (!senaraiHakmilik.isEmpty()) {
            //Need To generate before Noting
            prosesTukarGanti(peng, senaraiHakmilik);
        }

        KodUrusan kod = new KodUrusan();
        if (context.getPermohonan().getKodUrusan().getKod().equals("PPCS")) {
            kod = kodUrusanDAO.findById("PSM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("PPCB")) {
            kod = kodUrusanDAO.findById("PBM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("PYTN")) {
            kod = kodUrusanDAO.findById("CM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSKKT")) {
            kod = kodUrusanDAO.findById("TSSKM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSBSN")) {
            kod = kodUrusanDAO.findById("TSSKM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSPSN")) {
            kod = kodUrusanDAO.findById("TSSKM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSKSN")) {
            kod = kodUrusanDAO.findById("TSSKM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSPTD")) {
            kod = kodUrusanDAO.findById("TSSKM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSPTG")) {
            kod = kodUrusanDAO.findById("TSSKM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSMMK")) {
            kod = kodUrusanDAO.findById("TSSKM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("SBMS")) {
//            List<HakmilikPihakBerkepentingan> listTemp; 
//            senaraipihak = new ArrayList<HakmilikPihakBerkepentingan>();
//            for (int i = 0; i < senaraiHakmilik.size(); i++) {
//                listTemp = new ArrayList<HakmilikPihakBerkepentingan>();
//                hakmilik = senaraiHakmilik.get(i);
//                listTemp = devService.senaraiPihakBerkepentinganAktif(idHakmilik);
//                senaraipihak.addAll(listTemp);
//            }
//            if (senaraipihak.size()>0) {
//                KodUrusan kod2 = kodUrusanDAO.findById("SBKBG");
//                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow_Integration(kod2, peng, senaraiHakmilik, permohonan);
//
//            }
//            else{
            kod = kodUrusanDAO.findById("SBKSM");
//            }
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("PSMT")) {
            kod = kodUrusanDAO.findById("SBTM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("PSBT")) {
            kod = kodUrusanDAO.findById("SBSTM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("TSPSS")) {
            //kod = kodUrusanDAO.findById("PSTSM");
            kod = kodUrusanDAO.findById("SSKPM");
        } else if (context.getPermohonan().getKodUrusan().getKod().equals("SBPS")) {
            //used kodurusan untuk SBMS sebelum terlebih dahulu
            kod = kodUrusanDAO.findById("SBKSM");
        }
//        for (int i = 0; i < senaraiHakmilik.size(); i++) {
//            hakmilik = senaraiHakmilik.get(i);
//            senaraipihak = devService.senaraiPihakBerkepentingan(idHakmilik);
//        }
//        if (senaraipihak != null) {
//            KodUrusan kod2 = kodUrusanDAO.findById("SBKBG");
//            generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod2, peng, senaraiHakmilik, permohonan);
////             kod = kodUrusanDAO.findById("SBKSM");
//        }
//        LOG.info(kod.getNama());
//        LOG.info(permohonan.getFolderDokumen());
        //generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
        String kodNegeri = conf.getProperty("kodNegeri");

        if (kodNegeri.equals("05") && context.getPermohonan().getKodUrusan().getKod().equals("PYTN")) {
            if (comparePTD && comparePTG) {
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflowDiffHakmilik(kod, peng, senaraiHakmilik, permohonan);
            } else {
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow_Integration(kod, peng, senaraiHakmilik, permohonan);
            }
        } else {
            if (!senaraiHakmilikPTD.isEmpty()) {
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow_Integration(kod, peng, senaraiHakmilikPTD, permohonan);
            }
            if (!senaraiHakmilikPNDFTR.isEmpty()) {
                generateIdPerserahanWorkflow.generateIdPerserahanWorkflow_Integration(kod, peng, senaraiHakmilikPNDFTR, permohonan);
            }
        }

    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    private boolean check4ADUN(List<PermohonanRujukanLuar> jt) {
        boolean found = false;
        for (PermohonanRujukanLuar rujukLuar : jt) {
            KodAgensi agensi = rujukLuar.getAgensi();
            if (agensi != null) {
                KodKategoriAgensi katgAgensi = agensi.getKategoriAgensi();
                if (katgAgensi != null) {
                    katgAgensi = kodKategoriAgensiDAO.findById(katgAgensi.getKod());
                    if (katgAgensi.getKod().equalsIgnoreCase("ADN")) {
                        found = true;
                        break;
                    }
                }

            }
        }
        return found;
    }

    private void prosesTukarGanti(Pengguna pengguna, List<Hakmilik> senaraiHakmilik) {

        //urusan tukar ganti
        ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);
        String kodNegeri = conf.getProperty("kodNegeri");
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }

        if (!senaraiHakmilik.isEmpty()) {

            List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kodNegeri, pengguna, senaraiHakmilik);
            if (!senaraiPermohonanTukarGanti.isEmpty()) {
                for (Permohonan p : senaraiPermohonanTukarGanti) {
                    KodUrusan ku = p.getKodUrusan();
                    try {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                                p.getKodUrusan().getNama());

                        //fikri suruh pakai getidworkflow yang biasa
//                        WorkFlowService.initiateTask(ku.getIdWorkflowIntegration(),
//                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                            p.getKodUrusan().getNama());
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            }
        }

    }

    private void simpanKodBpmBaru(HakmilikPermohonan hp, Hakmilik h) {
        hp.setBandarPekanMukimBaru(h.getBandarPekanMukim());
        hakmilikPermohonanService.save(hp);

    }
}
