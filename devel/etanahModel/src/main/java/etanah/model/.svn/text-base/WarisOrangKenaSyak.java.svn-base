package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "waris_oks")
@SequenceGenerator (name = "seq_waris_oks", sequenceName = "seq_waris_oks")
public class WarisOrangKenaSyak implements Serializable  {
	
	@Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_waris_oks")
	@Column (name = "id_waris_oks")
	private long idWarisOks;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;
	
	@Column (name = "nama" )
	private String nama;
	
	@Column (name = "alamat1" )
	private String alamat1;
	
	@Column (name = "alamat2" )
	private String alamat2;
	
	@Column (name = "alamat3" )
	private String alamat3;
	
	@Column (name = "alamat4" )
	private String alamat4;
	
	@Column (name = "poskod")
	private String poskod;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_negeri")
	private KodNegeri negeri;
	
	@Column (name = "no_tel")
	private String noTelefon;

	@Column (name = "no_tel_bimbit")
	private String noTelefonBimbit;

	@Column (name = "hubungan")
	private String hubungan;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_bangsa")
	private KodBangsa kodBangsa;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_jantina")
	private KodJantina kodJantina;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_warganegara")
	private KodWarganegara kodWarganegara;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_oks")
	private AduanOrangKenaSyak aduanOrangKenaSyak;

        @ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;
        
         @ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;
         
         @Column (name = "ulasan")
	private String ulasan;

	@Embedded
	private InfoAudit infoAudit;

	
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

	public void setNegeri(KodNegeri negeri) {
		this.negeri = negeri;
	}

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public long getIdWarisOks() {
        return idWarisOks;
    }

    public void setIdWarisOks(long idWarisOks) {
        this.idWarisOks = idWarisOks;
    }

 

    public KodBangsa getKodBangsa() {
        return kodBangsa;
    }

    public void setKodBangsa(KodBangsa kodBangsa) {
        this.kodBangsa = kodBangsa;
    }

    public KodJantina getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(KodJantina kodJantina) {
        this.kodJantina = kodJantina;
    }

    public KodWarganegara getKodWarganegara() {
        return kodWarganegara;
    }

    public void setKodWarganegara(KodWarganegara kodWarganegara) {
        this.kodWarganegara = kodWarganegara;
    }

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getNoTelefonBimbit() {
        return noTelefonBimbit;
    }

    public void setNoTelefonBimbit(String noTelefonBimbit) {
        this.noTelefonBimbit = noTelefonBimbit;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
 
	

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
}
