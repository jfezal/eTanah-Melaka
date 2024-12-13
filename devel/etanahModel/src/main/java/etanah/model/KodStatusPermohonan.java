/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 *
 * @author ahmad.hisham
 */
@Entity
@Table(name = "kod_status_mohon")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStatusPermohonan implements Serializable {

    @Id
    @Column(name = "kod", columnDefinition = "char(2)")
    private String kod;
    @Column(name = "nama", length = 32, nullable = false, unique = true)
    private String nama;
    @Column(name = "aktif", length = 1)
    private char aktif;
    @Embedded
    private InfoAudit infoAudit;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
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


