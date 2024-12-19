/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.ws.form;

import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class PermohonanForm {
    protected String noFail;
    protected String noJilid;
    protected String noPU;
    protected String noRujukan;
    protected String noWarta;
    protected String tarikh;
    protected String tarikhKeputusan;
    protected String tarikhWarta;
    protected String catatan;
    protected String catatanKeputusan;
    protected String jenis;
    protected String keputusan;  
        protected List<HakmilikForm> listHakmilik;
    protected List<DokumenForm> listDokumen;

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getNoJilid() {
        return noJilid;
    }

    public void setNoJilid(String noJilid) {
        this.noJilid = noJilid;
    }

    public String getNoPU() {
        return noPU;
    }

    public void setNoPU(String noPU) {
        this.noPU = noPU;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(String tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getCatatanKeputusan() {
        return catatanKeputusan;
    }

    public void setCatatanKeputusan(String catatanKeputusan) {
        this.catatanKeputusan = catatanKeputusan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public List<HakmilikForm> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<HakmilikForm> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public List<DokumenForm> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<DokumenForm> listDokumen) {
        this.listDokumen = listDokumen;
    }
    
    
}
