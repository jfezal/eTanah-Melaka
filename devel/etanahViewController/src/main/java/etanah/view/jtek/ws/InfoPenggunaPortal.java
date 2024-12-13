/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.jtek.ws;

import java.util.Date;

/**
 *
 * @author faidzal
 */
public class InfoPenggunaPortal {

    private String idPguna;
    private String nama;
    private String agensi;
    private String kodAgensi;
    private String kodAgensiKutipan;
    private String namaKutipan;
    private String noKp;
    private String passwd;
    private String email;
    private Date trhLogin;
    private Integer bilCubaan;
    private String noTel;
    private boolean auth = false;
    private boolean kutipan = false;

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

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
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

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getKodAgensiKutipan() {
        return kodAgensiKutipan;
    }

    public void setKodAgensiKutipan(String kodAgensiKutipan) {
        this.kodAgensiKutipan = kodAgensiKutipan;
    }

    public String getNamaKutipan() {
        return namaKutipan;
    }

    public void setNamaKutipan(String namaKutipan) {
        this.namaKutipan = namaKutipan;
    }

    public boolean isKutipan() {
        return kutipan;
    }

    public void setKutipan(boolean kutipan) {
        this.kutipan = kutipan;
    }
    
    
}
