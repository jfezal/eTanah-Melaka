package etanah.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;


@Entity
@Table (name = "kod_dokumen")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodDokumen implements Serializable{
    
    @Id
    @Column (name = "kod", length = 8)
    private String kod;
    
    @Column (name = "nama", length = 256, nullable = false, unique = true)
    private String nama;
    
    @Column (name = "kwl_capai")
    private char kawalCapaian;
    
    @Column (name = "pjana", length = 50)
    private String penjana;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;
            
     @Column (name = "kod_myetapp")
    private String kodMyEtapp;
     
     @Column (name = "fail")
    private Blob fail;
    
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

    public char getKawalCapaian() {
		return kawalCapaian;
	}

	public void setKawalCapaian(char kawalCapaian) {
		this.kawalCapaian = kawalCapaian;
	}

	public String getPenjana() {
		return penjana;
	}

	public void setPenjana(String penjana) {
		this.penjana = penjana;
	}

	public char getAktif() {
		return aktif;
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

    public String getKodMyEtapp() {
        return kodMyEtapp;
    }

    public void setKodMyEtapp(String kodMyEtapp) {
        this.kodMyEtapp = kodMyEtapp;
    }

    public Blob getFail() {
        return fail;
    }

    public void setFail(Blob fail) {
        this.fail = fail;
    }
    
    
}
