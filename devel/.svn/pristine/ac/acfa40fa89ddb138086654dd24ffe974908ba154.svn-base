package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "aduan_oks")
@SequenceGenerator(name = "seq_aduan_oks", sequenceName = "seq_aduan_oks")
public class AduanOrangKenaSyak implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_oks")
	@Column (name = "id_oks")
	private long idOrangKenaSyak;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;

	@ManyToOne
	@JoinColumn (name = "id_dokumen")
	private Dokumen dokumen;

	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan kodJenisPengenalan;

	@Column (name = "nama", nullable = false)
	private String nama;

	@Column (name = "tempat_oks_ditahan" )
	private String tempatOksDitahan;

	@Column (name = "tempat_direman" )
	private String tempatDireman;


	@Column (name = "no_pengenalan")
	private String noPengenalan;

	@Column (name = "amaun_kompaun_syor")
	private BigDecimal amaunKompaunSyor;

	@Embedded
	private Alamat alamat;
	
	@Column (name = "no_tel1")
	private String noTelefon1;
	
	@Column (name = "no_tel2")
	private String noTelefon2;
	
	@Column (name = "keterangan")
	private String keterangan;
	
	@Column (name = "catatan")
	private String catatan;
	
	@Column (name = "trh_tahan")
	private Date tarikhDitahan;

	@Column (name = "senarai_hitam")
	private String senaraiHitam;

	@Column (name = "di_senarai_hitam")
	private String diSenaraiHitam;

	@Column (name = "trh_senarai_hitam")
	private Date tarikhSenaraiHitam;

	@Column (name = "trh_dibebas")
	private Date tarikhDiBebas;

	@Column (name = "di_bebas")
	private String diBebas;
        
        @Column (name = "no_notis")
	private String noNotis;


	@ManyToOne
	@JoinColumn (name = "kod_warnakp")
	private KodWarnaKP kodWarnaKP;

	@ManyToOne
	@JoinColumn (name = "kod_jantina")
	private KodJantina kodJantina;


	@ManyToOne
	@JoinColumn (name = "kod_warganegara")
	private KodWarganegara kodWarganegara;


	@ManyToOne
	@JoinColumn (name = "kod_negeri_majikan")
	private KodNegeri kodNegeriMajikan;

	@Column (name = "nama_majikan")
	private String namaMajikan;

	@Column (name = "no_tel_majikan")
	private String noTelMajikan;

	@Column (name = "no_faks_majikan")
	private String noFaksMajikan;

	@Column (name = "alamat1_majikan")
	private String alamat1Majikan;


	@Column (name = "alamat2_majikan")
	private String alamat2Majikan;


	@Column (name = "alamat3_majikan")
	private String alamat3Majikan;

	@Column (name = "alamat4_majikan")
	private String alamat4Majikan;


	@Column (name = "poskod_majikan")
	private String poskodMajikan;

	@Column (name = "status_dakwa")
	private String statusDakwaan;


	@ManyToOne
	@JoinColumn (name = "kod_bangsa")
	private KodBangsa kodBangsa;

        @Column (name = "kerja")
	private String pekerjaan;

        @Column (name = "trh_lahir")
	private Date tarikhlahir;

        @Column (name = "tempoh_byr_kompaun")
	private Integer tempohBayarKompaun;
        
        @ManyToOne
	@JoinColumn (name = "id_op")
	private OperasiPenguatkuasaan operasiPenguatkuasaan;    
        
        @Column (name = "trh_bongkar")
	private Date tarikhBongkar;
        
        @Column (name = "status_ip")
	private String statusIp;
        
        @ManyToOne
	@JoinColumn (name = "id_ks")
	private Permohonan permohonanAduanOrangKenaSyak; 
        
        @Column (name = "tpt_bongkar")
	private String tempatBongkar;
        
        @Column (name = "sts_oks")
	private String statusOrangKenaSyak;
        

	@Embedded
	private InfoAudit infoAudit;

	public long getIdOrangKenaSyak() {
		return idOrangKenaSyak;
	}

	public void setIdOrangKenaSyak(long idOrangKenaSyak) {
		this.idOrangKenaSyak = idOrangKenaSyak;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
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

    public BigDecimal getAmaunKompaunSyor() {
        return amaunKompaunSyor;
    }

    public void setAmaunKompaunSyor(BigDecimal amaunKompaunSyor) {
        this.amaunKompaunSyor = amaunKompaunSyor;
    }

	public Alamat getAlamat() {
		return alamat;
	}

	public void setAlamat(Alamat alamat) {
		this.alamat = alamat;
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

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public void setTarikhDitahan(Date tarikhDitahan) {
		this.tarikhDitahan = tarikhDitahan;
	}

	public Date getTarikhDitahan() {
		return tarikhDitahan;
	}

    public String getDiBebas() {
        return diBebas;
    }

    public void setDiBebas(String diBebas) {
        this.diBebas = diBebas;
    }

    public KodJantina getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(KodJantina kodJantina) {
        this.kodJantina = kodJantina;
    }

    public KodNegeri getKodNegeriMajikan() {
        return kodNegeriMajikan;
    }

    public void setKodNegeriMajikan(KodNegeri kodNegeriMajikan) {
        this.kodNegeriMajikan = kodNegeriMajikan;
    }

    public KodWarganegara getKodWarganegara() {
        return kodWarganegara;
    }

    public void setKodWarganegara(KodWarganegara kodWarganegara) {
        this.kodWarganegara = kodWarganegara;
    }

    public KodWarnaKP getKodWarnaKP() {
        return kodWarnaKP;
    }

    public void setKodWarnaKP(KodWarnaKP kodWarnaKP) {
        this.kodWarnaKP = kodWarnaKP;
    }

    public String getDiSenaraiHitam() {
        return diSenaraiHitam;
    }

    public void setDiSenaraiHitam(String diSenaraiHitam) {
        this.diSenaraiHitam = diSenaraiHitam;
    }

    public String getSenaraiHitam() {
        return senaraiHitam;
    }

    public void setSenaraiHitam(String senaraiHitam) {
        this.senaraiHitam = senaraiHitam;
    }

    public Date getTarikhDiBebas() {
        return tarikhDiBebas;
    }

    public void setTarikhDiBebas(Date tarikhDiBebas) {
        this.tarikhDiBebas = tarikhDiBebas;
    }

    public Date getTarikhSenaraiHitam() {
        return tarikhSenaraiHitam;
    }

    public void setTarikhSenaraiHitam(Date tarikhSenaraiHitam) {
        this.tarikhSenaraiHitam = tarikhSenaraiHitam;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }


    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public String getTempatDireman() {
        return tempatDireman;
    }

    public void setTempatDireman(String tempatDireman) {
        this.tempatDireman = tempatDireman;
    }

    public String getTempatOksDitahan() {
        return tempatOksDitahan;
    }

    public void setTempatOksDitahan(String tempatOksDitahan) {
        this.tempatOksDitahan = tempatOksDitahan;
    }

    public String getAlamat1Majikan() {
        return alamat1Majikan;
    }

    public void setAlamat1Majikan(String alamat1Majikan) {
        this.alamat1Majikan = alamat1Majikan;
    }

    public String getAlamat2Majikan() {
        return alamat2Majikan;
    }

    public void setAlamat2Majikan(String alamat2Majikan) {
        this.alamat2Majikan = alamat2Majikan;
    }

    public String getAlamat3Majikan() {
        return alamat3Majikan;
    }

    public void setAlamat3Majikan(String alamat3Majikan) {
        this.alamat3Majikan = alamat3Majikan;
    }

    public String getAlamat4Majikan() {
        return alamat4Majikan;
    }

    public void setAlamat4Majikan(String alamat4Majikan) {
        this.alamat4Majikan = alamat4Majikan;
    }

    public String getNamaMajikan() {
        return namaMajikan;
    }

    public void setNamaMajikan(String namaMajikan) {
        this.namaMajikan = namaMajikan;
    }

    public String getNoFaksMajikan() {
        return noFaksMajikan;
    }

    public void setNoFaksMajikan(String noFaksMajikan) {
        this.noFaksMajikan = noFaksMajikan;
    }

    public String getNoTelMajikan() {
        return noTelMajikan;
    }

    public void setNoTelMajikan(String noTelMajikan) {
        this.noTelMajikan = noTelMajikan;
    }

    public String getPoskodMajikan() {
        return poskodMajikan;
    }

    public void setPoskodMajikan(String poskodMajikan) {
        this.poskodMajikan = poskodMajikan;
    }

    public String getStatusDakwaan() {
        return statusDakwaan;
    }

    public void setStatusDakwaan(String statusDakwaan) {
        this.statusDakwaan = statusDakwaan;
    }

    public KodBangsa getKodBangsa() {
        return kodBangsa;
    }

    public void setKodBangsa(KodBangsa kodBangsa) {
        this.kodBangsa = kodBangsa;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Date getTarikhlahir() {
        return tarikhlahir;
    }

    public void setTarikhlahir(Date tarikhlahir) {
        this.tarikhlahir = tarikhlahir;
    }

    public Integer getTempohBayarKompaun() {
        return tempohBayarKompaun;
    }

    public void setTempohBayarKompaun(Integer tempohBayarKompaun) {
        this.tempohBayarKompaun = tempohBayarKompaun;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public Date getTarikhBongkar() {
        return tarikhBongkar;
    }

    public void setTarikhBongkar(Date tarikhBongkar) {
        this.tarikhBongkar = tarikhBongkar;
    }

    public String getTempatBongkar() {
        return tempatBongkar;
    }

    public void setTempatBongkar(String tempatBongkar) {
        this.tempatBongkar = tempatBongkar;
    }

    public String getNoNotis() {
        return noNotis;
    }

    public void setNoNotis(String noNotis) {
        this.noNotis = noNotis;
    }

    public String getStatusIp() {
        return statusIp;
    }

    public void setStatusIp(String statusIp) {
        this.statusIp = statusIp;
    }

    public Permohonan getPermohonanAduanOrangKenaSyak() {
        return permohonanAduanOrangKenaSyak;
    }

    public void setPermohonanAduanOrangKenaSyak(Permohonan permohonanAduanOrangKenaSyak) {
        this.permohonanAduanOrangKenaSyak = permohonanAduanOrangKenaSyak;
    }

    public String getStatusOrangKenaSyak() {
        return statusOrangKenaSyak;
    }

    public void setStatusOrangKenaSyak(String statusOrangKenaSyak) {
        this.statusOrangKenaSyak = statusOrangKenaSyak;
    }

}
