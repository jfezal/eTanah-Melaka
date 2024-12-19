package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_plot_pelan")
@SequenceGenerator(name = "seq_mohon_plot_pelan", sequenceName = "seq_mohon_plot_pelan")
public class PermohonanPlotPelan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_plot_pelan")
    @Column(name = "id_plot")
    private long idPlot;

    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "kod_milik")
    private KodPemilikan pemilikan;

    @ManyToOne
    @JoinColumn(name = "kod_rizab")
    private KodRizab rizab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @ManyToOne
    @JoinColumn(name = "kod_katg_tanah")
    private KodKategoriTanah kategoriTanah;

    @Column(name = "katg_tanah_lain")
    private String kategoriTanahLain;

    @ManyToOne
    @JoinColumn(name = "kod_guna_tanah")
    private KodKegunaanTanah kegunaanTanah;

    @Column(name = "guna_tanah_lain")
    private String kegunaanTanahLain;

    @Column(name = "luas")
    private BigDecimal luas;

    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;

    @Column(name = "no_dr", nullable = false)
    private int noDari;

    @Column(name = "no_ke", nullable = false)
    private int noKe;

    @Column(name = "no_plot")
    private String noPlot;

    @Column(name = "no_pt")
    private String noPt;

    @Column(name = "no_pu")
    private String noPu;

    @Column(name = "bil_plot", nullable = false)
    private int bilanganPlot;

    @Column(name = "bil_tingkat")
    private Integer bilanganTingkat;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "ungkapan_batal")
    private String ungkapanBatal;

    @ManyToOne
    @JoinColumn(name = "kod_syarat_nyata")
    private KodSyaratNyata kodSyaratNyata;

    @ManyToOne
    @JoinColumn(name = "kod_sekatan")
    private KodSekatanKepentingan kodSekatanKepentingan;

    @Embedded
    private InfoAudit infoAudit;

    @ManyToOne
    @JoinColumn(name = "KOD_HAKMILIK_TETAP")
    private KodHakmilik kodHakmilikTetap;

    @ManyToOne
    @JoinColumn(name = "KOD_HAKMILIK_SEMENTARA")
    private KodHakmilik kodHakmilikSementara;

    @Column(name = "tempoh_pegang")
    private Integer tempohPegangan;

    @Column(name = "KETERANGAN_CUKAI_BARU")
    private String keteranganCukaiBaru;

    @Column(name = "KETERANGAN_KADAR_PREMIUM")
    private String keteranganKadarPremium;

    @Column(name = "KOS_INFRA")
    private String kosInfra;

    @Column(name = "KETERANGAN_KADAR_UKUR")
    private String keteranganKadarUkur;

    @Column(name = "KETERANGAN_KADAR_DAFTAR")
    private String keteranganKadarDaftar;

    @Column(name = "pegangan")
    private Character pegangan;

    @Column(name = "premium_plot")
    private BigDecimal plotPremium;

    @Column(name = "hasil_plot")
    private BigDecimal plotHasil;

    @Column(name = "ingatan")
    private String peringatan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_plot_kpsn")
    private PermohonanPlotKpsn permohonanPlotKpsn;

    @OneToMany(mappedBy = "permohonanPlotPelan")
    private List<PermohonanPlotSekatan> senaraiPermohonanPlotSekatan;
    
    @OneToMany(mappedBy = "permohonanPlotPelan")
    private List<PermohonanPlotSyaratNyata> senaraiPermohonanPlotSyaratNyata;

    public int getBilanganPlot() {
        return bilanganPlot;
    }

    public void setBilanganPlot(int bilanganPlot) {
        this.bilanganPlot = bilanganPlot;
    }

    public long getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(long idPlot) {
        this.idPlot = idPlot;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public KodKategoriTanah getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(KodKategoriTanah kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public String getKategoriTanahLain() {
        return kategoriTanahLain;
    }

    public void setKategoriTanahLain(String kategoriTanahLain) {
        this.kategoriTanahLain = kategoriTanahLain;
    }

    public KodKegunaanTanah getKegunaanTanah() {
        return kegunaanTanah;
    }

    public void setKegunaanTanah(KodKegunaanTanah kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public String getKegunaanTanahLain() {
        return kegunaanTanahLain;
    }

    public void setKegunaanTanahLain(String kegunaanTanahLain) {
        this.kegunaanTanahLain = kegunaanTanahLain;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public int getNoDari() {
        return noDari;
    }

    public void setNoDari(int noDari) {
        this.noDari = noDari;
    }

    public int getNoKe() {
        return noKe;
    }

    public void setNoKe(int noKe) {
        this.noKe = noKe;
    }

    public String getNoPt() {
        return noPt;
    }

    public void setNoPt(String noPt) {
        this.noPt = noPt;
    }

    public String getNoPlot() {
        return noPlot;
    }

    public void setNoPlot(String noPlot) {
        this.noPlot = noPlot;
    }

    public String getNoPu() {
        return noPu;
    }

    public void setNoPu(String noPu) {
        this.noPu = noPu;
    }

    public Integer getBilanganTingkat() {
        return bilanganTingkat;
    }

    public void setBilanganTingkat(Integer bilanganTingkat) {
        this.bilanganTingkat = bilanganTingkat;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public KodSekatanKepentingan getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(KodSekatanKepentingan kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public KodSyaratNyata getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getUngkapanBatal() {
        return ungkapanBatal;
    }

    public void setUngkapanBatal(String ungkapanBatal) {
        this.ungkapanBatal = ungkapanBatal;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodHakmilik getKodHakmilikTetap() {
        return kodHakmilikTetap;
    }

    public void setKodHakmilikTetap(KodHakmilik kodHakmilikTetap) {
        this.kodHakmilikTetap = kodHakmilikTetap;
    }

    public KodHakmilik getKodHakmilikSementara() {
        return kodHakmilikSementara;
    }

    public void setKodHakmilikSementara(KodHakmilik kodHakmilikSementara) {
        this.kodHakmilikSementara = kodHakmilikSementara;
    }

    public Integer getTempohPegangan() {
        return tempohPegangan;
    }

    public void setTempohPegangan(Integer tempohPegangan) {
        this.tempohPegangan = tempohPegangan;
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

    public String getKosInfra() {
        return kosInfra;
    }

    public void setKosInfra(String kosInfra) {
        this.kosInfra = kosInfra;
    }

    public String getKeteranganKadarUkur() {
        return keteranganKadarUkur;
    }

    public void setKeteranganKadarUkur(String keteranganKadarUkur) {
        this.keteranganKadarUkur = keteranganKadarUkur;
    }

    public String getKeteranganKadarDaftar() {
        return keteranganKadarDaftar;
    }

    public void setKeteranganKadarDaftar(String keteranganKadarDaftar) {
        this.keteranganKadarDaftar = keteranganKadarDaftar;
    }

    public Character getPegangan() {
        return pegangan;
    }

    public void setPegangan(Character pegangan) {
        this.pegangan = pegangan;
    }

    public BigDecimal getPlotPremium() {
        return plotPremium;
    }

    public void setPlotPremium(BigDecimal plotPremium) {
        this.plotPremium = plotPremium;
    }

    public BigDecimal getPlotHasil() {
        return plotHasil;
    }

    public void setPlotHasil(BigDecimal plotHasil) {
        this.plotHasil = plotHasil;
    }

    public String getPeringatan() {
        return peringatan;
    }

    public void setPeringatan(String peringatan) {
        this.peringatan = peringatan;
    }

    public PermohonanPlotKpsn getPermohonanPlotKpsn() {
        return permohonanPlotKpsn;
    }

    public void setPermohonanPlotKpsn(PermohonanPlotKpsn permohonanPlotKpsn) {
        this.permohonanPlotKpsn = permohonanPlotKpsn;
    }

    public List<PermohonanPlotSekatan> getSenaraiPermohonanPlotSekatan() {
        return senaraiPermohonanPlotSekatan;
    }

    public void setSenaraiPermohonanPlotSekatan(List<PermohonanPlotSekatan> senaraiPermohonanPlotSekatan) {
        this.senaraiPermohonanPlotSekatan = senaraiPermohonanPlotSekatan;
    }

    public List<PermohonanPlotSyaratNyata> getSenaraiPermohonanPlotSyaratNyata() {
        return senaraiPermohonanPlotSyaratNyata;
    }

    public void setSenaraiPermohonanPlotSyaratNyata(List<PermohonanPlotSyaratNyata> senaraiPermohonanPlotSyaratNyata) {
        this.senaraiPermohonanPlotSyaratNyata = senaraiPermohonanPlotSyaratNyata;
    }
    
    

}
