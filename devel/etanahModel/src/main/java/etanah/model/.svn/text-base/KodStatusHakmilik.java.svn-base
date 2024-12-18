/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 *
 * @author khairil
 */
@Entity
@Table(name = "kod_status_hakmilik")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStatusHakmilik implements Serializable {

    @Id
    @Column(name = "kod", length = 2)
    private String kod;
    @Column(name = "nama", length = 32, nullable = false, unique = true)
    private String nama;
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


