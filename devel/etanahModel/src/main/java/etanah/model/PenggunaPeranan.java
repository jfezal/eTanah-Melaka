package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "pguna_peranan")
@SequenceGenerator(name = "seq_pguna_peranan", sequenceName = "seq_pguna_peranan")
public class PenggunaPeranan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pguna_peranan")
	@Column (name = "id_pp")
	private long idPenggunaPeranan;

	@ManyToOne
	@JoinColumn (name = "id_pguna")
	private Pengguna pengguna;

	@ManyToOne 
	@JoinColumn (name = "kod_peranan")
	private KodPeranan peranan;
        
        @ManyToOne 
	@JoinColumn (name = "kod_klas")
	private KodKlasifikasi klasifikasi;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPenggunaPeranan() {
		return idPenggunaPeranan;
	}

	public void setIdPenggunaPeranan(long idPenggunaPeranan) {
		this.idPenggunaPeranan = idPenggunaPeranan;
	}

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public KodPeranan getPeranan() {
		return peranan;
	}

	public void setPeranan(KodPeranan peranan) {
		this.peranan = peranan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public KodKlasifikasi getKlasifikasi() {
        return klasifikasi;
    }

    public void setKlasifikasi(KodKlasifikasi klasifikasi) {
        this.klasifikasi = klasifikasi;
    }
	
}
