/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import java.io.Serializable;
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
 * @author mohd.faidzal
 */
@Entity
@Table(name = "nota_siasatan_dok")
@SequenceGenerator(name = "seq_nota_siasatan_dok", sequenceName = "seq_nota_siasatan_dok")
public class NotaSiasatanDokumen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_nota_siasatan_dok")
    @Column(name = "id_notadok")
    private long idNotaDok;

    @ManyToOne
    @JoinColumn(name = "id_notalengkap")
    private NotaSiasatanLengkap notaSiasatanLengkap;

    @ManyToOne
    @JoinColumn(name = "id_dokumen")
    private Dokumen dokumen;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdNotaDok() {
        return idNotaDok;
    }

    public void setIdNotaDok(long idNotaDok) {
        this.idNotaDok = idNotaDok;
    }

    public NotaSiasatanLengkap getNotaSiasatanLengkap() {
        return notaSiasatanLengkap;
    }

    public void setNotaSiasatanLengkap(NotaSiasatanLengkap notaSiasatanLengkap) {
        this.notaSiasatanLengkap = notaSiasatanLengkap;
    }
    
    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
