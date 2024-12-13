package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_milik")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodPemilikan implements Serializable {
	@Id
	@Column(name = "kod", length = 1)
	private Character kod;

	@Column(name = "nama", nullable = false, unique = true)
	private String nama;

	@Column(name = "aktif", length = 1)
	private char aktif;

	@Embedded
	private InfoAudit infoAudit;

	public void setKod(Character kod) {
		this.kod = kod;
	}

	public Character getKod() {
		return kod;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNama() {
		return nama;
	}

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public char isAktif() {
		return aktif;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

}
