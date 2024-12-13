/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import etanah.workflow.StageListener;

import etanah.view.pengambilan.validator.*;
import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import etanah.service.PengambilanService;
import etanah.service.PengambilanService1;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodStatusEnkuiri;
import etanah.workflow.WorkFlowService;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.common.LelongService;

/**
 *
 * @author nordiyana
 */
public class SuspendedUrusanValidator implements StageListener {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PengambilanService1 pengambilanService1;
    private static final Logger LOG = Logger.getLogger(SuspendedUrusanValidator.class);
    private String idSebelum;
    List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    List<HakmilikPermohonan> senaraiHakmilikPB;
    private String taskId;
    @Inject
    LelongService lelongService;

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
        
        Permohonan pPB=pengambilanService1.findByIdPermohonanPB(permohonan.getIdPermohonan());
        
        senaraiHakmilikPermohonan=permohonan.getSenaraiHakmilik();
        senaraiHakmilikPB=pPB.getSenaraiHakmilik();
        LOG.info("---------------senaraiHakmilikPB---------------- "+senaraiHakmilikPB.size());
        LOG.info("---------------senaraiHakmilikPermohonan---------------- "+senaraiHakmilikPermohonan.size());

             if (permohonan.getStatus().equalsIgnoreCase("BP")) {

                LOG.info("-------Status Permohonan : " + permohonan.getStatus());
                    
                        proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                        return proposedOutcome;
                    

            }
            

        return proposedOutcome;
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
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
    
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
