package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_cara_bayar")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodCaraBayaran implements Serializable {
    @Id
    @Column (name = "kod")
    private String kod;
    
    @Column (name = "nama")
    private String nama;
    
    @Column (name = "aktif")
    private char aktif;
    
    @Column (name = "KODMYETAPP")
    private String kodMyEtapp;
    
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

    public String getKodMyEtapp() {
        return kodMyEtapp;
    }

    public void setKodMyEtapp(String kodMyEtapp) {
        this.kodMyEtapp = kodMyEtapp;
    }
    
    
}
