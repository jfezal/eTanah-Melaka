package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "kew_trans")
@SequenceGenerator(name = "seq_kew_trans", sequenceName = "seq_kew_trans")
public class Transaksi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kew_trans")
    @Column(name = "id_trans")
    private long idTransaksi;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "kod_trans")
    private KodTransaksi kodTransaksi;
    @Column(name = "kod_urusan")
    private String kodUrusan;
    @Column(name = "utk_thn", nullable = false)
    private int untukTahun;
    @Column(name = "thn_kew", nullable = false)
    private int tahunKewangan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_akaun_dt")
    private Akaun akaunDebit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_akaun_kr")
    private Akaun akaunKredit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @Column(name = "kntt")
    private int kuantiti;
    @Column(name = "amaun", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal amaun;
    @Column(name = "perihal")
    private String perihal;
    @ManyToOne
    @JoinColumn(name = "id_kew_dok")
    private DokumenKewangan dokumenKewangan;
    @ManyToOne
    @JoinColumn(name = "kod_status")
    private KodStatusTransaksiKewangan status;
    @ManyToOne
    @JoinColumn(name = "kod_batal")
    private KodBatalDokumenKewangan kodBatal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pos")
    private Transaksi transaksiPos;
    @Column(name = "agensi_pymt")
    private String bayaranAgensi;
    @Column(name = "id_kew_dok_sej")
    private String sejarahDokumen;
    @Embedded
    private InfoAudit infoAudit;

    public void setIdTransaksi(long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public long getIdTransaksi() {
        return idTransaksi;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setUntukTahun(int untukTahun) {
        this.untukTahun = untukTahun;
    }

    public int getUntukTahun() {
        return untukTahun;
    }

    public void setAkaunDebit(Akaun akaun) {
        this.akaunDebit = akaun;
    }

    public Akaun getAkaunDebit() {
        return akaunDebit;
    }

    public void setAkaunKredit(Akaun akaun) {
        this.akaunKredit = akaun;
    }

    public Akaun getAkaunKredit() {
        return akaunKredit;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodStatusTransaksiKewangan getStatus() {
        return status;
    }

    public void setStatus(KodStatusTransaksiKewangan status) {
        this.status = status;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public int getKuantiti() {
        return kuantiti;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setKodBatal(KodBatalDokumenKewangan kodBatal) {
        this.kodBatal = kodBatal;
    }

    public KodBatalDokumenKewangan getKodBatal() {
        return kodBatal;
    }

    public void setTransaksiPos(Transaksi transaksiPos) {
        this.transaksiPos = transaksiPos;
    }

    /**
     * Get the Transaksi that did the posting of this Transaksi.
     *
     * @return
     */
    public Transaksi getTransaksiPos() {
        return transaksiPos;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public void setTahunKewangan(int tahunKewangan) {
        this.tahunKewangan = tahunKewangan;
    }

    public String getBayaranAgensi() {
        return bayaranAgensi;
    }

    public void setBayaranAgensi(String bayaranAgensi) {
        this.bayaranAgensi = bayaranAgensi;
    }

    public int getTahunKewangan() {
        return tahunKewangan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public String getSejarahDokumen() {
        return sejarahDokumen;
    }

    public void setSejarahDokumen(String sejarahDokumen) {
        this.sejarahDokumen = sejarahDokumen;
    }
}
