package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "stor_rampas")
@SequenceGenerator(name = "seq_stor_rampas", sequenceName = "seq_stor_rampas")
public class StorRampasan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_stor_rampas")
	@Column (name = "id_stor_rampas")
	private long idPelaksanaWaran;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;

	@Column (name = "alamat1" )
	private String alamat1;

	@Column (name = "alamat2" )
	private String alamat2;

	@Column (name = "alamat3" )
	private String alamat3;

	@Column (name = "alamat4" )
	private String alamat4;

	@Column (name = "poskod" )
	private String poskod;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;


	@Embedded
	private InfoAudit infoAudit;


	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
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
