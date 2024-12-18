
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.service.ConsentPtdService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class UlasanValidation implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    ConsentPtdService consentService;
    private static final Logger LOG = Logger.getLogger(UlasanValidation.class);

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

//            if (permohonan.getSenaraiKertas().isEmpty()) {
//
//                    context.addMessage("Sila masukkan ulasan kertas : " + permohonan.getIdPermohonan());
//                    return null;          
//            }
            PermohonanKertas permohonanKertas = consentService.findMohonKertas(permohonan.getIdPermohonan());
            
            if(permohonanKertas == null){
                context.addMessage("Sila masukkan ulasan kertas : " + permohonan.getIdPermohonan());
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
