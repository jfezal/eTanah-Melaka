/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import etanah.model.FasaPermohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.KodStatusEnkuiri;
import etanah.model.Permohonan;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.sql.ARRAY;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class PermohoanBatalValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    private Permohonan permohonan;
    private List<Permohonan> listPermohonan;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(PermohoanBatalValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonan(permohonanla.getIdPermohonan());
        LOG.info("-------fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
        listPermohonan = new ArrayList<Permohonan>();
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
                    }
                }
                WorkFlowService.withdrawTask(taskId);
                LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
                permohonan = permohonanDAO.findById(idPermohonan);
                permohonan.setStatus("BP");
                List<Permohonan> listMohonSblm = lelongService.findPermohonanSblmByIdSblmAK(idPermohonan);
                for (Permohonan mohonSblm : listMohonSblm) {
                    mohonSblm.setStatus("BP");
                    lelongService.saveOrUpdate(mohonSblm);
                }
                lelongService.saveOrUpdate(permohonan);
                KodStatusEnkuiri kodEnkuiri = kodStatusEnkuiriDAO.findById("BP");
                Enkuiri en = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                en.setStatus(kodEnkuiri);
                lelongService.saveOrUpdate(en);
            } catch (Exception ex) {
                LOG.error(ex);
            }
            proposedOutcome = "L";
        }
        if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
            LOG.info("--------Tolak!!!-------");
            proposedOutcome = "T";
            permohonan = permohonanDAO.findById(permohonanla.getIdPermohonan());
            permohonan.setStatus("TL");
            lelongService.saveOrUpdate(permohonan);
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

    public List<Permohonan> getListPermohonan() {
        return listPermohonan;
    }

    public void setListPermohonan(List<Permohonan> listPermohonan) {
        this.listPermohonan = listPermohonan;
    }
    
}
