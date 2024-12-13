/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.jompay;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import java.io.Serializable;
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

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "JOMPAY_FAILED_RECORD")
@SequenceGenerator(name = "SEQ_JOMPAY_FAILED_RECORD", sequenceName = "SEQ_JOMPAY_FAILED_RECORD")
public class JomPayFailedRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_JOMPAY_FAILED_RECORD")
    @Column(name = "ID")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_JOMPAYDETAILS")
    JomPayFailDetails jomPayFailDetails;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hakmilik")
    Hakmilik hakmilik;
    
    @Column(name = "STATUS")
    String status;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JomPayFailDetails getJomPayFailDetails() {
        return jomPayFailDetails;
    }

    public void setJomPayFailDetails(JomPayFailDetails jomPayFailDetails) {
        this.jomPayFailDetails = jomPayFailDetails;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
