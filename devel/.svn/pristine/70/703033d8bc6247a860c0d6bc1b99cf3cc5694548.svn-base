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
@Table(name = "ISPEKS_JOURNAL")
@SequenceGenerator(name = "SEQ_ISPEKS_JOURNAL", sequenceName = "SEQ_ISPEKS_JOURNAL")
public class Journal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_JOURNAL")
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "KOD_CAW")
    private KodCawangan kodCawangan;
    @Column(name = "NO_JOURNAL")
    private String noJournal;
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
    @Column(name = "NO_RUJUKAN")
    private String noRujukan;
    @Column(name = "PERIHAL")
    private String perihal;
    @Column(name = "JENIS_JOURNAL")
    private String jenisJournal;
    @Column(name = "TRH_RUJUKAN")
    private Date tarikhRujukan;
    @Column(name = "TRH_HANTAR")
    private Date tarikhHantar;
    @ManyToOne
    @JoinColumn(name = "ID_JOURNAL_BATAL")
    private Journal journalBatal;

    public String getNoJournal() {
        return noJournal;
    }

    public void setNoJournal(String noJournal) {
        this.noJournal = noJournal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
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

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getJenisJournal() {
        return jenisJournal;
    }

    public void setJenisJournal(String jenisJournal) {
        this.jenisJournal = jenisJournal;
    }

    public Date getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(Date tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public Date getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(Date tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public Journal getJournalBatal() {
        return journalBatal;
    }

    public void setJournalBatal(Journal journalBatal) {
        this.journalBatal = journalBatal;
    }

   

}
