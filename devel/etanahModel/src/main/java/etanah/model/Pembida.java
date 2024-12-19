/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "pembida")
@SequenceGenerator(name = "seq_pembida", sequenceName = "seq_pembida")
public class Pembida implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pembida")
    @Column(name = "id_pembida")
    private Long idPembida;
    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lelong", nullable = false)
    private Lelongan lelong;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pihak", nullable = false)
    private Pihak pihak;
    @Column(name = "amoun_deposit")
    private BigDecimal amounDeposit;
    @Column(name = "no_rujukan")
    private String noRujukan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_sts_pembida")
    private KodStsPembida kodStsPembida;
    @Column(name = "trh_mula")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhMula;
    @Column(name = "trh_akhir")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhAkhir;
    @ManyToOne
    @JoinColumn(name = "id_dokumen")
    private Dokumen dokumen;
    @ManyToOne
    @JoinColumn(name = "id_wakil")
    private WakilPihak wakilPihak;
    @Embedded
    private InfoAudit infoAudit;

    public Pembida() {
    }

    public Pembida(Long idPembida) {
        this.idPembida = idPembida;
    }

    public Long getIdPembida() {
        return idPembida;
    }

    public void setIdPembida(Long idPembida) {
        this.idPembida = idPembida;
    }

    public BigDecimal getAmounDeposit() {
        return amounDeposit;
    }

    public void setAmounDeposit(BigDecimal amounDeposit) {
        this.amounDeposit = amounDeposit;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public KodStsPembida getKodStsPembida() {
        return kodStsPembida;
    }

    public void setKodStsPembida(KodStsPembida kodStsPembida) {
        this.kodStsPembida = kodStsPembida;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Date getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(Date tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public WakilPihak getWakilPihak() {
        return wakilPihak;
    }

    public void setWakilPihak(WakilPihak wakilPihak) {
        this.wakilPihak = wakilPihak;
    }

}
