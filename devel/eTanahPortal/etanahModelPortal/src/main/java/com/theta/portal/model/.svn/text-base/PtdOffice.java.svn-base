/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "PTD_OFFICE")
public class PtdOffice implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PTD_OFFICE_ID", nullable = false)
    private Long ptdOfficeId;
    @Column(name = "PTD_OFFICE_NAME")
    private String ptdOfficeName;
    @Column(name = "PTD_STATE_ID")
    private BigInteger ptdStateId;
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
    @Column(name = "PTG_FLAG")
    private Long ptgFlag;
    @Column(name = "PTD_ACRONYM")
    private String ptdAcronym;
    @Column(name = "PTD_CODE")
    private String ptdCode;
    @Column(name = "PTD_ADDRESS")
    private String ptdAddress;
    @Column(name = "PTD_NAME")
    private String ptdName;
   
    public PtdOffice() {
    }

    public PtdOffice(Long ptdOfficeId) {
        this.ptdOfficeId = ptdOfficeId;
    }

    public Long getPtdOfficeId() {
        return ptdOfficeId;
    }

    public void setPtdOfficeId(Long ptdOfficeId) {
        this.ptdOfficeId = ptdOfficeId;
    }

    public String getPtdOfficeName() {
        return ptdOfficeName;
    }

    public void setPtdOfficeName(String ptdOfficeName) {
        this.ptdOfficeName = ptdOfficeName;
    }

    public BigInteger getPtdStateId() {
        return ptdStateId;
    }

    public void setPtdStateId(BigInteger ptdStateId) {
        this.ptdStateId = ptdStateId;
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

    public Long getPtgFlag() {
        return ptgFlag;
    }

    public void setPtgFlag(Long ptgFlag) {
        this.ptgFlag = ptgFlag;
    }

    public String getPtdAcronym() {
        return ptdAcronym;
    }

    public void setPtdAcronym(String ptdAcronym) {
        this.ptdAcronym = ptdAcronym;
    }

    public String getPtdCode() {
        return ptdCode;
    }

    public void setPtdCode(String ptdCode) {
        this.ptdCode = ptdCode;
    }

    public String getPtdAddress() {
        return ptdAddress;
    }

    public void setPtdAddress(String ptdAddress) {
        this.ptdAddress = ptdAddress;
    }

    public String getPtdName() {
        return ptdName;
    }

    public void setPtdName(String ptdName) {
        this.ptdName = ptdName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptdOfficeId != null ? ptdOfficeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtdOffice)) {
            return false;
        }
        PtdOffice other = (PtdOffice) object;
        if ((this.ptdOfficeId == null && other.ptdOfficeId != null) || (this.ptdOfficeId != null && !this.ptdOfficeId.equals(other.ptdOfficeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.PtdOffice[ ptdOfficeId=" + ptdOfficeId + " ]";
    }
    
}
