package etanah.model;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

import javax.persistence.*;

/**
 *
 * @author khairil
 */
@Entity
@Table(name = "kod_jantina")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodJantina implements Serializable {

    @Id
    @Column(name = "kod", length = 8)
    private String kod;
    @Column(name = "nama", length = 256, nullable = false, unique = true)
    private String nama;
    @Column(name = "aktif")
    private char aktif;
    @Embedded
    InfoAudit infoAudit;

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
