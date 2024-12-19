package etanah.model;

import etanah.model.strata.BadanPengurusan;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "hakmilik")
public class Hakmilik implements Serializable {

    public static final String KOD_AKAUN_CUKAI = "AC";
    @Id
    @Column(name = "id_hakmilik", length = 23)
    private String idHakmilik;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_daerah")
    private KodDaerah daerah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukim;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_seksyen")
    private KodSeksyen seksyen;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_lot")
    private KodLot lot;
    @Column(name = "no_lot", length = 15)
    private String noLot;
    @Column(name = "no_hakmilik", nullable = false)
    private String noHakmilik;
    @Column(name = "upi")
    private String UPI;
    // FOR STRATA TITLES
    @Column(name = "id_hakmilik_induk", length = 28)
    private String idHakmilikInduk;
    @Column (name = "no_buku_strata")
    private String noBukuDaftarStrata;
    @Column (name = "no_versi_indeks")
    private Integer noVersiIndeksStrata;
    @Column(name = "no_bngn")
    private String noBangunan;
    @Column(name = "no_tgkt")
    private String noTingkat;
    @Column(name = "no_petak")
    private String noPetak;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bdn_urus")
    private BadanPengurusan badanPengurusan;
    @Column(name = "lokasi", length = 50)
    private String lokasi;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_kump")
//    private KumpulanHakmilik kumpulan;
    
