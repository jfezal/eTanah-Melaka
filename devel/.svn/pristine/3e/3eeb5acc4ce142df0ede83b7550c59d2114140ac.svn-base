/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author zadhirul.farihim
 */
public class PermitRuangUdaraValidator implements StageListener {

    @Inject
    StrataPtService strService;
    List<HakmilikPermohonan> listHakmilikP = new ArrayList();
    private static final Logger LOG = Logger.getLogger(PermitRuangUdaraValidator.class);
    private String jarakDari;
    private String lokasiTanah;

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
        listHakmilikP = strService.getMaklumatTan(permohonan.getIdPermohonan());
        try {

            if (permohonan.getSenaraiPemohon().isEmpty()) {
                context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
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
