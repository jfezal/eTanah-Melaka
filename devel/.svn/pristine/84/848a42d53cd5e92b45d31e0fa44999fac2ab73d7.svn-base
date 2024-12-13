/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
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
 * @author john
 */
@Entity
@Table(name = "mohon_ppelan_sekatan_penting")
@SequenceGenerator(name = "seq_mohon_ppelan_sekatan", sequenceName = "seq_mohon_ppelan_sekatan")
public class PermohonanPlotSekatan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_ppelan_sekatan")
    @Column(name = "id_mpp_sekatan")
    private long idMppSekatan;

    @ManyToOne
    @JoinColumn(name = "kod_sekatan")
    private KodSekatanKepentingan kodSekatanKepentingan;
    
    @ManyToOne
    @JoinColumn(name = "id_plot")
    private PermohonanPlotPelan permohonanPlotPelan;

    public long getIdMppSekatan() {
        return idMppSekatan;
    }

    public void setIdMppSekatan(long idMppSekatan) {
        this.idMppSekatan = idMppSekatan;
    }

    public KodSekatanKepentingan getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(KodSekatanKepentingan kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public PermohonanPlotPelan getPermohonanPlotPelan() {
        return permohonanPlotPelan;
    }

    public void setPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan) {
        this.permohonanPlotPelan = permohonanPlotPelan;
    }
    
    

}
