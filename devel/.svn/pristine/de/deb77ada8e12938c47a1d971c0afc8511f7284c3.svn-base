/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "HELPDESK_STAGE")
public class HelpdeskStage implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "REPORT_ID")
    private Long reportId;
    @Column(name = "PARAM_VALUE")
    private String paramValue;
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private HelpdeskReport helpdeskReport;
    @JoinColumn(name = "KOD_STAGE", referencedColumnName = "KOD")
    @ManyToOne
    private RefHelpdeskStage kodStage;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable userId;
        @JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable createdBy;
        @OneToMany(mappedBy = "refHelpdeskStage")
    private List<RefHelpdeskStageRole> stageRoleList;
    

    public HelpdeskStage() {
    }

    public HelpdeskStage(Long reportId) {
        this.reportId = reportId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public HelpdeskReport getHelpdeskReport() {
        return helpdeskReport;
    }

    public void setHelpdeskReport(HelpdeskReport helpdeskReport) {
        this.helpdeskReport = helpdeskReport;
    }

    public RefHelpdeskStage getKodStage() {
        return kodStage;
    }

    public void setKodStage(RefHelpdeskStage kodStage) {
        this.kodStage = kodStage;
    }

    public UserTable getUserId() {
        return userId;
    }

    public void setUserId(UserTable userId) {
        this.userId = userId;
    }

    public UserTable getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserTable createdBy) {
        this.createdBy = createdBy;
    }
    
    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelpdeskStage)) {
            return false;
        }
        HelpdeskStage other = (HelpdeskStage) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskStage[ reportId=" + reportId + " ]";
    }
    
}
