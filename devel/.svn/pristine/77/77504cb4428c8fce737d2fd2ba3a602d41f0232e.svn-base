/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author md.nurfikri
 */
@Entity
@Table(name = "kod_jenis_laluan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodJenisLaluan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KOD")
    private String kod;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "AKTIF")
    private Character aktif;
    
    @Embedded
    private InfoAudit infoAudit;

    public KodJenisLaluan() {
    }

    public KodJenisLaluan(String kod) {
        this.kod = kod;
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

    public Character getAktif() {
        return aktif;
    }

    public void setAktif(Character aktif) {
        this.aktif = aktif;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    
 

}
