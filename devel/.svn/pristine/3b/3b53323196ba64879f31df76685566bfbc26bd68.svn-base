/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model.emmkn;

import etanah.model.InfoAudit;
import etanah.model.Pihak;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Izam
 */
@Entity
@Table (name = "intg_pihak_jabatan")
@SequenceGenerator(name = "seq_intg_pihak_jabatan", sequenceName = "seq_intg_pihak_jabatan")
public class IntegrasiPihakJabatan implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "id_intg", length = 10)
    private Integer id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_pihak")
    private Pihak pihak;

    @Embedded
    private InfoAudit infoAudit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
