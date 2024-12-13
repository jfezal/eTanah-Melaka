package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_sts_tuntut_cukai")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStatusTuntutanCukai implements Serializable {

    @Id
    @Column (name = "kod", length = 2, columnDefinition = "char(2)")
    private String kod;

    @Column (name = "nama", length = 20, unique = true)
    private String nama;
    
    @Column (name = "perihal")
    private String perihal;

    @Column (name = "AKTIF")
    private char aktif;

    @Embedded
    private InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
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

    public void setPerihal(String perihal) {
		this.perihal = perihal;
	}

	public String getPerihal() {
		return perihal;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
	
}
