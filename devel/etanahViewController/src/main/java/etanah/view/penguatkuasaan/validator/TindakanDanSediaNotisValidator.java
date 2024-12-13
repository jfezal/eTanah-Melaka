/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.AduanLokasi;
import etanah.model.Aduan;
import etanah.model.Notis;
import etanah.service.common.EnforcementService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;


/**
 *
 * @author nurshahida.radzi
 */
public class TindakanDanSediaNotisValidator implements StageListener {

    @Inject
    EnforcementService enforcementService;
    @Inject
    Notis notis;
    private static final Logger LOG = Logger.getLogger(TindakanDanSediaNotisValidator.class);


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
        notis = enforcementService.findByIdNotis(permohonan.getIdPermohonan(),"NK");
        try {

            if (notis == null) {
                context.addMessage("Sila masukkan maklumat notis kosongkan tanah : ");
                return null;
//            } else if (pemilik == null) {
//                context.addMessage("Sila masukkan maklumat lokasi aduan : " + permohonan.getIdPermohonan());
//                return null;
//            }
//                else if (mc == null) {
//                context.addMessage("Sila masukkan maklumat orang disyaki : " + permohonan.getIdPermohonan());
//                return null;
//            }
//                else if (permohonan.getSenaraiBangunan().isEmpty()) {
//                context.addMessage("Sila masukkan maklumat laporan lawatan tapak dan siasatan : " + permohonan.getIdPermohonan());
//                return null;
//            }
//                else if (permohonan.getSenaraiBangunan().isEmpty()) {
//                context.addMessage("Sila masukkan maklumat notis kosongkan tanah : " + permohonan.getIdPermohonan());
//                return null;
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
