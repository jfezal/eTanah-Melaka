
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class KpsValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    private Permohonan mohonRMHS;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(BangunanKosRendahValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        
        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        
        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        if (permohonan.getKodUrusan().getKod().equals("PNB")) {
            if (mohonFasa.getKeputusan() != null) {                      
              permohonan.setKeputusan(mohonFasa.getKeputusan());
              Pengguna peng = (Pengguna) context.getPengguna();
              permohonan.setKeputusanOleh(peng);
              permohonan.setTarikhKeputusan(new Date());
              strService.updateMohon(permohonan);
             LOG.debug("----Update Keputusan Success----:"); 
             
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    LOG.debug("----lulus----:");
                    LOG.debug("----permohonan.getPermohonanSebelum()----:" + permohonan.getPermohonanSebelum());
                    if (permohonan.getPermohonanSebelum() != null) {
                        LOG.debug("----permohonan.getPermohonanSebelum()----:" + permohonan.getPermohonanSebelum().getIdPermohonan());
                        withDrawIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    }
                }
            }
        }
        return proposedOutcome;
    }

    public void withDrawIdPermohonan(String idPermohonan) {
        try {
            LOG.debug("----Withdrawing task----:");
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            List senaraiTask = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
            LOG.debug("----senaraiTask----:" + senaraiTask.size());
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
            permohonan.setStatus("BP");
            lelongService.saveOrUpdate(permohonan);
            LOG.debug("----task Withdraw success----:");
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
