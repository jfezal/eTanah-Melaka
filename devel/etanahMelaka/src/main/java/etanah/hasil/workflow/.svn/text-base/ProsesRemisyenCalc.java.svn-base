/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

import java.math.BigDecimal;

/**
 *
 * @author abu.mansur
 */
public class ProsesRemisyenCalc {
    public BigDecimal calc(String kodUrusan, String kodHakmilik, BigDecimal cukai, BigDecimal luas, String kodUOM){
        BigDecimal rtnDecimal = BigDecimal.ZERO;
        if("REMTS".equals(kodUrusan)){
            String value = null;
            if("GRN".equals(kodHakmilik) || "PN".equals(kodHakmilik) || "HSM".equals(kodHakmilik)){
                value = "15";
                if("H".equals(kodUOM)){
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
                if("M".equals(kodUOM)){
                    luas = luas.divide(new BigDecimal(10000),8,BigDecimal.ROUND_UP); // convert meterpersegi to hektar
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
                if("K".equals(kodUOM)){
                    luas = luas.multiply(new BigDecimal(0.0000092)); // convert kakipersegi to hektar
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
                if("E".equals(kodUOM)){
                    luas = luas.multiply(new BigDecimal(0.404686)); // convert Ekar to hektar
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
            }else{
                value = "12";
                if("H".equals(kodUOM)){
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
                if("M".equals(kodUOM)){
                    luas = luas.divide(new BigDecimal(10000),8,BigDecimal.ROUND_UP); // convert meterpersegi to hektar
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
                if("K".equals(kodUOM)){
                    luas = luas.multiply(new BigDecimal(0.0000092)); // convert kakipersegi to hektar
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
                if("E".equals(kodUOM)){
                    luas = luas.multiply(new BigDecimal(0.404686)); // convert Ekar to hektar
                    rtnDecimal = luas.multiply(new BigDecimal(value));
                }
            }
        }
        if("REMSB".equals(kodUrusan)){
            rtnDecimal = cukai.subtract(new BigDecimal(1.0));
//            System.out.println("ProsesRemisyenCalc::calc::REMTS::rtnDecimal = "+rtnDecimal);
        }
        if("REMRI".equals(kodUrusan)){
            rtnDecimal = cukai.subtract(new BigDecimal(1.0)); // can have enhancement later
//            System.out.println("ProsesRemisyenCalc::calc::REMRI::rtnDecimal = "+rtnDecimal);
        }
        return rtnDecimal;
    }

}
