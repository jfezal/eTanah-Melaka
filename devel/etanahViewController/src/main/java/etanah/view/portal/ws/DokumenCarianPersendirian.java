/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.portal.ws;

/**
 *
 * @author faidzal
 */
public class DokumenCarianPersendirian {
    Long idPortalTransaksi;
    String idHakmilik;
    String tarikh;
    Integer bilPaparan;

    public Long getIdPortalTransaksi() {
        return idPortalTransaksi;
    }

    public void setIdPortalTransaksi(Long idPortalTransaksi) {
        this.idPortalTransaksi = idPortalTransaksi;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public Integer getBilPaparan() {
        return bilPaparan;
    }

    public void setBilPaparan(Integer bilPaparan) {
        this.bilPaparan = bilPaparan;
    }
    
    
}
