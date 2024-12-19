/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.strata;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author !?
 */
@Entity
@Table(name = "hakmilik_strata_tukarganti")
@SequenceGenerator(name = "SEQ_HAKMILIK_STRATA_TUKARGANTI", sequenceName = "SEQ_HAKMILIK_STRATA_TUKARGANTI")
public class HakmilikTukarGantiStrata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HAKMILIK_STRATA_TUKARGANTI")
    @Column(name = "id_versi")
    private Long idVersi;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hakmilik_induk")   
    private Hakmilik hakmilikInduk;    
    
    @Column(name = "id_hakmilik_strata")
    private String hakmilikStrata;
    
    @Column(name = "tkh_tganti_4k")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhTukarganti4k;
    
    @Column(name = "tkh_tganti_2k")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhTukarganti2k;
    
    @Column(name = "tkh_cetak_4k")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhCetak4k;
    
    @Column(name = "tkh_cetak_2k")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhCetak2k;
    
    @Column(name = "versi_4k")
    private Integer versi4k;
    
    @Column(name = "versi_2k")
    private Integer versi2k;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdVersi() {
        return idVersi;
    }

    public void setIdVersi(Long idVersi) {
        this.idVersi = idVersi;
    }

    public Hakmilik getHakmilikInduk() {
        return hakmilikInduk;
    }

    public void setHakmilikInduk(Hakmilik hakmilikInduk) {
        this.hakmilikInduk = hakmilikInduk;
    }

    public String getHakmilikStrata() {
        return hakmilikStrata;
    }

    public void setHakmilikStrata(String hakmilikStrata) {
        this.hakmilikStrata = hakmilikStrata;
    }

    public Date getTarikhTukarganti4k() {
        return tarikhTukarganti4k;
    }

    public void setTarikhTukarganti4k(Date tarikhTukarganti4k) {
        this.tarikhTukarganti4k = tarikhTukarganti4k;
    }

    public Date getTarikhTukarganti2k() {
        return tarikhTukarganti2k;
    }

    public void setTarikhTukarganti2k(Date tarikhTukarganti2k) {
        this.tarikhTukarganti2k = tarikhTukarganti2k;
    }

    public Date getTarikhCetak4k() {
        return tarikhCetak4k;
    }

    public void setTarikhCetak4k(Date tarikhCetak4k) {
        this.tarikhCetak4k = tarikhCetak4k;
    }

    public Date getTarikhCetak2k() {
        return tarikhCetak2k;
    }

    public void setTarikhCetak2k(Date tarikhCetak2k) {
        this.tarikhCetak2k = tarikhCetak2k;
    }

    public Integer getVersi4k() {
        return versi4k;
    }

    public void setVersi4k(Integer versi4k) {
        this.versi4k = versi4k;
    }

    public Integer getVersi2k() {
        return versi2k;
    }

    public void setVersi2k(Integer versi2k) {
        this.versi2k = versi2k;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


}
