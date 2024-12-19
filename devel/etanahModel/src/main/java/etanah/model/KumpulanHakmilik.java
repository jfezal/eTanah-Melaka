package etanah.model;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kump_hakmilik")
public class KumpulanHakmilik implements Serializable {

	@Id
	@Column (name = "id_kump")
	private long idKumpulan;

	@Column (name = "no_warta", nullable = true)
	private String noWarta;
	
	@Column (name = "trh_warta")
	private Date tarikhWarta;
	
	@OneToMany (mappedBy = "kumpulan")
	private List<Hakmilik> senaraiHakmilik;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdKumpulan() {
		return idKumpulan;
	}

	public void setIdKumpulan(long idKumpulan) {
		this.idKumpulan = idKumpulan;
	}

	public String getNoWarta() {
		return noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public Date getTarikhWarta() {
		return tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public List<Hakmilik> getSenaraiHakmilik() {
		return senaraiHakmilik;
	}

	public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
