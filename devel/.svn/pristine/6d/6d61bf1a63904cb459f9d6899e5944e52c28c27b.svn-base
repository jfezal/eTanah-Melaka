package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_perserahan")
@SequenceGenerator(name = "seq_mohon_perserahan", sequenceName = "seq_mohon_perserahan")
public class PermohonanPerserahan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_perserahan")
	@Column (name = "id_mohon_perserahan")
	private long idMohonPerserahan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_perserahan" )
	private Permohonan idPerserahan;

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

    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public long getIdMohonPerserahan() {
        return idMohonPerserahan;
    }

    public void setIdMohonPerserahan(long idMohonPerserahan) {
        this.idMohonPerserahan = idMohonPerserahan;
    }

    public Permohonan getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(Permohonan idPerserahan) {
        this.idPerserahan = idPerserahan;
    }




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
