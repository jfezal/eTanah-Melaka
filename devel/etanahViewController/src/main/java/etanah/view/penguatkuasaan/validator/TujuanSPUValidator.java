
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.service.EnforceService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
public class TujuanSPUValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    PermohonanLaporanUlasan permohonanLaporanUlasan;
    @Inject
    EnforceService enfService;
    private static final Logger LOG = Logger.getLogger(TujuanSPUValidator.class);

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
        permohonanLaporanUlasan = enfService.findPermohonanUkurByIdPermohonanTujuan(permohonan.getIdPermohonan());
        try {
            if (permohonanLaporanUlasan.getUlasan().isEmpty()) {
                context.addMessage("Sila masukkan tujuan didalam sijil pengecualiaan ukur : " + permohonan.getIdPermohonan());
                return null;

            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
//        return proposedOutcome;
        return null;
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
