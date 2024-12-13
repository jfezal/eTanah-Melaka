package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_katg_trans")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKategoriTransaksi implements Serializable{
    
    @Id
    @Column (name ="kod", length = 3)
    private String kod;
        
    @Column (name = "nama", length = 50, nullable = false, unique = true)
    private String nama;
    
    @Column (name = "perihal", length = 200)
    private String perihal;
    
    @Column (name = "aktif", length = 1)
    private char aktif;
    
    @Column (name = "kod_speks")
    private String kodSpeks;

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

	public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public void setKodSpeks(String kodSpeks) {
		this.kodSpeks = kodSpeks;
	}

	public String getKodSpeks() {
		return kodSpeks;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
