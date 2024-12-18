package etanah.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "kod_klasifikasi")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKlasifikasi implements Serializable {

    @Id
    @Column (name = "kod", length = 8)
    private int kod;
    
    @Column (name = "nama", length = 50, nullable = false, unique = true)
    private String nama;
    
    @Column (name  = "perihal", length = 100)
    private String perihal;
    
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

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String setPerihal() {
        return perihal;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
