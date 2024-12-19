package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "mohon_strata")
@SequenceGenerator(name = "seq_mohon_strata", sequenceName = "seq_mohon_strata")
public class PermohonanStrata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_strata")
    @Column(name = "id_strata")
    private long idStrata;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    @Column(name = "no_buku")
    private String noBuku;
    // nama projek
    @Column(name = "nama", nullable = false)
    private String nama;
    @Column(name = "nama_skim")
    private String namaSkim;
    @Column(name = "pmilik", nullable = false)
    private String pemilikNama;
    @Column(name = "pmilik_alamat1")
    private String pemilikAlamat1;
    @Column(name = "pmilik_alamat2")
    private String pemilikAlamat2;
    @Column(name = "pmilik_alamat3")
    private String pemilikAlamat3;
    @Column(name = "pmilik_alamat4")
    private String pemilikAlamat4;
    @Column(name = "pmilik_poskod")
    private String poskod;
    @ManyToOne
    @JoinColumn(name = "pmilik_kod_negeri")
    private KodNegeri negeri;
    @Column(name = "trh_siap")
    private Date tarikhSiap;
    @Column(name = "amaun_kos")
    private BigDecimal amaunKos;
    @Column(name = "harga_petak")
    private BigDecimal hargaPetak;
    @Column(name = "jumlah_syer")
    private Integer jumlahSyer;
    @Column(name = "no_pab")
    private String noPAB;
    // CF
    @Column(name = "cf_no_sijil")
    private String cfNoSijil;
    @Column(name = "cf_trh_keluar")
    private Date cfTarikhKeluar;
    // Perakuan Sijil Kos Rendah
    @Column(name = "kos_rendah_no_sijil")
    private String perakuanKosRendahNoSijil;
    @Column(name = "kos_rendah_trh_keluar")
    private Date perakuanKosRendahTarikhKeluar;
    // LAPORAN
    @Column(name = "lapor_lokasi")
    private String laporanLokasi;
    @Column(name = "lapor_katg_bgn")
    private String laporanKategoriBangunani;
    @Column(name = "lapor_ulasan")
    private String laporanUlasan;
    @Column(name = "lapor_tanah")
    private String laporankeadaanTanah;
    // pekan/bandar terhampir
    @Column(name = "lapor_bandar")
    private String laporanBandarTerdekat;
    // flag kegunaan utk perniagaan
    @Column(name = "lapor_utk_niaga")
    private Character laporanUntukPerniagaan;
    // flag kegunaan utk kediaman
    @Column(name = "lapor_utk_duduk")
    private Character laporanUntukKediaman;
    // flag kegunaan utk pejabat
    @Column(name = "lapor_utk_pejabat")
    private Character laporanUntukPejabat;
    // description if being used for other
    @Column(name = "lapor_utk_lain")
    private String laporanKegunaanLain;
    // sama ada projek perumahan kos rendah/sederhana
    @Column(name = "lapor_kos_rendah")
    private Character laporanKosRendah;
    @Column(name = "lapor_bil_bngn")
    private Integer laporanBilBangunan;
    @Column(name = "lapor_bil_petak")
    private Integer laporanBilPetak;
    @Column(name = "lapor_bil_bngn_prov")
    private Integer laporanBilBangunanProvisional;
    @Column(name = "lapor_bil_petak_prov")
    private Integer laporanBilPetakProvisional;
    @Column(name = "lapor_bil_petak_tanah")
    private Integer laporanBilPetakTanah;
    // LAPORAN KEMUDAHAN2
    @Column(name = "lapor_kemudahan1")
    private String laporanKemudahan1;
    @Column(name = "lapor_kemudahan2")
    private String laporanKemudahan2;
    @Column(name = "lapor_kemudahan3")
    private String laporanKemudahan3;
    @Column(name = "lapor_kemudahan4")
    private String laporanKemudahan4;
    @Column(name = "lapor_kemudahan5")
    private String laporanKemudahan5;
    @Column(name = "lapor_kemudahan6")
    private String laporanKemudahan6;
    @Column(name = "lapor_kemudahan7")
    private String laporanKemudahan7;
    @Column(name = "lapor_kemudahan8")
    private String laporanKemudahan8;
    @Column(name = "lapor_kemudahan9")
    private String laporanKemudahan9;
    @Column(name = "lapor_kemudahan10")
    private String laporanKemudahan10;
    @Column(name = "lapor_kemudahan11")
    private String laporanKemudahan11;
    @Column(name = "lapor_kemudahan12")
    private String laporanKemudahan12;       
    @Column(name = "lapor_kemudahan13")
    private String laporanKemudahan13; 
    @Column(name = "lapor_kemudahan14")
    private String laporanKemudahan14; 
    // LAPORAN SEMPADAN
    @Column(name = "spdn_utara_kjaan", length = 1)
    private Character sempadanUtaraMilikKerajaan;
    @Column(name = "spdn_utara_no_lot", length = 10)
    private String sempadanUtaraNoLot;
    @Column(name = "spdn_utara_guna", length = 50)
    private String sempadanUtaraKegunaan;
    @Column(name = "spdn_selatan_kjaan", length = 1)
    private Character sempadanSelatanMilikKerajaan;
    @Column(name = "spdn_selatan_no_lot", length = 10)
    private String sempadanSelatanNoLot;
    @Column(name = "spdn_selatan_guna", length = 50)
    private String sempadanSelatanKegunaan;
    @Column(name = "spdn_timur_kjaan", length = 1)
    private Character sempadanTimurMilikKerajaan;
    @Column(name = "spdn_timur_no_lot", length = 10)
    private String sempadanTimurNoLot;
    @Column(name = "spdn_timur_guna", length = 50)
    private String sempadanTimurKegunaan;
    @Column(name = "spdn_barat_kjaan", length = 1)
    private Character sempadanBaratMilikKerajaan;
    @Column(name = "spdn_barat_no_lot", length = 10)
    private String sempadanBaratNoLot;
    @Column(name = "spdn_barat_guna", length = 50)
    private String sempadanBaratKegunaan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dilapor")
    private Pengguna laporanOleh;

    @Column(name = "trh_lapor")
    private Date tarikhLaporan;
    
    @Column(name = "mc_no_sijil")
    private String mcNoSijil;
    
    @Column(name = "mc_trh_sijil")
    private Date mcTrhSijil;
    
    @Column(name = "mc_no_ruj")
    private String mcNoRuj;
    
    @Column(name = "mc_trh_ruj")
    private Date mcTrhRuj;

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @Embedded
    private InfoAudit infoAudit;

	@Column (name = "kos_rendah")
	private char kosRendah;
	
	
    public long getIdStrata() {
        return idStrata;
    }


    public void setIdStrata(long idStrata) {
        this.idStrata = idStrata;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
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

    public void setNoBuku(String noBuku) {
        this.noBuku = noBuku;
    }

    public String getNoBuku() {
        return noBuku;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaSkim() {
        return namaSkim;
    }

    public void setNamaSkim(String namaSkim) {
        this.namaSkim = namaSkim;
    }

    public String getPemilikNama() {
        return pemilikNama;
    }

    public void setPemilikNama(String pemilikNama) {
        this.pemilikNama = pemilikNama;
    }

    public String getPemilikAlamat1() {
        return pemilikAlamat1;
    }

    public void setPemilikAlamat1(String pemilikAlamat1) {
        this.pemilikAlamat1 = pemilikAlamat1;
    }

    public String getPemilikAlamat2() {
        return pemilikAlamat2;
    }

    public void setPemilikAlamat2(String pemilikAlamat2) {
        this.pemilikAlamat2 = pemilikAlamat2;
    }

    public String getPemilikAlamat3() {
        return pemilikAlamat3;
    }

    public void setPemilikAlamat3(String pemilikAlamat3) {
        this.pemilikAlamat3 = pemilikAlamat3;
    }

    public String getPemilikAlamat4() {
        return pemilikAlamat4;
    }

    public void setPemilikAlamat4(String pemilikAlamat4) {
        this.pemilikAlamat4 = pemilikAlamat4;
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

    public Date getTarikhSiap() {
        return tarikhSiap;
    }

    public void setTarikhSiap(Date tarikhSiap) {
        this.tarikhSiap = tarikhSiap;
    }

    public BigDecimal getAmaunKos() {
        return amaunKos;
    }

    public void setAmaunKos(BigDecimal amaunKos) {
        this.amaunKos = amaunKos;
    }

    public BigDecimal getHargaPetak() {
        return hargaPetak;
    }

    public void setHargaPetak(BigDecimal hargaPetak) {
        this.hargaPetak = hargaPetak;
    }

    public void setJumlahSyer(Integer jumlahSyer) {
        this.jumlahSyer = jumlahSyer;
    }

    public Integer getJumlahSyer() {
        return jumlahSyer;
    }

    public void setNoPAB(String noPAB) {
        this.noPAB = noPAB;
    }

    public String getNoPAB() {
        return noPAB;
    }

    public String getCfNoSijil() {
        return cfNoSijil;
    }

    public void setCfNoSijil(String cfNoSijil) {
        this.cfNoSijil = cfNoSijil;
    }

    public Date getCfTarikhKeluar() {
        return cfTarikhKeluar;
    }

    public void setCfTarikhKeluar(Date cfTarikhKeluar) {
        this.cfTarikhKeluar = cfTarikhKeluar;
    }

    public void setPerakuanKosRendahNoSijil(String perakuanKosRendahNoSijil) {
        this.perakuanKosRendahNoSijil = perakuanKosRendahNoSijil;
    }


	public char getKosRendah() {
		return kosRendah;
	}

    public String getPerakuanKosRendahNoSijil() {
        return perakuanKosRendahNoSijil;
    }
	public void setKosRendah(char kosRendah) {
		this.kosRendah = kosRendah;
	}

    public void setPerakuanKosRendahTarikhKeluar(
            Date perakuanKosRendahTarikhKeluar) {
        this.perakuanKosRendahTarikhKeluar = perakuanKosRendahTarikhKeluar;
    }

    public Date getPerakuanKosRendahTarikhKeluar() {
        return perakuanKosRendahTarikhKeluar;
    }


    public String getLaporanLokasi() {
        return laporanLokasi;
    }

    public void setLaporanLokasi(String laporanLokasi) {
        this.laporanLokasi = laporanLokasi;
    }

    public String getLaporankeadaanTanah() {
        return laporankeadaanTanah;
    }

    public void setLaporankeadaanTanah(String laporankeadaanTanah) {
        this.laporankeadaanTanah = laporankeadaanTanah;
    }

    public String getLaporanKategoriBangunani() {
        return laporanKategoriBangunani;
    }

    public void setLaporanKategoriBangunani(String laporanKategoriBangunani) {
        this.laporanKategoriBangunani = laporanKategoriBangunani;
    }

    public String getLaporanUlasan() {
        return laporanUlasan;
    }

    public void setLaporanUlasan(String laporanUlasan) {
        this.laporanUlasan = laporanUlasan;
    }



    public String getLaporanBandarTerdekat() {
        return laporanBandarTerdekat;
    }

    public void setLaporanBandarTerdekat(String laporanBandarTerdekat) {
        this.laporanBandarTerdekat = laporanBandarTerdekat;
    }

    public Character getLaporanUntukPerniagaan() {
        return laporanUntukPerniagaan;
    }

    public void setLaporanUntukPerniagaan(Character laporanUntukPerniagaan) {
        this.laporanUntukPerniagaan = laporanUntukPerniagaan;
    }

    public Character getLaporanUntukKediaman() {
        return laporanUntukKediaman;
    }

    public void setLaporanUntukKediaman(Character laporanUntukKediaman) {
        this.laporanUntukKediaman = laporanUntukKediaman;
    }

    public Character getLaporanUntukPejabat() {
        return laporanUntukPejabat;
    }

    public void setLaporanUntukPejabat(Character lapotanUntukPejabat) {
        this.laporanUntukPejabat = lapotanUntukPejabat;
    }

    public String getLaporanKegunaanLain() {
        return laporanKegunaanLain;
    }

    public void setLaporanKegunaanLain(String laporanKegunaanLain) {
        this.laporanKegunaanLain = laporanKegunaanLain;
    }

    public Character getLaporanKosRendah() {
        return laporanKosRendah;
    }

    public void setLaporanKosRendah(Character laporanKosRendah) {
        this.laporanKosRendah = laporanKosRendah;
    }

    public Integer getLaporanBilBangunan() {
        return laporanBilBangunan;
    }

    public void setLaporanBilBangunan(Integer laporanBilBangunan) {
        this.laporanBilBangunan = laporanBilBangunan;
    }

    public Integer getLaporanBilPetak() {
        return laporanBilPetak;
    }

    public void setLaporanBilPetak(Integer laporanBilPetak) {
        this.laporanBilPetak = laporanBilPetak;
    }

    public Integer getLaporanBilBangunanProvisional() {
        return laporanBilBangunanProvisional;
    }

    public void setLaporanBilBangunanProvisional(
            Integer laporanBilBangunanProvisional) {
        this.laporanBilBangunanProvisional = laporanBilBangunanProvisional;
    }

    public Integer getLaporanBilPetakProvisional() {
        return laporanBilPetakProvisional;
    }

    public void setLaporanBilPetakProvisional(Integer laporanBilPetakProvisional) {
        this.laporanBilPetakProvisional = laporanBilPetakProvisional;
    }

    public String getLaporanKemudahan1() {
        return laporanKemudahan1;
    }

    public void setLaporanKemudahan1(String laporanKemudahan1) {
        this.laporanKemudahan1 = laporanKemudahan1;
    }

    public String getLaporanKemudahan2() {
        return laporanKemudahan2;
    }

    public void setLaporanKemudahan2(String laporanKemudahan2) {
        this.laporanKemudahan2 = laporanKemudahan2;
    }

    public String getLaporanKemudahan3() {
        return laporanKemudahan3;
    }

    public void setLaporanKemudahan3(String laporanKemudahan3) {
        this.laporanKemudahan3 = laporanKemudahan3;
    }

    public String getLaporanKemudahan4() {
        return laporanKemudahan4;
    }

    public void setLaporanKemudahan4(String laporanKemudahan4) {
        this.laporanKemudahan4 = laporanKemudahan4;
    }

    public String getLaporanKemudahan5() {
        return laporanKemudahan5;
    }

    public void setLaporanKemudahan5(String laporanKemudahan5) {
        this.laporanKemudahan5 = laporanKemudahan5;
    }

    public String getLaporanKemudahan6() {
        return laporanKemudahan6;
    }

    public void setLaporanKemudahan6(String laporanKemudahan6) {
        this.laporanKemudahan6 = laporanKemudahan6;
    }

    public String getLaporanKemudahan7() {
        return laporanKemudahan7;
    }

    public void setLaporanKemudahan7(String laporanKemudahan7) {
        this.laporanKemudahan7 = laporanKemudahan7;
    }

    public String getLaporanKemudahan8() {
        return laporanKemudahan8;
    }

    public void setLaporanKemudahan8(String laporanKemudahan8) {
        this.laporanKemudahan8 = laporanKemudahan8;
    }

    public String getLaporanKemudahan9() {
        return laporanKemudahan9;
    }

    public void setLaporanKemudahan9(String laporanKemudahan9) {
        this.laporanKemudahan9 = laporanKemudahan9;
    }

    public String getLaporanKemudahan10() {
        return laporanKemudahan10;
    }

    public void setLaporanKemudahan10(String laporanKemudahan10) {
        this.laporanKemudahan10 = laporanKemudahan10;
    }

    public String getLaporanKemudahan11() {
        return laporanKemudahan11;
    }

    public void setLaporanKemudahan11(String laporanKemudahan11) {
        this.laporanKemudahan11 = laporanKemudahan11;
    }

    public String getLaporanKemudahan12() {
        return laporanKemudahan12;
    }

    public void setLaporanKemudahan12(String laporanKemudahan12) {
        this.laporanKemudahan12 = laporanKemudahan12;
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

    public String getSempadanUtaraKegunaan() {
        return sempadanUtaraKegunaan;
    }

    public void setSempadanUtaraKegunaan(String sempadanUtaraKegunaan) {
        this.sempadanUtaraKegunaan = sempadanUtaraKegunaan;
    }

    public Character getSempadanSelatanMilikKerajaan() {
        return sempadanSelatanMilikKerajaan;
    }

    public void setSempadanSelatanMilikKerajaan(
            Character sempadanSelatanMilikKerajaan) {
        this.sempadanSelatanMilikKerajaan = sempadanSelatanMilikKerajaan;
    }

    public String getSempadanSelatanNoLot() {
        return sempadanSelatanNoLot;
    }

    public void setSempadanSelatanNoLot(String sempadanSelatanNoLot) {
        this.sempadanSelatanNoLot = sempadanSelatanNoLot;
    }

    public String getSempadanSelatanKegunaan() {
        return sempadanSelatanKegunaan;
    }

    public void setSempadanSelatanKegunaan(String sempadanSelatanKegunaan) {
        this.sempadanSelatanKegunaan = sempadanSelatanKegunaan;
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

    public String getSempadanTimurKegunaan() {
        return sempadanTimurKegunaan;
    }

    public void setSempadanTimurKegunaan(String sempadanTimurKegunaan) {
        this.sempadanTimurKegunaan = sempadanTimurKegunaan;
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

    public String getSempadanBaratKegunaan() {
        return sempadanBaratKegunaan;
    }

    public void setSempadanBaratKegunaan(String sempadanBaratKegunaan) {
        this.sempadanBaratKegunaan = sempadanBaratKegunaan;
    }

    public Pengguna getLaporanOleh() {
        return laporanOleh;
    }

    public void setLaporanOleh(Pengguna laporanOleh) {
        this.laporanOleh = laporanOleh;
    }

    public Date getTarikhLaporan() {
        return tarikhLaporan;
    }

    public void setTarikhLaporan(Date tarikhLaporan) {
        this.tarikhLaporan = tarikhLaporan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Integer getLaporanBilPetakTanah() {
        return laporanBilPetakTanah;
    }

    public void setLaporanBilPetakTanah(Integer laporanBilPetakTanah) {
        this.laporanBilPetakTanah = laporanBilPetakTanah;
    }

    public String getLaporanKemudahan13() {
        return laporanKemudahan13;
    }

    public void setLaporanKemudahan13(String laporanKemudahan13) {
        this.laporanKemudahan13 = laporanKemudahan13;
    }

    public String getLaporanKemudahan14() {
        return laporanKemudahan14;
    }

    public void setLaporanKemudahan14(String laporanKemudahan14) {
        this.laporanKemudahan14 = laporanKemudahan14;
    }

    public String getMcNoSijil() {
        return mcNoSijil;
    }

    public void setMcNoSijil(String mcNoSijil) {
        this.mcNoSijil = mcNoSijil;
    }

    public Date getMcTrhSijil() {
        return mcTrhSijil;
    }

    public void setMcTrhSijil(Date mcTrhSijil) {
        this.mcTrhSijil = mcTrhSijil;
    }

    public String getMcNoRuj() {
        return mcNoRuj;
    }

    public void setMcNoRuj(String mcNoRuj) {
        this.mcNoRuj = mcNoRuj;
    }

    public Date getMcTrhRuj() {
        return mcTrhRuj;
    }

    public void setMcTrhRuj(Date mcTrhRuj) {
        this.mcTrhRuj = mcTrhRuj;
    }
    
}

