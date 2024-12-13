/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import etanah.model.*;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "permit_kntt_item")
@SequenceGenerator(name = "seq_permit_kntt_item", sequenceName = "seq_permit_kntt_item")


public class PermitItemKuantiti implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permit_kntt_item")
    @Column(name = "id_permit_kntt_item")
    private Long idPermitKnttItem;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne
    @JoinColumn (name = "id_permit_item")
    private PermitItem permitItem;

    @ManyToOne
    @JoinColumn (name = "kod_item")
    private KodItemPermit kodItemPermit;

    @Column(name = "kntt_max")
    private BigDecimal kuantitiMax;

    @ManyToOne
    @JoinColumn (name = "kntt_max_uom")
    private KodUOM kuantitiMaxUom;

    @Column(name = "kntt_max_tempoh")
    private BigDecimal kuantitiMaxTempoh;

    @ManyToOne
    @JoinColumn (name = "kntt_max_tempoh_uom")
    private KodUOM kuantitiMaxTempohUom;

    @Column(name = "tempoh")
    private Integer tempoh;

    @ManyToOne
    @JoinColumn (name = "tempoh_uom")
    private KodUOM tempohUom;
    
    @Column (name = "NILAI", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal nilai;
    
    @ManyToOne
    @JoinColumn(name = "NILAI_UOM")
    private KodUOM nilaiUom;

    public PermitItemKuantiti() {
    }


	@Embedded
	private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Long getIdPermitKnttItem() {
        return idPermitKnttItem;
    }

    public void setIdPermitKnttItem(Long idPermitKnttItem) {
        this.idPermitKnttItem = idPermitKnttItem;
    }

    public BigDecimal getKuantitiMax() {
        return kuantitiMax;
    }

    public void setKuantitiMax(BigDecimal kuantitiMax) {
        this.kuantitiMax = kuantitiMax;
    }

    public BigDecimal getKuantitiMaxTempoh() {
        return kuantitiMaxTempoh;
    }

    public void setKuantitiMaxTempoh(BigDecimal kuantitiMaxTempoh) {
        this.kuantitiMaxTempoh = kuantitiMaxTempoh;
    }

    public KodUOM getKuantitiMaxTempohUom() {
        return kuantitiMaxTempohUom;
    }

    public void setKuantitiMaxTempohUom(KodUOM kuantitiMaxTempohUom) {
        this.kuantitiMaxTempohUom = kuantitiMaxTempohUom;
    }

    public KodUOM getKuantitiMaxUom() {
        return kuantitiMaxUom;
    }

    public void setKuantitiMaxUom(KodUOM kuantitiMaxUom) {
        this.kuantitiMaxUom = kuantitiMaxUom;
    }

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public KodUOM getTempohUom() {
        return tempohUom;
    }

    public void setTempohUom(KodUOM tempohUom) {
        this.tempohUom = tempohUom;
    }



    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public KodItemPermit getKodItemPermit() {
        return kodItemPermit;
    }

    public void setKodItemPermit(KodItemPermit kodItemPermit) {
        this.kodItemPermit = kodItemPermit;
    }

    public PermitItem getPermitItem() {
        return permitItem;
    }

    public void setPermitItem(PermitItem permitItem) {
        this.permitItem = permitItem;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public KodUOM getNilaiUom() {
        return nilaiUom;
    }

    public void setNilaiUom(KodUOM nilaiUom) {
        this.nilaiUom = nilaiUom;
    }
}
