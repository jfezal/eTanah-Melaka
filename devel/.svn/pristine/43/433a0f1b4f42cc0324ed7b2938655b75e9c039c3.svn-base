package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "pihak_pengarah")
@SequenceGenerator(name = "seq_pihak_pengarah", sequenceName = "seq_pihak_pengarah")
public class PihakPengarah implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pihak_pengarah")
	@Column (name = "id_pengarah")
	private long idPengarah;
	
	@ManyToOne
	@JoinColumn (name = "id_pihak", nullable = false)
	private Pihak pihak;
	
	@Column (name = "nama", length = 250, nullable = false)
	private String nama;
	
	@ManyToOne
	@JoinColumn (name = "KOD_PENGENALAN")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "NO_PENGENALAN")
	private String noPengenalan;
	
	@Column (name = "KOD_WARNAKP")
	private String warnaKP;
	
	@ManyToOne
	@JoinColumn (name  = "KOD_BANGSA")
	private KodBangsa bangsa;
	
	@Column (name = "KOD_JANTINA")
	private String jantina;
	
	@ManyToOne
	@JoinColumn (name = "KOD_WARGANEGARA")
	private KodWarganegara warganegara;
	
	@Column (name = "saham")
	private BigDecimal jumlahSaham;
	
	@Column (name = "aktif")
	private char aktif;


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
	@JoinColumn (name  = "kod_negeri")
	private KodNegeri kodNegeri;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdPengarah() {
		return idPengarah;
	}

	public void setIdPengarah(long idPengarah) {
		this.idPengarah = idPengarah;
	}

	public Pihak getPihak(){
		return pihak;
	}
	
	public void setPihak(Pihak pihak){
		this.pihak = pihak;
	}
	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public char getAktif() {
		return aktif;
	}

	public void setAktif(char aktif) {
		this.aktif = aktif;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public KodBangsa getBangsa() {
        return bangsa;
    }

    public void setBangsa(KodBangsa bangsa) {
        this.bangsa = bangsa;
    }

    public String getJantina() {
        return jantina;
    }

    public void setJantina(String jantina) {
        this.jantina = jantina;
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

    public KodWarganegara getWarganegara() {
        return warganegara;
    }

    public void setWarganegara(KodWarganegara warganegara) {
        this.warganegara = warganegara;
    }

    public String getWarnaKP() {
        return warnaKP;
    }

    public void setWarnaKP(String warnaKP) {
        this.warnaKP = warnaKP;
    }

	public void setJumlahSaham(BigDecimal jumlahSaham) {
		this.jumlahSaham = jumlahSaham;
	}

	public BigDecimal getJumlahSaham() {
		return jumlahSaham;
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

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

 


}
