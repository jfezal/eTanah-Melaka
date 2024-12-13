package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_lapor_ulas")
@SequenceGenerator(name = "seq_mohon_lapor_ulas", sequenceName = "seq_mohon_lapor_ulas")
public class PermohonanLaporanUlasan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_ulas")
    @Column(name = "id_lapor_ulas")
    private long idLaporUlas;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "kod_dokumen")
    private KodDokumen kodDokumen;
    @ManyToOne
    @JoinColumn(name = "kod_peranan")
    private KodPeranan kodPeranan;

    @ManyToOne
    @JoinColumn(name = "id_lapor")
    private LaporanTanah laporanTanah;
    
    @Column (name = "jenis_laporan")
	private String jenisLaporan;



    @Column(name = "jenis_ulasan")
    private String jenisUlasan;

    @Column(name = "ulasan")
    private String ulasan;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdLaporUlas() {
        return idLaporUlas;
    }

    public void setIdLaporUlas(long idLaporUlas) {
        this.idLaporUlas = idLaporUlas;
    }

    public String getJenisUlasan() {
        return jenisUlasan;
    }

    public void setJenisUlasan(String jenisUlasan) {
        this.jenisUlasan = jenisUlasan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public KodPeranan getKodPeranan() {
        return kodPeranan;
    }

    public void setKodPeranan(KodPeranan kodPeranan) {
        this.kodPeranan = kodPeranan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }


    @Embedded
    private InfoAudit infoAudit;


    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }
}
