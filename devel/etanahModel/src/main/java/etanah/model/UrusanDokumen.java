package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "urusan_dokumen")
@SequenceGenerator (name = "seq_urusan_dokumen", sequenceName = "seq_urusan_dokumen", allocationSize = 1)
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class UrusanDokumen implements Serializable {
	
	@Id 
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_urusan_dokumen")
   	@Column (name = "id_ud")
  	private long idUrusanDokumen;
   
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_dokumen")
	private KodDokumen kodDokumen;
	
	@ManyToOne
	@JoinColumn (name = "kod_urusan")
	private KodUrusan kodUrusan;
	
	@Column (name = "wajib", nullable = false)
	private char wajib;
	
	// X - semua, I individu, O -organisasi
	@Column (name = "katg_pemohon", nullable = false)
	private char kategoriPemohon;
	
	@Column (name = "perlu_sah", nullable = false)
	private char perluDisah;
	
	@Column (name = "perlu_imbas", nullable = false)
	private char perluDiimbas;
	
	@Column (name = "caj")
	private BigDecimal caj;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_trans")
	private KodTransaksi kodTransaksi;

	@Column (name = "catatan")
	private String catatan;

    @Column (name = "aktif")
	private char aktif;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdUrusanDokumen() {
		return idUrusanDokumen;
	}

	public void setIdUrusanDokumen(long idUrusanDokumen) {
		this.idUrusanDokumen = idUrusanDokumen;
	}

	public KodDokumen getKodDokumen() {
		return kodDokumen;
	}

	public void setKodDokumen(KodDokumen kodDokumen) {
		this.kodDokumen = kodDokumen;
	}

	public KodUrusan getKodUrusan() {
		return kodUrusan;
	}

	public void setKodUrusan(KodUrusan kodUrusan) {
		this.kodUrusan = kodUrusan;
	}

	public char getWajib() {
		return wajib;
	}

	public void setWajib(char wajib) {
		this.wajib = wajib;
	}

	public void setKategoriPemohon(char kategoriPemohon) {
        this.kategoriPemohon = kategoriPemohon;
    }

    public char getKategoriPemohon() {
        return kategoriPemohon;
    }

    public char getPerluDisah() {
		return perluDisah;
	}

	public void setPerluDisah(char perluDisah) {
		this.perluDisah = perluDisah;
	}

	public char getPerluDiimbas() {
		return perluDiimbas;
	}

	public void setPerluDiimbas(char perluDiimbas) {
		this.perluDiimbas = perluDiimbas;
	}

	public void setCaj(BigDecimal caj) {
		this.caj = caj;
	}

	public BigDecimal getCaj() {
		return caj;
	}

	public void setKodTransaksi(KodTransaksi kodTransaksi) {
		this.kodTransaksi = kodTransaksi;
	}

	public KodTransaksi getKodTransaksi() {
		return kodTransaksi;
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

        public char getAktif() {
            return aktif;
        }

        public void setAktif(char aktif) {
            this.aktif = aktif;
        }

}
