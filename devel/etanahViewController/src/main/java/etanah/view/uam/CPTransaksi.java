/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import etanah.model.Dokumen;

/**
 *
 * @author mohd.faidzal
 */
public class CPTransaksi {
    Dokumen dokumen;
    String idHakmilik;
    String idmohon;
    String noResit;
    String trhResit;

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdmohon() {
        return idmohon;
    }

    public void setIdmohon(String idmohon) {
        this.idmohon = idmohon;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public String getTrhResit() {
        return trhResit;
    }

    public void setTrhResit(String trhResit) {
        this.trhResit = trhResit;
    }
    
    
}
