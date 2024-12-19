/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_PERMIT_IMEJ")
@SequenceGenerator(name = "seq_mohon_permit_imej", sequenceName = "seq_mohon_permit_imej")
@NamedQueries({
    @NamedQuery(name = "PermohonanPermitImej.findAll", query = "SELECT m FROM PermohonanPermitImej m")})
public class PermohonanPermitImej implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_permit_imej")
    @Column(name = "ID_IMEJ")
    private Long idImej;
    
    @Column(name = "KOD_CAW")
    private String kodCaw;
    
    @Column(name = "ID_DOKUMEN")
    private long idDokumen;
    @Column(name = "PNDGN_IMEJ")
    private Character pndgnImej;
    @Column(name = "CATATAN")
    private String catatan;

	@Embedded
	private InfoAudit infoAudit;

    public PermohonanPermitImej() {
    }

    public PermohonanPermitImej(Long idImej) {
        this.idImej = idImej;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


    public Long getIdImej() {
        return idImej;
    }

    public void setIdImej(Long idImej) {
        this.idImej = idImej;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public Character getPndgnImej() {
        return pndgnImej;
    }

    public void setPndgnImej(Character pndgnImej) {
        this.pndgnImej = pndgnImej;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImej != null ? idImej.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermohonanPermitImej)) {
            return false;
        }
        PermohonanPermitImej other = (PermohonanPermitImej) object;
        if ((this.idImej == null && other.idImej != null) || (this.idImej != null && !this.idImej.equals(other.idImej))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.MohonPermitImej[idImej=" + idImej + "]";
    }

}
