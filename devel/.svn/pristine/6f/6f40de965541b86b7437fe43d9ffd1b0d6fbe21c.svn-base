/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.service.common.PermohonanService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author mohd.faidzal
 */
public class RekodKeputusanJKBBValidator implements StageListener {
@Inject
SBMSIntegrationFlowService sBMSIntegrationFlowService;
@Inject
PermohonanService permohonanService;
private String keputusan;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        
        //check keputusan null
        //eputusan = context.getPermohonan().getKeputusan().getNama();
        FasaPermohonan fasaPermohonan = permohonanService.findKeputusan(context.getPermohonan().getIdPermohonan(), "perakuanjkbbptg");
        if(fasaPermohonan.getKeputusan() != null){
            sBMSIntegrationFlowService.insertTugasanTable(context.getPermohonan(), "JKBB", context.getPengguna().getIdPengguna());
        }else{
            proposedOutcome = null;
             context.addMessage(" Tiada Keputusan di buat. Sila masukkan keputusan pada Tab Keputusan.");
        }
                  
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {

    }

}
