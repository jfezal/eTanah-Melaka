/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
 * @author john
 */
@Entity
@Table(name = "mohon_semak_pelan_hakmilik")
@SequenceGenerator(name = "seq_mohon_semak_pelan_hakmilik", sequenceName = "seq_mohon_semak_pelan_hakmilik")
public class PermohonanSemakPelanHakmilik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_semak_pelan_hakmilik")
    @Column(name = "id_msp_hakmilik")
    private long idMspHakmilik;

    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @Column(name = "no_pelan_pa")
    private String noPelanPA;

    @Column(name = "tarikh_sah")
    private Date tarikhSah;

    @Column(name = "luas")
    private BigDecimal luas;

    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUom;
    @Embedded
    private InfoAudit infoAudit;
@ManyToOne
    @JoinColumn(name = "id_msp")
    private PermohonanSemakPelan mohonSemakPelan;

    public long getIdMspHakmilik() {
        return idMspHakmilik;
    }

    public void setIdMspHakmilik(long idMspHakmilik) {
        this.idMspHakmilik = idMspHakmilik;
    }

    public PermohonanSemakPelan getMohonSemakPelan() {
        return mohonSemakPelan;
    }

    public void setMohonSemakPelan(PermohonanSemakPelan mohonSemakPelan) {
        this.mohonSemakPelan = mohonSemakPelan;
    }
   

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getNoPelanPA() {
        return noPelanPA;
    }

    public void setNoPelanPA(String noPelanPA) {
        this.noPelanPA = noPelanPA;
    }

    public Date getTarikhSah() {
        return tarikhSah;
    }

    public void setTarikhSah(Date tarikhSah) {
        this.tarikhSah = tarikhSah;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public KodUOM getKodUom() {
        return kodUom;
    }

    public void setKodUom(KodUOM kodUom) {
        this.kodUom = kodUom;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
