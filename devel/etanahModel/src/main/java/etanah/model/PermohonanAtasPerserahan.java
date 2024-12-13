package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_atas_urusan")
@SequenceGenerator (name = "seq_mohon_atas_urusan", sequenceName = "seq_mohon_atas_urusan")
public class PermohonanAtasPerserahan implements Serializable {
	
	@Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_atas_urusan")
	@Column (name = "id_au")
	private long idAtasUrusan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;
	
	@ManyToOne (fetch= FetchType.LAZY)
	@JoinColumn (name = "id_urusan")
	private HakmilikUrusan urusan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon_baru")
	private Permohonan permohonanBaru;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdAtasUrusan() {
		return idAtasUrusan;
	}

	public void setIdAtasUrusan(long idAtasUrusan) {
		this.idAtasUrusan = idAtasUrusan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public HakmilikUrusan getUrusan() {
		return urusan;
	}

	public void setUrusan(HakmilikUrusan urusan) {
		this.urusan = urusan;
	}

	public void setPermohonanBaru(Permohonan permohonanBaru) {
		this.permohonanBaru = permohonanBaru;
	}

	public Permohonan getPermohonanBaru() {
		return permohonanBaru;
	}

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
