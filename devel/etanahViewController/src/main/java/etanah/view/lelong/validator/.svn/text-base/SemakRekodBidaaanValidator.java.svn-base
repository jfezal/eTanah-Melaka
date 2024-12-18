/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.lelong.validator;

import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author mdizzat.mashrom
 */
public class SemakRekodBidaaanValidator implements StageListener{

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
        context.setNoRujukan(context.getPermohonan().getIdPermohonan());
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
