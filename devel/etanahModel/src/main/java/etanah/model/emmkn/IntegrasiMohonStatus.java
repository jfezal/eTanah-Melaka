/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model.emmkn;

import etanah.model.InfoAudit;
import etanah.model.Permohonan;
import java.io.Serializable;

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
 * @author Izam
 */
@Entity
@Table (name = "intg_mohon_status")
@SequenceGenerator(name = "seq_intg_mohon_status", sequenceName = "seq_intg_mohon_status")
public class IntegrasiMohonStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_intg_mohon_status")
    @Column (name = "id_intg")
    private long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;

    @Column (name = "ws_status")
    private String statusEMMKN;

    @Column (name = "ws_message", columnDefinition = "CLOB")
    private String mesejEMMKN;

    @Column (name = "ws_log_id")
    private String logEMMKN;

    @Embedded
    private InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getStatusEMMKN() {
        return statusEMMKN;
    }

    public void setStatusEMMKN(String statusEMMKN) {
        this.statusEMMKN = statusEMMKN;
    }

    public String getMesejEMMKN() {
        return mesejEMMKN;
    }

    public void setMesejEMMKN(String mesejEMMKN) {
        this.mesejEMMKN = mesejEMMKN;
    }

    public String getLogEMMKN() {
        return logEMMKN;
    }

    public void setLogEMMKN(String logEMMKN) {
        this.logEMMKN = logEMMKN;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
