/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
 * @author john
 */
@Entity
@Table(name = "skim_strata")
@SequenceGenerator(name = "seq_skim_strata", sequenceName = "seq_skim_strata")
public class SkimStrata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_skim_strata")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hakmilik_induk")
    private Hakmilik hakmilikInduk;

   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "katg_bangunan")
    private KodKegunaanBangunan kategoriBangunan;
 @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelas_tanah")
    private KodKelasTanah kelasTanah;
    
    @Column(name = "flag_cukai")
    private String flagCukai;
    
    @Column(name = "flag_jana")
    private String flagJana;
    
    @Column(name = "kos_rendah")
    private String kosRendah;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "peg_k_skim")
    private Pengguna pegKemaskiniSkim;

    @Column (name = "trh_k_skim")
    private Date tarikhKemaskiniSkim;
    
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "peg_jana")
    private Pengguna pegJanaCukai;

    @Column (name = "trh_jana")
    private Date tarikhJanaCukai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
    public Hakmilik getHakmilikInduk() {
        return hakmilikInduk;
    }

    public void setHakmilikInduk(Hakmilik hakmilikInduk) {
        this.hakmilikInduk = hakmilikInduk;
    }

    public KodKegunaanBangunan getKategoriBangunan() {
        return kategoriBangunan;
    }

    public void setKategoriBangunan(KodKegunaanBangunan kategoriBangunan) {
        this.kategoriBangunan = kategoriBangunan;
    }

    public KodKelasTanah getKelasTanah() {
        return kelasTanah;
    }

    public void setKelasTanah(KodKelasTanah kelasTanah) {
        this.kelasTanah = kelasTanah;
    }

    public String getFlagCukai() {
        return flagCukai;
    }

    public void setFlagCukai(String flagCukai) {
        this.flagCukai = flagCukai;
    }

    public String getFlagJana() {
        return flagJana;
    }

    public void setFlagJana(String flagJana) {
        this.flagJana = flagJana;
    }

    public String getKosRendah() {
        return kosRendah;
    }

    public void setKosRendah(String kosRendah) {
        this.kosRendah = kosRendah;
    }

    public Pengguna getPegKemaskiniSkim() {
        return pegKemaskiniSkim;
    }

    public void setPegKemaskiniSkim(Pengguna pegKemaskiniSkim) {
        this.pegKemaskiniSkim = pegKemaskiniSkim;
    }

    public Date getTarikhKemaskiniSkim() {
        return tarikhKemaskiniSkim;
    }

    public void setTarikhKemaskiniSkim(Date tarikhKemaskiniSkim) {
        this.tarikhKemaskiniSkim = tarikhKemaskiniSkim;
    }

    public Pengguna getPegJanaCukai() {
        return pegJanaCukai;
    }

    public void setPegJanaCukai(Pengguna pegJanaCukai) {
        this.pegJanaCukai = pegJanaCukai;
    }

    public Date getTarikhJanaCukai() {
        return tarikhJanaCukai;
    }

    public void setTarikhJanaCukai(Date tarikhJanaCukai) {
        this.tarikhJanaCukai = tarikhJanaCukai;
    }
    
    
    
}
