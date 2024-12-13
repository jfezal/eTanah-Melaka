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
@Table(name = "hantar_borang_pb")
@SequenceGenerator(name = "seq_hantar_borang_per_pb", sequenceName = "seq_hantar_borang_per_pb")
public class HantarBorangPB implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_hantar_borang_per_pb")
    @Column(name = "id_hantar_borangpb")
    private int id_hantar_borangpb;

    @ManyToOne
    @JoinColumn(name = "id_borang_bp")
    private BorangPerPB borangPerPB;

    @Column(name = "catatan")
    private String catatan;

    @Column(name = "status")
    private String status;

    @Column(name = "penghantar_notis", length = 250)
    private String penghantar_notis;

    @Column(name = "tarikh_hantar")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trh_hantar;

    @Embedded
    InfoAudit infoAudit;

    public int getId_hantar_borangpb() {
        return id_hantar_borangpb;
    }

    public void setId_hantar_borangpb(int id_hantar_borangpb) {
        this.id_hantar_borangpb = id_hantar_borangpb;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPenghantar_notis() {
        return penghantar_notis;
    }

    public void setPenghantar_notis(String penghantar_notis) {
        this.penghantar_notis = penghantar_notis;
    }

    public Date getTrh_hantar() {
        return trh_hantar;
    }

    public void setTrh_hantar(Date trh_hantar) {
        this.trh_hantar = trh_hantar;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
