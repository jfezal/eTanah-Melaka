package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "lapor_tanah_spdn")
@SequenceGenerator(name = "seq_lapor_tanah_spdn", sequenceName = "seq_lapor_tanah_spdn")
public class LaporanTanahSempadan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lapor_tanah_spdn")
    @Column(name = "id_lapor_tanah_spdn")
    private long idLaporTanahSpdn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lapor")
    private LaporanTanah laporanTanah;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;
    @Column(name = "jenis_spdn")
    private String jenisSempadan;
    @Column(name = "milik_kjaan")
    private String milikKerajaan;
    @Column(name = "guna")
    private String guna;
    @Column(name = "keadaan_tanah")
    private String keadaanTanah;
    @Column(name = "catatan")
    private String catatan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_lot")
    private KodLot kodLot;
    @Column(name = "no_lot")
    private String noLot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_katg_tanah")
    private KodKategoriTanah kodKategoriTanah;
    @Column(name = "jarak")
    private BigDecimal jarak;
    @ManyToOne
    @JoinColumn(name = "jarak_uom")
    private KodUOM jarakUom;
    @Embedded
    private InfoAudit infoAudit;

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getGuna() {
        return guna;
    }

    public void setGuna(String guna) {
        this.guna = guna;
    }

    public KodKategoriTanah getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(KodKategoriTanah kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public long getIdLaporTanahSpdn() {
        return idLaporTanahSpdn;
    }

    public void setIdLaporTanahSpdn(long idLaporTanahSpdn) {
        this.idLaporTanahSpdn = idLaporTanahSpdn;
    }

    public String getJenisSempadan() {
        return jenisSempadan;
    }

    public void setJenisSempadan(String jenisSempadan) {
        this.jenisSempadan = jenisSempadan;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public String getMilikKerajaan() {
        return milikKerajaan;
    }

    public void setMilikKerajaan(String milikKerajaan) {
        this.milikKerajaan = milikKerajaan;
    }

    public BigDecimal getJarak() {
        return jarak;
    }

    public void setJarak(BigDecimal jarak) {
        this.jarak = jarak;
    }

    public KodUOM getJarakUom() {
        return jarakUom;
    }

    public void setJarakUom(KodUOM jarakUom) {
        this.jarakUom = jarakUom;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
}
