package etanah.model;

import etanah.model.ambil.PermohonanPengambilanHakmilik;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * List of Hakmilik involved in the Permohonan.
 */
@Entity
@Table(name = "mohon_hakmilik")
@SequenceGenerator(name = "seq_mohon_hakmilik", sequenceName = "seq_mohon_hakmilik")
public class HakmilikPermohonan implements Serializable {

    public HakmilikPermohonan() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_hakmilik")
    @Column(name = "id_mh")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;
    @Column(name = "luas", nullable = true)
    private BigDecimal luasTerlibat;

    @Column(name = "luas_lama", nullable = true)
    private BigDecimal luasLama;

    @Column(name = "luas_lulus", nullable = true)
    private BigDecimal luasDiluluskan;

    @Column(name = "luas_ukur_halus", nullable = true)
    private BigDecimal luasUkurHalus;

    @Column(name = "luas_pelan_b1", nullable = true)
    private BigDecimal luasPelanB1;

    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;

    @ManyToOne
    @JoinColumn(name = "luas_ukur_halus_uom")
    private KodUOM luasUkurHalusUom;

    @ManyToOne
    @JoinColumn(name = "luas_lulus_uom")
    private KodUOM luasLulusUom;

    @ManyToOne
    @JoinColumn(name = "luas_pelan_b1_uom")
    private KodUOM luasPelanB1Uom;

    @OneToMany(mappedBy = "hakmilikPermohonan")
    private List<HakmilikAsalPermohonan> senaraiHakmilikAsal;
    @OneToMany(mappedBy = "hakmilikPermohonan")
    private List<HakmilikSebelumPermohonan> senaraiHakmilikSebelum;

    /*
     * fikri : for convert txn only
     */
    @ManyToOne
    @JoinColumn(name = "kod_seksyen")
    private KodSeksyen kodSeksyen;

    @ManyToOne
    @JoinColumn(name = "kod_bpm_baru")
    private KodBandarPekanMukim bandarPekanMukimBaru;

    @Column(name = "permit")
    private String permit;

    @ManyToOne
    @JoinColumn(name = "kod_hakmilik")
    private KodHakmilik kodHakmilik;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_lot")
    private KodLot lot;
    @Column(name = "no_lot", length = 15)
    private String noLot;
    @Column(name = "no_pajakan")
    private String noPajakan;
    @ManyToOne
    @JoinColumn(name = "kod_katg_tanah_baru")
    private KodKategoriTanah kategoriTanahBaru;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_syarat_nyata_baru")
    private KodSyaratNyata syaratNyataBaru;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_sekatan_baru")
    private KodSekatanKepentingan sekatanKepentinganBaru;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_cukai_baru")
    private KodKadarCukai kadarCukaiBaru;
    @Column(name = "cukai_baru")
    private BigDecimal cukaiBaru;

    @Column(name = "lokasi")
    private String lokasi;

    @Column(name = "jarak")
    private String jarak;

    @ManyToOne
    @JoinColumn(name = "jarak_kod_uom")
    private KodUOM unitJarak;

    @Column(name = "jarak_dr")
    private String jarakDari;

    @Column(name = "jarak_dr_nama")
    private String jarakDariNama;

    @Column(name = "no_ruj")
    private String nomborRujukan;

    /*
     * hairudin, 04/08/2010
     */
    @ManyToOne
    @JoinColumn(name = "kod_milik")
    private KodPemilikan kodMilik;

    @Column(name = "trh_awal_daftar_geran")
    private Date tarikhAwalDaftarGeran;
    @Column(name = "tempoh_pajakan")
    private Integer tempohPajakan;

