/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name = "NO_PT")
@SequenceGenerator(name = "seq_no_pt", sequenceName = "seq_no_pt")
@NamedQueries({
    @NamedQuery(name = "NoPt.findAll", query = "SELECT n FROM NoPt n")})
public class NoPt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_no_pt")
    @Column(name = "ID_PT")
    private Long idPt;
    @Column(name = "NO_PT")
    private Long noPt;
    @Column(name = "LUAS_DILULUS")
    private BigDecimal luasDilulus;

    @ManyToOne
    @JoinColumn(name = "kod_negeri")
    private KodNegeri kodNegeri;

    @ManyToOne
    @JoinColumn(name = "kod_daerah")
    private KodDaerah kodDaerah;

    @ManyToOne
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim kodBpm;


    @ManyToOne
    @JoinColumn(name = "LUAS_DILULUS_UOM")
    private KodUOM kodUOM;

    @ManyToOne
    @JoinColumn(name = "id_plot")
    private PermohonanPlotPelan permohonanPlotPelan;

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;
    
    @Column(name = "NO_PT_SEMENTARA")
    private Long noPtSementara;
    
    @Column(name = "no_plot")
    private String noPlot;
    
    @ManyToOne
    @JoinColumn(name = "KOD_SYARAT_NYATA")
    private KodSyaratNyata kodSyaratNyata;
    
    @ManyToOne
    @JoinColumn(name = "KOD_SEKATAN")
    private KodSekatanKepentingan kodSekatanKepentingan;
    
    @Column(name = "LOT_BUMI")
    private String bumi;

    @Embedded
    private InfoAudit infoAudit;

    public NoPt() {
    }

    public NoPt(Long idPt) {
        this.idPt = idPt;
    }


    public Long getIdPt() {
        return idPt;
    }

    public void setIdPt(Long idPt) {
        this.idPt = idPt;
    }

    public Long getNoPt() {
        return noPt;
    }

    public void setNoPt(Long noPt) {
        this.noPt = noPt;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodBandarPekanMukim getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(KodBandarPekanMukim kodBpm) {
        this.kodBpm = kodBpm;
    }

    public KodDaerah getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(KodDaerah kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public BigDecimal getLuasDilulus() {
        return luasDilulus;
    }

    public void setLuasDilulus(BigDecimal luasDilulus) {
        this.luasDilulus = luasDilulus;
    }

    public PermohonanPlotPelan getPermohonanPlotPelan() {
        return permohonanPlotPelan;
    }

    public void setPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan) {
        this.permohonanPlotPelan = permohonanPlotPelan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Long getNoPtSementara() {
        return noPtSementara;
    }

    public void setNoPtSementara(Long noPtSementara) {
        this.noPtSementara = noPtSementara;
    }

    public String getNoPlot() {
        return noPlot;
    }

    public void setNoPlot(String noPlot) {
        this.noPlot = noPlot;
    }




    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPt != null ? idPt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NoPt)) {
            return false;
        }
        NoPt other = (NoPt) object;
        if ((this.idPt == null && other.idPt != null) || (this.idPt != null && !this.idPt.equals(other.idPt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.NoPt[idPt=" + idPt + "]";
    }

    public KodSyaratNyata getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public KodSekatanKepentingan getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(KodSekatanKepentingan kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getBumi() {
        return bumi;
    }

    public void setBumi(String bumi) {
        this.bumi = bumi;
    }
    
    

}
