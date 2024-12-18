/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;

import etanah.model.Pengguna;
import etanah.model.PermohonanNota;
import etanah.report.ReportUtil;
import etanah.service.EnforceService;
import etanah.service.common.DokumenService;
import etanah.service.common.FasaPermohonanService;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.workflow.WorkFlowService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import org.hibernate.Session;


import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PegawaiPenyiasatDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKlasifikasi;
import etanah.model.PegawaiPenyiasat;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.BPelService;
import etanah.service.SemakDokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.LelongService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/agih_tugasan_penyiasat")
public class AgihTugasanPenyiasatActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(AgihTugasanPenyiasatActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    PegawaiPenyiasatDAO pegawaiSiasatDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    LelongService lelongService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    private Pengguna pengguna;
    private PegawaiPenyiasat pegawaiSiasat;
    private List<Pengguna> senaraiPengguna;
    private String perananPengguna;
    private String nama;
    private String jawatan;
    private String tarikhLantik;
    private String idPermohonan;
    private String idPengguna;
    private Permohonan permohonan;
    private String noPengenalan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pengguna selectedPengguna;
    IWorkflowContext ctx = null;
    private boolean isBerangkai = Boolean.FALSE;
    private List<Permohonan> senaraiPermohonanBerangkai;
    private String kodNegeri;
    private long idDokumen;
    private PermohonanNota permohonanNota;
    private String stageId;
    private String keputusan;
    private List<Permohonan> senaraiPermohonanBaru;
    private List<PegawaiPenyiasat> senaraiPegawaiPenyiasat;
    IWorkflowContext ctxOnBehalf = null;
    private String nextStage;
    private String taskId;
    private String messageAgihTugasan = "";
    private boolean reportSPPP = Boolean.FALSE;
    private boolean statusNotaExist = Boolean.TRUE;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/agih_tugasan_penyiasat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");


        if (idPermohonan != null) {
            try {
                senaraiPermohonanBaru = enforceService.findSenaraiPermohonanBaru(idPermohonan);

                senaraiPegawaiPenyiasat = enforceService.findAllPenyiasat(idPermohonan);
                permohonan = permohonanDAO.findById(idPermohonan);

                logger.info(":::::senaraiPermohonanBaru size : " + senaraiPermohonanBaru.size());
                logger.info(":::::senaraiPegawaiPenyiasat size : " + senaraiPegawaiPenyiasat.size());

                BPelService service = new BPelService();

                if (StringUtils.isNotBlank(taskId)) {
                    Task task = null;
                    task = service.getTaskFromBPel(taskId, pengguna);
                    if (task != null) {
                        stageId = task.getSystemAttributes().getStage();
                        logger.info("--------------stage Id BPEL ON--------------- : " + stageId);
                    }
                } else {
                    stageId = "keputusan_op";
                    logger.info("--------------stage Id BPEL OFF--------------- : " + stageId);
                }

                List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();

                for (FasaPermohonan fp : senaraiFasa) {
                    if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                        if (fp.getKeputusan() != null) {
                            keputusan = fp.getKeputusan().getKod();
                            logger.info("--------------keputusan--------------- : " + keputusan);
                        }
                    }
                }

                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                        int countReport = 0;
                        for (int i = 0; i < senaraiPermohonanBaru.size(); i++) {
                            Permohonan p = senaraiPermohonanBaru.get(i);
                            try {
                                FolderDokumen f = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());
                                List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(f.getFolderId(), "SPPP");
                                for (int j = 0; j < listDokumen.size(); j++) {
                                    Dokumen d = listDokumen.get(0).getDokumen();
                                    if (d != null) {
                                        countReport = countReport + 1;
                                        //reportSPPP = Boolean.TRUE;
                                        System.out.println("countReport :::: " + countReport);

                                    }

                                }
                            } catch (Exception ex) {
                                logger.error(ex);
                            }
                        }

                        if (senaraiPermohonanBaru.size() == countReport) {
                            reportSPPP = Boolean.TRUE;
                        }
                    }


                    System.out.println("reportSPPP ::: " + reportSPPP);
                }
                System.out.println("-------------stageId--" + stageId);

                permohonanNota = enforcementService.findEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
                logger.info("--------------Permohonan Nota Wujud or Not------------- : " + permohonanNota);
                if (permohonanNota != null) {
                    logger.info("::: kandungan nota :" + permohonanNota.getNota());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

    }

    public Resolution agihTugasan() throws WorkflowException, StaleObjectException, Exception {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                permohonanNota = enforcementService.findNotaMinit(permohonan.getIdPermohonan(), stageId);
                if ((permohonanNota == null) || (permohonanNota != null && StringUtils.isEmpty(permohonanNota.getNota()))) {
//                    logger.info("Hand");
                    messageAgihTugasan = "Sila isikan maklumat Nota Minit terlebih dahulu";
                    return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", messageAgihTugasan);
                }
            }
        }
        logger.info("------------agihTugasan--------------");

        logger.info("::::::size senarai permohonan baru : " + senaraiPermohonanBaru.size());

        //1)Initiate task for new id permohonan
        for (int i = 0; i < senaraiPermohonanBaru.size(); i++) {

            Permohonan permohonanBaru = senaraiPermohonanBaru.get(i);
            initiateTask(permohonanBaru, pengguna);
        }


        //2)Skip stage to buka kes based on selected user(ketua pegawai penyiasat)
        logger.info("::::::size senarai pegawai penyiasat: " + senaraiPegawaiPenyiasat.size());

        for (int j = 0; j < senaraiPegawaiPenyiasat.size(); j++) {
            PegawaiPenyiasat pegawaiPenyiasat = senaraiPegawaiPenyiasat.get(j);
            if (pegawaiPenyiasat.getStatusPeranan().equalsIgnoreCase("K")) {
                selectedPengguna = penggunaDAO.findById(pegawaiPenyiasat.getNamaJabatan());
                logger.info("::::::Tugasan akan dihantar kepada: " + selectedPengguna.getIdPengguna() + "(" + selectedPengguna.getNama() + ")");
                messageAgihTugasan += pegawaiPenyiasat.getPermohonan().getIdPermohonan() + ": Agihan Tugasan Berjaya kepada " + selectedPengguna.getIdPengguna() + "(" + selectedPengguna.getNama() + "). \n ";
                skipStage(pegawaiPenyiasat.getPermohonan(), selectedPengguna);
            }
        }

        System.out.println("messageAgihTugasan :" + messageAgihTugasan);

        messageAgihTugasan = messageAgihTugasan.replace("\n", "<br>");

        List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);

        if (l.isEmpty()) {
            try {
                Thread.sleep(5000);
                l = WorkFlowService.queryTasksByAdmin(idPermohonan);
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
        if (l != null) {
            for (Task t : l) {
                taskId = t.getSystemAttributes().getTaskId();
                if (StringUtils.isNotBlank(taskId)) {
                    try {
                        System.out.println("task id :::: " + taskId);
                        WorkFlowService.withdrawTask(taskId);
                        logger.info("Pembatalan Berjaya :: " + idPermohonan);
                    } catch (StaleObjectException ex) {
                        ex.printStackTrace();
                        logger.error(ex);
                    }
                }
            }
        }

        PermohonanNota nota = enforcementService.findNotEmptyNotaMinit(permohonan.getIdPermohonan(), stageId);
        if (nota != null) {
            logger.info("::: update status nota to T = tidak aktif ::: ");
            nota.setStatusNota('T');
            enforceService.simpanNota(nota);
        }

        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", messageAgihTugasan);
    }

    public void initiateTask(Permohonan permohonanBaru, Pengguna pengguna) {
        logger.info("-------initiateTask------- :::: " + permohonanBaru.getIdPermohonan());

        //initiate tugasan
        try {
            if (permohonanBaru.getKodUrusan().getKePTG() == 'Y') {
                WorkFlowService.initiateTask(permohonanBaru.getKodUrusan().getIdWorkflow(),
                        permohonanBaru.getIdPermohonan(), permohonanBaru.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                        permohonanBaru.getKodUrusan().getNama());
            } else if (permohonanBaru.getKodUrusan().getKePTG() == 'T') {
                WorkFlowService.initiateTask(permohonanBaru.getKodUrusan().getIdWorkflow(),
                        permohonanBaru.getIdPermohonan(), permohonanBaru.getCawangan().getKod(), pengguna.getIdPengguna(),
                        permohonanBaru.getKodUrusan().getNama());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void skipStage(Permohonan permohonanBaru, Pengguna selectedPengguna) throws WorkflowException, StaleObjectException, Exception {

        logger.info("------------- SKIP STAGE ---------------");

        ctxOnBehalf = WorkFlowService.authenticate("mtpptdkuasa1"); //pptd
        if (ctxOnBehalf != null) {
            System.out.println("ctxOnBehalf : " + ctxOnBehalf);
            System.out.println("id mohon : " + permohonanBaru.getIdPermohonan());

            List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru.getIdPermohonan());
            logger.info("1) Task FOund(size)::" + l.size());
            if (l.isEmpty()) {
                try {
                    Thread.sleep(5000);
                    l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru.getIdPermohonan());
                } catch (Exception ex) {
                    logger.error(ex);
                }
            }
            logger.info("2) Task FOund (size)::" + l.size());
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                taskId = t.getSystemAttributes().getTaskId();
                System.out.println("id mohon task id : " + t.getSystemMessageAttributes().getTextAttribute1());

                WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                logger.debug("Claim Found Task::" + taskId);
                if (permohonanBaru.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonanBaru.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonanBaru.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    System.out.println("----- for sek426 OR sek425 OR sek425A----- ");
                    //nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "LF"); // 
                    Task task = WorkFlowService.reassignTask(ctxOnBehalf, taskId, selectedPengguna.getIdPengguna(), "user", "IP");
                    nextStage = task.getSystemAttributes().getStage();

                    if (StringUtils.isNotBlank(keputusan)) {
                        if (keputusan.equalsIgnoreCase("PE") || keputusan.equalsIgnoreCase("BR")) {
                            createAndSkipPermohonan();
                        }
                    }
                }


                logger.debug("current stage id ::::::::::::::::" + stageId);
                logger.debug("next stage ::::::::::::::::" + nextStage);
                logger.debug("Tugasan dihantar ke : " + nextStage);
            }
        }
    }

    public Resolution janaDokumen() {
        logger.info("------------JanaDokumen--------------");

        for (int j = 0; j < senaraiPegawaiPenyiasat.size(); j++) {
            PegawaiPenyiasat pegawaiPenyiasat = senaraiPegawaiPenyiasat.get(j);

            try {

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    //for Melaka, need to generate report for Perlantikan Pegawai Penyiasat
                    logger.info("------------generate report for Melaka : ENFSPPP_MLK --------------");

                    String[] params = new String[]{"p_id_mohon"};
                    String[] values = new String[]{pegawaiPenyiasat.getPermohonan().getIdPermohonan()};
                    String path = "";
                    String dokumenPath = conf.getProperty("document.path");
                    Dokumen d = null;
                    KodDokumen kd = null;

                    FolderDokumen fd = folderDokumenDAO.findById(pegawaiPenyiasat.getPermohonan().getFolderDokumen().getFolderId());
                    String reportName = "";

                    kd = kodDokumenDAO.findById("SPPP");
                    reportName = "ENFSPPP_MLK.rdf";
                    d = saveOrUpdateDokumen(fd, kd, pegawaiPenyiasat.getPermohonan().getIdPermohonan());
                    path = pegawaiPenyiasat.getPermohonan().getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    idDokumen = d.getIdDokumen();
                    logger.info("-------id dokumen : --------" + idDokumen);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        rehydrate();
        addSimpleMessage("Dokumen untuk perlantikkan pegawai penyiasat telah berjaya di jana. Sila semak dokumen tersebut.");
        return new JSP("penguatkuasaan/agih_tugasan_penyiasat.jsp").addParameter("tab", "true");
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            logger.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            logger.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        logger.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk("Surat Pelantikan Pengawai Penyiasat");
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

    private void createAndSkipPermohonan() throws WorkflowException, StaleObjectException, Exception {
        logger.info("------------- createAndSkipPermohonan ---------------");
        InfoAudit ia = new InfoAudit();
        Permohonan permohonanBaru = new Permohonan();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());


        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        String idPermohonanBaru = idPermohonanGenerator.generate(
                ctx.getKodNegeri(), pengguna.getKodCawangan(), permohonan.getKodUrusan());

        //create id mohon baru
        permohonanBaru.setStatus("TA");
        permohonanBaru.setIdPermohonan(idPermohonanBaru);
        permohonanBaru.setCawangan(pengguna.getKodCawangan());
        permohonanBaru.setKodUrusan(permohonan.getKodUrusan());
        permohonanBaru.setCaraPermohonan(permohonan.getCaraPermohonan());
        permohonanBaru.setSebab(permohonan.getSebab());
        permohonanBaru.setPenyerahNama(permohonan.getPenyerahNama());
        permohonanBaru.setPenyerahJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
        permohonanBaru.setPenyerahNoPengenalan(permohonan.getPenyerahNoPengenalan());
        permohonanBaru.setPenyerahAlamat1(permohonan.getPenyerahAlamat1());
        permohonanBaru.setPenyerahAlamat2(permohonan.getPenyerahAlamat2());
        permohonanBaru.setPenyerahAlamat3(permohonan.getPenyerahAlamat3());
        permohonanBaru.setPenyerahAlamat4(permohonan.getPenyerahAlamat4());
        permohonanBaru.setPenyerahPoskod(permohonan.getPenyerahPoskod());
        permohonanBaru.setPenyerahNegeri(permohonan.getPenyerahNegeri());
        permohonanBaru.setPenyerahNoTelefon1(permohonan.getPenyerahNoTelefon1());
        permohonanBaru.setPenyerahEmail(permohonan.getPenyerahEmail());
        permohonanBaru.setPermohonanSebelum(permohonan);
        permohonanBaru.setInfoAudit(ia);

        enforceService.savePermohonan(permohonanBaru);

        if (permohonanBaru.getFolderDokumen() == null) {
            FolderDokumen fd = new FolderDokumen();
            fd.setTajuk(permohonanBaru.getIdPermohonan());
            fd.setInfoAudit(ia);
            enforceService.simpanFolderDokumen(fd);
            permohonanBaru.setFolderDokumen(fd);
        }

        enforceService.savePermohonan(permohonanBaru);

        initiateTask(permohonanBaru, pengguna);

        logger.info("------------- SKIP STAGE SECOND ID ---------------");

        ctxOnBehalf = WorkFlowService.authenticate("mtpptdkuasa1"); //pptd
        if (ctxOnBehalf != null) {
            System.out.println("ctxOnBehalf : " + ctxOnBehalf);
            System.out.println("id mohon : " + permohonanBaru.getIdPermohonan());

            List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru.getIdPermohonan());
            logger.info("1) Task FOund(size)::" + l.size());
            if (l.isEmpty()) {
                try {
                    Thread.sleep(5000);
                    l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru.getIdPermohonan());
                } catch (Exception ex) {
                    logger.error(ex);
                }
            }
            logger.info("2) Task FOund (size)::" + l.size());
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                taskId = t.getSystemAttributes().getTaskId();
                WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                logger.debug("Claim Found Task::" + taskId);
                nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "BR"); // 

                logger.debug("current stage id ::::::::::::::::" + stageId);
                logger.debug("next stage ::::::::::::::::" + nextStage);
                logger.debug("Tugasan dihantar ke : " + nextStage);
                messageAgihTugasan += permohonanBaru.getIdPermohonan() + " : Penghantaran Berjaya ke Keputusan Arah Jual. \n \n";
                System.out.println("::::::" + messageAgihTugasan);
            }
        }
