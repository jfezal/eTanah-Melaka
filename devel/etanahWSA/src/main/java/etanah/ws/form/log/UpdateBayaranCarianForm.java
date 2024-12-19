/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.form.log;

import java.math.BigDecimal;

/**
 *
 * @author mohd.faidzal
 */
public class UpdateBayaranCarianForm {
    String accountNo;
    String transId;
    String paymentDateTime;
    BigDecimal amaun;
    String idPengguna;
    String resitNo;
    String namaPenyerah;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(String paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getResitNo() {
        return resitNo;
    }

    public void setResitNo(String resitNo) {
        this.resitNo = resitNo;
    }

    public String getNamaPenyerah() {
        return namaPenyerah;
    }

    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;
    }
    
    
}
