package etanah.model;

import etanah.dao.KodJenisPengenalanDAO;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_caw_jabatan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodCawanganJabatan implements Serializable, Penyerah{
    
    @Id
    @Column (name = "kod", length = 8)
    private String kod;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;
    
    @ManyToOne
    @JoinColumn (name = "kod_jabatan")
    private KodJabatan jabatan;
    
    @Column (name = "nama")
    private String nama;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;

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
        
         @Column (name  = "email", length = 1)
    private String emel;
         
    @ManyToOne
    @JoinColumn (name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    
    @Embedded
    private InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setJabatan(KodJabatan jabatan) {
		this.jabatan = jabatan;
	}

	public KodJabatan getJabatan() {
		return jabatan;
	}
	
	@Override
	public String getNoPengenalan() {
		return kod;
	}

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
    
    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    @Transient
	public String getKodPenyerah(){
		return "00"; // unit dalaman
	}

    @Override
    public long getIdPenyerah() {
        return Long.parseLong(kod);
    }

    @Override
    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }
    
    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
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
        
        public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    
}
