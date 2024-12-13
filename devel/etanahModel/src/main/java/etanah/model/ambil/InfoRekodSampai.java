/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
import etanah.model.KodNotis;
import etanah.model.KodPeringkat;
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
 * @author zuraida
 */
@Entity
@Table(name = "Info_Rekodsampai")
@SequenceGenerator(name = "seq_inforekodsmpai", sequenceName = "seq_inforekodsmpai")
public class InfoRekodSampai implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_inforekodsmpai")
    @Column(name = "id_rekod")
    private Long idRekod;

    @Column(name = "id_mohon", length = 40)
    private String idMohon;

    @ManyToOne
    @JoinColumn(name = "KOD_PERINGKAT")
    private KodPeringkat kodPeringkat;

    @Column(name = "no_rekod", length = 6)
    private String noRekod;

    @Column(name = "KETERANGAN_REKOD", length = 200)
    private String keternganRekod;

    @ManyToOne
    @JoinColumn(name = "KOD_NOTIS")
    private KodNotis kodNotis;

    @Embedded
    private InfoAudit infoAudit;

    public Long getIdRekod() {
        return idRekod;
    }

    public void setIdRekod(Long idRekod) {
        this.idRekod = idRekod;
    }

    public String getIdMohon() {
        return idMohon;
    }

    public void setIdMohon(String idMohon) {
        this.idMohon = idMohon;
    }

    public KodPeringkat getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(KodPeringkat kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }

    public String getNoRekod() {
        return noRekod;
    }

    public void setNoRekod(String noRekod) {
        this.noRekod = noRekod;
    }

    public String getKeternganRekod() {
        return keternganRekod;
    }

    public void setKeternganRekod(String keternganRekod) {
        this.keternganRekod = keternganRekod;
    }

    public KodNotis getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(KodNotis kodNotis) {
        this.kodNotis = kodNotis;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
