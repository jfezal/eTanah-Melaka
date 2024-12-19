/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "HELPDESK_CONTRACTOR")
@SequenceGenerator(name = "SEQ_HELPDESK_CONTRACTOR_ID", sequenceName = "SEQ_HELPDESK_CONTRACTOR_ID")

public class HelpdeskContractor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HELPDESK_CONTRACTOR_ID")
    @Column(name = "HELPDESK_CONTRACTOR_ID")
    private Long helpdeskContractorId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ASSIGN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignDate;
    @Column(name = "CLOSED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;
    @Column(name = "CONTRACTOR_ID")
    private Long contractorId;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private HelpdeskReport reportId;
    @JoinColumn(name = "TECHNICAL_ID", referencedColumnName = "TECHNICAL_ID")
    @ManyToOne
    private HelpdeskTechnical technicalId;
    @JoinColumn(name = "STATUS", referencedColumnName = "HELPDESK_CONTRACTOR_TYPE_ID")
    @ManyToOne
    private RefHelpdeskContractorStatus status;
    @JoinColumn(name = "CONTRACTOR_OFFICER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable contractorUserId;

    public HelpdeskContractor() {
    }

    public HelpdeskContractor(Long helpdeskContractorId) {
        this.helpdeskContractorId = helpdeskContractorId;
    }

    public Long getHelpdeskContractorId() {
        return helpdeskContractorId;
    }

    public void setHelpdeskContractorId(Long helpdeskContractorId) {
        this.helpdeskContractorId = helpdeskContractorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Long getContractorId() {
        return contractorId;
    }

    public void setContractorId(Long contractorId) {
        this.contractorId = contractorId;
    }

    public HelpdeskReport getReportId() {
        return reportId;
    }

    public void setReportId(HelpdeskReport reportId) {
        this.reportId = reportId;
    }

    public HelpdeskTechnical getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(HelpdeskTechnical technicalId) {
        this.technicalId = technicalId;
    }

    public RefHelpdeskContractorStatus getStatus() {
        return status;
    }

    public void setStatus(RefHelpdeskContractorStatus status) {
        this.status = status;
    }

    public UserTable getContractorUserId() {
        return contractorUserId;
    }

    public void setContractorUserId(UserTable contractorUserId) {
        this.contractorUserId = contractorUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (helpdeskContractorId != null ? helpdeskContractorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelpdeskContractor)) {
            return false;
        }
        HelpdeskContractor other = (HelpdeskContractor) object;
        if ((this.helpdeskContractorId == null && other.helpdeskContractorId != null) || (this.helpdeskContractorId != null && !this.helpdeskContractorId.equals(other.helpdeskContractorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskContractor[ helpdeskContractorId=" + helpdeskContractorId + " ]";
    }
    
}
