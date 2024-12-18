package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_ruj_luar")
@SequenceGenerator(name = "seq_mohon_ruj_luar", sequenceName = "seq_mohon_ruj_luar")
public class PermohonanRujukanLuar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_ruj_luar")
    @Column(name = "id_ruj")
    private long idRujukan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "kod_perintah", nullable = true)
    private KodPerintah kodPerintah;
    @ManyToOne
    @JoinColumn(name = "kod_agensi", nullable = true)
    private KodAgensi agensi;
    
    @Column(name = "nama_masuk")
    private String namaMasuk;
    
    @Column (name = "nama_agensi")
    private String namaAgensi;
    
    @Column (name = "lokasi_agensi")
    private String lokasiAgensi;
    
    /*
     * if it's internal jabatan
     */
    @ManyToOne
    @JoinColumn (name = "kod_jabatan")
    private KodJabatan jabatan;
    
    @ManyToOne
    @JoinColumn (name = "kod_ruj", nullable = true)
    private KodRujukan kodRujukan;
    
    @Column(name = "nama_sidang", length = 50, nullable = true)
    private String namaSidang;
    @Column(name = "no_sidang")
    private String noSidang;
    @Column(name = "trh_sidang")
    private Date tarikhSidang;
    @Column(name = "no_fail")
    private String noFail;
    @Column(name = "no_ruj", length = 20, nullable = true)
    private String noRujukan;
    @Column(name = "trh_ruj")
    private Date tarikhRujukan;
    @Column (name = "trh_lulus")
    private Date tarikhLulus;
    @Column(name = "trh_kkuasa")
    private Date tarikhKuatkuasa;
    @Column(name = "trh_tamat")
    private Date tarikhTamat;
    @Column (name = "trh_sampai")
    private Date tarikhDisampai;
    
    /*
     * Pelupusan: Ulasan jabatan teknikal
     */
    @Column (name = "trh_jangka_terima")
    private Date tarikhJangkaTerima;
    @Column (name = "nama_penyedia")
    private String namaPenyedia;
    
    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;

    @Column (name = "tempoh_thn")
	private Integer tempohTahun;

	@Column (name = "tempoh_bln")
	private Integer tempohBulan;

	@Column (name = "tempoh_hari")
	private Integer tempohHari;
	
	@Column (name = "trh_terima")
	private Date tarikhTerima;
	
	@Column (name = "status_ulasan")
	private Character statusUlasan;
	
	@Column (name = "item")
	private String item;
	
	/**
	 * For amount of money, keluasan & other numerical value
	 */
	@Column (name = "nilai")
	private BigDecimal nilai;
        
        @Column (name = "nilai2")
	private BigDecimal nilai2;

	@OneToMany (mappedBy  = "rujukan")
	private List<PermohonanRujukanLuarPeranan> senaraiPeranan;

	@OneToMany (mappedBy  = "permohonanRujukanLuar")
	private List<PermohonanRujukanLuarSalinan>  senaraiSalinan;
    /**
     * Attachment of document
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen")
    private Dokumen dokumen;
    /**
     * Agensi tersebut menyokong (Y) atau tidak (T)
     */
    @Column(name = "sokong", length = 1, nullable = true)
    private Character diSokong;
    @Column(name = "ulasan", length = 4000)
    private String ulasan;
    @Column(name = "catatan", length = 4000)
    private String catatan;


    @Column(name = "ulasan_mandatori")
    private String ulasanMandatori;

    @Column(name = "kemampuan_pemohon")
    private String kemampuanPemohon;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_caw_ruj")
    private KodCawangan kodCawanganDirujuk;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_dakwa")
    private KodKeputusanPendakwaan keputusanPendakwaan;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(long idRujukan) {
        this.idRujukan = idRujukan;
    }

    public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

    public KodCawangan getCawangan() {
        return cawangan;
    }

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_dokumen_ruj")
	private KodDokumen kodDokumenRujukan;
	
	public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public void setKodPerintah(KodPerintah kodPerintah) {
        this.kodPerintah = kodPerintah;
    }

    public KodPerintah getKodPerintah() {
        return kodPerintah;
    }

    public KodAgensi getAgensi() {
        return agensi;
    }

    public void setAgensi(KodAgensi agensi) {
        this.agensi = agensi;
    }

    public void setNamaAgensi(String namaAgensi) {
		this.namaAgensi = namaAgensi;
	}

	public String getNamaAgensi() {
		return namaAgensi;
	}

	public void setLokasiAgensi(String lokasiAgensi) {
		this.lokasiAgensi = lokasiAgensi;
	}

	public String getLokasiAgensi() {
		return lokasiAgensi;
	}

	public void setJabatan(KodJabatan jabatan) {
		this.jabatan = jabatan;
	}

	public KodJabatan getJabatan() {
		return jabatan;
	}

	public void setKodRujukan(KodRujukan kodRujukan) {
		this.kodRujukan = kodRujukan;
	}

	public KodRujukan getKodRujukan() {
		return kodRujukan;
	}

	public void setNamaSidang(String namaSidang) {
        this.namaSidang = namaSidang;
    }

    public String getNamaSidang() {
        return namaSidang;
    }

    public String getNoSidang() {
        return noSidang;
    }

    public void setNoSidang(String noSidang) {
        this.noSidang = noSidang;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getNoFail() {
        return noFail;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public void setTarikhRujukan(Date tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public void setTarikhLulus(Date tarikhLulus) {
		this.tarikhLulus = tarikhLulus;
	}

	public Date getTarikhLulus() {
		return tarikhLulus;
	}

	public void setTarikhKuatkuasa(Date tarikhKuatkuasa) {
        this.tarikhKuatkuasa = tarikhKuatkuasa;
    }

    public Date getTarikhKuatkuasa() {
        return tarikhKuatkuasa;
    }

    public void setTarikhDisampai(Date tarikhDisampai) {
		this.tarikhDisampai = tarikhDisampai;
	}

	public Date getTarikhDisampai() {
		return tarikhDisampai;
	}

	public void setSenaraiPeranan(List<PermohonanRujukanLuarPeranan> senaraiPeranan) {
		this.senaraiPeranan = senaraiPeranan;
	}

	public List<PermohonanRujukanLuarPeranan> getSenaraiPeranan() {
		return senaraiPeranan;
	}

    public List<PermohonanRujukanLuarSalinan> getSenaraiSalinan() {
        return senaraiSalinan;
    }

    public void setSenaraiSalinan(List<PermohonanRujukanLuarSalinan> senaraiSalinan) {
        this.senaraiSalinan = senaraiSalinan;
    }

	public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }
	
	

    /**
     * Dokumen yang dilampirkan untuk rujukan ini.
     * @return
     */
    public Dokumen getDokumen() {
        return dokumen;
    }

    public Character getDiSokong() {
        return diSokong;
    }

    public void setDiSokong(Character diSokong) {
        this.diSokong = diSokong;
    }

    public String getUlasan() {
        return ulasan;
    }

	public void setKodDokumenRujukan(KodDokumen kodDokumenRujukan) {
		this.kodDokumenRujukan = kodDokumenRujukan;
	}

	public KodDokumen getKodDokumenRujukan() {
		return kodDokumenRujukan;
	}

	public Date getTarikhRujukan() {
		return tarikhRujukan;
	}

	public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getUlasanMandatori() {
        return ulasanMandatori;
    }

    public void setUlasanMandatori(String ulasanMandatori) {
        this.ulasanMandatori = ulasanMandatori;
    }

    public String getKemampuanPemohon() {
        return kemampuanPemohon;
    }

    public void setKemampuanPemohon(String kemampuanPemohon) {
        this.kemampuanPemohon = kemampuanPemohon;
    }

    public KodCawangan getKodCawanganDirujuk() {
        return kodCawanganDirujuk;
    }

    public void setKodCawanganDirujuk(KodCawangan kodCawanganDirujuk) {
        this.kodCawanganDirujuk = kodCawanganDirujuk;
    }

    


    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Integer getTempohBulan() {
        return tempohBulan;
    }

    public void setTempohBulan(int tempohBulan) {
        this.tempohBulan = tempohBulan;
    }

    public Integer getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(int tempohHari) {
        this.tempohHari = tempohHari;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(int tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public void setItem(String item) {
		this.item = item;
	}

	public String getItem() {
		return item;
	}

	public void setNilai(BigDecimal nilai) {
		this.nilai = nilai;
	}

	public BigDecimal getNilai() {
		return nilai;
	}

    public BigDecimal getNilai2() {
        return nilai2;
    }

    public void setNilai2(BigDecimal nilai2) {
        this.nilai2 = nilai2;
    }

	public Date getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(Date tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

	public void setTarikhJangkaTerima(Date tarikhJangkaTerima) {
		this.tarikhJangkaTerima = tarikhJangkaTerima;
	}

	public Date getTarikhJangkaTerima() {
		return tarikhJangkaTerima;
	}

	public void setNamaPenyedia(String namaPenyedia) {
		this.namaPenyedia = namaPenyedia;
	}

	public String getNamaPenyedia() {
		return namaPenyedia;
	}

	public void setKodUnitLuas(KodUOM kodUnitLuas) {
		this.kodUnitLuas = kodUnitLuas;
	}

	public KodUOM getKodUnitLuas() {
		return kodUnitLuas;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhTerima() {
		return tarikhTerima;
	}

	public void setStatusUlasan(Character statusUlasan) {
		this.statusUlasan = statusUlasan;
	}

	public Character getStatusUlasan() {
		return statusUlasan;
	}

    public KodKeputusanPendakwaan getKeputusanPendakwaan() {
        return keputusanPendakwaan;
    }

    public void setKeputusanPendakwaan(KodKeputusanPendakwaan keputusanPendakwaan) {
        this.keputusanPendakwaan = keputusanPendakwaan;
    }

    public String getNamaMasuk() {
        return namaMasuk;
    }

    public void setNamaMasuk(String namaMasuk) {
        this.namaMasuk = namaMasuk;
    }
    

}
