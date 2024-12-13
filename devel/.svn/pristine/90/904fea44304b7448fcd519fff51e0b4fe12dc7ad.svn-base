/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.bpel.services.workflow.WorkflowException;

/**
 *
 * @author faidzal
 */
public class SemakStrataValidator implements StageListener {
@Inject
 StrataValidatorService svs;
    @Override
    public boolean beforeStart(StageContext sc) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext sc, String string) {
       
        try {
            Map m = svs.semakPermohonan(sc.getPermohonan());
            String mesej = (String) m.get("Mesej");
            string = (String) m.get("status");
            sc.addMessage(mesej);

        } catch (WorkflowException ex) {
            Logger.getLogger(SemakStrataValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return string;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        return string;
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
