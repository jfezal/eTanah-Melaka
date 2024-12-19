/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
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
 * @author mohd.faidzal
 */
@Entity
@Table(name = "ISPEKS_BIL")
@SequenceGenerator(name = "SEQ_ISPEKS_BIL", sequenceName = "SEQ_ISPEKS_BIL")
public class Bil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_BIL")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NO_BIL")
    private String noBil;
    @Column(name = "TRH_JANA")
    private Date tarikhJana;
    @Column(name = "PENYEDIA")
    private String penyedia;
    @Column(name = "TRH_SEDIA")
    private Date tarikhSedia;
    @Column(name = "PENYEMAK")
    private String penyemak;
    @Column(name = "TRH_SEMAK")
    private Date tarikhSemak;
    @Column(name = "PELULUS")
    private String pelulus;
    @Column(name = "TRH_LULUS")
    private Date tarikhLulus;
    @Column(name = "JAW_PENYEDIA")
    private String jawatanPenyedia;
    @Column(name = "JAW_PENYEMAK")
    private String jawatanPenyemak;
    @Column(name = "JAW_PELULUS")
    private String jawatanPelulus;
      @ManyToOne
    @JoinColumn(name = "KOD_CAW")
    private KodCawangan kodCawangan;
    @Column(name = "TRH_HANTAR")
    private Date tarikhHantar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoBil() {
        return noBil;
    }

    public void setNoBil(String noBil) {
        this.noBil = noBil;
    }

    public Date getTarikhJana() {
        return tarikhJana;
    }

    public void setTarikhJana(Date tarikhJana) {
        this.tarikhJana = tarikhJana;
    }

    public String getPenyedia() {
        return penyedia;
    }

    public void setPenyedia(String penyedia) {
        this.penyedia = penyedia;
    }

    public Date getTarikhSedia() {
        return tarikhSedia;
    }

    public void setTarikhSedia(Date tarikhSedia) {
        this.tarikhSedia = tarikhSedia;
    }

    public String getPenyemak() {
        return penyemak;
    }

    public void setPenyemak(String penyemak) {
        this.penyemak = penyemak;
    }

    public Date getTarikhSemak() {
        return tarikhSemak;
    }

    public void setTarikhSemak(Date tarikhSemak) {
        this.tarikhSemak = tarikhSemak;
    }

    public String getPelulus() {
        return pelulus;
    }

    public void setPelulus(String pelulus) {
        this.pelulus = pelulus;
    }

    public Date getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(Date tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }

    public String getJawatanPenyedia() {
        return jawatanPenyedia;
    }

    public void setJawatanPenyedia(String jawatanPenyedia) {
        this.jawatanPenyedia = jawatanPenyedia;
    }

    public String getJawatanPenyemak() {
        return jawatanPenyemak;
    }

    public void setJawatanPenyemak(String jawatanPenyemak) {
        this.jawatanPenyemak = jawatanPenyemak;
    }

    public String getJawatanPelulus() {
        return jawatanPelulus;
    }

    public void setJawatanPelulus(String jawatanPelulus) {
        this.jawatanPelulus = jawatanPelulus;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public Date getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(Date tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }
    
    
}
