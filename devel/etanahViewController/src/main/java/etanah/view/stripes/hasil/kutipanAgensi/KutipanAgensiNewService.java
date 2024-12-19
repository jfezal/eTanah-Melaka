/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil.kutipanAgensi;

import etanah.model.Akaun;
import java.math.BigDecimal;

/**
 *
 * @author mohd.faidzal
 */
public class KutipanAgensiNewService {

    public void updateTransEtanah(BigDecimal amount, Akaun akaun, BigDecimal sumKewTrans) {
        switch (amount.compareTo(sumKewTrans)) {
            case -1:
                //kurang
                break;
            case 0:
                //sama
                break;
            case 1:
                //lebih
                break;
            default:
                break;
        }

    }
////    Landed
//61401 - cukai semasa
//61402 - tunggakan cukai
//76152 - denda lewat
//72457 - notis 6A
//
//Strata
//61501 - cukai petak semasa
//61502 - tunggakan cukai petak
//76156 - denda lewat petak
//72457 - notis 6A

}
