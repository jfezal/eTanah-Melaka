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
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_ANSURAN")
//@SequenceGenerator(name = "seq_mohon_ansuran", sequenceName = "seq_mohon_ansuran")
//@SequenceGenerator(name = "seq_permit_sah_laku", sequenceName = "seq_permit_sah_laku")
//update check

public class PermohonanAnsuran implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name ="ID_MOHON")
    private String idPermohonan;

    @Column(name = "JENIS_PEMOHON")
    private String jenisPemohon;
    @Column(name = "KATEGORI_LULUS")
    private String katgLulus;
    @Column(name = "RESIT_DEPO")
    private String idResitDepo;
    @Column(name = "AMAUN_DEPO")
    private BigDecimal amaunDepo;
    @Column(name = "JADUAL_BYR1")
    private BigDecimal jadualByr1;
    @Column(name = "JADUAL_BYR2")
    private BigDecimal jadualByr2;
    @Column(name = "JADUAL_BYR3")
    private BigDecimal jadualByr3;
    @Column(name = "JADUAL_BYR4")
    private BigDecimal jadualByr4;
    @Column(name = "JADUAL_BYR5")
    private BigDecimal jadualByr5;
    @Column(name = "JADUAL_BYR6")
    private BigDecimal jadualByr6;
    @Column(name = "BAKI")
    private BigDecimal baki;
    @Column(name = "AMT_DENDA")
    private BigDecimal denda;

    @Embedded
    private InfoAudit infoAudit;

    public PermohonanAnsuran() {
    }

    public String getJenisPemohon() {
        return jenisPemohon;
    }

    public void setJenisPemohon(String jenisPemohon) {
        this.jenisPemohon = jenisPemohon;
    }
    public String getKatgLulus() {
        return katgLulus;
    }

    public void setKatgLulus(String katgLulus) {
        this.katgLulus = katgLulus;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdResitDepo() {
        return idResitDepo;
    }

    public void setIdResitDepo(String idResitDepo) {
        this.idResitDepo = idResitDepo;
    }

   

    public BigDecimal getDenda() {
        return denda;
    }

    public void setDenda(BigDecimal denda) {
        this.denda = denda;
    }

    public BigDecimal getAmaunDepo() {
        return amaunDepo;
    }

    public void setAmaunDepo(BigDecimal amaunDepo) {
        this.amaunDepo = amaunDepo;
    }

    public BigDecimal getJadualByr1() {
        return jadualByr1;
    }

    public void setJadualByr1(BigDecimal jadualByr1) {
        this.jadualByr1 = jadualByr1;
    }

    public BigDecimal getJadualByr2() {
        return jadualByr2;
    }

    public void setJadualByr2(BigDecimal jadualByr2) {
        this.jadualByr2 = jadualByr2;
    }

    public BigDecimal getJadualByr3() {
        return jadualByr3;
    }

    public void setJadualByr3(BigDecimal jadualByr3) {
        this.jadualByr3 = jadualByr3;
    }

    public BigDecimal getJadualByr4() {
        return jadualByr4;
    }

    public void setJadualByr4(BigDecimal jadualByr4) {
        this.jadualByr4 = jadualByr4;
    }

    public BigDecimal getJadualByr5() {
        return jadualByr5;
    }

    public void setJadualByr5(BigDecimal jadualByr5) {
        this.jadualByr5 = jadualByr5;
    }

    public BigDecimal getJadualByr6() {
        return jadualByr6;
    }

    public void setJadualByr6(BigDecimal jadualByr6) {
        this.jadualByr6 = jadualByr6;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
