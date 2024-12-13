package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "notis_butir")
@SequenceGenerator(name = "seq_notis_butir", sequenceName = "seq_notis_butir")
public class NotisButiran implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notis_butir")
    @Column(name = "id_butir")
    private long idButir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_notis")
    private Notis notis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_kew_trans")
    private KodTransaksi kodTransaksi;

    @ManyToOne
    @JoinColumn(name = "kod_tuntut")
    private KodTuntut kodTuntut;

    @Column(name = "amaun")
    private BigDecimal amaun;

    @Embedded
    private InfoAudit infoAudit;

    public KodTuntut getKodTuntut() {
        return kodTuntut;
    }

    public void setKodTuntut(KodTuntut kodTuntut) {
        this.kodTuntut = kodTuntut;
    }

    

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public long getIdButir() {
        return idButir;
    }

    public void setIdButir(long idButir) {
        this.idButir = idButir;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
