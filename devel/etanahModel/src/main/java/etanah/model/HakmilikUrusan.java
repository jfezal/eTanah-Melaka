package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "hakmilik_urusan")
@SequenceGenerator(name = "seq_hakmilik_urusan", sequenceName = "seq_hakmilik_urusan")
public class HakmilikUrusan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hakmilik_urusan")
    @Column(name = "id_urusan")
    private long idUrusan;
    @ManyToOne
    @JoinColumn(name = "kod_daerah", nullable = false)
    private KodDaerah daerah;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hakmilik", nullable = false)
    private Hakmilik hakmilik;
    @ManyToOne
    @JoinColumn(name = "kod_urusan", nullable = false)
    private KodUrusan kodUrusan;
    
    @Column (name = "id_mohon", length = 22)
    private String idPerserahan;
    @Column(name = "trh_daftar")
    private Date tarikhDaftar;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_folder")
    private FolderDokumen folderDokumen;
    
    @Column(name = "trh_mula")
    private Date tarikhMula;
    // default -1 (forever)
    @Column(name = "tempoh_thn")
    private Integer tempohTahun;
    // default -1 (forever)
    @Column(name = "tempoh_bln")
    private Integer tempohBulan;
    // default -1 (forever)
    @Column(name = "tempoh_hari")
    private Integer tempohHari;
    @Column(name = "trh_tamat")
    private Date tarikhTamat;
    
    /**
     * Y = aktif, T = tidak aktif (batal)
     */
    @Column(name = "aktif")
    private char aktif;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_urusan_batal")
    private HakmilikUrusan urusanBatal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_urusan_ruj")
    private HakmilikUrusan urusanRujukan;

    @Column (name = "id_mohon_batal", length = 22)
    private String idPermohonanBatal;
    @Column(name = "trh_batal")
    private Date tarikhBatal;
   
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_folder_batal")
    private FolderDokumen folderDokumenBatal;

	@Column (name = "luas")
	private BigDecimal luasTerlibat;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_uom")
	private KodUOM kodUnitLuas;
	
	// RUJUKAN LUAR
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_agensi", nullable = true)
	private KodAgensi agensiRujukan;

	@Column (name = "no_sidang")
	private String noSidang;
	
	@Column (name = "trh_sidang")
	private Date tarikhSidang;
	
	@Column (name = "no_fail")
	private String noFail;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_dokumen_ruj")
	private KodDokumen kodDokumenRujukan;
	
	@Column (name = "no_ruj")
	private String noRujukan;
	
	@Column (name = "trh_ruj")
	private Date tarikhRujukan;
	
	// PERUBAHAN PADA HAKMILIK
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_bpm_lama")
	private KodBandarPekanMukim bandarPekanMukimLama;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_bpm_baru")
	private KodBandarPekanMukim bandarPekanMukimBaru;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_katg_tanah_lama")
	private KodKategoriTanah kategoriTanahLama;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_katg_tanah_baru")
	private KodKategoriTanah kategoriTanahBaru;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_syarat_nyata_lama")
	private KodSyaratNyata syaratNyataLama;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_syarat_nyata_baru")
	private KodSyaratNyata syaratNyataBaru;
	  
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_sekatan_lama")
	private KodSekatanKepentingan sekatanKepentinganLama;
	  
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_sekatan_baru")
	private KodSekatanKepentingan sekatanKepentinganBaru;

	@Column (name = "cukai_lama")
	private BigDecimal cukaiLama;

	@Column (name = "cukai_baru")
	private BigDecimal cukaiBaru;
	
	@Column (name = "item")
	private String item;
	
	@Column (name = "catatan")
	private String catatan;
	
	@OneToMany (mappedBy = "urusan", fetch = FetchType.LAZY)
	private List<HakmilikUrusanSurat> senaraiSurat;
        
        @OneToMany (mappedBy = "perserahan", fetch = FetchType.LAZY)
	private List<HakmilikPihakBerkepentingan> senaraiPihakTerlibat;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn (name = "kod_hbgn_hakmilik", nullable = true)
        private KodPerhubunganHakmilik hubunganHakmilik;

    @Embedded
    private InfoAudit infoAudit;
	
    public long getIdUrusan() {
        return idUrusan;
    }

    public void setIdUrusan(long idUrusan) {
        this.idUrusan = idUrusan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

	public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }
    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

	public Date getTarikhMula() {
        return tarikhMula;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public Integer getTempohBulan() {
        return tempohBulan;
    }

    public void setTempohBulan(Integer tempohBulan) {
        this.tempohBulan = tempohBulan;
    }

    public Integer getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(Integer tempohHari) {
        this.tempohHari = tempohHari;
    }

    public void setTarikhTamat(Date tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }
    public Date getTarikhTamat() {
        return tarikhTamat;
    }

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public BigDecimal getCukaiLama() {
        return cukaiLama;
    }

    public void setCukaiLama(BigDecimal cukaiLama) {
        this.cukaiLama = cukaiLama;
    }

    public BigDecimal getCukaiBaru() {
        return cukaiBaru;
    }

    public void setCukaiBaru(BigDecimal cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public HakmilikUrusan getUrusanBatal() {
        return urusanBatal;
    }

	public void setAgensiRujukan(KodAgensi agensiRujukan) {
		this.agensiRujukan = agensiRujukan;
	}

	public KodAgensi getAgensiRujukan() {
		return agensiRujukan;
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

	public String getNoFail() {
		return noFail;
	}

	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public KodDokumen getKodDokumenRujukan() {
		return kodDokumenRujukan;
	}

	public void setKodDokumenRujukan(KodDokumen kodDokumenRujukan) {
		this.kodDokumenRujukan = kodDokumenRujukan;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public Date getTarikhRujukan() {
		return tarikhRujukan;
	}

	public void setTarikhRujukan(Date tarikhRujukan) {
		this.tarikhRujukan = tarikhRujukan;
	}

	public void setBandarPekanMukimLama(KodBandarPekanMukim bandarPekanMukimLama) {
		this.bandarPekanMukimLama = bandarPekanMukimLama;
	}

	public KodBandarPekanMukim getBandarPekanMukimLama() {
		return bandarPekanMukimLama;
	}

	public void setBandarPekanMukimBaru(KodBandarPekanMukim bandarPekanMukimBaru) {
		this.bandarPekanMukimBaru = bandarPekanMukimBaru;
	}

	public KodBandarPekanMukim getBandarPekanMukimBaru() {
		return bandarPekanMukimBaru;
	}

	public KodKategoriTanah getKategoriTanahLama() {
		return kategoriTanahLama;
	}

	public void setKategoriTanahLama(KodKategoriTanah kategoriTanahLama) {
		this.kategoriTanahLama = kategoriTanahLama;
	}

	public KodKategoriTanah getKategoriTanahBaru() {
		return kategoriTanahBaru;
	}

	public void setKategoriTanahBaru(KodKategoriTanah kategoriTanahBaru) {
		this.kategoriTanahBaru = kategoriTanahBaru;
	}

	public KodSyaratNyata getSyaratNyataLama() {
		return syaratNyataLama;
	}

	public void setSyaratNyataLama(KodSyaratNyata syaratNyataLama) {
		this.syaratNyataLama = syaratNyataLama;
	}

	public KodSyaratNyata getSyaratNyataBaru() {
		return syaratNyataBaru;
	}

	public void setSyaratNyataBaru(KodSyaratNyata syaratNyataBaru) {
		this.syaratNyataBaru = syaratNyataBaru;
	}

	public KodSekatanKepentingan getSekatanKepentinganLama() {
		return sekatanKepentinganLama;
	}

	public void setSekatanKepentinganLama(
			KodSekatanKepentingan sekatanKepentinganLama) {
		this.sekatanKepentinganLama = sekatanKepentinganLama;
	}

	public KodSekatanKepentingan getSekatanKepentinganBaru() {
		return sekatanKepentinganBaru;
	}

	public void setSekatanKepentinganBaru(
			KodSekatanKepentingan sekatanKepentinganBaru) {
		this.sekatanKepentinganBaru = sekatanKepentinganBaru;
	}

	public void setTempohTahun(int tempohTahun) {
		this.tempohTahun = tempohTahun;
	}

	public void setTempohBulan(int tempohBulan) {
		this.tempohBulan = tempohBulan;
	}

	public void setTempohHari(int tempohHari) {
		this.tempohHari = tempohHari;
	}

	public void setUrusanBatal(HakmilikUrusan urusanBatal) {
        this.urusanBatal = urusanBatal;
    }

    public Date getTarikhBatal() {
        return tarikhBatal;
    }

    public void setTarikhBatal(Date tarikhBatal) {
        this.tarikhBatal = tarikhBatal;
    }

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setSenaraiSurat(List<HakmilikUrusanSurat> senaraiSurat) {
		this.senaraiSurat = senaraiSurat;
	}

	public List<HakmilikUrusanSurat> getSenaraiSurat() {
		return senaraiSurat;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public FolderDokumen getFolderDokumenBatal() {
        return folderDokumenBatal;
    }

    public void setFolderDokumenBatal(FolderDokumen folderDokumenBatal) {
        this.folderDokumenBatal = folderDokumenBatal;
    }

    public String getIdPermohonanBatal() {
        return idPermohonanBatal;
    }

    public void setIdPermohonanBatal(String idPermohonanBatal) {
        this.idPermohonanBatal = idPermohonanBatal;
    }

	public void setItem(String item) {
		this.item = item;
	}

	public String getItem() {
		return item;
	}

    public HakmilikUrusan getUrusanRujukan() {
        return urusanRujukan;
    }

    public void setUrusanRujukan(HakmilikUrusan urusanRujukan) {
        this.urusanRujukan = urusanRujukan;
    }

    public KodPerhubunganHakmilik getHubunganHakmilik() {
        return hubunganHakmilik;
    }

    public void setHubunganHakmilik(KodPerhubunganHakmilik hubunganHakmilik) {
        this.hubunganHakmilik = hubunganHakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPihakTerlibat() {
        return senaraiPihakTerlibat;
    }

    public void setSenaraiPihakTerlibat(List<HakmilikPihakBerkepentingan> senaraiPihakTerlibat) {
        this.senaraiPihakTerlibat = senaraiPihakTerlibat;
    }
}
