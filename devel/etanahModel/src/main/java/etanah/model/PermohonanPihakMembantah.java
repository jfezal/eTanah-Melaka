package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "mohon_pihak_bantah")
@SequenceGenerator(name = "seq_mohon_pihak_bantah", sequenceName = "seq_mohon_pihak_bantah")
public class PermohonanPihakMembantah implements Serializable {
	
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_pihak_bantah")
	@Column (name = "id_mpb")
	private long idMohonPihakBantah;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;

	@ManyToOne
	@JoinColumn (name = "kod_jantina")
	private KodJantina jantina;

	@ManyToOne
	@JoinColumn (name = "id_pihak")
	private Pihak pihak;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;
	
	@Column (name = "nama")
	private String nama;
	
	@Column (name = "alamat1")
	private String alamat1;
	
	@Column (name = "alamat2")
	private String alamat2;
	
	@Column (name = "alamat3")
	private String alamat3;
	
	@Column (name = "alamat4")
	private String alamat4;
	
	@Column (name = "poskod")
	private String poskod;

	@ManyToOne
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;
	
	@Column (name = "no_tel1")
	private String noTelefon1;

	@Column (name = "no_tel2")
	private String noTelefon2;

	@Column (name = "catatan")
	private String catatan;
	@Column (name = "kaitan")
	private String kaitan;
	@Column (name = "katg_pihak")
	private String kategoriPihakMembantah;
	@Column (name = "trh_bantah")
	private Date tarikhBantahan;
        
        @Column (name = "STS_WARIS")
        private String statusWaris;

	@Embedded
	private InfoAudit infoAudit;

	
	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodJenisPengenalan getJenisPengenalan() {
		return jenisPengenalan;
	}

	public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
		this.jenisPengenalan = jenisPengenalan;
	}

	public String getNoPengenalan() {
		return noPengenalan;
	}

	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
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


	public String getNoTelefon1() {
		return noTelefon1;
	}

	public void setNoTelefon1(String noTelefon1) {
		this.noTelefon1 = noTelefon1;
	}

	public String getNoTelefon2() {
		return noTelefon2;
	}

	public void setNoTelefon2(String noTelefon2) {
		this.noTelefon2 = noTelefon2;
	}

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public KodJantina getJantina() {
        return jantina;
    }

    public void setJantina(KodJantina jantina) {
        this.jantina = jantina;
    }

    public String getKaitan() {
        return kaitan;
    }

    public void setKaitan(String kaitan) {
        this.kaitan = kaitan;
    }

    public String getKategoriPihakMembantah() {
        return kategoriPihakMembantah;
    }

    public void setKategoriPihakMembantah(String kategoriPihakMembantah) {
        this.kategoriPihakMembantah = kategoriPihakMembantah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Date getTarikhBantahan() {
        return tarikhBantahan;
    }

    public void setTarikhBantahan(Date tarikhBantahan) {
        this.tarikhBantahan = tarikhBantahan;
    }
	

    public long getIdMohonPihakBantah() {
        return idMohonPihakBantah;
    }

    public void setIdMohonPihakBantah(long idMohonPihakBantah) {
        this.idMohonPihakBantah = idMohonPihakBantah;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public String getStatusWaris() {
        return statusWaris;
    }

    public void setStatusWaris(String statusWaris) {
        this.statusWaris = statusWaris;
    }
}
