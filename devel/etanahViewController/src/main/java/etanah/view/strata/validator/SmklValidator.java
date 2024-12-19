
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.service.StrataPtService;
import etanah.service.common.LelongService;
import etanah.service.common.TaskDebugService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @ Murali
 */
public class SmklValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    private TaskDebugService tds;
    private Permohonan mohonRMHS;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(SmklValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        FasaPermohonan mohonFasa = new FasaPermohonan();
        Permohonan permohonan = context.getPermohonan();
        
       if (permohonan.getKodUrusan().getKod().equals("PNB") || permohonan.getKodUrusan().getKod().equals("RTHS")
           || permohonan.getKodUrusan().getKod().equals("RTPS")) 
       { 
        
        try {
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "semaklaporan");
            if (mohonFasa.getKeputusan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan syor Penolong Pengarah Strata/Penolong Pegawai Tadbir. ");
                return null;
            }
            else
            {
            mohonFasa.setTarikhKeputusan(new Date());
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
       }
       
        if (permohonan.getKodUrusan().getKod().equals("RBHS")) 
       { 
        
        try {
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "semakkertasptg");
            if (mohonFasa.getKeputusan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan syor Penolong Pengarah Strata/Penolong Pegawai Tadbir. ");
                return null;
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
       }      
    if (permohonan.getKodUrusan().getKod().equals("RMH1A") || permohonan.getKodUrusan().getKod().equals("RMHS1")
        || permohonan.getKodUrusan().getKod().equals("RMHS5") || permohonan.getKodUrusan().getKod().equals("RMHS6")
        || permohonan.getKodUrusan().getKod().equals("RMHS7")) 
       { 
        
        try {
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "semakkertasptg");
            if (mohonFasa.getKeputusan() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan syor Penolong Pengarah Strata/Penolong Pegawai Tadbir. ");
                return null;
            }
            else
            {
            mohonFasa.setTarikhKeputusan(new Date());
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
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
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        //return proposedOutcome;
        return "back";
    }
}