    @Column (name = "id_kump")
    private Integer kumpulan;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_hakmilik")
    private KodHakmilik kodHakmilik;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_katg_tanah")
    private KodKategoriTanah kategoriTanah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_tanah")
    private KodTanah kodTanah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_syarat_nyata")
    private KodSyaratNyata syaratNyata;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_guna_tanah")
    private KodKegunaanTanah kegunaanTanah;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_sekatan")
    private KodSekatanKepentingan sekatanKepentingan;
    @Column(name = "trh_daftar")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhDaftar;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_sts_hakmilik")
    private KodStatusHakmilik kodStatusHakmilik;
    @Column(name = "trh_luput")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhLuput;
    @Column(name = "pegangan")
    private Character pegangan;
    @Column(name = "tempoh_pegang", nullable = true)
    private Integer tempohPegangan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_rizab")
    private KodRizab rizab;
    @Column(name = "rizab_no_warta")
    private String rizabNoWarta;
    @Column(name = "rizab_kws")
    private String rizabKawasan;
    @Column(name = "rizab_trh_warta")
    private Date rizabTarikhWarta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_pbt")
    private KodPBT pbt;
    @Column(name = "pbt_no_warta")
    private String pbtNoWarta;
    @Column(name = "pbt_kws")
    private String pbtKawasan;
    @Column(name = "pbt_trh_warta")
    private Date pbtTarikhWarta;
    @Column(name = "gsa_no_warta")
    private String gsaNoWarta;
    @Column(name = "gsa_kws")
    private String gsaKawasan;
    @Column(name = "gsa_trh_warta")
    private Date gsaTarikhWarta;
    @Column(name = "lot_bumi")
    private Character lotBumiputera;
    @Column(name = "milik_fed")
    private Character milikPersekutuan;
    @OneToMany(mappedBy = "hakmilik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;
    @Column(name = "luas", columnDefinition = "number(11,4)", nullable = false)
    private BigDecimal luas;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KOD_UOM_ALTERNATIF")
    private KodUOM kodUnitLuasAlternatif;
    
    @Column(name = "LUAS_ALTERNATIF")
    private BigDecimal luasAlternatif;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom_lama")
    private KodUOM kodUnitLuasLama;
    @Column(name = "luas_lama")
    private String luasLama;
    
    @Column(name = "no_litho", length = 50)
    private String noLitho;
    @Column(name = "no_pelan", length = 50)
    private String noPelan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pelan")
    private Dokumen pelan;
    @OneToMany(mappedBy = "hakmilik", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Akaun> senaraiAkaun;
    @Column(name = "no_pu")
    private String noPu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_cukai")
    private KodKadarCukai kodKadarCukai;
    @Column(name = "cukai")
    private BigDecimal cukai;
    @Column(name = "cukai_sbnr")
    private BigDecimal cukaiSebenar;
//    @Column (name = "nama_inst", length = 200)
//    private String namaInstitusi;
    @Column(name = "atas_tanah")
    private String maklumatAtasTanah;
    @OneToMany(mappedBy = "hakmilik")
    private List<HakmilikAsal> senaraiHakmilikAsal;
    @OneToMany(mappedBy = "hakmilik")
    private List<HakmilikSebelum> senaraiHakmilikSebelum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dhde")
    private Dokumen dhde;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dhke")
    private Dokumen dhke;

    @Column(name = "no_versi_dhde")
    private Integer noVersiDhde;
    @Column(name = "no_versi_dhke")
    private Integer noVersiDhke;
    @Column(name = "trh_batal")
    private Date tarikhBatal;
    @Column(name = "no_fail")
    private String noFail;
    @Column(name = "trh_daftar_asal")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhDaftarAsal;

    @Column(name = "unit_syer")
    private BigDecimal unitSyer;    

    @Column(name = "no_skim")
    private String noSkim;
    
    @Column(name = "id_4k")
    private String id4k;

    @Column(name = "jumlah_unit_syer")
    private Integer jumlahUnitSyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "katg_bngn")
    private KodKategoriBangunan kodKategoriBangunan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_guna_bngn")
    private KodKegunaanBangunan kodKegunaanBangunan;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_kelas_tanah")
    private KodKelasTanah kodkelasTanah;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_kat_pemilik")
    private KodKatPemilik kodKatPemilik;

    @Column(name = "kadar")
    private String kadar;
        
    @Column(name = "kos_rendah")
    private String kosRendah;
    
    @Column(name = "kod_sumber")
    private String kodSumber;
    
    @Column(name = "dlm_tanah")
    private BigDecimal kedalamanTanah;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dlm_tanah_kod_uom")
    private KodUOM kedalamanTanahUOM;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MOHON")
    private Permohonan permohonan;
    
    @Column (name = "menara")
    private String menara;
    
    @Column (name = "MEZANIN")
    private String mezanin;
    
    @Column(name = "version")
    private Integer version;
    
@OneToOne(mappedBy = "hakmilik", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EndorsCukaiB4K endorsanCukai;
    @Embedded
    private InfoAudit infoAudit;
    
    private InfoAuditBaru infoAuditBaru;

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public KodSeksyen getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(KodSeksyen seksyen) {
        this.seksyen = seksyen;
    }

    public void setLot(KodLot lot) {
        this.lot = lot;
    }

    public KodLot getLot() {
        return lot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setUPI(String uPI) {
        UPI = uPI;
    }

    public String getUPI() {
        return UPI;
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public void setNoBukuDaftarStrata(String noBukuDaftarStrata) {
		this.noBukuDaftarStrata = noBukuDaftarStrata;
	}

	public String getNoBukuDaftarStrata() {
		return noBukuDaftarStrata;
	}

	public void setNoVersiIndeksStrata(Integer noVersiIndeksStrata) {
		this.noVersiIndeksStrata = noVersiIndeksStrata;
	}

	public Integer getNoVersiIndeksStrata() {
		return noVersiIndeksStrata;
	}

	public String getNoBangunan() {
        return noBangunan;
    }

    public void setNoBangunan(String noBangunan) {
        this.noBangunan = noBangunan;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    //    public void setKumpulan(KumpulanHakmilik kumpulan) {
    //        this.kumpulan = kumpulan;
    //    }
    //
    //    public KumpulanHakmilik getKumpulan() {
    //        return kumpulan;
    //    }
    public Integer getKumpulan() {
        return kumpulan;
    }

    public void setKumpulan(Integer kumpulan) {
        this.kumpulan = kumpulan;
    }
    
    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public KodKategoriTanah getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(KodKategoriTanah kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public void setKodTanah(KodTanah kodTanah) {
        this.kodTanah = kodTanah;
    }

    public KodTanah getKodTanah() {
        return kodTanah;
    }

    public void setSyaratNyata(KodSyaratNyata syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public KodSyaratNyata getSyaratNyata() {
        return syaratNyata;
    }

    public void setKegunaanTanah(KodKegunaanTanah kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public KodKegunaanTanah getKegunaanTanah() {
        return kegunaanTanah;
    }

    public void setSekatanKepentingan(KodSekatanKepentingan sekatanKepentingan) {
        this.sekatanKepentingan = sekatanKepentingan;
    }

    public KodSekatanKepentingan getSekatanKepentingan() {
        return sekatanKepentingan;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public KodStatusHakmilik getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    public void setKodStatusHakmilik(KodStatusHakmilik kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    public Date getTarikhLuput() {
        return tarikhLuput;
    }

    public void setTarikhLuput(Date tarikhLuput) {
        this.tarikhLuput = tarikhLuput;
    }

    public void setPegangan(Character pegangan) {
        this.pegangan = pegangan;
    }

    public Character getPegangan() {
        return pegangan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiPihakBerkepentingan() {
        return senaraiPihakBerkepentingan;
    }

    public void setTempohPegangan(Integer tempohPegangan) {
        this.tempohPegangan = tempohPegangan;
    }

    public Integer getTempohPegangan() {
        return tempohPegangan;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public String getRizabNoWarta() {
        return rizabNoWarta;
    }

    public void setRizabNoWarta(String rizabNoWarta) {
        this.rizabNoWarta = rizabNoWarta;
    }

    public String getRizabKawasan() {
        return rizabKawasan;
    }

    public void setRizabKawasan(String rizabKawasan) {
        this.rizabKawasan = rizabKawasan;
    }

    public Date getRizabTarikhWarta() {
        return rizabTarikhWarta;
    }

    public void setRizabTarikhWarta(Date rizabTarikhWarta) {
        this.rizabTarikhWarta = rizabTarikhWarta;
    }

    public void setPbt(KodPBT pbt) {
        this.pbt = pbt;
    }

    public KodPBT getPbt() {
        return pbt;
    }

    public String getPbtNoWarta() {
        return pbtNoWarta;
    }

    public void setPbtNoWarta(String pbtNoWarta) {
        this.pbtNoWarta = pbtNoWarta;
    }

    public String getPbtKawasan() {
        return pbtKawasan;
    }

    public void setPbtKawasan(String pbtKawasan) {
        this.pbtKawasan = pbtKawasan;
    }

    public Date getPbtTarikhWarta() {
        return pbtTarikhWarta;
    }

    public void setPbtTarikhWarta(Date pbtTarikhWarta) {
        this.pbtTarikhWarta = pbtTarikhWarta;
    }

    public String getGsaNoWarta() {
        return gsaNoWarta;
    }

    public void setGsaNoWarta(String gsaNoWarta) {
        this.gsaNoWarta = gsaNoWarta;
    }

    public String getGsaKawasan() {
        return gsaKawasan;
    }

    public void setGsaKawasan(String gsaKawasan) {
        this.gsaKawasan = gsaKawasan;
    }

    public Date getGsaTarikhWarta() {
        return gsaTarikhWarta;
    }

    public void setGsaTarikhWarta(Date gsaTarikhWarta) {
        this.gsaTarikhWarta = gsaTarikhWarta;
    }

    public void setLotBumiputera(Character lotBumiputera) {
        this.lotBumiputera = lotBumiputera;
    }

    public Character getLotBumiputera() {
        return lotBumiputera;
    }

    public void setMilikPersekutuan(Character milikPersekutuan) {
        this.milikPersekutuan = milikPersekutuan;
    }

    public Character getMilikPersekutuan() {
        return milikPersekutuan;
    }

    public void setSenaraiPihakBerkepentingan(
            List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan) {
        this.senaraiPihakBerkepentingan = senaraiPihakBerkepentingan;
    }

//    public void setNamaInstitusi(String namaInstitusi) {
//		this.namaInstitusi = namaInstitusi;
//	}
//
//	public String getNamaInstitusi() {
//		return namaInstitusi;
//	}
    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoPelan() {
        return noPelan;
    }

    public void setNoPelan(String noPelan) {
        this.noPelan = noPelan;
    }

    public String getNoPu() {
        return noPu;
    }

    public void setNoPu(String noPu) {
        this.noPu = noPu;
    }

    public void setKodKadarCukai(KodKadarCukai kodKadarCukai) {
        this.kodKadarCukai = kodKadarCukai;
    }

    public KodKadarCukai getKodKadarCukai() {
        return kodKadarCukai;
    }

    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
    }

    public BigDecimal getCukaiSebenar() {
        return cukaiSebenar;
    }

    public void setCukaiSebenar(BigDecimal cukaiSebenar) {
        this.cukaiSebenar = cukaiSebenar;
    }

    public List<Akaun> getSenaraiAkaun() {
        return senaraiAkaun;
    }

    public void setAkaun(List<Akaun> senaraiAkaun) {
        this.senaraiAkaun = senaraiAkaun;
    }

    /**
     * Helper transient property
     * @return
     */
    public Akaun getAkaunCukai() {
        for (Akaun a : senaraiAkaun) {
            if (KOD_AKAUN_CUKAI.equals(a.getKodAkaun().getKod())) {
                return a;
            }
        }
        return null; // not found
    }

    public String getMaklumatAtasTanah() {
        return maklumatAtasTanah;
    }

    public void setMaklumatAtasTanah(String maklumatAtasTanah) {
        this.maklumatAtasTanah = maklumatAtasTanah;
    }

    public List<HakmilikAsal> getSenaraiHakmilikAsal() {
        return senaraiHakmilikAsal;
    }

    public void setSenaraiHakmilikAsal(List<HakmilikAsal> senaraiHakmilikAsal) {
        this.senaraiHakmilikAsal = senaraiHakmilikAsal;
    }

    public List<HakmilikSebelum> getSenaraiHakmilikSebelum() {
        return senaraiHakmilikSebelum;
    }

    public void setSenaraiHakmilikSebelum(
            List<HakmilikSebelum> senaraiHakmilikSebelum) {
        this.senaraiHakmilikSebelum = senaraiHakmilikSebelum;
    }

    public void setDhde(Dokumen dhde) {
        this.dhde = dhde;
    }

    public Dokumen getDhde() {
        return dhde;
    }

    public Dokumen getDhke() {
        return dhke;
    }

    public void setDhke(Dokumen dhke) {
        this.dhke = dhke;
    }

    public void setNoVersiDhde(Integer noVersiDhde) {
        this.noVersiDhde = noVersiDhde;
    }

    public Integer getNoVersiDhde() {
        return noVersiDhde;
    }

    public void setNoVersiDhke(Integer noVersiDhke) {
        this.noVersiDhke = noVersiDhke;
    }

    public Integer getNoVersiDhke() {
        return noVersiDhke;
    }

    public Date getTarikhBatal() {
        return tarikhBatal;
    }

    public void setTarikhBatal(Date tarikh) {
        tarikhBatal = tarikh;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getNoFail() {
        return noFail;
    }

    public Date getTarikhDaftarAsal() {
        return tarikhDaftarAsal;
    }

    public void setTarikhDaftarAsal(Date tarikhDaftarAsal) {
        this.tarikhDaftarAsal = tarikhDaftarAsal;
    }


    public BigDecimal getUnitSyer() {
        return unitSyer;
    }

    public void setUnitSyer(BigDecimal unitSyer) {
        this.unitSyer = unitSyer;
    }

    public String getNoSkim() {
        return noSkim;
    }

    public void setNoSkim(String noSkim) {
        this.noSkim = noSkim;
    }

    public Integer getJumlahUnitSyer() {
        return jumlahUnitSyer;
    }

    public void setJumlahUnitSyer(Integer jumlahUnitSyer) {
        this.jumlahUnitSyer = jumlahUnitSyer;
    }

    public KodKategoriBangunan getKodKategoriBangunan() {
        return kodKategoriBangunan;
    }

    public void setKodKategoriBangunan(KodKategoriBangunan kodKategoriBangunan) {
        this.kodKategoriBangunan = kodKategoriBangunan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public BadanPengurusan getBadanPengurusan() {
        return badanPengurusan;
    }

    public void setBadanPengurusan(BadanPengurusan badanPengurusan) {
        this.badanPengurusan = badanPengurusan;
    }

    public Dokumen getPelan() {
        return pelan;
    }

    public void setPelan(Dokumen pelan) {
        this.pelan = pelan;
    }

    public KodKegunaanBangunan getKodKegunaanBangunan() {
        return kodKegunaanBangunan;
    }

    public void setKodKegunaanBangunan(KodKegunaanBangunan kodKegunaanBangunan) {
        this.kodKegunaanBangunan = kodKegunaanBangunan;
    }

    public String getKodSumber() {
        return kodSumber;
    }

    public void setKodSumber(String kodSumber) {
        this.kodSumber = kodSumber;
    }

    public KodUOM getKodUnitLuasLama() {
        return kodUnitLuasLama;
    }

    public void setKodUnitLuasLama(KodUOM kodUnitLuasLama) {
        this.kodUnitLuasLama = kodUnitLuasLama;
    }

    public String getLuasLama() {
        return luasLama;
    }

    public void setLuasLama(String luasLama) {
        this.luasLama = luasLama;
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

     public String getMenara() {
          return menara;
     }

     public void setMenara(String menara) {
          this.menara = menara;
     }

    public String getMezanin() {
        return mezanin;
    }

    public void setMezanin(String mezanin) {
        this.mezanin = mezanin;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public InfoAuditBaru getInfoAuditBaru() {
        return infoAuditBaru;
    }

    public void setInfoAuditBaru(InfoAuditBaru infoAuditBaru) {
        this.infoAuditBaru = infoAuditBaru;
    }

    public EndorsCukaiB4K getEndorsanCukai() {
        return endorsanCukai;
    }

    public void setEndorsanCukai(EndorsCukaiB4K endorsanCukai) {
        this.endorsanCukai = endorsanCukai;
    }

    public String getId4k() {
        return id4k;
    }

    public void setId4k(String id4k) {
        this.id4k = id4k;
    }

    public KodKelasTanah getKodkelasTanah() {
        return kodkelasTanah;
    }

    public void setKodkelasTanah(KodKelasTanah kodkelasTanah) {
        this.kodkelasTanah = kodkelasTanah;
    }

    public String getKadar() {
        return kadar;
    }

    public void setKadar(String kadar) {
        this.kadar = kadar;
    }

    public String getKosRendah() {
        return kosRendah;
    }

    public void setKosRendah(String kosRendah) {
        this.kosRendah = kosRendah;
    }

    public KodKatPemilik getKodKatPemilik() {
        return kodKatPemilik;
    }

    public void setKodKatPemilik(KodKatPemilik kodKatPemilik) {
        this.kodKatPemilik = kodKatPemilik;
    }
    
}

