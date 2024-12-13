/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "mohon_permit_item")
@SequenceGenerator(name = "seq_mohon_permit_item", sequenceName = "seq_mohon_permit_item")


public class PermohonanPermitItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_permit_item")
    @Column(name = "id_mohon_permit_item")
    private Long idMohonPermitItem;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn (name = "kod_item")
    private KodItemPermit kodItemPermit;

    public PermohonanPermitItem() {
    }


	@Embedded
	private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
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

    public Long getIdMohonPermitItem() {
        return idMohonPermitItem;
    }

    public void setIdMohonPermitItem(Long idMohonPermitItem) {
        this.idMohonPermitItem = idMohonPermitItem;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

   


}
