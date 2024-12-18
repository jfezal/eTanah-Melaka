package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kkuasa_op_agensi")
@SequenceGenerator(name = "seq_kkuasa_op_agensi", sequenceName = "seq_kkuasa_op_agensi")
public class OperasiAgensi implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_op_agensi")
        @Column (name = "id_koa")
	private long idOperasiAgensi;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_op")
	private OperasiPenguatkuasaan operasi;
	
	@ManyToOne
	@JoinColumn (name = "kod_agensi", nullable = false)
	private KodAgensi agensi;
	
	@Column (name = "catatan")
	private String catatan;
        
	@Column (name = "keterangan_operasi")
	private String keteranganOperasi;
	
	/**
	 * Contact person
	 */
	@Column (name = "hbgn_nama")
	private String namaHubungan;
	
	@Column (name = "hbgn_tel")
	private String noTelefonHubungan;

	@Column (name = "hbgn_email")
	private String emailHubungan;
	
        @Column (name = "alamat_agensi")
	private String alamatAgensi;

	@Column (name = "hbgn_jawatan")
	private String jawatanHubungan;


	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan kodPengenalan;

	@Column (name = "no_pengenalan")
	private String noPengenalan;
        
        @Column (name = "no_ruj")
	private String noRujukan;
        
        @Column (name = "trh_ruj")
	private Date tarikhRujukan;        
      
        @OneToMany (mappedBy = "operasiAgensi")
	private List<BarangRampasan> senaraiBarangRampasan; 

	@Embedded
	private InfoAudit infoAudit;

	public long getIdOperasiAgensi() {
		return idOperasiAgensi;
	}

	public void setIdOperasiAgensi(long idOperasiAgensi) {
		this.idOperasiAgensi = idOperasiAgensi;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public void setOperasi(OperasiPenguatkuasaan operasi) {
		this.operasi = operasi;
	}

	public OperasiPenguatkuasaan getOperasi() {
		return operasi;
	}

	public KodAgensi getAgensi() {
		return agensi;
	}

	public void setAgensi(KodAgensi agensi) {
		this.agensi = agensi;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

    public String getKeteranganOperasi() {
        return keteranganOperasi;
    }

    public void setKeteranganOperasi(String keteranganOperasi) {
        this.keteranganOperasi = keteranganOperasi;
    }

        
        
        
	public void setNamaHubungan(String namaHubungan) {
		this.namaHubungan = namaHubungan;
	}

	public String getNamaHubungan() {
		return namaHubungan;
	}

	public void setNoTelefonHubungan(String noTelefonHubungan) {
		this.noTelefonHubungan = noTelefonHubungan;
	}

	public String getNoTelefonHubungan() {
		return noTelefonHubungan;
	}

	public void setEmailHubungan(String emailHubungan) {
		this.emailHubungan = emailHubungan;
	}

	public String getEmailHubungan() {
		return emailHubungan;
	}

    public String getAlamatAgensi() {
        return alamatAgensi;
    }

    public void setAlamatAgensi(String alamatAgensi) {
        this.alamatAgensi = alamatAgensi;
    }

    public String getJawatanHubungan() {
        return jawatanHubungan;
    }

    public void setJawatanHubungan(String jawatanHubungan) {
        this.jawatanHubungan = jawatanHubungan;
    }

    public KodJenisPengenalan getKodPengenalan() {
        return kodPengenalan;
    }

    public void setKodPengenalan(KodJenisPengenalan kodPengenalan) {
        this.kodPengenalan = kodPengenalan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

        
	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Date getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(Date tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }
	

}
