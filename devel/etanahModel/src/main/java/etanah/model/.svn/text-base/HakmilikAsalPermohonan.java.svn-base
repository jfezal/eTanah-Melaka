package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_hakmilik_asal")
@SequenceGenerator (name = "seq_mohon_hakmilik_asal", sequenceName = "seq_mohon_hakmilik_asal")
public class HakmilikAsalPermohonan implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_hakmilik_asal")
	@Column (name = "id_mha")
	private long idHakmilikAsalPermohonan;
	
	@ManyToOne
	@JoinColumn (name = "id_mh")
	private HakmilikPermohonan hakmilikPermohonan;
	
	@ManyToOne
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;
	
//	@ManyToOne
//	@JoinColumn (name = "id_sej_hakmilik")
//	private SejarahHakmilik hakmilikSejarah;
        
        @Column (name = "id_sej_hakmilik")
	private String hakmilikSejarah;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdHakmilikAsalPermohonan() {
		return idHakmilikAsalPermohonan;
	}

	public void setIdHakmilikAsalPermohonan(long idHakmilikAsalPermohonan) {
		this.idHakmilikAsalPermohonan = idHakmilikAsalPermohonan;
	}

	public HakmilikPermohonan getHakmilikPermohonan() {
		return hakmilikPermohonan;
	}

	public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
		this.hakmilikPermohonan = hakmilikPermohonan;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public String getHakmilikSejarah() {
		return hakmilikSejarah;
	}

	public void setHakmilikSejarah(String hakmilikSejarah) {
		this.hakmilikSejarah = hakmilikSejarah;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
