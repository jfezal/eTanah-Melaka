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
@Table(name = "REF_HELPDESK_TECHNICAL_STATUS")
public class RefHelpdeskTechnicalStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HELPDESK_TECHNICAL_TYPE_ID")
    private Long helpdeskTechnicalTypeId;
    @Column(name = "HELPDESK_TECHNICAL_TYPE")
    private String helpdeskTechnicalType;
    

    public RefHelpdeskTechnicalStatus() {
    }

    public RefHelpdeskTechnicalStatus(Long helpdeskTechnicalTypeId) {
        this.helpdeskTechnicalTypeId = helpdeskTechnicalTypeId;
    }

    public Long getHelpdeskTechnicalTypeId() {
        return helpdeskTechnicalTypeId;
    }

    public void setHelpdeskTechnicalTypeId(Long helpdeskTechnicalTypeId) {
        this.helpdeskTechnicalTypeId = helpdeskTechnicalTypeId;
    }

    public String getHelpdeskTechnicalType() {
        return helpdeskTechnicalType;
    }

    public void setHelpdeskTechnicalType(String helpdeskTechnicalType) {
        this.helpdeskTechnicalType = helpdeskTechnicalType;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (helpdeskTechnicalTypeId != null ? helpdeskTechnicalTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHelpdeskTechnicalStatus)) {
            return false;
        }
        RefHelpdeskTechnicalStatus other = (RefHelpdeskTechnicalStatus) object;
        if ((this.helpdeskTechnicalTypeId == null && other.helpdeskTechnicalTypeId != null) || (this.helpdeskTechnicalTypeId != null && !this.helpdeskTechnicalTypeId.equals(other.helpdeskTechnicalTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHelpdeskTechnicalStatus[ helpdeskTechnicalTypeId=" + helpdeskTechnicalTypeId + " ]";
    }
    
}
