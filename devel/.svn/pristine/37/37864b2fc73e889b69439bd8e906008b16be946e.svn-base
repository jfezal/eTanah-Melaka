/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.service.PelupusanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.workflow.WorkFlowService;

/**
 *
 * @author mazurahayati.yusop
 */
public class NotaValidator implements StageListener {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    etanah.Configuration conf;
    private String stage;
    private static final Logger LOG = Logger.getLogger(NotaValidator.class);

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
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        
        String outcome = " ";
        Permohonan permohonan = context.getPermohonan();
        String idMohon = permohonan.getIdPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        PermohonanNota mohonNota = new PermohonanNota();
        mohonFasa = pelupusanService.findStageIDByIdPermohonan(permohonan.getIdPermohonan());
        LOG.info("fasa = " + mohonFasa.getIdAliran());
        String idAliranMhonFasa = mohonFasa.getIdAliran();
//        mohonNota = pelupusanService.findNota(permohonan.getIdPermohonan(), idAliranMhonFasa);
        
        if (mohonNota == null) {
            context.addMessage(idMohon + " - Sila Isikan Arahan di Tab Nota.");
            return null;
        }
        
        return proposedOutcome;
        
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
