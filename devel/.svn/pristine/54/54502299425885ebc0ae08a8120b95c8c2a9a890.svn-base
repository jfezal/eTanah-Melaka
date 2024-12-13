package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "mohon_saksi")
@SequenceGenerator(name = "seq_mohon_saksi", sequenceName = "seq_mohon_saksi")
public class PermohonanSaksi implements Serializable{

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_saksi")
	@Column (name = "id_saksi")
	private long idSaksi;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;
	
	@Column (name = "nama", length = 250, nullable = false)
	private String nama;
	
	@Column (name = "alamat1", length = 40)
	private String alamat1;
	
	@Column (name = "alamat2", length = 40)
	private String alamat2;
	
	@Column (name = "alamat3", length = 40)
	private String alamat3;
	
	@Column (name = "alamat4", length = 40)
	private String alamat4;
	
	@Column (name = "poskod", length = 5)
	private String poskod;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;
	
	@Column (name = "no_tel", length = 20)
	private String noTelefon;
	@Column (name = "email", length = 20)
	private String email;
	@Column (name = "kerja", length = 20)
	private String pekerjaan;

        @Column (name = "status_dakwakompaun", length = 20)
	private String statusDakwaanKompaun;
        
        @ManyToOne
	@JoinColumn (name = "id_op")
	private OperasiPenguatkuasaan operasiPenguatkuasaan;    
        
        @ManyToOne
	@JoinColumn (name = "id_oks")
	private AduanOrangKenaSyak aduanOrangKenaSyak;    
        
        @OneToMany (mappedBy = "saksi")
	private List<Kompaun> senaraiKompaun;

    @Column (name = "sts_saksi")
    private char statusSaksi;


    @Embedded
	private InfoAudit infoAudit;

        public long getIdSaksi() {
            return idSaksi;
        }

        public void setIdSaksi(long idSaksi) {
            this.idSaksi = idSaksi;
        }

        public Permohonan getPermohonan() {
            return permohonan;
        }

        public void setPermohonan(Permohonan permohonan) {
            this.permohonan = permohonan;
        }
	
	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodJenisPengenalan getJenisPengenalan() {
		return jenisPengenalan;
	}

	public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
		this.jenisPengenalan = jenisPengenalan;
	}

	public String getNoPengenalan() {
		return noPengenalan;
	}

	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat1() {
		return alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getAlamat4() {
		return alamat4;
	}

	public void setAlamat4(String alamat4) {
		this.alamat4 = alamat4;
	}

	public String getPoskod() {
		return poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public KodNegeri getNegeri() {
		return negeri;
	}

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

	public void setNegeri(KodNegeri negeri) {
		this.negeri = negeri;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public String getStatusDakwaanKompaun() {
        return statusDakwaanKompaun;
    }

    public void setStatusDakwaanKompaun(String statusDakwaanKompaun) {
        this.statusDakwaanKompaun = statusDakwaanKompaun;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public char getStatusSaksi() {
        return statusSaksi;
    }

    public void setStatusSaksi(char statusSaksi) {
        this.statusSaksi = statusSaksi;
    }
}
