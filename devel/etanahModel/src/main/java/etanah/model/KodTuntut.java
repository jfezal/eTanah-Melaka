/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "KOD_TUNTUT")
@NamedQueries({
    @NamedQuery(name = "KodTuntut.findAll", query = "SELECT k FROM KodTuntut k")})
public class KodTuntut implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "KOD")
    private String kod;
    
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "AKTIF")
    private Character aktif;

	@ManyToOne
	@JoinColumn (name = "kod_kew_trans")
	private KodTransaksi kodKewTrans;


    @Embedded
    private InfoAudit infoAudit;


    public KodTuntut() {
    }

    public KodTuntut(String kod) {
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

    public KodTransaksi getKodKewTrans() {
        return kodKewTrans;
    }

    public void setKodKewTrans(KodTransaksi kodKewTrans) {
        this.kodKewTrans = kodKewTrans;
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
        if (!(object instanceof KodTuntut)) {
            return false;
        }
        KodTuntut other = (KodTuntut) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.KodTuntut[kod=" + kod + "]";
    }

}
