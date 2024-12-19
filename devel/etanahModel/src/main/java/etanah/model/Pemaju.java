package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "pemaju")
public class Pemaju implements Serializable, Penyerah {
	
	@Id
	@Column (name = "id_pemaju")
	private long idPemaju;
	
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
	
	@Column (name = "aktif")
	private char aktif;
        
         @Column (name  = "email", length = 1)
    private String emel;

	@Embedded
	private InfoAudit infoAudit;

	@Transient
	public String getKodPenyerah(){
		return "05"; // pemaju
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

	public long getIdPemaju() {
		return idPemaju;
	}

	public void setIdPemaju(long idPemaju) {
		this.idPemaju = idPemaju;
	}

	@Transient
	public long getIdPenyerah(){
		return idPemaju;
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
}
