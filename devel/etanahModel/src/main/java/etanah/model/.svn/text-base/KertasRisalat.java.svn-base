package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "kertas_risalat")
//@SequenceGenerator(name = "seq_mohon_lapor_tanah", sequenceName = "seq_mohon_lapor_tanah")
public class KertasRisalat implements Serializable {

    @Id
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id_kertas")
//    private PermohonanKertas kertas;
    @Column(name = "id_kertas")
    private long idKertas;
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @Column(name = "tajuk")
    private String tajuk;
    @Column(name = "luas")
    private String luas;
    @Column(name = "lokasi", length = 50)
    private String lokasi;
    @Column(name = "keadaan_tanah", length = 50)
    private String keadaanTanah;
    @Column(name = "no_ruj_ptg")
    private String noRujukan;
    @Column(name = "ulasan_adun")
    private String ulasanAdun;
    @Column(name = "ulasan_teknikal", length = 50)
    private String namaSempadanLaut;
    @Column(name = "perakuan_ptd")
    private String perakuanPTD;
    @Column(name = "perakuan_ptg")
    private String perakuanPTG;
    @Column(name = "keputusan")
    private String keputusan;
    @Column(name = "item")
    private String item;
     @ManyToOne
    @JoinColumn(name = "agensi")
    private KodAgensi agensi;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(long idKertas) {
        this.idKertas = idKertas;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getUlasanAdun() {
        return ulasanAdun;
    }

    public void setUlasanAdun(String ulasanAdun) {
        this.ulasanAdun = ulasanAdun;
    }

    public String getNamaSempadanLaut() {
        return namaSempadanLaut;
    }

    public void setNamaSempadanLaut(String namaSempadanLaut) {
        this.namaSempadanLaut = namaSempadanLaut;
    }

    public String getPerakuanPTD() {
        return perakuanPTD;
    }

    public void setPerakuanPTD(String perakuanPTD) {
        this.perakuanPTD = perakuanPTD;
    }

    public String getPerakuanPTG() {
        return perakuanPTG;
    }

    public void setPerakuanPTG(String perakuanPTG) {
        this.perakuanPTG = perakuanPTG;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public KodAgensi getAgensi() {
        return agensi;
    }

    public void setAgensi(KodAgensi agensi) {
        this.agensi = agensi;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }
    
}
