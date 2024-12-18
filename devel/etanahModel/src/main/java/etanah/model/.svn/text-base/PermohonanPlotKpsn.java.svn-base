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
@Table (name = "mohon_plot_kpsn")
@SequenceGenerator(name = "seq_mohon_plot_kpsn", sequenceName = "seq_mohon_plot_kpsn")
public class PermohonanPlotKpsn implements Serializable {
	  
	@Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_plot_kpsn")
	@Column (name = "id_plot_kpsn")
	private long idPlotKpsn;
        
         @ManyToOne
	@JoinColumn (name = "KOD_HAKMILIK_TETAP")
	private KodHakmilik kodHakmilikTetap;
        
        @ManyToOne
	@JoinColumn (name = "KOD_HAKMILIK_SEMENTARA")
	private KodHakmilik kodHakmilikSementara;
        
        @ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
        
        @ManyToOne
        @JoinColumn(name = "kod_daerah")
        private KodDaerah daerah;
        
        @ManyToOne
        @JoinColumn(name = "kod_bpm")
	private KodBandarPekanMukim bpm;
        
        @ManyToOne
        @JoinColumn(name = "kod_seksyen")
        private KodSeksyen seksyen;
	@Column (name = "tempoh_pegang")
        private Integer tempohPajakan;
        
    public long getIdPlotKpsn() {
        return idPlotKpsn;
    }

    public void setIdPlotKpsn(long idPlotKpsn) {
        this.idPlotKpsn = idPlotKpsn;
    }

    public KodHakmilik getKodHakmilikTetap() {
        return kodHakmilikTetap;
    }

    public void setKodHakmilikTetap(KodHakmilik kodHakmilikTetap) {
        this.kodHakmilikTetap = kodHakmilikTetap;
    }

    public KodHakmilik getKodHakmilikSementara() {
        return kodHakmilikSementara;
    }

    public void setKodHakmilikSementara(KodHakmilik kodHakmilikSementara) {
        this.kodHakmilikSementara = kodHakmilikSementara;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public KodBandarPekanMukim getBpm() {
        return bpm;
    }

    public void setBpm(KodBandarPekanMukim bpm) {
        this.bpm = bpm;
    }

    public KodSeksyen getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(KodSeksyen seksyen) {
        this.seksyen = seksyen;
    }

    public Integer getTempohPajakan() {
        return tempohPajakan;
    }

    public void setTempohPajakan(Integer tempohPajakan) {
        this.tempohPajakan = tempohPajakan;
    }
        
        
        
}
