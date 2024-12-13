package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_peguam")
@SequenceGenerator(name = "seq_mohon_peguam", sequenceName = "seq_mohon_peguam")
public class PermohonanPeguam implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_peguam")
	@Column (name = "id_mohon_peguam")
	private long idMohonPeguam;
	
	@ManyToOne
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;

	@ManyToOne
	@JoinColumn (name = "id_peguam" )
	private Peguam peguam;


	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

        @Column (name = "aktif")
        private String aktif;


	
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }


    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public long getIdMohonPeguam() {
        return idMohonPeguam;
    }

    public void setIdMohonPeguam(long idMohonPeguam) {
        this.idMohonPeguam = idMohonPeguam;
    }

    public Peguam getPeguam() {
        return peguam;
    }

    public void setPeguam(Peguam peguam) {
        this.peguam = peguam;
    }



	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
