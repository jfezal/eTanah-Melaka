/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.model.HakmilikPermohonan;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;

/**
 *
 * @author john
 */
public class SemakHakmilikEndorsPelanTataturValidator implements StageListener {

    @Inject
    SBMSIntegrationFlowService sBMSIntegrationFlowService;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        boolean ptd = false;
        boolean ptg = false;
        for (HakmilikPermohonan mh : context.getPermohonan().getSenaraiHakmilik()) {
            if (mh.getHakmilik().getCawangan().getKod().equals("00")) {
                ptg = true;
            } else {
                ptd = true;
            }

        }
        if (ptg) {
            sBMSIntegrationFlowService.insertTugasanTable(context.getPermohonan(), "ATPH", context.getPengguna().getIdPengguna());
        }
        if (ptd) {
            sBMSIntegrationFlowService.insertTugasanTable(context.getPermohonan(), "KMTP", context.getPengguna().getIdPengguna());
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
