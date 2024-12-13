/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodKadarCukai;
import etanah.model.KodUOM;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Orogenicgroup
 */
public class CalcTaxPelupusan {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanahActionBeanContext etanahConf;
    @Inject
    RegService regService;
    private final static Logger logger = Logger.getLogger(CalcTax.class);
    private BigDecimal total = BigDecimal.ZERO;
    private String kodNegeri;
    private List<KodKadarCukai> kadarCukaiList = new ArrayList();
    private Long five = new Long(5);
    private Long zero = new Long(0);

    public BigDecimal calculate(String kodTanah, String kodBpm, String kodUOM, BigDecimal luas,HakmilikPermohonan hakmilikPermohonan, String kodRizab) {
        kodNegeri = etanahConf.getKodNegeri();
        logger.debug(":start kira cukai:");
        logger.debug("kodBpm : " + kodBpm);
        logger.debug("kodTanah :" + kodTanah);
        logger.debug("kodUOM :" + kodUOM);
        logger.debug("kodRizab :" + kodRizab);
        //String result = "";
        if (StringUtils.isNotBlank(kodBpm) && StringUtils.isNotBlank(kodTanah)) {

            double q = 0;
            double r = 0;
            BigDecimal conv = BigDecimal.ZERO;
            BigDecimal conv2 = BigDecimal.ZERO;
            BigDecimal totalLuasByM = BigDecimal.ZERO;
            BigDecimal totalLuasByH = BigDecimal.ZERO;
            BigDecimal kC = BigDecimal.ZERO;

            if (kodNegeri.equals("04")) {
                logger.debug(":kira Cukai for MELAKA:");
                if (kodTanah.equals("P03") || kodTanah.equals("P01") || kodTanah.equals("P02")) {
                    //String unitLuas = hakmilik.getKodUnitLuas().getKod();
                    logger.debug("unitLuas:" + kodUOM);
                    logger.debug("luas:" + luas);
                    /*CONVERT UNIT KE HEKTAR*/
                    totalLuasByH = toHectare(kodUOM, luas);
                    BigDecimal bg = new BigDecimal(5);
                    /*check luas < 5 hectar*/
                    if (totalLuasByH.compareTo(bg) < 0) {
                        kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, zero);
                    } else {
                        kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, five);
                    }
                } else {
                    kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, zero);
                }
            } else {
                logger.debug(":kira Cukai for n9:");
                kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah, zero);
            }

            logger.debug("cari cukai list size:" + kadarCukaiList.size());
            if (kadarCukaiList.size() > 0) {
                kC = kadarCukaiList.get(0).getKadarMeterPersegi();
                //unit conversion taken from http://en.wikipedia.org/wiki/Conversion_of_units
                logger.debug("kadarCukai :" + kC);
                logger.debug("unitLuas:" + kodUOM);
                logger.debug("luas:" + luas);
                /*KIRA CUKAI SELAIN PERTANIAN N9 N MELAKA*/
                /*CONVERT UNIT KE METER PERSEGI*/
//                if (!hakmilik.getKategoriTanah().getKod().equals("3")) {
                if (!hakmilikPermohonan.getKategoriTanahBaru().getKod().equals("1")) {
                    if (kodNegeri.equals("04")) {
                        /*IF MELAKA LUAS DIVIDE BY 100 THEN ROUND UP FIRST PATUT LA HASIL MELAKA KAYE*/
                        BigDecimal hundred = new BigDecimal("100");
                        //luas = luas.divide(hundred);
                        luas = toMeter(kodUOM, luas).divide(hundred);
                        logger.debug("luas before round :" + luas);
                        luas = luas.setScale(0, RoundingMode.UP);
                        logger.debug("luas lepas round :" + luas);
                        kC = kC.multiply(hundred);
                        total = luas.multiply(kC);
                    } else {
                        total = toMeter(kodUOM, luas).multiply(kC);
                    }

                } else {
                    /*KIRA CUKAI PERTANIAN N9*/
                    //total = kadarCukaiList.get(0).getKadarMinimum();         
                    /*CONVERT UNIT KE HEKTAR*/
                    total = toHectare(kodUOM, luas).multiply(kadarCukaiList.get(0).getKadarMinimum());
                }
                //if not pertanian get Kadar Minimum
                if (kodNegeri.equals("05")) {
//                    if (!hakmilik.getKategoriTanah().getKod().equals("3")) {
//                    if (!hakmilik.getKategoriTanah().getNama().equalsIgnoreCase("Pertanian")) {
                    if (!hakmilikPermohonan.getKategoriTanahBaru().getKod().equals("1")) {
                        BigDecimal minima = kadarCukaiList.get(0).getKadarMinimum();
                        total = total.max(minima);
                    }
                }
                //melaka set cukai 1/2 utuk rizab melayu
                if (kodNegeri.equals("04")&& hakmilikPermohonan.getKodHakmilik()!=null) {
                    logger.debug("set cukai minima utk hakmilik: " + hakmilikPermohonan.getKategoriTanahBaru().getKod());
                    BigDecimal minimaAll = new BigDecimal(12);
                    total = total.max(minimaAll);
                    if (hakmilikPermohonan.getKodHakmilik().getKod().equals("GMM") || hakmilikPermohonan.getKodHakmilik().getKod().equals("PMM")
                            || hakmilikPermohonan.getKodHakmilik().getKod().equals("HMM")) {
                        BigDecimal minima = new BigDecimal(6);
                        BigDecimal two = new BigDecimal(2);
                        total = total.divide(two);
                        total = total.max(minima);
                        //total = total.divide(new BigDecimal(2));
                    }
                }
                //set cukai 1/2 if rizab melayu
                if (StringUtils.isNotBlank(kodRizab)) {
                    logger.debug("set cukai 1/2 kerana rezab Melayu ");
                    if (kodRizab.equals("3")) {
                        BigDecimal two = new BigDecimal(2);
                        total = total.divide(two);
                    }
                }
                //round up the total
                total = total.setScale(0, RoundingMode.UP);
            } else {
                total = BigDecimal.ZERO;
            }
            //result = String.valueOf(total);
            logger.debug("result :" + total);
        }
        return total;
    }

    public BigDecimal kiraUnitLuas(String kodUOMKepada, String kodUOMDari, BigDecimal luas) {
        total = BigDecimal.ZERO;

        if (kodUOMKepada.equals("H")) {
            total = toHectare(kodUOMDari, luas);
        }
        if (kodUOMKepada.equals("M")) {
            total = toMeter(kodUOMDari, luas);
        }
        if (kodUOMKepada.equals("E")) {
            total = toAcre(kodUOMDari, luas);
        }
        if (kodUOMKepada.equals("K")) {
            total = toSquareFoot(kodUOMDari, luas);
        }
        return total;
    }

    public BigDecimal toMeter(String kodUOM, BigDecimal luas) {
        total = BigDecimal.ZERO;
        double q = 0;
        BigDecimal conv = BigDecimal.ZERO;
        BigDecimal totalLuasByM = BigDecimal.ZERO;
        if (kodUOM.equals("D") || kodUOM.equals("E") || kodUOM.equals("P")) {
            q = 4046.8564224;
            conv = BigDecimal.valueOf(q);
            totalLuasByM = luas.multiply(conv);
            //kaki persegi
        } else if (kodUOM.equals("K")) {
            q = 0.09290304;
            conv = BigDecimal.valueOf(q);
            totalLuasByM = luas.multiply(conv);
            //hektar
        } else if (kodUOM.equals("H")) {
            q = 10000;
            conv = BigDecimal.valueOf(q);
            totalLuasByM = luas.multiply(conv);
        } else {
            q = 1;
            conv = BigDecimal.valueOf(q);
            totalLuasByM = luas.multiply(conv);
        }
        logger.debug("unit conv: " + q);
        logger.debug("totalLuasByM: " + totalLuasByM);
        return total = totalLuasByM;
    }

    public BigDecimal toHectare(String kodUOM, BigDecimal luas) {
        double conv = 0.0001;
        return toMeter(kodUOM, luas).multiply(BigDecimal.valueOf(conv));
    }

    public BigDecimal toSquareFoot(String kodUOM, BigDecimal luas) {
        double conv = 10.763910417;
        return toMeter(kodUOM, luas).multiply(BigDecimal.valueOf(conv));
    }

    public BigDecimal toAcre(String kodUOM, BigDecimal luas) {
        double conv = 0.0002471053814672;
        return toMeter(kodUOM, luas).multiply(BigDecimal.valueOf(conv));
    }
}
