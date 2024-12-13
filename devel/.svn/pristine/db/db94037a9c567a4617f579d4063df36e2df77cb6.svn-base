/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author john
 */
public class UtilitiTugasanValidator implements StageListener {

    @Inject
    SBMSIntegrationFlowService sBMSIntegrationFlowService;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
return true;    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
//cetakhitungtatatur12d "TPB2";   
        String stage = "TPB2";

        sBMSIntegrationFlowService.insertTugasanTable(context.getPermohonan(), stage, context.getPengguna().getIdPengguna());

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
