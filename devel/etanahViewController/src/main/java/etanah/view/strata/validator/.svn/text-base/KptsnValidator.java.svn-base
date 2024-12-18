/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @ trafidah
 */
public class KptsnValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;    
    private Permohonan mohonRMHS;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(BangunanKosRendahValidator.class);

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
        FasaPermohonan mohonFasa = new FasaPermohonan();
       
        
        if (permohonan.getKodUrusan().getKod().equals("PPPP")) {
         mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "semaksijiltptg");            
        
            if (mohonFasa != null) {                      
              
              KodKeputusan kodkpsn = kodKeputusanDAO.findById("L"); 
              mohonFasa.setKeputusan(kodkpsn);
              permohonan.setKeputusan(kodkpsn);
              Pengguna peng = (Pengguna) context.getPengguna();
              permohonan.setKeputusanOleh(peng);
              permohonan.setTarikhKeputusan(new Date());
              strService.updateMohon(permohonan);
             LOG.debug("----Update Keputusan Success----:"); 
             
            }
        }                   
 
        if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
            || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")
            || permohonan.getKodUrusan().getKod().equals("PBBSS") || permohonan.getKodUrusan().getKod().equals("PHPC")
            || permohonan.getKodUrusan().getKod().equals("PHPP")) {
         
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "bayaran5F");            
        
            if (mohonFasa != null) {                      
              
              KodKeputusan kodkpsn = kodKeputusanDAO.findById("L");               
              permohonan.setKeputusan(kodkpsn);
              Pengguna peng = (Pengguna) context.getPengguna();
              permohonan.setKeputusanOleh(peng);
              permohonan.setTarikhKeputusan(new Date());
              strService.updateMohon(permohonan);
              
              mohonFasa.setKeputusan(kodkpsn);
              strService.saveFasaMohon(mohonFasa);
              
             LOG.debug("----Update Keputusan Success----:"); 
             
            }
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

