
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.LelonganAwamDAO;
import etanah.model.Permohonan;
import etanah.model.TanahRizabPermohonan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanRujukanLuar;
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

public class DWValidator implements StageListener {

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
        TanahRizabPermohonan mtr = ps.findTanahRizabByIdPermohonan(p.getIdPermohonan());
        PermohonanLaporanKawasan plk = ps.findByidMohon(p.getIdPermohonan());
        PermohonanRujukanLuar prl = ps.findIDPermohonanRujByIdPermohonan(p.getIdPermohonan());
        
        try {
            // Stage 10 PWGSA
            if (p != null && stageId.equals("10SedDrafWrta") && mtr != null) {                
                if (mtr.getNoPW() == null || p.getSebab() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Draf Warta di tab Draf Warta terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("10SedDrafWrta") && mtr == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Draf Warta di tab Draf Warta terlebih dahulu.");
                return null;
            }
            // Stage 10 PWGSA
            if (p != null && stageId.equals("10SedDrafWrta") && plk != null) {                
                if (plk.getTarikhWarta() == null || plk.getCatatan() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Draf Warta di tab Draf Warta terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("10SedDrafWrta") && plk == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Draf Warta di tab Draf Warta terlebih dahulu.");
                return null;
            }
            // Stage 18 PWGSA (validate GSA)
            if (p != null && stageId.equals("18KmskniMklmnAgensi") && plk != null) {                
                if (mtr.getTarikhWarta() == null || mtr.getNoWarta() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Warta di tab Warta terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("18KmskniMklmnAgensi") && plk == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila lengkapkan Maklumat Warta di tab Warta terlebih dahulu.");
                return null;
            }
            // Stage 18 PWGSA (validate Agensi)
            if (p != null && stageId.equals("18KmskniMklmnAgensi") && prl != null) {                
                if (prl.getAgensi() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila pilih Agensi di tab Agensi terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("18KmskniMklmnAgensi") && prl == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila pilih Agensi di tab Agensi terlebih dahulu.");
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

