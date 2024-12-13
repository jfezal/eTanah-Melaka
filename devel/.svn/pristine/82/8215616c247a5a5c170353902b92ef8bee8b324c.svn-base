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
@Table(name = "MOHON_LAPOR_KAND")
@SequenceGenerator(name = "seq_mohon_lapor_kand", sequenceName = "seq_mohon_lapor_kand")
@NamedQueries({
    @NamedQuery(name = "PermohonanLaporanKandungan.findAll", query = "SELECT m FROM PermohonanLaporanKandungan m")})
public class PermohonanLaporanKandungan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_kand")
    @Column(name = "ID_KAND")
    private Long idKand;
    @Column(name = "BIL")
    private Short bil;
    @Column(name = "SUBTAJUK")
    private String subtajuk;
    
    @Column(name = "KAND")
    private String kand;
    
    @ManyToOne
    @JoinColumn(name = "ID_LAPOR", nullable = false)
    private LaporanTanah laporanTanah;

    @Embedded
    private InfoAudit infoAudit;

    public PermohonanLaporanKandungan() {
    }

    public PermohonanLaporanKandungan(Long idKand) {
        this.idKand = idKand;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    

    public Long getIdKand() {
        return idKand;
    }

    public void setIdKand(Long idKand) {
        this.idKand = idKand;
    }

    public Short getBil() {
        return bil;
    }

    public void setBil(Short bil) {
        this.bil = bil;
    }

    public String getSubtajuk() {
        return subtajuk;
    }

    public void setSubtajuk(String subtajuk) {
        this.subtajuk = subtajuk;
    }

    public String getKand() {
        return kand;
    }

    public void setKand(String kand) {
        this.kand = kand;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKand != null ? idKand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermohonanLaporanKandungan)) {
            return false;
        }
        PermohonanLaporanKandungan other = (PermohonanLaporanKandungan) object;
        if ((this.idKand == null && other.idKand != null) || (this.idKand != null && !this.idKand.equals(other.idKand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.PermohonanLaporanKandungan[idKand=" + idKand + "]";
    }

}
