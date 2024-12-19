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
@Table(name = "REF_HELPDESK_HISTORY_TYPE")
public class RefHelpdeskHistoryType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HELPDESK_HISTORY_TYPE_ID")
    private Long helpdeskHistoryTypeId;
    @Column(name = "HELPDESK_HISTORY_TYPE")
    private String helpdeskHistoryType;

    public RefHelpdeskHistoryType() {
    }

    public RefHelpdeskHistoryType(Long helpdeskHistoryTypeId) {
        this.helpdeskHistoryTypeId = helpdeskHistoryTypeId;
    }

    public Long getHelpdeskHistoryTypeId() {
        return helpdeskHistoryTypeId;
    }

    public void setHelpdeskHistoryTypeId(Long helpdeskHistoryTypeId) {
        this.helpdeskHistoryTypeId = helpdeskHistoryTypeId;
    }

    public String getHelpdeskHistoryType() {
        return helpdeskHistoryType;
    }

    public void setHelpdeskHistoryType(String helpdeskHistoryType) {
        this.helpdeskHistoryType = helpdeskHistoryType;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (helpdeskHistoryTypeId != null ? helpdeskHistoryTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHelpdeskHistoryType)) {
            return false;
        }
        RefHelpdeskHistoryType other = (RefHelpdeskHistoryType) object;
        if ((this.helpdeskHistoryTypeId == null && other.helpdeskHistoryTypeId != null) || (this.helpdeskHistoryTypeId != null && !this.helpdeskHistoryTypeId.equals(other.helpdeskHistoryTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHelpdeskHistoryType[ helpdeskHistoryTypeId=" + helpdeskHistoryTypeId + " ]";
    }
    
}
