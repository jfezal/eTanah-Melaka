package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kkuasa_enkuiri")
@SequenceGenerator (name = "seq_kkuasa_enkuiri", sequenceName = "seq_kkuasa_enkuiri")
public class EnkuiriPenguatkuasaan implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_enkuiri")
        @Column (name = "id_enkuiri")
	private long idEnkuiri;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@Column (name = "tmpt")
	private String tempat;
	
	@Column (name = "trh_mula", nullable = false)
	private Date tarikhMula;
	
	@Column (name = "tujuan", nullable = false)
	private String tujuan;
	
	@ManyToOne
	@JoinColumn (name = "dijln")
	private Pengguna dijalankanOleh;
	
	@Column (name = "catatan")
	private String catatan;

	@Column (name = "ulasan")
	private String ulasan;
        
	@Column (name = "sts")
	private String status;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "sts_enkuiri")
	private KodStatusEnkuiriPenguatkuasaan kodStatusEnkuiriPenguatkuasaan;
        
	@OneToMany (mappedBy = "enkuiri")
	private List<EnkuiriPenguatkuasaanKehadiran> senaraiKehadiran;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "jenis_enkuiri")
	private KodJenisEnkuiri kodJenisEnkuiri;


	@Embedded
	private InfoAudit infoAudit;

	public long getIdEnkuiri() {
		return idEnkuiri;
	}

	public void setIdEnkuiri(long idEnkuiri) {
		this.idEnkuiri = idEnkuiri;
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

	public void setTempat(String tempat) {
		this.tempat = tempat;
	}

	public String getTempat() {
		return tempat;
	}

	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public String getTujuan() {
		return tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public Pengguna getDijalankanOleh() {
		return dijalankanOleh;
	}

	public void setDijalankanOleh(Pengguna dijalankanOleh) {
		this.dijalankanOleh = dijalankanOleh;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

        
        
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KodStatusEnkuiriPenguatkuasaan getKodStatusEnkuiriPenguatkuasaan() {
        return kodStatusEnkuiriPenguatkuasaan;
    }

    public void setKodStatusEnkuiriPenguatkuasaan(KodStatusEnkuiriPenguatkuasaan kodStatusEnkuiriPenguatkuasaan) {
        this.kodStatusEnkuiriPenguatkuasaan = kodStatusEnkuiriPenguatkuasaan;
    }

    
    
	public List<EnkuiriPenguatkuasaanKehadiran> getSenaraiKehadiran() {
		return senaraiKehadiran;
	}

	public void setSenaraiKehadiran(
			List<EnkuiriPenguatkuasaanKehadiran> senaraiKehadiran) {
		this.senaraiKehadiran = senaraiKehadiran;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public KodJenisEnkuiri getKodJenisEnkuiri() {
        return kodJenisEnkuiri;
    }

    public void setKodJenisEnkuiri(KodJenisEnkuiri kodJenisEnkuiri) {
        this.kodJenisEnkuiri = kodJenisEnkuiri;
    }

   

}
