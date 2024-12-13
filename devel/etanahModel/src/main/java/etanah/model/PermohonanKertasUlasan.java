/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

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
@Table(name = "mohon_kertas_ulas")
@SequenceGenerator(name = "seq_mohon_kertas_ulas", sequenceName = "seq_mohon_kertas_ulas")
public class PermohonanKertasUlasan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_kertas_ulas")
    @Column(name = "id_kertas_ulas")
    private long idKertasUlas;

    @ManyToOne
    @JoinColumn(name = "id_kertas", nullable = false)
    private PermohonanKertas kertas;

    @ManyToOne
    @JoinColumn(name = "kod_kpsn", nullable = false)
    private KodKeputusan kpsn;

    @Column(name = "ulasan")
    private String ulasan;

    @Column(name = "label")
    private String label;

    @Column(name = "urutan")
    private Integer urutan;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdKertasUlas() {
        return idKertasUlas;
    }

    public void setIdKertasUlas(long idKertasUlas) {
        this.idKertasUlas = idKertasUlas;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public KodKeputusan getKpsn() {
        return kpsn;
    }

    public void setKpsn(KodKeputusan kpsn) {
        this.kpsn = kpsn;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
