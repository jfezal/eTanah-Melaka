/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.model;

import java.io.Serializable;
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
@Table(name = "REF_SUBMODUL_TYPE")
public class RefSubmodulType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SUBMODUL_TYPE_ID")
    private Long submodulTypeId;
    @Column(name = "SUBMODUL_TYPE_NAME")
    private String submodulTypeName;

    public RefSubmodulType() {
    }

    public RefSubmodulType(Long submodulTypeId) {
        this.submodulTypeId = submodulTypeId;
    }

    public Long getSubmodulTypeId() {
        return submodulTypeId;
    }

    public void setSubmodulTypeId(Long submodulTypeId) {
        this.submodulTypeId = submodulTypeId;
    }

    public String getSubmodulTypeName() {
        return submodulTypeName;
    }

    public void setSubmodulTypeName(String submodulTypeName) {
        this.submodulTypeName = submodulTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (submodulTypeId != null ? submodulTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefSubmodulType)) {
            return false;
        }
        RefSubmodulType other = (RefSubmodulType) object;
        if ((this.submodulTypeId == null && other.submodulTypeId != null) || (this.submodulTypeId != null && !this.submodulTypeId.equals(other.submodulTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefSubmodulType[ submodulTypeId=" + submodulTypeId + " ]";
    }
    
}
