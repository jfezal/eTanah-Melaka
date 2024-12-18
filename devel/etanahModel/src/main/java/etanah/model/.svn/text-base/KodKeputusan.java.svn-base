/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author abu.mansur
 */
@Entity
@Table(name = "KOD_KPSN")
@NamedQueries({@NamedQuery(name = "KodKeputusan.findAll", query = "SELECT k FROM KodKeputusan k")})
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKeputusan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "KOD")
    private String kod;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "PERLU_ULAS")
    private Character perluUlas;
    @Column(name = "AKTIF")
    private Character aktif;
    @Embedded
    private InfoAudit infoAudit;    

    public KodKeputusan() {
    }

    public KodKeputusan(String kod) {
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

    public Character getPerluUlas() {
        return perluUlas;
    }

    public void setPerluUlas(Character perluUlas) {
        this.perluUlas = perluUlas;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kod != null ? kod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KodKeputusan)) {
            return false;
        }
        KodKeputusan other = (KodKeputusan) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.dao.KodKeputusan[kod=" + kod + "]";
    }

}
