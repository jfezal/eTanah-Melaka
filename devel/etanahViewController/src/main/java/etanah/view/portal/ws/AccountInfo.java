/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author faidzal
 */
public class AccountInfo {

    private Date tarikhAkhirBayar;
    private String lokasiBayar;

    // additional fields for bil cukai
    private String daerah;
    private String tahun;
    private String noLot;
    private String syaratNyata;
    private String jenisPegangan;
    private String keluasan;
    private String kategori; // kategori tanah
    private String kegunaan; // kegunaan tanah
    private String tunggakanTahun; // dari tahun ke tahun
    private BigDecimal lebihan;
    private String lokasiTanah;
    private String lokaliti;//lokaliti
    private String akaunBaru;
    private String kodCaw;

    private String noAkaun;
    private String namaPemilik;
    private String namaPembayar;
    private String icPembayar;
    private String icPemilik;
    private String alamat;
    private String idHakmilik;
    private String jenisHakmilik;
    private String noHakmilik;
    private BigDecimal bakiAkaun;
    private String bandarpekanmukim;

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

    private boolean akaunBatal = false;
    List<SejarahCukai> listSejarahCukai;

    private String kodCukaiSemasa;
    private String kodTunggakanCukai;
    private String kodDendaLewat;
    private String kodRemisyen;
//    private List<String> kodRemisyenList;
    private String kodNotis6a;
    private String kodTunggakanDendaLewat;
    private String kodCukaiSemasaParit;
    private String kodTunggakanCukaiParit;
    private String kodKreditDebit;
    private String kodCarianpersendirian;

    private String kodCawValue;
    
    private String statusAkaun;
   
   
    
    public boolean isAkaunBatal() {
        return akaunBatal;
    }

    public void setAkaunBatal(boolean akaunBatal) {
        this.akaunBatal = akaunBatal;
    }

    public Date getTarikhAkhirBayar() {
        return tarikhAkhirBayar;
    }

    public void setTarikhAkhirBayar(Date tarikhAkhirBayar) {
        this.tarikhAkhirBayar = tarikhAkhirBayar;
    }

    public String getLokasiBayar() {
        return lokasiBayar;
    }

