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
@Table(name = "mohon_lapor_dipohon")
@SequenceGenerator(name = "SEQ_MOHON_LAPOR_DIPOHON", sequenceName = "SEQ_MOHON_LAPOR_DIPOHON")
public class PermohonanLaporanPohon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOHON_LAPOR_DIPOHON")
    @Column(name = "id_lapor_dipohon")
    private long idLaporanPohon;
    
    @ManyToOne
    @JoinColumn(name = "id_lapor", nullable = false)
    private LaporanTanah idLaporan;
    
    @Column(name = "jns_dipohon")
    private String jenisDipohon;
    
    @Column(name = "no_ruj")
    private String noRujukan;
    
    @Column(name = "kegunaan")
    private String kegunaan;
    
    @Embedded
    InfoAudit infoAudit;

    public long getIdLaporanPohon() {
        return idLaporanPohon;
    }

    public void setIdLaporanPohon(long idLaporanPohon) {
        this.idLaporanPohon = idLaporanPohon;
    }

    public LaporanTanah getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(LaporanTanah idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getJenisDipohon() {
        return jenisDipohon;
    }

    public void setJenisDipohon(String jenisDipohon) {
        this.jenisDipohon = jenisDipohon;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(String kegunaan) {
        this.kegunaan = kegunaan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
        
        
}
