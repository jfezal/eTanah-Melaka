/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import etanah.view.pengambilan.validator.*;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusEnkuiri;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.view.strata.CommonService;
import etanah.view.strata.InitiateTaskService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 * @modified by massita
 */
public class PermohonanBatalValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    private Permohonan permohonan;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(PermohonanBatalValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
//        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonanla = context.getPermohonan();
        String idPermohonan = permohonanla.getPermohonanSebelum().getIdPermohonan();
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanPengambilan(permohonanla.getIdPermohonan());
        LOG.info("-------fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getKeputusan() != null) {
                if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
                    LOG.info("--------lulus!!!-------");
                    try {
                        LOG.debug("idPermohonan = " + idPermohonan);

                        List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
                        LOG.info("senaraiTask : " + senaraiTask.size());
                        if (senaraiTask.isEmpty()) {
                            LOG.info("-----idPermohonan tidak di jumpai-----");
                        } else {

                            Task task = (Task) senaraiTask.get(0);
                            if (task != null) {
                                LOG.info("-----idPermohonan di jumpai-----");
                                LOG.info(task);
                                taskId = task.getSystemAttributes().getTaskId();
                                WorkFlowService.withdrawTask(taskId);
                                LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
                                permohonan = permohonanDAO.findById(idPermohonan);
                                permohonan.setStatus("BP");
                                lelongService.saveOrUpdate(permohonan);
                            }
                        }


                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }

                if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
                    LOG.info("--------Tolak!!!-------");
                    proposedOutcome = "T";
                }
            }
        }
        LOG.info("proposedOutcome : " + proposedOutcome);
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
