package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "mohon_urusan_betul")
@SequenceGenerator (name = "seq_mohon_urusan_betul", sequenceName = "seq_mohon_urusan_betul")
public class PermohonanPembetulanUrusan implements Serializable {

        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_urusan_betul")
	@Id 
	@Column (name = "id_betul")
	private long idPembetulan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "id_mohon_betul", nullable = false)
	private Permohonan permohonan;

	@ManyToOne
	@JoinColumn (name = "id_mh")
	private HakmilikPermohonan hakmilik;
	
	/**
	 * Urusan yg hendak dibetulkan.
	 */
	@ManyToOne
	@JoinColumn (name = "id_urusan")
	private HakmilikUrusan urusan;
	
	@ManyToOne
	@JoinColumn (name = "kod_urusan")
	private KodUrusan kodUrusan;
	
	@Column (name = "id_serah_lama")
	private String idPerserahanLama;
	
	@Column (name = "trh_daftar")
	private Date tarikhDaftar;
	
	@Column (name = "no_jilid")
	private String noJilid;
        
	@Column (name = "ulasan")
	private String ulasan;
	
	@Column (name = "no_folio")
	private String noFolio;
	
	@Column (name = "trh_mula")
	private Date tarikhMula;
	
	@Column (name = "tempoh_thn")
	private Integer tempohTahun;
	
	@Column (name = "tempoh_bln")
	private Integer tempohBulan;
	
	@Column (name = "tempoh_hari")
	private Integer tempohHari;
	
	@Column (name = "trh_tamat")
	private Date tarikhTamat;
	
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
        
	@Column (name = "aktif")
	private String aktif;
	
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
	
	@Column (name = "janji_no_ruj")
	private String perjanjianNoRujukan;
	
	@Column (name = "janji_amaun")
	private BigDecimal perjanjianAmaun;
	
	@Column (name = "janji_duti_setem")
	private BigDecimal perjanjianDutiStem;
	
	@ManyToOne
	@JoinColumn (name = "kod_perintah")
	private KodPerintah kodPerintah;
	
	@Column (name = "perintah_trh_kkuasa")
	private Date perintahTarikhKuatkuasa;
	
	@Column (name = "perintah_sbb")
	private String perintahSebab;

	
	@Column (name = "trh_saksi")
	private Date tarikhSaksi;
	
	@Column (name = "catatan")
	private String catatan;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdPembetulan() {
		return idPembetulan;
	}

	public void setIdPembetulan(long idPembetulan) {
		this.idPembetulan = idPembetulan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setHakmilik(HakmilikPermohonan hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilik() {
        return hakmilik;
    }

    public HakmilikUrusan getUrusan() {
		return urusan;
	}

	public void setUrusan(HakmilikUrusan urusan) {
		this.urusan = urusan;
	}

	public KodUrusan getKodUrusan() {
		return kodUrusan;
	}

	public void setKodUrusan(KodUrusan kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public void setIdPerserahanLama(String idPerserahanLama) {
		this.idPerserahanLama = idPerserahanLama;
	}

	public String getIdPerserahanLama() {
		return idPerserahanLama;
	}

	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public String getNoJilid() {
		return noJilid;
	}

	public void setNoJilid(String noJilid) {
		this.noJilid = noJilid;
	}

	public String getNoFolio() {
		return noFolio;
	}

	public void setNoFolio(String noFolio) {
		this.noFolio = noFolio;
	}

	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
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

	public Date getTarikhTamat() {
		return tarikhTamat;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
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

	public KodAgensi getAgensiRujukan() {
		return agensiRujukan;
	}

	public void setAgensiRujukan(KodAgensi agensiRujukan) {
		this.agensiRujukan = agensiRujukan;
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

	public KodBandarPekanMukim getBandarPekanMukimLama() {
		return bandarPekanMukimLama;
	}

	public void setBandarPekanMukimLama(KodBandarPekanMukim bandarPekanMukimLama) {
		this.bandarPekanMukimLama = bandarPekanMukimLama;
	}

	public KodBandarPekanMukim getBandarPekanMukimBaru() {
		return bandarPekanMukimBaru;
	}

	public void setBandarPekanMukimBaru(KodBandarPekanMukim bandarPekanMukimBaru) {
		this.bandarPekanMukimBaru = bandarPekanMukimBaru;
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

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPerjanjianNoRujukan() {
		return perjanjianNoRujukan;
	}

	public void setPerjanjianNoRujukan(String perjanjianNoRujukan) {
		this.perjanjianNoRujukan = perjanjianNoRujukan;
	}

	public BigDecimal getPerjanjianAmaun() {
		return perjanjianAmaun;
	}

	public void setPerjanjianAmaun(BigDecimal perjanjianAmaun) {
		this.perjanjianAmaun = perjanjianAmaun;
	}

	public BigDecimal getPerjanjianDutiStem() {
		return perjanjianDutiStem;
	}

	public void setPerjanjianDutiStem(BigDecimal perjanjianDutiStem) {
		this.perjanjianDutiStem = perjanjianDutiStem;
	}

	public KodPerintah getKodPerintah() {
		return kodPerintah;
	}

	public void setKodPerintah(KodPerintah kodPerintah) {
		this.kodPerintah = kodPerintah;
	}

	public Date getPerintahTarikhKuatkuasa() {
		return perintahTarikhKuatkuasa;
	}

	public void setPerintahTarikhKuatkuasa(Date perintahTarikhKuatkuasa) {
		this.perintahTarikhKuatkuasa = perintahTarikhKuatkuasa;
	}

	public String getPerintahSebab() {
		return perintahSebab;
	}

	public void setPerintahSebab(String perintahSebab) {
		this.perintahSebab = perintahSebab;
	}

	public Date getTarikhSaksi() {
		return tarikhSaksi;
	}

	public void setTarikhSaksi(Date tarikhSaksi) {
		this.tarikhSaksi = tarikhSaksi;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }
    
        
	
}
