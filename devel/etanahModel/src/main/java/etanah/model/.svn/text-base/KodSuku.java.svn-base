package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_suku")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodSuku implements Serializable{
    
    @Id
    @Column (name ="kod", length = 3)
    private String kod;
        
    @Column (name = "nama", length = 50, nullable = false, unique = true)
    private String nama;
    
    @Column (name = "perihal", length = 200)
    private String perihal;
    
    // nama pemimpin utk suku
    @Column (name = "dipimpin", length = 250)
    private String pemimpinNama;
    
    // alamat utk pemimpin
    @Column (name = "alamat1", length = 40)
    private String pemimpinAlamat1;
    
    // alamat utk pemimpin
    @Column (name = "alamat2", length = 40)
    private String pemimpinAlamat2;

    // alamat utk pemimpin
    @Column (name = "alamat3", length = 40)
    private String pemimpinAlamat3;
    
    // alamat utk pemimpin
    @Column (name = "alamat4", length = 40)
    private String pemimpinAlamat4;
    
    @Column (name = "poskod", length = 5)
    private String pemimpinPoskod;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_negeri")
    private KodNegeri pemimpinNegeri;
    
    @Column (name = "aktif", length = 1)
    private char aktif;

    @Embedded
    private InfoAudit infoAudit;


    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setPerihal(String perihal) {
		this.perihal = perihal;
	}

	public String getPerihal() {
		return perihal;
	}

	public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public String getPemimpinNama() {
		return pemimpinNama;
	}

	public void setPemimpinNama(String pemimpinNama) {
		this.pemimpinNama = pemimpinNama;
	}

	public String getPemimpinAlamat1() {
		return pemimpinAlamat1;
	}

	public void setPemimpinAlamat1(String pemimpinAlamat1) {
		this.pemimpinAlamat1 = pemimpinAlamat1;
	}

	public String getPemimpinAlamat2() {
		return pemimpinAlamat2;
	}

	public void setPemimpinAlamat2(String pemimpinAlamat2) {
		this.pemimpinAlamat2 = pemimpinAlamat2;
	}

	public String getPemimpinAlamat3() {
		return pemimpinAlamat3;
	}

	public void setPemimpinAlamat3(String pemimpinAlamat3) {
		this.pemimpinAlamat3 = pemimpinAlamat3;
	}

	public String getPemimpinAlamat4() {
		return pemimpinAlamat4;
	}

	public void setPemimpinAlamat4(String pemimpinAlamat4) {
		this.pemimpinAlamat4 = pemimpinAlamat4;
	}

	public String getPemimpinPoskod() {
		return pemimpinPoskod;
	}

	public void setPemimpinPoskod(String pemimpinPoskod) {
		this.pemimpinPoskod = pemimpinPoskod;
	}

	public KodNegeri getPemimpinNegeri() {
		return pemimpinNegeri;
	}

	public void setPemimpinNegeri(KodNegeri pemimpinNegeri) {
		this.pemimpinNegeri = pemimpinNegeri;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
