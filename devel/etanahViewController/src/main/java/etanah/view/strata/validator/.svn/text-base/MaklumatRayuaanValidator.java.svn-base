
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Sreenivasa Reddy Munagala.
 */
public class MaklumatRayuaanValidator implements StageListener {

    @Inject
    StrataPtService strService;
    private PermohonanStrata pemilik;
    private BadanPengurusan mc;
    @Inject
    PermohonanDAO permohonanDAO;
    private static final Logger LOG = Logger.getLogger(MaklumatRayuaanValidator.class);




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
         permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
        pemilik = strService.findID(permohonan.getIdPermohonan());
        mc = strService.findBdn(permohonan.getIdPermohonan());
        try {

               if(permohonan.getSebab()==null){
                context.addMessage("Sila masukkan maklumat sebab sebab : " + permohonan.getIdPermohonan());
                return null;
            }
                else if(permohonan.getTempohHari()==null){
                context.addMessage("Sila masukkan maklumat sebab sebab : " + permohonan.getIdPermohonan());
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
        return proposedOutcome;
    }
}
