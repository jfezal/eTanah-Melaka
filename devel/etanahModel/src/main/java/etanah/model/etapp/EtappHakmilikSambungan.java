/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.KodUOM;
import etanah.model.Permohonan;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "ETAPP_HAKMILIK_SAMBUNGAN")
@SequenceGenerator(name = "seq_etapp_hakmilik_sambungan", sequenceName = "seq_etapp_hakmilik_sambungan")
public class EtappHakmilikSambungan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_etapp_hakmilik_sambungan")
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_HAKMILIK", nullable = false)
    private Hakmilik hakmilik;
    @Column(name = "NO_LOT")
    String noLot;

    @Column(name = "NO_PELAN")
    String noPelan;
    @Column(name = "LUAS")
    BigDecimal luas;
    @ManyToOne
    @JoinColumn(name = "KOD_LUAS")
    KodUOM kodLuas;
    @Column(name = "PELAN")
    String pelan;
    @ManyToOne
    @JoinColumn(name = "ID_MOHON")
    Permohonan permohonan;
    @Column(name = "STATUS")
    String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoPelan() {
        return noPelan;
    }

    public void setNoPelan(String noPelan) {
        this.noPelan = noPelan;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public KodUOM getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(KodUOM kodLuas) {
        this.kodLuas = kodLuas;
    }

    public String getPelan() {
        return pelan;
    }

    public void setPelan(String pelan) {
        this.pelan = pelan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
