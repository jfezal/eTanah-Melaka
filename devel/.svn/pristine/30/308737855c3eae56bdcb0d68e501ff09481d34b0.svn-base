/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek4.validator;

import com.google.inject.Inject;
import etanah.dao.InfoMmknDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoMmkn;
import etanah.model.Permohonan;
import etanah.service.acq.integrationflow.Sek4IntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author mohd.faidzal
 */
public class LulusTolakMMKValidator implements StageListener {
@Inject
InfoMmknDAO infoMmknDAO;
@Inject
PermohonanDAO permohonanDAO;
@Inject
    Sek4IntegrationFlowService sek4IntegrationFlowService;
    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan mohon = context.getPermohonan();
        InfoMmkn mmk = sek4IntegrationFlowService.findMMKByKpsn(mohon.getIdPermohonan(),"RKMMK");
    try {
        proposedOutcome = sek4IntegrationFlowService.withdrawTaskTolak(proposedOutcome,mmk);
    } catch (WorkflowException ex) {
        Logger.getLogger(LulusTolakMMKValidator.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
