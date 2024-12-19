package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "mohon_lapor_tanah")
@SequenceGenerator(name = "seq_mohon_lapor_tanah", sequenceName = "seq_mohon_lapor_tanah")
public class LaporanTanah implements Serializable {

	@Id
	@Column (name = "id_lapor")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_tanah")
	private long idLaporan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

	@Column (name = "spdn_jraya")
	private String namaSempadanJalanraya;

	@Column (name = "spdn_jraya_jarak")
	private BigDecimal jarakSempadanJalanraya;
	
	@ManyToOne
	@JoinColumn (name = "spdn_jraya_jarak_uom")
	private KodUOM jarakSempadanJalanrayaUOM;

	@Column (name = "spdn_ktapi", length = 50)
	private String namaSempadanKeretapi;

	@Column (name = "spdn_ktapi_jarak")
	private BigDecimal jarakSempadanKeretapi;
	
	@ManyToOne
	@JoinColumn (name = "spdn_ktapi_jarak_uom")
	private KodUOM jarakSempadanKeretapiUOM;

	@Column (name = "spdn_laut", length = 50)
	private String namaSempadanLaut;

	@Column (name = "spdn_laut_jarak")
	private BigDecimal jarakSempadanLaut;
	
	@ManyToOne
	@JoinColumn (name = "spdn_laut_jarak_uom")
	private KodUOM jarakSempadanLautUOM;

	@Column (name = "spdn_sg")
	private String namaSempadanSungai;

	@Column (name = "spdn_sg_jarak")
	private BigDecimal jarakSempadanSungai;
	
	@ManyToOne
	@JoinColumn (name = "spdn_sg_jarak_uom")
	private KodUOM jarakSempadanSungaiUOM;

	@Column (name = "spdn_lain")
	private String namaSempadanlain;

	@Column (name = "spdn_lain_jarak")
	private BigDecimal jarakSempadanLain;
	
	@ManyToOne
	@JoinColumn (name = "spdn_lain_jarak_uom")
	private KodUOM jarakSempadanLainUOM;

	@Column (name = "jln_masuk")
	private Character adaJalanMasuk;

	@Column (name = "jln_masuk_catatan")
	private String catatanJalanMasuk;
	@Column (name = "pecah_spdn")
	private String adaPecahSempadan;

	@Column (name = "lapor_utk")
	private String laporanUntuk;
        
        @Column (name = "jenis_laporan")
	private String jenisLaporan;

	@Column (name = "trh_siasat")
	private Date tarikhSiasat;
	/**
	 * keadaan tanah
	 */
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_cerun_tanah")
	private KodKecerunanTanah kecerunanTanah;

    @Column (name = "keadaan_tanah")
    private String keadaanTanah;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_struktur_tanah")
	private KodStrukturTanah strukturTanah;

	@ManyToOne
	@JoinColumn (name = "kod_lapang_tanah")
	private KodKelapanganTanah kelapanganTanah;

	@ManyToOne
	@JoinColumn (name = "usaha_luas_uom")
	private KodUOM usahaLuasUom;
	@Column (name = "usaha_luas")
	private BigDecimal usahaLuas;
	@Column (name = "usaha_bil_pokok")
	private Integer usahaBilanganPokok;
	@Column (name = "usaha_harga")
	private BigDecimal usahaHarga;
	@Column (name = "tukar_syarat")
	private String tukarSyarat ;
	@Column (name = "perenggan")
	private String perenggan ;

	/**
	 * Dalam meter
	 */
	@Column (name = "tinggi_dr_jln")
	private BigDecimal ketinggianDariJalan;

	/**
	 * Dalam darjah
	 */
	@Column (name = "cerun_bukit")
	private BigDecimal kecerunanBukit;

	/**
	 * Dalam darjah
	 */
	@Column (name = "cerun_rendah")
	private BigDecimal kecerunanRendah;

	/**
	 * Dalam darjah
	 */
	@Column (name = "cerun_curam")
	private BigDecimal kecerunanCuram;

	@Column (name = "paras_air")
	private BigDecimal parasAir;

	@Column (name = "tiang_letrik")
	private Character dilintasTiangElektrik;

	@Column (name = "tiang_tel")
	private Character dilintasTiangTelefon;

	@Column (name = "paip_air")
	private Character dilintasPaip;

	@Column (name = "taliair")
	private Character dilintasTaliar;

	@Column (name = "sg")
	private Character dilintasSungai;

	@Column (name = "parit")
	private Character dilintasParit;

	@Column (name = "laluan_gas")
	private Character dilintasLaluanGas;

	/**
	 * Ada bangunan atau tidak?
	 */
	@Column (name = "bngn")
	private Character adaBangunan;

        @Column (name = "bil_bngn")
        private int bilanganBangunan;

	@Column (name = "bngn_bina")
	private Integer bangunanTahunDibina;

	@Column (name = "bngn_diam", length = 1)
	private Character bangunanDidiami;

	@Column (name = "jenis_bngn", length = 100)
	private String jenisBangunan;

	@Column (name = "ranc_kjaan", length = 100)
	private String rancanganKerajaan;

	@Column (name = "spdn_utara_kjaan", length = 1)
	private Character sempadanUtaraMilikKerajaan;

	@Column (name = "spdn_utara_no_lot", length = 10)
	private String sempadanUtaraNoLot;

	@Column (name = "spdn_utara_guna")
	private String sempadanUtaraKegunaan;

	@Column (name = "spdn_utara_jenis", length = 50)
	private String sempadanUtaraJenis;


	@Column (name = "spdn_selatan_kjaan", length = 1)
	private Character sempadanSelatanMilikKerajaan;

	@Column (name = "spdn_selatan_no_lot", length = 10)
	private String sempadanSelatanNoLot;

	@Column (name = "spdn_selatan_guna")
	private String sempadanSelatanKegunaan;

	@Column (name = "spdn_selatan_jenis", length = 50)
	private String sempadanSelatanJenis;

	@Column (name = "spdn_timur_kjaan", length = 1)
	private Character sempadanTimurMilikKerajaan;

	@Column (name = "spdn_timur_no_lot", length = 10)
	private String sempadanTimurNoLot;

	@Column (name = "spdn_timur_guna")
	private String sempadanTimurKegunaan;

	@Column (name = "spdn_timur_jenis", length = 50)
	private String sempadanTimurJenis;

	@Column (name = "spdn_barat_kjaan", length = 1)
	private Character sempadanBaratMilikKerajaan;

	@Column (name = "spdn_barat_no_lot", length = 10)
	private String sempadanBaratNoLot;

	@Column (name = "spdn_barat_guna")
	private String sempadanBaratKegunaan;

	@Column (name = "spdn_barat_jenis", length = 50)
	private String sempadanBaratJenis;

     /**
	 * Flag diusaha atau tidak?
	 */
	@Column (name = "usaha")
	private Character usaha;

