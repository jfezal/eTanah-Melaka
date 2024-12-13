package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_pihak_hbgn")
@SequenceGenerator(name = "seq_mohon_pihak_hbgn", sequenceName = "seq_mohon_pihak_hbgn")
public class PermohonanPihakHubungan implements Serializable {
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_pihak_hbgn")
	@Column (name = "id_hbgn")
	private long idHubungan;
	
	@ManyToOne 
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_mp", nullable = false)
	private PermohonanPihak pihak;
	
	@Column (name = "kaitan", nullable = false)
	private String kaitan;
	
	@Column (name = "nama", nullable = false)
	private String nama;

        @ManyToOne
        @JoinColumn(name = "kod_warganegara")
        private KodWarganegara wargaNegara;
	
	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;
	
	@Column (name = "umur")
	private Integer umur;
	
	@Column (name = "alamat1")
	private String alamat1;
	
	@Column (name = "alamat2")
	private String alamat2;
	
	@Column (name = "alamat3")
	private String alamat3;
	
	@Column (name = "alamat4")
	private String alamat4;
	
	@Column (name = "poskod")
	private String poskod;
	
	@ManyToOne
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;

        @ManyToOne
	@JoinColumn (name = "kod_negara")
	private KodNegara negara;
	
	@Column (name = "no_tel1")
	private String noTelefon1;

        @Column(name = "syer_pembilang", precision = 14, scale = 0, columnDefinition = "number(14,0)")
	private int syerPembilang;

	@Column(name = "syer_penyebut", precision = 14, scale = 0, columnDefinition = "number(14,0)")
	private int syerPenyebut;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdHubungan() {
		return idHubungan;
	}

	public void setIdHubungan(long idHubungan) {
		this.idHubungan = idHubungan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public PermohonanPihak getPihak() {
		return pihak;
	}

	public void setPihak(PermohonanPihak pihak) {
		this.pihak = pihak;
	}

	public String getKaitan() {
		return kaitan;
	}

	public void setKaitan(String kaitan) {
		this.kaitan = kaitan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
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

	public Integer getUmur() {
		return umur;
	}

	public void setUmur(Integer umur) {
		this.umur = umur;
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

        public KodNegara getNegara() {
            return negara;
        }

        public void setNegara(KodNegara negara) {
            this.negara = negara;
        }

	public String getNoTelefon1() {
		return noTelefon1;
	}

	public void setNoTelefon1(String noTelefon1) {
		this.noTelefon1 = noTelefon1;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

        public int getSyerPembilang() {
            return syerPembilang;
        }

        public void setSyerPembilang(int syerPembilang) {
            this.syerPembilang = syerPembilang;
        }

        public int getSyerPenyebut() {
            return syerPenyebut;
        }

        public void setSyerPenyebut(int syerPenyebut) {
            this.syerPenyebut = syerPenyebut;
        }

        public KodWarganegara getWargaNegara() {
            return wargaNegara;
        }

        public void setWargaNegara(KodWarganegara wargaNegara) {
            this.wargaNegara = wargaNegara;
        }

}
