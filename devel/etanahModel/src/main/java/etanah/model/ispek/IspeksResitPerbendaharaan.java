/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.InfoAudit;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ISPEKS_RESIT_PERBENDAHARAAN")
@SequenceGenerator(name = "SEQ_ISPEKS_PERBENDAHARAAN", sequenceName = "SEQ_ISPEKS_PERBENDAHARAAN")
public class IspeksResitPerbendaharaan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_PERBENDAHARAAN")
    @Column(name = "ID")
    private Long id;
    @Column(name = "DEPT_CODE")
    String deptCode;
    @Column(name = "PTJ_CODE")
    String ptjCode;
    @Column(name = "PRBNH_RESIT_NUM")
    String perbendaharaanResitNumber;
    @Column(name = "PRBNH_RESIT_DATE")
    Date perbendaharaanResitPostingDate;
    @Column(name = "COL_STATEMENT_NUM")
    String collectorStatementNum;
    @Column(name = "COL_STATEMENT_DATE")
    Date collectorStatementDate;
    @Column(name = "COL_STATEMNET_DATEFRM")
    Date collectorStateDateFrom;
    @Column(name = "COL_STATEMENT_DATETO")
    Date collectorStateDateTo;
    @Column(name = "TREASURY_ACC_NUM")
    String treasuryAccNum;
    @Column(name = "RECEIPT_AMOUNT")
    BigDecimal perbendaharaanResitAmount;
    @Embedded
    private InfoAudit infoAudit;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getPtjCode() {
        return ptjCode;
    }

    public void setPtjCode(String ptjCode) {
        this.ptjCode = ptjCode;
    }

    public String getPerbendaharaanResitNumber() {
        return perbendaharaanResitNumber;
    }

    public void setPerbendaharaanResitNumber(String perbendaharaanResitNumber) {
        this.perbendaharaanResitNumber = perbendaharaanResitNumber;
    }

    public Date getPerbendaharaanResitPostingDate() {
        return perbendaharaanResitPostingDate;
    }

    public void setPerbendaharaanResitPostingDate(Date perbendaharaanResitPostingDate) {
        this.perbendaharaanResitPostingDate = perbendaharaanResitPostingDate;
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

    public Date getCollectorStateDateFrom() {
        return collectorStateDateFrom;
    }

    public void setCollectorStateDateFrom(Date collectorStateDateFrom) {
        this.collectorStateDateFrom = collectorStateDateFrom;
    }

    public Date getCollectorStateDateTo() {
        return collectorStateDateTo;
    }

    public void setCollectorStateDateTo(Date collectorStateDateTo) {
        this.collectorStateDateTo = collectorStateDateTo;
    }

    public String getTreasuryAccNum() {
        return treasuryAccNum;
    }

    public void setTreasuryAccNum(String treasuryAccNum) {
        this.treasuryAccNum = treasuryAccNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPerbendaharaanResitAmount() {
        return perbendaharaanResitAmount;
    }

    public void setPerbendaharaanResitAmount(BigDecimal perbendaharaanResitAmount) {
        this.perbendaharaanResitAmount = perbendaharaanResitAmount;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
