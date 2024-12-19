/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "HELPDESK_TECHNICAL")
@SequenceGenerator(name = "SEQ_HELPDESK_TECHNICAL_ID", sequenceName = "SEQ_HELPDESK_TECHNICAL_ID")

public class HelpdeskTechnical implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HELPDESK_TECHNICAL_ID")

    @Basic(optional = false)
    @Column(name = "TECHNICAL_ID")
    private Long technicalId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CONTRACTOR")
    private String contractor;
    @Column(name = "ASSIGN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignDate;
    @Column(name = "CLOSED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closedDate;
    @Column(name = "CONTRACTOR_ID")
    private Long contractorId;
    @Column(name = "MODFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modfiedDate;
    @Column(name = "MODIFIED_BY")
    private BigInteger modifiedBy;
    @Column(name = "DESC_HDASSIGN")
    private String descHdassign;

    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private HelpdeskReport reportId;
    @JoinColumn(name = "FLAG_ID", referencedColumnName = "HELPDESK_FLAG_ID")
    @ManyToOne
    private RefHelpdeskFlag flagId;
    @JoinColumn(name = "STATUS", referencedColumnName = "HELPDESK_TECHNICAL_TYPE_ID")
    @ManyToOne
    private RefHelpdeskTechnicalStatus status;
    @JoinColumn(name = "TECHNICAL_OFFICER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable technicalOfficerId;

    public HelpdeskTechnical() {
    }

    public HelpdeskTechnical(Long technicalId) {
        this.technicalId = technicalId;
    }

    public Long getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(Long technicalId) {
        this.technicalId = technicalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
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

    public Date getModfiedDate() {
        return modfiedDate;
    }

    public void setModfiedDate(Date modfiedDate) {
        this.modfiedDate = modfiedDate;
    }

    public BigInteger getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BigInteger modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getDescHdassign() {
        return descHdassign;
    }

    public void setDescHdassign(String descHdassign) {
        this.descHdassign = descHdassign;
    }

    public HelpdeskReport getReportId() {
        return reportId;
    }

    public void setReportId(HelpdeskReport reportId) {
        this.reportId = reportId;
    }

    public RefHelpdeskFlag getFlagId() {
        return flagId;
    }

    public void setFlagId(RefHelpdeskFlag flagId) {
        this.flagId = flagId;
    }

    public RefHelpdeskTechnicalStatus getStatus() {
        return status;
    }

    public void setStatus(RefHelpdeskTechnicalStatus status) {
        this.status = status;
    }

    public UserTable getTechnicalOfficerId() {
        return technicalOfficerId;
    }

    public void setTechnicalOfficerId(UserTable technicalOfficerId) {
        this.technicalOfficerId = technicalOfficerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (technicalId != null ? technicalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelpdeskTechnical)) {
            return false;
        }
        HelpdeskTechnical other = (HelpdeskTechnical) object;
        if ((this.technicalId == null && other.technicalId != null) || (this.technicalId != null && !this.technicalId.equals(other.technicalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskTechnical[ technicalId=" + technicalId + " ]";
    }

}
