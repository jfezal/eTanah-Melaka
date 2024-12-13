/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author syaiful
 */
public class PenyatuanSemakStatusValidator implements StageListener {

    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private PembangunanService devService;
    private static final Logger LOG = Logger.getLogger(PenyatuanSemakStatusValidator.class);
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
        BigDecimal luasTemp = BigDecimal.ZERO;
        BigDecimal luasLotMax = BigDecimal.ZERO;
        BigDecimal limit = new BigDecimal(4);
        String maxKodHakmilik="";
        Permohonan permohonan = context.getPermohonan();
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hakmilikP;
        PermohonanPlotPelan pp;
        List<HakmilikPermohonan> hpList=new ArrayList<HakmilikPermohonan>();
        List<HakmilikPermohonan> hpList1=new ArrayList<HakmilikPermohonan>();
        List<HakmilikPermohonan> hpList2=new ArrayList<HakmilikPermohonan>();

        hpList = devService.findHakmilikPermohonanByIdPermohonan(permohonan.getIdPermohonan());
//        List<PermohonanPlotPelan> lpp = devService.findPermohonanPlotPelanByIdPermohonan(permohonan.getIdPermohonan());

        // Calculate LuasMax
        for (int ad1 = 0; ad1 < hpList.size(); ad1++) {
            hakmilikP = hpList.get(ad1);
            String kodHakmilik = hakmilikP.getHakmilik().getKodHakmilik().getKod();
            String kodLuas = hakmilikP.getHakmilik().getKodUnitLuas().getKod();

            if (kodLuas.equals("M")) {
                luasTemp = hakmilikP.getHakmilik().getLuas();
            } else if (kodLuas.equals("H")) {
                luasTemp = hakmilikP.getHakmilik().getLuas();
                luasTemp=luasTemp.multiply(new BigDecimal(10000));
            }else {
                LOG.error("Kod Unit Luas tidak tepat!");
                throw new RuntimeException("Kod Unit Luas tidak tepat!");
            }

            if (ad1 == 0) {
                luasMax = luasTemp;
                maxKodHakmilik = kodHakmilik;
            } else if (luasMax.compareTo(luasTemp) <= 0) {
                luasMax = luasTemp;
                maxKodHakmilik = kodHakmilik;
            }

            if (kodHakmilik.equals("HSM") || kodHakmilik.equals("GM") || kodHakmilik.equals("PM")) {
                hpList1.add(hakmilikP);
             } else if (kodHakmilik.equals("HSD") || kodHakmilik.equals("GRN") || kodHakmilik.equals("PN")) {
                 hpList2.add(hakmilikP);
             }
        }


        LOG.info("-----------hpList1.size()--------###### :"+hpList1.size());
        LOG.info("-----------hpList2.size()--------###### :"+hpList2.size());

        if(hpList1.size()==0 || hpList2.size()== 0){
            luas = luasMax.divide(new BigDecimal(10000));

            if (maxKodHakmilik.equals("HSM") || maxKodHakmilik.equals("GM") || maxKodHakmilik.equals("PM")) {
                if (luas.compareTo(limit) <= 0) {
                    LOG.info("-----------MaxLuas less than 4 Hektar--------###### HD ");
                    value = "HD";
                }else if (luas.compareTo(limit) > 0) {
                    LOG.info("-----------MaxLuas greater than 4 Hektar--------###### HG");
                    value = "HG";
                }else{
                    LOG.info("-----------MaxLuas equal to 4 Hektar--------######");
                    // Hakmilik PTD and Luas == 4h 
                    value = "HD";
                }                
            }else if (maxKodHakmilik.equals("HSD") || maxKodHakmilik.equals("GRN") || maxKodHakmilik.equals("PN")) {
                if (luas.compareTo(limit) > 0) {
                    value = "HG";
                  LOG.info("-----------HSD,GRN,HG---If-----#####"+value);
                }else{
                    // Hakmilik Pendaftaran and luas <= 4h
                    value = "HG";
                  LOG.info("-----------HSD,GRN,HG---Else-----#####"+value);
                }
            }
        }else{
            value ="HM";
            LOG.info("-----------Else--------#####"+value);
        }
        LOG.info("-----------value--------#####"+value);
        return value;
    }

