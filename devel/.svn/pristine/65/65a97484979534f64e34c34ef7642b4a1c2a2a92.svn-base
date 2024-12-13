/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.manager.TabManager;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/uam/utiliti_release_task")
public class UtilitiReleaseTaskActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiReleaseTaskActionBean.class);
    private List<ReleaseTaskList> listRelease;
    private String idMohon;
    @Inject
    private TabManager tm;
    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    EnforcementService enforcementService;
    String idPengguna;
    String RETURNJSP;
    String groupBpel;
    List<Pengguna> listPengguna = new ArrayList<Pengguna>();
    String newAssignUser;
    String rowCount;
    List<Pengguna> listKumpBpel = new ArrayList<Pengguna>();
    ArrayList dropDownKumpulanBpel = new ArrayList<String>();

    @DefaultHandler
    public Resolution showForm() throws WorkflowException {
        RETURNJSP = "showForm";
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        listRelease = new ArrayList<ReleaseTaskList>();
        List<Task> taskList = WorkFlowService.queryTasksASSIGNED(ctx, pengguna.getKodCawangan().getKod());
        for (Task t : taskList) {
            if (mohonDAO.findById(t.getSystemMessageAttributes().getTextAttribute1()) == null) continue;
            ReleaseTaskList rt = new ReleaseTaskList();
            rt.setIdPermohonan(t.getSystemMessageAttributes().getTextAttribute1());
            rt.setUrusan(t.getTitle());
            rt.setTarikhTerima(t.getSystemAttributes().getAssignedDate().getTime());
            rt.setTaskID(t.getSystemAttributes().getTaskId());
            rt.setStage(tm.getCurrentAction(mohonDAO.findById(t.getSystemMessageAttributes().getTextAttribute1()).getKodUrusan().getIdWorkflow(), t.getSystemAttributes().getStage()));
            listRelease.add(rt);
        }
        return new JSP("uam/release_task.jsp");
    }

    public Resolution showForm2() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (pguna.getKodCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
            dropDownKumpulanBpel.add("pptptgkuasa"); // PPT
            dropDownKumpulanBpel.add("ppptgkuasa"); //PPelan
        } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//            dropDownKumpulanBpel.add("pptptdkuasa"); // PPT
            dropDownKumpulanBpel.add("PPTanah"); // PPT
            dropDownKumpulanBpel.add("ppptdkuasa1"); //PPelan
        }

        logger.info("::::: size dropDownKumpulanBpel :::::::: " + dropDownKumpulanBpel.size());

        listKumpBpel = enforcementService.findUserKumpBpel(dropDownKumpulanBpel, pguna.getKodCawangan().getKod());

        return new JSP("uam/release_task_user.jsp");
    }

    public Resolution findByIdPengguna() throws WorkflowException {
        RETURNJSP = "showForm2";
        Pengguna pengguna = penggunaDAO.findById(idPengguna);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        listRelease = new ArrayList<ReleaseTaskList>();
        List<Task> taskList = WorkFlowService.queryTasksASSIGNED(ctx, pengguna.getKodCawangan().getKod());
        for (Task t : taskList) {
            ReleaseTaskList rt = new ReleaseTaskList();
            rt.setIdPermohonan(t.getSystemMessageAttributes().getTextAttribute1());
            rt.setUrusan(t.getTitle());
            rt.setTarikhTerima(t.getSystemAttributes().getAssignedDate().getTime());
            rt.setTaskID(t.getSystemAttributes().getTaskId());
            rt.setStage(tm.getCurrentAction(mohonDAO.findById(t.getSystemMessageAttributes().getTextAttribute1()).getKodUrusan().getIdWorkflow(), t.getSystemAttributes().getStage()));
            listRelease.add(rt);
        }
        return new JSP("uam/release_task_user.jsp");
    }

    public Resolution findByBpelGroup() throws WorkflowException {
        RETURNJSP = "showForm2";
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (pguna.getKodCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
            dropDownKumpulanBpel.add("pptptgkuasa"); // PPT
            dropDownKumpulanBpel.add("ppptgkuasa"); //PPelan
        } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//            dropDownKumpulanBpel.add("pptptdkuasa"); // PPT
            dropDownKumpulanBpel.add("PPTanah"); // PPT
            dropDownKumpulanBpel.add("ppptdkuasa1"); //PPelan
        }

        if (StringUtils.isBlank(groupBpel)) {
            addSimpleError("Sila pilih kumpulan pengguna terlebih dahulu.");
            return new JSP("uam/release_task_user.jsp");
        } else {
            ArrayList kumpulanBpel = new ArrayList<String>();
            kumpulanBpel.add(groupBpel);


            listPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, pguna.getKodCawangan().getKod());
        }


