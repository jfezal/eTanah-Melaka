/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
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
@Table(name = "cukai_petak")
@SequenceGenerator(name = "seq_cukai_petak", sequenceName = "seq_cukai_petak")
public class CukaiPetak implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cukai_petak")
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "katg_bangunan", nullable = false)
    private KodKegunaanBangunan kategoriBangunan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kelas_tanah", nullable = false)
    private KodKelasTanah kelasTanah;

    @Column(name = "kadar")
    private String kadar;
    
    @Column(name = "kadar_landed")
    private String kadarLanded;

    public String getKadarLanded() {
        return kadarLanded;
    }

    public void setKadarLanded(String kadarLanded) {
        this.kadarLanded = kadarLanded;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getKadar() {
        return kadar;
    }

    public void setKadar(String kadar) {
        this.kadar = kadar;
    }

}
