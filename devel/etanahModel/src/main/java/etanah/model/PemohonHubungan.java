package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "pemohon_hbgn")
@SequenceGenerator (name = "seq_pemohon_hbgn", sequenceName = "seq_pemohon_hbgn")
public class PemohonHubungan implements Serializable {
	@Id  @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_pemohon_hbgn")
	@Column (name = "id_hbgn")
	private long idHubungan;

	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "id_pemohon", nullable = false)
	private Pemohon pemohon;

	@Column (name = "kaitan", nullable = false)
	private String kaitan;

	@Column (name = "nama", nullable = false)
	private String nama;

	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;

        @Column(name = "no_pengenalan", length = 20)
        private String noPengenalan;

	@Column (name = "trh_lahir")
	private Date tarikhLahir;

	@Column (name = "tempat_lahir")
	private String tempatLahir;

	@ManyToOne
	@JoinColumn (name = "kod_negeri_lahir")
	private KodNegeri negeriLahir;

	@Column (name = "umur")
	private Integer umur;

	@ManyToOne
	@JoinColumn (name = "kod_bangsa")
	private KodBangsa bangsa;

	@Column (name = "kod_jantina")
	private String kodJantina;

	@ManyToOne
	@JoinColumn (name = "kod_warganegara")
	private KodWarganegara warganegara;

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

	@Column (name = "no_tel1")
	private String noTelefon1;

	@Column (name = "kerja")
	private String pekerjaan;

	@Column (name = "gaji")
	private BigDecimal gaji;

	@Column (name = "inst")
	private String institusi;

	@Column (name = "inst_alamat1")
	private String institusiAlamat1;

	@Column (name = "inst_alamat2")
	private String institusiAlamat2;

	@Column (name = "inst_alamat3")
	private String institusiAlamat3;

	@Column (name = "inst_alamat4")
	private String institusiAlamat4;

	@Column (name = "inst_poskod")
	private String institusiPoskod;

	@ManyToOne
	@JoinColumn (name = "inst_kod_negeri")
	private KodNegeri institusiNegeri;

	@Column (name = "telah_meninggal")
	private String telahMeninggal;

	@Column (name = "trh_mati")
	private Date tarikhMati;

	// tempat terakhir meningggal sebelum mati
	@Column (name = "tmpt_akhir")
	private String tempatTerakhir;

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

	public Pemohon getPemohon() {
		return pemohon;
	}

	public void setPemohon(Pemohon pemohon) {
		this.pemohon = pemohon;
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

	public Date getTarikhLahir() {
		return tarikhLahir;
	}

	public void setTarikhLahir(Date tarikhLahir) {
		this.tarikhLahir = tarikhLahir;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public KodNegeri getNegeriLahir() {
		return negeriLahir;
	}

	public void setNegeriLahir(KodNegeri negeriLahir) {
		this.negeriLahir = negeriLahir;
	}

	public Integer getUmur() {
		return umur;
	}

	public void setUmur(Integer umur) {
		this.umur = umur;
	}

	public KodBangsa getBangsa() {
		return bangsa;
	}

	public void setBangsa(KodBangsa bangsa) {
		this.bangsa = bangsa;
	}

	public String getKodJantina() {
		return kodJantina;
	}

	public void setKodJantina(String kodJantina) {
		this.kodJantina = kodJantina;
	}

	public KodWarganegara getWarganegara() {
		return warganegara;
	}

	public void setWarganegara(KodWarganegara warganegara) {
		this.warganegara = warganegara;
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

	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}

	public String getPekerjaan() {
		return pekerjaan;
	}

	public void setGaji(BigDecimal gaji) {
		this.gaji = gaji;
	}

	public BigDecimal getGaji() {
		return gaji;
	}

	public String getInstitusi() {
		return institusi;
	}

	public void setInstitusi(String institusi) {
		this.institusi = institusi;
	}

	public String getInstitusiAlamat1() {
		return institusiAlamat1;
	}

	public void setInstitusiAlamat1(String institusiAlamat1) {
		this.institusiAlamat1 = institusiAlamat1;
	}

	public String getInstitusiAlamat2() {
		return institusiAlamat2;
	}

	public void setInstitusiAlamat2(String institusiAlamat2) {
		this.institusiAlamat2 = institusiAlamat2;
	}

	public String getInstitusiAlamat3() {
		return institusiAlamat3;
	}

	public void setInstitusiAlamat3(String institusiAlamat3) {
		this.institusiAlamat3 = institusiAlamat3;
	}

	public String getInstitusiAlamat4() {
		return institusiAlamat4;
	}

	public void setInstitusiAlamat4(String institusiAlamat4) {
		this.institusiAlamat4 = institusiAlamat4;
	}

	public String getInstitusiPoskod() {
		return institusiPoskod;
	}

	public void setInstitusiPoskod(String institusiPoskod) {
		this.institusiPoskod = institusiPoskod;
	}

	public KodNegeri getInstitusiNegeri() {
		return institusiNegeri;
	}

	public void setInstitusiNegeri(KodNegeri institusiNegeri) {
		this.institusiNegeri = institusiNegeri;
	}

    public String getTelahMeninggal() {
        return telahMeninggal;
    }

    public void setTelahMeninggal(String telahMeninggal) {
        this.telahMeninggal = telahMeninggal;
    }

	public Date getTarikhMati() {
		return tarikhMati;
	}

	public void setTarikhMati(Date tarikhMati) {
		this.tarikhMati = tarikhMati;
	}

	public String getTempatTerakhir() {
		return tempatTerakhir;
	}

	public void setTempatTerakhir(String tempatTerakhir) {
		this.tempatTerakhir = tempatTerakhir;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
