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
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "REF_HELPDESK_TYPE")
public class RefHelpdeskType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HELPDESK_TYPE_ID")
    private Long helpdeskTypeId;
    @Column(name = "HELPDESK_TYPE")
    private String helpdeskType;
  
    public RefHelpdeskType() {
    }

    public RefHelpdeskType(Long helpdeskTypeId) {
        this.helpdeskTypeId = helpdeskTypeId;
    }

    public Long getHelpdeskTypeId() {
        return helpdeskTypeId;
    }

    public void setHelpdeskTypeId(Long helpdeskTypeId) {
        this.helpdeskTypeId = helpdeskTypeId;
    }

    public String getHelpdeskType() {
        return helpdeskType;
    }

    public void setHelpdeskType(String helpdeskType) {
        this.helpdeskType = helpdeskType;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (helpdeskTypeId != null ? helpdeskTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHelpdeskType)) {
            return false;
        }
        RefHelpdeskType other = (RefHelpdeskType) object;
        if ((this.helpdeskTypeId == null && other.helpdeskTypeId != null) || (this.helpdeskTypeId != null && !this.helpdeskTypeId.equals(other.helpdeskTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHelpdeskType[ helpdeskTypeId=" + helpdeskTypeId + " ]";
    }
    
}
