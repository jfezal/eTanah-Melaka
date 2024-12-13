package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "mohon_jentera")
@SequenceGenerator(name = "seq_mohon_jentera", sequenceName = "seq_mohon_jentera")
public class PermohonanJentera implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_jentera")
    @Column(name = "id_jentera")
    private long id;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mohon")
	private Permohonan permohonan;
	
	@ManyToOne
	@JoinColumn(name = "kod_caw")
	private KodCawangan cawangan;
	
	@Column(name = "jenis_jentera")
	private String jenisJentera;
	
	@Column(name = "no_daftar_jentera")
	private String noPendaftaranJentera;
	
	@Column(name = "kepunyaan")
	private String kepunyaan;
	
	@Column(name = "kapasiti")
	private String kapasiti;
	
	@Embedded
    private InfoAudit infoAudit;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setJenisJentera(String jenisJentera) {
		this.jenisJentera = jenisJentera;
	}

	public String getJenisJentera() {
		return jenisJentera;
	}

	public void setNoPendaftaranJentera(String noPendaftaranJentera) {
		this.noPendaftaranJentera = noPendaftaranJentera;
	}

	public String getNoPendaftaranJentera() {
		return noPendaftaranJentera;
	}

	public void setKepunyaan(String kepunyaan) {
		this.kepunyaan = kepunyaan;
	}

	public String getKepunyaan() {
		return kepunyaan;
	}

	public void setKapasiti(String kapasiti) {
		this.kapasiti = kapasiti;
	}

	public String getKapasiti() {
		return kapasiti;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

}
