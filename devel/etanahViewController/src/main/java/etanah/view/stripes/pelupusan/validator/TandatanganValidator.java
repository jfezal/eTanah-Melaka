
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.LelonganAwamDAO;
import etanah.model.PermohonanTandatanganDokumen;
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

public class TandatanganValidator implements StageListener {

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
        PermohonanTandatanganDokumen ptd1 = ps.findPermohonanDokTTByIdPermohonan(p.getIdPermohonan(),"SBU");
        PermohonanTandatanganDokumen ptd2 = ps.findPermohonanDokTTByIdPermohonan(p.getIdPermohonan(),"PU");
        PermohonanTandatanganDokumen ptd3 = ps.findPermohonanDokTTByIdPermohonan(p.getIdPermohonan(),"DW");
        PermohonanTandatanganDokumen ptd4 = ps.findPermohonanDokTTByIdPermohonan(p.getIdPermohonan(),"SRAGN");
        
        try {
            // Stage 04 PWGSA
            if (p != null && stageId.equals("04SedPermhnanSBU") && ptd1 != null) {                
                if (ptd1.getDiTandatangan() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("04SedPermhnanSBU") && ptd1 == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                return null;
            }
            // Stage 8A PWGSA
            if (p != null && stageId.equals("8aSedPU") && ptd2 != null) {                
                if (ptd2.getDiTandatangan() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("8aSedPU") && ptd2 == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                return null;
            }
            // Stage 15 PWGSA
            if (p != null && stageId.equals("15SmkdanSyrDrafWrta") && ptd3 != null) {                
                if (ptd2.getDiTandatangan() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("15SmkdanSyrDrafWrta") && ptd3 == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                return null;
            }
            // Stage 17 PWGSA
            if (p != null && stageId.equals("17PerakudanCtkWrta") && ptd3 != null) {                
                if (ptd3.getDiTandatangan() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("17PerakudanCtkWrta") && ptd3 == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                return null;
            }
            // Stage 18 PWGSA
            if (p != null && stageId.equals("18KmskniMklmnAgensi") && ptd4 != null) {                
                if (ptd4.getDiTandatangan() == null) {
                    context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
                    return null;
                } 
            } else if (p != null && stageId.equals("18KmskniMklmnAgensi") && ptd4 == null) {
                context.addMessage(p.getIdPermohonan() + " - Sila pilih Ditandatangan Oleh di tab Tandatangan Dokumen terlebih dahulu.");
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

