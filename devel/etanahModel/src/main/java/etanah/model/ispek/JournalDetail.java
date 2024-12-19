/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.KodCawangan;
import etanah.model.KodTransaksi;
import etanah.model.KodVotDana;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ISPEKS_JOURNAL_DETAIL")
@SequenceGenerator(name = "SEQ_ISPEKS_JOURNAL_DETAIL", sequenceName = "SEQ_ISPEKS_JOURNAL_DETAIL")
public class JournalDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_JOURNAL_DETAIL")
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ID_JOURNAL")
    private Journal journal;
    @Column(name = "AMAUN")
    private BigDecimal amaun;
    @Column(name = "JENIS_AMAUN")
    private String jenisAmaun;
    @ManyToOne
    @JoinColumn(name = "VOT_DANA")
    private KodVotDana votDana;
    @ManyToOne
    @JoinColumn(name = "KOD_TRANS")
    private KodTransaksi kodTransaksi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getJenisAmaun() {
        return jenisAmaun;
    }

    public void setJenisAmaun(String jenisAmaun) {
        this.jenisAmaun = jenisAmaun;
    }

    public KodVotDana getVotDana() {
        return votDana;
    }

    public void setVotDana(KodVotDana votDana) {
        this.votDana = votDana;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

}
