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
@Table(name = "permit_sah_laku")
@SequenceGenerator(name = "seq_permit_sah_laku", sequenceName = "seq_permit_sah_laku")
public class PermitSahLaku implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permit_sah_laku")
    @Column(name = "id_permit_sah_laku")
    private Long idPermitSahLaku;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan kodCawangan;
    @ManyToOne
    @JoinColumn(name = "id_permit")
    private Permit permit;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @Column(name = "no_siri")
    private String noSiri;
    @Column(name = "trh_permit")
    private Date tarikhPermit;
    @Column(name = "trh_permit_mula")
    private Date tarikhPermitMula;
    @Column(name = "trh_permit_tamat")
    private Date tarikhPermitTamat;
    @Column(name = "NO_LESEN")
    private String noLesen;

    public PermitSahLaku() {
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


    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public Long getIdPermitSahLaku() {
        return idPermitSahLaku;
    }

    public void setIdPermitSahLaku(Long idPermitSahLaku) {
        this.idPermitSahLaku = idPermitSahLaku;
    }

    public String getNoSiri() {
        return noSiri;
    }

    public void setNoSiri(String noSiri) {
        this.noSiri = noSiri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Date getTarikhPermit() {
        return tarikhPermit;
    }

    public void setTarikhPermit(Date tarikhPermit) {
        this.tarikhPermit = tarikhPermit;
    }

    public Date getTarikhPermitMula() {
        return tarikhPermitMula;
    }

    public void setTarikhPermitMula(Date tarikhPermitMula) {
        this.tarikhPermitMula = tarikhPermitMula;
    }

    public Date getTarikhPermitTamat() {
        return tarikhPermitTamat;
    }

    public void setTarikhPermitTamat(Date tarikhPermitTamat) {
        this.tarikhPermitTamat = tarikhPermitTamat;
    }

    public String getNoLesen() {
        return noLesen;
    }

    public void setNoLesen(String noLesen) {
        this.noLesen = noLesen;
    }
}
