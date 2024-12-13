/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
import etanah.model.KodPengambilanTuntutan;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name = "ambil_maklumat_tuntutan")
public class PengambilanMaklumatTuntutan implements Serializable {

    @Id
    @Column(name = "ID_AMT")
    private long idPengambilanMaklumatTuntutan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ABT")
    private PengambilanBicaraTuntutan pengambilanBicaraTuntutan;
    @Column(name = "AMAUN")
    private BigDecimal amaun;
    @ManyToOne
    @JoinColumn(name = "KOD_AMBIL_TUNTUTAN")
    private KodPengambilanTuntutan pengambilanTuntutan;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdPengambilanMaklumatTuntutan() {
        return idPengambilanMaklumatTuntutan;
    }

    public void setIdPengambilanMaklumatTuntutan(long idPengambilanMaklumatTuntutan) {
        this.idPengambilanMaklumatTuntutan = idPengambilanMaklumatTuntutan;
    }

    public PengambilanBicaraTuntutan getPengambilanBicaraTuntutan() {
        return pengambilanBicaraTuntutan;
    }

    public void setPengambilanBicaraTuntutan(PengambilanBicaraTuntutan pengambilanBicaraTuntutan) {
        this.pengambilanBicaraTuntutan = pengambilanBicaraTuntutan;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public KodPengambilanTuntutan getPengambilanTuntutan() {
        return pengambilanTuntutan;
    }

    public void setPengambilanTuntutan(KodPengambilanTuntutan pengambilanTuntutan) {
        this.pengambilanTuntutan = pengambilanTuntutan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
}
