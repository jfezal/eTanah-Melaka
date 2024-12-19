package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_ruj_luar_peranan")
@SequenceGenerator(name = "seq_mohon_ruj_luar_peranan", sequenceName = "seq_mohon_ruj_luar_peranan")
public class PermohonanRujukanLuarPeranan implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_ruj_luar_peranan")
	@Column (name = "id_peranan")
	private long idPeranan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_ruj", nullable = false)
	private PermohonanRujukanLuar rujukan;


	@ManyToOne
	@JoinColumn (name = "peranan" )
	private KodPerananRujukanLuar kodPerananRujukanLuar;

	@Column (name = "nama", nullable = false)
	private String nama;
	
	@Column (name = "no_kp")
	private String noPengenalan;
	
	@Column (name = "jawatan")
	private String jawatan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPeranan() {
		return idPeranan;
	}

	public void setIdPeranan(long idPeranan) {
		this.idPeranan = idPeranan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public PermohonanRujukanLuar getRujukan() {
		return rujukan;
	}

	public void setRujukan(PermohonanRujukanLuar rujukan) {
		this.rujukan = rujukan;
	}

    public KodPerananRujukanLuar getKodPerananRujukanLuar() {
        return kodPerananRujukanLuar;
    }

    public void setKodPerananRujukanLuar(KodPerananRujukanLuar kodPerananRujukanLuar) {
        this.kodPerananRujukanLuar = kodPerananRujukanLuar;
    }

 

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNoPengenalan() {
		return noPengenalan;
	}

	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	public String getJawatan() {
		return jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
