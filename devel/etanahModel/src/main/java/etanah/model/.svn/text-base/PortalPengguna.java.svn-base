/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "pguna_portal")
public class PortalPengguna implements Serializable {

    @Id
    @Column(name = "id_pguna")
    private String idPguna;
    @Column(name = "nama")
    private String nama;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_agensi")
    private KodAgensi kodAgensi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_agensi_kutipan")
    private KodAgensiKutipan kodAgensiKutipan;
    
    @Column(name = "no_kp")
    private String noKp;
    @Column(name = "passwd")
    private String passwd;
    @Column(name = "email")
    private String email;
    @Column(name = "tarikh_login")
    private Date trhLogin;
    @Column(name = "bil_cubaan")
    private Integer bilCubaan;
    @Column(name = "no_tel")
    private String noTel;
    @Column(name = "tarikh_kkini_laluan")
    private Date trhKKiniKatalaluan;
    @Column(name = "kod_sts")
    private String kodSts;
      @Column(name = "alamat")
    private String alamat;
    @Embedded
    private InfoAudit infoAudit;

    public String getIdPguna() {
        return idPguna;
    }

    public void setIdPguna(String idPguna) {
        this.idPguna = idPguna;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTrhLogin() {
        return trhLogin;
    }

    public void setTrhLogin(Date trhLogin) {
        this.trhLogin = trhLogin;
    }

    public Integer getBilCubaan() {
        return bilCubaan;
    }

    public void setBilCubaan(Integer bilCubaan) {
        this.bilCubaan = bilCubaan;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public Date getTrhKKiniKatalaluan() {
        return trhKKiniKatalaluan;
    }

    public void setTrhKKiniKatalaluan(Date trhKKiniKatalaluan) {
        this.trhKKiniKatalaluan = trhKKiniKatalaluan;
    }

    public String getKodSts() {
        return kodSts;
    }

    public void setKodSts(String kodSts) {
        this.kodSts = kodSts;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodAgensiKutipan getKodAgensiKutipan() {
        return kodAgensiKutipan;
    }

    public void setKodAgensiKutipan(KodAgensiKutipan kodAgensiKutipan) {
        this.kodAgensiKutipan = kodAgensiKutipan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    
    
}
