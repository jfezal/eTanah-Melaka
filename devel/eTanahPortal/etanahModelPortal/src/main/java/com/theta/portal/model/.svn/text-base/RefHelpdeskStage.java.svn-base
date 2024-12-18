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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "REF_HELPDESK_STAGE")
public class RefHelpdeskStage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KOD")
    private String kod;
    @Column(name = "URL")
    private String url;
    @Column(name = "PARAM_VALUE")
    private String paramValue;
    @Column(name = "NAME")
    private String name;
    @Column(name = "REF")
    private Integer ref;
     @Column(name = "REF_GRP")
    private String refGrp;

    public RefHelpdeskStage() {
    }

    public RefHelpdeskStage(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public String getRefGrp() {
        return refGrp;
    }

    public void setRefGrp(String refGrp) {
        this.refGrp = refGrp;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kod != null ? kod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefHelpdeskStage)) {
            return false;
        }
        RefHelpdeskStage other = (RefHelpdeskStage) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.RefHelpdeskStage[ kod=" + kod + " ]";
    }
    
}
