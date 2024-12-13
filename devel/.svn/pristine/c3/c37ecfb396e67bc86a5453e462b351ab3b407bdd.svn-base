
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class WaranPenahananValidator implements StageListener {

    @Inject
    StrataPtService strService;
    private PermohonanRujukanLuar rujukan;
    private PermohonanRujukanLuar rujukan2;
    private static final Logger LOG = Logger.getLogger(WaranPenahananValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        rujukan = strService.findPermohonan(permohonan.getIdPermohonan(), "AHSN53(1)");
        rujukan2 = strService.findPermohonan(permohonan.getIdPermohonan(), "AHSN53(2)");
        try {

            if (permohonan.getSenaraiPihak().isEmpty()) {
                context.addMessage("Sila masukkan maklumat permohonan pihak berkepentingan : " + permohonan.getIdPermohonan());
                return null;
            } else if (permohonan.getSenaraiPemohon().isEmpty()) {
                context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                return null;
            } else if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                context.addMessage("Sila isikan maklumat Notis  : " + permohonan.getIdPermohonan());
                return null;
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
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
