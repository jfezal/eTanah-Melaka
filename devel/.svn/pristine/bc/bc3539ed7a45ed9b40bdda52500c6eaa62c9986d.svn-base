/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "kod_trans_speks")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodTransIspeks implements Serializable {

    @Id
    @Column(name = "ID_SP", length = 8)
    private String id;
    
    @Column(name = "kod", length = 8)
    private String kod;
    @Column(name = "PERIHAL", length = 8)
    private String perihal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KOD_KEW_TRANS")
    private KodTransaksi kodTransaksi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOT_DANA")
    private KodVotDana kodVotDana;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public KodVotDana getKodVotDana() {
        return kodVotDana;
    }

    public void setKodVotDana(KodVotDana kodVotDana) {
        this.kodVotDana = kodVotDana;
    }
    
    
    }
