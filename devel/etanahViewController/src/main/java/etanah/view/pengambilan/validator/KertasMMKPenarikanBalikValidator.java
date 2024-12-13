
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import etanah.view.pengambilan.validator.*;
import com.google.inject.Inject;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import etanah.service.PengambilanService;
import etanah.service.PengambilanService1;
import etanah.dao.KodKeputusanDAO;
import etanah.model.FasaPermohonan;

/**
 *
 * @author massita
 */
public class KertasMMKPenarikanBalikValidator implements StageListener {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PengambilanService1 pengambilanService1;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private static final Logger LOG = Logger.getLogger(KertasMMKPenarikanBalikValidator.class);
    private String idSebelum;

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
        String idPermohonan = permohonan.getIdPermohonan();
        FasaPermohonan fasaPermohonan = pengambilanService1.findFasaPermohonan(idPermohonan);
        Permohonan psblm=permohonan.getPermohonanSebelum();
        String kod = "TS" ; //kod status permohonan
        
        try {
            if (permohonan.getSenaraiKertas().isEmpty()) {

                context.addMessage("Sila masukkan maklumat kertas MMK : " + permohonan.getIdPermohonan());
                return null;

            }

            if (permohonan.getPermohonanSebelum().getIdPermohonan().isEmpty()) {

                context.addMessage("Sila masukkan id Permohonan Terdahulu : " + permohonan.getIdPermohonan());
                return null;

            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
        
       
        return proposedOutcome;
    }

    public String getIdSebelum() {
        return idSebelum;
    }

    public void setIdSebelum(String idSebelum) {
        this.idSebelum = idSebelum;
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
