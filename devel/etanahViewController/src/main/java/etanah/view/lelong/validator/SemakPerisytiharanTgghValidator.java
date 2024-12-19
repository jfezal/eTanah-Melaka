/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.service.common.EnkuiriService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanDAO;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author mdizzatmashrom
 */
public class SemakPerisytiharanTgghValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enService;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    private String taskId;
    private static final Logger LOG = Logger.getLogger(SemakPerisytiharanTgghValidator.class);

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext sc) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("------beforeComplete-------");
        Pengguna p = (Pengguna) context.getPengguna();

        Permohonan permohonanBaru = context.getPermohonan();
        String idPermohonan = permohonanBaru.getIdPermohonan();

        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanTangguh(permohonanBaru.getIdPermohonan());
        FasaPermohonan fp = lelongService.findFasaPermohonanSediaPengisytiharan(permohonanBaru.getIdPermohonan());

        LOG.info("-------fasaPermohonan kpsn tggh : " + fasaPermohonan.getKeputusan().getKod());

        if (fasaPermohonan != null) {
            if (fp != null) {
                fp.setKeputusan(fasaPermohonan.getKeputusan());
                lelongService.saveUpdate2(fp);
            } else {
                fp = new FasaPermohonan();
                InfoAudit info = p.getInfoAudit();
                info.setDimasukOleh(p);
                info.setTarikhMasuk(new java.util.Date());
                fp.setCawangan(fasaPermohonan.getCawangan());
                fp.setIdPengguna(fasaPermohonan.getIdPengguna());
                fp.setInfoAudit(info);
                fp.setIdAliran("sediaPerisytiharan");
                fp.setPermohonan(permohonanBaru);
                fp.setTarikhHantar(new java.util.Date());
                if (StringUtils.isNotEmpty(fasaPermohonan.getUlasan())) {
                    fp.setUlasan(fasaPermohonan.getUlasan());
                }
                fp.setKeputusan(fasaPermohonan.getKeputusan());
                lelongService.saveUpdate2(fp);
            }
            proposedOutcome = "L";
        }

        LOG.info("fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
        LOG.info("proposedOutcome : " + proposedOutcome);
    
    return proposedOutcome ;
}

@Override
        public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
        public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
        public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
