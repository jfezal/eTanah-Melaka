package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "mohon_tuntut_kos")
@SequenceGenerator(name = "seq_mohon_tuntut_kos", sequenceName = "seq_mohon_tuntut_kos")
public class PermohonanTuntutanKos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_tuntut_kos")
    @Column(name = "id_kos")
    private long idKos;

    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mp", nullable = true)
    private PermohonanPihak pihak;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mh", nullable = true)
    private HakmilikPermohonan hakmilikPermohonan;

    @ManyToOne
    @JoinColumn(name = "kod_kew_trans")
    private KodTransaksi kodTransaksi;

    @ManyToOne
    @JoinColumn(name = "kod_tuntut")
    private KodTuntut kodTuntut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kntt_uom")
    private KodUOM kuantitiUOM;

    @Column(name = "item", nullable = false)
    private String item;

    @Column(name = "amaun_tuntut", nullable = false)
    private BigDecimal amaunTuntutan;

    @Column(name = "amaun_sbnr")
    private BigDecimal amaunSebenar;
    @Column(name = "amaun_unit")
    private BigDecimal amaunSeunit;
    @Column(name = "kntt")
    private Integer kuantiti;

    @Column(name = "TANAH")
    private Integer tanah;

    @Column(name = "BANGUNAN")
    private Integer bangunan;

    @Column(name = "PECAH_PISAH")
    private Integer pecahPisah;

    @Column(name = "MUDARAT")
    private Integer mudarat;

    @Column(name = "DLL")
    private Integer lainLain;

    @ManyToOne
    @JoinColumn(name = "dinilai")
    private Pengguna penilai;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "NO_LESEN")
    private String noLesen;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdKos() {
        return idKos;
    }

    public void setIdKos(long idKos) {
        this.idKos = idKos;
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

    public void setPihak(PermohonanPihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPihak getPihak() {
        return pihak;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    public KodUOM getKuantitiUOM() {
        return kuantitiUOM;
    }

    public void setKuantitiUOM(KodUOM kuantitiUOM) {
        this.kuantitiUOM = kuantitiUOM;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public BigDecimal getAmaunSeunit() {
        return amaunSeunit;
    }

    public void setAmaunSeunit(BigDecimal amaunSeunit) {
        this.amaunSeunit = amaunSeunit;
    }

    public Integer getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(Integer kuantiti) {
        this.kuantiti = kuantiti;
    }

    public BigDecimal getAmaunSebenar() {
        return amaunSebenar;
    }

    public void setAmaunSebenar(BigDecimal amaunSebenar) {
        this.amaunSebenar = amaunSebenar;
    }

    public Pengguna getPenilai() {
        return penilai;
    }

    public void setPenilai(Pengguna penilai) {
        this.penilai = penilai;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoLesen() {
        return noLesen;
    }

    public void setNoLesen(String noLesen) {
        this.noLesen = noLesen;
    }

    public Integer getTanah() {
        return tanah;
    }

    public void setTanah(Integer tanah) {
        this.tanah = tanah;
    }

    public Integer getBangunan() {
        return bangunan;
    }

    public void setBangunan(Integer bangunan) {
        this.bangunan = bangunan;
    }

    public Integer getPecahPisah() {
        return pecahPisah;
    }

    public void setPecahPisah(Integer pecahPisah) {
        this.pecahPisah = pecahPisah;
    }

    public Integer getMudarat() {
        return mudarat;
    }

    public void setMudarat(Integer mudarat) {
        this.mudarat = mudarat;
    }

    public Integer getLainLain() {
        return lainLain;
    }

    public void setLainLain(Integer lainLain) {
        this.lainLain = lainLain;
    }

}
