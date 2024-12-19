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
 * @author Administrator
 */
public class AdunValidator implements StageListener {

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
    private static final Logger LOGGER = Logger.getLogger(AdunValidator.class);
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
        //KodUrusan ku = p.getKodUrusan();
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
                if (stage.contentEquals("kenalpasti_jabatan_teknikal")) {
                    if (p.getKodUrusan().getKod().equals("PPRU")) {
                        gen1 = "DISSMUYBPPRU_MLK.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKodUrusan().getKod().equals("PRIZ")) {
                        gen1 = "DISSMUYBPRIZ_MLK.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKodUrusan().getKod().equals("PRMP")) {
                        gen1 = "DISSMUYBH_MLK.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else {
                        gen1 = "DISSMUYB_MLK.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                    }
                    if (p.getKodUrusan().getKod().equals("PRIZ")) {
                        gen2 = "DISSUTPRIZ_MLK.rdf";
                        code2 = "SUT";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (p.getKodUrusan().getKod().equals("PRMP")) {
                        gen2 = "DISSUTH_MLK.rdf";
                        code2 = "SUT";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else {
                        gen2 = "DISSUT_MLK.rdf";
                        code2 = "SUT";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }

                } else if (stage.contentEquals("sedia_surat_ulasan_jabtek")) {

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
                }
                if (p.getKodUrusan().getKod().equals("PBRZ")) {
                    if (stage.contentEquals("005_UlasanJabatanTeknikal")) {

                        gen1 = "DISSMUYBPBRZ_MLK.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        gen2 = "DISSUTPBRZ_MLK.rdf";
                        code2 = "SUT";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                if (p.getKodUrusan().getKod().equals("MLCRG")) {
                    if (stage.contentEquals("001_Kemasukan")) {
                        
                        gen1 = "DISSMUYBPBRZ_MLK.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        gen2 = "DISSUTMLCRG_MLK.rdf";
                        code2 = "SUT";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                 if (p.getKodUrusan().getKod().equals("MPJLB")) {
                    if (stage.contentEquals("001_Kemasukan")) {
                        
                        gen1 = "DISSMUYBMPJLB_MLK.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        gen2 = "DISSUTMPJLB_MLK.rdf";
                        code2 = "SUT";
                        kd = kodDokumenDAO.findById(code2);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
                
                 if (p.getKodUrusan().getKod().equals("PBMT")) {
                    if (stage.contentEquals("kenalpasti_jabatan_teknikal")) {
                        
                        gen1 = "DISSMUYB_MLK_PBMT.rdf";
                        code1 = "SUA";
                        kd = kodDokumenDAO.findById(code1);
                        LOGGER.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());

                        gen2 = "DISSUT_MLK_PBMT.rdf";
                        code2 = "SUT";
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
                if (stage.contentEquals("kenalpasti_jabatan_teknikal")) {
                    gen2 = "DISSuratUlasanTeknikal_NS.rdf";
                    code2 = "SUT";
                    kd = kodDokumenDAO.findById(code2);
                    d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                    path2 = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(gen2, params, values, dokumenPath + path2, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                }
            } else {
                // do nothing
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String kodNegeri = conf.getProperty("kodNegeri");
        if (context.getStageName().equals("kenalpasti_jabatan_teknikal") && kodNegeri.equalsIgnoreCase("04")) {
            Permohonan permohonan = context.getPermohonan();
            senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
            senaraiRujukanLuar = plpservice.getSenaraiRujLuarByIDPermohonanAgensiADUN(permohonan.getIdPermohonan(), "ADN");
            if (senaraiRujukanLuar.isEmpty()) {
                if (!permohonan.getKodUrusan().getKod().equals("PPRU") && !permohonan.getKodUrusan().getKod().equals("PLPS")) {
                    context.addMessage("Sila pilih ADUN kawasan berkenaan untuk diminta ulasan.");
                    return null;
                }
            } else if (senaraiRujukanLuar.size() > 1) {
                context.addMessage("Sila pilih satu ADUN untuk satu kawasan sahaja");
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
