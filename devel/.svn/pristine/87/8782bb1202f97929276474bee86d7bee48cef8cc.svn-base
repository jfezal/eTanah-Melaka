package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity 
@Table (name = "kod_status_kew_dokumen")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStatusDokumenKewangan implements Serializable {

	@Id
	@Column (name = "kod")
	private String kod;
	
	@Column (name = "nama", unique = true)
	private String nama;
	
	@Column (name = "perihal")
	private String perihal;
	
	@Embedded
	private InfoAudit infoAudit;

	
	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPerihal() {
		return perihal;
	}

	public void setPerihal(String perihal) {
		this.perihal = perihal;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	
}
