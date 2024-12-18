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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "EMPLOYEE")
@SequenceGenerator(name = "SEQ_EMPLOYEE_ID", sequenceName = "SEQ_EMPLOYEE_ID")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EMPLOYEE_ID")
    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;
    @Column(name = "EMPLOYEE_STAFF_ID")
    private String employeeStaffId;
    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;
    @Column(name = "EMPLOYEE_PHONE_NO")
    private String employeePhoneNo;
    @Column(name = "EMPLOYEE_ADDRESS")
    private String employeeAddress;
    @Column(name = "EMPLOYEE_ACTIVE_STATUS")
    private String employeeActiveStatus;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "CREATE_BY")
    private BigInteger createBy;
    @Column(name = "MODFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modfiedDate;
    @Column(name = "MODIFIED_BY")
    private BigInteger modifiedBy;
    @JoinColumn(name = "POSITION_ID", referencedColumnName = "POSITION_ID")
    @ManyToOne
    private Position positionId;
    @Column(name = "EMPLOYEE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date employeeDate;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    
    @Column(name = "EMAIL")
    private String email;
    
    @JoinColumn(name = "EMPLOYEE_DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    @ManyToOne
    private Department employeeDepartmentId;
    @JoinColumn(name = "EMPLOYEE_PTD_OFFICE_ID", referencedColumnName = "PTD_OFFICE_ID")
    @ManyToOne
    private PtdOffice employeePtdOfficeId;
    

    public Employee() {
    }

    public Employee(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeStaffId() {
        return employeeStaffId;
    }

    public void setEmployeeStaffId(String employeeStaffId) {
        this.employeeStaffId = employeeStaffId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhoneNo() {
        return employeePhoneNo;
    }

    public void setEmployeePhoneNo(String employeePhoneNo) {
        this.employeePhoneNo = employeePhoneNo;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeActiveStatus() {
        return employeeActiveStatus;
    }

    public void setEmployeeActiveStatus(String employeeActiveStatus) {
        this.employeeActiveStatus = employeeActiveStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigInteger getCreateBy() {
        return createBy;
    }

    public void setCreateBy(BigInteger createBy) {
        this.createBy = createBy;
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

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    
    public Date getEmployeeDate() {
        return employeeDate;
    }

    public void setEmployeeDate(Date employeeDate) {
        this.employeeDate = employeeDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getEmployeeDepartmentId() {
        return employeeDepartmentId;
    }

    public void setEmployeeDepartmentId(Department employeeDepartmentId) {
        this.employeeDepartmentId = employeeDepartmentId;
    }

    public PtdOffice getEmployeePtdOfficeId() {
        return employeePtdOfficeId;
    }

    public void setEmployeePtdOfficeId(PtdOffice employeePtdOfficeId) {
        this.employeePtdOfficeId = employeePtdOfficeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeId != null ? employeeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeId == null && other.employeeId != null) || (this.employeeId != null && !this.employeeId.equals(other.employeeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.Employee[ employeeId=" + employeeId + " ]";
    }
    
}
