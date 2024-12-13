package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

// TODO

@Entity
@Table (name = "kod_rizab")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodRizab implements Serializable {
    @Id
    @Column (name = "kod")
    private int kod;
    
    @Column (name = "nama", unique = true)
    private String nama;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;
    
    @Embedded
    private InfoAudit infoAudit;

    public void setKod(int kod) {
        this.kod = kod;
    }

    public int getKod() {
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
