
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author w.fairul
 */
public class SijilPerbadananValidator implements StageListener {

    @Inject
    StrataPtService strService;
    private PermohonanStrata sijil;
    private BadanPengurusan mc;
    private static final Logger LOG = Logger.getLogger(SijilPerbadananValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        mc = strService.findBdn(permohonan.getIdPermohonan());

        if (mc != null) {
            mc.setPengurusanTarikhRujukan(new Date());
            strService.simpanSijil(mc);
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

//        Permohonan permohonan = context.getPermohonan();
//        sijil = strService.findID(permohonan.getIdPermohonan());
//        mc = strService.findBdn(permohonan.getIdPermohonan());
//
//        try {
//
//            if(permohonan.getSenaraiPemohon().isEmpty())
//            {
//                    context.addMessage("Sila masukkan maklumat pemohon - " + permohonan.getIdPermohonan());
//                    return null;
//            }
//
//            if(mc == null)
//            {
//                    context.addMessage("Sila masukkan maklumat perbadanan pengurusan - " + permohonan.getIdPermohonan());
//                    return null;
//            }
//
////             else if(sijil == null)
////            {
////                    context.addMessage("Sila masukkan maklumat draf sijil perbadanan pengurusan : " + permohonan.getIdPermohonan());
////                    return null;
////            }
//
//
//
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            return null;
//        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        Permohonan permohonan = ctx.getPermohonan();
        mc = strService.findBdn(permohonan.getIdPermohonan());

        if (mc != null) {
            mc.setPengurusanTarikhRujukan(new Date());
            strService.simpanSijil(mc);
        }
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
