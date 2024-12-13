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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "REF_HELPDESK_STAGE_ROLE")
public class RefHelpdeskStageRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;


     @JoinColumn(name = "KOD_STAGE", referencedColumnName = "KOD")
    @ManyToOne
    private RefHelpdeskStage refHelpdeskStage;

     @JoinColumn(name = "TYPE_ID", referencedColumnName = "TYPE_ID")
    @ManyToOne
    private RefUserType refUserType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RefHelpdeskStage getRefHelpdeskStage() {
        return refHelpdeskStage;
    }

    public void setRefHelpdeskStage(RefHelpdeskStage refHelpdeskStage) {
        this.refHelpdeskStage = refHelpdeskStage;
    }

    public RefUserType getRefUserType() {
        return refUserType;
    }

    public void setRefUserType(RefUserType refUserType) {
        this.refUserType = refUserType;
    }
    

    public RefHelpdeskStageRole() {
    }
    
}
