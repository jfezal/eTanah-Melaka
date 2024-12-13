package etanah.model;

import java.io.Serializable;

import javax.persistence.*;
/**
 *
 * @author nadia
 */
@Entity
@Table (name = "kod_jenis_pindahmilik")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodJenisPindahmilik implements Serializable {
    @Id
    @Column (name = "kod", length = 5)
    private String kod;
    
    @Column (name = "nama", length = 100)
    private String nama;
    
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

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getPerihal() {
        return perihal;
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
