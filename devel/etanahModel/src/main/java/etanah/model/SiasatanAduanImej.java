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
@Table(name = "aduan_siasat_imej")
@SequenceGenerator(name = "seq_aduan_siasat_imej", sequenceName = "seq_aduan_siasat_imej")

public class SiasatanAduanImej implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_siasat_imej")
    @Column(name = "ID_IMEJ")
    private Long idImej;


	@ManyToOne
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;


	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	@ManyToOne
	@JoinColumn (name = "ID_DOKUMEN")
	private Dokumen Dokumen;

    @Column(name = "CATATAN")
    private String catatan;

	@Embedded
	private InfoAudit infoAudit;

    public SiasatanAduanImej() {
    }

    public SiasatanAduanImej(Long idImej) {
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

    public Dokumen getDokumen() {
        return Dokumen;
    }

    public void setDokumen(Dokumen Dokumen) {
        this.Dokumen = Dokumen;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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
        if (!(object instanceof SiasatanAduanImej)) {
            return false;
        }
        SiasatanAduanImej other = (SiasatanAduanImej) object;
        if ((this.idImej == null && other.idImej != null) || (this.idImej != null && !this.idImej.equals(other.idImej))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.SiasatanAduanImej[idImej=" + idImej + "]";
    }

}
