


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.service.LaporanPelukisPelanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Rohan
 */
public class LaporanPelukisPelanValidator implements StageListener {

   
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;

    private PermohonanLaporanPelan permohonanLaporanPelan;
    private static final Logger LOG = Logger.getLogger(LaporanPelukisPelanValidator.class);


    @Override
    public boolean beforeStart(StageContext context) {

        return true ;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public  String beforeComplete(StageContext context, String proposedOutcome) {

    try {
        Permohonan permohonan = context.getPermohonan();
        permohonanLaporanPelan= laporanPelukisPelanService.findMohonLaporPelanByidMohon(permohonan.getIdPermohonan());
       System.out.println("-----------------permohonan.getIdPermohonan()----------------"+permohonan.getIdPermohonan());
        if(permohonanLaporanPelan == null){
            System.out.println("-----------permohonanLaporanPelan------------------"+permohonanLaporanPelan);
            context.addMessage("Sila Simpan Laporan Pelukis Pelan Terlebih Dahulu");
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


