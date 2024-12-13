/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.EnforceService;
import etanah.service.common.FasaPermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.KodUrusan;
import etanah.service.AduanService;
import etanah.view.stripes.SenaraiTugasanActionBean;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/pertukaran_urusan")
public class MaklumatPertukaranSeksyenActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatPertukaranSeksyenActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    AduanService aduanService;
    @Inject
    FasaPermohonanService fasaPermohonanManager;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private String kodUrusan;
    private String oldKodUrusan;
    private List<KodUrusan> senaraiUrusan;
    private String nextStage;
    private String taskId;
    private String stageId;
    private boolean isBerangkai = Boolean.FALSE;
    private List<Permohonan> senaraiPermohonanBerangkai;
    IWorkflowContext ctx = null;
    IWorkflowContext ctxOnBehalf = null;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("/penguatkuasaan/maklumat_pertukaran_urusan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_pertukaran_urusan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        senaraiUrusan = aduanService.getSenaraiUrusan();

        if (permohonan != null) {
            if (permohonan.getRujukanUndang2() != null) {
                kodUrusan = permohonan.getRujukanUndang2();
            }
        }
    }

    public Resolution simpan() {
        InfoAudit ia = new InfoAudit();

        if (permohonan != null) {
            ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonan.setInfoAudit(ia);
            //permohonan.setKodUrusan(kodUrusanDAO.findById(kodUrusan));
            permohonan.setRujukanUndang2(kodUrusan);
            enforceService.simpanPermohonan(permohonan);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("/penguatkuasaan/maklumat_pertukaran_urusan.jsp").addParameter("tab", "true");
    }

    public Resolution initiateTask() {
        logger.info("------------initiateTask--------------");

        InfoAudit ia = new InfoAudit();

        if (permohonan != null) {
            ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

            permohonan.setInfoAudit(ia);
            permohonan.setKodUrusan(kodUrusanDAO.findById(kodUrusan));
            permohonan.setRujukanUndang2(null);
            enforceService.simpanPermohonan(permohonan);
        }

        StringBuilder sb = new StringBuilder();
        try {

            IWorkflowContext ctx1 = WorkFlowService.authenticate(pengguna.getIdPengguna());
            System.out.println("CTX1 :" + ctx1);
            List<Map<String, String>> list = getPermohonanWithTaskID(pengguna);
            for (Map<String, String> map : list) {
                String taskID = map.get("taskId");
                idPermohonan = map.get("idPermohonan");
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(idPermohonan);
                logger.info("TaskID ::" + taskID);
                logger.info("idPermohonan ::" + idPermohonan);

                permohonan = permohonanDAO.findById(idPermohonan);

                try {
                    if (permohonan.getKodUrusan().getKePTG() == 'Y') {
                        WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),
                                permohonan.getIdPermohonan(), permohonan.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                                permohonan.getKodUrusan().getNama());
                    } else if (permohonan.getKodUrusan().getKePTG() == 'T') {
                        WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(),
                                permohonan.getIdPermohonan(), permohonan.getCawangan().getKod(), pengguna.getIdPengguna(),
                                permohonan.getKodUrusan().getNama());
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                logger.info("Initiating BPEL Success....");

                skipStage(permohonan);
                WorkFlowService.updateTaskOutcome(taskID, ctx1, "APPROVE");
                //passStage(permohonan);


            }

        } catch (Exception ex) {
            ex.printStackTrace();


            return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("error", sb.toString() + " :Pertukaran Urusan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
        }


        return new RedirectResolution(SenaraiTugasanActionBean.class).addParameter("message", sb.toString() + " :Pertukaran Urusan telah berjaya. Tugasan dihantar ke stage " + nextStage);
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        if (isBerangkai) {
            logger.info("Urusan Berangkai");
            List taskList = WorkFlowService.queryTasks(ctx, pengguna.getKodCawangan().getKod());
            logger.info("taskList :: " + taskList.size());
            for (int i = 0; i < taskList.size(); i++) {
                Task impl = (Task) taskList.get(i);
                taskId = impl.getSystemAttributes().getTaskId();
                idPermohonan = impl.getSystemMessageAttributes().getTextAttribute1();
                System.out.println("taskID::" + taskId);
                Task temp = WorkFlowService.getTask(taskId, ctx);

                if (temp.getSystemAttributes().getAcquiredBy() == null) {
                    WorkFlowService.acquireTask(taskId, ctx);
                }

                for (Permohonan p : senaraiPermohonanBerangkai) {
                    logger.info("idPermohonan :: " + idPermohonan);
                    logger.info("p.idPermohonan :: " + p.getIdPermohonan());
                    if (p.getIdPermohonan().equals(idPermohonan)) {
                        map = new HashMap<String, String>();
                        map.put("idPermohonan", p.getIdPermohonan());
                        map.put("taskId", taskId);
                        list.add(map);
                    }
                }
            }
        } else {
            //standalone
            logger.info("Urusan tidak berangkai");
            taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            map = new HashMap<String, String>();
            map.put("idPermohonan", permohonan.getIdPermohonan());
            map.put("taskId", taskId);
            list.add(map);
        }
        return list;
    }

    public String skipStage(Permohonan permohonan) throws WorkflowException {
        logger.info("------------- SKIP STAGE ---------------");

        ctxOnBehalf = WorkFlowService.authenticate("mtpptdkuasa1"); //pptd
        if (ctxOnBehalf != null) {
            System.out.println("ctxOnBehalf : " + ctxOnBehalf);
            System.out.println("id mohon : " + permohonan.getIdPermohonan());
            List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
//            List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
            logger.info("Task FOund (size)::" + l.size());
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                taskId = t.getSystemAttributes().getTaskId();
                System.out.println("t :::: " + t.getSystemMessageAttributes().getTextAttribute1());
                try {
                    WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                    logger.debug("Claim Found Task::" + taskId);
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                        System.out.println("----- for sek426 or sek425----- ");
                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "LF"); // 
                    }
//                    else {
//                        System.out.println("----- for sek425 ----- ");
//                        nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "LF"); // LF = go to g_sedia_plan
//                    }


                    logger.debug("stage id ::::::::::::::::" + stageId);
                    logger.debug("next stage ::::::::::::::::" + nextStage);


                } catch (StaleObjectException ex) {
                    java.util.logging.Logger.getLogger(MaklumatPertukaranSeksyenActionBean.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return nextStage;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getOldKodUrusan() {
        return oldKodUrusan;
    }

    public void setOldKodUrusan(String oldKodUrusan) {
        this.oldKodUrusan = oldKodUrusan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
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

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public IWorkflowContext getCtx() {
        return ctx;
    }

    public void setCtx(IWorkflowContext ctx) {
        this.ctx = ctx;
    }

    public FasaPermohonanService getFasaPermohonanManager() {
        return fasaPermohonanManager;
    }

    public void setFasaPermohonanManager(FasaPermohonanService fasaPermohonanManager) {
        this.fasaPermohonanManager = fasaPermohonanManager;
    }

    public boolean isIsBerangkai() {
        return isBerangkai;
    }

    public void setIsBerangkai(boolean isBerangkai) {
        this.isBerangkai = isBerangkai;
    }

    public List<Permohonan> getSenaraiPermohonanBerangkai() {
        return senaraiPermohonanBerangkai;
    }

    public void setSenaraiPermohonanBerangkai(List<Permohonan> senaraiPermohonanBerangkai) {
        this.senaraiPermohonanBerangkai = senaraiPermohonanBerangkai;
    }
}
