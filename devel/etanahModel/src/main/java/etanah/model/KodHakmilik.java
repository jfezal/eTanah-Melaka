package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_hakmilik")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodHakmilik implements Serializable {
    @Id
    @Column (name = "kod")
    private String kod;
    
    @Column (name = "nama", unique = true)
    private String nama;
    
    @ManyToOne
    @JoinColumn (name = "kod_dokumen")
    private KodDokumen kodDokumen;

    @Column (name = "milik_daerah", columnDefinition = "char(1)")
    private char milikDaerah;
    
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

    public void setKodDokumen(KodDokumen kodDokumen) {
		this.kodDokumen = kodDokumen;
	}

	public KodDokumen getKodDokumen() {
		return kodDokumen;
	}

	public void setMilikDaerah(char milikDaerah) {
		this.milikDaerah = milikDaerah;
	}

	public char getMilikDaerah() {
		return milikDaerah;
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
