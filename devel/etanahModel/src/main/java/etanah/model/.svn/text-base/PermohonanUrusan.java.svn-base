package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "mohon_urusan")
@SequenceGenerator(name = "seq_mohon_urusan", sequenceName = "seq_mohon_urusan")
public class PermohonanUrusan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_urusan")
    @Column(name = "id_urusan")
    private long idUrusan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @Column(name = "janji_no_ruj", length = 20)
    private String perjanjianNoRujukan;

    @Column(name = "janji_amaun", precision = 12, scale = 2, columnDefinition = "number(12,2)")
    private BigDecimal perjanjianAmaun;

    @Column(name = "janji_duti_setem", precision = 12, scale = 2, columnDefinition = "number(12,2)")
    private BigDecimal perjanjianDutiSetem;

    @Column(name = "janji_trh_mula")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date perjanjianTarikhMula;

    @Column(name = "janji_tempoh_thn")
    private Integer perjanjianTempohTahun;

    @Column(name = "janji_tempoh_bln")
    private Integer perjanjianTempohBulan;

    @Column(name = "janji_tempoh_hari")
    private Integer perjanjianTempohHari;

    @Column(name = "janji_trh_tamat")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date perjanjianTarikhTamat;

    @Column(name = "trh_saksi")
    private Date tarikhSaksi;

    @Column(name = "saksi_nama")
    private String saksiNama;

    @Column(name = "sbb")
    private String sebab;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "perihal")
    private String perihal;

    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;
    
    @ManyToOne
    @JoinColumn(name = "kod_pmilik")
    private KodJenisPindahmilik pindahmilik;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdUrusan() {
        return idUrusan;
    }

    public void setIdUrusan(long idUrusan) {
        this.idUrusan = idUrusan;
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

    public String getPerjanjianNoRujukan() {
        return perjanjianNoRujukan;
    }

    public void setPerjanjianNoRujukan(String perjanjianNoRujukan) {
        this.perjanjianNoRujukan = perjanjianNoRujukan;
    }

    public BigDecimal getPerjanjianAmaun() {
        return perjanjianAmaun;
    }

    public void setPerjanjianAmaun(BigDecimal perjanjianAmaun) {
        this.perjanjianAmaun = perjanjianAmaun;
    }

    public BigDecimal getPerjanjianDutiSetem() {
        return perjanjianDutiSetem;
    }

    public void setPerjanjianDutiSetem(BigDecimal perjanjianDutiSetem) {
        this.perjanjianDutiSetem = perjanjianDutiSetem;
    }

    public Date getPerjanjianTarikhMula() {
        return perjanjianTarikhMula;
    }

    public void setPerjanjianTarikhMula(Date perjanjianTarikhMula) {
        this.perjanjianTarikhMula = perjanjianTarikhMula;
    }

    public Integer getPerjanjianTempohTahun() {
        return perjanjianTempohTahun;
    }

    public void setPerjanjianTempohTahun(int perjanjianTempohTahun) {
        this.perjanjianTempohTahun = perjanjianTempohTahun;
    }

    public Integer getPerjanjianTempohBulan() {
        return perjanjianTempohBulan;
    }

    public void setPerjanjianTempohBulan(int perjanjianTempohBulan) {
        this.perjanjianTempohBulan = perjanjianTempohBulan;
    }

    public Integer getPerjanjianTempohHari() {
        return perjanjianTempohHari;
    }

    public void setPerjanjianTempohHari(int perjanjianTempohHari) {
        this.perjanjianTempohHari = perjanjianTempohHari;
    }

    public Date getPerjanjianTarikhTamat() {
        return perjanjianTarikhTamat;
    }

    public void setPerjanjianTarikhTamat(Date perjanjianTarikhTamat) {
        this.perjanjianTarikhTamat = perjanjianTarikhTamat;
    }

    public void setTarikhSaksi(Date tarikhSaksi) {
        this.tarikhSaksi = tarikhSaksi;
    }

    public Date getTarikhSaksi() {
        return tarikhSaksi;
    }

    public String getSaksiNama() {
        return saksiNama;
    }

    public void setSaksiNama(String saksiNama) {
        this.saksiNama = saksiNama;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }
    
    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setPindahmilik(KodJenisPindahmilik pindahmilik) {
        this.pindahmilik = pindahmilik;
    }
    
    public KodJenisPindahmilik getPindahmilik() {
        return pindahmilik;
    }
    
    
}
