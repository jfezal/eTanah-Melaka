/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Immutable
@Table(name = "V_HAKMILIK_HASIL")
public class HakmilikHasil implements Serializable {

    @Id
    @Column(name = "id_hakmilik")
    private String idHakmilik;
    @Column(name = "kod_caw")
    private String kodCaw;
    @Column(name = "kod_daerah")
    private String kodDaerah;
    @Column(name = "kod_bpm")
    private String kodBPM;
    @Column(name = "bpm_nama")
    private String bpmNama;
    @Column(name = "kod_katg_tanah")
    private String kodKatgTanah;
    @Column(name = "katg_tanah")
    private String katgTanah;
    @Column(name = "trh_daftar")
    private Date trhDaftar;
    @Column(name = "jum_pemilik")
    private Integer jumPemilik;
    @Column(name = "senarai_pemilik")
    private String senaraiPemilik;

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
