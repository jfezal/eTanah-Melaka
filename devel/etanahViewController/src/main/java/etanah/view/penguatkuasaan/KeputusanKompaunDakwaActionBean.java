/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.dao.OperasiPenguatkuasaanPasukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanSaksiDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanSaksi;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/keputusan_kompaun_dakwa")
public class KeputusanKompaunDakwaActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KeputusanKompaunDakwaActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanSaksiDAO permohonanSaksiDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    OperasiPenguatkuasaanPasukanDAO operasiPenguatkuasaanPasukanDAO;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    private KodJenisPengenalan jenisPengenalan;
    private PermohonanSaksi permohonanSaksi;
    private String idSaksi;
    private Permohonan permohonan;
    private String nama;
    private KodNegeri negeri;
    private KodCawangan cawangan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String noPengenalan;
    private String negeri1;
    private String idPermohonan;
    private List<PermohonanSaksi> senaraiPermohonanSaksi;
    private String kodNegeriOrg;
    private String noTelefon;
    private String email;
    private String pekerjaan;
    private String kod;
    private String kp;
    private Pengguna pguna;
    SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
    private String idOp;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private Boolean opFlag = false;
    private List<OperasiPenguatkuasaan> listOp;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Long idOpBasedOnIdIP;
    private Boolean idIP = false;
    private String idOks;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private String keputusan;
    private int totalDakwa = 0;
    private int totalKompaun = 0;
    private int totalNFA = 0;
    private Permohonan permohonanBaru;
    private List<Permohonan> senaraiPermohonanBaru;
    private List<AduanOrangKenaSyak> senaraiOksForKompaunDakwa;
    private String idPermohonanForDakwa;
    private String idPermohonanForKompaun;
    private String idPermohonanForNFA;
    IWorkflowContext ctxOnBehalf = null;
    private String nextStage;
    private String taskId;
    private String messageAgihTugasan = "";
    private String stageId;

    @DefaultHandler
    public Resolution showForm() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        }
        return new JSP("/penguatkuasaan/keputusan_kompaun_dakwa.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_saksi.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                    senaraiOksIp = enforceService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());
                    logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                    if (!senaraiOksIp.isEmpty()) {
                        opFlag = true;
                        idIP = true;
                        idOpBasedOnIdIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                        logger.info("id operasi : " + idOpBasedOnIdIP);
                        logger.info("status idIP : " + idIP);

                        if (StringUtils.isNotBlank(idOpBasedOnIdIP.toString())) {
                            operasiPenguatkuasaan = enforceService.findByIdOp(idOpBasedOnIdIP);
                        }
                    } else {
                        logger.info("::: already created new permohonna");
                        senaraiPermohonanBaru = enforcementService.findSenaraiPermohonanBaruForOks(permohonan.getIdPermohonan());
                        logger.info("senaraiPermohonanBaru : " + senaraiPermohonanBaru.size());

                        if (permohonan.getPermohonanSebelum() != null) {
                            senaraiOksForKompaunDakwa = enforcementService.getListKompaunDakwaOks(permohonan.getPermohonanSebelum().getIdPermohonan());
                            logger.info("senaraiOksForKompaunDakwa : " + senaraiOksForKompaunDakwa.size());
                        }
                    }
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                }

            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }


        }
    }

    public Resolution refreshpage() {
        return new RedirectResolution(KeputusanKompaunDakwaActionBean.class, "locate");
    }

    public Resolution simpan() {
        logger.info("---------simpan---------");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        for (int i = 0; i < senaraiOksIp.size(); i++) {
            idOks = getContext().getRequest().getParameter("idOks" + i);
            keputusan = getContext().getRequest().getParameter("keputusan" + i);
            logger.info("keputusan :::: " + keputusan);
            if (StringUtils.isNotBlank(idOks)) {
                aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));

                if (aduanOrangKenaSyak != null) {
                    ia = aduanOrangKenaSyak.getInfoAudit();
                    ia.setDiKemaskiniOleh(pguna);
                    ia.setTarikhKemaskini(new java.util.Date());

                    aduanOrangKenaSyak.setStatusDakwaan(keputusan);
                    aduanOrangKenaSyak.setInfoAudit(ia);
                    enforceService.simpanAduanOrangKenaSyak(aduanOrangKenaSyak);
                }
            }
            if (StringUtils.isNotBlank(keputusan)) {
                if (keputusan.equalsIgnoreCase("K")) {
                    totalKompaun++;
                } else if (keputusan.equalsIgnoreCase("D")) {
                    totalDakwa++;
                } else if (keputusan.equalsIgnoreCase("N")) {
                    totalNFA++;
                }
            }
        }
        logger.info("total dakwa : (" + totalDakwa + ") :::: total kompaun : (" + totalKompaun + "):::: total nfa : (" + totalNFA + ")");

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new RedirectResolution(KeputusanKompaunDakwaActionBean.class, "locate");
    }

    public Resolution createPermohonan() {
        logger.info("---------createPermohonan---------");

        for (int i = 0; i < senaraiOksIp.size(); i++) {
            aduanOrangKenaSyak = senaraiOksIp.get(i);
            if (aduanOrangKenaSyak != null) {
                if (StringUtils.isNotBlank(aduanOrangKenaSyak.getStatusDakwaan())) {
                    keputusan = aduanOrangKenaSyak.getStatusDakwaan();
                    if (keputusan.equalsIgnoreCase("K")) {
                        totalKompaun++;
                    } else if (keputusan.equalsIgnoreCase("D")) {
                        totalDakwa++;
                    } else if (keputusan.equalsIgnoreCase("N")) {
                        totalNFA++;
                    }
                }
            }
        }

        logger.info("createPermohonan :::: total dakwa : (" + totalDakwa + ") :::: total kompaun : (" + totalKompaun + "):::: total nfa : (" + totalNFA + ")");

        if (totalDakwa > 0) {
            createNewPermohonan("D");
        }
        if (totalKompaun > 0) {
            createNewPermohonan("K");
        }
        if (totalNFA > 0) {
            createNewPermohonan("N");
        }

        return new RedirectResolution(KeputusanKompaunDakwaActionBean.class, "locate");
    }

    public void createNewPermohonan(String keputusan) {
        logger.info("--------- ::: start createNewPermohonan ::: for " + keputusan + "---------");
        InfoAudit ia = new InfoAudit();

        permohonanBaru = new Permohonan();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();

        String idPermohonanBaru = idPermohonanGenerator.generate(
                ctx.getKodNegeri(), pguna.getKodCawangan(), permohonan.getKodUrusan());

        //create id mohon baru
        permohonanBaru.setStatus("TA");
        permohonanBaru.setIdPermohonan(idPermohonanBaru);
        permohonanBaru.setCawangan(pguna.getKodCawangan());
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

        for (int i = 0; i < senaraiOksIp.size(); i++) {
            aduanOrangKenaSyak = senaraiOksIp.get(i);
            if (aduanOrangKenaSyak != null) {
                if (aduanOrangKenaSyak.getStatusDakwaan().equalsIgnoreCase(keputusan)) {
                    aduanOrangKenaSyak.setPermohonanAduanOrangKenaSyak(permohonanBaru);
                    aduanOrangKenaSyak.setInfoAudit(ia);
                    enforceService.simpanAduanOrangKenaSyak(aduanOrangKenaSyak);
                }
            }
        }
    }

    public Resolution agihTugasan() throws WorkflowException, StaleObjectException, Exception {

        logger.info("------------agihTugasan--------------");

        logger.info("::::::size senarai permohonan baru : " + senaraiPermohonanBaru.size());

        //1)Initiate task for new id permohonan
        for (int i = 0; i < senaraiPermohonanBaru.size(); i++) {
            permohonanBaru = senaraiPermohonanBaru.get(i);
            initiateTask(permohonanBaru, pguna);
        }
        if (permohonan.getPermohonanSebelum() != null) {
            try {
                senaraiOksForKompaunDakwa = enforcementService.getListKompaunDakwaOks(permohonan.getPermohonanSebelum().getIdPermohonan());
                logger.info("senaraiOksForKompaunDakwa : " + senaraiOksForKompaunDakwa.size());

                for (int i = 0; i < senaraiPermohonanBaru.size(); i++) {
                    permohonanBaru = senaraiPermohonanBaru.get(i);
                    for (int j = 0; j < senaraiOksForKompaunDakwa.size(); j++) {
                        aduanOrangKenaSyak = senaraiOksForKompaunDakwa.get(j);

                        if (aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan().equalsIgnoreCase(permohonanBaru.getIdPermohonan())) {
                            if (aduanOrangKenaSyak.getStatusDakwaan().equalsIgnoreCase("D")) {
                                idPermohonanForDakwa = aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan();
                            } else if (aduanOrangKenaSyak.getStatusDakwaan().equalsIgnoreCase("K")) {
                                idPermohonanForKompaun = aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan();
                            } else if (aduanOrangKenaSyak.getStatusDakwaan().equalsIgnoreCase("N")) {
                                idPermohonanForNFA = aduanOrangKenaSyak.getPermohonanAduanOrangKenaSyak().getIdPermohonan();
                            }

                        }
                    }
                }
                logger.info("idPermohonanForDakwa : " + idPermohonanForDakwa);
                logger.info("idPermohonanForKompaun : " + idPermohonanForKompaun);
                logger.info("idPermohonanForNFA : " + idPermohonanForNFA);

                //2) Skip stage

                try {
                    messageAgihTugasan += "Penghantaran Berjaya untuk : \n";
                    if (!etanah.util.StringUtils.isBlank(idPermohonanForDakwa)) {
                        skipStage(idPermohonanForDakwa, "DK");
                        messageAgihTugasan += idPermohonanForDakwa + ": Permohonan Baru Untuk Kes Dakwa. \n ";
                    }
                    if (!etanah.util.StringUtils.isBlank(idPermohonanForKompaun)) {
                        skipStage(idPermohonanForKompaun, "C");
                        messageAgihTugasan += idPermohonanForKompaun + ": Permohonan Baru Untuk Kes Kompaun. \n ";
                    }
                    if (!etanah.util.StringUtils.isBlank(idPermohonanForNFA)) {
                        skipStage(idPermohonanForNFA, "TT");
                        messageAgihTugasan += idPermohonanForNFA + ": Permohonan Baru Untuk Kes NFA. \n ";
                    }
                } catch (WorkflowException ex) {
                    java.util.logging.Logger.getLogger(KeputusanKompaunDakwaActionBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (StaleObjectException ex) {
                    java.util.logging.Logger.getLogger(KeputusanKompaunDakwaActionBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(KeputusanKompaunDakwaActionBean.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("messageAgihTugasan :" + messageAgihTugasan);
                messageAgihTugasan = messageAgihTugasan.replace("\n", "<br>");

                //3) withdraw current task

                List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

                if (l.isEmpty()) {
                    try {
                        Thread.sleep(5000);
                        l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                }
                if (l != null) {
                    for (Task t : l) {
                        taskId = t.getSystemAttributes().getTaskId();
                        if (org.apache.commons.lang.StringUtils.isNotBlank(taskId)) {
                            try {
                                System.out.println("task id :::: " + taskId);
                                WorkFlowService.withdrawTask(taskId);
                                logger.info("Pembatalan Berjaya");
                            } catch (StaleObjectException ex) {
                                ex.printStackTrace();
                                logger.error(ex);
                            }
                        }
                    }
                }
            } catch (WorkflowException ex) {
                java.util.logging.Logger.getLogger(KeputusanKompaunDakwaActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }

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

    public void skipStage(String permohonanBaru, String keputusan) throws WorkflowException, StaleObjectException, Exception {

        logger.info("------------- SKIP STAGE ---------------");

        ctxOnBehalf = WorkFlowService.authenticate("mtpptdkuasa1"); //pptd
        if (ctxOnBehalf != null) {
            System.out.println("ctxOnBehalf : " + ctxOnBehalf);
            System.out.println("id mohon : " + permohonanBaru);

            List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru);
            logger.info("1) Task FOund(size)::" + l.size());
            if (l.isEmpty()) {
                try {
                    Thread.sleep(5000);
                    l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonanBaru);
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

                Permohonan p = permohonanDAO.findById(permohonanBaru);
                if (p.getKodUrusan().getKod().equalsIgnoreCase("426") || p.getKodUrusan().getKod().equalsIgnoreCase("425") || p.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                    System.out.println("----- for sek426 or sek425----- ");
                    logger.info("::: keputusan :" + keputusan);
                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, keputusan);
                }


                logger.debug("current stage id ::::::::::::::::" + stageId);
                logger.debug("next stage ::::::::::::::::" + nextStage);
                logger.debug("Tugasan dihantar ke : " + nextStage);
            }
        }
    }

    public String getIdSaksi() {
        return idSaksi;
    }

    public void setIdSaksi(String idSaksi) {
        this.idSaksi = idSaksi;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public PermohonanSaksi getPermohonanSaksi() {
        return permohonanSaksi;
    }

    public void setPermohonanSaksi(PermohonanSaksi permohonanSaksi) {
        this.permohonanSaksi = permohonanSaksi;
    }

    public List<PermohonanSaksi> getSenaraiPermohonanSaksi() {
        return senaraiPermohonanSaksi;
    }

    public void setSenaraiPermohonanSaksi(List<PermohonanSaksi> senaraiPermohonanSaksi) {
        this.senaraiPermohonanSaksi = senaraiPermohonanSaksi;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getNegeri1() {
        return negeri1;
    }

    public void setNegeri1(String negeri1) {
        this.negeri1 = negeri1;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getKodNegeriOrg() {
        return kodNegeriOrg;
    }

    public void setKodNegeriOrg(String kodNegeriOrg) {
        this.kodNegeriOrg = kodNegeriOrg;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKp() {
        return kp;
    }

    public void setKp(String kp) {
        this.kp = kp;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public Boolean getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Boolean opFlag) {
        this.opFlag = opFlag;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Long getIdOpBasedOnIdIP() {
        return idOpBasedOnIdIP;
    }

    public void setIdOpBasedOnIdIP(Long idOpBasedOnIdIP) {
        this.idOpBasedOnIdIP = idOpBasedOnIdIP;
    }

    public Boolean getIdIP() {
        return idIP;
    }

    public void setIdIP(Boolean idIP) {
        this.idIP = idIP;
    }

    public String getIdOks() {
        return idOks;
    }

    public void setIdOks(String idOks) {
        this.idOks = idOks;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public int getTotalDakwa() {
        return totalDakwa;
    }

    public void setTotalDakwa(int totalDakwa) {
        this.totalDakwa = totalDakwa;
    }

    public int getTotalKompaun() {
        return totalKompaun;
    }

    public void setTotalKompaun(int totalKompaun) {
        this.totalKompaun = totalKompaun;
    }

    public int getTotalNFA() {
        return totalNFA;
    }

    public void setTotalNFA(int totalNFA) {
        this.totalNFA = totalNFA;
    }

    public List<Permohonan> getSenaraiPermohonanBaru() {
        return senaraiPermohonanBaru;
    }

    public void setSenaraiPermohonanBaru(List<Permohonan> senaraiPermohonanBaru) {
        this.senaraiPermohonanBaru = senaraiPermohonanBaru;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksForKompaunDakwa() {
        return senaraiOksForKompaunDakwa;
    }

    public void setSenaraiOksForKompaunDakwa(List<AduanOrangKenaSyak> senaraiOksForKompaunDakwa) {
        this.senaraiOksForKompaunDakwa = senaraiOksForKompaunDakwa;
    }

    public Permohonan getPermohonanBaru() {
        return permohonanBaru;
    }

    public void setPermohonanBaru(Permohonan permohonanBaru) {
        this.permohonanBaru = permohonanBaru;
    }

    public String getIdPermohonanForDakwa() {
        return idPermohonanForDakwa;
    }

    public void setIdPermohonanForDakwa(String idPermohonanForDakwa) {
        this.idPermohonanForDakwa = idPermohonanForDakwa;
    }

    public String getIdPermohonanForKompaun() {
        return idPermohonanForKompaun;
    }

    public void setIdPermohonanForKompaun(String idPermohonanForKompaun) {
        this.idPermohonanForKompaun = idPermohonanForKompaun;
    }

    public String getIdPermohonanForNFA() {
        return idPermohonanForNFA;
    }

    public void setIdPermohonanForNFA(String idPermohonanForNFA) {
        this.idPermohonanForNFA = idPermohonanForNFA;
    }

    public IWorkflowContext getCtxOnBehalf() {
        return ctxOnBehalf;
    }

    public void setCtxOnBehalf(IWorkflowContext ctxOnBehalf) {
        this.ctxOnBehalf = ctxOnBehalf;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMessageAgihTugasan() {
        return messageAgihTugasan;
    }

    public void setMessageAgihTugasan(String messageAgihTugasan) {
        this.messageAgihTugasan = messageAgihTugasan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
