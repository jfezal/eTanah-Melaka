/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "mohon_status_tanah")
@SequenceGenerator(name = "seq_mohon_status_tanah", sequenceName = "seq_mohon_status_tanah")
public class MohonStatusTanah {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_status_tanah")
    @Column(name = "id_status_tnh")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kod_status", nullable = false)
    private KodStatusTanah kodStatusTanah;

    @Column(name = "keterangan")
    private String keterangan;


    @Embedded
    InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public long getIdStatusTnh() {
        return id;
    }

    public void setIdStatusTnh(long idStatusTnh) {
        this.id = id;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public KodStatusTanah getKodStatusTanah() {
        return kodStatusTanah;
    }

    public void setKodStatusTanah(KodStatusTanah kodStatusTanah) {
        this.kodStatusTanah = kodStatusTanah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    

}