    /* 
     * syarat-syarat hakmilik
     */
    @ManyToOne
    @JoinColumn(name = "kod_hakmilik_sementara")
    private KodHakmilik kodHakmilikSementara;
    @ManyToOne
    @JoinColumn(name = "kod_hakmilik_tetap")
    private KodHakmilik kodHakmilikTetap;
    @Column(name = "tempoh_hakmilik")
    private String tempohHakmilik;
    @Column(name = "kadar_cukai_mp")
    private BigDecimal cukaiPerMeterPersegi;
    @Column(name = "kadar_cukai_lot")
    private BigDecimal cukaiPerLot;
    @Column(name = "kadar_premium")
    private BigDecimal kadarPremium;
    @Column(name = "denda_premium")
    private BigDecimal dendaPremium;
    @ManyToOne
    @JoinColumn(name = "kod_katg_tanah")
    private KodKategoriTanah jenisPenggunaanTanah;
    @ManyToOne
    @JoinColumn(name = "kod_syarat_nyata")
    private KodSyaratNyata syaratNyata;
    @ManyToOne
    @JoinColumn(name = "kod_sekatan")
    private KodSekatanKepentingan sekatanKepentingan;
    @ManyToOne
    @JoinColumn(name = "kod_pbt")
    private KodPBT kodPbt;

    @Column(name = "nilaian_jpph")
    private BigDecimal nilaianJpph;

    @Column(name = "catatan")
    private String catatan;
    @Column(name = "flag_diff_lot")
    private String flagLot;

    @ManyToOne
    @JoinColumn(name = "kod_hbgn_hakmilik")
    private KodPerhubunganHakmilik hubunganHakmilik;

