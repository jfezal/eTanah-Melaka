package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "mohon_bahan_batu")
@SequenceGenerator(name = "seq_mohon_bahan_batu", sequenceName = "seq_mohon_bahan_batu")
public class PermohonanBahanBatuan implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_bahan_batu")
    @Column(name = "id_mbb")
    private long id;
	
	@ManyToOne
	@JoinColumn(name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mohon")
	private Permohonan permohonan;
	
	@ManyToOne
    @JoinColumn (name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukim;
	
	@ManyToOne
	@JoinColumn(name = "kod_bhn_batu")
	private KodItemPermit jenisBahanBatu;
	
	@Column(name = "kwsn_ambil_batu")
	private String kawasanAmbilBatuan;
	
	@Column(name = "kwsn_pindah_batu")
	private String kawasanPindahBatuan;
	
	@Column(name = "jln_dilalui")
	private String jalanDilalui;
	
	@Column(name = "trh_mula")
	private Date tarikhMula;
	
	@Column(name = "trh_tamat")
	private Date tarikhTamat;
	
	@Column(name = "tempoh_keluar")
	private Integer tempohKeluar;
        
	@ManyToOne
	@JoinColumn(name = "tempoh_keluar_unit")
	private KodUOM unitTempohKeluar;
	
	@Column(name = "jum_isipadu")
	private BigDecimal jumlahIsipadu;
	
	@Column(name = "anggaran_muatan")
	private BigDecimal anggaranMuatan;
	
	@Column(name = "jum_isipadu_dipohon")
	private BigDecimal jumlahIsipaduDipohon;
	
	@Column(name = "lebar_bngn_sementara")
	private BigDecimal lebarBangunanSementara;
	
	@Column(name = "pjg_bngn_sementara")
	private BigDecimal panjangBangunanSementara;
	
	@Column(name = "bil_pekerja")
	private String bilanganPekerja;

	@ManyToOne
	@JoinColumn(name = "jum_isipadu_uom")
	private KodUOM jumlahIsipaduUom;

	@ManyToOne
	@JoinColumn(name = "jum_isipadu_dipohon_uom")
	private KodUOM jumlahIsipaduDipohonUom;

	@ManyToOne
	@JoinColumn(name = "anggaran_muatan_uom")
	private KodUOM anggaranMuatanUom;

	@ManyToOne
	@JoinColumn(name = "lebar_bngn_sementara_uom")
	private KodUOM lebarBangunanSementaraUom;

	@ManyToOne
	@JoinColumn(name = "pjg_bngn_sementara_uom")
	private KodUOM panjangBangunanSementaraUom;


	@Column(name = "luas_syor")
	private BigDecimal luasDisyor;

	@ManyToOne
	@JoinColumn(name = "luas_syor_uom")
	private KodUOM luasSyorUom;

	@Column(name = "tempoh_syor")
	private Integer tempohDisyor;

	@ManyToOne
	@JoinColumn(name = "tempoh_syor_uom")
	private KodUOM tempohSyorUom;
        
        
        @ManyToOne
	@JoinColumn(name = "id_lapor")
	private LaporanTanah laporanTanah;


	@Embedded
    private InfoAudit infoAudit;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
		this.bandarPekanMukim = bandarPekanMukim;
	}

	public KodBandarPekanMukim getBandarPekanMukim() {
		return bandarPekanMukim;
	}


	public void setKawasanAmbilBatuan(String kawasanAmbilBatuan) {
		this.kawasanAmbilBatuan = kawasanAmbilBatuan;
	}

	public String getKawasanAmbilBatuan() {
		return kawasanAmbilBatuan;
	}

	public void setKawasanPindahBatuan(String kawasanPindahBatuan) {
		this.kawasanPindahBatuan = kawasanPindahBatuan;
	}

	public String getKawasanPindahBatuan() {
		return kawasanPindahBatuan;
	}

	public void setJalanDilalui(String jalanDilalui) {
		this.jalanDilalui = jalanDilalui;
	}

	public String getJalanDilalui() {
		return jalanDilalui;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}

	public Date getTarikhTamat() {
		return tarikhTamat;
	}

	public void setTempohKeluar(Integer tempohKeluar) {
		this.tempohKeluar = tempohKeluar;
	}

	public Integer getTempohKeluar() {
		return tempohKeluar;
	}



        public KodUOM getUnitTempohKeluar() {
        return unitTempohKeluar;
        }
        
        public void setUnitTempohKeluar(KodUOM unitTempohKeluar) {
        this.unitTempohKeluar = unitTempohKeluar;
        }
        
        

	public void setJumlahIsipadu(BigDecimal jumlahIsipadu) {
		this.jumlahIsipadu = jumlahIsipadu;
	}

	public BigDecimal getJumlahIsipadu() {
		return jumlahIsipadu;
	}

	public void setAnggaranMuatan(BigDecimal anggaranMuatan) {
		this.anggaranMuatan = anggaranMuatan;
	}

	public BigDecimal getAnggaranMuatan() {
		return anggaranMuatan;
	}

	public void setJumlahIsipaduDipohon(BigDecimal jumlahIsipaduDipohon) {
		this.jumlahIsipaduDipohon = jumlahIsipaduDipohon;
	}

	public BigDecimal getJumlahIsipaduDipohon() {
		return jumlahIsipaduDipohon;
	}

	public void setLebarBangunanSementara(BigDecimal lebarBangunanSementara) {
		this.lebarBangunanSementara = lebarBangunanSementara;
	}

	public BigDecimal getLebarBangunanSementara() {
		return lebarBangunanSementara;
	}

	public void setPanjangBangunanSementara(BigDecimal panjangBangunanSementara) {
		this.panjangBangunanSementara = panjangBangunanSementara;
	}

	public BigDecimal getPanjangBangunanSementara() {
		return panjangBangunanSementara;
	}

	public void setBilanganPekerja(String bilanganPekerja) {
		this.bilanganPekerja = bilanganPekerja;
	}

	public String getBilanganPekerja() {
		return bilanganPekerja;
	}

    public KodUOM getAnggaranMuatanUom() {
        return anggaranMuatanUom;
    }

    public void setAnggaranMuatanUom(KodUOM anggaranMuatanUom) {
        this.anggaranMuatanUom = anggaranMuatanUom;
    }

    public KodItemPermit getJenisBahanBatu() {
        return jenisBahanBatu;
    }

    public void setJenisBahanBatu(KodItemPermit jenisBahanBatu) {
        this.jenisBahanBatu = jenisBahanBatu;
    }

    public KodUOM getJumlahIsipaduDipohonUom() {
        return jumlahIsipaduDipohonUom;
    }

    public void setJumlahIsipaduDipohonUom(KodUOM jumlahIsipaduDipohonUom) {
        this.jumlahIsipaduDipohonUom = jumlahIsipaduDipohonUom;
    }

    public KodUOM getJumlahIsipaduUom() {
        return jumlahIsipaduUom;
    }

    public void setJumlahIsipaduUom(KodUOM jumlahIsipaduUom) {
        this.jumlahIsipaduUom = jumlahIsipaduUom;
    }

    public KodUOM getLebarBangunanSementaraUom() {
        return lebarBangunanSementaraUom;
    }

    public void setLebarBangunanSementaraUom(KodUOM lebarBangunanSementaraUom) {
        this.lebarBangunanSementaraUom = lebarBangunanSementaraUom;
    }

    public KodUOM getPanjangBangunanSementaraUom() {
        return panjangBangunanSementaraUom;
    }

    public void setPanjangBangunanSementaraUom(KodUOM panjangBangunanSementaraUom) {
        this.panjangBangunanSementaraUom = panjangBangunanSementaraUom;
    }

    public BigDecimal getLuasDisyor() {
        return luasDisyor;
    }

    public void setLuasDisyor(BigDecimal luasDisyor) {
        this.luasDisyor = luasDisyor;
    }

    public Integer getTempohDisyor() {
        return tempohDisyor;
    }

    public void setTempohDisyor(Integer tempohDisyor) {
        this.tempohDisyor = tempohDisyor;
    }
 

    public KodUOM getLuasSyorUom() {
        return luasSyorUom;
    }

    public void setLuasSyorUom(KodUOM luasSyorUom) {
        this.luasSyorUom = luasSyorUom;
    }


    public KodUOM getTempohSyorUom() {
        return tempohSyorUom;
    }

    public void setTempohSyorUom(KodUOM tempohSyorUom) {
        this.tempohSyorUom = tempohSyorUom;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }     



	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}



}
