/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.portal.ws.form;

import java.math.BigDecimal;

/**
 *
 * @author faidzal
 */
public class SejarahTransaksiPortal {
    
    String idkewdok;
    String tarikhResit;
    String idHakmilik;
    String jenistrans;
    BigDecimal amaun;
    String nofpx;
    String status;
    String tarikhTrasaksi;

    public String getNofpx() {
        return nofpx;
    }

    public String getTarikhTrasaksi() {
        return tarikhTrasaksi;
    }

    public void setTarikhTrasaksi(String tarikhTrasaksi) {
        this.tarikhTrasaksi = tarikhTrasaksi;
    }

    public void setNofpx(String nofpx) {
        this.nofpx = nofpx;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdkewdok() {
        return idkewdok;
    }

    public void setIdkewdok(String idkewdok) {
        this.idkewdok = idkewdok;
    }

    public String getTarikhResit() {
        return tarikhResit;
    }

    public void setTarikhResit(String tarikhResit) {
        this.tarikhResit = tarikhResit;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getJenistrans() {
        return jenistrans;
    }

    public void setJenistrans(String jenistrans) {
        this.jenistrans = jenistrans;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }
    
    
    
}
