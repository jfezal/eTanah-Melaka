/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "REF_HELPDESK_FLAG")
public class RefHelpdeskFlag implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "HELPDESK_FLAG_ID")
    private BigDecimal helpdeskFlagId;
    @Column(name = "HELPDESK_FLAG_NAME")
    private String helpdeskFlagName;
    
    public RefHelpdeskFlag() {
    }

    public RefHelpdeskFlag(BigDecimal helpdeskFlagId) {
        this.helpdeskFlagId = helpdeskFlagId;
    }

    public BigDecimal getHelpdeskFlagId() {
        return helpdeskFlagId;
    }

    public void setHelpdeskFlagId(BigDecimal helpdeskFlagId) {
        this.helpdeskFlagId = helpdeskFlagId;
    }

    public String getHelpdeskFlagName() {
        return helpdeskFlagName;
    }

    public void setHelpdeskFlagName(String helpdeskFlagName) {
        this.helpdeskFlagName = helpdeskFlagName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (helpdeskFlagId != null ? helpdeskFlagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHelpdeskFlag)) {
            return false;
        }
        RefHelpdeskFlag other = (RefHelpdeskFlag) object;
        if ((this.helpdeskFlagId == null && other.helpdeskFlagId != null) || (this.helpdeskFlagId != null && !this.helpdeskFlagId.equals(other.helpdeskFlagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHelpdeskFlag[ helpdeskFlagId=" + helpdeskFlagId + " ]";
    }
    
}
