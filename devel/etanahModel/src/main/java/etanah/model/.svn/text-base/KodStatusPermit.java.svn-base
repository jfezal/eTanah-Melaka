package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_sts_permit")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStatusPermit implements Serializable {
    @Id
    @Column (name = "kod", length = 2, columnDefinition = "char(1)")
    private char kod;

    @Column (name = "nama", length = 50, unique = true)
    private String nama;
    
    @Column (name = "perihal")
    private String perihal;

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
