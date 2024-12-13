/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
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
@Table(name = "ISPEKS_TUGASAN")
@SequenceGenerator(name = "seq_ispeks_tugasan", sequenceName = "seq_ispeks_tugasan")
public class TugasanIspeks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ispeks_tugasan")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NO_REF")
    private String noRef;
    @ManyToOne
    @JoinColumn(name = "KOD_PERINGKAT")
    private KodPeringkatIspeks kodPeringkat;
    @Column(name = "TRH_TERIMA")
    private Date tarikhTerima;
    @ManyToOne
    @JoinColumn(name = "KOD_CAW")
    private KodCawangan kodCaw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoRef() {
        return noRef;
    }

    public void setNoRef(String noRef) {
        this.noRef = noRef;
    }

    public KodPeringkatIspeks getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(KodPeringkatIspeks kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public KodCawangan getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(KodCawangan kodCaw) {
        this.kodCaw = kodCaw;
    }

}
