package etanah.model;

import etanah.model.ambil.PermohonanPengambilan;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "mohon")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,
dynamicInsert = true, dynamicUpdate = true)
public class Permohonan implements Serializable {

    @Id
    @Column(name = "id_mohon")
    private String idPermohonan;
    @Column(name = "id_kump")
    private String idKumpulan;
    
    /**
     * Turutan dalam kumpulan
     */
    @Column (name = "kump_no")
    private int kumpulanNo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_urusan")
    private KodUrusan kodUrusan;
    
    @ManyToOne
    @JoinColumn (name = "kod_cara_mohon")
    private KodCaraPermohonan caraPermohonan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trans")
    private Transaksi transaksi;
    @Column(name = "catatan", length = 1024, nullable = true)
    private String catatan;
    @Column (name = "nilai")
    private BigDecimal nilaiDipohon;
    
    @Column (name = "ruj_undang2")
    private String rujukanUndang2;
    
    /**
     * Tempoh bagi permohonan dlm tahun
     */
    @Column(name = "tempoh_thn", nullable = true)
    private Integer tempohTahun;
    @Column(name = "tempoh_bln", nullable = true)
    private Integer tempohBulan;
    @Column(name = "tempoh_hari", nullable = true)
    private Integer tempohHari;
    @Column(name = "no_mahkamah")
    private String noMahkamah;
    
    @Column (name = "trh_mula")
    private Date tarikhMula;
    
    @OneToMany(mappedBy = "permohonan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HakmilikPermohonan> senaraiHakmilik;
    
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TanahRizabPermohonan> senaraiTanahRizab;
    
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pemohon> senaraiPemohon;
    
    @OneToMany(mappedBy = "permohonan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermohonanPihak> senaraiPihak;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_folder")
    private FolderDokumen folderDokumen;
    
    @Column(name = "sbb", length = 4000, nullable = true)
    private String sebab;
    
    @ManyToOne
    @JoinColumn (name = "kod_penyerah", nullable = true)
    private KodPenyerah kodPenyerah;
    
    @Column (name = "id_penyerah", nullable = true)
    private Long idPenyerah;
    
    @ManyToOne
    @JoinColumn (name = "penyerah_kod_pengenalan")
    private KodJenisPengenalan penyerahJenisPengenalan;
    
    @Column (name = "penyerah_no_pengenalan")
    private String penyerahNoPengenalan;
    
    @Column(name = "penyerah_no_ruj", length = 50)
    private String penyerahNoRujukan;
    @Column(name = "penyerah_nama", length = 250)
    private String penyerahNama;
    @Column(name = "penyerah_alamat1", length = 40)
    private String penyerahAlamat1;
    @Column(name = "penyerah_alamat2", length = 40)
    private String penyerahAlamat2;
    @Column(name = "penyerah_alamat3", length = 40)
    private String penyerahAlamat3;
    @Column(name = "penyerah_alamat4", length = 40)
    private String penyerahAlamat4;
    @Column(name = "penyerah_poskod", length = 5)
    private String penyerahPoskod;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "penyerah_kod_negeri")
    private KodNegeri penyerahNegeri;
    @Column (name = "penyerah_tel1")
    private String penyerahNoTelefon1;
    
    @Column (name = "penyerah_email")
    private String penyerahEmail;
    
