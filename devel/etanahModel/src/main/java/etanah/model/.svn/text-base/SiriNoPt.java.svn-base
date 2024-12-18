package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "siri_no_pt")
@SequenceGenerator(name = "seq_siri_no_pt", sequenceName = "seq_siri_no_pt")
public class SiriNoPt implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_siri_no_pt")
	@Column (name = "id_siri_no_pt")
	private long idSiriNoPt;
	
	@ManyToOne
	@JoinColumn (name = "kod_bpm" )
	private KodBandarPekanMukim kodBandarPekanMukim;


	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


    @Column(name = "no_pt")
    private Integer noPt;
	
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Integer getNoPt() {
        return noPt;
    }

    public void setNoPt(Integer noPt) {
        this.noPt = noPt;
    }

    

    public long getIdSiriNoPt() {
        return idSiriNoPt;
    }

    public void setIdSiriNoPt(long idSiriNoPt) {
        this.idSiriNoPt = idSiriNoPt;
    }

    public KodBandarPekanMukim getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
