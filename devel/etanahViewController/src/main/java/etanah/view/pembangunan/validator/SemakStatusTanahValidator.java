/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import java.math.BigDecimal;

/**
 *
 * @author syaiful
 */
public class SemakStatusTanahValidator implements StageListener {

    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private PembangunanService devService;
    private static final Logger LOG = Logger.getLogger(SemakStatusTanahValidator.class);
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
        BigDecimal luas = BigDecimal.ZERO;
        BigDecimal luasMax = BigDecimal.ZERO;
        BigDecimal limit = new BigDecimal(40);
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hakmilik;

        List<HakmilikPermohonan> hm = devService.findHakmilikPermohonanByIdPermohonan(permohonan.getIdPermohonan());

        LOG.info("hm.size() : " + hm.size());

           for (int ad1 = 0; ad1 < hm.size(); ad1++) {

            hakmilik = hm.get(ad1);

            if (ad1 == 0) {
                luasMax = hakmilik.getHakmilik().getLuas();
            } else if (luasMax.compareTo(hakmilik.getHakmilik().getLuas()) < 0) {
                luasMax = hakmilik.getHakmilik().getLuas();
            }
        }

        for (int ad1 = 0; ad1 < hm.size(); ad1++) {

            hakmilik = hm.get(ad1);

            LOG.info("Luas MAx : " + luasMax);

            if (hakmilik.getHakmilik().getKodUnitLuas().getKod().equals("M")) {

                luas = luasMax.divide(new BigDecimal(10000));
                LOG.info("Luas : " + luas);
                LOG.info("Limit : " + limit);

                if (luas.compareTo(limit) >= 0) {
                    LOG.info("###### LD");
                    value = "LD";
                } else {
                    LOG.info("###### NM");
                    value = "NM";
                }
                LOG.info("Value Hektar " + value);

            } else if (hakmilik.getHakmilik().getKodUnitLuas().getKod().equals("H")) {

                luas = luasMax;

                if (luas.compareTo(limit) >= 0) {
                    LOG.info("###### LD");
                    value = "LD";
                } else {
                    LOG.info("###### NM");
                    value = "NM";
                }

            } else {

                LOG.error("Kod Unit Luas tidak tepat!");
                throw new RuntimeException("Kod Unit Luas tidak tepat!");
            }

        }
//            hakmilik = hm.get(ad);

//        }

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
