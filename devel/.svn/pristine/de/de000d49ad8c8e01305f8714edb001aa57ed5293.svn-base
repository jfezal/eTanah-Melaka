/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
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
@Table(name = "tampal_borang_pb")
@SequenceGenerator(name = "seq_tampal_borang_per_pb", sequenceName = "seq_tampal_borang_per_pb")
public class TampalBorangPB implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tampal_borang_per_pb")
    @Column(name = "id_tampal_borangpb")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_borang_bp")
    private BorangPerPB borangPerPB;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "penghantar_notis", length = 250)
    private String penghantar_notis;

    @Column(name = "tempat_tampal")
    private String tempat_tampal;

    @Column(name = "tarikh_tampal")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trh_tampal;

    @Embedded
    private InfoAudit infoAudit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BorangPerPB getBorangPerPB() {
        return borangPerPB;
    }

    public void setBorangPerPB(BorangPerPB borangPerPB) {
        this.borangPerPB = borangPerPB;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getPenghantar_notis() {
        return penghantar_notis;
    }

    public void setPenghantar_notis(String penghantar_notis) {
        this.penghantar_notis = penghantar_notis;
    }

    public String getTempat_tampal() {
        return tempat_tampal;
    }

    public void setTempat_tampal(String tempat_tampal) {
        this.tempat_tampal = tempat_tampal;
    }

    public Date getTrh_tampal() {
        return trh_tampal;
    }

    public void setTrh_tampal(Date trh_tampal) {
        this.trh_tampal = trh_tampal;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
