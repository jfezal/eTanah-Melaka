package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "kod_cara_mohon")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodCaraPermohonan implements Serializable {

    @Id
    @Column(name = "kod", columnDefinition = "char(2)")
    private String kod;
    
    @Column(name = "nama")
    private String nama;
    
    @Column(name = "aktif")
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
