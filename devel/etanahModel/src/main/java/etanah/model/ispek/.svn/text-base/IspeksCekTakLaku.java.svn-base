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
@Table(name = "ISPEKS_CEK_TAKLAKU")
@SequenceGenerator(name = "SEQ_ISPEKS_CEK_TAKLAKU", sequenceName = "SEQ_ISPEKS_CEK_TAKLAKU")
public class IspeksCekTakLaku implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_CEK_TAKLAKU")
    @Column(name = "ID")
    private Long id;
    @Column(name = "REC_NUM")
    String recordNum;
    @Column(name = "DEPT_CODE")
    String departCode;
    @Column(name = "PTJ_CODE")
    String ptjCode;
    @Column(name = "COL_STATEMENT_NUM")
    String collectorStatementNum;
    @Column(name = "COL_STATEMENT_DATE")
    Date collectorStatementDate;
    @Column(name = "PRNHR_RESIT_NUM")
    String perbendaharaanResitNum;
    @Column(name = "RESIT_DATE")
    Date resitDate;
    @Column(name = "BANK_CODE")
    String bankCode;
    @Column(name = "REF_NUM")
    String refNum;
    @Column(name = "PAYER_ACC_NUM")
    String payerAccNum;
    @Column(name = "REF_DATE")
    Date refDate;
    @Column(name = "REF_AMAUN")
    String refAmaun;
    @Column(name = "DESCRIPTION")
    String description;
    @Column(name = "VOUCHER_NUM")
    String voucherNum;
    @Column(name = "VOUCHER_DATE")
    Date voucherDate;
@Embedded
    private InfoAudit infoAudit;

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getPtjCode() {
        return ptjCode;
    }

    public void setPtjCode(String ptjCode) {
        this.ptjCode = ptjCode;
    }

    public String getCollectorStatementNum() {
        return collectorStatementNum;
    }

    public void setCollectorStatementNum(String collectorStatementNum) {
        this.collectorStatementNum = collectorStatementNum;
    }

    public Date getCollectorStatementDate() {
        return collectorStatementDate;
    }

    public void setCollectorStatementDate(Date collectorStatementDate) {
        this.collectorStatementDate = collectorStatementDate;
    }

    public String getPerbendaharaanResitNum() {
        return perbendaharaanResitNum;
    }

    public void setPerbendaharaanResitNum(String perbendaharaanResitNum) {
        this.perbendaharaanResitNum = perbendaharaanResitNum;
    }

    public Date getResitDate() {
        return resitDate;
    }

    public void setResitDate(Date resitDate) {
        this.resitDate = resitDate;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public String getPayerAccNum() {
        return payerAccNum;
    }

    public void setPayerAccNum(String payerAccNum) {
        this.payerAccNum = payerAccNum;
    }

    public Date getRefDate() {
        return refDate;
    }

    public void setRefDate(Date refDate) {
        this.refDate = refDate;
    }

    public String getRefAmaun() {
        return refAmaun;
    }

    public void setRefAmaun(String refAmaun) {
        this.refAmaun = refAmaun;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVoucherNum() {
        return voucherNum;
    }

    public void setVoucherNum(String voucherNum) {
        this.voucherNum = voucherNum;
    }

    public Date getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
