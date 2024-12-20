package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "pihak")
@SequenceGenerator(name = "seq_pihak", sequenceName = "seq_pihak")
public class Pihak implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pihak")
    @Column(name = "id_pihak")
    private long idPihak;
    // TODO kod_pengenalan VARCHAR2(1),
    @Column(name = "nama", length = 250)
    private String nama;
    @Column(name = "no_pengenalan", length = 20)
    private String noPengenalan;
    @Column(name = "no_pengenalan_lain", length = 20)
    private String noPengenalanLain;
    @Column(name = "kod_warnakp")
    private String warnaKP;
    @ManyToOne
    @JoinColumn(name = "kod_bangsa")
    private KodBangsa bangsa;
    @Column(name = "kod_jantina")
    private String kodJantina;
    @ManyToOne
    @JoinColumn(name = "kod_warganegara")
    private KodWarganegara wargaNegara;
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
    @JoinColumn(name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_pengenalan_lain")
    private KodJenisPengenalan jenisPengenalanLain;
    
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
    
    @OneToMany (mappedBy = "pihak", fetch = FetchType.LAZY)
    private List<PihakAlamatTamb> senaraiAlamatTamb;
    
    // rumah
    @Column(name = "no_tel1")
    private String noTelefon1;
    @Column(name = "no_tel2")
    private String noTelefon2;
    @Column(name = "no_bimbit")
    private String noTelefonBimbit;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "ibupejabat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PihakCawangan> senaraiCawangan;
    @OneToMany(mappedBy = "pihak", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PihakPengarah> senaraiPengarah;
    @Column(name = "trh_lahir")
    private Date tarikhLahir;
    @Column(name = "tempat_lahir")
    private String tempatLahir;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_negeri_lahir")
    private KodNegeri negeriKelahiran;
	
	@Column (name = "anak_tempatan")
	private Character anakTempatan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_suku", nullable = true)
    private KodSuku suku;

    @Column (name = "tempat_suku")
    private String tempatSuku;
    
    @Column (name = "sub_suku")
    private String subSuku;
	
    @Column (name = "kturunan")
    private String keturunan;
    
	@ManyToOne
	@JoinColumn(name = "kod_bank")
	private KodBank bank;
	
	@Column(name = "no_akaun_bank")
	private String noAkaunBank;
	
	@ManyToOne
	@JoinColumn (name = "kod_sykt_tubuh")
	private KodPenubuhanSyarikat penubuhanSyarikat;
	
	@Column(name = "atvt_niaga")
	private String aktivitiPerniagaan;
    
    @Column (name = "modal_bayar")
    private BigDecimal modalBerbayar;
    @Column(name = "modal_benar")
    private BigDecimal modalDibenar;
    @Column(name = "trh_mati")
    private Date tarikhMati;
    @Column(name = "no_sijil_mati")
    private String noSijilMati;
    //@OneToMany (fetch = FetchType.LAZY, mappedBy = "pihak")
    //private List<WakilPihak> senaraiWakil;
    
    @Column (name = "NO_SYKTMCL")
    private String noSyarikatMcl;

    @ManyToOne
    @JoinColumn(name = "ID_PIHAK_SYKT")
    private Pihak pihakSyarikat;
    
    @ManyToOne
    @JoinColumn(name = "kod_flag_pihak")
    private KodFlagPihak kodFlagPihak;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getWarnaKP() {
        return warnaKP;
    }

    public void setWarnaKP(String warnaKP) {
        this.warnaKP = warnaKP;
    }

    public KodBangsa getBangsa() {
        return bangsa;
    }

    public void setBangsa(KodBangsa bangsa) {
        this.bangsa = bangsa;
    }

    public void setSuku(KodSuku suku) {
        this.suku = suku;
    }

    public KodSuku getSuku() {
        return suku;
    }

    public void setTempatSuku(String tempatSuku) {
		this.tempatSuku = tempatSuku;
	}

	public String getTempatSuku() {
		return tempatSuku;
	}

	public void setSubSuku(String subSuku) {
		this.subSuku = subSuku;
	}

	public String getSubSuku() {
		return subSuku;
	}

	public void setKeturunan(String keturunan) {
		this.keturunan = keturunan;
	}

	public String getKeturunan() {
		return keturunan;
	}

	public String getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(String kodJantina) {
        this.kodJantina = kodJantina;
    }

    public KodWarganegara getWargaNegara() {
        return wargaNegara;
    }

    public void setWargaNegara(KodWarganegara wargaNegara) {
        this.wargaNegara = wargaNegara;
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

    public void setSenaraiAlamatTamb(List<PihakAlamatTamb> senaraiAlamatTamb) {
		this.senaraiAlamatTamb = senaraiAlamatTamb;
	}

	public List<PihakAlamatTamb> getSenaraiAlamatTamb() {
		return senaraiAlamatTamb;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSenaraiCawangan(List<PihakCawangan> senaraiCawangan) {
        this.senaraiCawangan = senaraiCawangan;
    }

    public List<PihakCawangan> getSenaraiCawangan() {
        return senaraiCawangan;
    }

    public void setSenaraiPengarah(List<PihakPengarah> senaraiPengarah) {
        this.senaraiPengarah = senaraiPengarah;
    }

    public List<PihakPengarah> getSenaraiPengarah() {
        return senaraiPengarah;
    }

    //public void setSenaraiWakil(List<WakilPihak> senaraiWakil) {
    //	this.senaraiWakil = senaraiWakil;
    //}
    //public List<WakilPihak> getSenaraiWakil() {
    //	return senaraiWakil;
    //}
    public void setTarikhLahir(Date tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public Date getTarikhLahir() {
        return tarikhLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public void setNegeriKelahiran(KodNegeri negeriKelahiran) {
        this.negeriKelahiran = negeriKelahiran;
    }

    public KodNegeri getNegeriKelahiran() {
        return negeriKelahiran;
    }

    public BigDecimal getModalBerbayar() {
        return modalBerbayar;
    }

    public void setModalBerbayar(BigDecimal modalBerbayar) {
        this.modalBerbayar = modalBerbayar;
    }
	
	public void setAnakTempatan(Character anakTempatan) {
		this.anakTempatan = anakTempatan;
	}

	public Character getAnakTempatan() {
		return anakTempatan;
	}

    public BigDecimal getModalDibenar() {
        return modalDibenar;
    }

    public void setModalDibenar(BigDecimal modalDibenar) {
        this.modalDibenar = modalDibenar;
    }

    public void setTarikhMati(Date tarikhMati) {
        this.tarikhMati = tarikhMati;
    }

    public Date getTarikhMati() {
        return tarikhMati;
    }

    public void setNoSijilMati(String noSijilMati) {
        this.noSijilMati = noSijilMati;
    }

    public String getNoSijilMati() {
        return noSijilMati;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) return true;
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pihak other = (Pihak) obj;
        if (this.idPihak != other.idPihak) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (int) (this.idPihak ^ (this.idPihak >>> 32));
        return hash;
    }

	public void setBank(KodBank bank) {
		this.bank = bank;
	}
    
	public KodBank getBank() {
		return bank;
	}
    
    public String getNoPengenalanLain() {
        return noPengenalanLain;
    }

    public void setNoPengenalanLain(String noPengenalanLain) {
        this.noPengenalanLain = noPengenalanLain;
    }

	public void setNoAkaunBank(String noAkaunBank) {
		this.noAkaunBank = noAkaunBank;
	}

	public String getNoAkaunBank() {
		return noAkaunBank;
	}

	public void setAktivitiPerniagaan(String aktivitiPerniagaan) {
		this.aktivitiPerniagaan = aktivitiPerniagaan;
	}

	public String getAktivitiPerniagaan() {
		return aktivitiPerniagaan;
	}

    public void setPenubuhanSyarikat(KodPenubuhanSyarikat penubuhanSyarikat) {
        this.penubuhanSyarikat = penubuhanSyarikat;
    }

    public KodPenubuhanSyarikat getPenubuhanSyarikat() {
        return penubuhanSyarikat;
    }

    public String getNoSyarikatMcl() {
        return noSyarikatMcl;
    }

    public void setNoSyarikatMcl(String noSyarikatMcl) {
        this.noSyarikatMcl = noSyarikatMcl;
    }

    public Pihak getPihakSyarikat() {
        return pihakSyarikat;
    }

    public void setPihakSyarikat(Pihak pihakSyarikat) {
        this.pihakSyarikat = pihakSyarikat;
    }

    public KodFlagPihak getKodFlagPihak() {
        return kodFlagPihak;
    }

    public void setKodFlagPihak(KodFlagPihak kodFlagPihak) {
        this.kodFlagPihak = kodFlagPihak;
    }

    public KodJenisPengenalan getJenisPengenalanLain() {
        return jenisPengenalanLain;
    }

    public void setJenisPengenalanLain(KodJenisPengenalan jenisPengenalanLain) {
        this.jenisPengenalanLain = jenisPengenalanLain;
    }
   
}
