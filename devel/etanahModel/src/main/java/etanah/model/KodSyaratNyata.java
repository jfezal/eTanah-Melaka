package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_syarat_nyata")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodSyaratNyata implements Serializable {
    @Id
    @Column (name = "kod", length = 10)
    private String kod;

    @Column (name = "kod_syarat", length = 9)
    private String kodSyarat;
    
    @ManyToOne 
    @JoinColumn (name = "kod_katg_tanah") // TODO nullable
    private KodKategoriTanah kategoriTanah;
    
    @Column (name = "syarat", columnDefinition = "CLOB", updatable = false)
    private String syarat;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;
    
    @ManyToOne 
    @JoinColumn (name = "katg_hasil") // TODO nullable
    private KodKategoriTanah kategoriHasil;
    
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

    public void setKodSyarat(String kodSyarat) {
        this.kodSyarat = kodSyarat;
    }

    public String getKodSyarat() {
        return kodSyarat;
    }

    public void setKategoriTanah(KodKategoriTanah kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public KodKategoriTanah getKategoriTanah() {
        return kategoriTanah;
    }

    public void setSyarat(String syarat) {
		this.syarat = syarat;
	}

	public String getSyarat() {
		return syarat;
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

    public KodKategoriTanah getKategoriHasil() {
        return kategoriHasil;
    }

    public void setKategoriHasil(KodKategoriTanah kategoriHasil) {
        this.kategoriHasil = kategoriHasil;
    }
    

}
