package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_sekatan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodSekatanKepentingan implements Serializable {
	
    @Id
    @Column (name = "kod", length = 10)
    private String kod;

    @Column (name = "kod_sekatan", length = 9, updatable = false)
    private String kodSekatan;
    
    @Column (name = "sekatan", columnDefinition = "CLOB", updatable = false)
    private String sekatan;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;
    
    @Embedded
    private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
    
    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setSekatan(String sekatan) {
		this.sekatan = sekatan;
	}

	public String getSekatan() {
		return sekatan;
	}

	public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