    /**
     * Dokumen yg dijana utk permohonan/perserahan *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen1")
    private Dokumen dokumen1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen2")
    private Dokumen dokumen2;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen3")
    private Dokumen dokumen3;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen4")
    private Dokumen dokumen4;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen5")
    private Dokumen dokumen5;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dokumen6")
    private Dokumen dokumen6;

//    @OneToOne (mappedBy = "hakmilik")
//    private PermohonanPembetulanHakmilik pembetulan;
    @OneToMany(mappedBy = "hakmilik")
    private List<PermohonanPembetulanHakmilik> senaraiPembetulan;

    // Laporan tanah
    @OneToMany(mappedBy = "hakmilikPermohonan", fetch = FetchType.LAZY)
    private List<LaporanTanah> senaraiLaporanTanah;

    @Column(name = "kos_infra")
    private BigDecimal kosInfra;

    @Column(name = "tanah_diambil")
    private String tanahDiambil;

    @Column(name = "keterangan_infra")
    private String keteranganInfra;

    @Column(name = "keterangan_cukai_baru")
    private String keteranganCukaiBaru;

    @Column(name = "keterangan_kadar_premium")
    private String keteranganKadarPremium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_guna_tanah")
    private KodKegunaanTanah kodKegunaanTanah;

    @Column(name = "keterangan_kadar_ukur")
    private String keteranganKadarUkur;

    @Column(name = "keterangan_kadar_daftar")
    private String keteranganKadarDaftar;

    @Column(name = "jarak_dr_kediaman")
    private BigDecimal jarakDariKediaman;

    @ManyToOne
    @JoinColumn(name = "jarak_dr_kediaman_uom")
    private KodUOM jarakDariKediamanUom;

    @Column(name = "agensi_upah_ukur")
    private String agensiUpahUkur;

    @Column(name = "status_luas_lulus")
    private String statusLuasDiluluskan;

    @Column(name = "penjenisan")
    private String penjenisan;

    @Column(name = "no_unit_petak")
    private String noUnitPetak;

    @Column(name = "pegangan")
    private String pegangan;

    @ManyToOne
    @JoinColumn(name = "kod_dun")
    private KodDUN kodDUN;

    @ManyToOne
    @JoinColumn(name = "kod_kws_parlimen")
    private KodKawasanParlimen kodKawasanParlimen;

    @Column(name = "tempoh_pengosongan")
    private Integer tempohPengosongan;

    @OneToMany(mappedBy = "hakmilikPermohonan")
    private List<PermohonanPihakWakil> senaraiWakilPermohonanPihak;

    @Column(name = "jenis_hakisan", length = 1)
    private String jenisHakisan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_kpsn", nullable = true)
    private KodKeputusan keputusan;

    @Column(name = "lokaliti")
    private String lokaliti;

    @Column(name = "flag_pbl")
    private String penarikBalikan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @Column(name = "dlm_tanah")
    private BigDecimal kedalamanTanah;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dlm_tanah_kod_uom")
    private KodUOM kedalamanTanahUOM;

    @Column(name = "dlm_tanah_lulus")
    private BigDecimal kedalamanTanahDiluluskan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom_dlm_tanah_lulus")
    private KodUOM kedalamanTanahUOMDiLuluskan;

    @Column(name = "fi_pengeluaran")
    private BigDecimal fiPengeluaran;

    @Column(name = "fi_pegangan")
    private BigDecimal fiPegangan;

    @Column(name = "jumlah_pegangan")
    private BigDecimal jumlahPegangan;

    @Column(name = "bahan_keluar")
    private String bahanKeluar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KOD_UOM_ALTERNATIF")
    private KodUOM kodUnitLuasAlternatif;

    @Column(name = "LUAS_ALTERNATIF")
    private BigDecimal luasAlternatif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom_baru")
    private KodUOM kodUnitLuasBaru;

    @Column(name = "luas_baru")
    private BigDecimal luasBaru;

    @Column(name = "aktif")
    private Character aktif;

    @Column(name = "lanjut_tempoh")
    private String lanjutTempoh;

    @Column(name = "hakmilik_ambil")//A-AKTIF TA-TIDAK AKTIF 
    private String hakmilikAmbil;

    @Column(name = "trh_nilaian_jpph")
    private Date tarikhNilaianJPPH;

    @OneToMany(mappedBy = "hakmilikPermohonan")
    private List<PermohonanPengambilanHakmilik> senaraiHakmilikAmbil;

    @Embedded
    private InfoAudit infoAudit;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public BigDecimal getLuasLama() {
        return luasLama;
    }

    public void setLuasLama(BigDecimal luasLama) {
        this.luasLama = luasLama;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public List<HakmilikAsalPermohonan> getSenaraiHakmilikAsal() {
        return senaraiHakmilikAsal;
    }

    public void setSenaraiHakmilikAsal(List<HakmilikAsalPermohonan> senaraiHakmilikAsal) {
        this.senaraiHakmilikAsal = senaraiHakmilikAsal;
    }

    public List<HakmilikSebelumPermohonan> getSenaraiHakmilikSebelum() {
        return senaraiHakmilikSebelum;
    }

    public void setSenaraiHakmilikSebelum(
            List<HakmilikSebelumPermohonan> senaraiHakmilikSebelum) {
        this.senaraiHakmilikSebelum = senaraiHakmilikSebelum;
    }

    public void setBandarPekanMukimBaru(KodBandarPekanMukim bandarPekanMukimBaru) {
        this.bandarPekanMukimBaru = bandarPekanMukimBaru;
    }

    public KodBandarPekanMukim getBandarPekanMukimBaru() {
        return bandarPekanMukimBaru;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public KodLot getLot() {
        return lot;
    }

    public void setLot(KodLot lot) {
        this.lot = lot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public void setKategoriTanahBaru(KodKategoriTanah kategoriTanahBaru) {
        this.kategoriTanahBaru = kategoriTanahBaru;
    }

    public KodKategoriTanah getKategoriTanahBaru() {
        return kategoriTanahBaru;
    }

    public void setSyaratNyataBaru(KodSyaratNyata syaratNyataBaru) {
        this.syaratNyataBaru = syaratNyataBaru;
    }

    public KodSyaratNyata getSyaratNyataBaru() {
        return syaratNyataBaru;
    }

    public void setSekatanKepentinganBaru(KodSekatanKepentingan sekatanKepentinganBaru) {
        this.sekatanKepentinganBaru = sekatanKepentinganBaru;
    }

    public KodSekatanKepentingan getSekatanKepentinganBaru() {
        return sekatanKepentinganBaru;
    }

    public BigDecimal getNilaianJpph() {
        return nilaianJpph;
    }

    public void setNilaianJpph(BigDecimal nilaianJpph) {
        this.nilaianJpph = nilaianJpph;
    }

    public void setKadarCukaiBaru(KodKadarCukai kadarCukaiBaru) {
        this.kadarCukaiBaru = kadarCukaiBaru;
    }

    public KodKadarCukai getKadarCukaiBaru() {
        return kadarCukaiBaru;
    }

    public void setCukaiBaru(BigDecimal cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public BigDecimal getCukaiBaru() {
        return cukaiBaru;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public KodUOM getUnitJarak() {
        return unitJarak;
    }

    public void setUnitJarak(KodUOM unitJarak) {
        this.unitJarak = unitJarak;
    }

    public String getJarakDari() {
        return jarakDari;
    }

    public void setJarakDari(String jarakDari) {
        this.jarakDari = jarakDari;
    }

    public String getJarakDariNama() {
        return jarakDariNama;
    }

    public void setJarakDariNama(String jarakDariNama) {
        this.jarakDariNama = jarakDariNama;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setHubunganHakmilik(KodPerhubunganHakmilik hubunganHakmilik) {
        this.hubunganHakmilik = hubunganHakmilik;
    }

    public KodPerhubunganHakmilik getHubunganHakmilik() {
        return hubunganHakmilik;
    }

    public void setDokumen1(Dokumen dokumen) {
        this.dokumen1 = dokumen;
    }

    public Dokumen getDokumen1() {
        return dokumen1;
    }

    public void setDokumen2(Dokumen dokumen) {
        this.dokumen2 = dokumen;
    }

    public Dokumen getDokumen2() {
        return dokumen2;
    }

    public void setDokumen3(Dokumen dokumen) {
        this.dokumen3 = dokumen;
    }

    public Dokumen getDokumen3() {
        return dokumen3;
    }

    public Dokumen getDokumen4() {
        return dokumen4;
    }

    public void setDokumen4(Dokumen dokumen4) {
        this.dokumen4 = dokumen4;
    }

    public Dokumen getDokumen5() {
        return dokumen5;
    }

    public void setDokumen5(Dokumen dokumen5) {
        this.dokumen5 = dokumen5;
    }

    public Dokumen getDokumen6() {
        return dokumen6;
    }

    public void setDokumen6(Dokumen dokumen6) {
        this.dokumen6 = dokumen6;
    }

    public List<PermohonanPembetulanHakmilik> getSenaraiPembetulan() {
        return senaraiPembetulan;
    }

    public void setSenaraiPembetulan(List<PermohonanPembetulanHakmilik> senaraiPembetulan) {
        this.senaraiPembetulan = senaraiPembetulan;
    }

    public BigDecimal getKosInfra() {
        return kosInfra;
    }

    public void setKosInfra(BigDecimal kosInfra) {
        this.kosInfra = kosInfra;
    }

    public String getTanahDiambil() {
        return tanahDiambil;
    }

    public void setTanahDiambil(String tanahDiambil) {
        this.tanahDiambil = tanahDiambil;
    }

    public String getKeteranganInfra() {
        return keteranganInfra;
    }

    public void setKeteranganInfra(String keteranganInfra) {
        this.keteranganInfra = keteranganInfra;
    }

    public String getKeteranganCukaiBaru() {
        return keteranganCukaiBaru;
    }

    public void setKeteranganCukaiBaru(String keteranganCukaiBaru) {
        this.keteranganCukaiBaru = keteranganCukaiBaru;
    }

    public String getKeteranganKadarPremium() {
        return keteranganKadarPremium;
    }

    public void setKeteranganKadarPremium(String keteranganKadarPremium) {
        this.keteranganKadarPremium = keteranganKadarPremium;
    }

    public KodKegunaanTanah getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(KodKegunaanTanah kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public String getKeteranganKadarDaftar() {
        return keteranganKadarDaftar;
    }

    public void setKeteranganKadarDaftar(String keteranganKadarDaftar) {
        this.keteranganKadarDaftar = keteranganKadarDaftar;
    }

    public BigDecimal getJarakDariKediaman() {
        return jarakDariKediaman;
    }

    public void setJarakDariKediaman(BigDecimal jarakDariKediaman) {
        this.jarakDariKediaman = jarakDariKediaman;
    }

    public KodUOM getJarakDariKediamanUom() {
        return jarakDariKediamanUom;
    }

    public void setJarakDariKediamanUom(KodUOM jarakDariKediamanUom) {
        this.jarakDariKediamanUom = jarakDariKediamanUom;
    }

    public String getKeteranganKadarUkur() {
        return keteranganKadarUkur;
    }

    public void setKeteranganKadarUkur(String keteranganKadarUkur) {
        this.keteranganKadarUkur = keteranganKadarUkur;
    }

//    public void setPembetulan(PermohonanPembetulanHakmilik pembetulan) {
//		this.pembetulan = pembetulan;
//	}
//
//	public PermohonanPembetulanHakmilik getPembetulan() {
//		return pembetulan;
//	}
    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public List<LaporanTanah> getSenaraiLaporanTanah() {
        return senaraiLaporanTanah;
    }

    public void setSenaraiLaporanTanah(List<LaporanTanah> laporanTanah) {
        this.senaraiLaporanTanah = laporanTanah;
    }

    public void setKodMilik(KodPemilikan kodMilik) {
        this.kodMilik = kodMilik;
    }

    public KodPemilikan getKodMilik() {
        return kodMilik;
    }

    public void setTarikhAwalDaftarGeran(Date tarikhAwalDaftarGeran) {
        this.tarikhAwalDaftarGeran = tarikhAwalDaftarGeran;
    }

    public Date getTarikhAwalDaftarGeran() {
        return tarikhAwalDaftarGeran;
    }

    public void setKodHakmilikSementara(KodHakmilik kodHakmilikSementara) {
        this.kodHakmilikSementara = kodHakmilikSementara;
    }

    public KodHakmilik getKodHakmilikSementara() {
        return kodHakmilikSementara;
    }

    public void setKodHakmilikTetap(KodHakmilik kodHakmilikTetap) {
        this.kodHakmilikTetap = kodHakmilikTetap;
    }

    public KodHakmilik getKodHakmilikTetap() {
        return kodHakmilikTetap;
    }

    public void setCukaiPerMeterPersegi(BigDecimal cukaiPerMeterPersegi) {
        this.cukaiPerMeterPersegi = cukaiPerMeterPersegi;
    }

    public BigDecimal getCukaiPerMeterPersegi() {
        return cukaiPerMeterPersegi;
    }

    public void setCukaiPerLot(BigDecimal cukaiPerLot) {
        this.cukaiPerLot = cukaiPerLot;
    }

    public BigDecimal getCukaiPerLot() {
        return cukaiPerLot;
    }

    public void setKadarPremium(BigDecimal kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public BigDecimal getKadarPremium() {
        return kadarPremium;
    }

    public void setDendaPremium(BigDecimal dendaPremium) {
        this.dendaPremium = dendaPremium;
    }

    public BigDecimal getDendaPremium() {
        return dendaPremium;
    }

    public void setSyaratNyata(KodSyaratNyata syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public KodSyaratNyata getSyaratNyata() {
        return syaratNyata;
    }

    public void setSekatanKepentingan(KodSekatanKepentingan sekatanKepentingan) {
        this.sekatanKepentingan = sekatanKepentingan;
    }

    public KodSekatanKepentingan getSekatanKepentingan() {
        return sekatanKepentingan;
    }

    public void setTempohPajakan(Integer tempohPajakan) {
        this.tempohPajakan = tempohPajakan;
    }

    public Integer getTempohPajakan() {
        return tempohPajakan;
    }

    public void setTempohHakmilik(String tempohHakmilik) {
        this.tempohHakmilik = tempohHakmilik;
    }

    public String getTempohHakmilik() {
        return tempohHakmilik;
    }

    public void setNoPajakan(String noPajakan) {
        this.noPajakan = noPajakan;
    }

    public String getNoPajakan() {
        return noPajakan;
    }

    public void setJenisPenggunaanTanah(KodKategoriTanah jenisPenggunaanTanah) {
        this.jenisPenggunaanTanah = jenisPenggunaanTanah;
    }

    public KodKategoriTanah getJenisPenggunaanTanah() {
        return jenisPenggunaanTanah;
    }

    public KodSeksyen getKodSeksyen() {
        return kodSeksyen;
    }

    public void setKodSeksyen(KodSeksyen kodSeksyen) {
        this.kodSeksyen = kodSeksyen;
    }

    public BigDecimal getLuasPelanB1() {
        return luasPelanB1;
    }

    public void setLuasPelanB1(BigDecimal luasPelanB1) {
        this.luasPelanB1 = luasPelanB1;
    }

    public KodUOM getLuasPelanB1Uom() {
        return luasPelanB1Uom;
    }

    public void setLuasPelanB1Uom(KodUOM luasPelanB1Uom) {
        this.luasPelanB1Uom = luasPelanB1Uom;
    }

    public BigDecimal getLuasDiluluskan() {
        return luasDiluluskan;
    }

    public void setLuasDiluluskan(BigDecimal luasDiluluskan) {
        this.luasDiluluskan = luasDiluluskan;
    }

    public KodUOM getLuasLulusUom() {
        return luasLulusUom;
    }

    public void setLuasLulusUom(KodUOM luasLulusUom) {
        this.luasLulusUom = luasLulusUom;
    }

    public BigDecimal getLuasUkurHalus() {
        return luasUkurHalus;
    }

    public void setLuasUkurHalus(BigDecimal luasUkurHalus) {
        this.luasUkurHalus = luasUkurHalus;
    }

    public KodUOM getLuasUkurHalusUom() {
        return luasUkurHalusUom;
    }

    public void setLuasUkurHalusUom(KodUOM luasUkurHalusUom) {
        this.luasUkurHalusUom = luasUkurHalusUom;
    }

    public String getAgensiUpahUkur() {
        return agensiUpahUkur;
    }

    public void setAgensiUpahUkur(String agensiUpahUkur) {
        this.agensiUpahUkur = agensiUpahUkur;
    }

    public KodDUN getKodDUN() {
        return kodDUN;
    }

    public void setKodDUN(KodDUN kodDUN) {
        this.kodDUN = kodDUN;
    }

    public KodKawasanParlimen getKodKawasanParlimen() {
        return kodKawasanParlimen;
    }

    public void setKodKawasanParlimen(KodKawasanParlimen kodKawasanParlimen) {
        this.kodKawasanParlimen = kodKawasanParlimen;
    }

    public Integer getTempohPengosongan() {
        return tempohPengosongan;
    }

    public void setTempohPengosongan(Integer tempohPengosongan) {
        this.tempohPengosongan = tempohPengosongan;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getStatusLuasDiluluskan() {
        return statusLuasDiluluskan;
    }

    public void setStatusLuasDiluluskan(String statusLuasDiluluskan) {
        this.statusLuasDiluluskan = statusLuasDiluluskan;
    }

    public String getPenjenisan() {
        return penjenisan;
    }

    public void setPenjenisan(String penjenisan) {
        this.penjenisan = penjenisan;
    }

    public String getNoUnitPetak() {
        return noUnitPetak;
    }

    public void setNoUnitPetak(String noUnitPetak) {
        this.noUnitPetak = noUnitPetak;
    }

    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public List<PermohonanPihakWakil> getSenaraiWakilPermohonanPihak() {
        return senaraiWakilPermohonanPihak;
    }

    public void setSenaraiWakilPermohonanPihak(List<PermohonanPihakWakil> senaraiWakilPermohonanPihak) {
        this.senaraiWakilPermohonanPihak = senaraiWakilPermohonanPihak;
    }

    public String getJenisHakisan() {
        return jenisHakisan;
    }

    public void setJenisHakisan(String jenisHakisan) {
        this.jenisHakisan = jenisHakisan;
    }

    public KodPBT getKodPbt() {
        return kodPbt;
    }

    public void setKodPbt(KodPBT kodPbt) {
        this.kodPbt = kodPbt;
    }

    public KodKeputusan getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(KodKeputusan keputusan) {
        this.keputusan = keputusan;
    }

    public String getLokaliti() {
        return lokaliti;
    }

    public void setLokaliti(String lokaliti) {
        this.lokaliti = lokaliti;
    }

    public String getPenarikBalikan() {
        return penarikBalikan;
    }

    public void setPenarikBalikan(String penarikBalikan) {
        this.penarikBalikan = penarikBalikan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public BigDecimal getKedalamanTanah() {
        return kedalamanTanah;
    }

    public void setKedalamanTanah(BigDecimal kedalamanTanah) {
        this.kedalamanTanah = kedalamanTanah;
    }

    public KodUOM getKedalamanTanahUOM() {
        return kedalamanTanahUOM;
    }

    public void setKedalamanTanahUOM(KodUOM kedalamanTanahUOM) {
        this.kedalamanTanahUOM = kedalamanTanahUOM;
    }

    public BigDecimal getKedalamanTanahDiluluskan() {
        return kedalamanTanahDiluluskan;
    }

    public void setKedalamanTanahDiluluskan(BigDecimal kedalamanTanahDiluluskan) {
        this.kedalamanTanahDiluluskan = kedalamanTanahDiluluskan;
    }

    public KodUOM getKedalamanTanahUOMDiLuluskan() {
        return kedalamanTanahUOMDiLuluskan;
    }

    public void setKedalamanTanahUOMDiLuluskan(KodUOM kedalamanTanahUOMDiLuluskan) {
        this.kedalamanTanahUOMDiLuluskan = kedalamanTanahUOMDiLuluskan;
    }

    public BigDecimal getFiPengeluaran() {
        return fiPengeluaran;
    }

    public void setFiPengeluaran(BigDecimal fiPengeluaran) {
        this.fiPengeluaran = fiPengeluaran;
    }

    public BigDecimal getFiPegangan() {
        return fiPegangan;
    }

    public void setFiPegangan(BigDecimal fiPegangan) {
        this.fiPegangan = fiPegangan;
    }

    public BigDecimal getJumlahPegangan() {
        return jumlahPegangan;
    }

    public void setJumlahPegangan(BigDecimal jumlahPegangan) {
        this.jumlahPegangan = jumlahPegangan;
    }

    public String getBahanKeluar() {
        return bahanKeluar;
    }

    public void setBahanKeluar(String bahanKeluar) {
        this.bahanKeluar = bahanKeluar;
    }

    public KodUOM getKodUnitLuasAlternatif() {
        return kodUnitLuasAlternatif;
    }

    public void setKodUnitLuasAlternatif(KodUOM kodUnitLuasAlternatif) {
        this.kodUnitLuasAlternatif = kodUnitLuasAlternatif;
    }

    public BigDecimal getLuasAlternatif() {
        return luasAlternatif;
    }

    public void setLuasAlternatif(BigDecimal luasAlternatif) {
        this.luasAlternatif = luasAlternatif;
    }

    public KodUOM getKodUnitLuasBaru() {
        return kodUnitLuasBaru;
    }

    public void setKodUnitLuasBaru(KodUOM kodUnitLuasBaru) {
        this.kodUnitLuasBaru = kodUnitLuasBaru;
    }

    public BigDecimal getLuasBaru() {
        return luasBaru;
    }

    public void setLuasBaru(BigDecimal luasBaru) {
        this.luasBaru = luasBaru;
    }

    public Character getAktif() {
        return aktif;
    }

    public void setAktif(Character aktif) {
        this.aktif = aktif;
    }

    public String getLanjutTempoh() {
        return lanjutTempoh;
    }

    public void setLanjutTempoh(String lanjutTempoh) {
        this.lanjutTempoh = lanjutTempoh;
    }

    public Date getTarikhNilaianJPPH() {
        return tarikhNilaianJPPH;
    }

    public void setTarikhNilaianJPPH(Date tarikhNilaianJPPH) {
        this.tarikhNilaianJPPH = tarikhNilaianJPPH;
    }

    public List<PermohonanPengambilanHakmilik> getSenaraiHakmilikAmbil() {
        return senaraiHakmilikAmbil;
    }

    public void setSenaraiHakmilikAmbil(List<PermohonanPengambilanHakmilik> senaraiHakmilikAmbil) {
        this.senaraiHakmilikAmbil = senaraiHakmilikAmbil;
    }

    public String getFlagLot() {
        return flagLot;
    }

    public void setFlagLot(String flagLot) {
        this.flagLot = flagLot;
    }

    public String getHakmilikAmbil() {
        return hakmilikAmbil;
    }

    public void setHakmilikAmbil(String hakmilikAmbil) {
        this.hakmilikAmbil = hakmilikAmbil;
    }
    
    

}
