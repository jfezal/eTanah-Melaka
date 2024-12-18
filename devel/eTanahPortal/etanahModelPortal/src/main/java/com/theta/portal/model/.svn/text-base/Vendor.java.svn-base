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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "VENDOR")
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "VENDOR_ID")
    private Long vendorId;
    @Column(name = "VENDOR_NAME")
    private String vendorName;
    @Column(name = "VENDOR_CONTACT_PERSON")
    private String vendorContactPerson;
    @Column(name = "VENDOR_CONTACT_PHONE")
    private String vendorContactPhone;
    @Column(name = "VENDOR_ADD")
    private String vendorAdd;
    @Column(name = "VENDOR_COMP_PHONE")
    private String vendorCompPhone;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "MODFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modfiedDate;
    @Column(name = "MODIFIED_BY")
    private BigInteger modifiedBy;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "EMAIL")
    private String email;
    @JoinColumn(name = "PTD_OFFICE_ID", referencedColumnName = "PTD_OFFICE_ID")
    @ManyToOne
    private PtdOffice ptdOfficeId;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "USER_ID")
    @ManyToOne
    private UserTable createBy;
    @Column(name = "HD_STS")
    private String helpdeskStatus;

    public Vendor() {
    }

    public Vendor(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContactPerson() {
        return vendorContactPerson;
    }

    public void setVendorContactPerson(String vendorContactPerson) {
        this.vendorContactPerson = vendorContactPerson;
    }

    public String getVendorContactPhone() {
        return vendorContactPhone;
    }

    public void setVendorContactPhone(String vendorContactPhone) {
        this.vendorContactPhone = vendorContactPhone;
    }

    public String getVendorAdd() {
        return vendorAdd;
    }

    public void setVendorAdd(String vendorAdd) {
        this.vendorAdd = vendorAdd;
    }

    public String getVendorCompPhone() {
        return vendorCompPhone;
    }

    public void setVendorCompPhone(String vendorCompPhone) {
        this.vendorCompPhone = vendorCompPhone;
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

    public BigInteger getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BigInteger modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PtdOffice getPtdOfficeId() {
        return ptdOfficeId;
    }

    public void setPtdOfficeId(PtdOffice ptdOfficeId) {
        this.ptdOfficeId = ptdOfficeId;
    }

    public UserTable getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserTable createBy) {
        this.createBy = createBy;
    }

    public String getHelpdeskStatus() {
        return helpdeskStatus;
    }

    public void setHelpdeskStatus(String helpdeskStatus) {
        this.helpdeskStatus = helpdeskStatus;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorId != null ? vendorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendor)) {
            return false;
        }
        Vendor other = (Vendor) object;
        if ((this.vendorId == null && other.vendorId != null) || (this.vendorId != null && !this.vendorId.equals(other.vendorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.Vendor[ vendorId=" + vendorId + " ]";
    }
    
}
