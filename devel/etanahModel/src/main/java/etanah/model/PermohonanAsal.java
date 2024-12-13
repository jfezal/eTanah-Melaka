package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_asal")
@SequenceGenerator(name = "seq_mohon_asal", sequenceName = "seq_mohon_asal")
public class PermohonanAsal implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_asal")
	@Column (name = "id_m_asal")
	private long idMAsal;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon_asal" )
	private Permohonan idPermohonanAsal;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	@ManyToOne
	@JoinColumn (name = "kod_kpsn")
	private KodKeputusan keputusan;
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdMAsal() {
        return idMAsal;
    }

    public void setIdMAsal(long idMAsal) {
        this.idMAsal = idMAsal;
    }

    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getIdPermohonanAsal() {
        return idPermohonanAsal;
    }

    public void setIdPermohonanAsal(Permohonan idPermohonanAsal) {
        this.idPermohonanAsal = idPermohonanAsal;
    }





	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public KodKeputusan getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(KodKeputusan keputusan) {
        this.keputusan = keputusan;
    }
	
	

	
}
