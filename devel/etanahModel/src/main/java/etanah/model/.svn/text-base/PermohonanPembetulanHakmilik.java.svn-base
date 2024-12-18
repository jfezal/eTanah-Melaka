package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "mohon_hakmilik_betul")
@SequenceGenerator(name = "seq_mohon_hakmilik_betul", sequenceName = "seq_mohon_hakmilik_betul")
@org.hibernate.annotations.Entity(dynamicInsert = true, selectBeforeUpdate = true, dynamicUpdate = true)
public class PermohonanPembetulanHakmilik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_hakmilik_betul")
    @Column(name = "id_betul")
    private long idBetul;
    
    @ManyToOne
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    
    // cawangan tempat perserahan
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @ManyToOne
    @JoinColumn(name = "id_mh", nullable = true)
    private HakmilikPermohonan hakmilik;
    
    @Column(name = "id_hakmilik", nullable = true)
    private String idHakmilik;
    
    @ManyToOne
    @JoinColumn(name = "kod_daerah", nullable = true)
    private KodDaerah daerah;
    
    @ManyToOne
    @JoinColumn(name = "kod_bpm", nullable = true)
    private KodBandarPekanMukim bandarPekanMukim;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_bpm_sms", nullable = true)
    private KodBandarPekanMukim bandarPekanMukimSemasa;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_seksyen", nullable = true)
    private KodSeksyen seksyen;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_seksyen_sms", nullable = true)
    private KodSeksyen seksyenSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_hakmilik", nullable = true)
    private KodHakmilik kodHakmilik;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_hakmilik_sms", nullable = true)
    private KodHakmilik kodHakmilikSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_lot", nullable = true)
    private KodLot kodLot;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_lot_sms", nullable = true)
    private KodLot kodLotSemasa;

    @Column(name = "no_lot", nullable = true)
    private String noLot;

    @Column(name = "no_lot_sms", nullable = true)
    private String noLotSemasa;

    @Column(name = "no_hakmilik", nullable = true)
    private String noHakmilik;

    @Column(name = "no_hakmilik_sms", nullable = true)
    private String noHakmilikSemasa;

    @Column(name = "lokasi", nullable = true)
    private String lokasi;

    @Column(name = "lokasi_sms", nullable = true)
    private String lokasiSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kump", nullable = true)
    private KumpulanHakmilik kumpulan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_katg_tanah", nullable = true)
    private KodKategoriTanah kategoriTanah;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_katg_tanah_sms", nullable = true)
    private KodKategoriTanah kategoriTanahSemasa;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_guna_tanah", nullable = true)
    private KodKegunaanTanah kegunaanTanah;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_guna_tanah_sms", nullable = true)
    private KodKegunaanTanah kegunaanTanahSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_tanah", nullable = true)
    private KodTanah kodTanah;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_tanah_sms", nullable = true)
    private KodTanah kodTanahSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_syarat_nyata", nullable = true)
    private KodSyaratNyata syaratNyata;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_syarat_nyata_sms", nullable = true)
    private KodSyaratNyata syaratNyataSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_sekatan", nullable = true)
    private KodSekatanKepentingan sekatanKepentingan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_sekatan_sms", nullable = true)
    private KodSekatanKepentingan sekatanKepentinganSemasa;

    @Column(name = "trh_daftar", nullable = true)
    private Date tarikhDaftar;

    @Column(name = "trh_daftar_sms", nullable = true)
    private Date tarikhDaftarSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_sts_hakmilik", nullable = true)
    private KodStatusHakmilik statusHakmilik;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_sts_hakmilik_sms", nullable = true)
    private KodStatusHakmilik statusHakmilikSemasa;

    @Column(name = "pegangan", length = 1, nullable = true)
    private Character pegangan;

    @Column(name = "pegangan_sms", length = 1, nullable = true)
    private Character peganganSemasa;

    @Column(name = "tempoh_pegang", nullable = true)
    private Integer tempohPengangan;

    @Column(name = "tempoh_pegang_sms", nullable = true)
    private Integer tempohPenganganSemasa;

    @Column(name = "trh_luput", nullable = true)
    private Date tarikhLuput;

    @Column(name = "trh_luput_sms", nullable = true)
    private Date tarikhLuputSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_rizab", nullable = true)
    private KodRizab rizab;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_rizab_sms", nullable = true)
    private KodRizab rizabSemasa;

    // keluasan
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom", nullable = true)
    private KodUOM uom;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom_sms", nullable = true)
    private KodUOM uomSemasa;

    @Column(name = "luas", nullable = true)
    private BigDecimal luas;

    @Column(name = "luas_sms", nullable = true)
    private BigDecimal luasSemasa;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom_lama")
    private KodUOM uomLama;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_uom_lama_sms")
    private KodUOM uomLamaSemasa;

    @Column(name = "luas_lama")
    private String luasLama;

    @Column(name = "luas_lama_sms")
    private String luasLamaSemasa;

    @Column(name = "no_litho")
    private String noLitho;

    @Column(name = "no_litho_sms")
    private String noLithoSemasa;

    // Pelan Akui
    @Column(name = "no_pelan", nullable = true)
    private String noPelan;

    @Column(name = "no_pelan_sms", nullable = true)
    private String noPelanSemasa;

    @Column(name = "no_pu", nullable = true)
    private String noPU;

    @Column(name = "no_pu_sms", nullable = true)
    private String noPUSemasa;

    // cukai
    @Column(name = "cukai", nullable = true)
    private BigDecimal cukai;

    @Column(name = "cukai_sms", nullable = true)
    private BigDecimal cukaiSemasa;

    // hakmilik asal
    @Column(name = "id_hakmilik_asal")
    private String idHakmilikAsal;

    @Column(name = "id_hakmilik_asal_sms")
    private String idHakmilikAsalSemasa;

    @Column(name = "trh_daftar_asal")
    private Date tarikhDaftarHakmilikAsal;

    @Column(name = "trh_daftar_asal_sms")
    private Date tarikhDaftarHakmilikAsalSemasa;

    // hakmilik sebelum
    @Column(name = "id_hakmilik_sblm")
    private String idHakmilikSebelum;

    @Column(name = "id_hakmilik_sblm_sms")
    private String idHakmilikSebelumSemasa;

    // no fail, jilid dan folio
    @Column(name = "no_fail")
    private String noFail;

    @Column(name = "no_fail_sms")
    private String noFailSemasa;

    @Column (name = "no_srt")
    private String noSurat;
    
    @Column (name = "no_srt_sms")
    private String noSuratSemasa;
    
    @Column(name = "id_au")
    private Long idAtasUrusan;

    @Column(name="upi")
  private String UPI;

  @Column(name="id_hakmilik_induk", length=28)
  private String idHakmilikInduk;

  @Column(name="no_buku_strata")
  private String noBukuDaftarStrata;

  @Column(name="no_versi_indeks")
  private Integer noVersiIndeksStrata;

  @Column(name="no_bngn")
  private String noBangunan;

  @Column(name="no_tgkt")
  private String noTingkat;

  @Column(name="no_petak")
  private String noPetak;

    @Embedded
    private InfoAudit infoAudit;

	public long getIdBetul() {
		return idBetul;
	}

	public void setIdBetul(long idBetul) {
		this.idBetul = idBetul;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public HakmilikPermohonan getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(HakmilikPermohonan hakmilik) {
		this.hakmilik = hakmilik;
	}

	public String getIdHakmilik() {
		return idHakmilik;
	}

	public void setIdHakmilik(String idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public KodDaerah getDaerah() {
		return daerah;
	}

	public void setDaerah(KodDaerah daerah) {
		this.daerah = daerah;
	}

	public KodBandarPekanMukim getBandarPekanMukim() {
		return bandarPekanMukim;
	}

	public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
		this.bandarPekanMukim = bandarPekanMukim;
	}

	public KodBandarPekanMukim getBandarPekanMukimSemasa() {
		return bandarPekanMukimSemasa;
	}

	public void setBandarPekanMukimSemasa(KodBandarPekanMukim bandarPekanMukimSemasa) {
		this.bandarPekanMukimSemasa = bandarPekanMukimSemasa;
	}

	public KodSeksyen getSeksyen() {
		return seksyen;
	}

	public void setSeksyen(KodSeksyen seksyen) {
		this.seksyen = seksyen;
	}

	public KodSeksyen getSeksyenSemasa() {
		return seksyenSemasa;
	}

	public void setSeksyenSemasa(KodSeksyen seksyenSemasa) {
		this.seksyenSemasa = seksyenSemasa;
	}

	public KodHakmilik getKodHakmilik() {
		return kodHakmilik;
	}

	public void setKodHakmilik(KodHakmilik kodHakmilik) {
		this.kodHakmilik = kodHakmilik;
	}

	public KodHakmilik getKodHakmilikSemasa() {
		return kodHakmilikSemasa;
	}

	public void setKodHakmilikSemasa(KodHakmilik kodHakmilikSemasa) {
		this.kodHakmilikSemasa = kodHakmilikSemasa;
	}

	public KodLot getKodLot() {
		return kodLot;
	}

	public void setKodLot(KodLot kodLot) {
		this.kodLot = kodLot;
	}

	public KodLot getKodLotSemasa() {
		return kodLotSemasa;
	}

	public void setKodLotSemasa(KodLot kodLotSemasa) {
		this.kodLotSemasa = kodLotSemasa;
	}

	public String getNoLot() {
		return noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public String getNoLotSemasa() {
		return noLotSemasa;
	}

	public void setNoLotSemasa(String noLotSemasa) {
		this.noLotSemasa = noLotSemasa;
	}

	public String getNoHakmilik() {
		return noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}

	public String getNoHakmilikSemasa() {
		return noHakmilikSemasa;
	}

	public void setNoHakmilikSemasa(String noHakmilikSemasa) {
		this.noHakmilikSemasa = noHakmilikSemasa;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getLokasiSemasa() {
		return lokasiSemasa;
	}

	public void setLokasiSemasa(String lokasiSemasa) {
		this.lokasiSemasa = lokasiSemasa;
	}

	public KumpulanHakmilik getKumpulan() {
		return kumpulan;
	}

	public void setKumpulan(KumpulanHakmilik kumpulan) {
		this.kumpulan = kumpulan;
	}

	public KodKategoriTanah getKategoriTanah() {
		return kategoriTanah;
	}

	public void setKategoriTanah(KodKategoriTanah kategoriTanah) {
		this.kategoriTanah = kategoriTanah;
	}

	public KodKategoriTanah getKategoriTanahSemasa() {
		return kategoriTanahSemasa;
	}

	public void setKategoriTanahSemasa(KodKategoriTanah kategoriTanahSemasa) {
		this.kategoriTanahSemasa = kategoriTanahSemasa;
	}

	public KodKegunaanTanah getKegunaanTanah() {
        return kegunaanTanah;
    }

    public void setKegunaanTanah(KodKegunaanTanah kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public KodKegunaanTanah getKegunaanTanahSemasa() {
        return kegunaanTanahSemasa;
    }

    public void setKegunaanTanahSemasa(KodKegunaanTanah kegunaanTanahSemasa) {
        this.kegunaanTanahSemasa = kegunaanTanahSemasa;
    }

    public KodTanah getKodTanah() {
		return kodTanah;
	}

	public void setKodTanah(KodTanah kodTanah) {
		this.kodTanah = kodTanah;
	}

	public KodTanah getKodTanahSemasa() {
		return kodTanahSemasa;
	}

	public void setKodTanahSemasa(KodTanah kodTanahSemasa) {
		this.kodTanahSemasa = kodTanahSemasa;
	}

	public KodSyaratNyata getSyaratNyata() {
		return syaratNyata;
	}

	public void setSyaratNyata(KodSyaratNyata syaratNyata) {
		this.syaratNyata = syaratNyata;
	}

	public KodSyaratNyata getSyaratNyataSemasa() {
		return syaratNyataSemasa;
	}

	public void setSyaratNyataSemasa(KodSyaratNyata syaratNyataSemasa) {
		this.syaratNyataSemasa = syaratNyataSemasa;
	}

	public KodSekatanKepentingan getSekatanKepentingan() {
		return sekatanKepentingan;
	}

	public void setSekatanKepentingan(KodSekatanKepentingan sekatanKepentingan) {
		this.sekatanKepentingan = sekatanKepentingan;
	}

	public KodSekatanKepentingan getSekatanKepentinganSemasa() {
		return sekatanKepentinganSemasa;
	}

	public void setSekatanKepentinganSemasa(
			KodSekatanKepentingan sekatanKepentinganSemasa) {
		this.sekatanKepentinganSemasa = sekatanKepentinganSemasa;
	}

	public Date getTarikhDaftar() {
		return tarikhDaftar;
	}

	public void setTarikhDaftar(Date tarikhDaftar) {
		this.tarikhDaftar = tarikhDaftar;
	}

	public Date getTarikhDaftarSemasa() {
		return tarikhDaftarSemasa;
	}

	public void setTarikhDaftarSemasa(Date tarikhDaftarSemasa) {
		this.tarikhDaftarSemasa = tarikhDaftarSemasa;
	}

	public KodStatusHakmilik getStatusHakmilik() {
		return statusHakmilik;
	}

	public void setStatusHakmilik(KodStatusHakmilik statusHakmilik) {
		this.statusHakmilik = statusHakmilik;
	}

	public KodStatusHakmilik getStatusHakmilikSemasa() {
		return statusHakmilikSemasa;
	}

	public void setStatusHakmilikSemasa(KodStatusHakmilik statusHakmilikSemasa) {
		this.statusHakmilikSemasa = statusHakmilikSemasa;
	}

	public Character getPegangan() {
		return pegangan;
	}

	public void setPegangan(Character pegangan) {
		this.pegangan = pegangan;
	}

	public Character getPeganganSemasa() {
		return peganganSemasa;
	}

	public void setPeganganSemasa(Character peganganSemasa) {
		this.peganganSemasa = peganganSemasa;
	}

	public Integer getTempohPengangan() {
		return tempohPengangan;
	}

	public void setTempohPengangan(Integer tempohPengangan) {
		this.tempohPengangan = tempohPengangan;
	}

	public Integer getTempohPenganganSemasa() {
		return tempohPenganganSemasa;
	}

	public void setTempohPenganganSemasa(Integer tempohPenganganSemasa) {
		this.tempohPenganganSemasa = tempohPenganganSemasa;
	}

	public Date getTarikhLuput() {
		return tarikhLuput;
	}

	public void setTarikhLuput(Date tarikhLuput) {
		this.tarikhLuput = tarikhLuput;
	}

	public Date getTarikhLuputSemasa() {
		return tarikhLuputSemasa;
	}

	public void setTarikhLuputSemasa(Date tarikhLuputSemasa) {
		this.tarikhLuputSemasa = tarikhLuputSemasa;
	}

	public KodRizab getRizab() {
		return rizab;
	}

	public void setRizab(KodRizab rizab) {
		this.rizab = rizab;
	}

	public KodRizab getRizabSemasa() {
		return rizabSemasa;
	}

	public void setRizabSemasa(KodRizab rizabSemasa) {
		this.rizabSemasa = rizabSemasa;
	}

	public KodUOM getUom() {
		return uom;
	}

	public void setUom(KodUOM uom) {
		this.uom = uom;
	}

	public KodUOM getUomSemasa() {
		return uomSemasa;
	}

	public void setUomSemasa(KodUOM uomSemasa) {
		this.uomSemasa = uomSemasa;
	}

	public BigDecimal getLuas() {
		return luas;
	}

	public void setLuas(BigDecimal luas) {
		this.luas = luas;
	}

	public BigDecimal getLuasSemasa() {
		return luasSemasa;
	}

	public void setLuasSemasa(BigDecimal luasSemasa) {
		this.luasSemasa = luasSemasa;
	}

	public KodUOM getUomLama() {
		return uomLama;
	}

	public void setUomLama(KodUOM uomLama) {
		this.uomLama = uomLama;
	}

	public KodUOM getUomLamaSemasa() {
		return uomLamaSemasa;
	}

	public void setUomLamaSemasa(KodUOM uomLamaSemasa) {
		this.uomLamaSemasa = uomLamaSemasa;
	}

	public String getLuasLama() {
		return luasLama;
	}

	public void setLuasLama(String luasLama) {
		this.luasLama = luasLama;
	}

	public String getLuasLamaSemasa() {
		return luasLamaSemasa;
	}

	public void setLuasLamaSemasa(String luasLamaSemasa) {
		this.luasLamaSemasa = luasLamaSemasa;
	}

	public String getNoLitho() {
		return noLitho;
	}

	public void setNoLitho(String noLitho) {
		this.noLitho = noLitho;
	}

	public String getNoLithoSemasa() {
		return noLithoSemasa;
	}

	public void setNoLithoSemasa(String noLithoSemasa) {
		this.noLithoSemasa = noLithoSemasa;
	}

	public String getNoPelan() {
		return noPelan;
	}

	public void setNoPelan(String noPelan) {
		this.noPelan = noPelan;
	}

	public String getNoPelanSemasa() {
		return noPelanSemasa;
	}

	public void setNoPelanSemasa(String noPelanSemasa) {
		this.noPelanSemasa = noPelanSemasa;
	}

	public String getNoPU() {
		return noPU;
	}

	public void setNoPU(String noPU) {
		this.noPU = noPU;
	}

	public String getNoPUSemasa() {
		return noPUSemasa;
	}

	public void setNoPUSemasa(String noPUSemasa) {
		this.noPUSemasa = noPUSemasa;
	}

	public BigDecimal getCukai() {
		return cukai;
	}

	public void setCukai(BigDecimal cukai) {
		this.cukai = cukai;
	}

	public BigDecimal getCukaiSemasa() {
		return cukaiSemasa;
	}

	public void setCukaiSemasa(BigDecimal cukaiSemasa) {
		this.cukaiSemasa = cukaiSemasa;
	}

	public String getIdHakmilikAsal() {
		return idHakmilikAsal;
	}

	public void setIdHakmilikAsal(String idHakmilikAsal) {
		this.idHakmilikAsal = idHakmilikAsal;
	}

	public String getIdHakmilikAsalSemasa() {
		return idHakmilikAsalSemasa;
	}

	public void setIdHakmilikAsalSemasa(String idHakmilikAsalSemasa) {
		this.idHakmilikAsalSemasa = idHakmilikAsalSemasa;
	}

	public Date getTarikhDaftarHakmilikAsal() {
		return tarikhDaftarHakmilikAsal;
	}

	public void setTarikhDaftarHakmilikAsal(Date tarikhDaftarHakmilikAsal) {
		this.tarikhDaftarHakmilikAsal = tarikhDaftarHakmilikAsal;
	}

	public Date getTarikhDaftarHakmilikAsalSemasa() {
		return tarikhDaftarHakmilikAsalSemasa;
	}

	public void setTarikhDaftarHakmilikAsalSemasa(
			Date tarikhDaftarHakmilikAsalSemasa) {
		this.tarikhDaftarHakmilikAsalSemasa = tarikhDaftarHakmilikAsalSemasa;
	}

	public String getIdHakmilikSebelum() {
		return idHakmilikSebelum;
	}

	public void setIdHakmilikSebelum(String idHakmilikSebelum) {
		this.idHakmilikSebelum = idHakmilikSebelum;
	}

	public String getIdHakmilikSebelumSemasa() {
		return idHakmilikSebelumSemasa;
	}

	public void setIdHakmilikSebelumSemasa(String idHakmilikSebelumSemasa) {
		this.idHakmilikSebelumSemasa = idHakmilikSebelumSemasa;
	}

	public String getNoFail() {
		return noFail;
	}

	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public String getNoFailSemasa() {
		return noFailSemasa;
	}

	public void setNoFailSemasa(String noFailSemasa) {
		this.noFailSemasa = noFailSemasa;
	}

	public Long getIdAtasUrusan() {
		return idAtasUrusan;
	}

	public void setIdAtasUrusan(Long idAtasUrusan) {
		this.idAtasUrusan = idAtasUrusan;
	}

	public String getNoSurat() {
        return noSurat;
    }

    public void setNoSurat(String noSurat) {
        this.noSurat = noSurat;
    }

    public String getNoSuratSemasa() {
        return noSuratSemasa;
    }

    public void setNoSuratSemasa(String noSuratSemasa) {
        this.noSuratSemasa = noSuratSemasa;
    }

    public String getUPI() {
        return UPI;
    }

    public void setUPI(String UPI) {
        this.UPI = UPI;
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public String getNoBangunan() {
        return noBangunan;
    }

    public void setNoBangunan(String noBangunan) {
        this.noBangunan = noBangunan;
    }

    public String getNoBukuDaftarStrata() {
        return noBukuDaftarStrata;
    }

    public void setNoBukuDaftarStrata(String noBukuDaftarStrata) {
        this.noBukuDaftarStrata = noBukuDaftarStrata;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public Integer getNoVersiIndeksStrata() {
        return noVersiIndeksStrata;
    }

    public void setNoVersiIndeksStrata(Integer noVersiIndeksStrata) {
        this.noVersiIndeksStrata = noVersiIndeksStrata;
    }

    public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}     
    
}

