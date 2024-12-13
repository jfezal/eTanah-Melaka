package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_surat_betul")
@SequenceGenerator(name = "seq_mohon_surat_betul", sequenceName = "seq_mohon_surat_betul")
public class PermohonanSuratPembetulan implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_surat_betul")
	@Column (name = "id_betul")
	private long idPembetulan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_mohon")
    private Permohonan permohonan;
	
	@ManyToOne
	@JoinColumn (name = "id_urusan")
	private HakmilikUrusan urusan;
	
	@ManyToOne
	@JoinColumn (name = "id_wakil", nullable = false)
	private WakilKuasa wakilKuasa;
	
	@Column (name = "no_jilid")
	private String noJilid;
	
	@Column (name = "no_folio")
	private String noFolio;
	
	@Column (name = "no_ruj")
	private String noRujukan;
        
        @ManyToOne
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;
	
	@Embedded
	private InfoAudit infoAudit;


    public long getIdPembetulan() {
            return idPembetulan;
    }

    public void setIdPembetulan(long idPembetulan) {
            this.idPembetulan = idPembetulan;
    }

	
	public KodCawangan getCawangan() {
			return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setUrusan(HakmilikUrusan urusan) {
        this.urusan = urusan;
    }

    public HakmilikUrusan getUrusan() {
        return urusan;
    }

    public WakilKuasa getWakilKuasa() {
		return wakilKuasa;
	}

	public void setWakilKuasa(WakilKuasa wakilKuasa) {
		this.wakilKuasa = wakilKuasa;
	}

	public String getNoJilid() {
		return noJilid;
	}

	public void setNoJilid(String noJilid) {
		this.noJilid = noJilid;
	}

	public String getNoFolio() {
		return noFolio;
	}

	public void setNoFolio(String noFolio) {
		this.noFolio = noFolio;
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

}
