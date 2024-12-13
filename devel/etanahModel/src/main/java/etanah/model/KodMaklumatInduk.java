package etanah.model;

import java.io.Serializable;

import javax.persistence.*;
/**
 *
 * @author nadia
 */
@Entity
@Table(name = "kod_maklumat_induk")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodMaklumatInduk implements Serializable {

    @Id
    @Column(name = "kod")
    private String kod;
    
    @Column(name = "nama", nullable = false, unique = true)
    private String nama;
    
    @ManyToOne
    @JoinColumn(name = "kod_induk")
    private KodInduk kodInduk;
    
    @Column(name = "perihal")
    private String perihal;
    
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

    public void setKodInduk(KodInduk kodInduk) {
        this.kodInduk = kodInduk;
    }

    public KodInduk getKodInduk() {
        return kodInduk;
    }
    
    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
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
