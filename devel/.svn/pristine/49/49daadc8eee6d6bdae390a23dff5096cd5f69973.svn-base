package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "kod_matawang")
public class KodMatawang implements Serializable {

    @Id
    @Column(name = "kod", length = 3)
    private String kod;
    
    @Column(name = "nama", length = 40, nullable = false, unique = true)
    private String nama;
    
    @Column(name = "aktif", length = 1, nullable = false)
    private char aktif;
    
    @Embedded
    private InfoAudit infoAudit;

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
