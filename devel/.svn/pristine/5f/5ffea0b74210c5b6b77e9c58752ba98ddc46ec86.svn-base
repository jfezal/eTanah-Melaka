/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek4aduan.validator;

import com.google.inject.Inject;
import etanah.ref.pengambilan.sek4aduan.RefPeringkat;
import etanah.service.acq.integrationflow.Sek4AduanIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author mohd.faidzal
 */
public class SemakTandatanganArahanBayaranValidator implements StageListener {

    @Inject
    Sek4AduanIntegrationFlowService sek4AduanIntegrationFlowService;

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

        RefPeringkat ref = new RefPeringkat();
        sek4AduanIntegrationFlowService.completeTask(ref.REKOD_TERIMA_BYR_AP, context.getPermohonan(), context.getPengguna());

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
    }

}
