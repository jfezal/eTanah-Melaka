/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikUrusan;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author faidzal
 */
public class SemakLaporanValidator implements StageListener {

    @Inject
    PermohonanDAO mohonDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    StrataPtService strService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan mohon = context.getPermohonan();
        Permohonan mohonREG;
        mohon = mohonDAO.findById(mohon.getIdPermohonan());

        HakmilikUrusan hakmilikUrusan;
        mohonREG = strService.findByIDSblm(mohon.getIdPermohonan());
        if (strService.findHakmilikUrusan(mohonREG.getIdPermohonan()) != null) {
            context.addMessage("Urusan ini perlu diendoskan dahulu oleh pihak pendaftaran : " + mohon.getIdPermohonan());
            return null;
        } else {
            return proposedOutcome;
        }
    }

    @Override
    public void afterComplete(StageContext context) {
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
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
