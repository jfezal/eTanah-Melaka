/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.plpt.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.IntegrasiPendaftaranService;
import etanah.service.common.PgunaService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class PLPTKeputusanMMKNValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(PLPTKeputusanMMKNValidator.class);
    private static String workflowId = "http://xmlns.oracle.com/etanah_lanjutan_pajakan/Project1/sedia_surat_tolak";
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    IntegrasiPendaftaranService integrasiPendaftaranService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    WorkFlowService service;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        try {
//            proposedOutcome = "T";
            if (proposedOutcome.equals("T")) {
                Permohonan p = context.getPermohonan();
                Pengguna pengguna = context.getPengguna();
                if (WorkFlowService.initiateTask(workflowId,
                        p.getIdPermohonan(), p.getCawangan().getKod(), pengguna.getIdPengguna(),
                        p.getKodUrusan().getNama())) {
                    proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                }
            }
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(PLPTEndorsanPembatalanValidator.class.getName()).log(Level.SEVERE, null, ex);
            proposedOutcome = null;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PLPTEndorsanPembatalanValidator.class.getName()).log(Level.SEVERE, null, ex);
            proposedOutcome = null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
