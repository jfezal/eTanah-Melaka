package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_bilik")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodBilik implements Serializable {
    @Id
    @Column (name = "kod")
    private int kod;
    
    @ManyToOne
    @JoinColumn (name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @Column (name = "nama", nullable = false)
    private String nama;
    
    @Column (name = "aktif")
    private char aktif;
    
    @Embedded
    InfoAudit infoAudit;

    public void setKod(int kod) {
        this.kod = kod;
    }

    public int getKod() {
        return kod;
    }

    public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
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
