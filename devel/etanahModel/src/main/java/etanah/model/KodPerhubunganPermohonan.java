package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_mohon_hbgn")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodPerhubunganPermohonan implements Serializable{

    @Id
    @Column (name = "KOD", length = 2, columnDefinition="char(2)")
    private String kod;

    @Column (name = "NAMA", length = 100)
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

//    public void setAktif(char aktif) {
//        this.aktif = aktif;
//    }
//
//    public char isAktif() {
//        return aktif;
//    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
}
