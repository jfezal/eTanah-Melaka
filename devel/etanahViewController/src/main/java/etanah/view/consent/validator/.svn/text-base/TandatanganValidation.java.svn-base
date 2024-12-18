
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.ConsentPtdService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author muhammad.amin
 */
public class TandatanganValidation implements StageListener {

    private String stageA;
    private String stageB;
    @Inject
    ConsentPtdService consentService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        PermohonanTandatanganDokumen mohonTandatanganDokumen = new PermohonanTandatanganDokumen();

        if (ctx.getPermohonan().getKodUrusan().getKod().equals("PMJTL") || ctx.getPermohonan().getKodUrusan().getKod().equals("RMJTL")) {

            if (ctx.getPermohonan().getKodUrusan().getKod().equals("PMJTL")) {
                stageA = "Stage1"; // SURAT JABATAN TEKNIKAL
                stageB = "Stage6"; // SURAT PANGGILAN MESYUARAT
            } else {
                stageA = "Stage1";
                stageB = "Stage5";
            }

            if (ctx.getStageName().equals(stageA)) {
                mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan(), "SUT");
            } else if (ctx.getStageName().equals(stageB)) {
                mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan(), "KR");
            }

            if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getDiTandatangan() == null) {
                ctx.addMessage("Sila masukkan maklumat tandatangan terlebih dahulu.");
                return Boolean.FALSE;
            }

        } else {
            mohonTandatanganDokumen = consentService.findMohonTandatanganDokomen(ctx.getPermohonan().getIdPermohonan());

            if (mohonTandatanganDokumen == null || mohonTandatanganDokumen.getDiTandatangan() == null) {
                ctx.addMessage("Sila masukkan maklumat tandatangan terlebih dahulu.");
                return Boolean.FALSE;
            }

        }

        return Boolean.TRUE;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