	/**
	 * Flag jika diusahakan pemohon
	 */
	@Column (name = "diusaha_pemohon")
	private Character diusahaPemohonan;

	/**
	 * Orang yang mengusahakan (jika selain pemohon)
	 */
	@Column (name = "diusaha", length = 50)
	private String diusaha;

	@Column (name = "mula_usaha")
	private Integer tarikhMulaUsaha;

    @Column (name = "usaha_tanam", length = 100)
	private String usahaTanam;

	@Column (name = "jum_klga_pdah")
	private Integer jumlahKeluargaKenaPindah;

	@Column (name = "bil_pmilik")
	private Integer bilanganPemilik;

	@Column (name = "bil_pihak")
	private Integer bilanganPihakBerkepentingan;

	@Column (name = "nilai_tanah")
	private BigDecimal nilaiTanah;

	@Column (name = "nilai_bngn")
	private BigDecimal nilaiBangunan;

	@Column (name = "jum_nilai")
	private BigDecimal jumlahNilai;

	@Column (name = "struk_tambah_jenis", length = 50)
	private String strukturTambahanJenis;

	@Column (name = "struk_tambah_ked", length = 50)
	private String strukturTambahanKedudukan;

	@Column (name = "struk_tambah_ked_terlunjur", length = 50)
	private String strukturTambahanKedudukanTerlunjur;
	
	@OneToMany (mappedBy = "laporanTanah")
	private List<PermohonanLaporanBangunan> senaraiLaporanBangunan;

	@Column (name = "perusahaan" )
	private String perusahaan;

	@Column (name = "trh_mula_usaha" )
	private Date tarikhMulaUsaha2;

	@Column (name = "lain_lain_bngn" )
	private String lainLainBangunan;

	@Column (name = "jenis_jln" )
	private String jenisJalan;

	@Column (name = "dilintasi_lain" )
	private String dilintasiLain;

	@Column (name = "boleh_berimilik" )
	private String bolehBerimilik;


	@Column (name = "sbb_tidak_boleh_berimilik" )
	private String sebabTidakBolehBerimilik;

	@Column (name = "tanah_bertumpu" )
	private String tanahBertumpu;

	@Column (name = "keterangan_tanah_bertumpu" )
	private String keteranganTanahBertumpu;

	@Column (name = "struktur_tanah_lain" )
	private String strukturTanahLain;

	@ManyToOne
	@JoinColumn (name = "kod_keadaan_tanah")
	private KodKeadaanTanah kodKeadaanTanah;

	@Column (name = "kedudukan_tanah" )
	private String kedudukanTanah;

	@Column (name = "kuasa_seksyen" )
	private String kuasaSeksyen;

	@Column (name = "rang_perisytiharan" )
	private String rangPerisytiharan;

	@Column (name = "dok_tt" )
	private String dokDitandatangan;

	@Column (name = "jenis_dok_tt" )
	private String jenisDokDitandatangan;

	@Column (name = "pengesahan_kepentingan" )
	private String pengesahanKepentingan;

	@Column (name = "catatan_sekatan_hakmilik" )
	private String catatanSekatanHakmilik;

	@Column (name = "mercu_tanda" )
	private String mercuTanda;

	@Column (name = "syor" )
	private String syor;

	@Column (name = "spdn_utara_catatan" )
	private String catatanSempadanUtara;

	@Column (name = "spdn_selatan_catatan" )
	private String catatanSempadanSelatan;

	@Column (name = "spdn_timur_catatan" )
	private String catatanSempadanTimur;

	@Column (name = "spdn_barat_catatan" )
	private String catatanSempadanBarat;


	@Column (name = "spdn_utara_keadaan_tanah" )
	private String keadaanTanahSempadanUtara;

	@Column (name = "spdn_selatan_keadaan_tanah" )
	private String keadaanTanahSempadanSelatan;

	@Column (name = "spdn_timur_keadaan_tanah" )
	private String keadaanTanahSempadanTimur;

	@Column (name = "spdn_barat_keadaan_tanah" )
	private String keadaanTanahSempadanBarat;
        
        @Column (name="jenis_tanah")
        private Character jenisTanah;
        
        @ManyToOne
	@JoinColumn (name = "perenggan_luas_sblm_uom")
	private KodUOM perengganLuasSebelumUom;
	@Column (name = "perenggan_luas_sblm")
	private BigDecimal perengganLuasSebelum;
        
        @ManyToOne
	@JoinColumn (name = "perenggan_luas_uom")
	private KodUOM perengganLuasUom;
	@Column (name = "perenggan_luas")
	private BigDecimal perengganLuas;
        
        @Column (name = "ada_kes")
	private Character adaKes;
        
        @Column (name = "jenis_maraan")
	private Character jenisMaraan;
        
        @Column (name = "tempoh_ditinggalkan")
	private BigDecimal tempohDitinggalkan;
        
        @Column (name = "ulasan")
	private String ulasan;
        
        @Column (name = "grant_probet")
	private Character grantProbet;
        
        @Column (name = "SYOR_URUSAN_PILIHAN")
	private String syorUlasanPilihan;
         
        @Column (name = "SYOR_URUSAN")
	private String syorUrusan;
          
        @Column (name = "SYOR_LAMPIRAN")
	private String syorLampiran;
           
        @Column (name = "SYOR_PELAN_TANAH_PILIHAN")
	private String syorPelanTanahPilihan;
            
        @Column (name = "SYOR_NILAI_BAHAGI")
	private String syorNilaiBahagi;
        
        @Column (name = "SYOR_LOT_PERTANIAN")
	private String syorLotPertanian;
         
        @Column (name = "SYOR_LOT_KEDIAMAN")
	private String syorLotKediaman;
         
        @Column (name = "SYOR_LOT_PERNIAGAAN")
	private String syorLotPerniagaan;
          
        @Column (name = "SYOR_LOT_INDUSTRI")
	private String syorLotIndustri;
           
        @Column (name = "SYOR_LOT_LAIN")
	private String syorLotLain;
            
        @Column (name = "SYOR_CANTUM_BAHAGIAN")
	private String syorCantumBahagian;
        
        @Column (name = "SYOR_TUKAR_DARI")
	private String syorTukarDari;
              
        @Column (name = "SYOR_TUKAR_KEPADA")
	private String syorTukarKepada;
               
        @Column (name = "SYOR_SERAHLOT_PERTANIAN")
	private String syorSerahLotPertanian;
                
        @Column (name = "SYOR_SERAHLOT_KEDIAMAN")
	private String syorSerahLotKediaman;
                 
        @Column (name = "SYOR_SERAHLOT_PERNIAGAAN")
	private String syorSerahLotPerniagaan;
                  
        @Column (name = "SYOR_SERAHLOT_INDUSTRI")
	private String syorSerahLotIndustri;
                   
        @Column (name = "SYOR_SERAHLOT_KEMAJUAN")
	private String syorSerahLotKemajuan;
        
