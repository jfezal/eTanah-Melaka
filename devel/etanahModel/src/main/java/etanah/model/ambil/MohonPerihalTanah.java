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
@Table(name = "mohon_perihal_tanah")
@SequenceGenerator(name = "seq_mohon_perihal_tanah", sequenceName = "seq_mohon_perihal_tanah")
public class MohonPerihalTanah {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_perihal_tanah")
    @Column(name = "id_mpt")
    private long id;
    

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kod_perihal", nullable = false)
    private KodPerihalTanah kodPerihalTanah;

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


    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public long getId() {
        return id;
    }

    public void setIdMpt(long id) {
        this.id = id;
    }

    public KodPerihalTanah getKodPerihalTanah() {
        return kodPerihalTanah;
    }

    public void setKodPerihalTanah(KodPerihalTanah kodPerihalTanah) {
        this.kodPerihalTanah = kodPerihalTanah;
    }
    
    

    

}