//        Pengguna pengguna = penggunaDAO.findById(idPengguna);

        logger.info("::::: size listPengguna from bpel group (" + groupBpel + ") :::::::: " + listPengguna.size());

        listRelease = new ArrayList<ReleaseTaskList>();

        int i = 1;
        for (Pengguna p : listPengguna) {
            logger.info("::: START USER " + i + ") " + p.getIdPengguna() + ":::");
            IWorkflowContext ctx = WorkFlowService.authenticate(p.getIdPengguna());
            List<Task> taskList = WorkFlowService.queryTasksASSIGNED(ctx, p.getKodCawangan().getKod());
            for (Task t : taskList) {
                ReleaseTaskList rt = new ReleaseTaskList();
                logger.info(":::: id Permohonan :" + t.getSystemMessageAttributes().getTextAttribute1());
                rt.setIdPermohonan(t.getSystemMessageAttributes().getTextAttribute1());
                rt.setUrusan(t.getTitle());
                rt.setSelectedUser(t.getSystemAttributes().getAcquiredBy());
                rt.setTarikhTerima(t.getSystemAttributes().getAssignedDate().getTime());
                rt.setTaskID(t.getSystemAttributes().getTaskId());
                rt.setStage(tm.getCurrentAction(mohonDAO.findById(t.getSystemMessageAttributes().getTextAttribute1()).getKodUrusan().getIdWorkflow(), t.getSystemAttributes().getStage()));
                listRelease.add(rt);
            }
            logger.info("::: END USER " + p.getIdPengguna() + ":::");
            logger.info("::: ************************************ :::");
            i++;
        }

        logger.info("::::: size listRelease :::::::: " + listRelease.size());
        rowCount = String.valueOf(listRelease.size());

        return new JSP("uam/release_task_user.jsp");
    }

    public Resolution findByidMohon() throws WorkflowException {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);
        IWorkflowContext ctx = WorkFlowService.authenticate(pengguna.getIdPengguna());
        listRelease = new ArrayList<ReleaseTaskList>();

        if (idMohon != null || idMohon.equals("")) {
            List<Task> taskList = WorkFlowService.queryTasksByIdMohon(ctx, idMohon);
            for (Task t : taskList) {
                ReleaseTaskList rt = new ReleaseTaskList();
                rt.setIdPermohonan(t.getSystemMessageAttributes().getTextAttribute1());
                rt.setUrusan(t.getTitle());
                rt.setTarikhTerima(t.getSystemAttributes().getAssignedDate().getTime());
                rt.setTaskID(t.getSystemAttributes().getTaskId());
                listRelease.add(rt);
            }
        } else {
            addSimpleError("Sila masukkan ID Permohonan");
        }
        return new JSP("uam/release_task.jsp");
    }

    public Resolution releaseTask() throws WorkflowException, StaleObjectException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().
                getAttribute(etanahActionBeanContext.KEY_USER);
        String[] ids = getContext().getRequest().getParameterValues("chkbox");

        IWorkflowContext ctxA = WorkFlowService.authenticateAdmin();
        //listRelease = new ArrayList<ReleaseTaskList>();
        for (String id : ids) {
            WorkFlowService.releaseTask(ctxA, id);
        }

        addSimpleMessage("Tugasan telah berjaya di lepaskan.");
        return new RedirectResolution(UtilitiReleaseTaskActionBean.class, RETURNJSP);
    }

    public Resolution releaseTaskAndReassign() throws WorkflowException, StaleObjectException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (pengguna.getKodCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
            dropDownKumpulanBpel.add("pptptgkuasa"); // PPT
            dropDownKumpulanBpel.add("ppptgkuasa"); //PPelan
        } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//            dropDownKumpulanBpel.add("pptptdkuasa"); // PPT
            dropDownKumpulanBpel.add("PPTanah"); // PPT
            dropDownKumpulanBpel.add("ppptdkuasa1"); //PPelan
        }

        String[] ids = getContext().getRequest().getParameterValues("chkbox");

        if (ids == null) {
            addSimpleError("Sila pilih pengguna terlebih dahulu.");
            return new JSP("uam/release_task_user.jsp");
        }

        IWorkflowContext ctxA = WorkFlowService.authenticateAdmin();
        IWorkflowContext ctxOnBehalf = null;
        Task task = null;
        rowCount = getContext().getRequest().getParameter("rowCount");
        String msgAgihan = "";
        //listRelease = new ArrayList<ReleaseTaskList>();
        for (String id : ids) {
            try {
                String arrSplit[];
                if (StringUtils.isNotBlank(rowCount)) {
                    for (int i = 0; i < Integer.parseInt(rowCount); i++) {
                        String val = getContext().getRequest().getParameter("assignUserRow" + i);
                        System.out.println("val :" + val);
                        if (StringUtils.isNotBlank(val)) {
                            arrSplit = val.split(",");
                            logger.info("id ::::::" + id);
                            logger.info("arrSplit[0] ::::::" + arrSplit[0]);
                            logger.info("arrSplit[1] ::::::" + arrSplit[1]);
                            if (arrSplit[0].equalsIgnoreCase(id)) {
                                logger.info("::::: same task id :::::::: ");

                                WorkFlowService.releaseTask(ctxA, id);
                                Pengguna selectedUser = penggunaDAO.findById(arrSplit[1]);
                                logger.info("::::: selectedUser :::::::: " + selectedUser);
                                ctxOnBehalf = WorkFlowService.authenticate(selectedUser.getIdPengguna()); //pptd
                                Task t = WorkFlowService.getTask(id, ctxOnBehalf);
                                if (t.getSystemAttributes().getAcquiredBy() == null) {
                                    logger.info("::::: getAcquiredBy is null :::::::: ");
                                    WorkFlowService.acquireTask(id, ctxOnBehalf);
                                    logger.info("::::: This task reassign to  :::::::: " + t.getSystemAttributes().getAcquiredBy());
                                }
//                                task = WorkFlowService.reassignTask(ctxA, id, selectedUser.getIdPengguna(), "user");
                                msgAgihan += arrSplit[2] + " : Penghantaran Berjaya Kepada " + selectedUser.getIdPengguna() + "(" + selectedUser.getNama() + ")\n";
                            }
                        } else {
                            logger.info("task id ada but task id dalam array list tiada ::::::");
//                            addSimpleError("Sila pilih pengguna terlebih dahulu.");
//                            return new JSP("uam/release_task_user.jsp");
                        }
                    }
                }
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(UtilitiReleaseTaskActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        logger.info("::::: size rowCount :::::::: " + rowCount);

        msgAgihan = msgAgihan.replace("\n", "<br>");

        addSimpleMessage("Tugasan telah berjaya di lepaskan.");
        addSimpleMessage(msgAgihan);
        return new RedirectResolution(UtilitiReleaseTaskActionBean.class, RETURNJSP);
    }

    public List<ReleaseTaskList> getListRelease() {
        return listRelease;
    }

    public void setListRelease(List<ReleaseTaskList> listRelease) {
        this.listRelease = listRelease;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public PermohonanDAO getMohonDAO() {
        return mohonDAO;
    }

    public void setMohonDAO(PermohonanDAO mohonDAO) {
        this.mohonDAO = mohonDAO;
    }

    public PenggunaDAO getPenggunaDAO() {
        return penggunaDAO;
    }

    public void setPenggunaDAO(PenggunaDAO penggunaDAO) {
        this.penggunaDAO = penggunaDAO;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getRETURNJSP() {
        return RETURNJSP;
    }

    public void setRETURNJSP(String RETURNJSP) {
        this.RETURNJSP = RETURNJSP;
    }

    public String getGroupBpel() {
        return groupBpel;
    }

    public void setGroupBpel(String groupBpel) {
        this.groupBpel = groupBpel;
    }

    public List<Pengguna> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<Pengguna> listPengguna) {
        this.listPengguna = listPengguna;
    }

    public String getNewAssignUser() {
        return newAssignUser;
    }

    public void setNewAssignUser(String newAssignUser) {
        this.newAssignUser = newAssignUser;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public List<Pengguna> getListKumpBpel() {
        return listKumpBpel;
    }

    public void setListKumpBpel(List<Pengguna> listKumpBpel) {
        this.listKumpBpel = listKumpBpel;
    }

    public ArrayList getDropDownKumpulanBpel() {
        return dropDownKumpulanBpel;
    }

    public void setDropDownKumpulanBpel(ArrayList dropDownKumpulanBpel) {
        this.dropDownKumpulanBpel = dropDownKumpulanBpel;
    }
}
