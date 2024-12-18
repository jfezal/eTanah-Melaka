package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "mohon_jubl")
@SequenceGenerator(name = "seq_mohon_jubl", sequenceName = "seq_mohon_jubl")
public class PermohonanJUBL implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_jubl")
        @Column (name = "id_mj")
	private long idPermohonanJUBL;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	// syarikat
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_jubl")
	private JUBL jurukur;
	
	@Column (name = "nama_jubl")
	private String namaJUBL;
	
	@Column (name = "trh_ruj")
	private Date tarikhRujukan;
	
	@Column (name = "no_ruj")
	private String noRujukan;
	
	@Column (name = "ulasan")
	private String ulasan;
	
	@Column (name = "catatan")
	private String catatan;
        
         @Column (name = "trh_fee")
        private Date tarikhFee;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPermohonanJUBL() {
		return idPermohonanJUBL;
	}

	public void setIdPermohonanJUBL(long idPermohonanJUBL) {
		this.idPermohonanJUBL = idPermohonanJUBL;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public JUBL getJurukur() {
		return jurukur;
	}

	public void setJurukur(JUBL jurukur) {
		this.jurukur = jurukur;
	}

	public String getNamaJUBL() {
		return namaJUBL;
	}

	public void setNamaJUBL(String namaJUBL) {
		this.namaJUBL = namaJUBL;
	}

	public Date getTarikhRujukan() {
		return tarikhRujukan;
	}

	public void setTarikhRujukan(Date tarikhRujukan) {
		this.tarikhRujukan = tarikhRujukan;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public String getUlasan() {
		return ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public Date getTarikhFee() {
        return tarikhFee;
    }

    public void setTarikhFee(Date tarikhFee) {
        this.tarikhFee = tarikhFee;
    }
	
}
