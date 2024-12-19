/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.service.NotifikasiService;
import etanah.service.PembangunanService;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.view.strata.GenerateIdPerserahanWorkflow;

/**
 *
 * @author nursyazwani
 */
public class ChartingAkhir implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(ChartingNotification.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return "";
    }

    @Override
    public void afterComplete(StageContext context) {
        try {
            String idPermohonan = context.getPermohonan().getIdPermohonan();
            LOG.debug("idPermohonan = " + idPermohonan);

            Thread.sleep(10000);
            List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
            LOG.info("senaraiTask : " + senaraiTask.size());
            if (senaraiTask.isEmpty()) {
                LOG.info("-----idPermohonan tidak di jumpai-----");
            } else {
                for (int a = 0; a < senaraiTask.size(); a++) {
                    Task task = (Task) senaraiTask.get(a);
                    if (task != null) {
                        LOG.info("-----idPermohonan di jumpai-----");
                        LOG.info(task);
                        taskId = task.getSystemAttributes().getTaskId();
                        LOG.info("Task Id : " + taskId);
                        WorkFlowService.withdrawTask(taskId);
                        LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
                    }
                }
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
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
        return proposedOutcome;
    }
}
