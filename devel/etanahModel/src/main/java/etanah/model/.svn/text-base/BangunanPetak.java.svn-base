package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "bngn_petak")
@SequenceGenerator (name = "seq_bngn_petak", sequenceName = "seq_bngn_petak")
public class BangunanPetak implements Serializable {

	@Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_bngn_petak")
	@Column (name = "id_petak")
	private long idPetak;
	
	@ManyToOne
	@JoinColumn (name = "id_tgkt", nullable = false)
	private BangunanTingkat tingkat;
	
	@Column (name = "nama")
	private String nama;


	@ManyToOne
	@JoinColumn (name = "kod_guna_petak")
	private KodKegunaanPetak kegunaan;


	@ManyToOne
	@JoinColumn (name = "kod_uom")
	private KodUOM luasUom;

	@Column (name = "luas")
	private BigDecimal luas;
	
	@Column (name = "syer")
	private Integer syer;
	
	@OneToMany (mappedBy = "petak")
	private List<BangunanPetakAksesori> senaraiPetakAksesori;
        
        @Column (name = "pab_petak")
	private String pabPetak;
        
        @Column(name = "lain")
        private String lain;
        
        @Column(name = "kod_syarat_nyata")
        private String syaratNyata;
          
        @Column(name = "kod_sekatan")
        private String sekatanKepentingan;
        
        @ManyToOne
        @JoinColumn(name = "syarat")
        private KodSyaratNyata syarat;
        
        @ManyToOne
        @JoinColumn(name = "sekatan")
        private KodSekatanKepentingan sekatan;
        
        @Column(name = "cukai")
        private BigDecimal cukai;
        
        @ManyToOne
        @JoinColumn (name = "kod_guna_bngn")
        private KodKegunaanBangunan kodKegunaanBangunan;
	
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "kod_kelas_tanah")
        private KodKelasTanah kodkelasTanah;

        @Column(name = "kadar")
        private String kadar;
        
        @Column(name = "kos_rendah")
        private String kosRendah;
        
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPetak() {
		return idPetak;
	}

	public void setIdPetak(long idPetak) {
		this.idPetak = idPetak;
	}

	public BangunanTingkat getTingkat() {
		return tingkat;
	}

	public void setTingkat(BangunanTingkat tingkat) {
		this.tingkat = tingkat;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public KodKegunaanPetak getKegunaan() {
		return kegunaan;
	}

	public void setKegunaan(KodKegunaanPetak kegunaan) {
		this.kegunaan = kegunaan;
	}

	public BigDecimal getLuas() {
		return luas;
	}

	public void setLuas(BigDecimal luas) {
		this.luas = luas;
	}

    public KodUOM getLuasUom() {
        return luasUom;
    }

    public void setLuasUom(KodUOM luasUom) {
        this.luasUom = luasUom;
    }

	public Integer getSyer() {
		return syer;
	}

	public void setSyer(Integer syer) {
		this.syer = syer;
	}

	public void setSenaraiPetakAksesori(List<BangunanPetakAksesori> senaraiPetakAksesori) {
		this.senaraiPetakAksesori = senaraiPetakAksesori;
	}

	public List<BangunanPetakAksesori> getSenaraiPetakAksesori() {
		return senaraiPetakAksesori;
	}

    public String getPabPetak() {
        return pabPetak;
    }

    public void setPabPetak(String pabPetak) {
        this.pabPetak = pabPetak;
    }
        
        

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSekatanKepentingan() {
        return sekatanKepentingan;
    }

    public void setSekatanKepentingan(String sekatanKepentingan) {
        this.sekatanKepentingan = sekatanKepentingan;
    }

    public KodSyaratNyata getSyarat() {
        return syarat;
    }

    public void setSyarat(KodSyaratNyata syarat) {
        this.syarat = syarat;
    }

    public KodSekatanKepentingan getSekatan() {
        return sekatan;
    }

    public void setSekatan(KodSekatanKepentingan sekatan) {
        this.sekatan = sekatan;
    }

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public KodKegunaanBangunan getKodKegunaanBangunan() {
        return kodKegunaanBangunan;
    }

    public void setKodKegunaanBangunan(KodKegunaanBangunan kodKegunaanBangunan) {
        this.kodKegunaanBangunan = kodKegunaanBangunan;
    }

    public KodKelasTanah getKodkelasTanah() {
        return kodkelasTanah;
    }

    public void setKodkelasTanah(KodKelasTanah kodkelasTanah) {
        this.kodkelasTanah = kodkelasTanah;
    }

    public String getKadar() {
        return kadar;
    }

    public void setKadar(String kadar) {
        this.kadar = kadar;
    }

    public String getKosRendah() {
        return kosRendah;
    }

    public void setKosRendah(String kosRendah) {
        this.kosRendah = kosRendah;
    }
       	
}
