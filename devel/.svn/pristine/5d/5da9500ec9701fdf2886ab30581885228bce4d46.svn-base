package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "surat_ruj_luar")
@SequenceGenerator(name = "seq_surat_ruj_luar", sequenceName = "seq_surat_ruj_luar")
public class SuratRujukanLuar implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_surat_ruj_luar")
	@Column (name = "id_surat_ruj_luar")
	private long idSuratRujLuar;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_ruj" )
	private PermohonanRujukanLuar permohonanRujukanLuar;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_dokumen" )
	private KodDokumen kodDokumen;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	@ManyToOne
	@JoinColumn (name = "id_dokumen")
	private Dokumen dokumen;

	
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public long getIdSuratRujLuar() {
        return idSuratRujLuar;
    }

    public void setIdSuratRujLuar(long idSuratRujLuar) {
        this.idSuratRujLuar = idSuratRujLuar;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }





	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
