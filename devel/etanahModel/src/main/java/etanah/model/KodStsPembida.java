/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

/**
 *
 * @author faidzal
 */

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_sts_pembida")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStsPembida implements Serializable {
    
    @Id
    @Column (name = "kod")
    private String kod;
    
    @Column (name = "nama")
    private String nama;
    
    @Column (name  = "aktif", length = 1)
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
