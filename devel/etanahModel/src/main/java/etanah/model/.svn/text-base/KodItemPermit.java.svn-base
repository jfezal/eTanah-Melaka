/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author md.nurfikri
 */
@Entity
@Table(name = "kod_item_permit")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodItemPermit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "KOD")
    private String kod;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "AKTIF")
    private Character aktif;
    @ManyToOne
    @JoinColumn(name = "katg_item")
    private KodKategoriItemPermit kodKategoriItemPermit;




    @Column(name = "royalti_tanah_kjaan")
    private BigDecimal royaltiTanahKerajaan;

    @Column(name = "royalti_tanah_kjaan_kntt")
    private BigDecimal royaltiTanahKerajaanKuantiti;

    @ManyToOne
    @JoinColumn(name = "royalti_tanah_kjaan_uom")
    private KodUOM royaltiTanahKerajaanUom;

    @Column(name = "royalti_tanah_milik")
    private BigDecimal royaltiTanahMilik;

    @Column(name = "royalti_tanah_milik_kntt")
    private BigDecimal royaltiTanahMilikKuantiti;

    @ManyToOne
    @JoinColumn(name = "royalti_tanah_milik_uom")
    private KodUOM royaltiTanahMilikUom;





    @Embedded
    private InfoAudit infoAudit;

    public KodItemPermit() {
    }

    public KodItemPermit(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Character getAktif() {
        return aktif;
    }

    public void setAktif(Character aktif) {
        this.aktif = aktif;
    }

    public KodKategoriItemPermit getKodKategoriItemPermit() {
        return kodKategoriItemPermit;
    }

    public void setKodKategoriItemPermit(KodKategoriItemPermit kodKategoriItemPermit) {
        this.kodKategoriItemPermit = kodKategoriItemPermit;
    }

    public BigDecimal getRoyaltiTanahKerajaan() {
        return royaltiTanahKerajaan;
    }

    public void setRoyaltiTanahKerajaan(BigDecimal royaltiTanahKerajaan) {
        this.royaltiTanahKerajaan = royaltiTanahKerajaan;
    }

    public KodUOM getRoyaltiTanahKerajaanUom() {
        return royaltiTanahKerajaanUom;
    }

    public void setRoyaltiTanahKerajaanUom(KodUOM royaltiTanahKerajaanUom) {
        this.royaltiTanahKerajaanUom = royaltiTanahKerajaanUom;
    }

    public BigDecimal getRoyaltiTanahMilik() {
        return royaltiTanahMilik;
    }

    public void setRoyaltiTanahMilik(BigDecimal royaltiTanahMilik) {
        this.royaltiTanahMilik = royaltiTanahMilik;
    }

    public KodUOM getRoyaltiTanahMilikUom() {
        return royaltiTanahMilikUom;
    }

    public void setRoyaltiTanahMilikUom(KodUOM royaltiTanahMilikUom) {
        this.royaltiTanahMilikUom = royaltiTanahMilikUom;
    }

    public BigDecimal getRoyaltiTanahKerajaanKuantiti() {
        return royaltiTanahKerajaanKuantiti;
    }

    public void setRoyaltiTanahKerajaanKuantiti(BigDecimal royaltiTanahKerajaanKuantiti) {
        this.royaltiTanahKerajaanKuantiti = royaltiTanahKerajaanKuantiti;
    }

    public BigDecimal getRoyaltiTanahMilikKuantiti() {
        return royaltiTanahMilikKuantiti;
    }

    public void setRoyaltiTanahMilikKuantiti(BigDecimal royaltiTanahMilikKuantiti) {
        this.royaltiTanahMilikKuantiti = royaltiTanahMilikKuantiti;
    }




    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
