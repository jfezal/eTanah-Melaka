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
@Table(name = "hantar_borang_mohon")
@SequenceGenerator(name = "seq_hantar_per_mohon", sequenceName = "seq_hantar_per_mohon")
public class HantarBorangPermohonan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_hantar_per_mohon")
    @Column(name = "id_hantar_borang")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_borang_mohon")
    private BorangPerPermohonan borangPerPermohonan;
    @Column(name = "catatan")
    private String catatan;
    @Column(name = "penghantar_notis")
    private String penghantarNotis;
    @Column(name = "status")
    private String status;
    @Column(name = "tarikh_hantar")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhHantar;
    @Embedded
    InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BorangPerPermohonan getBorangPerPermohonan() {
        return borangPerPermohonan;
    }

    public void setBorangPerPermohonan(BorangPerPermohonan borangPerPermohonan) {
        this.borangPerPermohonan = borangPerPermohonan;
    }



    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(String penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public Date getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(Date tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }


    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
}
