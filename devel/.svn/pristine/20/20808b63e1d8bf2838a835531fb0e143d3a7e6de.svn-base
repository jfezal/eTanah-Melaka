package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_jadual_kedua")
public class KodJadualKedua implements Serializable {

	@Id
	@Column (name = "kod")
	private String kod;
	
	@Column (name = "nama")
	private String nama;
	
	@Column (name = "no_ruj")
	private String noRujukan;
	
	@Column (name = "aktif")
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

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
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
