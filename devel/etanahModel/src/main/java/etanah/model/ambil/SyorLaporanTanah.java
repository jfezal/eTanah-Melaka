/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model.ambil;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
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
 * @author zuraida
 */

@Entity
@Table(name = "syor_lprn_tanah")
@SequenceGenerator(name = "seq_syor_lprn_tanah", sequenceName = "seq_syor_lprn_tanah")
public class SyorLaporanTanah {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_syor_lprn_tanah")
    @Column(name = "id_slt")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;


    @Column(name = "item")
    private String item;
    
    @Column(name = "id_mohon")
    private String idPermohonan;
    
    @Column(name = "nilai_lama")
    private String nilaiLama;
    
    @Column(name = "nilai_baru")
    private String nilaiBaru;


    @Embedded
    InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getNilaiLama() {
        return nilaiLama;
    }

    public void setNilaiLama(String nilaiLama) {
        this.nilaiLama = nilaiLama;
    }

    public String getNilaiBaru() {
        return nilaiBaru;
    }

    public void setNilaiBaru(String nilaiBaru) {
        this.nilaiBaru = nilaiBaru;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
    
    
}
