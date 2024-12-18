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
@Table(name = "ALIRAN_BARU")
@SequenceGenerator(name = "seq_aliran_baru", sequenceName = "seq_aliran_baru")

public class AliranBaru implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aliran_baru")
    @Column(name = "ID_REKOD")
    private Long idRekod;

    @Column(name = "STS")
    private String sts;
    
    
    @ManyToOne
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    
    @ManyToOne
    @JoinColumn(name = "id_mohon_baru", nullable = false)
    private Permohonan permohonanBaru;


    @Embedded
    private InfoAudit infoAudit;

    public AliranBaru() {
    }

    public AliranBaru(Long idRekod) {
        this.idRekod = idRekod;
    }

    public Long getIdRekod() {
        return idRekod;
    }

    public void setIdRekod(Long idRekod) {
        this.idRekod = idRekod;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonanBaru() {
        return permohonanBaru;
    }

    public void setPermohonan_Baru(Permohonan permohonanBaru) {
        this.permohonanBaru = permohonanBaru;
    }

 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRekod != null ? idRekod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AliranBaru)) {
            return false;
        }
        AliranBaru other = (AliranBaru) object;
        if ((this.idRekod == null && other.idRekod != null) || (this.idRekod != null && !this.idRekod.equals(other.idRekod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.AliranBaru[idRekod=" + idRekod + "]";
    }

}
