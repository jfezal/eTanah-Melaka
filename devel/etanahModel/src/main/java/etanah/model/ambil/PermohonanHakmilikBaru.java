/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import java.io.Serializable;
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
@Table(name = "mohon_hakmilik_baru")
@SequenceGenerator(name = "seq_mohon_hakmilik_baru", sequenceName = "seq_mohon_hakmilik_baru")
public class PermohonanHakmilikBaru implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_hakmilik_baru")
    @Column(name = "ID_MHB")
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_MH", nullable = false)
    private HakmilikPermohonan hakmilikPermohonan;
    @Column(name = "NO_PELAN_PA")
    private String noPelanPA;
    @Column(name = "LUAS")
    private String luas;
    @ManyToOne
    @JoinColumn(name = "KOD_UOM")
    private KodUOM kodUom;
    @ManyToOne
    @JoinColumn(name = "KOD_BPM")
    private KodBandarPekanMukim kodBandarPekanMukim;
    @Column(name = "NO_LOT")
    private String noLot;

    @Embedded
    private InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getNoPelanPA() {
        return noPelanPA;
    }

    public void setNoPelanPA(String noPelanPA) {
        this.noPelanPA = noPelanPA;
    }

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }
    
    
    public KodUOM getKodUom() {
        return kodUom;
    }

    public void setKodUom(KodUOM kodUom) {
        this.kodUom = kodUom;
    }

    public KodBandarPekanMukim getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

}
