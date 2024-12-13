
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.*;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.log4j.Logger;
import java.util.*;

/**
 *
 * @ndy
 */
public class BangunanKhasValidator implements StageListener {

    @Inject
    StrataPtService strService;
    private PermohonanStrata pemilik;
    private static final Logger LOG = Logger.getLogger(BangunanKosRendahValidator.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    CommonService comm;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    IWorkflowContext ctxOnBehalf = null;
    private PermohonanStrata mohonStrata = new PermohonanStrata();
    private String stageId;
    private String taskId;
    private String nextStage;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome
    ) {

        Permohonan permohonan = context.getPermohonan();
        pemilik = strService.findID(permohonan.getIdPermohonan());
        try {

            if (permohonan.getSenaraiPemohon().isEmpty()) {
                context.addMessage("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
                return null;
            } else if (pemilik == null) {
                context.addMessage("Sila masukkan maklumat pemilik dan maklumat bangunan : " + permohonan.getIdPermohonan());
                return null;
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context
    ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx
    ) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context
    ) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome
    ) {
//        return proposedOutcome;
        return "back";
    }
}
