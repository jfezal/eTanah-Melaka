package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "waran_pihak")
@SequenceGenerator(name = "seq_waran_pihak", sequenceName = "seq_waran_pihak")
public class WaranPihak implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_waran_pihak")
	@Column (name = "id_waran_pihak")
	private long idWaranPihak;
	
	@ManyToOne
	@JoinColumn (name = "id_waran" )
	private Waran waran;


	@ManyToOne
	@JoinColumn (name = "id_pihak" )
	private Pihak pihak;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdWaranPihak() {
        return idWaranPihak;
    }

    public void setIdWaranPihak(long idWaranPihak) {
        this.idWaranPihak = idWaranPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Waran getWaran() {
        return waran;
    }

    public void setWaran(Waran waran) {
        this.waran = waran;
    }


    



	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
