/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.jomPay;

import etanah.model.jompay.JomPayFailMuatnaik;
import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public class SenaraiTransaksiForm {
    JomPayFailMuatnaik j;
    String tarikhMuatNaik;
    Long ok;
    Long xok;

    public JomPayFailMuatnaik getJ() {
        return j;
    }

    public void setJ(JomPayFailMuatnaik j) {
        this.j = j;
    }

    public Long getOk() {
        return ok;
    }

    public void setOk(Long ok) {
        this.ok = ok;
    }

    public Long getXok() {
        return xok;
    }

    public void setXok(Long xok) {
        this.xok = xok;
    }

    public String getTarikhMuatNaik() {
        return tarikhMuatNaik;
    }

    public void setTarikhMuatNaik(String tarikhMuatNaik) {
        this.tarikhMuatNaik = tarikhMuatNaik;
    }

 
    
}
