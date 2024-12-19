package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_kutip")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKutipan implements Serializable {

	@Id
	@Column (name = "kod")
	private char kod;
	
	@Column (name = "nama")
	private String nama;
	
	@Column (name = "aktif")
	private char aktif;
	
	@Embedded
	private InfoAudit infoAudit;

	public char getKod() {
		return kod;
	}

	public void setKod(char kod) {
		this.kod = kod;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
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
