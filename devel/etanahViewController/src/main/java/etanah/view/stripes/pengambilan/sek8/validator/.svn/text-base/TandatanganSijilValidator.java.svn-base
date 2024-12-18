/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8.validator;

import com.google.inject.Inject;
import etanah.ref.pengambilan.sek8a.RefPeringkat;
import etanah.service.acq.integrationflow.Sek8aIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author mohd.faidzal
 */
public class TandatanganSijilValidator implements StageListener {

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
        sek8aIntegrationFlowService.completeTask(ref.TERIMASIJILUKUR, context.getPermohonan(), context.getPengguna());
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
