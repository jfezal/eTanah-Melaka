package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "pelaksana_waran")
@SequenceGenerator(name = "seq_pelaksana_waran", sequenceName = "seq_pelaksana_waran")
public class PelaksanaWaran implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pelaksana_waran")
	@Column (name = "id_pelaksana_waran")
	private long idPelaksanaWaran;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;

	@ManyToOne
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;

	@Column (name = "no_pengenalan")
	private String noPengenalan;


	@Column (name = "nama")
	private String nama;

	@Column (name = "alamat1", length =	40)
	private String alamat1;

	@Column (name = "alamat2", length = 40)
	private String alamat2;

	@Column (name = "alamat3", length = 40)
	private String alamat3;

	@Column (name = "alamat4", length = 40)
	private String alamat4;

	@Column (name = "poskod", length = 5)
	private String poskod;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;

	@Column (name = "kerja")
	private String kerja;


	@Column (name = "trh_lantik")
	private Date tarikhLantikan;

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

	public KodNegeri getNegeri() {
		return negeri;
	}

	public void setNegeri(KodNegeri negeri) {
		this.negeri = negeri;
	}

    public long getIdPelaksanaWaran() {
        return idPelaksanaWaran;
    }

    public void setIdPelaksanaWaran(long idPelaksanaWaran) {
        this.idPelaksanaWaran = idPelaksanaWaran;
    }

    public String getKerja() {
        return kerja;
    }

    public void setKerja(String kerja) {
        this.kerja = kerja;
    }

    public Date getTarikhLantikan() {
        return tarikhLantikan;
    }

    public void setTarikhLantikan(Date tarikhLantikan) {
        this.tarikhLantikan = tarikhLantikan;
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
}
