/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import java.math.BigDecimal;


public class SerahBalikSebahagianValidator implements StageListener {

    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private PembangunanService devService;
    private static final Logger LOG = Logger.getLogger(TentuHakmilikUmpukanValidator.class);
    private String stage;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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
        String outcome = "";
        try {
            outcome = checkStatusLuas(context);
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
        LOG.info("Validator checkStatusLuas..End");
        proposedOutcome = outcome;
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

    public String checkStatusLuas(StageContext context) throws WorkflowException {
        LOG.info("Validator checkStatusLuas..Start");
        String value = "";        
        BigDecimal luasMax = BigDecimal.ZERO;        
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hakmilikP;        

        List<HakmilikPermohonan> hm = devService.findHakmilikPermohonanByIdPermohonan(permohonan.getIdPermohonan());

        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
            hakmilikP = hm.get(ad1);
            if (ad1 == 0) {
                luasMax = hakmilikP.getHakmilik().getLuas();
            } else if (luasMax.compareTo(hakmilikP.getHakmilik().getLuas()) <= 0) {
                luasMax = hakmilikP.getHakmilik().getLuas();
            }
        }
        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
            hakmilikP = hm.get(ad1);
            String kod = hakmilikP.getHakmilik().getKodHakmilik().getKod();            
            
            LOG.info("##### Log Umpukan Hakmilik Start Validator");
            LOG.info("Luas MAx : " + luasMax);
                if (kod.equals("HSM") || kod.equals("GM") || kod.equals("PM")) {
                    value = "HT";
                } else if (kod.equals("HSD") || kod.equals("GRN") || kod.equals("PN")) {                    
                    value = "HF";
                }
        }
        return value;
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
