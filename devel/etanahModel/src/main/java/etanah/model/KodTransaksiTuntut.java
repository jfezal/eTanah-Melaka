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
@Table(name = "KOD_TRANS_TUNTUT")
@NamedQueries({
    @NamedQuery(name = "KodTransaksiTuntut.findAll", query = "SELECT k FROM KodTransaksiTuntut k")})
public class KodTransaksiTuntut implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "KOD")
    private String kod;
    
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "AKTIF")
    private Character aktif;

        @Column(name = "tempoh")
        private Integer tempoh;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "tempoh_uom" )
	private KodUOM tempohUom;



    @Embedded
    private InfoAudit infoAudit;


    public KodTransaksiTuntut() {
    }

    public KodTransaksiTuntut(String kod) {
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

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public KodUOM getTempohUom() {
        return tempohUom;
    }

    public void setTempohUom(KodUOM tempohUom) {
        this.tempohUom = tempohUom;
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
        if (!(object instanceof KodTransaksiTuntut)) {
            return false;
        }
        KodTransaksiTuntut other = (KodTransaksiTuntut) object;
        if ((this.kod == null && other.kod != null) || (this.kod != null && !this.kod.equals(other.kod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.KodTransaksiTuntut[kod=" + kod + "]";
    }

}
