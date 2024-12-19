/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.form;

import etanah.model.Hakmilik;
import java.math.BigDecimal;

/**
 *
 * @author mohd.faidzal
 */
public class HakmilikTuntutan {
     Hakmilik hakmilik;
        BigDecimal tuntutan;

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public BigDecimal getTuntutan() {
        return tuntutan;
    }

    public void setTuntutan(BigDecimal tuntutan) {
        this.tuntutan = tuntutan;
    }
        
        
}
