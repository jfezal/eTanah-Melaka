/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author zipzap
 */
@Entity
@Table(name = "mohon_hakmilik_baru")
@SequenceGenerator(name = "seq_mohon_hakmilik_baru", sequenceName = "seq_mohon_hakmilik_baru")
public class HakmilikPermohonanBaru implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_hakmilik_baru")
    @Column(name = "id_mhb")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mh", nullable = false)
    private HakmilikPermohonan hakmilikPermohonan;

    @Embedded
    private InfoAudit infoAudit;

    @Column(name = "no_pelan_pa")
    private String noPelanPA;

    @Column(name = "luas", nullable = true)
    private BigDecimal luas;

    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;

    @ManyToOne
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukimBaru;

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

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoPelanPA() {
        return noPelanPA;
    }

    public void setNoPelanPA(String noPelanPA) {
        this.noPelanPA = noPelanPA;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public KodBandarPekanMukim getBandarPekanMukimBaru() {
        return bandarPekanMukimBaru;
    }

    public void setBandarPekanMukimBaru(KodBandarPekanMukim bandarPekanMukimBaru) {
        this.bandarPekanMukimBaru = bandarPekanMukimBaru;
    }

}