//    public String checkStatusLuas(StageContext context) throws WorkflowException {
//        LOG.info("Validator checkStatusLuas..Start");
//        String value = "";
//        BigDecimal luas = BigDecimal.ZERO;
//        BigDecimal luasMax = BigDecimal.ZERO;
//        BigDecimal luasLotMax = BigDecimal.ZERO;
//        BigDecimal limit = new BigDecimal(0.4);
//        Permohonan permohonan = context.getPermohonan();
//        HakmilikPermohonan hakmilikP;
//        PermohonanPlotPelan pp;
//
//        List<HakmilikPermohonan> hm = devService.findHakmilikPermohonanByIdPermohonan(permohonan.getIdPermohonan());
//        List<PermohonanPlotPelan> lpp = devService.findPermohonanPlotPelanByIdPermohonan(permohonan.getIdPermohonan());
//
//        for (int ad1 = 0; ad1 < hm.size(); ad1++) {
//            hakmilikP = hm.get(ad1);
//            String kod = hakmilikP.getHakmilik().getKodHakmilik().getKod();
//            String kodLuas = hakmilikP.getHakmilik().getKodUnitLuas().getKod();
//            String ktgTanah = hakmilikP.getHakmilik().getKategoriTanah().getKod();
//
//            if (ad1 == 0) {
//                luasMax = hakmilikP.getHakmilik().getLuas();
//            } else if (luasMax.compareTo(hakmilikP.getHakmilik().getLuas()) <= 0) {
//                luasMax = hakmilikP.getHakmilik().getLuas();
//            }
//            LOG.info("##### Log Umpukan Hakmilik Start Validator");
//            LOG.info("Luas MAx : " + luasMax);
//
//                if (kod.equals("HSM") || kod.equals("GM") || kod.equals("PM")) {
//                    LOG.info("######Kod Hakmilik : " + kod + " Kategori Tanah " + ktgTanah);
//                    if (kodLuas.equals("M")) {
//                        LOG.info("Kod Luas : " + kodLuas);
//                        luas = luasMax.divide(new BigDecimal(10000));
//                        LOG.info("Luas : " + luas);
//                        LOG.info("Limit : " + limit);
//                        if (luas.compareTo(limit) >= 1) {
//                            LOG.info("###### MG ");
//                            value = "MG";
//                        } else {
//                            LOG.info("###### MK ");
//                            value = "MK";
//                        }
//                        LOG.info("Value Hektar " + value);
//                    } else if (kodLuas.equals("H")) {
//                        LOG.info("Kod Luas : " + kodLuas);
//                        luas = luasMax;
//                        if (luas.compareTo(limit) >= 1) {
//                            LOG.info("###### MG ");
//                            value = "MG";
//                        } else {
//                            LOG.info("###### MK ");
//                            value = "MK";
//                        }
//                    } else {
//                        LOG.error("Kod Unit Luas tidak tepat!");
//                        throw new RuntimeException("Kod Unit Luas tidak tepat!");
//                    }
//                } else if (kod.equals("HSD") || kod.equals("GRN") || kod.equals("PN")) {
//                    LOG.info("######Kod Hakmilik : " + kod + " Kategori Tanah " + ktgTanah);
//                    if (kodLuas.equals("M")) {
//                        LOG.info("Kod Luas : " + kodLuas);
//                        luas = luasMax.divide(new BigDecimal(10000));
//                        LOG.info("Luas : " + luas);
//                        LOG.info("Limit : " + limit);
//                        if (luas.compareTo(limit) >= 1) {
//                            LOG.info("###### MG ");
//                            value = "MG";
//                        } else {
//                            LOG.info("###### MK ");
//                            value = "MK";
//                        }
//                        LOG.info("Value Hektar " + value);
//                    } else if (kodLuas.equals("H")) {
//                        LOG.info("Kod Luas : " + kodLuas);
//                        luas = luasMax;
//                        if (luas.compareTo(limit) >= 1) {
//                            LOG.info("###### MG ");
//                            value = "MG";
//                        } else {
//                            LOG.info("###### MK ");
//                            value = "MK";
//                        }
//                    } else {
//                        LOG.error("Kod Unit Luas tidak tepat!");
//                        throw new RuntimeException("Kod Unit Luas tidak tepat!");
//                    }
//                }
//        }
//        return value;
//    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
