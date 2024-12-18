package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "hakmilik_wakil")
@SequenceGenerator(name = "seq_hakmilik_wakil", sequenceName = "seq_hakmilik_wakil")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true)
public class WakilHakmilik implements Serializable {
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hakmilik_wakil")
	@Column (name = "id_hw")
	private long idWakilHakmilik;
//        private String idWakilHakmilik;
	
//	@ManyToOne
//	@JoinColumn (name = "id_wakil")
//	private WakilPihak wakilPihak;

        //add by fairul
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



//	public WakilPihak getWakilPihak() {
//		return wakilPihak;
//	}
//
//	public void setWakilPihak(WakilPihak wakilPihak) {
//		this.wakilPihak = wakilPihak;
//	}

        //edit by fairul
        public WakilKuasa getWakilKuasa() {
        return wakilKuasa;
    }

    public void setWakilKuasa(WakilKuasa wakilKuasa) {
        this.wakilKuasa = wakilKuasa;
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
