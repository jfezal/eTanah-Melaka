/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil.kutipanAgensi;

/**
 *
 * @author faidzal
 */
public class LoginInfo {
    
   private String nama;
   private String username;
   private String kodAgensiKutipan;
   private String noKp;
   private String  emel;
   private String noTel;
   private String namaAgensi;
   private String password;
   private boolean auth = false;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKodAgensiKutipan() {
        return kodAgensiKutipan;
    }

    public void setKodAgensiKutipan(String kodAgensiKutipan) {
        this.kodAgensiKutipan = kodAgensiKutipan;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }
   
   
    
}
