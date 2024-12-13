/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.SyerValidationService;
import etanah.service.common.PermohonanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class SyerValidation implements StageListener {

    @Inject
    SyerValidationService syerService;
    @Inject
    PermohonanService permohonanService;
    private static final Logger LOG = Logger.getLogger(SyerValidation.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
//        String result = proposedOutcome;
//        Permohonan p = context.getPermohonan();

//        List<HakmilikPermohonan> senarai = p.getSenaraiHakmilik();
//        for (HakmilikPermohonan hp : senarai) {
//            if (hp == null || hp.getHakmilik() == null) {
//                continue;
//            }
//            Hakmilik hm = hp.getHakmilik();
//            int r = 0;
//            try {
//                r = syerService.doValidateSyerPortion(p.getIdPermohonan(), hm.getIdHakmilik());
//                if (r != 0) {
//                    context.addMessage("Pembahagian Syer Tanah Tidak Sah Untuk Permohonan : " + p.getIdPermohonan());
//                    return null;
//                }
//            } catch (Exception e) {
//                LOG.error(e.getMessage());
//                context.addMessage(e.getMessage());
//                return null;
//            }
//        }
//        context.addMessage(p.getIdPermohonan() + ": Penghantaran Ke Peringkat Seterusnya Berjaya.");
//        return result;

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
