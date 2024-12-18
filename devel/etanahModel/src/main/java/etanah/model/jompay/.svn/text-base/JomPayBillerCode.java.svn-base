/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.jompay;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "JOMPAY_BILLERCODE")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class JomPayBillerCode implements Serializable {

    @Id
    @Column(name = "VIRTUAL_AC")
    private String VaNo;

    @Column(name = "BILLER_CODE")
    String billerCode;

    @Column(name = "JENIS_BC")
    String jenisBillerCode;

    @Column(name = "ACCOUNT")
    String account;
    @Column(name = "KOD_CAW")
    String kodCaw;

    @Column(name = "STATUS")
    String sts;

    public String getVaNo() {
        return VaNo;
    }

    public void setVaNo(String VaNo) {
        this.VaNo = VaNo;
    }

    public String getBillerCode() {
        return billerCode;
    }

    public void setBillerCode(String billerCode) {
        this.billerCode = billerCode;
    }

    public String getJenisBillerCode() {
        return jenisBillerCode;
    }

    public void setJenisBillerCode(String jenisBillerCode) {
        this.jenisBillerCode = jenisBillerCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    
}
