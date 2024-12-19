
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.LelonganAwamDAO;
import etanah.model.TanahRizabPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.service.BPelService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/*
 * @author  : Hayyan
 * @txn     : PJTK N9
 */

public class GISIntegrationValidator implements StageListener {

    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PelupusanService ps;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    LelonganAwamDAO lelonganAwamDAO;
    @Inject
    BPelService bpelService;

    private static final Logger l = Logger.getLogger(GISIntegrationValidator.class);

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
        
        String stageId = context.getStageName();        
        Permohonan permohonan = context.getPermohonan();        
        HakmilikPermohonan hp = ps.findHakmilikPermohonan(permohonan.getIdPermohonan());
        TanahRizabPermohonan trp = ps.findTanahRizabByIdPermohonan(permohonan.getIdPermohonan());        
        
        try {
            if (stageId.equals("g_terima_pw") && trp != null && hp != null) {                
                if (trp.getNoPW() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan No PW di tab PW terlebih dahulu.");
                    return null;
                }                            
            } else {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan No PW di tab PW terlebih dahulu.");
                return null;
            }            
        } catch (Exception e) {
            l.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
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

