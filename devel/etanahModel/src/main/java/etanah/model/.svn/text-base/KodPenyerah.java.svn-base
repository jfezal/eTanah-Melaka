package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_penyerah")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodPenyerah implements Serializable {
	
	@Id
	@Column (name = "kod", length = 2, columnDefinition = "char(2)")
	private String kod;
	
	@Column (name = "nama", nullable = false, unique = true)
	private String nama;
	
	@Column (name = "perihal")
	private String perihal;
	
	@Column (name = "aktif", length = 1)
	private char aktif;
	
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

	public char getAktif() {
		return aktif;
	}

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
