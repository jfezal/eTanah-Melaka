/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import java.math.BigDecimal;

/**
 *
 * @author mohd.faidzal
 */
public class AkaunForm {
     String noAccount;
    BigDecimal amaunt;

    public String getNoAccount() {
        return noAccount;
    }

    public void setNoAccount(String noAccount) {
        this.noAccount = noAccount;
    }

    public BigDecimal getAmaunt() {
        return amaunt;
    }

    public void setAmaunt(BigDecimal amaunt) {
        this.amaunt = amaunt;
    }

}
