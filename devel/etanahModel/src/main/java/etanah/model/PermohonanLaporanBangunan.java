package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table(name = "mohon_lapor_bngn")
@SequenceGenerator(name = "seq_mohon_lapor_bngn", sequenceName = "seq_mohon_lapor_bngn")
public class PermohonanLaporanBangunan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_bngn")
    @Column(name = "id_lapor_bngn")
    private long idLaporBangunan;
    
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    
    @ManyToOne
    @JoinColumn (name = "id_lapor")
    private LaporanTanah laporanTanah;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mp")
    private PermohonanPihak permohonanPihak;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mpt")
    private PermohonanPihakTidakBerkepentingan permohonanPihakTidakBerkepentingan;
    
    @Column(name = "kategori")
    private String kategori;

    @Column(name = "jenis_bngn")
    private String jenisBangunan;

    @Column(name = "keterangan_thn_binaan")
    private String keteranganTahunBinaan;

    @Column(name = "ukuran")
    private BigDecimal ukuran;
    
    
    @ManyToOne
    @JoinColumn(name = "ukuran_uom")
    private KodUOM uomUkuran;
    
    @ManyToOne
    @JoinColumn(name = "ukuran_lebar_uom")
    private KodUOM ukuranLebarUom;
    
    @ManyToOne
    @JoinColumn(name = "ukuran_panjang_uom")
    private KodUOM ukuranPanjangUom;
    
    @Column(name = "nilai")
    private BigDecimal nilai;

    @Column(name = "nilai_keputusan")
    private BigDecimal nilaiKeputusan;
    
    @Column(name = "keadaan_bngn")
    private String keadaanBangunan;
    
        @Column(name = "ktrgn_jns_bngn")
    private String keteranganJenisBngn;

    public BigDecimal getNilaiKeputusan() {
        return nilaiKeputusan;
    }

    public void setNilaiKeputusan(BigDecimal nilaiKeputusan) {
        this.nilaiKeputusan = nilaiKeputusan;
    }



    @Column(name = "thn_bina")
    private Integer tahunDibina;

    @Column(name = "nama_pemunya")
    private String namaPemunya;

    @Column(name = "nama_ketua")
    private String namaKetua;

    @Column(name = "nama_penyewa")
    private String namaPenyewa;

    @ManyToOne
    @JoinColumn (name = "jenis_penduduk")
    private KodJenisPendudukan jenisPendudukan;

    @Embedded
    private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

    public void setLaporanTanah(LaporanTanah laporanTanah) {
		this.laporanTanah = laporanTanah;
	}

	public LaporanTanah getLaporanTanah() {
		return laporanTanah;
	}

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

	public long getIdLaporBangunan() {
        return idLaporBangunan;
    }

    public void setIdLaporBangunan(long idLaporBangunan) {
        this.idLaporBangunan = idLaporBangunan;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    public String getKeteranganTahunBinaan() {
        return keteranganTahunBinaan;
    }

    public void setKeteranganTahunBinaan(String keteranganTahunBinaan) {
        this.keteranganTahunBinaan = keteranganTahunBinaan;
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public void setJenisPendudukan(KodJenisPendudukan jenisPendudukan) {
		this.jenisPendudukan = jenisPendudukan;
	}

	public KodJenisPendudukan getJenisPendudukan() {
		return jenisPendudukan;
	}

	public String getNamaPemunya() {
        return namaPemunya;
    }

    public void setNamaPemunya(String namaPemunya) {
        this.namaPemunya = namaPemunya;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public Integer getTahunDibina() {
        return tahunDibina;
    }

    public void setTahunDibina(Integer tahunDibina) {
        this.tahunDibina = tahunDibina;
    }

    public BigDecimal getUkuran() {
        return ukuran;
    }

    public void setUkuran(BigDecimal ukuran) {
        this.ukuran = ukuran;
    }

    public KodUOM getUomUkuran() {
        return uomUkuran;
    }

    public void setUomUkuran(KodUOM uomUkuran) {
        this.uomUkuran = uomUkuran;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanPihakTidakBerkepentingan getPermohonanPihakTidakBerkepentingan() {
        return permohonanPihakTidakBerkepentingan;
    }

    public void setPermohonanPihakTidakBerkepentingan(PermohonanPihakTidakBerkepentingan permohonanPihakTidakBerkepentingan) {
        this.permohonanPihakTidakBerkepentingan = permohonanPihakTidakBerkepentingan;
    }

    public KodUOM getUkuranLebarUom() {
        return ukuranLebarUom;
    }

    public void setUkuranLebarUom(KodUOM ukuranLebarUom) {
        this.ukuranLebarUom = ukuranLebarUom;
    }

    public KodUOM getUkuranPanjangUom() {
        return ukuranPanjangUom;
    }

    public void setUkuranPanjangUom(KodUOM ukuranPanjangUom) {
        this.ukuranPanjangUom = ukuranPanjangUom;
    }

    
    
    
    
	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getKeadaanBangunan() {
        return keadaanBangunan;
    }

    public void setKeadaanBangunan(String keadaanBangunan) {
        this.keadaanBangunan = keadaanBangunan;
    }

    public String getKeteranganJenisBngn() {
        return keteranganJenisBngn;
    }

    public void setKeteranganJenisBngn(String keteranganJenisBngn) {
        this.keteranganJenisBngn = keteranganJenisBngn;
    }
    
    
}
