/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "mohon_semak_pelan")
//@SequenceGenerator(name = "seq_mohon_semak_pelan", sequenceName = "seq_mohon_semak_pelan")

public class PermohonanSemakPelan implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_semak_pelan")
    @Column(name = "id_msp")
    private long idMsp;
    
    @Column(name = "jum_luas")
    private BigDecimal jumLuas;
    
    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUom;
    
     @ManyToOne
    @JoinColumn(name = "id_pihak")
    private Pihak pihak;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdMsp() {
        return idMsp;
    }

    public void setIdMsp(long idMsp) {
        this.idMsp = idMsp;
    }

    public BigDecimal getJumLuas() {
        return jumLuas;
    }

    public void setJumLuas(BigDecimal jumLuas) {
        this.jumLuas = jumLuas;
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

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }
    
    
    
}
