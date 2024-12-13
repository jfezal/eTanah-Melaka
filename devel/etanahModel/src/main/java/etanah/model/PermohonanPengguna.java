/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Izam
 */

@Entity
@Table(name = "mohon_pguna")
public class PermohonanPengguna implements Serializable {
    @Id
    @Column(name = "id_pguna", length = 30)
    private String idPengguna;

    @Column(name = "nama", length = 50, nullable = false)
    private String nama;

    @Column(name = "no_kp")
    private String noPengenalan;

    @ManyToOne
    @JoinColumn(name = "kod_sts")
    private KodStatusPengguna status;

    @ManyToOne
    @JoinColumn(name = "kod_jab")
    private KodJabatan kodJabatan;

    @Column(name = "id_kaunter", length = 2, nullable = true)
    private String idKaunter;

    @Column(name = "bil_cubaan", length = 2, nullable = true)
    private Long bilCubaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan kodCawangan;

    @Column(name = "jawatan")
    private String jawatan;

    @Column(name = "alamat1", length = 40)
    private String alamat1;

    @Column(name = "alamat2", length = 40)
    private String alamat2;

    @Column(name = "alamat3", length = 40)
    private String alamat3;

    @Column(name = "alamat4", length = 40)
    private String alamat4;

    @Column(name = "poskod", length = 5)
    private String poskod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_negeri")
    private KodNegeri negeri;

    @Column(name = "no_tel")
    private String noTelefon;

    @Column(name = "no_bimbit")
    private String noTelefonBimbit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_penyelia")
    private Pengguna penyelia;

    @Column(name = "email")
    private String email;

    @Column(name = "trh_login")
    private Date tarikhAkhirLogin;

    @Column(name = "passwd", length = 100)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peranan_utama")
    private KodPeranan perananUtama;

    @ManyToOne
    @JoinColumn(name = "kod_klas")
    private KodKlasifikasi tahapSekuriti;

    @Column(name = "pcetak1")
    private String namaPencetak1;

    @Column(name = "pcetak2")
    private String namaPencetak2;

    @OneToMany(mappedBy = "pengguna")
    private List<PenggunaPeranan> senaraiPeranan;
    
    @Column(name = "catatan")
    private String catatan;
    
    @ManyToOne
    @JoinColumn(name = "kod_jawatan")
    private KodJawatan kodJawatan;

    @Column(name = "pelulus_bayar_kurang")
    private String pelulusBayaranKurang;

    @Embedded
    private InfoAudit infoAufit;

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public Long getBilCubaan() {
        return bilCubaan;
    }

    public void setBilCubaan(Long bilCubaan) {
        this.bilCubaan = bilCubaan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdKaunter() {
        return idKaunter;
    }

    public void setIdKaunter(String idKaunter) {
        this.idKaunter = idKaunter;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public InfoAudit getInfoAufit() {
        return infoAufit;
    }

    public void setInfoAufit(InfoAudit infoAufit) {
        this.infoAufit = infoAufit;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public KodJabatan getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(KodJabatan kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaPencetak1() {
        return namaPencetak1;
    }

    public void setNamaPencetak1(String namaPencetak1) {
        this.namaPencetak1 = namaPencetak1;
    }

    public String getNamaPencetak2() {
        return namaPencetak2;
    }

    public void setNamaPencetak2(String namaPencetak2) {
        this.namaPencetak2 = namaPencetak2;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getNoTelefonBimbit() {
        return noTelefonBimbit;
    }

    public void setNoTelefonBimbit(String noTelefonBimbit) {
        this.noTelefonBimbit = noTelefonBimbit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pengguna getPenyelia() {
        return penyelia;
    }

    public void setPenyelia(Pengguna penyelia) {
        this.penyelia = penyelia;
    }

    public KodPeranan getPerananUtama() {
        return perananUtama;
    }

    public void setPerananUtama(KodPeranan perananUtama) {
        this.perananUtama = perananUtama;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public List<PenggunaPeranan> getSenaraiPeranan() {
        return senaraiPeranan;
    }

    public void setSenaraiPeranan(List<PenggunaPeranan> senaraiPeranan) {
        this.senaraiPeranan = senaraiPeranan;
    }

    public KodStatusPengguna getStatus() {
        return status;
    }

    public void setStatus(KodStatusPengguna status) {
        this.status = status;
    }

    public KodKlasifikasi getTahapSekuriti() {
        return tahapSekuriti;
    }

    public void setTahapSekuriti(KodKlasifikasi tahapSekuriti) {
        this.tahapSekuriti = tahapSekuriti;
    }

    public Date getTarikhAkhirLogin() {
        return tarikhAkhirLogin;
    }

    public void setTarikhAkhirLogin(Date tarikhAkhirLogin) {
        this.tarikhAkhirLogin = tarikhAkhirLogin;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public KodJawatan getKodJawatan() {
        return kodJawatan;
    }

    public void setKodJawatan(KodJawatan kodJawatan) {
        this.kodJawatan = kodJawatan;
    }

    public String getPelulusBayaranKurang() {
        return pelulusBayaranKurang;
    }

    public void setPelulusBayaranKurang(String pelulusBayaranKurang) {
        this.pelulusBayaranKurang = pelulusBayaranKurang;
    }
    
    
}