         @Column (name = "SYOR_PELAN_TANAH_PILIHAN_1")
	private String syorPelanTanah1;
          @Column (name = "SYOR_PELAN_TANAH_PILIHAN_2")
	private String syorPelanTanah2;
           @Column (name = "SYOR_PELAN_TANAH_PILIHAN_3")
	private String syorPelanTanah3;
           
        @Column (name = "SYOR_PELAN_TANAH_PILIHAN_4")
	private String syorPelanTanah4;
           
        @Column (name = "SYOR_SEBAHAGIAN_LUAS")
	private String syorSebahagianLuas;
       
        
        @Column (name = "SYOR_SEBAHAGIAN_OUM")
	private String syorSebahagianOum;
        
        @Column (name = "SYOR_SEBAHAGIAN_TUJUAN")
	private String syorSebahagianTujuan;
        
        @Column (name = "SPDN_TIMURLAUT_KJAAN", length = 1)
	private Character sempadanTimurLautMilikKerajaan;
        
        @Column (name = "SPDN_TENGGARA_KJAAN", length = 1)
	private Character sempadanTenggaraMilikKerajaan;
        
        @Column (name = "SPDN_BARATDAYA_KJAAN", length = 1)
	private Character sempadanBaratdayaMilikKerajaan;
        
        @Column (name = "SPDN_BARATLAUT_KJAAN", length = 1)
	private Character sempadanBaratLautMilikKerajaan;
        
        @Column (name = "SPDN_TIMURLAUT_NO_LOT")
	private String sempadanTimurLautNoLot;
        
        @Column (name = "SPDN_TENGGARA_NO_LOT")
	private String sempadanTenggaraNoLot;
        
        @Column (name = "SPDN_BARATDAYA_NO_LOT")
	private String sempadanBaratdayaNoLot;
        
        @Column (name = "SPDN_BARATLAUT_NO_LOT")
	private String sempadanBaratlautNoLot;
        
        @Column (name = "SPDN_TIMURLAUT_GUNA")
	private String sempadanTimurlautKegunaan;
        
        @Column (name = "SPDN_TENGGARA_GUNA")
	private String sempadanTenggaraKegunaan;
        
        @Column (name = "SPDN_BARATLAUT_GUNA")
	private String sempadanBaratlautKegunaan;
        
        @Column (name = "SPDN_BARATDAYA_GUNA")
	private String sempadanBaratdayaKegunaan;
        
        @Column (name = "SPDN_TIMURLAUT_CATATAN" )
	private String catatanSempadanTimurlaut;
        
        @Column (name = "SPDN_TENGGARA_CATATAN" )
	private String catatanSempadanTenggara;
        
        @Column (name = "SPDN_BARATDAYA_CATATAN" )
	private String catatanSempadanBaratdaya;
        
        @Column (name = "SPDN_BARATLAUT_CATATAN" )
	private String catatanSempadanBaratlaut;
        
        @Column (name = "SPDN_TIMURLAUT_JENIS")
	private String sempadanTimurlautJenis;
        
        @Column (name = "SPDN_TENGGARA_JENIS")
	private String sempadanTenggaraJenis;
        
        @Column (name = "SPDN_BARATDAYA_JENIS")
	private String sempadanBaratdayaJenis;
        
        @Column (name = "SPDN_BARATLAUT_JENIS")
	private String sempadanBaratlautJenis;
        
	@Embedded
	private InfoAudit infoAudit;

    public Character getAdaBangunan() {
        return adaBangunan;
    }

    public void setAdaBangunan(Character adaBangunan) {
        this.adaBangunan = adaBangunan;
    }

    public Character getAdaJalanMasuk() {
        return adaJalanMasuk;
    }

    public void setAdaJalanMasuk(Character adaJalanMasuk) {
        this.adaJalanMasuk = adaJalanMasuk;
    }

    public String getAdaPecahSempadan() {
        return adaPecahSempadan;
    }

    public void setAdaPecahSempadan(String adaPecahSempadan) {
        this.adaPecahSempadan = adaPecahSempadan;
    }

    public String getLaporanUntuk() {
        return laporanUntuk;
    }

    public void setLaporanUntuk(String laporanUntuk) {
        this.laporanUntuk = laporanUntuk;
    }

    public Date getTarikhSiasat() {
        return tarikhSiasat;
    }

    public void setTarikhSiasat(Date tarikhSiasat) {
        this.tarikhSiasat = tarikhSiasat;
    }

    public void setCatatanJalanMasuk(String catatanJalanMasuk) {
		this.catatanJalanMasuk = catatanJalanMasuk;
	}

	public String getCatatanJalanMasuk() {
		return catatanJalanMasuk;
	}

	public Character getBangunanDidiami() {
        return bangunanDidiami;
    }

    public void setBangunanDidiami(Character bangunanDidiami) {
        this.bangunanDidiami = bangunanDidiami;
    }

    public Integer getBangunanTahunDibina() {
        return bangunanTahunDibina;
    }

    public void setBangunanTahunDibina(Integer bangunanTahunDibina) {
        this.bangunanTahunDibina = bangunanTahunDibina;
    }

    public Character getDilintasPaip() {
        return dilintasPaip;
    }

    public void setDilintasPaip(Character dilintasPaip) {
        this.dilintasPaip = dilintasPaip;
    }

    public Character getDilintasParit() {
        return dilintasParit;
    }

    public void setDilintasParit(Character dilintasParit) {
        this.dilintasParit = dilintasParit;
    }

    public void setDilintasLaluanGas(Character dilintasLaluanGas) {
		this.dilintasLaluanGas = dilintasLaluanGas;
	}

	public Character getDilintasLaluanGas() {
		return dilintasLaluanGas;
	}

	public Character getDilintasSungai() {
        return dilintasSungai;
    }

    public void setDilintasSungai(Character dilintasSungai) {
        this.dilintasSungai = dilintasSungai;
    }

    public Character getDilintasTaliar() {
        return dilintasTaliar;
    }

    public void setDilintasTaliar(Character dilintasTaliar) {
        this.dilintasTaliar = dilintasTaliar;
    }

    public Character getDilintasTiangElektrik() {
        return dilintasTiangElektrik;
    }

    public void setDilintasTiangElektrik(Character dilintasTiangElektrik) {
        this.dilintasTiangElektrik = dilintasTiangElektrik;
    }

    public Character getDilintasTiangTelefon() {
        return dilintasTiangTelefon;
    }

    public void setDilintasTiangTelefon(Character dilintasTiangTelefon) {
        this.dilintasTiangTelefon = dilintasTiangTelefon;
    }

