/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
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
@Table(name = "tampal_borang_hakmilik")
@SequenceGenerator(name = "seq_tampal_borang_hakmilik", sequenceName = "seq_tampal_borang_hakmilik")
public class TampalBorangHakmilik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tampal_borang_hakmilik")
    @Column(name = "id_tampal_borangHM")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_borang_hm")
    private BorangPerHakmilik borangPerHakmilik;
    @Column(name = "catatan")
    private String catatan;
    @Column(name = "tempat_tampal")
    private String tempatTampal;
    @Column(name = "penghantar_notis")
    private String penghantarNotis;
    @Column(name = "tarikh_tampal")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhTampal;
    @Embedded
    InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BorangPerHakmilik getBorangPerHakmilik() {
        return borangPerHakmilik;
    }

    public void setBorangPerHakmilik(BorangPerHakmilik borangPerHakmilik) {
        this.borangPerHakmilik = borangPerHakmilik;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTempatTampal() {
        return tempatTampal;
    }

    public void setTempatTampal(String tempatTampal) {
        this.tempatTampal = tempatTampal;
    }

    public String getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(String penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public Date getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(Date tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
