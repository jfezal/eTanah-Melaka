package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "op_brg_rampas")
@SequenceGenerator(name = "seq_op_brg_rampas", sequenceName = "seq_op_brg_rampas")
public class BarangRampasan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_op_brg_rampas")
    @Column(name = "id_brg")
    private Long idBarang;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "id_op", nullable = false)
    private OperasiPenguatkuasaan operasi;
    @Column(name = "item", nullable = false)
    private String item;
    @Column(name = "catatan")
    private String catatan;
    @Column(name = "kntt")
    private int kuantiti;
    @Column(name = "tmpt_simpan")
    private String tempatSimpanan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_imej")
    private Dokumen imej;
    @ManyToOne
    @JoinColumn(name = "sts")
    private KodStatusBarangRampasan status;
    @Column(name = "trh_tuntut")
    private Date tarikhTuntut;
    @Column(name = "trh_rampas")
    private Date tarikhRampas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_surat_tuntut")
    private Dokumen suratTuntutan;
    @Column(name = "lps_kpd")
    private String dilepasKepada;
    @Column(name = "lps_no_kp")
    private String dilepasNoKP;
    @Column(name = "lps_trh")
    private Date tarikhDilepas;
    @Column(name = "lps_amaun")
    private BigDecimal amaunJaminan;
    @ManyToOne
    @JoinColumn(name = "lps_oleh")
    private Pengguna dilepaskanOleh;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jamin")
    private Dokumen suratJaminan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lps")
    private Dokumen suratPelepasan;
    @Column(name = "kntt_unit")
    private String kuantitiUnit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "katg_item")
    private KodKategoriItemRampasan kodKategoriItemRampasan;
    @Column(name = "no_daftar")
    private String nomborPendaftaran;
    @Column(name = "trh_daftar")
    private Date tarikhPendaftaran;
    @Column(name = "nama_pemunya")
    private String namaPemunya;
    @Column(name = "alamat1")
    private String alamat1;
    @Column(name = "alamat2")
    private String alamat2;
    @Column(name = "alamat3")
    private String alamat3;
    @Column(name = "alamat4")
    private String alamat4;
    @Column(name = "poskod")
    private String poskod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_negeri")
    private KodNegeri kodNegeri;
    @Column(name = "sts_pemunya")
    private String statusPemunya;
    @Column(name = "no_enjin")
    private String nomborEnjin;
    @Column(name = "no_casis")
    private String nomborCasis;
    @Column(name = "nama_model")
    private String namaModel;
    @Column(name = "buatan")
    private String buatan;
    @Column(name = "kapasiti_enjin")
    private String kapasitiEnjin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bahan_bakar")
    private KodBahanBakar kodBahanBakar;
    @Column(name = "warna")
    private String warna;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kelas_guna")
    private KodKegunaanKenderaan kodKegunaanKenderaan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jenis_badan")
    private KodJenisBadanKenderaan kodJenisBadanKenderaan;
    @Column(name = "tahun_dibuat")
    private String tahunDibuat;
    @Column(name = "muatan_tempat_duduk")
    private Integer muatanTempatDuduk;
    @Column(name = "kadar_lesen")
    private BigDecimal kadarLesen;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_pengenalan_pemunya")
    private KodJenisPengenalan kodJenisPengenalanPemunya;
    @Column(name = "no_pengenalan_pemunya")
    private String noPengenalanPemunya;


    @Column(name = "amaun_jual")
    private BigDecimal amaunJual;

    @Column(name = "amaun_kompaun_syor")
    private BigDecimal amaunKompaunSyor;

    @Column(name = "amaun_kompaun")
    private BigDecimal amaunKompaun;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kompaun")
    private Kompaun kompaun;

	@Column (name = "status_dakwa")
	private String statusDakwaan;

	@Column (name = "no_syarikat")
	private String noSyarikat;

	@Column (name = "pemegang_permit")
	private String pemegangPermit;

    @Column(name = "nama_pemandu")
    private String namaPemandu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_pngl_pemegang_permit")
    private KodJenisPengenalan kodJenisPengenalanPemegangPermit;

	@Column (name = "no_pngl_pemegang_permit")
	private String nomborPengenalanPemegangPermit;

	@Column (name = "tempat_ambil")
	private String tempatPengambilan;

	@Column (name = "tempat_hantar")
	private String tempatPenghantaran;

    @Column(name = "alamat1_pemegang_permit")
    private String alamat1PemegangPermit;
    @Column(name = "alamat2_pemegang_permit")
    private String alamat2PemegangPermit;
    @Column(name = "alamat3_pemegang_permit")
    private String alamat3PemegangPermit;
    @Column(name = "alamat4_pemegang_permit")
    private String alamat4PemegangPermit;
    @Column(name = "poskod_pemegang_permit")
    private String poskodPemegangPermit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_negeri_pemegang_permit")
    private KodNegeri kodNegeriPemegangPermit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oks")
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    
    @Column (name = "kesalahan")
	private String kesalahan;
    
    @Column (name = "sts_kesalahan")
	private String statusKesalahan;
    
    @Column (name = "jns_batuan")
    private String jenisBatuan;
    
    @Column (name = "tpt_tangkap")
    private String tempatTangkap;
    
    @Column (name = "pemunya_no_tel")
    private String noTelPemunya;
    
    @Column (name = "suspek_no_tel")
    private String noTelSuspek;
    
    @Column (name = "suspek_nama")
    private String namaSuspek;
    
    @Column (name = "suspek_no_kp")
    private String noPengenalanSuspek;
    
    @Column (name = "suspek_alamat1")
    private String alamat1Suspek;
    
    @Column (name = "suspek_alamat2")
    private String alamat2Suspek;
    
    @Column (name = "suspek_alamat3")
    private String alamat3Suspek;
    
    @Column (name = "suspek_poskod")
    private String poskodSuspek;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suspek_kod_negeri")
    private KodNegeri kodNegeriSuspek;

    @Column(name = "no_siri")
    private String noSiri;
    
    
    @Column (name = "no_sita")
    private String noSita;
    
    @Column (name = "no_notis")
    private String noNotis;
    
    @ManyToOne
	@JoinColumn (name = "id_koa")
	private OperasiAgensi operasiAgensi; 
    
    @Column (name = "sts_serah_io")
    private String statusSerah;    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_penyerah_brg_op")
    private PenyerahBarangOperasi penyerahBarangOperasi;
    
    @Column (name = "tpt_bongkar")
    private String tempatBongkar;    
    
    @Column (name = "ulasan")
    private String ulasan;    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sts_brg_baru")
    private KodStatusBarangRampasan statusBarangRampasan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_kpsn")
    private KodKeputusan keputusan;
    
    
    @Column(name = "sts_jual")
    private String statusJual;
        
    @Embedded
    private InfoAudit infoAudit;

    public long getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(long idBarang) {
        this.idBarang = idBarang;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public OperasiPenguatkuasaan getOperasi() {
        return operasi;
    }

    public void setOperasi(OperasiPenguatkuasaan operasi) {
        this.operasi = operasi;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public int getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public String getTempatSimpanan() {
        return tempatSimpanan;
    }

    public void setTempatSimpanan(String tempatSimpanan) {
        this.tempatSimpanan = tempatSimpanan;
    }

    public void setImej(Dokumen imej) {
        this.imej = imej;
    }

    public Dokumen getImej() {
        return imej;
    }

    public void setStatus(KodStatusBarangRampasan status) {
        this.status = status;
    }

    public KodStatusBarangRampasan getStatus() {
        return status;
    }

    public Date getTarikhTuntut() {
        return tarikhTuntut;
    }

    public void setTarikhTuntut(Date tarikhTuntut) {
        this.tarikhTuntut = tarikhTuntut;
    }

    public Date getTarikhRampas() {
        return tarikhRampas;
    }

    public void setTarikhRampas(Date tarikhRampas) {
        this.tarikhRampas = tarikhRampas;
    }

    public Dokumen getSuratTuntutan() {
        return suratTuntutan;
    }

    public void setSuratTuntutan(Dokumen suratTuntutan) {
        this.suratTuntutan = suratTuntutan;
    }

    public String getDilepasKepada() {
        return dilepasKepada;
    }

    public void setDilepasKepada(String lepasKepada) {
        this.dilepasKepada = lepasKepada;
    }

    public String getDilepasNoKP() {
        return dilepasNoKP;
    }

    public void setDilepasNoKP(String dilepasNoKP) {
        this.dilepasNoKP = dilepasNoKP;
    }

    public Date getTarikhDilepas() {
        return tarikhDilepas;
    }

    public void setTarikhDilepas(Date tarikhDilepas) {
        this.tarikhDilepas = tarikhDilepas;
    }

    public BigDecimal getAmaunJaminan() {
        return amaunJaminan;
    }

    public void setAmaunJaminan(BigDecimal amaunJaminan) {
        this.amaunJaminan = amaunJaminan;
    }

    public Pengguna getDilepaskanOleh() {
        return dilepaskanOleh;
    }

    public void setDilepaskanOleh(Pengguna dilepaskanOleh) {
        this.dilepaskanOleh = dilepaskanOleh;
    }

    public void setSuratJaminan(Dokumen suratJaminan) {
        this.suratJaminan = suratJaminan;
    }

    public Dokumen getSuratJaminan() {
        return suratJaminan;
    }

    public void setSuratPelepasan(Dokumen suratPelepasan) {
        this.suratPelepasan = suratPelepasan;
    }

    public Dokumen getSuratPelepasan() {
        return suratPelepasan;
    }

    public String getKuantitiUnit() {
        return kuantitiUnit;
    }

    public void setKuantitiUnit(String kuantitiUnit) {
        this.kuantitiUnit = kuantitiUnit;
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

    public BigDecimal getKadarLesen() {
        return kadarLesen;
    }

    public void setKadarLesen(BigDecimal kadarLesen) {
        this.kadarLesen = kadarLesen;
    }

    public void setIdBarang(Long idBarang) {
        this.idBarang = idBarang;
    }

    public KodJenisPengenalan getKodJenisPengenalanPemunya() {
        return kodJenisPengenalanPemunya;
    }

    public void setKodJenisPengenalanPemunya(KodJenisPengenalan kodJenisPengenalanPemunya) {
        this.kodJenisPengenalanPemunya = kodJenisPengenalanPemunya;
    }

    public String getKapasitiEnjin() {
        return kapasitiEnjin;
    }

    public void setKapasitiEnjin(String kapasitiEnjin) {
        this.kapasitiEnjin = kapasitiEnjin;
    }

    public KodBahanBakar getKodBahanBakar() {
        return kodBahanBakar;
    }

    public void setKodBahanBakar(KodBahanBakar kodBahanBakar) {
        this.kodBahanBakar = kodBahanBakar;
    }

    public KodJenisBadanKenderaan getKodJenisBadanKenderaan() {
        return kodJenisBadanKenderaan;
    }

    public void setKodJenisBadanKenderaan(KodJenisBadanKenderaan kodJenisBadanKenderaan) {
        this.kodJenisBadanKenderaan = kodJenisBadanKenderaan;
    }

    public KodKategoriItemRampasan getKodKategoriItemRampasan() {
        return kodKategoriItemRampasan;
    }

    public void setKodKategoriItemRampasan(KodKategoriItemRampasan kodKategoriItemRampasan) {
        this.kodKategoriItemRampasan = kodKategoriItemRampasan;
    }

    public KodKegunaanKenderaan getKodKegunaanKenderaan() {
        return kodKegunaanKenderaan;
    }

    public void setKodKegunaanKenderaan(KodKegunaanKenderaan kodKegunaanKenderaan) {
        this.kodKegunaanKenderaan = kodKegunaanKenderaan;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Integer getMuatanTempatDuduk() {
        return muatanTempatDuduk;
    }

    public void setMuatanTempatDuduk(Integer muatanTempatDuduk) {
        this.muatanTempatDuduk = muatanTempatDuduk;
    }

    public String getNamaModel() {
        return namaModel;
    }

    public void setNamaModel(String namaModel) {
        this.namaModel = namaModel;
    }

    public String getNamaPemunya() {
        return namaPemunya;
    }

    public void setNamaPemunya(String namaPemunya) {
        this.namaPemunya = namaPemunya;
    }

    public String getNomborCasis() {
        return nomborCasis;
    }

    public void setNomborCasis(String nomborCasis) {
        this.nomborCasis = nomborCasis;
    }

    public String getNomborEnjin() {
        return nomborEnjin;
    }

    public void setNomborEnjin(String nomborEnjin) {
        this.nomborEnjin = nomborEnjin;
    }

    public String getBuatan() {
        return buatan;
    }

    public void setBuatan(String buatan) {
        this.buatan = buatan;
    }

    public String getNomborPendaftaran() {
        return nomborPendaftaran;
    }

    public void setNomborPendaftaran(String nomborPendaftaran) {
        this.nomborPendaftaran = nomborPendaftaran;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getStatusPemunya() {
        return statusPemunya;
    }

    public void setStatusPemunya(String statusPemunya) {
        this.statusPemunya = statusPemunya;
    }

    public String getTahunDibuat() {
        return tahunDibuat;
    }

    public void setTahunDibuat(String tahunDibuat) {
        this.tahunDibuat = tahunDibuat;
    }

    public Date getTarikhPendaftaran() {
        return tarikhPendaftaran;
    }

    public void setTarikhPendaftaran(Date tarikhPendaftaran) {
        this.tarikhPendaftaran = tarikhPendaftaran;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoPengenalanPemunya() {
        return noPengenalanPemunya;
    }

    public void setNoPengenalanPemunya(String noPengenalanPemunya) {
        this.noPengenalanPemunya = noPengenalanPemunya;
    }

    public BigDecimal getAmaunKompaun() {
        return amaunKompaun;
    }

    public void setAmaunKompaun(BigDecimal amaunKompaun) {
        this.amaunKompaun = amaunKompaun;
    }

    public BigDecimal getAmaunKompaunSyor() {
        return amaunKompaunSyor;
    }

    public void setAmaunKompaunSyor(BigDecimal amaunKompaunSyor) {
        this.amaunKompaunSyor = amaunKompaunSyor;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public String getNoSyarikat() {
        return noSyarikat;
    }

    public void setNoSyarikat(String noSyarikat) {
        this.noSyarikat = noSyarikat;
    }

    public KodJenisPengenalan getKodJenisPengenalanPemegangPermit() {
        return kodJenisPengenalanPemegangPermit;
    }

    public void setKodJenisPengenalanPemegangPermit(KodJenisPengenalan kodJenisPengenalanPemegangPermit) {
        this.kodJenisPengenalanPemegangPermit = kodJenisPengenalanPemegangPermit;
    }

    public String getNomborPengenalanPemegangPermit() {
        return nomborPengenalanPemegangPermit;
    }

    public void setNomborPengenalanPemegangPermit(String nomborPengenalanPemegangPermit) {
        this.nomborPengenalanPemegangPermit = nomborPengenalanPemegangPermit;
    }

    public String getPemegangPermit() {
        return pemegangPermit;
    }

    public void setPemegangPermit(String pemegangPermit) {
        this.pemegangPermit = pemegangPermit;
    }

    public String getTempatPengambilan() {
        return tempatPengambilan;
    }

    public void setTempatPengambilan(String tempatPengambilan) {
        this.tempatPengambilan = tempatPengambilan;
    }

    public String getTempatPenghantaran() {
        return tempatPenghantaran;
    }

    public void setTempatPenghantaran(String tempatPenghantaran) {
        this.tempatPenghantaran = tempatPenghantaran;
    }

    public String getAlamat1PemegangPermit() {
        return alamat1PemegangPermit;
    }

    public void setAlamat1PemegangPermit(String alamat1PemegangPermit) {
        this.alamat1PemegangPermit = alamat1PemegangPermit;
    }

    public String getAlamat2PemegangPermit() {
        return alamat2PemegangPermit;
    }

    public void setAlamat2PemegangPermit(String alamat2PemegangPermit) {
        this.alamat2PemegangPermit = alamat2PemegangPermit;
    }

    public String getAlamat3PemegangPermit() {
        return alamat3PemegangPermit;
    }

    public void setAlamat3PemegangPermit(String alamat3PemegangPermit) {
        this.alamat3PemegangPermit = alamat3PemegangPermit;
    }

    public String getAlamat4PemegangPermit() {
        return alamat4PemegangPermit;
    }

    public void setAlamat4PemegangPermit(String alamat4PemegangPermit) {
        this.alamat4PemegangPermit = alamat4PemegangPermit;
    }

    public KodNegeri getKodNegeriPemegangPermit() {
        return kodNegeriPemegangPermit;
    }

    public void setKodNegeriPemegangPermit(KodNegeri kodNegeriPemegangPermit) {
        this.kodNegeriPemegangPermit = kodNegeriPemegangPermit;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public String getPoskodPemegangPermit() {
        return poskodPemegangPermit;
    }

    public void setPoskodPemegangPermit(String poskodPemegangPermit) {
        this.poskodPemegangPermit = poskodPemegangPermit;
    }

  



    public String getStatusDakwaan() {
        return statusDakwaan;
    }

    public void setStatusDakwaan(String statusDakwaan) {
        this.statusDakwaan = statusDakwaan;
    }

    public String getNamaPemandu() {
        return namaPemandu;
    }

    public void setNamaPemandu(String namaPemandu) {
        this.namaPemandu = namaPemandu;
    }

    public String getKesalahan() {
        return kesalahan;
    }

    public void setKesalahan(String kesalahan) {
        this.kesalahan = kesalahan;
    }

    public String getStatusKesalahan() {
        return statusKesalahan;
    }

    public void setStatusKesalahan(String statusKesalahan) {
        this.statusKesalahan = statusKesalahan;
    }

    public String getAlamat1Suspek() {
        return alamat1Suspek;
    }

    public void setAlamat1Suspek(String alamat1Suspek) {
        this.alamat1Suspek = alamat1Suspek;
    }

    public String getAlamat2Suspek() {
        return alamat2Suspek;
    }

    public void setAlamat2Suspek(String alamat2Suspek) {
        this.alamat2Suspek = alamat2Suspek;
    }

    public String getAlamat3Suspek() {
        return alamat3Suspek;
    }

    public void setAlamat3Suspek(String alamat3Suspek) {
        this.alamat3Suspek = alamat3Suspek;
    }

    public String getJenisBatuan() {
        return jenisBatuan;
    }

    public void setJenisBatuan(String jenisBatuan) {
        this.jenisBatuan = jenisBatuan;
    }

    public String getNamaSuspek() {
        return namaSuspek;
    }

    public void setNamaSuspek(String namaSuspek) {
        this.namaSuspek = namaSuspek;
    }

    public String getNoPengenalanSuspek() {
        return noPengenalanSuspek;
    }

    public void setNoPengenalanSuspek(String noPengenalanSuspek) {
        this.noPengenalanSuspek = noPengenalanSuspek;
    }

    public String getNoTelPemunya() {
        return noTelPemunya;
    }

    public void setNoTelPemunya(String noTelPemunya) {
        this.noTelPemunya = noTelPemunya;
    }

    public String getNoTelSuspek() {
        return noTelSuspek;
    }

    public void setNoTelSuspek(String noTelSuspek) {
        this.noTelSuspek = noTelSuspek;
    }

    public String getPoskodSuspek() {
        return poskodSuspek;
    }

    public void setPoskodSuspek(String poskodSuspek) {
        this.poskodSuspek = poskodSuspek;
    }

    public String getTempatTangkap() {
        return tempatTangkap;
    }

    public void setTempatTangkap(String tempatTangkap) {
        this.tempatTangkap = tempatTangkap;
    }

    public String getNoSiri() {
        return noSiri;
    }

    public void setNoSiri(String noSiri) {
        this.noSiri = noSiri;
    }

    public KodNegeri getKodNegeriSuspek() {
        return kodNegeriSuspek;
    }

    public void setKodNegeriSuspek(KodNegeri kodNegeriSuspek) {
        this.kodNegeriSuspek = kodNegeriSuspek;
    }

    public String getNoSita() {
        return noSita;
    }

    public void setNoSita(String noSita) {
        this.noSita = noSita;
    }

    public String getNoNotis() {
        return noNotis;
    }

    public void setNoNotis(String noNotis) {
        this.noNotis = noNotis;
    }

    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }

    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }

    public String getStatusSerah() {
        return statusSerah;
    }

    public void setStatusSerah(String statusSerah) {
        this.statusSerah = statusSerah;
    }

    public PenyerahBarangOperasi getPenyerahBarangOperasi() {
        return penyerahBarangOperasi;
    }

    public void setPenyerahBarangOperasi(PenyerahBarangOperasi penyerahBarangOperasi) {
        this.penyerahBarangOperasi = penyerahBarangOperasi;
    }

    public String getTempatBongkar() {
        return tempatBongkar;
    }

    public void setTempatBongkar(String tempatBongkar) {
        this.tempatBongkar = tempatBongkar;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public KodStatusBarangRampasan getStatusBarangRampasan() {
        return statusBarangRampasan;
    }

    public void setStatusBarangRampasan(KodStatusBarangRampasan statusBarangRampasan) {
        this.statusBarangRampasan = statusBarangRampasan;
    }

    public KodKeputusan getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(KodKeputusan keputusan) {
        this.keputusan = keputusan;
    }

    public String getStatusJual() {
        return statusJual;
    }

    public void setStatusJual(String statusJual) {
        this.statusJual = statusJual;
    }

    public BigDecimal getAmaunJual() {
        return amaunJual;
    }

    public void setAmaunJual(BigDecimal amaunJual) {
        this.amaunJual = amaunJual;
    }
    
}
