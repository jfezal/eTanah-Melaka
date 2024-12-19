/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.etapp.validator;

import com.google.inject.Inject;
import etanah.ref.pengambilan.myetapp.RefPeringkat;
import etanah.service.acq.integrationflow.MyeTappIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohd.faidzal
 */
public class HantarBorangBValidator implements StageListener {

    @Inject
    MyeTappIntegrationFlowService myeTappIntegrationFlowService;

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
        try {
            myeTappIntegrationFlowService.completeTask(ref.SEK4_HANTAR_BORANGB, context.getPermohonan(), context.getPengguna(),context.getPermohonan().getCawangan().getDaerah());
        } catch (IOException ex) {
            Logger.getLogger(HantarBorangAValidator.class.getName()).log(Level.SEVERE, null, ex);
        }

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
