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
@Table (name = "kod_unit")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)

public class KodUnit implements Serializable{
     @Id
    @Column(name = "id", length = 4)
    private Integer id;

    @Column(name = "nama")
    private String nama;
    
    @Column(name = "skrin")
    private char skrin;
    
    @Embedded
    InfoAudit infoAudit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public char getSkrin() {
        return skrin;
    }

    public void setSkrin(char skrin) {
        this.skrin = skrin;
    }


    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
