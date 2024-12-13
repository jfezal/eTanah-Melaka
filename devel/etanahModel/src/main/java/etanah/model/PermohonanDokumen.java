package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_dok")
@SequenceGenerator (name = "seq_mohon_dok", sequenceName = "seq_mohon_dok")
public class PermohonanDokumen implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_dok")
	@Column (name = "id_md")
	private long idPermohonanDokumen;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mh")
	private HakmilikPermohonan hakmilikPermohonan;
	
	@ManyToOne
	@JoinColumn (name = "id_dokumen", nullable = true)
	private Dokumen dokumen;
	
	@Column (name = "no_ruj")
	private String noRujukan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPermohonanDokumen() {
		return idPermohonanDokumen;
	}

	public void setIdPermohonanDokumen(long idPermohonanDokumen) {
		this.idPermohonanDokumen = idPermohonanDokumen;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public Dokumen getDokumen() {
		return dokumen;
	}

	public void setDokumen(Dokumen dokumen) {
		this.dokumen = dokumen;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
