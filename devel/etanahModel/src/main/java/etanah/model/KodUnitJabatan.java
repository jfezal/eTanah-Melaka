/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "kod_jab_unit")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodUnitJabatan implements Serializable {

    @Id
    @Column(name = "id", length = 4)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_jab")
    private KodJabatan jabatan;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_unit")
    private KodUnit kodUnit;
    
    @Column(name = "ptg")
    private char ptg;
    
    @Embedded
    InfoAudit infoAudit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KodJabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(KodJabatan jabatan) {
        this.jabatan = jabatan;
    }

    public KodUnit getKodUnit() {
        return kodUnit;
    }

    public void setKodUnit(KodUnit kodUnit) {
        this.kodUnit = kodUnit;
    }

    public char getPtg() {
        return ptg;
    }

    public void setPtg(char ptg) {
        this.ptg = ptg;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
