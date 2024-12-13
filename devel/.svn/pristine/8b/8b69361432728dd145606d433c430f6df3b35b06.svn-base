package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "mohon_fasa_log")
@SequenceGenerator(name = "seq_mohon_fasa_log", sequenceName = "seq_mohon_fasa_log")
public class FasaPermohonanLog implements Serializable {
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_fasa_log")
	@Column (name = "id_log")
	private long idLog;
	
	@ManyToOne
	@JoinColumn (name = "id_fasa", nullable = false)
	private FasaPermohonan fasa;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "kod_kpsn")
	private KodKeputusan keputusan;
	
	@Column (name = "ulasan")
	private String ulasan;
	
	@Column (name = "trh_hantar")
	private Date tarikhHantar;

	@Column (name = "no_ruj")
	private String nomborRujukan;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdLog() {
		return idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	public FasaPermohonan getFasa() {
		return fasa;
	}

	public void setFasa(FasaPermohonan fasa) {
		this.fasa = fasa;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodKeputusan getKeputusan() {
		return keputusan;
	}

	public void setKeputusan(KodKeputusan keputusan) {
		this.keputusan = keputusan;
	}

	public String getUlasan() {
		return ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhHantar() {
		return tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	

}
