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
@Table(name = "REF_HELPDESK_REPORT_STATUS")
public class RefHelpdeskReportStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HELPDESK_REPORT_STATUS_TYPE_ID")
    private Long helpdeskReportStatusTypeId;
    @Column(name = "HELPDESK_REPORT_STATUS_TYPE")
    private String helpdeskReportStatusType;
    
    public RefHelpdeskReportStatus() {
    }

    public RefHelpdeskReportStatus(Long helpdeskReportStatusTypeId) {
        this.helpdeskReportStatusTypeId = helpdeskReportStatusTypeId;
    }

    public Long getHelpdeskReportStatusTypeId() {
        return helpdeskReportStatusTypeId;
    }

    public void setHelpdeskReportStatusTypeId(Long helpdeskReportStatusTypeId) {
        this.helpdeskReportStatusTypeId = helpdeskReportStatusTypeId;
    }

    public String getHelpdeskReportStatusType() {
        return helpdeskReportStatusType;
    }

    public void setHelpdeskReportStatusType(String helpdeskReportStatusType) {
        this.helpdeskReportStatusType = helpdeskReportStatusType;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (helpdeskReportStatusTypeId != null ? helpdeskReportStatusTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHelpdeskReportStatus)) {
            return false;
        }
        RefHelpdeskReportStatus other = (RefHelpdeskReportStatus) object;
        if ((this.helpdeskReportStatusTypeId == null && other.helpdeskReportStatusTypeId != null) || (this.helpdeskReportStatusTypeId != null && !this.helpdeskReportStatusTypeId.equals(other.helpdeskReportStatusTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHelpdeskReportStatus[ helpdeskReportStatusTypeId=" + helpdeskReportStatusTypeId + " ]";
    }
    
}
