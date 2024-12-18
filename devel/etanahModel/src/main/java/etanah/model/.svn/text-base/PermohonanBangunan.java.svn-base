package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "mohon_bngn")
@SequenceGenerator (name = "seq_mohon_bngn", sequenceName = "seq_mohon_bngn")
public class PermohonanBangunan implements Serializable {
	@Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_bngn")
	@Column (name =  "id_bngn")
	private long idBangunan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
        
	@ManyToOne
	@JoinColumn (name = "id_skim", nullable = true)
	private PermohonanSkim permohonanSkim;
 
	@Column (name = "nama", nullable = false)
	private String nama;
	
	@Column (name = "nama_lain")
	private String namaLain;
        
	@ManyToOne
	@JoinColumn (name = "katg_bngn", nullable = false)
	private KodKategoriBangunan kodKategoriBangunan;
        
        @ManyToOne
        @JoinColumn (name = "kod_guna_bngn")
        private KodKegunaanBangunan kodKegunaanBangunan;

	@Column (name = "syer_blok")
	private Integer syerBlok;
	
	@Column (name = "bil_tgkt", nullable = false)
	private int bilanganTingkat;
	
	@Column (name = "bil_petak")
	private int bilanganPetak;
	
	@OneToOne
	@JoinColumn (name = "id_mezanin")
	private BangunanTingkat tingkatMezanin;
	
	@Column (name = "utk_duduk")
	private char untukKediaman;
	
	@Column (name = "utk_niaga")
	private char untukPerniagaan;
	
	@Column (name = "utk_pejabat")
	private char untukPejabat;
	
	@Column (name = "utk_lain")
	private String untukKegunaanLain;
	
	@Column (name = "kekal")
	private char kekal;	

	@Column (name = "bil_menara")
	private int bilanganMenara;	

	@Column (name = "bil_bilik")
	private int bilanganBilik;
	
	@OneToMany (mappedBy = "bangunan")
	private List<BangunanTingkat> senaraiTingkat;
	
	// laporan bangunan
	
	@Column (name = "lapor_laluan")
	private Character laporanLaluan;

	@Column (name = "lapor_tangga")
	private Character laporanTangga;

	@Column (name = "lapor_telaga_udara")
	private Character laporanTelagaUdara;
        
        @OneToMany(mappedBy = "bangunan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private List<BangunanPetakAksesori> senaraiBangunanPetakAksesori;
        
        @Column(name = "lain")
        private String lain;
        
        @Column(name = "ID_MOHON_SBGN")
        private String idPermohonanSambung;
	@Embedded
	private InfoAudit infoAudit;

	public long getIdBangunan() {
		return idBangunan;
	}

	public void setIdBangunan(long idBangunan) {
		this.idBangunan = idBangunan;
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

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNamaLain() {
		return namaLain;
	}

	public void setNamaLain(String namaLain) {
		this.namaLain = namaLain;
	}

    public KodKategoriBangunan getKodKategoriBangunan() {
        return kodKategoriBangunan;
    }

    public void setKodKategoriBangunan(KodKategoriBangunan kodKategoriBangunan) {
        this.kodKategoriBangunan = kodKategoriBangunan;
    }



    public Integer getSyerBlok() {
        return syerBlok;
    }

    public void setSyerBlok(Integer syerBlok) {
        this.syerBlok = syerBlok;
    }

	public int getBilanganTingkat() {
		return bilanganTingkat;
	}

	public void setBilanganTingkat(int bilanganTingkat) {
		this.bilanganTingkat = bilanganTingkat;
	}

	public void setBilanganPetak(int bilanganPetak) {
		this.bilanganPetak = bilanganPetak;
	}

	public int getBilanganPetak() {
		return bilanganPetak;
	}

	public char getUntukKediaman() {
		return untukKediaman;
	}

	public void setUntukKediaman(char untukKediaman) {
		this.untukKediaman = untukKediaman;
	}

	public char getUntukPerniagaan() {
		return untukPerniagaan;
	}

	public void setUntukPerniagaan(char untukPerniagaan) {
		this.untukPerniagaan = untukPerniagaan;
	}

	public char getUntukPejabat() {
		return untukPejabat;
	}

	public void setUntukPejabat(char untukPejabat) {
		this.untukPejabat = untukPejabat;
	}

	public String getUntukKegunaanLain() {
		return untukKegunaanLain;
	}

	public void setUntukKegunaanLain(String untukKegunaanLain) {
		this.untukKegunaanLain = untukKegunaanLain;
	}

	public char getKekal() {
		return kekal;
	}

	public void setKekal(char kekal) {
		this.kekal = kekal;
	}

	public void setBilanganMenara(int menara) {
		this.bilanganMenara = menara;
	}

	public int getBilanganMenara() {
		return bilanganMenara;
	}

	public void setBilanganBilik(int bilanganBilik) {
		this.bilanganBilik = bilanganBilik;
	}

	public int getBilanganBilik() {
		return bilanganBilik;
	}

	public BangunanTingkat getTingkatMezanin() {
		return tingkatMezanin;
	}

	public void setTingkatMezanin(BangunanTingkat tingkatMezanin) {
		this.tingkatMezanin = tingkatMezanin;
	}

	public void setSenaraiTingkat(List<BangunanTingkat> senaraiTingkat) {
		this.senaraiTingkat = senaraiTingkat;
	}

	public List<BangunanTingkat> getSenaraiTingkat() {
		return senaraiTingkat;
	}

	public Character getLaporanLaluan() {
		return laporanLaluan;
	}

	public void setLaporanLaluan(Character laporanLaluan) {
		this.laporanLaluan = laporanLaluan;
	}

	public Character getLaporanTangga() {
		return laporanTangga;
	}

	public void setLaporanTangga(Character laporanTangga) {
		this.laporanTangga = laporanTangga;
	}

	public Character getLaporanTelagaUdara() {
		return laporanTelagaUdara;
	}

	public void setLaporanTelagaUdara(Character laporanTelagaUdara) {
		this.laporanTelagaUdara = laporanTelagaUdara;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

        public PermohonanSkim getPermohonanSkim() {
            return permohonanSkim;
        }

        public void setPermohonanSkim(PermohonanSkim permohonanSkim) {
            this.permohonanSkim = permohonanSkim;
        }

    public KodKegunaanBangunan getKodKegunaanBangunan() {
        return kodKegunaanBangunan;
    }

    public void setKodKegunaanBangunan(KodKegunaanBangunan kodKegunaanBangunan) {
        this.kodKegunaanBangunan = kodKegunaanBangunan;
    }

    public List<BangunanPetakAksesori> getSenaraiBangunanPetakAksesori() {
        return senaraiBangunanPetakAksesori;
    }

    public void setSenaraiBangunanPetakAksesori(List<BangunanPetakAksesori> senaraiBangunanPetakAksesori) {
        this.senaraiBangunanPetakAksesori = senaraiBangunanPetakAksesori;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }

    public String getIdPermohonanSambung() {
        return idPermohonanSambung;
    }

    public void setIdPermohonanSambung(String idPermohonanSambung) {
        this.idPermohonanSambung = idPermohonanSambung;
    }
}

