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
import java.math.MathContext;

/**
 *
 * @author syaiful
 */
public class TentuHakmilikUmpukanValidator implements StageListener {

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
        BigDecimal limit = new BigDecimal(0.4);
        BigDecimal jumlahPihak = BigDecimal.ZERO;
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hakmilikP;
        PermohonanPlotPelan pp;

//        List<HakmilikPermohonan> hm = devService.findHakmilikPermohonanByIdPermohonan(permohonan.getIdPermohonan());
        List<PermohonanPlotPelan> lpp = devService.findPermohonanPlotPelanPemilikanByIdPermohonan(permohonan.getIdPermohonan());
        jumlahPihak = new BigDecimal(lpp.size());

//        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
//            hakmilikP = hm.get(ad1);
//            if (ad1 == 0) {
//                luasMax = hakmilikP.getHakmilik().getLuas();
//            } else if (luasMax.compareTo(hakmilikP.getHakmilik().getLuas()) <= 0) {
//                luasMax = hakmilikP.getHakmilik().getLuas();
//            }
//        }
        boolean ptd=false;
        boolean ptg=false;
        boolean mmk=false;
        
        for(int a=0; a<lpp.size();a++){
            PermohonanPlotPelan ppp = (PermohonanPlotPelan)lpp.get(a);
            
            if(ppp.getKodHakmilikTetap().getKod().equals("HSM") || ppp.getKodHakmilikTetap().getKod().equals("GM") || ppp.getKodHakmilikTetap().getKod().equals("PM")){
                if(ppp.getKodUnitLuas().getKod().equals("M")){
                    BigDecimal luasCompare = new BigDecimal(4046.8545);
                    if(ppp.getLuas().compareTo(luasCompare) >= 0){
                        ptg=true;
                    }else{
                        mmk=true;
                    }
                }else if(ppp.getKodUnitLuas().getKod().equals("H")){
                    BigDecimal luasCompare = new BigDecimal(0.4047);
                    if(ppp.getLuas().compareTo(luasCompare) >= 0){
                        ptg=true;
                    }else{
                        mmk=true;
                    }
                }
            }else if(ppp.getKodHakmilikTetap().getKod().equals("HSD") || ppp.getKodHakmilikTetap().getKod().equals("GRN") || ppp.getKodHakmilikTetap().getKod().equals("PN")){
                if(ppp.getKodUnitLuas().getKod().equals("M")){
                    BigDecimal luasCompare = new BigDecimal(4046.8545);
                    if(ppp.getLuas().compareTo(luasCompare) >= 0){
                        ptg=true;
                    }else{
                        mmk=true;
                    }
                }else if(ppp.getKodUnitLuas().getKod().equals("H")){
                    BigDecimal luasCompare = new BigDecimal(0.4047);
                    if(ppp.getLuas().compareTo(luasCompare) >= 0){
                        ptg=true;
                    }else{
                        mmk=true;
                    }
                }
            }
        }
        
