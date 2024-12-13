/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.form;

/**
 *
 * @author mohd.faidzal
 */
public class BorangBForm {
    String idMohon;
    
    Long idBorangPerPermohonan;
    Long id;
    String alamatHantar;
    String namaPenghantar;
    String tarikhHantar;
    String penerimaNama;
    String penerimaNokp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public String getAlamatHantar() {
        return alamatHantar;
    }

    public void setAlamatHantar(String alamatHantar) {
        this.alamatHantar = alamatHantar;
    }

    public String getPenerimaNama() {
        return penerimaNama;
    }

    public void setPenerimaNama(String penerimaNama) {
        this.penerimaNama = penerimaNama;
    }

    public String getPenerimaNokp() {
        return penerimaNokp;
    }

    public void setPenerimaNokp(String penerimaNokp) {
        this.penerimaNokp = penerimaNokp;
    }

    public Long getIdBorangPerPermohonan() {
        return idBorangPerPermohonan;
    }

    public void setIdBorangPerPermohonan(Long idBorangPerPermohonan) {
        this.idBorangPerPermohonan = idBorangPerPermohonan;
    }

    public String getNamaPenghantar() {
        return namaPenghantar;
    }

    public void setNamaPenghantar(String namaPenghantar) {
        this.namaPenghantar = namaPenghantar;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }
    
    
}
