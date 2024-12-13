package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "bngn_petak_aksr")
@SequenceGenerator(name = "seq_bngn_petak_aksr", sequenceName = "seq_bngn_petak_aksr")
public class BangunanPetakAksesori implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bngn_petak_aksr")
    @Column(name = "id_aksr")
    private long idAksesori;
    @ManyToOne
    @JoinColumn(name = "id_bngn", nullable = false)
    private PermohonanBangunan bangunan;
    @ManyToOne
    @JoinColumn(name = "id_tgkt", nullable = false)
    private BangunanTingkat tingkat;
    @ManyToOne
    @JoinColumn(name = "id_petak", nullable = true)
    private BangunanPetak petak;
    @Column(name = "nama")
    private String nama;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "kod_petak_aksr", nullable = true)
    private KodKegunaanPetakAksesori kegunaan;
    @Column(name = "lokasi")
    private String lokasi;
    @Column(name = "pab_aksr")
    private String pabAksesori;
    
    @Column(name = "petak_sgkt")
    private String petakSangkut;
    @Column(name = "lain")
    private String lain;
    @Embedded
    private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdAksesori() {
        return idAksesori;
    }

    public void setIdAksesori(long idAksesori) {
        this.idAksesori = idAksesori;
    }

    public PermohonanBangunan getBangunan() {
        return bangunan;
    }

    public void setBangunan(PermohonanBangunan bangunan) {
        this.bangunan = bangunan;
    }

    public BangunanTingkat getTingkat() {
        return tingkat;
    }

    public void setTingkat(BangunanTingkat tingkat) {
        this.tingkat = tingkat;
    }

    public BangunanPetak getPetak() {
        return petak;
    }

    public void setPetak(BangunanPetak petak) {
        this.petak = petak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public KodKegunaanPetakAksesori getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(KodKegunaanPetakAksesori kegunaan) {
        this.kegunaan = kegunaan;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getPabAksesori() {
        return pabAksesori;
    }

    public void setPabAksesori(String pabAksesori) {
        this.pabAksesori = pabAksesori;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getPetakSangkut() {
        return petakSangkut;
    }

    public void setPetakSangkut(String petakSangkut) {
        this.petakSangkut = petakSangkut;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }
}
