/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.ws.account;

import java.math.BigDecimal;

/**
 *
 * @author Izam
 */
public class AccountInfo {

    private String noAkaun;
    //private String namaPemilik;
    private String namaPembayar;
    //private String icPembayar;
    private String icPemilik;
    private String alamat;
    private String idHakmilik;
    private String idHakmilikBatal;
    private String jenisHakmilik;
    private String noHakmilik;
    private BigDecimal bakiAkaun ;
  
//    private long cukaiSemasa;
//    private long tunggakanCukai;
//    private long dendaLewat;
    //private long tunggakanDenda;
    //private long kutipanKurang;
    //private long kutipanLebih;
//    private long remisyen;
//    private long notis6a;
    private BigDecimal jumlahBayaran;
    private String statusBayaran;
    private String tarikhBayaran;
    private String noResit;
   
    private BigDecimal cukaiSemasaTanah;
    private BigDecimal cukaiSemasaParit;
    private BigDecimal remisyen;
    private BigDecimal tunggakanCukaiTanah;
    private BigDecimal tunggakanCukaiParit;
    private BigDecimal dendaLewatSemasa;
    private BigDecimal tunggakanDendaLewat;
    private BigDecimal notis6a;
    private BigDecimal jumlahCukaiSemasa;
    private BigDecimal jumlahTunggakan;
    private BigDecimal kreditDebit;
        private BigDecimal jumlahPecahan;

    public BigDecimal getJumlahPecahan() {
        return jumlahPecahan;
    }

    public void setJumlahPecahan(BigDecimal jumlahPecahan) {
        this.jumlahPecahan = jumlahPecahan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

//    public long getCukaiSemasa() {
//        return cukaiSemasa;
//    }
//
//    public void setCukaiSemasa(long cukaiSemasa) {
//        this.cukaiSemasa = cukaiSemasa;
//    }
//
//    public long getDendaLewat() {
//        return dendaLewat;
//    }
//
//    public void setDendaLewat(long dendaLewat) {
//        this.dendaLewat = dendaLewat;
//    }

//    public String getIcPembayar() {
//        return icPembayar;
//    }
//
//    public void setIcPembayar(String icPembayar) {
//        this.icPembayar = icPembayar;
//    }
    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public BigDecimal getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(BigDecimal jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
    }

//    public long getKutipanKurang() {
//        return kutipanKurang;
//    }
//
//    public void setKutipanKurang(long kutipanKurang) {
//        this.kutipanKurang = kutipanKurang;
//    }
//
//    public long getKutipanLebih() {
//        return kutipanLebih;
//    }
//
//    public void setKutipanLebih(long kutipanLebih) {
//        this.kutipanLebih = kutipanLebih;
//    }
    public String getNamaPembayar() {
        return namaPembayar;
    }

    public void setNamaPembayar(String namaPembayar) {
        this.namaPembayar = namaPembayar;
    }

//    public String getNamaPemilik() {
//        return namaPemilik;
//    }
//
//    public void setNamaPemilik(String namaPemilik) {
//        this.namaPemilik = namaPemilik;
//    }
    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public BigDecimal getNotis6a() {
        return notis6a;
    }

    public void setNotis6a(BigDecimal notis6a) {
        this.notis6a = notis6a;
    }

    public BigDecimal getRemisyen() {
        return remisyen;
    }

    public void setRemisyen(BigDecimal remisyen) {
        this.remisyen = remisyen;
    }

    public String getStatusBayaran() {
        return statusBayaran;
    }

    public void setStatusBayaran(String statusBayaran) {
        this.statusBayaran = statusBayaran;
    }

    public String getTarikhBayaran() {
        return tarikhBayaran;
    }

    public void setTarikhBayaran(String tarikhBayaran) {
        this.tarikhBayaran = tarikhBayaran;
    }

//    public long getTunggakanCukai() {
//        return tunggakanCukai;
//    }
//
//    public void setTunggakanCukai(long tunggakanCukai) {
//        this.tunggakanCukai = tunggakanCukai;
//    }

//    public long getTunggakanDenda() {
//        return tunggakanDenda;
//    }
//
//    public void setTunggakanDenda(long tunggakanDenda) {
//        this.tunggakanDenda = tunggakanDenda;
//    }
    public BigDecimal getBakiAkaun() {
        return bakiAkaun;
    }

    public void setBakiAkaun(BigDecimal bakiAkaun) {
        this.bakiAkaun = bakiAkaun;
    }

    public String getIcPemilik() {
        return icPemilik;
    }

    public void setIcPemilik(String icPemilik) {
        this.icPemilik = icPemilik;
    }

    public BigDecimal getCukaiSemasaParit() {
        return cukaiSemasaParit;
    }

    public void setCukaiSemasaParit(BigDecimal cukaiSemasaParit) {
        this.cukaiSemasaParit = cukaiSemasaParit;
    }

    public BigDecimal getCukaiSemasaTanah() {
        return cukaiSemasaTanah;
    }

    public void setCukaiSemasaTanah(BigDecimal cukaiSemasaTanah) {
        this.cukaiSemasaTanah = cukaiSemasaTanah;
    }

    public BigDecimal getDendaLewatSemasa() {
        return dendaLewatSemasa;
    }

    public void setDendaLewatSemasa(BigDecimal dendaLewatSemasa) {
        this.dendaLewatSemasa = dendaLewatSemasa;
    }

    public BigDecimal getTunggakanCukaiParit() {
        return tunggakanCukaiParit;
    }

    public void setTunggakanCukaiParit(BigDecimal tunggakanCukaiParit) {
        this.tunggakanCukaiParit = tunggakanCukaiParit;
    }

    public BigDecimal getTunggakanCukaiTanah() {
        return tunggakanCukaiTanah;
    }

    public void setTunggakanCukaiTanah(BigDecimal tunggakanCukaiTanah) {
        this.tunggakanCukaiTanah = tunggakanCukaiTanah;
    }

    public BigDecimal getTunggakanDendaLewat() {
        return tunggakanDendaLewat;
    }

    public void setTunggakanDendaLewat(BigDecimal tunggakanDendaLewat) {
        this.tunggakanDendaLewat = tunggakanDendaLewat;
    }

    public BigDecimal getJumlahCukaiSemasa() {
        return jumlahCukaiSemasa;
    }

    public void setJumlahCukaiSemasa(BigDecimal jumlahCukaiSemasa) {
        this.jumlahCukaiSemasa = jumlahCukaiSemasa;
    }

    public BigDecimal getJumlahTunggakan() {
        return jumlahTunggakan;
    }

    public void setJumlahTunggakan(BigDecimal jumlahTunggakan) {
        this.jumlahTunggakan = jumlahTunggakan;
    }

    public BigDecimal getKreditDebit() {
        return kreditDebit;
    }

    public void setKreditDebit(BigDecimal kreditDebit) {
        this.kreditDebit = kreditDebit;
    }

    public String getIdHakmilikBatal() {
        return idHakmilikBatal;
    }

    public void setIdHakmilikBatal(String idHakmilikBatal) {
        this.idHakmilikBatal = idHakmilikBatal;
    }
    
    
}
