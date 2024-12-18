/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author fikri
 */

@Entity
@Table (name = "lampiran_perintah")
@SequenceGenerator(name = "seq_lampiran_perintah", sequenceName = "seq_lampiran_perintah")
public class LampiranPerintah implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_lampiran_perintah")
    @Column (name = "ID_LAMPIRAN_PERINTAH")
    private long idLampiranPerintah;
    
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "ID_MOHON_PERINTAH", nullable = false)
    private PermohonanLampiranPerintah permohonanLampiranPerintah;
    
    @Column (name = "perihal")
    private String perihalPerintah;
    
    @Column (name = "catatan")
    private String catatanPerintah;
    
    @Embedded
    InfoAudit infoAudit;

    public String getCatatanPerintah() {
        return catatanPerintah;
    }

    public void setCatatanPerintah(String catatanPerintah) {
        this.catatanPerintah = catatanPerintah;
    }

    public long getIdLampiranPerintah() {
        return idLampiranPerintah;
    }

    public void setIdLampiranPerintah(long idLampiranPerintah) {
        this.idLampiranPerintah = idLampiranPerintah;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getPerihalPerintah() {
        return perihalPerintah;
    }

    public void setPerihalPerintah(String perihalPerintah) {
        this.perihalPerintah = perihalPerintah;
    }

    public PermohonanLampiranPerintah getPermohonanLampiranPerintah() {
        return permohonanLampiranPerintah;
    }

    public void setPermohonanLampiranPerintah(PermohonanLampiranPerintah permohonanLampiranPerintah) {
        this.permohonanLampiranPerintah = permohonanLampiranPerintah;
    }
}
