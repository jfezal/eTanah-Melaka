package etanah.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import weblogic.cluster.messaging.internal.Hex;

@Entity
@Table(name = "pguna")
public class Pengguna implements Serializable {

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

//    @ManyToOne
//    @JoinColumn(name = "imej_tt")
//    private Imej imejTT;
   
    @Column(name = "imej_tt")
    private Blob imejTT;

    @Column(name = "id_kaunter", length = 2, nullable = true)
    private String idKaunter;

    @Column(name = "bil_cubaan", length = 2, nullable = true)
    private Long bilCubaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan kodCawangan;

    @Column(name = "jawatan")
    private String jawatan;
    
    @Column(name = "dimasuk_klaluan")
    private String dimasukKlaluan;

    @Column(name = "kekananan")
    private Integer kekananan;

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
//    @Temporal(TemporalType.DATE)
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

    @OneToMany(mappedBy = "pengguna", fetch = FetchType.EAGER)
    private List<PenggunaPeranan> senaraiPeranan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_jawatan")
    private KodJawatan kodJawatan;

    @Column(name = "penyelia")
    private String penyeliaFlag;

    @Column(name = "trh_kkini_klaluan")
    private Date tarikhKemaskiniKatalaluan;

    @Column(name = "pelulus_bayar_kurang")
    private String pelulusBayaranKurang;

    @Column(name = "kad_kuasa")
    private String kadKuasa;

    @Embedded
    private InfoAudit infoAudit;

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setStatus(KodStatusPengguna status) {
        this.status = status;
    }

    public KodStatusPengguna getStatus() {
        return status;
    }

    public KodJabatan getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(KodJabatan kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public void setIdKaunter(String idKaunter) {
        this.idKaunter = idKaunter;
    }

    public String getIdKaunter() {
        return idKaunter;
    }

    public Long getBilCubaan() {
        return bilCubaan;
    }

    public void setBilCubaan(Long bilCubaan) {
        this.bilCubaan = bilCubaan;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public Integer getKekananan() {
        return kekananan;
    }

    public void setKekananan(Integer kekananan) {
        this.kekananan = kekananan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTarikhAkhirLogin() {
        return tarikhAkhirLogin;
    }

    public void setTarikhAkhirLogin(Date lastLoginDate) {
        this.tarikhAkhirLogin = lastLoginDate;
    }

    public KodPeranan getPerananUtama() {
        return perananUtama;
    }

    public void setPerananUtama(KodPeranan perananUtama) {
        this.perananUtama = perananUtama;
    }

    public KodKlasifikasi getTahapSekuriti() {
        return tahapSekuriti;
    }

    public void setTahapSekuriti(KodKlasifikasi tahapSekuriti) {
        this.tahapSekuriti = tahapSekuriti;
    }

    public void setNamaPencetak1(String pencetak1) {
        this.namaPencetak1 = pencetak1;
    }

    public String getNamaPencetak1() {
        return namaPencetak1;
    }

    public void setNamaPencetak2(String pencetak2) {
        this.namaPencetak2 = pencetak2;
    }

    public String getNamaPencetak2() {
        return namaPencetak2;
    }

    public List<PenggunaPeranan> getSenaraiPeranan() {
        return senaraiPeranan;
    }

    public void setSenaraiPeranan(List<PenggunaPeranan> senaraiPeranan) {
        this.senaraiPeranan = senaraiPeranan;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    /**
     * Sekiranya pengguna adalah pengguna ptg
     *
     * @return true - pengguna ptg, false - tidak
     */
    public boolean isPenggunaPTG() {
        boolean ptg = true;
        if (kodCawangan.getDaerah() != null) {
            ptg = false;
        }
        return ptg;
    }

    public KodJawatan getKodJawatan() {
        return kodJawatan;
    }

    public void setKodJawatan(KodJawatan kodJawatan) {
        this.kodJawatan = kodJawatan;
    }

    public String getPenyeliaFlag() {
        return penyeliaFlag;
    }

    public void setPenyeliaFlag(String penyeliaFlag) {
        this.penyeliaFlag = penyeliaFlag;
    }

    public Date getTarikhKemaskiniKatalaluan() {
        return tarikhKemaskiniKatalaluan;
    }

    public void setTarikhKemaskiniKatalaluan(Date tarikhKemaskiniKatalaluan) {
        this.tarikhKemaskiniKatalaluan = tarikhKemaskiniKatalaluan;
    }

    public String getPelulusBayaranKurang() {
        return pelulusBayaranKurang;
    }

    public void setPelulusBayaranKurang(String pelulusBayaranKurang) {
        this.pelulusBayaranKurang = pelulusBayaranKurang;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
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

    public Pengguna getPenyelia() {
        return penyelia;
    }

    public void setPenyelia(Pengguna penyelia) {
        this.penyelia = penyelia;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getKadKuasa() {
        return kadKuasa;
    }

    public void setKadKuasa(String kadKuasa) {
        this.kadKuasa = kadKuasa;
    }

    public Blob getImejTT() {
        return imejTT;
    }

    public void setImejTT(Blob imejTT) {
        this.imejTT = imejTT;
    }

    public String getDimasukKlaluan() {
        return dimasukKlaluan;
    }

    public void setDimasukKlaluan(String dimasukKlaluan) {
        this.dimasukKlaluan = dimasukKlaluan;
    }
    

  
}
