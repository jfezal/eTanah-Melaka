/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "KOD_KEPUTUSAN_MAHKAMAH")
@NamedQueries({
    @NamedQuery(name = "KodKeputusanMahkamah.findAll", query = "SELECT k FROM KodKeputusanMahkamah k")})
public class KodKeputusanMahkamah implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "KOD")
    private String kod;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "AKTIF")
    private Character aktif;

    public KodKeputusanMahkamah() {
    }

    @Embedded
    private InfoAudit infoAudit;

    public KodKeputusanMahkamah(String kod) {
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


    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
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
        if (!(object instanceof KodKeputusanMahkamah)) {
            return false;
        }
        KodKeputusanMahkamah other = (KodKeputusanMahkamah) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.KodKeputusanMahkamah[kod=" + kod + "]";
    }

}
