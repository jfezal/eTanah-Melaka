/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_TUNTUT_BAYAR")
@SequenceGenerator(name = "seq_mohon_tuntut_bayar", sequenceName = "seq_mohon_tuntut_bayar")
@NamedQueries({
    @NamedQuery(name = "MohonTuntutBayar.findAll", query = "SELECT m FROM PermohonanTuntutanBayar m")})
public class PermohonanTuntutanBayar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_tuntut_bayar")
    @Column(name = "ID_BAYAR")
    private Long idBayar;




    @ManyToOne
    @JoinColumn (name = "ID_KOS")
    private PermohonanTuntutanKos permohonanTuntutanKos;



    @ManyToOne
    @JoinColumn (name = "id_kew_dok")
    private DokumenKewangan dokumenKewangan;


    @Column(name = "trh_bayar")
    private Date tarikhBayar;

    @Column(name = "amaun")
    private BigDecimal amaun;

    @Embedded
    private InfoAudit infoAudit;
    
    @ManyToOne (fetch = FetchType.LAZY) 
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    
    @Column (name = "NO_LESEN")
    private String noLesen;

    public PermohonanTuntutanBayar() {
    }

    public PermohonanTuntutanBayar(Long idBayar) {
        this.idBayar = idBayar;
    }

    public Long getIdBayar() {
        return idBayar;
    }

    public void setIdBayar(Long idBayar) {
        this.idBayar = idBayar;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public Date getTarikhBayar() {
        return tarikhBayar;
    }

    public void setTarikhBayar(Date tarikhBayar) {
        this.tarikhBayar = tarikhBayar;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoLesen() {
        return noLesen;
    }

    public void setNoLesen(String noLesen) {
        this.noLesen = noLesen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBayar != null ? idBayar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermohonanTuntutanBayar)) {
            return false;
        }
        PermohonanTuntutanBayar other = (PermohonanTuntutanBayar) object;
        if ((this.idBayar == null && other.idBayar != null) || (this.idBayar != null && !this.idBayar.equals(other.idBayar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.PermohonanTuntutanBayar[idBayar=" + idBayar + "]";
    }

}
