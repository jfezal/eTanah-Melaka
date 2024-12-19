package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "wakil_kuasa_hakmilik")
@SequenceGenerator(name = "seq_hakmilik_wakil", sequenceName = "seq_hakmilik_wakil")
public class WakilKuasaHakmilik implements Serializable {
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hakmilik_wakil")
	@Column (name = "id_wkh")
	private long idWakilHakmilik;
//        private String idWakilHakmilik;
	
	@ManyToOne
	@JoinColumn (name = "id_wakil")
	private WakilKuasa wakilKuasa;
	
	@ManyToOne
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;
	
	@Column (name = "aktif")
	private char aktif;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdWakilHakmilik() {
		return idWakilHakmilik;
	}

	public void setIdWakilHakmilik(long idWakilHakmilik) {
		this.idWakilHakmilik = idWakilHakmilik;
	}

	public WakilKuasa getWakilKuasa() {
		return wakilKuasa;
	}

	public void setWakilKuasa(WakilKuasa wakil) {
		this.wakilKuasa = wakil;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public char getAktif() {
		return aktif;
	}

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
