/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view;

/**
 *
 * @author mohd.faidzal
 */
public class MaklumatSek4Form {
    String idPermohonan = "Tiada Maklumat";
    String urusan= "Tiada Maklumat";
    String tarikhWarta= "Tiada Maklumat";
    String bilHakmilik= "Tiada Maklumat";
    String bilHakmilikTDK= "Tiada Maklumat";
    String senaraiHakmilik= "Tiada Maklumat";
    String jumLuasAmbil= "Tiada Maklumat";
    String kodLuasAmbil= "Tiada Maklumat";
    String tujuanPengambilan= "Tiada Maklumat";
    boolean expiredWarta = Boolean.FALSE;
    boolean invalidId = Boolean.FALSE;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getBilHakmilik() {
        return bilHakmilik;
    }

    public void setBilHakmilik(String bilHakmilik) {
        this.bilHakmilik = bilHakmilik;
    }

    public String getBilHakmilikTDK() {
        return bilHakmilikTDK;
    }

    public void setBilHakmilikTDK(String bilHakmilikTDK) {
        this.bilHakmilikTDK = bilHakmilikTDK;
    }

    public String getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(String senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getJumLuasAmbil() {
        return jumLuasAmbil;
    }

    public void setJumLuasAmbil(String jumLuasAmbil) {
        this.jumLuasAmbil = jumLuasAmbil;
    }

    public String getKodLuasAmbil() {
        return kodLuasAmbil;
    }

    public void setKodLuasAmbil(String kodLuasAmbil) {
        this.kodLuasAmbil = kodLuasAmbil;
    }

    public boolean isExpiredWarta() {
        return expiredWarta;
    }

    public void setExpiredWarta(boolean expiredWarta) {
        this.expiredWarta = expiredWarta;
    }

    public String getTujuanPengambilan() {
        return tujuanPengambilan;
    }

    public void setTujuanPengambilan(String tujuanPengambilan) {
        this.tujuanPengambilan = tujuanPengambilan;
    }

    public boolean isInvalidId() {
        return invalidId;
    }

    public void setInvalidId(boolean invalidId) {
        this.invalidId = invalidId;
    }
    
    
    
    
}
