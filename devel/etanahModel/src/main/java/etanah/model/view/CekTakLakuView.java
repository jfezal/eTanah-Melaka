/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Immutable
@Table(name = "V_ISPEKS_CEK_TAK_LAKU")
public class CekTakLakuView implements Serializable {

    @Id
    @Column(name = "ID")
    private long id;
    @Column(name = "NO_PENYATA")
    private String noPenyata;
    @Column(name = "TRH_PENYATA")
    private Date tarikhPenyata;
    @Column(name = "PRNHR_NO")
    private String noPerbendaharaan;
    @Column(name = "PRHNR_TRH_RESIT")
    private Date tarikhPerbendaharaan;
    @Column(name = "BANK_CODE")
    private String kodBank;
    @Column(name = "NO_RUJ")
    private String noRujukan;
    @Column(name = "NO_ACC_BANK")
    private String noAkaunBankPembayar;
    @Column(name = "TRH_RUJ")
    private Date tarikhRujukan;
    @Column(name = "AMAUN")
    private BigDecimal amaun;
    @Column(name = "DESCRIPTION")
    private String keterangan;
    @Column(name = "ID_KEW_DOK")
    private String idKewDok;
    @Column(name = "KOD_STS")
    private String kodStatus;
    @Column(name = "TRH_BATAL")
    private Date tarikhBatal;
    @Column(name = "DIBATAL")
    private String diBatal;
    @Column(name = "KOD_SBB_BATAL")
    private String kodSbbBatal;
    @Column(name = "CATATAN")
    private String catatan;
//

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoPenyata() {
        return noPenyata;
    }

    public void setNoPenyata(String noPenyata) {
        this.noPenyata = noPenyata;
    }

    public Date getTarikhPenyata() {
        return tarikhPenyata;
    }

    public void setTarikhPenyata(Date tarikhPenyata) {
        this.tarikhPenyata = tarikhPenyata;
    }

    public String getNoPerbendaharaan() {
        return noPerbendaharaan;
    }

    public void setNoPerbendaharaan(String noPerbendaharaan) {
        this.noPerbendaharaan = noPerbendaharaan;
    }

    public Date getTarikhPerbendaharaan() {
        return tarikhPerbendaharaan;
    }

    public void setTarikhPerbendaharaan(Date tarikhPerbendaharaan) {
        this.tarikhPerbendaharaan = tarikhPerbendaharaan;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNoAkaunBankPembayar() {
        return noAkaunBankPembayar;
    }

    public void setNoAkaunBankPembayar(String noAkaunBankPembayar) {
        this.noAkaunBankPembayar = noAkaunBankPembayar;
    }

    public Date getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(Date tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public String getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(String kodStatus) {
        this.kodStatus = kodStatus;
    }

    public Date getTarikhBatal() {
        return tarikhBatal;
    }

    public void setTarikhBatal(Date tarikhBatal) {
        this.tarikhBatal = tarikhBatal;
    }

    public String getDiBatal() {
        return diBatal;
    }

    public void setDiBatal(String diBatal) {
        this.diBatal = diBatal;
    }
    

    public String getKodSbbBatal() {
        return kodSbbBatal;
    }

    public void setKodSbbBatal(String kodSbbBatal) {
        this.kodSbbBatal = kodSbbBatal;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    
}
