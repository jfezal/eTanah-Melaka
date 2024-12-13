/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.InfoAudit;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "ISPEKS_BATAL_JOURNAL")
@SequenceGenerator(name = "SEQ_ISPEKS_BATAL_JOURNAL", sequenceName = "SEQ_ISPEKS_BATAL_JOURNAL")
public class IspeksBatalJurnal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_BATAL_JOURNAL")
    @Column(name = "ID")
    private Long id;
    @Column(name = "RECORD_TYPE")
    String recordType;
    @Column(name = "VOUCHER_NUMBER")
    String voucherNumber;
    @Column(name = "FILE_NAME")
    String fileName;
    @Column(name = "VALIDATOR_NAME")
    String validatorName;
    @Column(name = "VERIFIER_POSITION")
    String verifierPosition;
    @Column(name = "VERIFY_DATE")
    Date verifyDate;
@Embedded
    private InfoAudit infoAudit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getValidatorName() {
        return validatorName;
    }

    public void setValidatorName(String validatorName) {
        this.validatorName = validatorName;
    }

    public String getVerifierPosition() {
        return verifierPosition;
    }

    public void setVerifierPosition(String verifierPosition) {
        this.verifierPosition = verifierPosition;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    

}