    @Column(name = "utk_thn")
    private Integer untukTahun;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_kpsn", nullable = true)
    private KodKeputusan keputusan;
    /**
     * Nilai keputusan jika numeric.
     */
    @Column(name = "nilai_kpsn")
    private BigDecimal nilaiKeputusan;
    @Column(name = "trh_kpsn")
    private Date tarikhKeputusan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kpsn_oleh")
    private Pengguna keputusanOleh;
    @Column(name = "ulasan")
    private String ulasan;
    @OneToMany(mappedBy = "permohonan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermohonanUrusan> senaraiUrusan;
    @OneToMany(mappedBy = "permohonan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    
    @OneToMany(mappedBy = "permohonan", fetch = FetchType.LAZY)
    @OrderBy(value="infoAudit.tarikhMasuk")
    private List<FasaPermohonan> senaraiFasa;
    @OneToMany(mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<Transaksi> senaraiTransaksi;
    @OneToMany(mappedBy = "permohonan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermohonanAtasPihakBerkepentingan> senaraiPermohonanAtasPihakBerkepentingan;
    @OneToMany(mappedBy = "permohonan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermohonanAtasPerserahan> senaraiPermohonanAtasPerserahan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sblm")
    private Permohonan permohonanSebelum;



    @Column(name = "utama", nullable = true)
    private Integer utama;

    @Column (name = "bil_mohon")
    private Integer bilanganPermohonan;
    
    @Column (name = "gis_stage_flag", length=1)
    private String stageGis;
    
    @OneToMany(mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<PermohonanPihakKemaskini> senaraiPihakKemaskini;
    @OneToOne (fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private PermohonanAktiviti infoAktiviti;
    
    @OneToMany (mappedBy = "permohonan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermohonanKertas> senaraiKertas;    

    @OneToMany (mappedBy = "permohonan")
    private List<PermohonanTuntutanKos> senaraiTuntutanKos;
    
    // STRATA
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
  //  private PermohonanStrata strata;
    private List<PermohonanStrata> senaraiStrata;

    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<PermohonanBangunan> senaraiBangunan;
    
    // JUBL
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<PermohonanJUBL> senaraiJurukur;
    
    // PENGUATKUASAAN
    @Embedded
    private Aduan aduan;
    
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<OperasiPenguatkuasaan> senaraiOperasi;
    
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<Kompaun> senaraiKompaun;
    
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<NotisPenguatkuasaan> senaraiNotisPenguatkuasaaan;
    
    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<PermohonanLampiranPerintah> senaraiPermohonanLampiranPerintah;
        
    // surat kuasa wakil
    //@OneToOne (fetch = FetchType.LAZY, mappedBy = "permohonan")
    //private WakilPihak wakilPihak;
    
    // urusan pengambilan
    @OneToOne (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private PermohonanPengambilan pengambilan;

    @OneToMany (mappedBy = "permohonan", fetch = FetchType.LAZY)
    private List<PermohonanPembetulanHakmilik> senaraiHakmilikBetul;
    
    @Column(name = "kod_sts", columnDefinition = "CHAR(2)")
    private String status;
    
    /**
     * Senarai dokumen yang digunakan untuk Permohonan ini. E.g. Surat Kuasa Wakil/Amanah/Kebenaran
     */
    @OneToMany (mappedBy = "permohonan")
    private List<PermohonanDokumen> senaraiDokumen;
    
    /**
     * simpan id workflow normal ataupun integration memandangkan id workflow tidak boleh dapat dari BPEL 
     */
    
    @Column(name = "id_workflow", length = 250)
    private String idWorkflow;
    
    @Column(name = "id_stage_sblm", length = 100)
    private String idStagePermohonanSebelum;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_projek")
    private Projek projek;
    
    
    @Column(name = "penerima_nama")
    private String namaPenerima;
    
    @ManyToOne
    @JoinColumn (name = "penerima_kod_pengenalan")
    private KodJenisPengenalan kodPengenalanPenerima;
    
    @Column(name = "penerima_no_pengenalan")
    private String noPengenalanPenerima;
    
    @Column(name = "penerima_alamat1")
    private String alamatPenerima1;
    
    @Column(name = "penerima_alamat2")
    private String alamatPenerima2;
    
    @Column(name = "penerima_alamat3")
    private String alamatPenerima3;
    
    @Column(name = "penerima_alamat4")
    private String alamatPenerima4;
    
    
    @Column(name = "penyampai_nama")
    private String namaPenyampai;
    
    @Column(name = "penyampai_no_pengenalan")
    private String noPengenalanPenyampai;
    
    @Column(name = "penyampai_no_tel")
    private String noTelPenyampai;
    
    @Column(name = "no_psrh")
    private String noPerserahanSptb;
    @Column(name = "ID_MOHON_SBGN")
    private String idMohonSbgn;
    
    
    @Embedded
    private InfoAudit infoAudit;
    
    @Embedded
    private InfoAuditBaru infoAuditBaru;

    public Permohonan() {
        super();
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdKumpulan(String id) {
        this.idKumpulan = id;
    }

    public String getIdKumpulan() {
        return idKumpulan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
		this.caraPermohonan = caraPermohonan;
	}

	public KodCaraPermohonan getCaraPermohonan() {
		return caraPermohonan;
	}

	public void setNoMahkamah(String noMahkamah) {
        this.noMahkamah = noMahkamah;
    }

    public String getNoMahkamah() {
        return noMahkamah;
    }

    public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setNilaiDipohon(BigDecimal nilaiDipohon) {
		this.nilaiDipohon = nilaiDipohon;
	}

	public BigDecimal getNilaiDipohon() {
		return nilaiDipohon;
	}

	public void setRujukanUndang2(String rujukanUndang2) {
		this.rujukanUndang2 = rujukanUndang2;
	}

	public String getRujukanUndang2() {
		return rujukanUndang2;
	}

	public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public Integer getTempohBulan() {
        return tempohBulan;
    }

    public void setTempohBulan(Integer tempohBulan) {
        this.tempohBulan = tempohBulan;
    }

    public Integer getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(Integer tempohHari) {
        this.tempohHari = tempohHari;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiTanahRizab(List<TanahRizabPermohonan> senaraiTanahRizab) {
		this.senaraiTanahRizab = senaraiTanahRizab;
	}

	public List<TanahRizabPermohonan> getSenaraiTanahRizab() {
		return senaraiTanahRizab;
	}

	public void setFolderDokumen(FolderDokumen folder) {
        this.folderDokumen = folder;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public void setKodPenyerah(KodPenyerah kodPenyerah) {
		this.kodPenyerah = kodPenyerah;
	}

	public KodPenyerah getKodPenyerah() {
		return kodPenyerah;
	}

	public void setIdPenyerah(Long idPenyerah) {
		this.idPenyerah = idPenyerah;
	}

	public Long getIdPenyerah() {
		return idPenyerah;
	}

	public KodJenisPengenalan getPenyerahJenisPengenalan() {
		return penyerahJenisPengenalan;
	}

	public void setPenyerahJenisPengenalan(
			KodJenisPengenalan penyerahJenisPengenalan) {
		this.penyerahJenisPengenalan = penyerahJenisPengenalan;
	}

	public String getPenyerahNoPengenalan() {
		return penyerahNoPengenalan;
	}

	public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
		this.penyerahNoPengenalan = penyerahNoPengenalan;
	}

	public String getPenyerahNoRujukan() {
        return penyerahNoRujukan;
    }

    public void setPenyerahNoRujukan(String penyerahNoRujukan) {
        this.penyerahNoRujukan = penyerahNoRujukan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public void setPenyerahNoTelefon1(String penyerahNoTelefon1) {
		this.penyerahNoTelefon1 = penyerahNoTelefon1;
	}

	public String getPenyerahNoTelefon1() {
		return penyerahNoTelefon1;
	}

	public String getPenyerahEmail() {
		return penyerahEmail;
	}

	public void setPenyerahEmail(String email) {
		this.penyerahEmail = email;
	}

	public void setUntukTahun(Integer untukTahun) {
        this.untukTahun = untukTahun;
    }

    public Integer getUntukTahun() {
        return untukTahun;
    }

    public KodKeputusan getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(KodKeputusan keputusan) {
        this.keputusan = keputusan;
    }

    public void setNilaiKeputusan(BigDecimal nilaiKeputusan) {
        this.nilaiKeputusan = nilaiKeputusan;
    }

    public BigDecimal getNilaiKeputusan() {
        return nilaiKeputusan;
    }

    public Date getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(Date tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public Pengguna getKeputusanOleh() {
        return keputusanOleh;
    }

    public void setKeputusanOleh(Pengguna keputusanOleh) {
        this.keputusanOleh = keputusanOleh;
    }

    public List<PermohonanUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<PermohonanUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public void setSenaraiUlasan(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        this.senaraiRujukanLuar = senaraiRujukanLuar;
    }

    public List<PermohonanRujukanLuar> getSenaraiRujukanLuar() {
        return senaraiRujukanLuar;
    }

    public List<FasaPermohonan> getSenaraiFasa() {
        return senaraiFasa;
    }

    public void setSenaraiFasa(List<FasaPermohonan> senaraiFasa) {
        this.senaraiFasa = senaraiFasa;
    }

    public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public Integer getUtama() {
        return utama;
    }

    public void setUtama(Integer utama) {
        this.utama = utama;
    }

    

    public void setBilanganPermohonan(Integer bilanganPermohonan) {
		this.bilanganPermohonan = bilanganPermohonan;
	}

	public Integer getBilanganPermohonan() {
		return bilanganPermohonan;
	}

	public void setInfoAktiviti(PermohonanAktiviti infoAktiviti) {
        this.infoAktiviti = infoAktiviti;
    }

    public PermohonanAktiviti getInfoAktiviti() {
        return infoAktiviti;
    }

    public void setSenaraiKertas(List<PermohonanKertas> senaraiKertas) {
		this.senaraiKertas = senaraiKertas;
	}

	public List<PermohonanKertas> getSenaraiKertas() {
		return senaraiKertas;
	}

	public void setSenaraiTuntutanKos(List<PermohonanTuntutanKos> senaraiTuntutanKos) {
		this.senaraiTuntutanKos = senaraiTuntutanKos;
	}

	public List<PermohonanTuntutanKos> getSenaraiTuntutanKos() {
		return senaraiTuntutanKos;
	}

    public List<PermohonanStrata> getSenaraiStrata() {
        return senaraiStrata;
    }

    public void setSenaraiStrata(List<PermohonanStrata> senaraiStrata) {
        this.senaraiStrata = senaraiStrata;
    }
/*
	public void setStrata(PermohonanStrata strata) {
		this.strata = strata;
	}

	public PermohonanStrata getStrata() {
		return strata;
	}
*/
        
	public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public List<PermohonanPihak> getSenaraiPihak() {
        return senaraiPihak;
    }
    
    public void setSenaraiPihak(List<PermohonanPihak> senarai){
    	senaraiPihak = senarai;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
		this.senaraiPemohon = senaraiPemohon;
	}

	public List<Pemohon> getSenaraiPemohon() {
		return senaraiPemohon;
	}

	public void setSenaraiPenerima(List<PermohonanPihak> senaraiPihak) {
        this.senaraiPihak = senaraiPihak;
    }

    public List<PermohonanAtasPihakBerkepentingan> getSenaraiPermohonanAtasPihakBerkepentingan() {
        return senaraiPermohonanAtasPihakBerkepentingan;
    }

    public void setSenaraiPermohonanAtasPihakBerkepentingan(List<PermohonanAtasPihakBerkepentingan> senaraiPermohonanAtasPihakBerkepentingan) {
        this.senaraiPermohonanAtasPihakBerkepentingan = senaraiPermohonanAtasPihakBerkepentingan;
    }

    public List<PermohonanAtasPerserahan> getSenaraiPermohonanAtasPerserahan() {
        return senaraiPermohonanAtasPerserahan;
    }

    public void setSenaraiPermohonanAtasPerserahan(List<PermohonanAtasPerserahan> senaraiPermohonanAtasPerserahan) {
        this.senaraiPermohonanAtasPerserahan = senaraiPermohonanAtasPerserahan;
    }

    public void setSenaraiPihakKemaskini(List<PermohonanPihakKemaskini> senaraiPihakKemaskini) {
        this.senaraiPihakKemaskini = senaraiPihakKemaskini;
    }

    public List<PermohonanPihakKemaskini> getSenaraiPihakKemaskini() {
        return senaraiPihakKemaskini;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public void setSenaraiBangunan(List<PermohonanBangunan> senaraiBangunan) {
		this.senaraiBangunan = senaraiBangunan;
	}

	public List<PermohonanBangunan> getSenaraiBangunan() {
		return senaraiBangunan;
	}

	public void setSenaraiJurukur(List<PermohonanJUBL> senaraiJurukur) {
		this.senaraiJurukur = senaraiJurukur;
	}

	public List<PermohonanJUBL> getSenaraiJurukur() {
		return senaraiJurukur;
	}

        public Aduan getAduan(){
            return aduan;
        }

        public void setAduan(Aduan aduan){
            this.aduan = aduan;
        }

	public void setSenaraiNotisPenguatkuasaaan(
				List<NotisPenguatkuasaan> senaraiNotisPenguatkuasaaan) {
			this.senaraiNotisPenguatkuasaaan = senaraiNotisPenguatkuasaaan;
		}

		public List<NotisPenguatkuasaan> getSenaraiNotisPenguatkuasaaan() {
			return senaraiNotisPenguatkuasaaan;
		}

	public void setSenaraiOperasi(List<OperasiPenguatkuasaan> senaraiOperasi) {
			this.senaraiOperasi = senaraiOperasi;
		}

		public List<OperasiPenguatkuasaan> getSenaraiOperasi() {
			return senaraiOperasi;
		}

	public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
			this.senaraiKompaun = senaraiKompaun;
		}

		public List<Kompaun> getSenaraiKompaun() {
			return senaraiKompaun;
		}

    public List<PermohonanPembetulanHakmilik> getSenaraiHakmilikBetul() {
        return senaraiHakmilikBetul;
    }

    public void setSenaraiHakmilikBetul(List<PermohonanPembetulanHakmilik> senaraiHakmilikBetul) {
        this.senaraiHakmilikBetul = senaraiHakmilikBetul;
    }

	//public void setWakilPihak(WakilPihak wakilPihak) {
	//	this.wakilPihak = wakilPihak;
	//}

	//public WakilPihak getWakilPihak() {
	//	return wakilPihak;
	//}

	public void setPengambilan(PermohonanPengambilan pengambilan) {
		this.pengambilan = pengambilan;
	}

	public PermohonanPengambilan getPengambilan() {
		return pengambilan;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public int getKumpulanNo() {
        return kumpulanNo;
    }

    public void setKumpulanNo(int kumpulanNo) {
        this.kumpulanNo = kumpulanNo;
    }

    public List<PermohonanLampiranPerintah> getSenaraiPermohonanLampiranPerintah() {
        return senaraiPermohonanLampiranPerintah;
    }

    public void setSenaraiPermohonanLampiranPerintah(List<PermohonanLampiranPerintah> senaraiPermohonanLampiranPerintah) {
        this.senaraiPermohonanLampiranPerintah = senaraiPermohonanLampiranPerintah;
    }

    public List<PermohonanDokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<PermohonanDokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public String getStageGis() {
        return stageGis;
    }

    public void setStageGis(String stageGis) {
        this.stageGis = stageGis;
    }

    public String getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(String idWorkflow) {
        this.idWorkflow = idWorkflow;
    }

    public Projek getProjek() {
        return projek;
    }

    public void setProjek(Projek projek) {
        this.projek = projek;
    }

    public String getIdStagePermohonanSebelum() {
        return idStagePermohonanSebelum;
    }

    public void setIdStagePermohonanSebelum(String idStagePermohonanSebelum) {
        this.idStagePermohonanSebelum = idStagePermohonanSebelum;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public KodJenisPengenalan getKodPengenalanPenerima() {
        return kodPengenalanPenerima;
    }

    public void setKodPengenalanPenerima(KodJenisPengenalan kodPengenalanPenerima) {
        this.kodPengenalanPenerima = kodPengenalanPenerima;
    }

    public String getNoPengenalanPenerima() {
        return noPengenalanPenerima;
    }

    public void setNoPengenalanPenerima(String noPengenalanPenerima) {
        this.noPengenalanPenerima = noPengenalanPenerima;
    }

    public String getAlamatPenerima1() {
        return alamatPenerima1;
    }

    public void setAlamatPenerima1(String alamatPenerima1) {
        this.alamatPenerima1 = alamatPenerima1;
    }

    public String getAlamatPenerima2() {
        return alamatPenerima2;
    }

    public void setAlamatPenerima2(String alamatPenerima2) {
        this.alamatPenerima2 = alamatPenerima2;
    }

    public String getAlamatPenerima3() {
        return alamatPenerima3;
    }

    public void setAlamatPenerima3(String alamatPenerima3) {
        this.alamatPenerima3 = alamatPenerima3;
    }

    public String getAlamatPenerima4() {
        return alamatPenerima4;
    }

    public void setAlamatPenerima4(String alamatPenerima4) {
        this.alamatPenerima4 = alamatPenerima4;
    }

    public String getNamaPenyampai() {
        return namaPenyampai;
    }

    public void setNamaPenyampai(String namaPenyampai) {
        this.namaPenyampai = namaPenyampai;
    }

    public String getNoPengenalanPenyampai() {
        return noPengenalanPenyampai;
    }

    public void setNoPengenalanPenyampai(String noPengenalanPenyampai) {
        this.noPengenalanPenyampai = noPengenalanPenyampai;
    }

    public String getNoTelPenyampai() {
        return noTelPenyampai;
    }

    public void setNoTelPenyampai(String noTelPenyampai) {
        this.noTelPenyampai = noTelPenyampai;
    }

     public String getNoPerserahanSptb() {
          return noPerserahanSptb;
     }

     public void setNoPerserahanSptb(String noPerserahanSptb) {
          this.noPerserahanSptb = noPerserahanSptb;
     }

    public InfoAuditBaru getInfoAuditBaru() {
        return infoAuditBaru;
    }

    public void setInfoAuditBaru(InfoAuditBaru infoAuditBaru) {
        this.infoAuditBaru = infoAuditBaru;
    }

    public String getIdMohonSbgn() {
        return idMohonSbgn;
    }

    public void setIdMohonSbgn(String idMohonSbgn) {
        this.idMohonSbgn = idMohonSbgn;
    }
     
    
}