    public void setLokasiBayar(String lokasiBayar) {
        this.lokasiBayar = lokasiBayar;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getJenisPegangan() {
        return jenisPegangan;
    }

    public void setJenisPegangan(String jenisPegangan) {
        this.jenisPegangan = jenisPegangan;
    }

    public String getKeluasan() {
        return keluasan;
    }

    public void setKeluasan(String keluasan) {
        this.keluasan = keluasan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(String kegunaan) {
        this.kegunaan = kegunaan;
    }

    public String getTunggakanTahun() {
        return tunggakanTahun;
    }

    public void setTunggakanTahun(String tunggakanTahun) {
        this.tunggakanTahun = tunggakanTahun;
    }

    public BigDecimal getLebihan() {
        return lebihan;
    }

    public void setLebihan(BigDecimal lebihan) {
        this.lebihan = lebihan;
    }

    public String getLokasiTanah() {
        return lokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    public String getLokaliti() {
        return lokaliti;
    }

    public void setLokaliti(String lokaliti) {
        this.lokaliti = lokaliti;
    }

    public String getAkaunBaru() {
        return akaunBaru;
    }

    public void setAkaunBaru(String akaunBaru) {
        this.akaunBaru = akaunBaru;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getNamaPembayar() {
        return namaPembayar;
    }

    public void setNamaPembayar(String namaPembayar) {
        this.namaPembayar = namaPembayar;
    }

    public String getIcPemilik() {
        return icPemilik;
    }

    public void setIcPemilik(String icPemilik) {
        this.icPemilik = icPemilik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

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

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public BigDecimal getBakiAkaun() {
        return bakiAkaun;
    }

    public void setBakiAkaun(BigDecimal bakiAkaun) {
        this.bakiAkaun = bakiAkaun;
    }

    public BigDecimal getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(BigDecimal jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
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

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public BigDecimal getCukaiSemasaTanah() {
        return cukaiSemasaTanah;
    }

    public void setCukaiSemasaTanah(BigDecimal cukaiSemasaTanah) {
        this.cukaiSemasaTanah = cukaiSemasaTanah;
    }

    public BigDecimal getCukaiSemasaParit() {
        return cukaiSemasaParit;
    }

    public void setCukaiSemasaParit(BigDecimal cukaiSemasaParit) {
        this.cukaiSemasaParit = cukaiSemasaParit;
    }

    public BigDecimal getRemisyen() {
        return remisyen;
    }

    public void setRemisyen(BigDecimal remisyen) {
        this.remisyen = remisyen;
    }

    public BigDecimal getTunggakanCukaiTanah() {
        return tunggakanCukaiTanah;
    }

    public void setTunggakanCukaiTanah(BigDecimal tunggakanCukaiTanah) {
        this.tunggakanCukaiTanah = tunggakanCukaiTanah;
    }

    public BigDecimal getTunggakanCukaiParit() {
        return tunggakanCukaiParit;
    }

    public void setTunggakanCukaiParit(BigDecimal tunggakanCukaiParit) {
        this.tunggakanCukaiParit = tunggakanCukaiParit;
    }

    public BigDecimal getDendaLewatSemasa() {
        return dendaLewatSemasa;
    }

    public void setDendaLewatSemasa(BigDecimal dendaLewatSemasa) {
        this.dendaLewatSemasa = dendaLewatSemasa;
    }

    public BigDecimal getTunggakanDendaLewat() {
        return tunggakanDendaLewat;
    }

    public void setTunggakanDendaLewat(BigDecimal tunggakanDendaLewat) {
        this.tunggakanDendaLewat = tunggakanDendaLewat;
    }

    public BigDecimal getNotis6a() {
        return notis6a;
    }

    public void setNotis6a(BigDecimal notis6a) {
        this.notis6a = notis6a;
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

    public List<SejarahCukai> getListSejarahCukai() {
        return listSejarahCukai;
    }

    public void setListSejarahCukai(List<SejarahCukai> listSejarahCukai) {
        this.listSejarahCukai = listSejarahCukai;
    }

    public String getBandarpekanmukim() {
        return bandarpekanmukim;
    }

    public void setBandarpekanmukim(String bandarpekanmukim) {
        this.bandarpekanmukim = bandarpekanmukim;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getIcPembayar() {
        return icPembayar;
    }

    public void setIcPembayar(String icPembayar) {
        this.icPembayar = icPembayar;
    }

    public String getKodCukaiSemasa() {
        return kodCukaiSemasa;
    }

    public void setKodCukaiSemasa(String kodCukaiSemasa) {
        this.kodCukaiSemasa = kodCukaiSemasa;
    }

    public String getKodTunggakanCukai() {
        return kodTunggakanCukai;
    }

    public void setKodTunggakanCukai(String kodTunggakanCukai) {
        this.kodTunggakanCukai = kodTunggakanCukai;
    }

    public String getKodDendaLewat() {
        return kodDendaLewat;
    }

    public void setKodDendaLewat(String kodDendaLewat) {
        this.kodDendaLewat = kodDendaLewat;
    }

    public String getKodRemisyen() {
        return kodRemisyen;
    }

    public void setKodRemisyen(String kodRemisyen) {
        this.kodRemisyen = kodRemisyen;
    }

    public String getKodNotis6a() {
        return kodNotis6a;
    }

    public void setKodNotis6a(String kodNotis6a) {
        this.kodNotis6a = kodNotis6a;
    }

    public String getKodTunggakanDendaLewat() {
        return kodTunggakanDendaLewat;
    }

    public void setKodTunggakanDendaLewat(String kodTunggakanDendaLewat) {
        this.kodTunggakanDendaLewat = kodTunggakanDendaLewat;
    }

    public String getKodCukaiSemasaParit() {
        return kodCukaiSemasaParit;
    }

    public void setKodCukaiSemasaParit(String kodCukaiSemasaParit) {
        this.kodCukaiSemasaParit = kodCukaiSemasaParit;
    }

    public String getKodTunggakanCukaiParit() {
        return kodTunggakanCukaiParit;
    }

    public void setKodTunggakanCukaiParit(String kodTunggakanCukaiParit) {
        this.kodTunggakanCukaiParit = kodTunggakanCukaiParit;
    }

    public String getKodKreditDebit() {
        return kodKreditDebit;
    }

    public void setKodKreditDebit(String kodKreditDebit) {
        this.kodKreditDebit = kodKreditDebit;
    }

    public String getKodCarianpersendirian() {
        return kodCarianpersendirian;
    }

    public void setKodCarianpersendirian(String kodCarianpersendirian) {
        this.kodCarianpersendirian = kodCarianpersendirian;
    }

    public String getKodCawValue() {
        return kodCawValue;
    }

    public void setKodCawValue(String kodCawValue) {
        this.kodCawValue = kodCawValue;
    }

    public String getStatusAkaun() {
        return statusAkaun;
    }

    public void setStatusAkaun(String statusAkaun) {
        this.statusAkaun = statusAkaun;
    }

}
