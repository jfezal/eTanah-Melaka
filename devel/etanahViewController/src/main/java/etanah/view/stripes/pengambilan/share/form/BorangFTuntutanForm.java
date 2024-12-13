/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.form;

import etanah.model.ambil.BorangPerPB;
import java.math.BigDecimal;

/**
 *
 * @author mohd.faidzal
 */
public class BorangFTuntutanForm {
    BorangPerPB pb;
    Integer totalItem;
    BigDecimal amaun;

    public BorangPerPB getPb() {
        return pb;
    }

    public void setPb(BorangPerPB pb) {
        this.pb = pb;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }
    
    
    
    
}
