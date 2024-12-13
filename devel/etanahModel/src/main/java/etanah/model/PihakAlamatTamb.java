package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "pihak_alamat_tamb")
@SequenceGenerator(name = "seq_pihak_alamat_tamb", sequenceName = "seq_pihak_alamat_tamb")
public class PihakAlamatTamb implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pihak_alamat_tamb")
	@Column (name = "id_tamb")
	private long idTambahan;
	
	@ManyToOne
	@JoinColumn (name = "id_pihak", nullable = true)
	private Pihak pihak;
	
	@Column (name = "alamat3_1")
	private String alamatKetiga1;
	
	@Column (name =   "alamat3_2")
	private String alamatKetiga2;
	
	@Column (name =   "alamat3_3")
	private String alamatKetiga3;
	
	@Column (name =   "alamat3_4")
	private String alamatKetiga4;
	
	@Column (name =   "poskod3")
	private String alamatKetigaPoskod;
	
	@ManyToOne
	@JoinColumn (name = "kod_negeri3")
	private KodNegeri alamatKetigaNegeri;
	  
	@Column (name = "alamat4_1")
	private String alamatKeempat1;
	
	@Column (name = "alamat4_2")
	private String alamatKeempat2;
	
	@Column (name = "alamat4_3")
	private String alamatKeempat3;
	
	@Column (name = "alamat4_4")
	private String alamatKeempat4;
	
	@Column (name = "poskod4")
	private String alamatKeempatPoskod;
	
	@ManyToOne
	@JoinColumn (name = "kod_negeri4")
	private KodNegeri alamatKeempatNegeri;
	
	@Column (name = "alamat5_1")
	private String alamatKelima1;
	
	@Column (name = "alamat5_2")
	private String alamatKelima2;
	
	@Column (name = "alamat5_3")
	private String alamatKelima3;
	
	@Column (name = "alamat5_4")
	private String alamatKelima4;
	
	@Column (name = "poskod5")
	private String alamatKelimaPoskod;
	
	@ManyToOne
	@JoinColumn (name = "kod_negeri5")
	private KodNegeri alamatKelimaNegeri;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdTambahan() {
		return idTambahan;
	}

	public void setIdTambahan(long idTambahan) {
		this.idTambahan = idTambahan;
	}

	public Pihak getPihak() {
		return pihak;
	}

	public void setPihak(Pihak pihak) {
		this.pihak = pihak;
	}

	public String getAlamatKetiga1() {
		return alamatKetiga1;
	}

	public void setAlamatKetiga1(String alamatKetiga1) {
		this.alamatKetiga1 = alamatKetiga1;
	}

	public String getAlamatKetiga2() {
		return alamatKetiga2;
	}

	public void setAlamatKetiga2(String alamatKetiga2) {
		this.alamatKetiga2 = alamatKetiga2;
	}

	public String getAlamatKetiga3() {
		return alamatKetiga3;
	}

	public void setAlamatKetiga3(String alamatKetiga3) {
		this.alamatKetiga3 = alamatKetiga3;
	}

	public String getAlamatKetiga4() {
		return alamatKetiga4;
	}

	public void setAlamatKetiga4(String alamatKetiga4) {
		this.alamatKetiga4 = alamatKetiga4;
	}

	public String getAlamatKetigaPoskod() {
		return alamatKetigaPoskod;
	}

	public void setAlamatKetigaPoskod(String alamatKetigaPoskod) {
		this.alamatKetigaPoskod = alamatKetigaPoskod;
	}

	public KodNegeri getAlamatKetigaNegeri() {
		return alamatKetigaNegeri;
	}

	public void setAlamatKetigaNegeri(KodNegeri alamatKetigaNegeri) {
		this.alamatKetigaNegeri = alamatKetigaNegeri;
	}

	public String getAlamatKeempat1() {
		return alamatKeempat1;
	}

	public void setAlamatKeempat1(String alamatKeempat1) {
		this.alamatKeempat1 = alamatKeempat1;
	}

	public String getAlamatKeempat2() {
		return alamatKeempat2;
	}

	public void setAlamatKeempat2(String alamatKeempat2) {
		this.alamatKeempat2 = alamatKeempat2;
	}

	public String getAlamatKeempat3() {
		return alamatKeempat3;
	}

	public void setAlamatKeempat3(String alamatKeempat3) {
		this.alamatKeempat3 = alamatKeempat3;
	}

	public String getAlamatKeempat4() {
		return alamatKeempat4;
	}

	public void setAlamatKeempat4(String alamatKeempat4) {
		this.alamatKeempat4 = alamatKeempat4;
	}

	public String getAlamatKeempatPoskod() {
		return alamatKeempatPoskod;
	}

	public void setAlamatKeempatPoskod(String alamatKeempatPoskod) {
		this.alamatKeempatPoskod = alamatKeempatPoskod;
	}

	public KodNegeri getAlamatKeempatNegeri() {
		return alamatKeempatNegeri;
	}

	public void setAlamatKeempatNegeri(KodNegeri alamatKeempatNegeri) {
		this.alamatKeempatNegeri = alamatKeempatNegeri;
	}

	public String getAlamatKelima1() {
		return alamatKelima1;
	}

	public void setAlamatKelima1(String alamatKelima1) {
		this.alamatKelima1 = alamatKelima1;
	}

	public String getAlamatKelima2() {
		return alamatKelima2;
	}

	public void setAlamatKelima2(String alamatKelima2) {
		this.alamatKelima2 = alamatKelima2;
	}

	public String getAlamatKelima3() {
		return alamatKelima3;
	}

	public void setAlamatKelima3(String alamatKelima3) {
		this.alamatKelima3 = alamatKelima3;
	}

	public String getAlamatKelima4() {
		return alamatKelima4;
	}

	public void setAlamatKelima4(String alamatKelima4) {
		this.alamatKelima4 = alamatKelima4;
	}

	public String getAlamatKelimaPoskod() {
		return alamatKelimaPoskod;
	}

	public void setAlamatKelimaPoskod(String alamatKelimaPoskod) {
		this.alamatKelimaPoskod = alamatKelimaPoskod;
	}

	public KodNegeri getAlamatKelimaNegeri() {
		return alamatKelimaNegeri;
	}

	public void setAlamatKelimaNegeri(KodNegeri alamatKelimaNegeri) {
		this.alamatKelimaNegeri = alamatKelimaNegeri;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
