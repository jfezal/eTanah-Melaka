package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "hakmilik_sblm")
@SequenceGenerator (name = "seq_hakmilik_sblm", sequenceName =  "seq_hakmilik_sblm")
public class HakmilikSebelum implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator =  "seq_hakmilik_sblm")
	@Column (name = "id_sblm")
	private long idSebelum;
	
	@ManyToOne 
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;
	
//	@ManyToOne
//	@JoinColumn (name = "id_hakmilik_sblm")
//	private SejarahHakmilik hakmilikSebelum;
        
        @Column (name = "id_hakmilik_sblm")
	private String hakmilikSebelum;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdSebelum() {
		return idSebelum;
	}

	public void setIdSebelum(long idSebelum) {
		this.idSebelum = idSebelum;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public String getHakmilikSebelum() {
		return hakmilikSebelum;
	}

	public void setHakmilikSebelum(String hakmilikSebelum) {
		this.hakmilikSebelum = hakmilikSebelum;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	

}
