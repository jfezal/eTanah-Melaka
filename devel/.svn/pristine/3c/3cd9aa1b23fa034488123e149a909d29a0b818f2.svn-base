package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "hakmilik_asal")
@SequenceGenerator (name = "seq_hakmilik_asal", sequenceName =  "seq_hakmilik_asal")
public class HakmilikAsal implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator =  "seq_hakmilik_asal")
	@Column (name = "id_asal")
	private long idAsal;
	
	@ManyToOne 
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;
	
//	@ManyToOne
//	@JoinColumn (name = "id_sej")
//	private SejarahHakmilik hakmilikAsal;
        
        @Column (name = "id_sej")
	private String hakmilikAsal;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdAsal() {
		return idAsal;
	}

	public void setIdSebelum(long idAsal) {
		this.idAsal = idAsal;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public String getHakmilikAsal() {
		return hakmilikAsal;
	}

	public void setHakmilikAsal(String hakmilikAsal) {
		this.hakmilikAsal = hakmilikAsal;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	

}
