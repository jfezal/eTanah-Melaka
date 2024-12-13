/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import java.util.Date;

/**
 *
 * @author mohd.faidzal
 */
public class SenaraiHakmilikHasilForm {

    private String idHakmilik;
    private String kodBPM;
    private String bpmNama;
    private String kodKatgTanah;
    private String katgTanah;
    private Date trhDaftar;
    private Integer jumPemilik;
    private String senaraiPemilik;
    private String kodCaw;
    private String kodDaerah;

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(String kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getBpmNama() {
        return bpmNama;
    }

    public void setBpmNama(String bpmNama) {
        this.bpmNama = bpmNama;
    }

    public String getKodKatgTanah() {
        return kodKatgTanah;
    }

    public void setKodKatgTanah(String kodKatgTanah) {
        this.kodKatgTanah = kodKatgTanah;
    }

    public String getKatgTanah() {
        return katgTanah;
    }

    public void setKatgTanah(String katgTanah) {
        this.katgTanah = katgTanah;
    }

    public Date getTrhDaftar() {
        return trhDaftar;
    }

    public void setTrhDaftar(Date trhDaftar) {
        this.trhDaftar = trhDaftar;
    }

    public Integer getJumPemilik() {
        return jumPemilik;
    }

    public void setJumPemilik(Integer jumPemilik) {
        this.jumPemilik = jumPemilik;
    }

    public String getSenaraiPemilik() {
        return senaraiPemilik;
    }

    public void setSenaraiPemilik(String senaraiPemilik) {
        this.senaraiPemilik = senaraiPemilik;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }
}
