/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "PORTAL_TRANS")
@SequenceGenerator(name = "SEQ_PORTAL_TRANS", sequenceName = "SEQ_PORTAL_TRANS")
public class PortalTransaksi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PORTAL_TRANS")
    @Column(name = "ID_PORTAL_TRANS")
    private Long idTransaksi;
     @Column(name = "ID_MOHON")
    private String mohon;
    @Column(name = "JENIS_TRANS")
    private String jenisTrans;
    @Column(name = "JENIS_AKAUN")
    private String jenisAkaun;
    @Column(name = "ID_TRANS_ASAL")
    private String idTransAsal;
    @Column(name = "ID_KEW_DOK")
    private String idKewDok;
    @Column(name = "NO_RESIT")
    private String noResit;
    @Column(name = "TRH_RESIT")
    private Date trhResit;
    @Column(name = "ID_PGUNA")
    private String idPengguna;
    @Column(name = "NO_TRANS_FPX")
    private String noFpx;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_AKAUN")
    private Akaun akaun;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HAKMILIK")

    private Hakmilik hakmilik;
    @Column(name = "AMAUN")
    private BigDecimal amaun;
    @Column(name = "BIL_PAPARAN")
    private Integer bilPaparan;
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getJenisTrans() {
        return jenisTrans;
    }

    public void setJenisTrans(String jenisTrans) {
        this.jenisTrans = jenisTrans;
    }

    public String getJenisAkaun() {
        return jenisAkaun;
    }

    public void setJenisAkaun(String jenisAkaun) {
        this.jenisAkaun = jenisAkaun;
    }

    public String getIdTransAsal() {
        return idTransAsal;
    }

    public void setIdTransAsal(String idTransAsal) {
        this.idTransAsal = idTransAsal;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public Date getTrhResit() {
        return trhResit;
    }

    public void setTrhResit(Date trhResit) {
        this.trhResit = trhResit;
    }


    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNoFpx() {
        return noFpx;
    }

    public void setNoFpx(String noFpx) {
        this.noFpx = noFpx;
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public Integer getBilPaparan() {
        return bilPaparan;
    }

    public void setBilPaparan(Integer bilPaparan) {
        this.bilPaparan = bilPaparan;
    }

    public String getMohon() {
        return mohon;
    }

    public void setMohon(String mohon) {
        this.mohon = mohon;
    }

 



    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
