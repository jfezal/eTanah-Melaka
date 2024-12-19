package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "salinan_kepada")
@SequenceGenerator (name = "seq_salinan_kepada", sequenceName = "seq_salinan_kepada")
public class SalinanKepada implements Serializable  {
	
	@Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_salinan_kepada")
	@Column (name = "id_salinan_kepada")
	private long idSalinanKepada;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;

	@ManyToOne
	@JoinColumn (name = "kod_dokumen")
	private KodDokumen kodDokumen;

	@Column (name = "nama" )
	private String nama;
	
	@Column (name = "alamat1" )
	private String alamat1;
	
	@Column (name = "alamat2" )
	private String alamat2;
	
	@Column (name = "alamat3" )
	private String alamat3;
	
	@Column (name = "alamat4" )
	private String alamat4;
	
	@Column (name = "poskod")
	private String poskod;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;
        
        @Column (name = "catatan")
	private String catatan;




	@Embedded
	private InfoAudit infoAudit;

	
	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat1() {
		return alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getAlamat4() {
		return alamat4;
	}

	public void setAlamat4(String alamat4) {
		this.alamat4 = alamat4;
	}

	public String getPoskod() {
		return poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public KodNegeri getNegeri() {
		return negeri;
	}

	public void setNegeri(KodNegeri negeri) {
		this.negeri = negeri;
	}

    public long getIdSalinanKepada() {
        return idSalinanKepada;
    }

    public void setIdSalinanKepada(long idSalinanKepada) {
        this.idSalinanKepada = idSalinanKepada;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }


	

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
