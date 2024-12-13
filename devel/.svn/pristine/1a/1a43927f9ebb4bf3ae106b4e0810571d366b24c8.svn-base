/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "mohon_lapor_usaha")
@SequenceGenerator(name = "SEQ_MOHON_LAPOR_USAHA", sequenceName = "SEQ_MOHON_LAPOR_USAHA")
public class PermohonanLaporanUsaha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOHON_LAPOR_USAHA")
    @Column(name = "id_lapor_usaha")
    private long idLaporanUsaha;
    
    @ManyToOne
    @JoinColumn(name = "id_lapor", nullable = false)
    private LaporanTanah idLaporan;
    
    @Column(name = "diusaha")
    private String diUsaha;
    
    @Column(name = "diusaha_oleh")
    private String diUsahaOleh;
        
    @Embedded
    InfoAudit infoAudit;

    public long getIdLaporanUsaha() {
        return idLaporanUsaha;
    }

    public void setIdLaporanUsaha(long idLaporanUsaha) {
        this.idLaporanUsaha = idLaporanUsaha;
    }

    public LaporanTanah getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(LaporanTanah idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getDiUsaha() {
        return diUsaha;
    }

    public void setDiUsaha(String diUsaha) {
        this.diUsaha = diUsaha;
    }

    public String getDiUsahaOleh() {
        return diUsahaOleh;
    }

    public void setDiUsahaOleh(String diUsahaOleh) {
        this.diUsahaOleh = diUsahaOleh;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
