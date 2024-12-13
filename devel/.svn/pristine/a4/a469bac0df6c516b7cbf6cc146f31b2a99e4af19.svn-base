/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.controller.LifecycleStage;
import able.stripes.JSP;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.dao.PermohonanDAO;
import etanah.workflow.WorkFlowService;
import net.sourceforge.stripes.action.Resolution;
import etanah.view.etanahActionBeanContext;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import com.google.inject.Inject;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
/**
 *
 * @author aminah.abdmutalib
 */
@UrlBinding("/penguatkuasaan/bayaran")
public class BayaranActionbean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    private String taskId;
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws WorkflowException, StaleObjectException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        permohonan = permohonanDAO.findById("0505ENF2010008292");
//        try {
//
////            WorkFlowService.initiateTask(permohonan.getKodUrusan().getIdWorkflow(), permohonan.getIdPermohonan(), "99", pengguna.getIdPengguna(), permohonan.getKodUrusan().getNama());
//            IWorkflowContext ctx = WorkFlowService.authenticate("pptdkuasa1");
//            List senaraiTask = WorkFlowService.queryTasks(ctx,"99");
////            List senaraiTask = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
//                if (senaraiTask.isEmpty()) {
////                    addSimpleError("Permohonan tidak di jumpai");
//                } else {
//                 for (int i = 0; i < senaraiTask.size(); i++) {
//                      Task task = (Task) senaraiTask.get(i);
//                    if (task != null && task.getSystemAttributes().getStage().matches("Maklum_aduan")
//                              ) {
//                        taskId = task.getSystemAttributes().getTaskId();
//                        System.out.println("taskId: "+taskId);
//                    }
//                 }
//                }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
////            Logger.getLogger(BayaranActionbean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @DefaultHandler
    public Resolution showForm() {
         return new JSP("penguatkuasaan/test.jsp");
     }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }


}
