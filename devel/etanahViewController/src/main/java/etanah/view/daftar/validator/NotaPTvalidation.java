/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar.validator;

import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author mohd.fairul
 */
public class NotaPTvalidation implements StageListener{

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
         return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
