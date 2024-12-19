/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "KOD_STS_MOHON_PIHAK")
@NamedQueries({
    @NamedQuery(name = "KodStatusMohonPihak.findAll", query = "SELECT k FROM KodStatusMohonPihak k")})
public class KodStatusMohonPihak implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "KOD")
    private String kod;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "AKTIF")
    private Character aktif;
    @Column(name = "DIMASUK")
    private String dimasuk;
    @Column(name = "DIKKINI")
    private String dikkini;

    public KodStatusMohonPihak() {
    }

    public KodStatusMohonPihak(String kod) {
        this.kod = kod;
    }

  
    @Embedded
    private InfoAudit infoAudit;

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

    public String getDimasuk() {
        return dimasuk;
    }

    public void setDimasuk(String dimasuk) {
        this.dimasuk = dimasuk;
    }


    public String getDikkini() {
        return dikkini;
    }

    public void setDikkini(String dikkini) {
        this.dikkini = dikkini;
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
        if (!(object instanceof KodStatusMohonPihak)) {
            return false;
        }
        KodStatusMohonPihak other = (KodStatusMohonPihak) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.KodStsPampasan[kod=" + kod + "]";
    }

}
