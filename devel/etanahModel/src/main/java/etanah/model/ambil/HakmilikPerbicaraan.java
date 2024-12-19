package etanah.model.ambil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.PermohonanPihakWakil;

@Entity
@Table(name = "ambil_hakmilik_bicara")
@SequenceGenerator(name = "seq_ambil_hakmilik_bicara", sequenceName = "seq_ambil_hakmilik_bicara")
public class HakmilikPerbicaraan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ambil_hakmilik_bicara")
    @Column(name = "id_bicara")
    private long idPerbicaraan;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mh", nullable = false)
    private HakmilikPermohonan hakmilikPermohonan;
    @Column(name = "no_ruj")
    public String noRujukan;
    @Column(name = "trh_bicara")
    private Date tarikhBicara;
    @Column(name = "tmpt_bicara")
    private String lokasiBicara;
    @Column(name = "penilai_ulasan")
    private String ulasanPenilai;
    @ManyToOne
    @JoinColumn(name = "dibicara")
    private Pengguna dibicaraOleh;
    @Column(name = "tempoh_tulis")
    private Integer tempohKeteranganBertulis;
    @Column(name = "trh_surat_penilai")
    private Date tarikhSuratPenilai;
    // siasatan tanah
    @Column(name = "trh_milik")
    private Date tarikhPemilikan;
    @Column(name = "cara_milik")
    private String caraPemilikan;
    @Column(name = "harga")
    private BigDecimal hargaPembelian;
    @Column(name = "lokasi")
    private String lokasi;
    /**
     * Jarak ke bandar/pekan dalam KM.
     */
    @Column(name = "jarak_bandar")
    private BigDecimal jarakKeBandar;
    @Column(name = "keadaan_tanah")
    private String keadaanTanah;
    @Column(name = "tanaman")
    private String tanaman;
    @Column(name = "bngn")
    private String bangunan;
    @Column(name = "amaun_tuntut")
    private BigDecimal amaunDituntut;
    @Column(name = "alasan_tuntut")
    private String alasanTuntutan;
    @Column(name = "pemohon_ulasan")
    private String pemohonUlasan;
    @Column(name = "penilai_nama")
    private String penilaiNama;
    @Column(name = "penilai_no_ruj")
    private String penilaiNoRujukan;
    @Column(name = "catatan")
    private String catatan;
    @Column(name = "kod_kpsn")
    private String kodkpsn;
    @Column(name = "nilai_kpsn")
    private BigDecimal keputusanNilai;
    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUOM;
    @OneToMany(mappedBy = "perbicaraan", fetch = FetchType.LAZY)
    private List<PerbicaraanKehadiran> senaraiKehadiran;
    @Column(name = "tempoh_kosong")
    private Integer tempohPengosongan;
    @Column(name = "tempoh_serah")
    private Integer tempohPenyerahan;

    @Column(name = "pecah_syrt")
    private String pecahSyarat;

    @Column(name = "trh_pecah_syrt")
    private Date tarikhPecahSyarat;
    @Column(name = "lokasi_terkini")
    private String lokasiTerkini;
    @Column(name = "ujud_GPPJ")
    private String ujudGPPJ;
    @Column(name = "komen_GPPJ")
    private String komenGPPJ;

    @Column(name = "nilaian_penilai")
    private BigDecimal nilaianPenilai;
    @Column(name = "bantahan_penilai")
    private String bantahanPenilai;
    @Column(name = "akujanji_penilai")
    private String akujanjiPenilai;
    @Column(name = "sbb_tangguh")
    private String sebabTangguh;

    // flag
    @Column(name = "batal_rizab")
    private char batalRizab;
    @Column(name = "kws_pbt")
    private char kawasanPBT;
    @Column(name = "pelan_bangun")
    private char pelanPembangunan;
    @Column(name = "jenis_bangun", length = 1000)
    private String jenisPembangunan;

    @Column(name = "keterangan_tuanpunya")
    private String keteranganTuanpunya;

    @Column(name = "keterangan_ppp")
    private String keteranganPPP;

    @Column(name = "keterangan_gkl")
    private String keteranganGKL;

    @Column(name = "keterangan_lain")
    private String keteranganLain;

    @Column(name = "trh_ulangan")
    private Date tarikhUlangan;

    @Column(name = "aktif")
    private String aktif;

    @Column(name = "keterangan_penuntut")
    private String keteranganPenuntut;

    @OneToMany(mappedBy = "hakmilikPerbicaraan", fetch = FetchType.LAZY)
    private List<PermohonanPihakWakil> senaraiWakilPermohonanPihak;

    @Column(name = "lokasi_ulangan")
    private String lokasiUlangan;

    @Column(name = "perintah_ptd")
    private String perintahPtd;

    @Column(name = "nilai_hutang")
    private BigDecimal nilaiHutang;

    @Column(name = "jenis_hutang")
    private String jenisHutang;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdPerbicaraan() {
        return idPerbicaraan;
    }

    public void setIdPerbicaraan(long idPerbicaraan) {
        this.idPerbicaraan = idPerbicaraan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Date getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(Date tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public String getLokasiBicara() {
        return lokasiBicara;
    }

    public void setLokasiBicara(String lokasiBicara) {
        this.lokasiBicara = lokasiBicara;
    }

    public Pengguna getDibicaraOleh() {
        return dibicaraOleh;
    }

    public void setDibicaraOleh(Pengguna dibicaraOleh) {
        this.dibicaraOleh = dibicaraOleh;
    }

    public void setTempohKeteranganBertulis(Integer tempohKeteranganBertulis) {
        this.tempohKeteranganBertulis = tempohKeteranganBertulis;
    }

    public Integer getTempohKeteranganBertulis() {
        return tempohKeteranganBertulis;
    }

    public Date getTarikhSuratPenilai() {
        return tarikhSuratPenilai;
    }

    public void setTarikhSuratPenilai(Date tarikhSuratPenilai) {
        this.tarikhSuratPenilai = tarikhSuratPenilai;
    }

    public Date getTarikhPemilikan() {
        return tarikhPemilikan;
    }

    public void setTarikhPemilikan(Date tarikhPemilikan) {
        this.tarikhPemilikan = tarikhPemilikan;
    }

    public String getCaraPemilikan() {
        return caraPemilikan;
    }

    public void setCaraPemilikan(String caraPemilikan) {
        this.caraPemilikan = caraPemilikan;
    }

    public BigDecimal getHargaPembelian() {
        return hargaPembelian;
    }

    public void setHargaPembelian(BigDecimal hargaPembelian) {
        this.hargaPembelian = hargaPembelian;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public BigDecimal getJarakKeBandar() {
        return jarakKeBandar;
    }

    public void setJarakKeBandar(BigDecimal jarakKeBandar) {
        this.jarakKeBandar = jarakKeBandar;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getTanaman() {
        return tanaman;
    }

    public void setTanaman(String tanaman) {
        this.tanaman = tanaman;
    }

    public String getBangunan() {
        return bangunan;
    }

    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }

    public BigDecimal getAmaunDituntut() {
        return amaunDituntut;
    }

    public void setAmaunDituntut(BigDecimal amaunDituntut) {
        this.amaunDituntut = amaunDituntut;
    }

    public String getAlasanTuntutan() {
        return alasanTuntutan;
    }

    public void setAlasanTuntutan(String alasanTuntutan) {
        this.alasanTuntutan = alasanTuntutan;
    }

    public String getPemohonUlasan() {
        return pemohonUlasan;
    }

    public void setPemohonUlasan(String ulasanPemohon) {
        this.pemohonUlasan = ulasanPemohon;
    }

    public void setPenilaiNama(String penilaiNama) {
        this.penilaiNama = penilaiNama;
    }

    public String getPenilaiNama() {
        return penilaiNama;
    }

    public String getPenilaiNoRujukan() {
        return penilaiNoRujukan;
    }

    public void setPenilaiNoRujukan(String penilaiNoRujukan) {
        this.penilaiNoRujukan = penilaiNoRujukan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public BigDecimal getKeputusanNilai() {
        return keputusanNilai;
    }

    public void setKeputusanNilai(BigDecimal keputusanNilai) {
        this.keputusanNilai = keputusanNilai;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public void setSenaraiKehadiran(List<PerbicaraanKehadiran> senaraiKehadiran) {
        this.senaraiKehadiran = senaraiKehadiran;
    }

    public List<PerbicaraanKehadiran> getSenaraiKehadiran() {
        return senaraiKehadiran;
    }

    public Integer getTempohPengosongan() {
        return tempohPengosongan;
    }

    public void setTempohPengosongan(Integer tempohPengosongan) {
        this.tempohPengosongan = tempohPengosongan;
    }

    public Integer getTempohPenyerahan() {
        return tempohPenyerahan;
    }

    public void setTempohPenyerahan(Integer tempohPenyerahan) {
        this.tempohPenyerahan = tempohPenyerahan;
    }

    public String getAkujanjiPenilai() {
        return akujanjiPenilai;
    }

    public void setAkujanjiPenilai(String akujanjiPenilai) {
        this.akujanjiPenilai = akujanjiPenilai;
    }

    public String getBantahanPenilai() {
        return bantahanPenilai;
    }

    public void setBantahanPenilai(String bantahanPenilai) {
        this.bantahanPenilai = bantahanPenilai;
    }

    public String getKomenGPPJ() {
        return komenGPPJ;
    }

    public void setKomenGPPJ(String komenGPPJ) {
        this.komenGPPJ = komenGPPJ;
    }

    public String getLokasiTerkini() {
        return lokasiTerkini;
    }

    public void setLokasiTerkini(String lokasiTerkini) {
        this.lokasiTerkini = lokasiTerkini;
    }

    public BigDecimal getNilaianPenilai() {
        return nilaianPenilai;
    }

    public void setNilaianPenilai(BigDecimal nilaianPenilai) {
        this.nilaianPenilai = nilaianPenilai;
    }

    public String getPecahSyarat() {
        return pecahSyarat;
    }

    public void setPecahSyarat(String pecahSyarat) {
        this.pecahSyarat = pecahSyarat;
    }

    public String getSebabTangguh() {
        return sebabTangguh;
    }

    public void setSebabTangguh(String sebabTangguh) {
        this.sebabTangguh = sebabTangguh;
    }

    public String getKeteranganGKL() {
        return keteranganGKL;
    }

    public void setKeteranganGKL(String keteranganGKL) {
        this.keteranganGKL = keteranganGKL;
    }

    public String getKeteranganLain() {
        return keteranganLain;
    }

    public void setKeteranganLain(String keteranganLain) {
        this.keteranganLain = keteranganLain;
    }

    public Date getTarikhUlangan() {
        return tarikhUlangan;
    }

    public void setTarikhUlangan(Date tarikhUlangan) {
        this.tarikhUlangan = tarikhUlangan;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getKeteranganPenuntut() {
        return keteranganPenuntut;
    }

    public void setKeteranganPenuntut(String keteranganPenuntut) {
        this.keteranganPenuntut = keteranganPenuntut;
    }

    public String getKeteranganPPP() {
        return keteranganPPP;
    }

    public void setKeteranganPPP(String keteranganPPP) {
        this.keteranganPPP = keteranganPPP;
    }

    public String getKeteranganTuanpunya() {
        return keteranganTuanpunya;
    }

    public void setKeteranganTuanpunya(String keteranganTuanpunya) {
        this.keteranganTuanpunya = keteranganTuanpunya;
    }

    public Date getTarikhPecahSyarat() {
        return tarikhPecahSyarat;
    }

    public void setTarikhPecahSyarat(Date tarikhPecahSyarat) {
        this.tarikhPecahSyarat = tarikhPecahSyarat;
    }

    public String getUjudGPPJ() {
        return ujudGPPJ;
    }

    public void setUjudGPPJ(String ujudGPPJ) {
        this.ujudGPPJ = ujudGPPJ;
    }

    public String getUlasanPenilai() {
        return ulasanPenilai;
    }

    public void setUlasanPenilai(String ulasanPenilai) {
        this.ulasanPenilai = ulasanPenilai;
    }

    public char getBatalRizab() {
        return batalRizab;
    }

    public void setBatalRizab(char batalRizab) {
        this.batalRizab = batalRizab;
    }

    public String getJenisPembangunan() {
        return jenisPembangunan;
    }

    public void setJenisPembangunan(String jenisPembangunan) {
        this.jenisPembangunan = jenisPembangunan;
    }

    public char getKawasanPBT() {
        return kawasanPBT;
    }

    public void setKawasanPBT(char kawasanPBT) {
        this.kawasanPBT = kawasanPBT;
    }

    public char getPelanPembangunan() {
        return pelanPembangunan;
    }

    public void setPelanPembangunan(char pelanPembangunan) {
        this.pelanPembangunan = pelanPembangunan;
    }

    public List<PermohonanPihakWakil> getSenaraiWakilPermohonanPihak() {
        return senaraiWakilPermohonanPihak;
    }

    public void setSenaraiWakilPermohonanPihak(List<PermohonanPihakWakil> senaraiWakilPermohonanPihak) {
        this.senaraiWakilPermohonanPihak = senaraiWakilPermohonanPihak;
    }

    public String getLokasiUlangan() {
        return lokasiUlangan;
    }

    public void setLokasiUlangan(String lokasiUlangan) {
        this.lokasiUlangan = lokasiUlangan;
    }

    public String getPerintahPtd() {
        return perintahPtd;
    }

    public void setPerintahPtd(String perintahPtd) {
        this.perintahPtd = perintahPtd;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public BigDecimal getNilaiHutang() {
        return nilaiHutang;
    }

    public void setNilaiHutang(BigDecimal nilaiHutang) {
        this.nilaiHutang = nilaiHutang;
    }

    public String getJenisHutang() {
        return jenisHutang;
    }

    public void setJenisHutang(String jenisHutang) {
        this.jenisHutang = jenisHutang;
    }

    public String getKodkpsn() {
        return kodkpsn;
    }

    public void setKodkpsn(String kodkpsn) {
        this.kodkpsn = kodkpsn;
    }

   
    
    
}
