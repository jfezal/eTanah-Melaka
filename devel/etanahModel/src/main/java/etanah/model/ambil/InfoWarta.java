/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodNotis;
import etanah.model.KodPeringkat;
import etanah.model.KodUOM;
import etanah.model.Permohonan;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;    
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/*
 *
 * @author wazer
 */
@Entity
@Table(name = "info_warta")
@SequenceGenerator(name = "SEQ_INFOWARTA", sequenceName = "SEQ_INFOWARTA")
public class InfoWarta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_INFOWARTA")
    @Column(name = "id_warta")
    private long idWarta;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "mohon")
//    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
     
     @ManyToOne
    @JoinColumn(name = "KOD_PERINGKAT")
    private KodPeringkat kodPeringkat;
    @Column(name = "no_fail_jkptg")
    private String failJKPTG;
    @Column(name = "no_warta")
    private String noWarta;
    @Column(name = "trh_warta")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trhWarta;
    @Column(name = "keterangan_warta")
    private String keteranganWarta;
     @ManyToOne
    @JoinColumn(name =  "kod_notis")
    private KodDokumen notis;

    @Embedded
    private InfoAudit infoAudit;

    public Date getTrhWarta() {
        return trhWarta;
    }

    public void setTrhWarta(Date trhWarta) {
        this.trhWarta = trhWarta;
    }

    public long getIdWarta() {
        return idWarta;
    }

    public void setIdWarta(long idWarta) {
        this.idWarta = idWarta;
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

    public String getFailJKPTG() {
        return failJKPTG;
    }

    public void setFailJKPTG(String failJKPTG) {
        this.failJKPTG = failJKPTG;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getKeteranganWarta() {
        return keteranganWarta;
    }

    public void setKeteranganWarta(String keteranganWarta) {
        this.keteranganWarta = keteranganWarta;
    }

    public KodDokumen getNotis() {
        return notis;
    }

    public void setNotis(KodDokumen notis) {
        this.notis = notis;
    }
    
    

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

   
    
}
