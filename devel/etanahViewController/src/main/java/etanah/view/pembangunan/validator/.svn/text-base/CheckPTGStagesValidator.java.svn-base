/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author rajib
 */
public class CheckPTGStagesValidator implements StageListener {
    
    @Inject
    PembangunanService pembangunanServis;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext context) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String returnResult = proposedOutcome;
        String idPermohonan = context.getPermohonan().getPermohonanSebelum().getIdPermohonan();
        List<FasaPermohonan> listMohonFasa = pembangunanServis.findFasaPermohonanByIdPermohonan2(idPermohonan);
        for(FasaPermohonan fasa:listMohonFasa){
            if((context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PPCB")||
                    context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PPCS") ||
                    context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PYTN"))&&
                    fasa.getIdAliran().equalsIgnoreCase("sediarencanajkbb")){
                
                        returnResult = "TK";
                        break;
                
            }
        }
        
        return returnResult;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
