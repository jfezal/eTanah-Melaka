package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "mohon_trizab")
@SequenceGenerator(name = "seq_mohon_trizab", sequenceName = "seq_mohon_trizab")
public class TanahRizabPermohonan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_trizab")
        @Column (name = "id_mtr")
	private long idTanahRizabPermohonan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
        
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mh")
	private HakmilikPermohonan hakmilikPermohonan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@Column (name = "no_litho")
	private String noLitho;

	@Column (name = "no_hakmilik")
	private String noHakmilik;

	@ManyToOne
	@JoinColumn (name = "kod_rizab")
	private KodRizab rizab;

	@ManyToOne
	@JoinColumn (name = "kod_syarat_nyata")
	private KodSyaratNyata kodSyaratNyata;

	@ManyToOne
	@JoinColumn (name = "kod_daerah")
	private KodDaerah daerah;
	
	@ManyToOne
	@JoinColumn (name = "kod_bpm")
	private KodBandarPekanMukim bandarPekanMukim;
	
	@Column (name = "no_lot", nullable = true)
	private String noLot;
	
	@Column (name = "no_warta")
	private String noWarta;
	
	@Column (name = "trh_warta")
	private Date tarikhWarta;
	
	@Column(name = "no_pw")
	private String noPW;
	
    @Column (name = "lokasi")
    private String lokasi;
	
	@Column (name = "dijaga")
	private String penjaga;

	@Column (name = "jaga_nama")
	private String namaPenjaga;
	@Column (name = "jaga_alamat1")
	private String jagaAlamat1;
	@Column (name = "jaga_alamat2")
	private String jagaAlamat2;
	@Column (name = "jaga_alamat3")
	private String jagaAlamat3;
	@Column (name = "jaga_alamat4")
	private String jagaAlamat4;
	@Column (name = "jaga_poskod")
	private String jagaPoskod;

    @ManyToOne
    @JoinColumn(name = "jaga_negeri")
    private KodNegeri jagaNegeri;

	@Column (name = "jaga_tel")
	private String jagaTel;
	@Column (name = "jaga_fax")
	private String jagaFax;
	@Column (name = "jaga_email")
	private String jagaEmail;
	@Column (name = "ada_cukai")
	private String adaCukai;
    @Column(name = "cukai", nullable = true)
    private BigDecimal cukai;
	
    @Column(name = "luas", nullable = true)
    private BigDecimal luasTerlibat;

    @Column(name = "luas_diambil", nullable = true)
    private BigDecimal luasDiambil;

    @Column(name = "nama_kegunaan", nullable = true)
    private String namaKegunaan;

    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;

    @ManyToOne
    @JoinColumn(name = "luas_diambil_uom")
    private KodUOM luasDiambilUom;

    @Column (name = "aktif")
    private char aktif;

    @Column(name = "tanah_diambil")
    private String tanahDiambil;

	@Column (name = "catatan")
	private String catatan;
        
        @Column (name = "TRH_PENYEDIAAN_WARTA")
	private Date tarikhPenyediaanWarta;
        
        @Column (name = "TRH_PENGESAHAN_WARTA")
	private Date tarikhPengesahanWarta;
        

    public String getTanahDiambil() {
        return tanahDiambil;
    }

    public void setTanahDiambil(String tanahDiambil) {
        this.tanahDiambil = tanahDiambil;
    }
	
	private InfoAudit infoAudit;

	public long getIdTanahRizabPermohonan() {
		return idTanahRizabPermohonan;
	}

	public void setIdTanahRizabPermohonan(long idTanahRizabPermohonan) {
		this.idTanahRizabPermohonan = idTanahRizabPermohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public String getNoLitho() {
		return noLitho;
	}

	public void setNoLitho(String noLitho) {
		this.noLitho = noLitho;
	}

	public KodRizab getRizab() {
		return rizab;
	}

	public void setRizab(KodRizab rizab) {
		this.rizab = rizab;
	}

    public KodSyaratNyata getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

	public KodDaerah getDaerah() {
		return daerah;
	}

	public void setDaerah(KodDaerah daerah) {
		this.daerah = daerah;
	}

	public KodBandarPekanMukim getBandarPekanMukim() {
		return bandarPekanMukim;
	}

	public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
		this.bandarPekanMukim = bandarPekanMukim;
	}

	public String getNoLot() {
		return noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public String getNoWarta() {
		return noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public Date getTarikhWarta() {
		return tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}
	
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getLokasi() {
		return lokasi;
	}

	public String getPenjaga(){
		return penjaga;
	}
	
	public void setPenjaga(String penjaga){
		this.penjaga = penjaga;
	}

    public String getAdaCukai() {
        return adaCukai;
    }

    public void setAdaCukai(String adaCukai) {
        this.adaCukai = adaCukai;
    }

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public String getJagaAlamat1() {
        return jagaAlamat1;
    }

    public void setJagaAlamat1(String jagaAlamat1) {
        this.jagaAlamat1 = jagaAlamat1;
    }

    public String getJagaAlamat2() {
        return jagaAlamat2;
    }

    public void setJagaAlamat2(String jagaAlamat2) {
        this.jagaAlamat2 = jagaAlamat2;
    }

    public String getJagaAlamat3() {
        return jagaAlamat3;
    }

    public void setJagaAlamat3(String jagaAlamat3) {
        this.jagaAlamat3 = jagaAlamat3;
    }

    public String getJagaAlamat4() {
        return jagaAlamat4;
    }

    public void setJagaAlamat4(String jagaAlamat4) {
        this.jagaAlamat4 = jagaAlamat4;
    }

    public String getJagaEmail() {
        return jagaEmail;
    }

    public void setJagaEmail(String jagaEmail) {
        this.jagaEmail = jagaEmail;
    }

    public String getJagaFax() {
        return jagaFax;
    }

    public void setJagaFax(String jagaFax) {
        this.jagaFax = jagaFax;
    }

    public KodNegeri getJagaNegeri() {
        return jagaNegeri;
    }

    public void setJagaNegeri(KodNegeri jagaNegeri) {
        this.jagaNegeri = jagaNegeri;
    }

    public String getJagaPoskod() {
        return jagaPoskod;
    }

    public void setJagaPoskod(String jagaPoskod) {
        this.jagaPoskod = jagaPoskod;
    }

    public String getJagaTel() {
        return jagaTel;
    }

    public void setJagaTel(String jagaTel) {
        this.jagaTel = jagaTel;
    }

    public String getNamaPenjaga() {
        return namaPenjaga;
    }

    public void setNamaPenjaga(String namaPenjaga) {
        this.namaPenjaga = namaPenjaga;
    }

	public void setLuasTerlibat(BigDecimal luasTerlibat) {
		this.luasTerlibat = luasTerlibat;
	}

	public BigDecimal getLuasTerlibat() {
		return luasTerlibat;
	}

	public void setKodUnitLuas(KodUOM kodUnitLuas) {
		this.kodUnitLuas = kodUnitLuas;
	}

	public KodUOM getKodUnitLuas() {
		return kodUnitLuas;
	}

    public BigDecimal getLuasDiambil() {
        return luasDiambil;
    }

    public void setLuasDiambil(BigDecimal luasDiambil) {
        this.luasDiambil = luasDiambil;
    }

    public KodUOM getLuasDiambilUom() {
        return luasDiambilUom;
    }

    public void setLuasDiambilUom(KodUOM luasDiambilUom) {
        this.luasDiambilUom = luasDiambilUom;
    }

    public String getNamaKegunaan() {
        return namaKegunaan;
    }

    public void setNamaKegunaan(String namaKegunaan) {
        this.namaKegunaan = namaKegunaan;
    }

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public char getAktif() {
		return aktif;
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

	public void setNoPW(String noPW) {
		this.noPW = noPW;
	}

	public String getNoPW() {
		return noPW;
	}

    public Date getTarikhPengesahanWarta() {
        return tarikhPengesahanWarta;
    }

    public void setTarikhPengesahanWarta(Date tarikhPengesahanWarta) {
        this.tarikhPengesahanWarta = tarikhPengesahanWarta;
    }

    public Date getTarikhPenyediaanWarta() {
        return tarikhPenyediaanWarta;
    }

    public void setTarikhPenyediaanWarta(Date tarikhPenyediaanWarta) {
        this.tarikhPenyediaanWarta = tarikhPenyediaanWarta;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    
	
}