//        return messageAgihTugasan;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPerananPengguna() {
        return perananPengguna;
    }

    public void setPerananPengguna(String perananPengguna) {
        this.perananPengguna = perananPengguna;
    }

    public String getTarikhLantik() {
        return tarikhLantik;
    }

    public void setTarikhLantik(String tarikhLantik) {
        this.tarikhLantik = tarikhLantik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PegawaiPenyiasat getPegawaiSiasat() {
        return pegawaiSiasat;
    }

    public void setPegawaiSiasat(PegawaiPenyiasat pegawaiSiasat) {
        this.pegawaiSiasat = pegawaiSiasat;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getSelectedPengguna() {
        return selectedPengguna;
    }

    public void setSelectedPengguna(Pengguna selectedPengguna) {
        this.selectedPengguna = selectedPengguna;
    }

    public List<Permohonan> getSenaraiPermohonanBerangkai() {
        return senaraiPermohonanBerangkai;
    }

    public void setSenaraiPermohonanBerangkai(List<Permohonan> senaraiPermohonanBerangkai) {
        this.senaraiPermohonanBerangkai = senaraiPermohonanBerangkai;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public List<Permohonan> getSenaraiPermohonanBaru() {
        return senaraiPermohonanBaru;
    }

    public void setSenaraiPermohonanBaru(List<Permohonan> senaraiPermohonanBaru) {
        this.senaraiPermohonanBaru = senaraiPermohonanBaru;
    }

    public List<PegawaiPenyiasat> getSenaraiPegawaiPenyiasat() {
        return senaraiPegawaiPenyiasat;
    }

    public void setSenaraiPegawaiPenyiasat(List<PegawaiPenyiasat> senaraiPegawaiPenyiasat) {
        this.senaraiPegawaiPenyiasat = senaraiPegawaiPenyiasat;
    }

    public String getMessageAgihTugasan() {
        return messageAgihTugasan;
    }

    public void setMessageAgihTugasan(String messageAgihTugasan) {
        this.messageAgihTugasan = messageAgihTugasan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isReportSPPP() {
        return reportSPPP;
    }

    public void setReportSPPP(boolean reportSPPP) {
        this.reportSPPP = reportSPPP;
    }

    public boolean isStatusNotaExist() {
        return statusNotaExist;
    }

    public void setStatusNotaExist(boolean statusNotaExist) {
        this.statusNotaExist = statusNotaExist;
    }

    public boolean isIsBerangkai() {
        return isBerangkai;
    }

    public void setIsBerangkai(boolean isBerangkai) {
        this.isBerangkai = isBerangkai;
    }
}
