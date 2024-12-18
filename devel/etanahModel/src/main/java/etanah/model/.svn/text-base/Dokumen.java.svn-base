package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "dokumen")
@SequenceGenerator (name = "seq_dokumen", sequenceName = "seq_dokumen")
public class Dokumen implements Serializable {
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_dokumen")
	@Column (name = "ID_DOKUMEN")
	private long idDokumen;

	@ManyToOne
	@JoinColumn (name = "KOD_DOKUMEN", nullable = true)
	private KodDokumen kodDokumen;
	
	@Column (name = "TAJUK", length = 128, nullable = false)
	private String tajuk;
	
	@Column (name = "no_ruj")
	private String noRujukan;
	
	@Column (name = "MINIT", length = 1024)
	private String catatanMinit;
	
	@ManyToOne
	@JoinColumn (name = "DIMINIT")
	private Pengguna pencatatMinit;
	
	@ManyToOne
	@JoinColumn (name = "KOD_KLAS")
	KodKlasifikasi klasifikasi;
	
	@Column (name = "PERIHAL", length = 4000)
	private String perihal;
	
	@Column (name = "NO_VERSI", length = 8)
	private String noVersi;
	
	@Column (name = "FORMAT")
	private String format;
	
	@Column (name = "SAIZ")
	private Long saiz;
	
	@Column (name = "baru")
	private char baru;
	 
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "DIKARANG")
	private Pengguna pengarang;
	 
	@Column (name = "NAMA_FIZIKAL")
	private String namaFizikal;
	
	@Column (name = "nama_tt")
	private String namaTandatangan;

	@Column (name = "trh_dokumen")
	private Date tarikhDokumen;
		
	@OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "dokumen")
	private List<DokumenCapaian> senaraiCapaian;

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "dokumen")
	private List<KandunganFolder> senaraiFolder;
    
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "induk")
    @OrderBy("noVersi desc")
    private List<SejarahDokumen> senaraiSejarah;
	 
	@Embedded
	private InfoAudit infoAudit;

	// UNTUK KEGUNAAN DALAMAN
	
	@Column (name = "d_nilai1", length = 50)
	private String dalamanNilai1;
        
        
	@Column (name = "id_hakmilik")
	private String hakmilik;
        
        @ManyToOne
        @JoinColumn(name = "id_mohon")
	private Permohonan permohonan;

	public long getIdDokumen() {
		return idDokumen;
	}

	public void setIdDokumen(long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public KodDokumen getKodDokumen() {
		return kodDokumen;
	}

	public void setKodDokumen(KodDokumen kodDokumen) {
		this.kodDokumen = kodDokumen;
	}

	public String getTajuk() {
		return tajuk;
	}

	public void setTajuk(String tajuk) {
		this.tajuk = tajuk;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public String getCatatanMinit() {
		return catatanMinit;
	}

	public void setCatatanMinit(String catatanMinit) {
		this.catatanMinit = catatanMinit;
	}

	public Pengguna getPencatatMinit() {
		return pencatatMinit;
	}

	public void setPencatatMinit(Pengguna pencatatMinit) {
		this.pencatatMinit = pencatatMinit;
	}

	public KodKlasifikasi getKlasifikasi() {
		return klasifikasi;
	}

	public void setKlasifikasi(KodKlasifikasi klasifikasi) {
		this.klasifikasi = klasifikasi;
	}

	public String getPerihal() {
		return perihal;
	}

	public void setPerihal(String perihal) {
		this.perihal = perihal;
	}

	public String getNoVersi() {
		return noVersi;
	}

	public void setNoVersi(String noVersi) {
		this.noVersi = noVersi;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public long getSaiz() {
		return saiz;
	}

	public void setSaiz(long saiz) {
		this.saiz = saiz;
	}

	public Pengguna getPengarang() {
		return pengarang;
	}

	public void setPengarang(Pengguna pengarang) {
		this.pengarang = pengarang;
	}

	public String getNamaFizikal() {
		return namaFizikal;
	}

	public void setNamaFizikal(String namaFizikal) {
		this.namaFizikal = namaFizikal;
	}

	public void setNamaTandatangan(String namaTandatangan) {
		this.namaTandatangan = namaTandatangan;
	}

	public String getNamaTandatangan() {
		return namaTandatangan;
	}

    public Date getTarikhDokumen() {
        return tarikhDokumen;
    }

    public void setTarikhDokumen(Date tarikhDokumen) {
        this.tarikhDokumen = tarikhDokumen;
    }

	public void setBaru(char baru) {
		if (this.baru == 'T' && baru == 'Y'){
			throw new RuntimeException("Can't set an old dokumen back to new!");
		}
		
		this.baru = baru;
	}

	public char getBaru() {
		return baru;
	}
	 
	public void setSenaraiCapaian(List<DokumenCapaian> senaraiCapaian) {
		this.senaraiCapaian = senaraiCapaian;
	}

	public List<DokumenCapaian> getSenaraiCapaian() {
		return senaraiCapaian;
	}

	public void setDalamanNilai1(String dalamanNilai1) {
		this.dalamanNilai1 = dalamanNilai1;
	}

	public String getDalamanNilai1() {
		return dalamanNilai1;
	}

        public List<KandunganFolder> getSenaraiFolder() {
            return senaraiFolder;
        }

        public void setSenaraiFolder(List<KandunganFolder> senaraiFolder) {
            this.senaraiFolder = senaraiFolder;
        }

		public void setSenaraiSejarah(List<SejarahDokumen> senaraiSejarah) {
			this.senaraiSejarah = senaraiSejarah;
		}

		public List<SejarahDokumen> getSenaraiSejarah() {
			return senaraiSejarah;
		}
		
		public InfoAudit getInfoAudit() {
			return infoAudit;
		}

		public void setInfoAudit(InfoAudit infoAudit) {
			this.infoAudit = infoAudit;
		}

                    public String getHakmilik() {
                        return hakmilik;
                    }

                    public void setHakmilik(String hakmilik) {
                        this.hakmilik = hakmilik;
                    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }




}
