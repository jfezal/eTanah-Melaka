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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "HELPDESK_CONTRACTOR_DOCUMENTS")
@SequenceGenerator(name = "SEQ_HELPDESK_CONTRACTOR_DOC_ID", sequenceName = "SEQ_HELPDESK_CONTRACTOR_DOC_ID")
public class HelpdeskContractorDocuments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HELPDESK_CONTRACTOR_DOC_ID")    
    @Column(name = "ID")
    private Long id;
    @Lob
    @Column(name = "BINARY_DATA")
    private byte[] binaryData;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @JoinColumn(name = "HELPDESK_CONTRACTOR_ID", referencedColumnName = "HELPDESK_CONTRACTOR_ID")
    @ManyToOne
    private HelpdeskContractor helpdeskContractorId;

    public HelpdeskContractorDocuments() {
    }

    public HelpdeskContractorDocuments(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HelpdeskContractor getHelpdeskContractorId() {
        return helpdeskContractorId;
    }

    public void setHelpdeskContractorId(HelpdeskContractor helpdeskContractorId) {
        this.helpdeskContractorId = helpdeskContractorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HelpdeskContractorDocuments)) {
            return false;
        }
        HelpdeskContractorDocuments other = (HelpdeskContractorDocuments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.theta.portal.model.HelpdeskContractorDocuments[ id=" + id + " ]";
    }
    
}
