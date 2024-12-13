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


public class SemakHakmilikValidator implements StageListener {

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
        BigDecimal luas = BigDecimal.ZERO;
        BigDecimal luasMax = BigDecimal.ZERO;
        BigDecimal luasLotMax = BigDecimal.ZERO;
        BigDecimal limit = new BigDecimal(40);
        BigDecimal jumlahPihak = BigDecimal.ZERO;
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hakmilikP;
        PermohonanPlotPelan pp;

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
            String kodLuas = hakmilikP.getHakmilik().getKodUnitLuas().getKod();
            
            LOG.info("##### Log Umpukan Hakmilik Start Validator");
            LOG.info("Luas MAx : " + luasMax);
                if (kod.equals("HSM") || kod.equals("GM") || kod.equals("PM")) {

                    if (kodLuas.equals("M")) {
                        LOG.info("Kod Luas in M: " + kodLuas);
                        luas = luasMax.divide(new BigDecimal(10000));
                        LOG.info("Luas : " + luas);                        
                        LOG.info("Limit : " + limit);                        
                        if (luas.compareTo(limit) >= 0) {
                            LOG.info("###### MD ");
                           value = "JT";
                        } else {                            
                            value = "NT";
                        }
                        LOG.info("Value Hektar " + value);
                    } else if (kodLuas.equals("H")) {
                        LOG.info("Kod Luas in H: " + kodLuas);
                        luas = luasMax;
                        LOG.info("Luas : " + luas);                        
                        if (luas.compareTo(limit) >= 0) {
                            LOG.info("###### MD ");
                            value = "JT";
                        } else {                            
                            value = "NT";
                        }
                    } else {
                        LOG.error("Kod Unit Luas tidak tepat!");
                        throw new RuntimeException("Kod Unit Luas tidak tepat!");
                    }
                } else if (kod.equals("HSD") || kod.equals("GRN") || kod.equals("PN")) {                    
                    if (kodLuas.equals("M")) {
                        LOG.info("Kod Luas in M: " + kodLuas);
                        luas = luasMax.divide(new BigDecimal(10000));
                        LOG.info("Luas : " + luas);                        
                        LOG.info("Limit : " + limit);                        
                        if (luas.compareTo(limit) >= 0) {
                            value = "JD";
                        } else {                            
                            value = "ND";
                        }
                        LOG.info("Value Hektar " + value);
                    } else if (kodLuas.equals("H")) {
                        LOG.info("Kod Luas in H: " + kodLuas);
                        luas = luasMax;
                        LOG.info("Luas : " + luas);                        
                        if (luas.compareTo(limit) >= 0) {
                            value = "JD";
                        } else {                            
                            value = "ND";
                        }
                    } else {
                        LOG.error("Kod Unit Luas tidak tepat!");
                        throw new RuntimeException("Kod Unit Luas tidak tepat!");
                    }
                }
//            } else {
//                LOG.error("Kod Keterangan Tanah tidak tepat!" + ktgTanah);
//                throw new RuntimeException("Kod Keterangan Tanah tidak tepat!");
//            }
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
