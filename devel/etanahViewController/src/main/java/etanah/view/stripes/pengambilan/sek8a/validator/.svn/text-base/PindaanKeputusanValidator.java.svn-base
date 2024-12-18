/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8a.validator;

import com.google.inject.Inject;
import etanah.ref.pengambilan.sek8a.RefPeringkat;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author mohd.faidzal
 */
public class PindaanKeputusanValidator implements StageListener {

    @Inject
    Sek8aIntegrationFlowService sek8aIntegrationFlowService;

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

        if (proposedOutcome.equals("XN")) {//TIADA PINDAAN
            sek8aIntegrationFlowService.completeTask(ref.KPSN_TIADA_PINDAAN, context.getPermohonan(), context.getPengguna());

        } else if (proposedOutcome.equals("PW")) { //PEMBETULAN WARTA
            sek8aIntegrationFlowService.completeTask(ref.PEMBETULAN_WARTA, context.getPermohonan(), context.getPengguna());
        } else {//PINDAAN MAKLUMAT TANAH
            sek8aIntegrationFlowService.completeTask(ref.KEMASKINI_MAKLUMAT_TANAH, context.getPermohonan(), context.getPengguna());
        }
        
        proposedOutcome ="APPROVE";
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
