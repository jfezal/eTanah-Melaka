
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.view.stripes.pembangunan.KeputusanRPPN9ActionBean;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class KeputusanValidation implements StageListener {

    private static Logger logger = Logger.getLogger(KeputusanRPPN9ActionBean.class);
    private static final Logger LOG = Logger.getLogger(KeputusanValidation.class);
    @Inject
    PelupusanService pelupusanService;

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

            if (permohonan.getKodUrusan().getKod().equals("PMMK1") || permohonan.getKodUrusan().getKod().equals("PMMK2")
                    || permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                if (permohonan.getKeputusan() == null) {
                    context.addMessage("Sila masukkan keputusan terlebih dahulu : " + permohonan.getIdPermohonan());
                    return null;
                }
            } else {
                if (permohonan.getSenaraiRujukanLuar().isEmpty()) {
                    context.addMessage("Sila masukkan keputusan permohonan : " + permohonan.getIdPermohonan());
                    return null;
                }
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

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KeputusanValidation.logger = logger;
    }
}
