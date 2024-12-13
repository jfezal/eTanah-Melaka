/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.InfoAudit;
import java.io.Serializable;
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
@Table(name = "ISPEKS_STATUS_INFO")
@SequenceGenerator(name = "seq_ispeks_status_info", sequenceName = "seq_ispeks_status_info")
public class StatusInfoIspeks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ispeks_status_info")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NO_REF")
    private String noRef;
    @Column(name = "IDD_PATH")
    private String iddPath;
    @Column(name = "GPG_PATH")
    private String gpgPath;
    @Column(name = "STS_IDD")
    private String stsIDD;
    @Column(name = "STS_ENCRYPT")
    private String stsEncryptDecrypt;
    @Column(name = "STS_TRANSFER")
    private String stsTransfer;
    @Column(name = "GPG_FILENAME")
    private String gpgFileName;
    @Column(name = "JNS_FILE")
    private String jenisFail;
     @Column(name = "KOD_CAW")
    private String kodCawangan;
    @Embedded
	private InfoAudit infoAudit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoRef() {
        return noRef;
    }

    public void setNoRef(String noRef) {
        this.noRef = noRef;
    }

    public String getIddPath() {
        return iddPath;
    }

    public void setIddPath(String iddPath) {
        this.iddPath = iddPath;
    }

    public String getGpgPath() {
        return gpgPath;
    }

    public void setGpgPath(String gpgPath) {
        this.gpgPath = gpgPath;
    }

    public String getStsIDD() {
        return stsIDD;
    }

    public void setStsIDD(String stsIDD) {
        this.stsIDD = stsIDD;
    }

    public String getStsEncryptDecrypt() {
        return stsEncryptDecrypt;
    }

    public void setStsEncryptDecrypt(String stsEncryptDecrypt) {
        this.stsEncryptDecrypt = stsEncryptDecrypt;
    }

    public String getStsTransfer() {
        return stsTransfer;
    }

    public void setStsTransfer(String stsTransfer) {
        this.stsTransfer = stsTransfer;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getGpgFileName() {
        return gpgFileName;
    }

    public void setGpgFileName(String gpgFileName) {
        this.gpgFileName = gpgFileName;
    }

    public String getJenisFail() {
        return jenisFail;
    }

    public void setJenisFail(String jenisFail) {
        this.jenisFail = jenisFail;
    }

    public String getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }
    
    

}
