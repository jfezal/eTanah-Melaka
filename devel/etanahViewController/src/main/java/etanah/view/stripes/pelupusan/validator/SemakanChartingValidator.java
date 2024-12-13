/**
 * 
 * @author Mohd Hairudin Mansur
 * @version 05052011
 * @modified by Afham
 */
package etanah.view.stripes.pelupusan.validator;

import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.service.PelupusanService;
import java.util.List;

public class SemakanChartingValidator implements StageListener {

    private final static Logger LOG = Logger.getLogger(SemakanChartingValidator.class);
    @Inject
    WorkFlowService workFlowService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService plpservice;

    @Override
    public void afterComplete(StageContext context) {
          FasaPermohonan fasaPermohonan = plpservice.findMohonFasaBySemakCharting(context.getPermohonan().getIdPermohonan());


            if (fasaPermohonan.getKeputusan().getKod().equals("TA")) {
            try {
                checkingOutcome(context.getPermohonan(), context.getPengguna());
            } catch (WorkflowException ex) {
                java.util.logging.Logger.getLogger(SemakanChartingValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (StaleObjectException ex) {
                java.util.logging.Logger.getLogger(SemakanChartingValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
            }



    }

//    @Override
//    public String beforeComplete(StageContext context, String proposedOutcome) {
////        FasaPermohonan fasaPermohonan = plpservice.findMohonFasaBySemakCharting(context.getPermohonan().getIdPermohonan());
////
////
////            if (fasaPermohonan.getKeputusan().getKod().equals("TA")) {
////            try {
////                checkingOutcome(context.getPermohonan(), context.getPengguna());
////            } catch (WorkflowException ex) {
////                java.util.logging.Logger.getLogger(SemakanChartingValidator.class.getName()).log(Level.SEVERE, null, ex);
////            } catch (StaleObjectException ex) {
////                java.util.logging.Logger.getLogger(SemakanChartingValidator.class.getName()).log(Level.SEVERE, null, ex);
////            }
////
////            }
////
////        proposedOutcome = "APPROVE";
////        LOG.info("Status " + proposedOutcome) ;
////        return proposedOutcome;
//    }

    @SuppressWarnings("static-access")
    public void checkingOutcome(Permohonan p, Pengguna pp) throws WorkflowException, StaleObjectException {

        IWorkflowContext ctx = workFlowService.authenticate(pp.getIdPengguna());
        List<Task> taskList = null;
        do {
            taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
            LOG.info("Task :" + taskList.size());
        } while (taskList.size() == 0);
        if (taskList.size() > 0) {     
                Task t = taskList.get(0);
                String taskId = t.getSystemAttributes().getTaskId();
                String stageID = workFlowService.updateTaskOutcome(taskId, ctx, "TA");
                LOG.info(" new stageID : " + stageID);
                ctx = workFlowService.authenticate("ptlupus1");
                    LOG.info("ctx : " +ctx.getUser());
                do {
                    taskList = workFlowService.queryTasksByAdmin(p.getIdPermohonan());
                    LOG.info("Task :" + taskList.size());
                } while (taskList.size() == 0);
                t = taskList.get(0);
                taskId = t.getSystemAttributes().getTaskId();
                LOG.info("taskId : " +taskId);
                workFlowService.acquireTask(taskId, ctx) ;
//                String stageNext = workFlowService.updateTaskOutcome(taskId, ctx, "APPROVE") ;
//                LOG.info("Go to stage next :" + stageNext);
           
        }
    }

    @Override
    public boolean beforeGenerateReports(StageContext arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeStart(StageContext arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext arg0) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public String beforeComplete(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
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
