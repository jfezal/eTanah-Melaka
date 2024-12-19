package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import javax.persistence.ManyToOne;

@Entity
@Table(name = "pemohon")
@SequenceGenerator(name = "seq_pemohon", sequenceName = "seq_pemohon")
public class Pemohon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pemohon")
    @Column(name = "id_pemohon")
    private long idPemohon;

    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pihak")
    private Pihak pihak;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pihak_caw", nullable = true)
    private PihakCawangan pihakCawangan;

    @ManyToOne
    @JoinColumn(name = "kod_pihak")
    private KodJenisPihakBerkepentingan jenis;

    @ManyToOne
    @JoinColumn(name = "kod_matawang")
    private KodMatawang matawang;

    @Column(name = "kod_pemohon")
    private String jenisPemohon;

    @Column(name = "umur", nullable = true)
    private Integer umur;

    @Column(name = "kerja")
    private String pekerjaan;

    @Column(name = "gaji")
    private BigDecimal pendapatan;

    @Column(name = "gaji2")
    private BigDecimal pendapatanLain;

    @Column(name = "sts_kahwin")
    private String statusKahwin;

    @Column(name = "kaitan", length = 50)
    private String kaitan;

    @Column(name = "tanggung")
    private Integer tanggungan;

    @Column(name = "tempoh_mastautin")
    private Integer tempohMastautin;

    @Column(name = "ranc_kjaan")
    private String rancanganKerajaan;

    // maklumat majikan
    @Column(name = "inst")
    private String institusi;

    @Column(name = "inst_alamat1")
    private String institusiAlamat1;

    @Column(name = "inst_alamat2")
    private String institusiAlamat2;

    @Column(name = "inst_alamat3")
    private String institusiAlamat3;

    @Column(name = "inst_alamat4")
    private String institusiAlamat4;

    @Column(name = "inst_poskod")
    private String institusiPoskod;
    
    @Column(name = "jenis_pemohon")
    private String pemohonJenis;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @ManyToOne
    @JoinColumn(name = "inst_kod_negeri")
    private KodNegeri institusiNegeri;

    @OneToMany(mappedBy = "pemohon", fetch = FetchType.LAZY)
    private List<PemohonHubungan> senaraiHubungan;

    @OneToMany(mappedBy = "pemohon", fetch = FetchType.LAZY)
    private List<PemohonTanah> senaraiTanah;

    @Column(name = "syer_pembilang", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPembilang;

    @Column(name = "syer_penyebut", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPenyebut;

    @Column(name = "nama", length = 250)
    private String nama;
    @Column(name = "no_pengenalan", length = 20)
    private String noPengenalan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOD_PENGENALAN")
    private KodJenisPengenalan jenisPengenalan;

    @ManyToOne
    @JoinColumn(name = "kod_warganegara")
    private KodWarganegara wargaNegara;

    @Column(name = "id_mohon_lama")
    private String idPermohonanLama;

     @ManyToOne
    @JoinColumn(name = "kod_jenis_pemohon")
    private KodJnsPemohon kodJnsPemohon;
     
    @Embedded
    private Alamat alamat;

    @ManyToOne
    @JoinColumn(name = "kod_sykt_tubuh")
    private KodPenubuhanSyarikat penubuhanSyarikat;

    @Column(name = "JUM_PENYEBUT", precision = 22, scale = 0, columnDefinition = "number(22,0)")
    private Integer jumlahPenyebut;

    @Column(name = "JUM_PEMBILANG", precision = 22, scale = 0, columnDefinition = "number(22,0)")
    private Integer jumlahPembilang;

    @Column(name = "d_nilai1")
    private String dalamanNilai1;

    @Column(name = "d_nilai2")
    private String dalamanNilai2;

    @ManyToOne
    @JoinColumn(name = "id_hp")
    private HakmilikPihakBerkepentingan hakmilikPihak;

    @Column(name = "kod_status", length = 250)
    private String kodStatus;

    @Embedded
    private AlamatSurat alamatSurat;

    @Embedded
    private InfoAudit infoAudit;

    @Column(name = "flag_surat")
    private Character surat;

    public long getIdPemohon() {
        return idPemohon;
    }

    public void setIdPemohon(long idPemohon) {
        this.idPemohon = idPemohon;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public void setPihakCawangan(PihakCawangan cawangan) {
        this.pihakCawangan = cawangan;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public BigDecimal getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(BigDecimal pendapatan) {
        this.pendapatan = pendapatan;
    }

    public BigDecimal getPendapatanLain() {
        return pendapatanLain;
    }

    public void setPendapatanLain(BigDecimal pendapatanLain) {
        this.pendapatanLain = pendapatanLain;
    }

    public String getStatusKahwin() {
        return statusKahwin;
    }

    public void setStatusKahwin(String statusKahwin) {
        this.statusKahwin = statusKahwin;
    }

    public String getKaitan() {
        return kaitan;
    }

    public void setKaitan(String kaitan) {
        this.kaitan = kaitan;
    }

    public Integer getTanggungan() {
        return tanggungan;
    }

    public void setTanggungan(Integer tanggungan) {
        this.tanggungan = tanggungan;
    }

    public Integer getTempohMastautin() {
        return tempohMastautin;
    }

    public void setTempohMastautin(Integer tempohMastautin) {
        this.tempohMastautin = tempohMastautin;
    }

    public String getRancanganKerajaan() {
        return rancanganKerajaan;
    }

    public void setRancanganKerajaan(String rancanganKerajaan) {
        this.rancanganKerajaan = rancanganKerajaan;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getInstitusiAlamat1() {
        return institusiAlamat1;
    }

    public void setInstitusiAlamat1(String institusiAlamat1) {
        this.institusiAlamat1 = institusiAlamat1;
    }

    public String getInstitusiAlamat2() {
        return institusiAlamat2;
    }

    public void setInstitusiAlamat2(String institusiAlamat2) {
        this.institusiAlamat2 = institusiAlamat2;
    }

    public String getInstitusiAlamat3() {
        return institusiAlamat3;
    }

    public void setInstitusiAlamat3(String institusiAlamat3) {
        this.institusiAlamat3 = institusiAlamat3;
    }

    public String getInstitusiAlamat4() {
        return institusiAlamat4;
    }

    public void setInstitusiAlamat4(String institusiAlamat4) {
        this.institusiAlamat4 = institusiAlamat4;
    }

    public String getInstitusiPoskod() {
        return institusiPoskod;
    }

    public void setInstitusiPoskod(String institusiPoskod) {
        this.institusiPoskod = institusiPoskod;
    }

    public KodNegeri getInstitusiNegeri() {
        return institusiNegeri;
    }

    public void setInstitusiNegeri(KodNegeri institusiNegeri) {
        this.institusiNegeri = institusiNegeri;
    }

    public void setSenaraiHubungan(List<PemohonHubungan> senaraiHubungan) {
        this.senaraiHubungan = senaraiHubungan;
    }

    public List<PemohonHubungan> getSenaraiHubungan() {
        return senaraiHubungan;
    }

    public void setSenaraiTanah(List<PemohonTanah> senaraiTanah) {
        this.senaraiTanah = senaraiTanah;
    }

    public List<PemohonTanah> getSenaraiTanah() {
        return senaraiTanah;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodJenisPihakBerkepentingan getJenis() {
        return jenis;
    }

    public void setJenis(KodJenisPihakBerkepentingan jenis) {
        this.jenis = jenis;
    }

    public String getJenisPemohon() {
        return jenisPemohon;
    }

    public void setJenisPemohon(String jenisPemohon) {
        this.jenisPemohon = jenisPemohon;
    }

    public Integer getSyerPembilang() {
        return syerPembilang;
    }

    public void setSyerPembilang(Integer syerPembilang) {
        this.syerPembilang = syerPembilang;
    }

    public Integer getSyerPenyebut() {
        return syerPenyebut;
    }

    public void setSyerPenyebut(Integer syerPenyebut) {
        this.syerPenyebut = syerPenyebut;
    }

    public KodMatawang getMatawang() {
        return matawang;
    }

    public void setMatawang(KodMatawang matawang) {
        this.matawang = matawang;
    }

    public Character getSurat() {
        return surat;
    }

    public void setSurat(Character surat) {
        this.surat = surat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public KodWarganegara getWargaNegara() {
        return wargaNegara;
    }

    public void setWargaNegara(KodWarganegara wargaNegara) {
        this.wargaNegara = wargaNegara;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public KodPenubuhanSyarikat getPenubuhanSyarikat() {
        return penubuhanSyarikat;
    }

    public void setPenubuhanSyarikat(KodPenubuhanSyarikat penubuhanSyarikat) {
        this.penubuhanSyarikat = penubuhanSyarikat;
    }

    public Integer getJumlahPenyebut() {
        return jumlahPenyebut;
    }

    public void setJumlahPenyebut(Integer jumlahPenyebut) {
        this.jumlahPenyebut = jumlahPenyebut;
    }

    public Integer getJumlahPembilang() {
        return jumlahPembilang;
    }

    public void setJumlahPembilang(Integer jumlahPembilang) {
        this.jumlahPembilang = jumlahPembilang;
    }

    public AlamatSurat getAlamatSurat() {
        return alamatSurat;
    }

    public void setAlamatSurat(AlamatSurat alamatSurat) {
        this.alamatSurat = alamatSurat;
    }

    public String getDalamanNilai1() {
        return dalamanNilai1;
    }

    public void setDalamanNilai1(String dalamanNilai1) {
        this.dalamanNilai1 = dalamanNilai1;
    }

    public String getDalamanNilai2() {
        return dalamanNilai2;
    }

    public void setDalamanNilai2(String dalamanNilai2) {
        this.dalamanNilai2 = dalamanNilai2;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihak;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihak = hakmilikPihak;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public String getIdPermohonanLama() {
        return idPermohonanLama;
    }

    public void setIdPermohonanLama(String idPermohonanLama) {
        this.idPermohonanLama = idPermohonanLama;
    }

    public KodJnsPemohon getKodJnsPemohon() {
        return kodJnsPemohon;
    }

    public void setKodJnsPemohon(KodJnsPemohon kodJnsPemohon) {
        this.kodJnsPemohon = kodJnsPemohon;
    }

    public String getPemohonJenis() {
        return pemohonJenis;
    }

    public void setPemohonJenis(String pemohonJenis) {
        this.pemohonJenis = pemohonJenis;
    }
    

}
