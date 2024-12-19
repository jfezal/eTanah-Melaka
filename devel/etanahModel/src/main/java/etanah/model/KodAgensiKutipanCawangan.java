package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_agensi_kutipan_caw")
public class KodAgensiKutipanCawangan implements Serializable {

	@Id
	@Column (name = "kod")
	private String kod;
	
	@ManyToOne
	@JoinColumn (name = "kod_agensi_kutipan", nullable = false)
	private KodAgensiKutipan agensiKutipan;
	
	@Column (name = "nama", nullable = false, unique = true)
	private String nama;
	
	@Column (name = "lokasi")
	private String lokasi;
	
	@Embedded
	private InfoAudit infoAudit;

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public KodAgensiKutipan getAgensiKutipan() {
		return agensiKutipan;
	}

	public void setAgensiKutipan(KodAgensiKutipan agensiKutipan) {
		this.agensiKutipan = agensiKutipan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
