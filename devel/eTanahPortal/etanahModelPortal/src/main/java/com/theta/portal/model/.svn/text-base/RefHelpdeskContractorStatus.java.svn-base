/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "REF_HELPDESK_CONTRACTOR_STATUS")
public class RefHelpdeskContractorStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HELPDESK_CONTRACTOR_TYPE_ID")
    private Long helpdeskContractorTypeId;
    @Column(name = "HELPDESK_CONTRACTOR_TYPE")
    private String helpdeskContractorType;
   
    public RefHelpdeskContractorStatus() {
    }

    public RefHelpdeskContractorStatus(Long helpdeskContractorTypeId) {
        this.helpdeskContractorTypeId = helpdeskContractorTypeId;
    }

    public Long getHelpdeskContractorTypeId() {
        return helpdeskContractorTypeId;
    }

    public void setHelpdeskContractorTypeId(Long helpdeskContractorTypeId) {
        this.helpdeskContractorTypeId = helpdeskContractorTypeId;
    }

    public String getHelpdeskContractorType() {
        return helpdeskContractorType;
    }

    public void setHelpdeskContractorType(String helpdeskContractorType) {
        this.helpdeskContractorType = helpdeskContractorType;
    }

       @Override
    public int hashCode() {
        int hash = 0;
        hash += (helpdeskContractorTypeId != null ? helpdeskContractorTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHelpdeskContractorStatus)) {
            return false;
        }
        RefHelpdeskContractorStatus other = (RefHelpdeskContractorStatus) object;
        if ((this.helpdeskContractorTypeId == null && other.helpdeskContractorTypeId != null) || (this.helpdeskContractorTypeId != null && !this.helpdeskContractorTypeId.equals(other.helpdeskContractorTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHelpdeskContractorStatus[ helpdeskContractorTypeId=" + helpdeskContractorTypeId + " ]";
    }
    
}
