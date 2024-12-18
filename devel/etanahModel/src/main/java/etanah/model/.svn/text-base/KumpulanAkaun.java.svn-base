package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kump_akaun")
public class KumpulanAkaun implements Serializable {
	
	@Id
	@Column (name = "id_kump")
	private String idKumpulan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_pbayar")
	private Pihak pembayar;
	
	@Column (name = "catatan")
	private String catatan;
	
	@OneToMany (mappedBy = "kumpulan")
	private List<Akaun> senaraiAkaun;
	
	@Embedded
	private InfoAudit infoAudit;

	public String getIdKumpulan() {
		return idKumpulan;
	}

	public void setIdKumpulan(String idKumpulan) {
		this.idKumpulan = idKumpulan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public void setPembayar(Pihak pembayar) {
		this.pembayar = pembayar;
	}

	public Pihak getPembayar() {
		return pembayar;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public List<Akaun> getSenaraiAkaun() {
		return senaraiAkaun;
	}

	public void setSenaraiAkaun(List<Akaun> senaraiAkaun) {
		this.senaraiAkaun = senaraiAkaun;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
