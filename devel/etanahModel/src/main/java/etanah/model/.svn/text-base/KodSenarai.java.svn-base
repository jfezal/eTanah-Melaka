package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Jenis senarai.
 *
 */
@Entity
@Table (name = "kod_senarai")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodSenarai implements Serializable{

    @Id
    @Column (name = "kod")
    private String kod;

    @Column (name = "nama")
    private String nama;

    @ManyToOne
    @JoinColumn (name = "kod_jabatan")
    private KodJabatan kodJabatan;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;

    @Embedded
    private InfoAudit infoAudit;

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

    public KodJabatan getKodJabatan() {
        return kodJabatan;
    }

    public void setKodJabatan(KodJabatan kodJabatan) {
        this.kodJabatan = kodJabatan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
}
