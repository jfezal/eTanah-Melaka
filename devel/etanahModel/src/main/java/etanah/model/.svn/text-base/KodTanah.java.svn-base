package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_tanah")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodTanah implements Serializable{
    
    @Id
    @Column (name = "KOD", length = 2)
    private String kod;

    @Column (name = "NAMA", length = 30)
    private String nama;

    @Column (name = "AKTIF")
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
}
