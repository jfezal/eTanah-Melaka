package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "mohon_kertas_butir")
@SequenceGenerator(name = "seq_mohon_kertas_butir", sequenceName = "seq_mohon_kertas_butir")
public class PermohonanKertasButiran implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_kertas_butir")
    @Column(name = "id_kertas_btr")
    private long idKertasBtr;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_kand", updatable = false)
    private PermohonanKertasKandungan kertasKandungan;

    @Column(name = "pembangunan")
    private String pembangunan;
    @Column(name = "unit")
    private String unit;
    @Column(name = "luas")
    private String luas;
    @Column(name = "harga_seunit")
    private String hargaSeunit;
    @Embedded
    private InfoAudit infoAudit;

    public String getHargaSeunit() {
        return hargaSeunit;
    }

    public void setHargaSeunit(String hargaSeunit) {
        this.hargaSeunit = hargaSeunit;
    }

    public long getIdKertasBtr() {
        return idKertasBtr;
    }

    public void setIdKertasBtr(long idKertasBtr) {
        this.idKertasBtr = idKertasBtr;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getPembangunan() {
        return pembangunan;
    }

    public void setPembangunan(String pembangunan) {
        this.pembangunan = pembangunan;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
