/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fikri
 */
@Entity
@Table(name = "stage_urusan")
@SequenceGenerator(name = "SEQ_STAGE_URUSAN", sequenceName = "SEQ_STAGE_URUSAN")
public class UrusanAliran implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STAGE_URUSAN")
    @Column(name = "ID_STAGE_URUSAN")
    private Long idUrusanAliran;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_urusan")
    private KodUrusan kodUrusan;
    
    @Column (name = "stage_id")
    private String idAliran;
    
    @Column (name = "sasar_hari")
    private BigDecimal sasaranHari;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdUrusanAliran() {
        return idUrusanAliran;
    }

    public void setIdUrusanAliran(Long idUrusanAliran) {
        this.idUrusanAliran = idUrusanAliran;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public BigDecimal getSasaranHari() {
        return sasaranHari;
    }

    public void setSasaranHari(BigDecimal sasaranHari) {
        this.sasaranHari = sasaranHari;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
    
    
}
