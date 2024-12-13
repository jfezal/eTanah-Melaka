package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_bangsa")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodBangsa implements Serializable {
    @Id
    @Column (name = "kod", length = 3)
    private String kod;
    
    @Column (name = "nama", length = 32)
    private String nama;
    
    @Column (name = "utk_idvd")
    private char untukIndividu;

    @Column (name = "bangsa")
    private char bangsa;

    @Column (name = "perihal")
    private String perihal;

    @Column (name = "aktif")
    private char aktif;
        
    @Embedded
    InfoAudit infoAudit;

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

    public char getBangsa() {
        return bangsa;
    }

    public void setBangsa(char bangsa) {
        this.bangsa = bangsa;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public void setUntukIndividu(char untukIndividu) {
		this.untukIndividu = untukIndividu;
	}

	public char getUntukIndividu() {
		return untukIndividu;
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
