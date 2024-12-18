/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.client.wslog;

import etanah.ws.AkaunForm;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class CukaiKelompokForm {
    List<AkaunForm> listAccountForm;
    BigDecimal amount;
    String fpxNo;
     String transactionNo;
     String paymentTime;
     String paymentType;
     String resitNo;
     String dimasuk;

    public List<AkaunForm> getListAccountForm() {
        return listAccountForm;
    }

    public void setListAccountForm(List<AkaunForm> listAccountForm) {
        this.listAccountForm = listAccountForm;
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
    
    public boolean isNull() {
        Field fields[] = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                Object value = f.get(this);
                if (value == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        return true;

    }
     
     
}
