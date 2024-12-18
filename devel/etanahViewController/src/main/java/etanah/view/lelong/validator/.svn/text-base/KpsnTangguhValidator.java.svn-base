/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class KpsnTangguhValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private Permohonan permohonan;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(KpsnTangguhValidator.class);

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
        Permohonan permohonanBaru = context.getPermohonan();
        String idPermohonan = permohonanBaru.getPermohonanSebelum().getIdPermohonan();
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanTangguh(permohonanBaru.getIdPermohonan());
        LOG.info("-------fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
        if (fasaPermohonan.getKeputusan().getKod().equals("L")) {
            LOG.debug("idPermohonan = " + idPermohonan);
//                List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
//                LOG.info("senaraiTask : " + senaraiTask.size());
//                if (senaraiTask.isEmpty()) {
//                    LOG.info("-----idPermohonan tidak di jumpai-----");
//                } else {
//                    Task task = (Task) senaraiTask.get(0);
//                    if (task != null) {
//                        LOG.info("-----idPermohonan di jumpai-----");
//                        LOG.info(task);
//                        taskId = task.getSystemAttributes().getTaskId();
//                        WorkFlowService.withdrawTask(taskId);
//                        LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
//                    }
//                }
            permohonan = permohonanDAO.findById(permohonanBaru.getIdPermohonan());
            permohonan.setStatus("L");
            lelongService.saveOrUpdate(permohonan);

            List<FasaPermohonan> mohonFasa2 = lelongService.getPermonanFasa2(permohonanBaru.getIdPermohonan());
            FasaPermohonan ff3 = null;
            //klu pilih jurulelong berlesen call complete izzat kate. Klu pilih pentadbir tanah pergi ke next stage
            if (mohonFasa2.size() != 0) {
                ff3 = mohonFasa2.get(0);
                if (ff3.getKeputusan().getKod().equals("JL")) {
                    proposedOutcome = "L";
                }
            }
        }
        if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
            proposedOutcome = "T";
            permohonan = permohonanDAO.findById(permohonanBaru.getIdPermohonan());
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

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
