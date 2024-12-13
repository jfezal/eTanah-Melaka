package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import javax.persistence.ManyToOne;

@Entity
@Table(name = "mohon_pihak")
@SequenceGenerator(name = "seq_mohon_pihak", sequenceName = "seq_mohon_pihak")
public class PermohonanPihak implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_pihak")
    @Column(name = "id_mp")
    private long idPermohonanPihak;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
    @ManyToOne
    @JoinColumn (name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pihak")
    private Pihak pihak;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "id_pihak_caw")
	private PihakCawangan pihakCawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;

    @ManyToOne
    @JoinColumn(name = "kod_pihak", nullable = false)
    private KodJenisPihakBerkepentingan jenis;
    
    @Column(name = "syer_pembilang", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPembilang;
    
    @Column(name = "syer_penyebut", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPenyebut;
    
    @Column (name = "umur", nullable = true)
    private Integer umur;
    
    @Column (name = "kerja")
    private String pekerjaan;
    
    @Column (name = "gaji")
    private BigDecimal pendapatan;
    
    @Column (name = "gaji2")
    private BigDecimal pendapatanLain;
    
    @Column (name = "sts_kahwin")
    private String statusKahwin;
    
    @Column (name = "kaitan", length = 50)
    private String kaitan;
    
    @Column (name = "tanggung")
    private Integer tangungan;
    

    @OneToMany (mappedBy = "pihak",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermohonanPihakHubungan> senaraiHubungan;
    

    @Column (name = "nilai")
    private BigDecimal nilai;
    
    @OneToMany (mappedBy = "pihak", fetch = FetchType.LAZY)
    private List<PermohonanTuntutanKos> senaraiTuntutanKos;
    

    @Column (name = "d_nilai1")
    private String dalamanNilai1;
    
    @Column (name = "d_nilai2")
    private String dalamanNilai2;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "sts_mohon_pihak")
	private KodStatusMohonPihak statusMohonPihak;


    @Column (name = "no_ruj")
    private String noRujukan;    
    
    @Column(name = "id_pihak_kongsi", nullable = true)
    private Integer idPihakKongsi;
    
    @Column(name = "syer_bersama")
	private Character syerBersama;
    
    @Column(name = "luas", nullable = true)
    private BigDecimal luasTerlibat;
    
     @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;
     
    @ManyToOne
    @JoinColumn(name = "kod_matawang")
    private KodMatawang kodMataWang;    
      
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
    
    @Column(name = "kod_status", length = 250)
    private String kodStatus;
    
    @Embedded
    private Alamat alamat;
    
    @ManyToOne
    @JoinColumn (name = "kod_sykt_tubuh")
    private KodPenubuhanSyarikat penubuhanSyarikat;
    
    @Column(name = "JUM_PENYEBUT", precision = 22, scale = 0, columnDefinition = "number(22,0)")
    private Integer jumlahPenyebut;
	
    @Column(name = "JUM_PEMBILANG", precision = 22, scale = 0, columnDefinition = "number(22,0)")
    private Integer jumlahPembilang;   
    
    @Embedded
    private AlamatSurat alamatSurat;
    
    @Column (name = "id_mohon_lama")
    private String idPermohonanLama;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
		this.pihakCawangan = pihakCawangan;
	}

	public PihakCawangan getPihakCawangan() {
		return pihakCawangan;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public KodJenisPihakBerkepentingan getJenis() {
        return jenis;
    }

    public void setJenis(KodJenisPihakBerkepentingan jenis) {
        this.jenis = jenis;
    }

    public Integer getSyerPembilang() {
        return syerPembilang;
    }

    public void setSyerPembilang(int syerPembilang) {
        this.syerPembilang = syerPembilang;
    }

    public Integer getSyerPenyebut() {
        return syerPenyebut;
    }

    public void setSyerPenyebut(int syerPenyebut) {
        this.syerPenyebut = syerPenyebut;
    }

	public Integer getUmur() {
		return umur;
	}

	public void setUmur(Integer umur) {
		this.umur = umur;
	}

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public String getPekerjaan() {
		return pekerjaan;
	}

	public BigDecimal getPendapatan() {
		return pendapatan;
	}

	public void setPendapatan(BigDecimal pendapatan) {
		this.pendapatan = pendapatan;
	}

	public void setPendapatanLain(BigDecimal pendapatanLain) {
		this.pendapatanLain = pendapatanLain;
	}

	public BigDecimal getPendapatanLain() {
		return pendapatanLain;
	}

	public void setStatusKahwin(String statusKahwin) {
		this.statusKahwin = statusKahwin;
	}

	public String getStatusKahwin() {
		return statusKahwin;
	}

	public String getKaitan() {
		return kaitan;
	}

	public void setKaitan(String kaitan) {
		this.kaitan = kaitan;
	}

	public Integer getTangungan() {
		return tangungan;
	}

	public void setTangungan(Integer tangungan) {
		this.tangungan = tangungan;
	}
	

	public List<PermohonanPihakHubungan> getSenaraiHubungan(){
		return senaraiHubungan;
	}
	
	public void setSenaraiHubungan(List<PermohonanPihakHubungan> senaraiHubungan){
		this.senaraiHubungan = senaraiHubungan;
	}	

    public void setNilai(BigDecimal nilai) {
		this.nilai = nilai;
	}

	public BigDecimal getNilai() {
		return nilai;
	}

	public void setSenaraiTuntutanKos(List<PermohonanTuntutanKos> senaraiTuntutanKos) {
		this.senaraiTuntutanKos = senaraiTuntutanKos;
	}

	public List<PermohonanTuntutanKos> getSenaraiTuntutanKos() {
		return senaraiTuntutanKos;
	}

	public void setDalamanNilai1(String dalamanNilai1) {
		this.dalamanNilai1 = dalamanNilai1;
	}

	public String getDalamanNilai1() {
		return dalamanNilai1;
	}
	
	public void setStatusMohonPihak(KodStatusMohonPihak statusMohonPihak) {
		this.statusMohonPihak = statusMohonPihak;
	}

	public KodStatusMohonPihak getStatusMohonPihak() {
		return statusMohonPihak;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Character getSyerBersama() {
        return syerBersama;
    }

    public void setSyerBersama(Character syerBersama) {
        this.syerBersama = syerBersama;
    }

    public KodMatawang getKodMataWang() {
        return kodMataWang;
    }

    public void setKodMataWang(KodMatawang kodMataWang) {
        this.kodMataWang = kodMataWang;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
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

    public String getDalamanNilai2() {
        return dalamanNilai2;
    }

    public void setDalamanNilai2(String dalamanNilai2) {
        this.dalamanNilai2 = dalamanNilai2;
    }   

    public String getIdPermohonanLama() {
        return idPermohonanLama;
    }

    public void setIdPermohonanLama(String idPermohonanLama) {
        this.idPermohonanLama = idPermohonanLama;
    }

    public String getKodStatus() {
        return kodStatus;
    }
    
    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
}

    public Integer getIdPihakKongsi() {
        return idPihakKongsi;
    }

    public void setIdPihakKongsi(Integer idPihakKongsi) {
        this.idPihakKongsi = idPihakKongsi;
    }
}
