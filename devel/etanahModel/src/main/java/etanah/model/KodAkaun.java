package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_akaun")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodAkaun implements Serializable{
    
    @Id
    @Column (name = "kod", length = 8)
    private String kod;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_katg_akaun")
    private KodKategoriAkaun kategoriAkaun;
    
    @Column (name = "nama", length = 256, nullable = false, unique = true)
    private String nama;
        
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

	/**
	 * @param kategoriAkaun the kategoriAkaun to set
	 */
	public void setKategoriAkaun(KodKategoriAkaun kategoriAkaun) {
		this.kategoriAkaun = kategoriAkaun;
	}

	/**
	 * @return the kategoriAkaun
	 */
	public KodKategoriAkaun getKategoriAkaun() {
		return kategoriAkaun;
	}
}
