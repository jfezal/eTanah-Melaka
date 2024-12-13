/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "REF_HARDWARE_TYPE")
public class RefHardwareType implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "HARDWARE_TYPE_ID")
    private Long hardwareTypeId;
    @Column(name = "HARDWARE_TYPE_NAME")
    private String hardwareTypeName;

    public RefHardwareType() {
    }

    public RefHardwareType(Long hardwareTypeId) {
        this.hardwareTypeId = hardwareTypeId;
    }

    public Long getHardwareTypeId() {
        return hardwareTypeId;
    }

    public void setHardwareTypeId(Long hardwareTypeId) {
        this.hardwareTypeId = hardwareTypeId;
    }

    public String getHardwareTypeName() {
        return hardwareTypeName;
    }

    public void setHardwareTypeName(String hardwareTypeName) {
        this.hardwareTypeName = hardwareTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hardwareTypeId != null ? hardwareTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHardwareType)) {
            return false;
        }
        RefHardwareType other = (RefHardwareType) object;
        if ((this.hardwareTypeId == null && other.hardwareTypeId != null) || (this.hardwareTypeId != null && !this.hardwareTypeId.equals(other.hardwareTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHardwareType[ hardwareTypeId=" + hardwareTypeId + " ]";
    }
    
}
