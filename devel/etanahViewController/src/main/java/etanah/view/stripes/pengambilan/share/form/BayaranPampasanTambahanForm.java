/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.form;

import etanah.model.Hakmilik;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class BayaranPampasanTambahanForm {
    BigDecimal deposit50;
    BigDecimal deposit125;
    BigDecimal depoTotal;
    List<HakmilikTuntutan> listHakmilikTututan;
    BigDecimal tuntutanTotal;

    public BigDecimal getDeposit50() {
        return deposit50;
    }

    public void setDeposit50(BigDecimal deposit50) {
        this.deposit50 = deposit50;
    }

    public BigDecimal getDeposit125() {
        return deposit125;
    }

    public void setDeposit125(BigDecimal deposit125) {
        this.deposit125 = deposit125;
    }

    public BigDecimal getDepoTotal() {
        return depoTotal;
    }

    public void setDepoTotal(BigDecimal depoTotal) {
        this.depoTotal = depoTotal;
    }

    public List<HakmilikTuntutan> getListHakmilikTututan() {
        return listHakmilikTututan;
    }

    public void setListHakmilikTututan(List<HakmilikTuntutan> listHakmilikTututan) {
        this.listHakmilikTututan = listHakmilikTututan;
    }

    public BigDecimal getTuntutanTotal() {
        return tuntutanTotal;
    }

    public void setTuntutanTotal(BigDecimal tuntutanTotal) {
        this.tuntutanTotal = tuntutanTotal;
    }
    
    
    
    
}
