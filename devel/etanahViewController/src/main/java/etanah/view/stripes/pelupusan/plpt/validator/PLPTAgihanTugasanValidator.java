/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.plpt.validator;

import com.google.inject.Inject;
import etanah.view.stripes.pelupusan.validator.NotifikasiPermohonanValidator;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class PLPTAgihanTugasanValidator implements StageListener{
private static final Logger LOG = Logger.getLogger(NotifikasiPermohonanValidator.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
return "back";    }

    @Override
    public void afterPushBack(StageContext context) {
    }
    
}
