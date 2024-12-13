/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.Permohonan;
import etanah.service.LaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;

/**
 *
 * @author aminah.abdmutalib
 */
public class CompleteTaskValidator implements StageListener {

    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    LaporanTanahService laporanTanahService;

    private FasaPermohonan fasaPermohonan;

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

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

        String[] tnameFasa = {"permohonan", "idAliran"};
        Object[] modelFasa = {permohonan, "Maklum_syor"};

        List<FasaPermohonan> listFasa = fasaPermohonanDAO.findByEqualCriterias(tnameFasa, modelFasa, null);
        if (listFasa.size() != 0) {
            fasaPermohonan = listFasa.get(0);
            KodKeputusan kpsn = new KodKeputusan();
            kpsn.setKod("TK");
            fasaPermohonan.setKeputusan(kpsn);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
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
