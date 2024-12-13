/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author md.nurfikri
 * @author hishammk
 */
@Entity
@Table(name = "KOD_SEKSYEN")
@NamedQueries({@NamedQuery(name = "KodSeksyen.findAll", query = "SELECT k FROM KodSeksyen k")})
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodSeksyen implements Serializable {
	
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "KOD")
    private int kod;

    @ManyToOne
    @JoinColumn (name = "kod_bpm")
    private KodBandarPekanMukim kodBandarPekanMukim;
    
    @Basic(optional = false)
    @Column(name = "SEKSYEN", length = 3)
    private String seksyen;
    
    @Column(name = "NAMA")
    private String nama;
    
    @Column(name = "AKTIF")
    private String aktif;
    
    @Embedded
    private InfoAudit infoAudit;

	public int getKod() {
		return kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	public KodBandarPekanMukim getKodBandarPekanMukim() {
		return kodBandarPekanMukim;
	}

	public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
		this.kodBandarPekanMukim = kodBandarPekanMukim;
	}

	public String getSeksyen() {
		return seksyen;
	}

	public void setSeksyen(String seksyen) {
		this.seksyen = seksyen;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAktif() {
		return aktif;
	}

	public void setAktif(String aktif) {
		this.aktif = aktif;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
    
}
