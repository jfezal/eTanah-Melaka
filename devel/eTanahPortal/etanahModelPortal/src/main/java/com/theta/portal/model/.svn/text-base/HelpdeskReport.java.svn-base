/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "HELPDESK_REPORT")
@SequenceGenerator(name = "SEQ_HELPDESK_REPORT_ID", sequenceName = "SEQ_HELPDESK_REPORT_ID")

public class HelpdeskReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HELPDESK_REPORT_ID")
    @Column(name = "REPORT_ID")
    private Long reportId;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "MODFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modfiedDate;
    @Column(name = "DATE_REPORT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReport;
    @Column(name = "DATE_CLOSED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClosed;
    @Column(name = "NOTE")
    private String note;
//    @Column(name = "MODUL_REPORT")
//    private Long modulReport;
     @JoinColumn(name = "MODUL_REPORT", referencedColumnName = "SUBMODUL_TYPE_ID")
    @ManyToOne
     private RefSubmodulType modulType;
//    @Column(name = "ITEM_REPORT")
//    private Long itemReport;
        @JoinColumn(name = "ITEM_REPORT", referencedColumnName = "ITEM_TYPE_ID")
    @ManyToOne
    private RefItemType itemType;
    @Column(name = "TITLE")
    private String title;
    @JoinColumn(name = "HARDWARE_TYPE", referencedColumnName = "HARDWARE_TYPE_ID")
    @ManyToOne
    private RefHardwareType hardwareType;
    
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @Column(name = "DATE_EDITABLE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEditable;
    @Column(name = "DESC_EDITABLE")
    private String descEditable;
    @JoinColumn(name = "EMP_ID", referencedColumnName = "EMPLOYEE_ID")
    @ManyToOne
    private Employee empId;
    @JoinColumn(name = "FLAG", referencedColumnName = "HELPDESK_FLAG_ID")
    @ManyToOne
    private RefHelpdeskFlag flag;
    @JoinColumn(name = "STATUS", referencedColumnName = "HELPDESK_REPORT_STATUS_TYPE_ID")
    @ManyToOne
    private RefHelpdeskReportStatus status;
    @JoinColumn(name = "TYPE_REPORT", referencedColumnName = "HELPDESK_TYPE_ID")
    @ManyToOne
    private RefHelpdeskType typeReport;
    @JoinColumn(name = "CLOSED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable closedBy;
    @JoinColumn(name = "MODIFIED_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable modifiedBy;
    @JoinColumn(name = "REPORT_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable reportBy;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable createBy;
    
    @OneToMany (mappedBy = "reportId")
	private List<HelpdeskTechnical> senaraiHelpdeskTeknical;
    

    public HelpdeskReport() {
    }

    public HelpdeskReport(Long reportId) {
        this.reportId = reportId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModfiedDate() {
        return modfiedDate;
    }

    public void setModfiedDate(Date modfiedDate) {
        this.modfiedDate = modfiedDate;
    }

    public Date getDateReport() {
        return dateReport;
    }

    public void setDateReport(Date dateReport) {
        this.dateReport = dateReport;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RefHardwareType getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(RefHardwareType hardwareType) {
        this.hardwareType = hardwareType;
    }

   

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getDateEditable() {
        return dateEditable;
    }

    public void setDateEditable(Date dateEditable) {
        this.dateEditable = dateEditable;
    }

    public String getDescEditable() {
        return descEditable;
    }

    public void setDescEditable(String descEditable) {
        this.descEditable = descEditable;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

    public RefHelpdeskFlag getFlag() {
        return flag;
    }

    public void setFlag(RefHelpdeskFlag flag) {
        this.flag = flag;
    }

    public RefHelpdeskReportStatus getStatus() {
        return status;
    }

    public void setStatus(RefHelpdeskReportStatus status) {
        this.status = status;
    }

    public RefHelpdeskType getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(RefHelpdeskType typeReport) {
        this.typeReport = typeReport;
    }

    public UserTable getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(UserTable closedBy) {
        this.closedBy = closedBy;
    }

    public UserTable getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserTable modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public UserTable getReportBy() {
        return reportBy;
    }

    public void setReportBy(UserTable reportBy) {
        this.reportBy = reportBy;
    }

    public UserTable getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserTable createBy) {
        this.createBy = createBy;
    }

    public RefSubmodulType getModulType() {
        return modulType;
    }

    public void setModulType(RefSubmodulType modulType) {
        this.modulType = modulType;
    }

    public RefItemType getItemType() {
        return itemType;
    }

    public void setItemType(RefItemType itemType) {
        this.itemType = itemType;
    }

    public List<HelpdeskTechnical> getSenaraiHelpdeskTeknical() {
        return senaraiHelpdeskTeknical;
    }

    public void setSenaraiHelpdeskTeknical(List<HelpdeskTechnical> senaraiHelpdeskTeknical) {
        this.senaraiHelpdeskTeknical = senaraiHelpdeskTeknical;
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
        if (!(object instanceof HelpdeskReport)) {
            return false;
        }
        HelpdeskReport other = (HelpdeskReport) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskReport[ reportId=" + reportId + " ]";
    }
    
}
