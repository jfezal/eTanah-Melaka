/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Embedded;

/**
 *
 * @author Izam
 */

@Entity
@Table (name = "kod_jawatan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodJawatan implements Serializable {
    @Id
    @Column (name = "kod", length = 2)
    private String kod;

    @Column (name = "nama", length = 100)
    private String nama;

    @Column (name = "prioriti")
    private int prioriti;

    @Column (name = "aktif", length = 2)
    private String aktif;

    @Embedded
    private InfoAudit infoAudit;

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
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

    public int getPrioriti() {
        return prioriti;
    }

    public void setPrioriti(int prioriti) {
        this.prioriti = prioriti;
    }
}
