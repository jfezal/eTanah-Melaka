/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.form.log;

import java.math.BigDecimal;
import javax.jws.WebParam;

/**
 *
 * @author mohd.faidzal
 */
public class UpdateAccountUserForm {
   String idHakmilik;
    BigDecimal amount;
    String fpxNo;
    String transactionNo;
    String paymentTime;
    String paymentType; String resitNo;
    String dimasuk;

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFpxNo() {
        return fpxNo;
    }

    public void setFpxNo(String fpxNo) {
        this.fpxNo = fpxNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getResitNo() {
        return resitNo;
    }

    public void setResitNo(String resitNo) {
        this.resitNo = resitNo;
    }

    public String getDimasuk() {
        return dimasuk;
    }

    public void setDimasuk(String dimasuk) {
        this.dimasuk = dimasuk;
    }
    
    
}
