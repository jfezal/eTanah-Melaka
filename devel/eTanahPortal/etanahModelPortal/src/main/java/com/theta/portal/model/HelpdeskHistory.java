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
@Table(name = "HELPDESK_HISTORY")
@SequenceGenerator(name = "SEQ_HELPDESK_HISTORY_ID", sequenceName = "SEQ_HELPDESK_HISTORY_ID")

public class HelpdeskHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HELPDESK_HISTORY_ID")    
    @Column(name = "HISTORY_ID")
    private Long historyId;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "REF_ID")
    private Long refId;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "REPORT_ID", referencedColumnName = "REPORT_ID")
    @ManyToOne
    private HelpdeskReport reportId;
    @JoinColumn(name = "HISTORY_TYPE", referencedColumnName = "HELPDESK_HISTORY_TYPE_ID")
    @ManyToOne
    private RefHelpdeskHistoryType historyType;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable createBy;

    public HelpdeskHistory() {
    }

    public HelpdeskHistory(Long historyId) {
        this.historyId = historyId;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HelpdeskReport getReportId() {
        return reportId;
    }

    public void setReportId(HelpdeskReport reportId) {
        this.reportId = reportId;
    }

    public RefHelpdeskHistoryType getHistoryType() {
        return historyType;
    }

    public void setHistoryType(RefHelpdeskHistoryType historyType) {
        this.historyType = historyType;
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
        hash += (historyId != null ? historyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelpdeskHistory)) {
            return false;
        }
        HelpdeskHistory other = (HelpdeskHistory) object;
        if ((this.historyId == null && other.historyId != null) || (this.historyId != null && !this.historyId.equals(other.historyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskHistory[ historyId=" + historyId + " ]";
    }
    
}
