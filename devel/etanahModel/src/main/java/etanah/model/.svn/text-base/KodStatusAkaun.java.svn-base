package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_sts_akaun")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStatusAkaun implements Serializable{
    
    @Id
    @Column (name ="kod")
    private String kod;
        
    @Column (name = "nama", nullable = false, unique = true)
    private String nama;
    
    @Column (name = "perihal")
    private String perihal;
    
    @Column (name  = "aktif", length = 1)
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

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
}