        if(ptg && mmk){
            value = "MK";
        }else if(ptd && mmk){
            value = "MK";
        }else if(ptd && !mmk){
            value = "MD";
        }else if(ptg && !mmk){
            value = "MG";
        }else if(!ptg && mmk){
            value = "MK";
        }else if(!ptd && mmk){
            value = "MK";
        }
        
        
//        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
//            hakmilikP = hm.get(ad1);
//            String kod = hakmilikP.getHakmilik().getKodHakmilik().getKod();
//            String kodLuas = hakmilikP.getHakmilik().getKodUnitLuas().getKod();
//
//            LOG.info("##### Log Umpukan Hakmilik Start Validator #######");
//            LOG.info("Luas Max : " + luasMax);
//            LOG.info("Jumlah Pihak : " + jumlahPihak);
//            
//            if (kod.equals("HSM") || kod.equals("GM") || kod.equals("PM")) {
//                LOG.info("Kod Luas in M: " + kodLuas);
//                if (kodLuas.equals("M")) {
//                    luas = luasMax.divide(new BigDecimal(10000));
//                    LOG.info("Luas : " + luas);
//                    luas = luas.divide(jumlahPihak);
//                    LOG.info("Luas / jumlahPemilik : " + luas);
//                    LOG.info("Limit : " + limit);
//                    //if (luas.compareTo(limit) >= 1) {
//                    if (luas.compareTo(limit) >= 0) {
//                        LOG.info("###### MD ");
//                        if (ktgTanah.equals("1")) {
//                            value = "MD";
//                        } else {
//                            LOG.error("Kod Keterangan Tanah tidak tepat!" + ktgTanah);
//                            throw new RuntimeException("Kod Keterangan Tanah tidak tepat!");
//                        }
//                    } else {
//                        LOG.info("###### MK ");
//                        value = "MK";
//                    }
//                    LOG.info("Value Hektar " + value);
//                } else if (kodLuas.equals("H")) {
//                    LOG.info("Kod Luas in H: " + kodLuas);
//                    luas = luasMax;
//                    LOG.info("Luas : " + luas);
//                    luas = luas.divide(jumlahPihak, MathContext.DECIMAL32);
//                    LOG.info("Luas / jumlahPemilik : " + luas);
//                    //if (luas.compareTo(limit) >= 1) {
//                    if (luas.compareTo(limit) >= 0) {
//                        LOG.info("###### MD ");
//                        if (ktgTanah.equals("1")) {
//                            value = "MD";
//                        } else {
//                            LOG.error("Kod Keterangan Tanah tidak tepat!" + ktgTanah);
//                            throw new RuntimeException("Kod Keterangan Tanah tidak tepat!");
//                        }
//                    } else {
//                        LOG.info("###### MK ");
//                        value = "MK";
//                    }
//                } else {
//                    LOG.error("Kod Unit Luas tidak tepat!");
//                    throw new RuntimeException("Kod Unit Luas tidak tepat!");
//                }
//            } else if (kod.equals("HSD") || kod.equals("GRN") || kod.equals("PN")) {
//                LOG.info("######Kod Hakmilik : " + kod + " Kategori Tanah " + ktgTanah);
//                if (kodLuas.equals("M")) {
//                    LOG.info("Kod Luas in M: " + kodLuas);
//                    luas = luasMax.divide(new BigDecimal(10000));
//                    LOG.info("Luas : " + luas);
//                    luas = luas.divide(jumlahPihak);
//                    LOG.info("Luas / jumlahPemilik : " + luas);
//                    LOG.info("Limit : " + limit);
//                    //if (luas.compareTo(limit) >= 1) {
//                    if (luas.compareTo(limit) >= 0) {
//                        LOG.info("###### MG ");
//                        if (ktgTanah.equals("1")) {
//                            value = "MG";
//                        } else {
//                            LOG.error("Kod Keterangan Tanah tidak tepat!" + ktgTanah);
//                            throw new RuntimeException("Kod Keterangan Tanah tidak tepat!");
//                        }
//                    } else {
//                        LOG.info("###### MK ");
//                        value = "MK";
//                    }
//                    LOG.info("Value Hektar " + value);
//                } else if (kodLuas.equals("H")) {
//                    LOG.info("Kod Luas in H: " + kodLuas);
//                    luas = luasMax;
//                    LOG.info("Luas : " + luas);
//                    luas = luas.divide(jumlahPihak);
//                    LOG.info("Luas / jumlahPemilik : " + luas);
//                    //if (luas.compareTo(limit) >= 1) {
//                    if (luas.compareTo(limit) >= 0) {
//                        LOG.info("###### MG ");
//                        if (ktgTanah.equals("1")) {
//                            value = "MG";
//                        } else {
//                            LOG.error("Kod Keterangan Tanah tidak tepat!" + ktgTanah);
//                            throw new RuntimeException("Kod Keterangan Tanah tidak tepat!");
//                        }
//                    } else {
//                        LOG.info("###### MK ");
//                        value = "MK";
//                    }
//                } else {
//                    LOG.error("Kod Unit Luas tidak tepat!");
//                    throw new RuntimeException("Kod Unit Luas tidak tepat!");
//                }
//            }
//            } else {
//                LOG.error("Kod Keterangan Tanah tidak tepat!" + ktgTanah);
//                throw new RuntimeException("Kod Keterangan Tanah tidak tepat!");
//            }
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
