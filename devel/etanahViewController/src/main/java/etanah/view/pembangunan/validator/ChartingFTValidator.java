/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author nursyazwani
 */
public class ChartingFTValidator implements StageListener{
    
    @Inject
    PembangunanService devService;
    @Inject
    PermohonanDAO permohonanDAO;
    
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        List<FasaPermohonan> mohonFasa = devService.findFasaPermohonanByIdPermohonan2(permohonan.getIdPermohonan());
        
        for(FasaPermohonan mf : mohonFasa){
            if (mf.getIdAliran().equals("g_semak_ft")) {
                permohonan.setStatus("SL");
                devService.simpanPermohonan(permohonan);
            }
        }
        proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
        context.addMessage("Tugasan Tamat");
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