    public long getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(long idLaporan) {
        this.idLaporan = idLaporan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public BigDecimal getJarakSempadanJalanraya() {
        return jarakSempadanJalanraya;
    }

    public void setJarakSempadanJalanrayaUOM(KodUOM jarakSempadanJalanrayaUOM) {
        this.jarakSempadanJalanrayaUOM = jarakSempadanJalanrayaUOM;
    }

    public KodUOM getJarakSempadanJalanrayaUOM() {
        return jarakSempadanJalanrayaUOM;
    }

    public void setJarakSempadanJalanraya(BigDecimal jarakSempadanJalanraya) {
        this.jarakSempadanJalanraya = jarakSempadanJalanraya;
    }

    public BigDecimal getJarakSempadanKeretapi() {
        return jarakSempadanKeretapi;
    }

    public void setJarakSempadanKeretapi(BigDecimal jarakSempadanKeretapi) {
        this.jarakSempadanKeretapi = jarakSempadanKeretapi;
    }

    public BigDecimal getJarakSempadanLaut() {
        return jarakSempadanLaut;
    }

    public void setJarakSempadanLaut(BigDecimal jarakSempadanLaut) {
        this.jarakSempadanLaut = jarakSempadanLaut;
    }

    public void setJarakSempadanLautUOM(KodUOM jarakSempadanLautUOM) {
        this.jarakSempadanLautUOM = jarakSempadanLautUOM;
    }

    public KodUOM getJarakSempadanLautUOM() {
        return jarakSempadanLautUOM;
    }

    public BigDecimal getJarakSempadanSungai() {
        return jarakSempadanSungai;
    }

    public void setJarakSempadanSungai(BigDecimal jarakSempadanSungai) {
        this.jarakSempadanSungai = jarakSempadanSungai;
    }

    public void setJarakSempadanSungaiUOM(KodUOM jarakSempadanSungaiUOM) {
        this.jarakSempadanSungaiUOM = jarakSempadanSungaiUOM;
    }

    public KodUOM getJarakSempadanSungaiUOM() {
        return jarakSempadanSungaiUOM;
    }

    public BigDecimal getJarakSempadanLaini() {
        return jarakSempadanLain;
    }

    public void setJarakSempadanLaini(BigDecimal jarakSempadanLain) {
        this.jarakSempadanLain = jarakSempadanLain;
    }

    public void setJarakSempadanLainUOM(KodUOM jarakSempadanLainUOM) {
        this.jarakSempadanLainUOM = jarakSempadanLainUOM;
    }

    public KodUOM getJarakSempadanLainUOM() {
        return jarakSempadanLainUOM;
    }

    public String getNamaSempadanlain() {
        return namaSempadanlain;
    }

    public void setNamaSempadanlain(String namaSempadanlain) {
        this.namaSempadanlain = namaSempadanlain;
    }

    public String getJenisBangunan() {
        return jenisBangunan;
    }

    public void setJenisBangunan(String jenisBangunan) {
        this.jenisBangunan = jenisBangunan;
    }

    public BigDecimal getKecerunanBukit() {
        return kecerunanBukit;
    }

    public void setKecerunanBukit(BigDecimal kecerunanBukit) {
        this.kecerunanBukit = kecerunanBukit;
    }

    public BigDecimal getKecerunanCuram() {
        return kecerunanCuram;
    }

    public void setKecerunanCuram(BigDecimal kecerunanCuram) {
        this.kecerunanCuram = kecerunanCuram;
    }

    public BigDecimal getKecerunanRendah() {
        return kecerunanRendah;
    }

    public void setKecerunanRendah(BigDecimal kecerunanRendah) {
        this.kecerunanRendah = kecerunanRendah;
    }

    public KodKecerunanTanah getKecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public KodKelapanganTanah getKelapanganTanah() {
        return kelapanganTanah;
    }

    public void setKelapanganTanah(KodKelapanganTanah kelapanganTanah) {
        this.kelapanganTanah = kelapanganTanah;
    }

    public String getTukarSyarat() {
        return tukarSyarat;
    }

    public void setTukarSyarat(String tukarSyarat) {
        this.tukarSyarat = tukarSyarat;
    }

    public String getPerenggan() {
        return perenggan;
    }

    public void setPerenggan(String perenggan) {
        this.perenggan = perenggan;
    }

    public Integer getUsahaBilanganPokok() {
        return usahaBilanganPokok;
    }

    public void setUsahaBilanganPokok(Integer usahaBilanganPokok) {
        this.usahaBilanganPokok = usahaBilanganPokok;
    }

    public BigDecimal getUsahaHarga() {
        return usahaHarga;
    }

    public void setUsahaHarga(BigDecimal usahaHarga) {
        this.usahaHarga = usahaHarga;
    }

    public BigDecimal getUsahaLuas() {
        return usahaLuas;
    }

    public void setUsahaLuas(BigDecimal usahaLuas) {
        this.usahaLuas = usahaLuas;
    }

    public KodUOM getUsahaLuasUom() {
        return usahaLuasUom;
    }

    public void setUsahaLuasUom(KodUOM usahaLuasUom) {
        this.usahaLuasUom = usahaLuasUom;
    }

    public BigDecimal getKetinggianDariJalan() {
        return ketinggianDariJalan;
    }

    public void setKetinggianDariJalan(BigDecimal ketinggianDariJalan) {
        this.ketinggianDariJalan = ketinggianDariJalan;
    }

    public String getNamaSempadanJalanraya() {
        return namaSempadanJalanraya;
    }

    public void setNamaSempadanJalanraya(String namaSempadanJalanraya) {
        this.namaSempadanJalanraya = namaSempadanJalanraya;
    }

    public String getNamaSempadanKeretapi() {
        return namaSempadanKeretapi;
    }

    public void setNamaSempadanKeretapi(String namaSempadanKeretapi) {
        this.namaSempadanKeretapi = namaSempadanKeretapi;
    }

    public void setJarakSempadanKeretapiUOM(KodUOM jarakSempadanKeretapiUOM) {
        this.jarakSempadanKeretapiUOM = jarakSempadanKeretapiUOM;
    }

    public KodUOM getJarakSempadanKeretapiUOM() {
        return jarakSempadanKeretapiUOM;
    }

    public String getNamaSempadanLaut() {
        return namaSempadanLaut;
    }

    public void setNamaSempadanLaut(String namaSempadanLaut) {
        this.namaSempadanLaut = namaSempadanLaut;
    }

    public String getNamaSempadanSungai() {
        return namaSempadanSungai;
    }

    public void setNamaSempadanSungai(String namaSempadanSungai) {
        this.namaSempadanSungai = namaSempadanSungai;
    }

    public BigDecimal getParasAir() {
        return parasAir;
    }

    public void setParasAir(BigDecimal parasAir) {
        this.parasAir = parasAir;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getRancanganKerajaan() {
        return rancanganKerajaan;
    }

    public void setRancanganKerajaan(String rancanganKerajaan) {
        this.rancanganKerajaan = rancanganKerajaan;
    }

    public String getSempadanBaratKegunaan() {
        return sempadanBaratKegunaan;
    }

    public void setSempadanBaratKegunaan(String sempadanBaratKegunaan) {
        this.sempadanBaratKegunaan = sempadanBaratKegunaan;
    }

    public Character getSempadanBaratMilikKerajaan() {
        return sempadanBaratMilikKerajaan;
    }

    public void setSempadanBaratMilikKerajaan(Character sempadanBaratMilikKerajaan) {
        this.sempadanBaratMilikKerajaan = sempadanBaratMilikKerajaan;
    }

    public String getSempadanBaratNoLot() {
        return sempadanBaratNoLot;
    }

    public void setSempadanBaratNoLot(String sempadanBaratNoLot) {
        this.sempadanBaratNoLot = sempadanBaratNoLot;
    }

    public String getSempadanSelatanKegunaan() {
        return sempadanSelatanKegunaan;
    }

    public void setSempadanSelatanKegunaan(String sempadanSelatanKegunaan) {
        this.sempadanSelatanKegunaan = sempadanSelatanKegunaan;
    }

    public Character getSempadanSelatanMilikKerajaan() {
        return sempadanSelatanMilikKerajaan;
    }

    public void setSempadanSelatanMilikKerajaan(Character sempadanSelatanMilikKerajaan) {
        this.sempadanSelatanMilikKerajaan = sempadanSelatanMilikKerajaan;
    }

    public String getSempadanSelatanNoLot() {
        return sempadanSelatanNoLot;
    }

    public void setSempadanSelatanNoLot(String sempadanSelatanNoLot) {
        this.sempadanSelatanNoLot = sempadanSelatanNoLot;
    }

    public String getSempadanTimurKegunaan() {
        return sempadanTimurKegunaan;
    }

    public void setSempadanTimurKegunaan(String sempadanTimurKegunaan) {
        this.sempadanTimurKegunaan = sempadanTimurKegunaan;
    }

    public Character getSempadanTimurMilikKerajaan() {
        return sempadanTimurMilikKerajaan;
    }

    public void setSempadanTimurMilikKerajaan(Character sempadanTimurMilikKerajaan) {
        this.sempadanTimurMilikKerajaan = sempadanTimurMilikKerajaan;
    }

    public String getSempadanTimurNoLot() {
        return sempadanTimurNoLot;
    }

    public void setSempadanTimurNoLot(String sempadanTimurNoLot) {
        this.sempadanTimurNoLot = sempadanTimurNoLot;
    }

    public String getSempadanUtaraKegunaan() {
        return sempadanUtaraKegunaan;
    }

    public void setSempadanUtaraKegunaan(String sempadanUtaraKegunaan) {
        this.sempadanUtaraKegunaan = sempadanUtaraKegunaan;
    }

    public Character getSempadanUtaraMilikKerajaan() {
        return sempadanUtaraMilikKerajaan;
    }

    public void setSempadanUtaraMilikKerajaan(Character sempadanUtaraMilikKerajaan) {
        this.sempadanUtaraMilikKerajaan = sempadanUtaraMilikKerajaan;
    }

    public String getSempadanUtaraNoLot() {
        return sempadanUtaraNoLot;
    }

    public void setSempadanUtaraNoLot(String sempadanUtaraNoLot) {
        this.sempadanUtaraNoLot = sempadanUtaraNoLot;
    }

    public KodStrukturTanah getStrukturTanah() {
        return strukturTanah;
    }

    public void setStrukturTanah(KodStrukturTanah strukturTanah) {
        this.strukturTanah = strukturTanah;
    }

    public Character getUsaha() {
        return usaha;
    }

    public void setUsaha(Character usaha) {
        this.usaha = usaha;
    }

    public void setDiusahaPemohonan(Character diusahaPemohonan) {
		this.diusahaPemohonan = diusahaPemohonan;
	}

	public Character getDiusahaPemohonan() {
		return diusahaPemohonan;
	}

	public String getUsahaTanam() {
        return usahaTanam;
    }

    public void setUsahaTanam(String usahaTanam) {
        this.usahaTanam = usahaTanam;
    }

    public String getDiusaha() {
        return diusaha;
    }

    public void setDiusaha(String diusaha) {
        this.diusaha = diusaha;
    }

	public void setTarikhMulaUsaha(Integer tarikhMulaUsaha) {
		this.tarikhMulaUsaha = tarikhMulaUsaha;
	}

	public Integer getTarikhMulaUsaha() {
		return tarikhMulaUsaha;
	}

    public int getBilanganBangunan() {
        return bilanganBangunan;
    }

    public void setBilanganBangunan(int bilanganBangunan) {
        this.bilanganBangunan = bilanganBangunan;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Integer getJumlahKeluargaKenaPindah() {
        return jumlahKeluargaKenaPindah;
    }

    public void setJumlahKeluargaKenaPindah(Integer jumlahKeluargaKenaPindah) {
        this.jumlahKeluargaKenaPindah = jumlahKeluargaKenaPindah;
    }

    public Integer getBilanganPemilik() {
        return bilanganPemilik;
    }

    public void setBilanganPemilik(Integer bilanganPemilik) {
        this.bilanganPemilik = bilanganPemilik;
    }

    public Integer getBilanganPihakBerkepentingan() {
        return bilanganPihakBerkepentingan;
    }

    public void setBilanganPihakBerkepentingan(Integer bilanganPihakBerkepentingan) {
        this.bilanganPihakBerkepentingan = bilanganPihakBerkepentingan;
    }

    public BigDecimal getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(BigDecimal nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

    public BigDecimal getNilaiBangunan() {
        return nilaiBangunan;
    }

    public void setNilaiBangunan(BigDecimal nilaiBangunan) {
        this.nilaiBangunan = nilaiBangunan;
    }

    public BigDecimal getJumlahNilai() {
        return jumlahNilai;
    }

    public void setJumlahNilai(BigDecimal jumlahNilai) {
        this.jumlahNilai = jumlahNilai;
    }

    public String getSempadanBaratJenis() {
        return sempadanBaratJenis;
    }

    public void setSempadanBaratJenis(String sempadanBaratJenis) {
        this.sempadanBaratJenis = sempadanBaratJenis;
    }

    public String getSempadanSelatanJenis() {
        return sempadanSelatanJenis;
    }

    public void setSempadanSelatanJenis(String sempadanSelatanJenis) {
        this.sempadanSelatanJenis = sempadanSelatanJenis;
    }

    public String getSempadanTimurJenis() {
        return sempadanTimurJenis;
    }

    public void setSempadanTimurJenis(String sempadanTimurJenis) {
        this.sempadanTimurJenis = sempadanTimurJenis;
    }

    public String getSempadanUtaraJenis() {
        return sempadanUtaraJenis;
    }

    public void setSempadanUtaraJenis(String sempadanUtaraJenis) {
        this.sempadanUtaraJenis = sempadanUtaraJenis;
    }

    public String getStrukturTambahanJenis() {
        return strukturTambahanJenis;
    }

    public void setStrukturTambahanJenis(String strukturTambahanJenis) {
        this.strukturTambahanJenis = strukturTambahanJenis;
    }

    public String getStrukturTambahanKedudukan() {
        return strukturTambahanKedudukan;
    }

    public void setStrukturTambahanKedudukan(String strukturTambahanKedudukan) {
        this.strukturTambahanKedudukan = strukturTambahanKedudukan;
    }

    public String getStrukturTambahanKedudukanTerlunjur() {
        return strukturTambahanKedudukanTerlunjur;
    }

    public void setStrukturTambahanKedudukanTerlunjur(String strukturTambahanKedudukanTerlunjur) {
        this.strukturTambahanKedudukanTerlunjur = strukturTambahanKedudukanTerlunjur;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    public String getJenisJalan() {
        return jenisJalan;
    }

    public void setJenisJalan(String jenisJalan) {
        this.jenisJalan = jenisJalan;
    }

    public String getDilintasiLain() {
        return dilintasiLain;
    }

    public void setDilintasiLain(String dilintasiLain) {
        this.dilintasiLain = dilintasiLain;
    }


    public String getLainLainBangunan() {
        return lainLainBangunan;
    }

    public void setLainLainBangunan(String lainLainBangunan) {
        this.lainLainBangunan = lainLainBangunan;
    }

    public Date getTarikhMulaUsaha2() {
        return tarikhMulaUsaha2;
    }

    public void setTarikhMulaUsaha2(Date tarikhMulaUsaha2) {
        this.tarikhMulaUsaha2 = tarikhMulaUsaha2;
    }




	public void setSenaraiLaporanBangunan(List<PermohonanLaporanBangunan> senaraiLaporanBangunan) {
		this.senaraiLaporanBangunan = senaraiLaporanBangunan;
	}

	public List<PermohonanLaporanBangunan> getSenaraiLaporanBangunan() {
		return senaraiLaporanBangunan;
	}

    public String getBolehBerimilik() {
        return bolehBerimilik;
    }

    public void setBolehBerimilik(String bolehBerimilik) {
        this.bolehBerimilik = bolehBerimilik;
    }

    public BigDecimal getJarakSempadanLain() {
        return jarakSempadanLain;
    }

    public void setJarakSempadanLain(BigDecimal jarakSempadanLain) {
        this.jarakSempadanLain = jarakSempadanLain;
    }

    public String getKeteranganTanahBertumpu() {
        return keteranganTanahBertumpu;
    }

    public void setKeteranganTanahBertumpu(String keteranganTanahBertumpu) {
        this.keteranganTanahBertumpu = keteranganTanahBertumpu;
    }

    public KodKeadaanTanah getKodKeadaanTanah() {
        return kodKeadaanTanah;
    }

    public void setKodKeadaanTanah(KodKeadaanTanah kodKeadaanTanah) {
        this.kodKeadaanTanah = kodKeadaanTanah;
    }

    public String getKedudukanTanah() {
        return kedudukanTanah;
    }

    public void setKedudukanTanah(String kedudukanTanah) {
        this.kedudukanTanah = kedudukanTanah;
    }

    public String getKuasaSeksyen() {
        return kuasaSeksyen;
    }

    public void setKuasaSeksyen(String kuasaSeksyen) {
        this.kuasaSeksyen = kuasaSeksyen;
    }

    public String getRangPerisytiharan() {
        return rangPerisytiharan;
    }

    public void setRangPerisytiharan(String rangPerisytiharan) {
        this.rangPerisytiharan = rangPerisytiharan;
    }

    public String getSebabTidakBolehBerimilik() {
        return sebabTidakBolehBerimilik;
    }

    public void setSebabTidakBolehBerimilik(String sebabTidakBolehBerimilik) {
        this.sebabTidakBolehBerimilik = sebabTidakBolehBerimilik;
    }

    public String getStrukturTanahLain() {
        return strukturTanahLain;
    }

    public void setStrukturTanahLain(String strukturTanahLain) {
        this.strukturTanahLain = strukturTanahLain;
    }

    public String getTanahBertumpu() {
        return tanahBertumpu;
    }

    public void setTanahBertumpu(String tanahBertumpu) {
        this.tanahBertumpu = tanahBertumpu;
    }

    public String getCatatanSekatanHakmilik() {
        return catatanSekatanHakmilik;
    }

    public void setCatatanSekatanHakmilik(String catatanSekatanHakmilik) {
        this.catatanSekatanHakmilik = catatanSekatanHakmilik;
    }

    public String getDokDitandatangan() {
        return dokDitandatangan;
    }

    public void setDokDitandatangan(String dokDitandatangan) {
        this.dokDitandatangan = dokDitandatangan;
    }

    public String getJenisDokDitandatangan() {
        return jenisDokDitandatangan;
    }

    public void setJenisDokDitandatangan(String jenisDokDitandatangan) {
        this.jenisDokDitandatangan = jenisDokDitandatangan;
    }

    public String getMercuTanda() {
        return mercuTanda;
    }

    public void setMercuTanda(String mercuTanda) {
        this.mercuTanda = mercuTanda;
    }

    public String getPengesahanKepentingan() {
        return pengesahanKepentingan;
    }

    public void setPengesahanKepentingan(String pengesahanKepentingan) {
        this.pengesahanKepentingan = pengesahanKepentingan;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getCatatanSempadanBarat() {
        return catatanSempadanBarat;
    }

    public void setCatatanSempadanBarat(String catatanSempadanBarat) {
        this.catatanSempadanBarat = catatanSempadanBarat;
    }

    public String getCatatanSempadanSelatan() {
        return catatanSempadanSelatan;
    }

    public void setCatatanSempadanSelatan(String catatanSempadanSelatan) {
        this.catatanSempadanSelatan = catatanSempadanSelatan;
    }

    public String getCatatanSempadanTimur() {
        return catatanSempadanTimur;
    }

    public void setCatatanSempadanTimur(String catatanSempadanTimur) {
        this.catatanSempadanTimur = catatanSempadanTimur;
    }

    public String getCatatanSempadanUtara() {
        return catatanSempadanUtara;
    }

    public void setCatatanSempadanUtara(String catatanSempadanUtara) {
        this.catatanSempadanUtara = catatanSempadanUtara;
    }

    public String getKeadaanTanahSempadanBarat() {
        return keadaanTanahSempadanBarat;
    }

    public void setKeadaanTanahSempadanBarat(String keadaanTanahSempadanBarat) {
        this.keadaanTanahSempadanBarat = keadaanTanahSempadanBarat;
    }

    public String getKeadaanTanahSempadanSelatan() {
        return keadaanTanahSempadanSelatan;
    }

    public void setKeadaanTanahSempadanSelatan(String keadaanTanahSempadanSelatan) {
        this.keadaanTanahSempadanSelatan = keadaanTanahSempadanSelatan;
    }

    public String getKeadaanTanahSempadanTimur() {
        return keadaanTanahSempadanTimur;
    }

    public void setKeadaanTanahSempadanTimur(String keadaanTanahSempadanTimur) {
        this.keadaanTanahSempadanTimur = keadaanTanahSempadanTimur;
    }

    public String getKeadaanTanahSempadanUtara() {
        return keadaanTanahSempadanUtara;
    }

    public void setKeadaanTanahSempadanUtara(String keadaanTanahSempadanUtara) {
        this.keadaanTanahSempadanUtara = keadaanTanahSempadanUtara;
    }

    public Character getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(Character jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public KodUOM getPerengganLuasSebelumUom() {
        return perengganLuasSebelumUom;
    }

    public void setPerengganLuasSebelumUom(KodUOM perengganLuasSebelumUom) {
        this.perengganLuasSebelumUom = perengganLuasSebelumUom;
    }

    public BigDecimal getPerengganLuasSebelum() {
        return perengganLuasSebelum;
    }

    public void setPerengganLuasSebelum(BigDecimal perengganLuasSebelum) {
        this.perengganLuasSebelum = perengganLuasSebelum;
    }

    public KodUOM getPerengganLuasUom() {
        return perengganLuasUom;
    }

    public void setPerengganLuasUom(KodUOM perengganLuasUom) {
        this.perengganLuasUom = perengganLuasUom;
    }

    public BigDecimal getPerengganLuas() {
        return perengganLuas;
    }

    public void setPerengganLuas(BigDecimal perengganLuas) {
        this.perengganLuas = perengganLuas;
    }

    public Character getAdaKes() {
        return adaKes;
    }

    public void setAdaKes(Character adaKes) {
        this.adaKes = adaKes;
    }

    public Character getJenisMaraan() {
        return jenisMaraan;
    }

    public void setJenisMaraan(Character jenisMaraan) {
        this.jenisMaraan = jenisMaraan;
    }

    public BigDecimal getTempohDitinggalkan() {
        return tempohDitinggalkan;
    }

    public void setTempohDitinggalkan(BigDecimal tempohDitinggalkan) {
        this.tempohDitinggalkan = tempohDitinggalkan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Character getGrantProbet() {
        return grantProbet;
    }

    public void setGrantProbet(Character grantProbet) {
        this.grantProbet = grantProbet;
    }

    public String getSyorUlasanPilihan() {
        return syorUlasanPilihan;
    }

    public void setSyorUlasanPilihan(String syorUlasanPilihan) {
        this.syorUlasanPilihan = syorUlasanPilihan;
    }

    public String getSyorUrusan() {
        return syorUrusan;
    }

    public void setSyorUrusan(String syorUrusan) {
        this.syorUrusan = syorUrusan;
    }

    public String getSyorLampiran() {
        return syorLampiran;
    }

    public void setSyorLampiran(String syorLampiran) {
        this.syorLampiran = syorLampiran;
    }

    public String getSyorPelanTanahPilihan() {
        return syorPelanTanahPilihan;
    }

    public void setSyorPelanTanahPilihan(String syorPelanTanahPilihan) {
        this.syorPelanTanahPilihan = syorPelanTanahPilihan;
    }

    public String getSyorNilaiBahagi() {
        return syorNilaiBahagi;
    }

    public void setSyorNilaiBahagi(String syorNilaiBahagi) {
        this.syorNilaiBahagi = syorNilaiBahagi;
    }

    public String getSyorLotPertanian() {
        return syorLotPertanian;
    }

    public void setSyorLotPertanian(String syorLotPertanian) {
        this.syorLotPertanian = syorLotPertanian;
    }

    public String getSyorLotKediaman() {
        return syorLotKediaman;
    }

    public void setSyorLotKediaman(String syorLotKediaman) {
        this.syorLotKediaman = syorLotKediaman;
    }

    public String getSyorLotPerniagaan() {
        return syorLotPerniagaan;
    }

    public void setSyorLotPerniagaan(String syorLotPerniagaan) {
        this.syorLotPerniagaan = syorLotPerniagaan;
    }

    public String getSyorLotIndustri() {
        return syorLotIndustri;
    }

    public void setSyorLotIndustri(String syorLotIndustri) {
        this.syorLotIndustri = syorLotIndustri;
    }

    public String getSyorLotLain() {
        return syorLotLain;
    }

    public void setSyorLotLain(String syorLotLain) {
        this.syorLotLain = syorLotLain;
    }

    public String getSyorCantumBahagian() {
        return syorCantumBahagian;
    }

    public void setSyorCantumBahagian(String syorCantumBahagian) {
        this.syorCantumBahagian = syorCantumBahagian;
    }

    public String getSyorTukarDari() {
        return syorTukarDari;
    }

    public void setSyorTukarDari(String syorTukarDari) {
        this.syorTukarDari = syorTukarDari;
    }

    public String getSyorTukarKepada() {
        return syorTukarKepada;
    }

    public void setSyorTukarKepada(String syorTukarKepada) {
        this.syorTukarKepada = syorTukarKepada;
    }

    public String getSyorSerahLotPertanian() {
        return syorSerahLotPertanian;
    }

    public void setSyorSerahLotPertanian(String syorSerahLotPertanian) {
        this.syorSerahLotPertanian = syorSerahLotPertanian;
    }

    public String getSyorSerahLotKediaman() {
        return syorSerahLotKediaman;
    }

    public void setSyorSerahLotKediaman(String syorSerahLotKediaman) {
        this.syorSerahLotKediaman = syorSerahLotKediaman;
    }

    public String getSyorSerahLotPerniagaan() {
        return syorSerahLotPerniagaan;
    }

    public void setSyorSerahLotPerniagaan(String syorSerahLotPerniagaan) {
        this.syorSerahLotPerniagaan = syorSerahLotPerniagaan;
    }

    public String getSyorSerahLotIndustri() {
        return syorSerahLotIndustri;
    }

    public void setSyorSerahLotIndustri(String syorSerahLotIndustri) {
        this.syorSerahLotIndustri = syorSerahLotIndustri;
    }

    public String getSyorSerahLotKemajuan() {
        return syorSerahLotKemajuan;
    }

    public void setSyorSerahLotKemajuan(String syorSerahLotKemajuan) {
        this.syorSerahLotKemajuan = syorSerahLotKemajuan;
    }

    public String getSyorPelanTanah1() {
        return syorPelanTanah1;
    }

    public void setSyorPelanTanah1(String syorPelanTanah1) {
        this.syorPelanTanah1 = syorPelanTanah1;
    }

    public String getSyorPelanTanah2() {
        return syorPelanTanah2;
    }

    public void setSyorPelanTanah2(String syorPelanTanah2) {
        this.syorPelanTanah2 = syorPelanTanah2;
    }

    public String getSyorPelanTanah3() {
        return syorPelanTanah3;
    }

    public void setSyorPelanTanah3(String syorPelanTanah3) {
        this.syorPelanTanah3 = syorPelanTanah3;
    }

    public String getSyorSebahagianLuas() {
        return syorSebahagianLuas;
    }

    public void setSyorSebahagianLuas(String syorSebahagianLuas) {
        this.syorSebahagianLuas = syorSebahagianLuas;
    }

    public String getSyorSebahagianOum() {
        return syorSebahagianOum;
    }

    public void setSyorSebahagianOum(String syorSebahagianOum) {
        this.syorSebahagianOum = syorSebahagianOum;
    }

    public String getSyorSebahagianTujuan() {
        return syorSebahagianTujuan;
    }

    public void setSyorSebahagianTujuan(String syorSebahagianTujuan) {
        this.syorSebahagianTujuan = syorSebahagianTujuan;
    }

    public String getSyorPelanTanah4() {
        return syorPelanTanah4;
    }

    public void setSyorPelanTanah4(String syorPelanTanah4) {
        this.syorPelanTanah4 = syorPelanTanah4;
    }

    public Character getSempadanTimurLautMilikKerajaan() {
        return sempadanTimurLautMilikKerajaan;
    }

    public void setSempadanTimurLautMilikKerajaan(Character sempadanTimurLautMilikKerajaan) {
        this.sempadanTimurLautMilikKerajaan = sempadanTimurLautMilikKerajaan;
    }

    public Character getSempadanTenggaraMilikKerajaan() {
        return sempadanTenggaraMilikKerajaan;
    }

    public void setSempadanTenggaraMilikKerajaan(Character sempadanTenggaraMilikKerajaan) {
        this.sempadanTenggaraMilikKerajaan = sempadanTenggaraMilikKerajaan;
    }

    public Character getSempadanBaratdayaMilikKerajaan() {
        return sempadanBaratdayaMilikKerajaan;
    }

    public void setSempadanBaratdayaMilikKerajaan(Character sempadanBaratdayaMilikKerajaan) {
        this.sempadanBaratdayaMilikKerajaan = sempadanBaratdayaMilikKerajaan;
    }

    public Character getSempadanBaratLautMilikKerajaan() {
        return sempadanBaratLautMilikKerajaan;
    }

    public void setSempadanBaratLautMilikKerajaan(Character sempadanBaratLautMilikKerajaan) {
        this.sempadanBaratLautMilikKerajaan = sempadanBaratLautMilikKerajaan;
    }

    public String getSempadanTimurLautNoLot() {
        return sempadanTimurLautNoLot;
    }

    public void setSempadanTimurLautNoLot(String sempadanTimurLautNoLot) {
        this.sempadanTimurLautNoLot = sempadanTimurLautNoLot;
    }

    public String getSempadanTenggaraNoLot() {
        return sempadanTenggaraNoLot;
    }

    public void setSempadanTenggaraNoLot(String sempadanTenggaraNoLot) {
        this.sempadanTenggaraNoLot = sempadanTenggaraNoLot;
    }

    public String getSempadanBaratdayaNoLot() {
        return sempadanBaratdayaNoLot;
    }

    public void setSempadanBaratdayaNoLot(String sempadanBaratdayaNoLot) {
        this.sempadanBaratdayaNoLot = sempadanBaratdayaNoLot;
    }

    public String getSempadanBaratlautNoLot() {
        return sempadanBaratlautNoLot;
    }

    public void setSempadanBaratlautNoLot(String sempadanBaratlautNoLot) {
        this.sempadanBaratlautNoLot = sempadanBaratlautNoLot;
    }

    public String getSempadanTimurlautKegunaan() {
        return sempadanTimurlautKegunaan;
    }

    public void setSempadanTimurlautKegunaan(String sempadanTimurlautKegunaan) {
        this.sempadanTimurlautKegunaan = sempadanTimurlautKegunaan;
    }

    public String getSempadanTenggaraKegunaan() {
        return sempadanTenggaraKegunaan;
    }

    public void setSempadanTenggaraKegunaan(String sempadanTenggaraKegunaan) {
        this.sempadanTenggaraKegunaan = sempadanTenggaraKegunaan;
    }

    public String getSempadanBaratlautKegunaan() {
        return sempadanBaratlautKegunaan;
    }

    public void setSempadanBaratlautKegunaan(String sempadanBaratlautKegunaan) {
        this.sempadanBaratlautKegunaan = sempadanBaratlautKegunaan;
    }

    public String getSempadanBaratdayaKegunaan() {
        return sempadanBaratdayaKegunaan;
    }

    public void setSempadanBaratdayaKegunaan(String sempadanBaratdayaKegunaan) {
        this.sempadanBaratdayaKegunaan = sempadanBaratdayaKegunaan;
    }

    public String getCatatanSempadanTimurlaut() {
        return catatanSempadanTimurlaut;
    }

    public void setCatatanSempadanTimurlaut(String catatanSempadanTimurlaut) {
        this.catatanSempadanTimurlaut = catatanSempadanTimurlaut;
    }

    public String getCatatanSempadanTenggara() {
        return catatanSempadanTenggara;
    }

    public void setCatatanSempadanTenggara(String catatanSempadanTenggara) {
        this.catatanSempadanTenggara = catatanSempadanTenggara;
    }

    public String getCatatanSempadanBaratdaya() {
        return catatanSempadanBaratdaya;
    }

    public void setCatatanSempadanBaratdaya(String catatanSempadanBaratdaya) {
        this.catatanSempadanBaratdaya = catatanSempadanBaratdaya;
    }

    public String getCatatanSempadanBaratlaut() {
        return catatanSempadanBaratlaut;
    }

    public void setCatatanSempadanBaratlaut(String catatanSempadanBaratlaut) {
        this.catatanSempadanBaratlaut = catatanSempadanBaratlaut;
    }

    public String getSempadanTimurlautJenis() {
        return sempadanTimurlautJenis;
    }

    public void setSempadanTimurlautJenis(String sempadanTimurlautJenis) {
        this.sempadanTimurlautJenis = sempadanTimurlautJenis;
    }

    public String getSempadanTenggaraJenis() {
        return sempadanTenggaraJenis;
    }

    public void setSempadanTenggaraJenis(String sempadanTenggaraJenis) {
        this.sempadanTenggaraJenis = sempadanTenggaraJenis;
    }

    public String getSempadanBaratdayaJenis() {
        return sempadanBaratdayaJenis;
    }

    public void setSempadanBaratdayaJenis(String sempadanBaratdayaJenis) {
        this.sempadanBaratdayaJenis = sempadanBaratdayaJenis;
    }

    public String getSempadanBaratlautJenis() {
        return sempadanBaratlautJenis;
    }

    public void setSempadanBaratlautJenis(String sempadanBaratlautJenis) {
        this.sempadanBaratlautJenis = sempadanBaratlautJenis;
    }

}
