
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.LelonganAwamDAO;
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
 * @txn     : PWGSA N9
 * @update  : 20131126
 */

public class MaklumatTanahValidator implements StageListener {

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

    private static final Logger l = Logger.getLogger(MaklumatPelelonganValidator.class);

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
        
        Permohonan p = context.getPermohonan();
        String stageId = context.getStageName();
        HakmilikPermohonan hp = ps.findHakmilikPermohonan(p.getIdPermohonan());
        
        try {
            if (p != null && stageId.equals("01TrmArhn") && hp != null) {                
                if (hp.getBandarPekanMukimBaru() == null
                        || hp.getLokasi() == null
                        || hp.getLuasTerlibat() == null
                        || hp.getKodUnitLuas() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
                    return null;
                } else if (p != null && stageId.equals("01TrmArhn")) {
                    if (p.getSebab() == null) {
                        context.addMessage(p.getIdPermohonan() + " - Sila masukkan Tujuan di tab Tanah terlebih dahulu.");
                        return null;
                    }
                }
            } else if (p != null && stageId.equals("01TrmArhn") && hp == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Tanah Yang Terlibat di tab Tanah terlebih dahulu.");
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

