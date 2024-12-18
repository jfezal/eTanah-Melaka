
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.LaporanTanah;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class LaporanTanahValidation implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    private static final Logger LOG = Logger.getLogger(LaporanTanahValidation.class);

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
        try {
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            List<LaporanTanah> listLaporanTanah;
            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

            if (listLaporanTanah.isEmpty()) {

                    context.addMessage("Sila masukkan maklumat laporan tanah : " + permohonan.getIdPermohonan());
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
        return "back";
    }
}
