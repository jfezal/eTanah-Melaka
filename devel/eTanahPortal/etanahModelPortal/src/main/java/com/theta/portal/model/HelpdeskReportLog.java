/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "HELPDESK_REPORT_LOG")
@SequenceGenerator(name = "SEQ_HELPDESK_REPORT_LOG_ID", sequenceName = "SEQ_HELPDESK_REPORT_LOG_ID")

public class HelpdeskReportLog implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HELPDESK_REPORT_LOG_ID")
    @Column(name = "LOG_ID")
    private Long logId;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @OneToOne(optional = false)
    private HelpdeskReport helpdeskReport;
    @JoinColumn(name = "STAGE_ID", referencedColumnName = "KOD")
    @ManyToOne
    private RefHelpdeskStage kodStage;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable userId;

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

    
    public HelpdeskReportLog() {
    }

    public HelpdeskReportLog(Long logId) {
        this.logId = logId;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelpdeskReportLog)) {
            return false;
        }
        HelpdeskReportLog other = (HelpdeskReportLog) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskReportLog[ logId=" + logId + " ]";
    }

}
