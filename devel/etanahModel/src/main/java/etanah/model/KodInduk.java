package etanah.model;

import java.io.Serializable;

import javax.persistence.*;
/**
 *
 * @author nadia
 */
@Entity
@Table(name = "kod_induk")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodInduk implements Serializable {

    @Id
    @Column(name = "kod", length = 10)
    private String kod;
    
    @Column(name = "nama", length = 100, nullable = false, unique = true)
    private String nama;    
    
    @Column(name = "perihal")
    private String perihal;
    
    @Column(name = "aktif", length = 1)
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
