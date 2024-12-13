/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
import etanah.model.KodPeringkat;
import etanah.model.Permohonan;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "nota_siasatan")
@SequenceGenerator(name = "seq_nota_siasatan", sequenceName = "seq_nota_siasatan")
public class NotaSiasatan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_nota_siasatan")
    @Column(name = "id_notis")
    private long idNota;

    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "kod_peringkat")
    private KodPeringkat kodPeringkat;
    @OneToMany(mappedBy = "notaSiasatan", fetch = FetchType.LAZY)
    private List<NotaSiasatanLengkap> senaraiNotaSiasatanLengkap;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdNota() {
        return idNota;
    }

    public void setIdNota(long idNota) {
        this.idNota = idNota;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodPeringkat getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(KodPeringkat kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public List<NotaSiasatanLengkap> getSenaraiNotaSiasatanLengkap() {
        return senaraiNotaSiasatanLengkap;
    }

    public void setSenaraiNotaSiasatanLengkap(List<NotaSiasatanLengkap> senaraiNotaSiasatanLengkap) {
        this.senaraiNotaSiasatanLengkap = senaraiNotaSiasatanLengkap;
    }
    
    
}
