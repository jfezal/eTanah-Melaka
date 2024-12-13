/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodNotis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "borang_per_mohon")
@SequenceGenerator(name = "seq_borang_per_mohon", sequenceName = "seq_borang_per_mohon")
public class BorangPerPermohonan implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_borang_per_mohon")
    @Column(name = "id_borang_mohon")
    private long id;

    @ManyToOne
    @JoinColumn(name = "kod_notis")
    private KodNotis kodNotis;
    
    @ManyToOne
    @JoinColumn(name = "id_dok")
    private Dokumen dokumen;
    
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
  @ManyToOne
    @JoinColumn(name = "ditandatangan")
    private Pengguna   ditandatangan;
    
    @Column (name = "trh_tt")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trh_tt;

    
    @Embedded
    private InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public KodNotis getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(KodNotis kodNotis) {
        this.kodNotis = kodNotis;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getDitandatangan() {
        return ditandatangan;
    }

    public void setDitandatangan(Pengguna ditandatangan) {
        this.ditandatangan = ditandatangan;
    }

    
    public Date getTrh_tt() {
        return trh_tt;
    }

    public void setTrh_tt(Date trh_tt) {
        this.trh_tt = trh_tt;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
