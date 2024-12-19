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
@Table(name = "HELPDESK_ASSIGN")
@SequenceGenerator(name = "SEQ_HELPDESK_ASSIGN_ID", sequenceName = "SEQ_HELPDESK_ASSIGN_ID")

public class HelpdeskAssign implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HELPDESK_ASSIGN_ID")
    @Column(name = "ASSIGN_ID")
    private Long assignId;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "ASSIGN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignDate;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "CONTRACTOR_ID", referencedColumnName = "HELPDESK_CONTRACTOR_ID")
    @ManyToOne
    private HelpdeskContractor contractorId;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private HelpdeskReport reportId;
    @JoinColumn(name = "TECHNICAL_REPORT_ID", referencedColumnName = "TECHNICAL_ID")
    @ManyToOne
    private HelpdeskTechnical technicalReportId;
    @JoinColumn(name = "FLAG_ID", referencedColumnName = "HELPDESK_FLAG_ID")
    @ManyToOne
    private RefHelpdeskFlag flagId;
    @JoinColumn(name = "TECHNICAL_STATUS_ID", referencedColumnName = "HELPDESK_TECHNICAL_TYPE_ID")
    @ManyToOne
    private RefHelpdeskTechnicalStatus technicalStatusId;
    @JoinColumn(name = "TECHNICAL_OFFICER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable technicalOfficerId;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable createBy;

    public HelpdeskAssign() {
    }

    public HelpdeskAssign(Long assignId) {
        this.assignId = assignId;
    }

    public Long getAssignId() {
        return assignId;
    }

    public void setAssignId(Long assignId) {
        this.assignId = assignId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HelpdeskContractor getContractorId() {
        return contractorId;
    }

    public void setContractorId(HelpdeskContractor contractorId) {
        this.contractorId = contractorId;
    }

    public HelpdeskReport getReportId() {
        return reportId;
    }

    public void setReportId(HelpdeskReport reportId) {
        this.reportId = reportId;
    }

    public HelpdeskTechnical getTechnicalReportId() {
        return technicalReportId;
    }

    public void setTechnicalReportId(HelpdeskTechnical technicalReportId) {
        this.technicalReportId = technicalReportId;
    }

    public RefHelpdeskFlag getFlagId() {
        return flagId;
    }

    public void setFlagId(RefHelpdeskFlag flagId) {
        this.flagId = flagId;
    }

    public RefHelpdeskTechnicalStatus getTechnicalStatusId() {
        return technicalStatusId;
    }

    public void setTechnicalStatusId(RefHelpdeskTechnicalStatus technicalStatusId) {
        this.technicalStatusId = technicalStatusId;
    }

    public UserTable getTechnicalOfficerId() {
        return technicalOfficerId;
    }

    public void setTechnicalOfficerId(UserTable technicalOfficerId) {
        this.technicalOfficerId = technicalOfficerId;
    }

    public UserTable getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserTable createBy) {
        this.createBy = createBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assignId != null ? assignId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelpdeskAssign)) {
            return false;
        }
        HelpdeskAssign other = (HelpdeskAssign) object;
        if ((this.assignId == null && other.assignId != null) || (this.assignId != null && !this.assignId.equals(other.assignId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskAssign[ assignId=" + assignId + " ]";
    }
    
}
