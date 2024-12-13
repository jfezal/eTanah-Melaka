/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
public class SemakPindaanValidator implements StageListener {
    private static final Logger LOG = Logger.getLogger(SemakPindaanValidator.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /***
     *edit untuk guna di TSBSN shaja
     *
     */
    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        //Pengguna p = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        String idAliran = new String();
        if(permohonan.getKodUrusan().getKod().equals("TSBSN")){
            idAliran = "perakuanptg";
        }  
        if(permohonan.getKodUrusan().getKod().equals("TSKSN")){
            idAliran = "keputusanptgpertimb";
        }
        if(permohonan.getKodUrusan().getKod().equals("TSPSN")){
            idAliran = "perakuanrencanaringkasptg";
        }
        FasaPermohonan fasaPermohonan = devService.findFasaPermohonanByIdAliran(idPermohonan,idAliran);        
        proposedOutcome = fasaPermohonan.getKeputusan().getKod();
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
