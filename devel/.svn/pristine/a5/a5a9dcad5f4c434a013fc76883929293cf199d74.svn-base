/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
import etanah.model.Pengguna;
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

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "nota_siasatan_lengkap")
@SequenceGenerator(name = "seq_nota_siasatan_lengkap", sequenceName = "seq_nota_siasatan_lengkap")
public class NotaSiasatanLengkap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_nota_siasatan_lengkap")
    @Column(name = "id_notalengkap")
    private long idNotaLengkap;

    @ManyToOne
    @JoinColumn(name = "id_notis")
    private NotaSiasatan notaSiasatan;

    @Column(name = "no_bicara")
    private String noBicara;

    @Column(name = "trh_bicara")
    private Date tarikhBicara;

    @Column(name = "masa_bicara")
    private String masaBicara;

    @Column(name = "tempat_bicara")
    private String tempatBicara;

    @Column(name = "keterangan_bicara")
    private String keteranganicara;

    @Column(name = "flag_bicara")
    private String flagBicara;

    @ManyToOne
    @JoinColumn(name = "id_borang_pb")
    private BorangPerPB borangPerPB;

    @ManyToOne
    @JoinColumn(name = "di_bicara")
    private Pengguna diBicaraOleh;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdNotaLengkap() {
        return idNotaLengkap;
    }

    public void setIdNotaLengkap(long idNotaLengkap) {
        this.idNotaLengkap = idNotaLengkap;
    }

    public NotaSiasatan getNotaSiasatan() {
        return notaSiasatan;
    }

    public void setNotaSiasatan(NotaSiasatan notaSiasatan) {
        this.notaSiasatan = notaSiasatan;
    }

    public String getNoBicara() {
        return noBicara;
    }

    public void setNoBicara(String noBicara) {
        this.noBicara = noBicara;
    }

    public Date getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(Date tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    

    public String getMasaBicara() {
        return masaBicara;
    }

    public void setMasaBicara(String masaBicara) {
        this.masaBicara = masaBicara;
    }

    public String getTempatBicara() {
        return tempatBicara;
    }

    public void setTempatBicara(String tempatBicara) {
        this.tempatBicara = tempatBicara;
    }

    public String getKeteranganicara() {
        return keteranganicara;
    }

    public void setKeteranganicara(String keteranganicara) {
        this.keteranganicara = keteranganicara;
    }

    public String getFlagBicara() {
        return flagBicara;
    }

    public void setFlagBicara(String flagBicara) {
        this.flagBicara = flagBicara;
    }

    public BorangPerPB getBorangPerPB() {
        return borangPerPB;
    }

    public void setBorangPerPB(BorangPerPB borangPerPB) {
        this.borangPerPB = borangPerPB;
    }

    public Pengguna getDiBicaraOleh() {
        return diBicaraOleh;
    }

    public void setDiBicaraOleh(Pengguna diBicaraOleh) {
        this.diBicaraOleh = diBicaraOleh;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    

}
