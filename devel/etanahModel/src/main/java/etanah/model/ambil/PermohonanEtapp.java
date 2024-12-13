/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "mohon_ambil_etapp")
public class PermohonanEtapp implements Serializable {

    @Id
    @Column(name = "id_mohon")
    private String idPermohonan;
//    
//    @ManyToOne
//    @JoinColumn(name = "id_mohon")
//    private Permohonan permohonan;
    @Column(name = "TRH_MOHON")
    private Date trhMohon;
    @Column(name = "NO_FAIL_JKPTG")
    String noFailJkptg;
    @Column(name = "TARIKH_WARTA_A")
    private Date trhWartaA;

    @Column(name = "NO_WARTA_A")
    String noWartaA;
    @Column(name = "TARIKH_WARTA_C")
    private Date trhWartaC;
    @Column(name = "NO_WARTA_C")
    String noWartaC;
    @Column(name = "TRH_PU")
    private Date trhPu;
    @Column(name = "NO_PU")
    String noPu;
    @Column(name = "TRH_HANTAR_PU")
    private Date trhHantarPu;
    @Column(name = "NO_RUJ")
    String noRujukanPU;
    @Embedded
    private InfoAudit infoAudit;


    public Date getTrhMohon() {
        return trhMohon;
    }

    public void setTrhMohon(Date trhMohon) {
        this.trhMohon = trhMohon;
    }

    public String getNoFailJkptg() {
        return noFailJkptg;
    }

    public void setNoFailJkptg(String noFailJkptg) {
        this.noFailJkptg = noFailJkptg;
    }

    public Date getTrhWartaA() {
        return trhWartaA;
    }

    public void setTrhWartaA(Date trhWartaA) {
        this.trhWartaA = trhWartaA;
    }

    public String getNoWartaA() {
        return noWartaA;
    }

    public void setNoWartaA(String noWartaA) {
        this.noWartaA = noWartaA;
    }

    public Date getTrhWartaC() {
        return trhWartaC;
    }

    public void setTrhWartaC(Date trhWartaC) {
        this.trhWartaC = trhWartaC;
    }

    public String getNoWartaC() {
        return noWartaC;
    }

    public void setNoWartaC(String noWartaC) {
        this.noWartaC = noWartaC;
    }

    public Date getTrhPu() {
        return trhPu;
    }

    public void setTrhPu(Date trhPu) {
        this.trhPu = trhPu;
    }

    public String getNoPu() {
        return noPu;
    }

    public void setNoPu(String noPu) {
        this.noPu = noPu;
    }

    public Date getTrhHantarPu() {
        return trhHantarPu;
    }

    public void setTrhHantarPu(Date trhHantarPu) {
        this.trhHantarPu = trhHantarPu;
    }

    public String getNoRujukanPU() {
        return noRujukanPU;
    }

    public void setNoRujukanPU(String noRujukanPU) {
        this.noRujukanPU = noRujukanPU;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    
}
