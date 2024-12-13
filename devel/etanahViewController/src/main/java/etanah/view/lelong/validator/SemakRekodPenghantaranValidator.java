/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class SemakRekodPenghantaranValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(KpsnSiasatanN9Validator.class);
    @Inject
    LelongService lelongService;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        
        List<Permohonan> listPermohonan = lelongService.getListPPTL(idPermohonan);
        if (!listPermohonan.isEmpty()) {
            LOG.info("----PPTL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Tangguh 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }
        
        List<Permohonan> listPermohonan2 = lelongService.getListPPBL(idPermohonan);
        if (!listPermohonan2.isEmpty()) {       
            LOG.info("----PPBL----");
            context.addMessage(idPermohonan + " - Terdapat Permohonan Pembatalan 16O Pada Permohonan Ini. Urusan Tidak Dapat Diteruskan.");
            return null;
        }        
//        List<Permohonan> listPermohonan = lelongService.getListPPBLPPTL(permohonan.getIdPermohonan());
//        if (!listPermohonan.isEmpty()) {
//            context.addMessage(permohonan.getIdPermohonan() + " - Terdapat Permohonan Tangguh Pada Permohonan Ini.Urusan Tidak Dapat Diteruskan.");
//            return null;
//        }
        
        FasaPermohonan fasa = lelongService.findFasaPermohonanSemakRekodPenghantaran(permohonan.getIdPermohonan());
        LOG.info("fasa.getKeputusan().getKod() : " + fasa.getKeputusan().getKod());
        if (fasa.getKeputusan().getKod().equals("BS")) {
            proposedOutcome = fasa.getKeputusan().getKod();
        }


//        String idPermohonan = context.getPermohonan().getIdPermohonan();
        Enkuiri enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());

        if (fasa.getKeputusan().getKod().equals("WN")) {
            LOG.info("---WN--");
            if (enkuiri != null) {

                if (enkuiri.getTarikhEnkuiri() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                    return null;
                }
            } else {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila Masukkan Tarikh Enkuiri Di Tab Maklumat Keputusan");
                return null;
            }
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
//        return proposedOutcome;
    }
}
