/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_INTEG")
@SequenceGenerator(name = "seq_mohon_integ", sequenceName = "seq_mohon_integ")
@NamedQueries({
    @NamedQuery(name = "IntegrasiPermohonan.findAll", query = "SELECT m FROM IntegrasiPermohonan m")})
public class IntegrasiPermohonan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_integ")
    @Column(name = "ID_INTEG")
    private Long idInteg;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "ID_MOHON")
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn (name = "KOD_URUSAN")
    private KodUrusan kodUrusan;

    @Column(name = "ID_ALIRAN")
    private String idAliran;
    @Column(name = "KKINI_BPEL")
    private String kkiniBpel;
     @Column(name = "ID_ALIRAN_TERIMA")
    private String idAliranTerima;


    @ManyToOne
    @JoinColumn (name = "KOD_KPSN")
    private KodKeputusan kodKeputusan;

    @Column(name = "STS")
    private String sts;
    @Column(name = "STS_KKINI_BPEL")
    private String stsKkiniBpel;
    @Column(name = "MESEJ_RALAT")
    private String mesejRalat;

    @Embedded
    private InfoAudit infoAudit;

    public IntegrasiPermohonan() {
    }

    public IntegrasiPermohonan(Long idInteg) {
        this.idInteg = idInteg;
    }


    public Long getIdInteg() {
        return idInteg;
    }

    public void setIdInteg(Long idInteg) {
        this.idInteg = idInteg;
    }



    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public String getKkiniBpel() {
        return kkiniBpel;
    }

    public void setKkiniBpel(String kkiniBpel) {
        this.kkiniBpel = kkiniBpel;
    }


    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getStsKkiniBpel() {
        return stsKkiniBpel;
    }

    public void setStsKkiniBpel(String stsKkiniBpel) {
        this.stsKkiniBpel = stsKkiniBpel;
    }

    public String getMesejRalat() {
        return mesejRalat;
    }

    public void setMesejRalat(String mesejRalat) {
        this.mesejRalat = mesejRalat;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodKeputusan getKodKeputusan() {
        return kodKeputusan;
    }

    public void setKodKeputusan(KodKeputusan kodKeputusan) {
        this.kodKeputusan = kodKeputusan;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdAliranTerima() {
        return idAliranTerima;
    }

    public void setIdAliranTerima(String idAliranTerima) {
        this.idAliranTerima = idAliranTerima;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInteg != null ? idInteg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntegrasiPermohonan)) {
            return false;
        }
        IntegrasiPermohonan other = (IntegrasiPermohonan) object;
        if ((this.idInteg == null && other.idInteg != null) || (this.idInteg != null && !this.idInteg.equals(other.idInteg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.IntegrasiPermohonan[idInteg=" + idInteg + "]";
    }

}
