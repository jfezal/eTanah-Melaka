/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class PembatalanPermohonanValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private static final Logger LOG = Logger.getLogger(PembatalanPermohonanValidator.class);

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
        LOG.info("------beforeComplete-------");
        Permohonan permohonanla = context.getPermohonan();
        String idPermohonan = permohonanla.getIdPermohonan();
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanChartingKeputusan(idPermohonan);
        if (fasaPermohonan == null) {
            if (permohonanla.getKodUrusan().getKod().equals("PTSP")) {
                fasaPermohonan = lelongService.findFasaPermohonanChartingKeputusanPTSP(idPermohonan);
            } else {
                fasaPermohonan = lelongService.findFasaPermohonanChartingKeputusan831b(idPermohonan);
            }
        }
//        LOG.info("-------fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
        if (fasaPermohonan.getKeputusan().getKod().equals("TL")) {

            proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
            return proposedOutcome;
        }
        return proposedOutcome;
//        return null;
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
