/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author mohd.faidzal
 */
@Embeddable
public class PPPKTunai implements Serializable {
     @Column (name = "caw")
    private String caw;
      @Column (name = "kod_trans")
    private String kodTrans;
       @Column (name = "no_penyata")
    private String noPenyata;

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getKodTrans() {
        return kodTrans;
    }

    public void setKodTrans(String kodTrans) {
        this.kodTrans = kodTrans;
    }

    public String getNoPenyata() {
        return noPenyata;
    }

    public void setNoPenyata(String noPenyata) {
        this.noPenyata = noPenyata;
    }
       
       
}
