package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "jurulelong")
@SequenceGenerator (name = "seq_jurulelong", sequenceName = "seq_jurulelong")
public class JuruLelong implements Serializable, Penyerah {

	@Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_jurulelong")
	@Column (name = "id_jlb")
	private long idJlb;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;

	@ManyToOne
	@JoinColumn (name = "id_syt_jlb")
	private SytJuruLelong sytJuruLelong;

	@Column (name = "no_pengenalan")
	private String noPengenalan;

	@Column (name = "nama", length = 250, nullable = false)
	private String nama;

	@Column (name = "alamat1", length =	40)
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

	@Column (name = "no_tel1", length = 20)
	private String noTelefon1;

	@Column (name = "no_tel2", length = 20)
	private String noTelefon2;

	@Column (name = "thn_aktif" )
	private String thn_aktif;

	@Column (name = "aktif")
	private char aktif;
        
         @Column (name  = "email", length = 1)
    private String emel;
         
         @ManyToOne
	@JoinColumn (name = "id_pguna")
	private Pengguna idPengguna;

	@Embedded
	private InfoAudit infoAudit;

	@Transient
	public String getKodPenyerah(){
		return "04";
	}

	public long getIdJlb() {
		return idJlb;
	}

	public void setIdJlb(long idJlb) {
		this.idJlb = idJlb;
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

    public SytJuruLelong getSytJuruLelong() {
        return sytJuruLelong;
    }

    public void setSytJuruLelong(SytJuruLelong sytJuruLelong) {
        this.sytJuruLelong = sytJuruLelong;
    }

	public String getNoPengenalan() {
		return noPengenalan;
	}

	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	@Transient
	public long getIdPenyerah(){
		return idJlb;
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

	public void setNegeri(KodNegeri negeri) {
		this.negeri = negeri;
	}

	public String getNoTelefon1() {
		return noTelefon1;
	}

	public void setNoTelefon1(String noTelefon1) {
		this.noTelefon1 = noTelefon1;
	}

	public String getNoTelefon2() {
		return noTelefon2;
	}

	public void setNoTelefon2(String noTelefon2) {
		this.noTelefon2 = noTelefon2;
	}

    public String getThn_aktif() {
        return thn_aktif;
    }

    public void setThn_aktif(String thn_aktif) {
        this.thn_aktif = thn_aktif;
    }
	
	public char getAktif(){
		return aktif;
	}
	
	public void setAktif(char aktif){
		this.aktif = aktif;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
        public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public Pengguna getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Pengguna idPengguna) {
        this.idPengguna = idPengguna;
    }
    
    
}