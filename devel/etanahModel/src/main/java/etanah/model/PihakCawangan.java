package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "pihak_caw")
@SequenceGenerator (name = "seq_pihak_caw", sequenceName = "seq_pihak_caw")
public class PihakCawangan implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_pihak_caw")
	@Column(name = "id_pihak_caw")
	private long idPihakCawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_pihak")
	private Pihak ibupejabat;

	@Column(name = "nama", length = 250)
	private String namaCawangan;

	@Column(name = "alamat1", length = 40)
	private String alamat1;

	@Column(name = "alamat2", length = 40)
	private String alamat2;

	@Column(name = "alamat3", length = 40)
	private String alamat3;

	@Column(name = "alamat4", length = 40)
	private String alamat4;

	@Column(name = "poskod", length = 5)
	private String poskod;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kod_negeri")
	private KodNegeri negeri;

	@Column(name = "surat_alamat1", length = 40)
	private String suratAlamat1;

	@Column(name = "surat_alamat2", length = 40)
	private String suratAlamat2;

	@Column(name = "surat_alamat3", length = 40)
	private String suratAlamat3;

	@Column(name = "surat_alamat4", length = 40)
	private String suratAlamat4;

	@Column(name = "surat_poskod", length = 5)
	private String suratPoskod;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "surat_kod_negeri")
	private KodNegeri suratNegeri;
	
	// rumah
	@Column (name = "no_tel1")
	private String noTelefon1;
	
	@Column (name = "no_tel2")
	private String noTelefon2;
	
	@Column (name = "no_bimbit")
	private String noTelefonBimbit;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdPihakCawangan() {
		return idPihakCawangan;
	}

	public void setIdPihakCawangan(long idCawangan) {
		this.idPihakCawangan = idCawangan;
	}

	public Pihak getIbupejabat(){
		return ibupejabat;
	}
	
	public void setIbupejabat(Pihak ibupejabat){
		this.ibupejabat = ibupejabat;
	}
	
	public String getNamaCawangan() {
		return namaCawangan;
	}

	public void setNamaCawangan(String namaCawangan) {
		this.namaCawangan = namaCawangan;
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

	public String getSuratAlamat1() {
		return suratAlamat1;
	}

	public void setSuratAlamat1(String suratAlamat1) {
		this.suratAlamat1 = suratAlamat1;
	}

	public String getSuratAlamat2() {
		return suratAlamat2;
	}

	public void setSuratAlamat2(String suratAlamat2) {
		this.suratAlamat2 = suratAlamat2;
	}

	public String getSuratAlamat3() {
		return suratAlamat3;
	}

	public void setSuratAlamat3(String suratAlamat3) {
		this.suratAlamat3 = suratAlamat3;
	}

	public String getSuratAlamat4() {
		return suratAlamat4;
	}

	public void setSuratAlamat4(String suratAlamat4) {
		this.suratAlamat4 = suratAlamat4;
	}

	public String getSuratPoskod() {
		return suratPoskod;
	}

	public void setSuratPoskod(String suratPoskod) {
		this.suratPoskod = suratPoskod;
	}

	public KodNegeri getSuratNegeri() {
		return suratNegeri;
	}

	public void setSuratNegeri(KodNegeri suratNegeri) {
		this.suratNegeri = suratNegeri;
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

	public String getNoTelefonBimbit() {
		return noTelefonBimbit;
	}

	public void setNoTelefonBimbit(String noTelefonBimbit) {
		this.noTelefonBimbit = noTelefonBimbit;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
