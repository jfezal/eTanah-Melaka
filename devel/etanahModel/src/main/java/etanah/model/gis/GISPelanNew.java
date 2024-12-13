/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model.gis;

import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author zuraida
 */
@Entity
@Table (name = "gis_pelan")
public class GISPelanNew implements Serializable{
     @Id
     @Column(name = "pelan_tif", length = 30)
    private String pelanTif; 
     
     @ManyToOne
    @JoinColumn (name = "kod_negeri")
    private KodNegeri kodNegeri;

    @ManyToOne
    @JoinColumn (name = "kod_daerah")
    private KodDaerah kodDaerah;
    
    @Column (name="id_permohonan")
    private String permohonan;

    @Column (name = "kod_mukim")
    private String kodMukim;

    @Column (name = "kod_seksyen")
    private String kodSeksyen;

        @Column(name = "jenis_pelan", length = 2)
    private String jenisPelan;
    
        @Column (name = "no_lot")
    private String noLot;

   



    @Column(name = "NO_PT")
    private String noPt;
    
    @Column(name = "NO_PA")
    private String noPelanAkui;
    
    @Column(name = "unit")
    private String unitUkur;
    
    @Column(name = "luas")
    private BigDecimal luas;
    @Column(name = "no_fail_ukur")
    private String noFailUkur;
    
    @Column(name = "TKH_KEMASKINI")
    private Date trhKemaskini; 

    public String getPelanTif() {
        return pelanTif;
    }

    public void setPelanTif(String pelanTif) {
        this.pelanTif = pelanTif;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public KodDaerah getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(KodDaerah kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(String permohonan) {
        this.permohonan = permohonan;
    }

    public String getKodMukim() {
        return kodMukim;
    }

    public void setKodMukim(String kodMukim) {
        this.kodMukim = kodMukim;
    }

    public String getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(String kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public String getJenisPelan() {
        return jenisPelan;
    }

    public void setJenisPelan(String jenisPelan) {
        this.jenisPelan = jenisPelan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoPt() {
        return noPt;
    }

    public void setNoPt(String noPt) {
        this.noPt = noPt;
    }

    public String getNoPelanAkui() {
        return noPelanAkui;
    }

    public void setNoPelanAkui(String noPelanAkui) {
        this.noPelanAkui = noPelanAkui;
    }

    public String getUnitUkur() {
        return unitUkur;
    }

    public void setUnitUkur(String unitUkur) {
        this.unitUkur = unitUkur;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public String getNoFailUkur() {
        return noFailUkur;
    }

    public void setNoFailUkur(String noFailUkur) {
        this.noFailUkur = noFailUkur;
    }

    public Date getTrhKemaskini() {
        return trhKemaskini;
    }

    public void setTrhKemaskini(Date trhKemaskini) {
        this.trhKemaskini = trhKemaskini;
    }
    
    
    
}
