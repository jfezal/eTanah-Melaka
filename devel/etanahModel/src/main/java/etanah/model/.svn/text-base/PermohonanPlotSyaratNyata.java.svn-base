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
@Table(name = "mohon_ppelan_syarat_nyata")
@SequenceGenerator(name = "seq_mohon_ppelan_syarat_nyata", sequenceName = "seq_mohon_ppelan_syarat_nyata")

public class PermohonanPlotSyaratNyata implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_ppelan_syarat_nyata")
    @Column(name = "id_mpp_nyata")
    private long idMppSyarat;

    @ManyToOne
    @JoinColumn(name = "kod_syarat_nyata")
    private KodSyaratNyata kodSyaratNyata;
    
    @ManyToOne
    @JoinColumn(name = "id_plot")
    private PermohonanPlotPelan permohonanPlotPelan;

    public long getIdMppSyarat() {
        return idMppSyarat;
    }

    public void setIdMppSyarat(long idMppSyarat) {
        this.idMppSyarat = idMppSyarat;
    }

    

    public KodSyaratNyata getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public PermohonanPlotPelan getPermohonanPlotPelan() {
        return permohonanPlotPelan;
    }

    public void setPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan) {
        this.permohonanPlotPelan = permohonanPlotPelan;
    }
    
    
}
