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
@Table(name = "permit_item")
@SequenceGenerator(name = "seq_permit_item", sequenceName = "seq_permit_item")


public class PermitItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permit_item")
    @Column(name = "id_permit_item")
    private Long idPermitItem;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne
    @JoinColumn (name = "id_permit")
    private Permit permit;

    @ManyToOne
    @JoinColumn (name = "kod_item")
    private KodItemPermit kodItemPermit;

    public PermitItem() {
    }


	@Embedded
	private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Long getIdPermitItem() {
        return idPermitItem;
    }

    public void setIdPermitItem(Long idPermitItem) {
        this.idPermitItem = idPermitItem;
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

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

   


}
