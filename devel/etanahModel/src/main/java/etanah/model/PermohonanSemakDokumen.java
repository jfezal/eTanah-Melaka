package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_semak_dok")
@SequenceGenerator(name = "seq_mohon_semak_dok", sequenceName = "seq_mohon_semak_dok")
public class PermohonanSemakDokumen implements Serializable {
	
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_semak_dok")
	@Column (name = "id_semak_dok")
	private long idPermohonanSemakDokumen;

	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@ManyToOne
	@JoinColumn (name = "kod_dokumen", nullable = false)
	private KodDokumen kodDokumen;
        
        @ManyToOne
	@JoinColumn (name = "id_dokumen")
	private Dokumen dokumen;
        
        @Column (name = "ada_dok")
	private String adaDokumen;

	@Column (name = "status")
	private String status;

	@Column (name = "catatan")
	private String catatan;

	@Embedded
	private InfoAudit infoAudit;


	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

    public String getAdaDokumen() {
        return adaDokumen;
    }

    public void setAdaDokumen(String adaDokumen) {
        this.adaDokumen = adaDokumen;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdPermohonanSemakDokumen() {
        return idPermohonanSemakDokumen;
    }

    public void setIdPermohonanSemakDokumen(long idPermohonanSemakDokumen) {
        this.idPermohonanSemakDokumen = idPermohonanSemakDokumen;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
