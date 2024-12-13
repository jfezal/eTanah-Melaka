/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.lelong.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
public class NotisGantianValidator implements StageListener {

    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    private static final Logger LOG = Logger.getLogger(NotisGantianValidator.class);

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
        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan fasaPermohonan = lelongService.findFasaPermohonanSiasatanKodTG(permohonan.getIdPermohonan());
        if (fasaPermohonan.getKeputusan().getKod().equals("TG")) {

            FasaPermohonan ff = lelongService.findFasaPermohonanNotisGanti(permohonan.getIdPermohonan());

            if (ff != null) {

                ff.setKeputusan(fasaPermohonan.getKeputusan());
                lelongService.saveUpdate2(ff);
            } else {
                ff = new FasaPermohonan();

                Pengguna p = (Pengguna) context.getPengguna();
                InfoAudit info = p.getInfoAudit();
                info.setDimasukOleh(p);
                info.setTarikhMasuk(new java.util.Date());
                ff.setCawangan(fasaPermohonan.getCawangan());
                ff.setIdPengguna(fasaPermohonan.getIdPengguna());
                ff.setInfoAudit(info);
                ff.setIdAliran("notisGantian");
                ff.setPermohonan(permohonan);
                ff.setTarikhHantar(new java.util.Date());
                if (StringUtils.isNotEmpty(fasaPermohonan.getUlasan())) {
                    ff.setUlasan(fasaPermohonan.getUlasan());
                }
                ff.setKeputusan(fasaPermohonan.getKeputusan());
                lelongService.saveUpdate2(ff);
            }

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
