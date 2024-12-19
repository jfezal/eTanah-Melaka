
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.LelonganAwamDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.LelonganAwam;
import etanah.model.TanahRizabPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.KodBandarPekanMukim;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.service.BPelService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/*
 * @author  : Hayyan
 * @txn     : PJTK N9
 */

public class MaklumatPelelonganValidator implements StageListener {

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
        
        Permohonan permohonan = context.getPermohonan();
        String stageId = context.getStageName();
        HakmilikPermohonan hp = ps.findHakmilikPermohonan(permohonan.getIdPermohonan());
        LelonganAwam la = ps.findLelonganAwamById(permohonan.getIdPermohonan());
        TanahRizabPermohonan trp = ps.findTanahRizabByIdPermohonan(permohonan.getIdPermohonan());        
        
        try {
            if (permohonan != null && stageId.equals("01Kemasukan") && hp != null) {                
                if (hp.getBandarPekanMukimBaru() == null
                        || hp.getLokasi() == null
                        || hp.getLuasTerlibat() == null
                        || hp.getKodUnitLuas() == null
                        || hp.getLot() == null
                        || hp.getNoLot() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Tanah Dipohon di tab Tanah terlebih dahulu.");
                    return null;
                }                            
            }
//            else {
//                context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Tanah Dipohon di tab Tanah terlebih dahulu.");
//                return null;
//            }
            if (permohonan != null && stageId.equals("01Kemasukan") && la == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Pelelongan di tab Pelelongan terlebih dahulu.");
                return null;
            }
            if (permohonan != null && stageId.equals("laporan_tanah") && la != null) {
                if (la.getHargaRizab() == null && la.getDeposit() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Pelelongan di tab Pelelongan terlebih dahulu.");
                    return null;
                }
            }
            if (permohonan != null && stageId.equals("14KmsknNoWarta") && trp != null) {
                if (trp.getNoWarta() == null && trp.getTarikhWarta() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Warta di tab Pelelongan terlebih dahulu.");
                    return null;
                }
//                else {
//                    context.addMessage(permohonan.getIdPermohonan() + " - Sila lengkapkan Maklumat Warta di tab Pelelongan terlebih dahulu.");
//                    return null;
//                }
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

