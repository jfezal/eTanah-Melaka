/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;


import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;


/**
 *
 * @author Administrator
 */
public class RayuanPermohonanValidator implements StageListener{

    private static final Logger LOG = Logger.getLogger(RayuanPermohonanValidator.class);


    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        if(permohonan.getPermohonanSebelum()== null){
            context.addMessage("Sila masukan id permohonan");

                return null;
         }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
