/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "AMBIL_HADIR_BAYAR")
@SequenceGenerator(name = "seq_ambil_hadir_bayar", sequenceName = "seq_ambil_hadir_bayar")
@NamedQueries({
    @NamedQuery(name = "BayaranPenilaian.findAll", query = "SELECT a FROM BayaranPenilaian a")})
public class BayaranPenilaian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ambil_hadir_bayar")
    @Column(name = "ID_BAYAR")
    private Long idBayar;
    @ManyToOne
    @JoinColumn(name = "id_nilai")
    private Penilaian penilaian;
    @Column(name = "amaun", nullable = false)
    private BigDecimal amaun;
    @Column(name = "no_dok")
    private String noDok;
    @ManyToOne
    @JoinColumn(name = "kod_cara_bayar")
    private KodCaraBayaran kodCaraBayaran;
    @Column(name = "trh_dok")
    private Date trhDok;
    @ManyToOne
    @JoinColumn(name = "kod_bank")
    private KodBank kodBank;
    @Column(name = "dok_diambil")
    private String dokDiambil;
    @Column(name = "trh_dok_diambil")
    private Date trhDokDiambil;
    @Embedded
    private InfoAudit infoAudit;

    public BayaranPenilaian() {
    }

    public BayaranPenilaian(Long idBayar) {
        this.idBayar = idBayar;
    }

    public Long getIdBayar() {
        return idBayar;
    }

    public void setIdBayar(Long idBayar) {
        this.idBayar = idBayar;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Penilaian getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getDokDiambil() {
        return dokDiambil;
    }

    public void setDokDiambil(String dokDiambil) {
        this.dokDiambil = dokDiambil;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

    public KodCaraBayaran getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    public Date getTrhDok() {
        return trhDok;
    }

    public void setTrhDok(Date trhDok) {
        this.trhDok = trhDok;
    }

    public Date getTrhDokDiambil() {
        return trhDokDiambil;
    }

    public void setTrhDokDiambil(Date trhDokDiambil) {
        this.trhDokDiambil = trhDokDiambil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBayar != null ? idBayar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BayaranPenilaian)) {
            return false;
        }
        BayaranPenilaian other = (BayaranPenilaian) object;
        if ((this.idBayar == null && other.idBayar != null) || (this.idBayar != null && !this.idBayar.equals(other.idBayar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.ambil.BayaranPenilaian[idBayar=" + idBayar + "]";
    }
}
