/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model.ambil;

import etanah.model.InfoAudit;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author zuraida
 */
@Entity
@Table(name = "tuntutan_per_pb")
@SequenceGenerator(name = "seq_tuntutan_per_pb", sequenceName = "seq_tuntutan_per_pb")
public class TuntutanPerPB implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_tuntutan_per_pb")
    @Column(name = "id_tuntutan")
    private Long idTuntutan;
    
    @ManyToOne
    @JoinColumn(name = "id_borang_pb")
    private BorangPerPB borangPerPB;

    @Column(name = "amaun")
    private BigDecimal amaun;
    
    @Column(name = "item_tuntutan")
    private String itemTuntutan;
    
     @Column(name = "nota")
    private String nota;
    
    @Embedded
    InfoAudit infoAudit;

    public Long getIdTuntutan() {
        return idTuntutan;
    }

    public void setIdTuntutan(Long idTuntutan) {
        this.idTuntutan = idTuntutan;
    }



    public BorangPerPB getBorangPerPB() {
        return borangPerPB;
    }

    public void setBorangPerPB(BorangPerPB borangPerPB) {
        this.borangPerPB = borangPerPB;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getItemTuntutan() {
        return itemTuntutan;
    }

    public void setItemTuntutan(String itemTuntutan) {
        this.itemTuntutan = itemTuntutan;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    
}
