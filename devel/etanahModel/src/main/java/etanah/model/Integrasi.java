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
@Table(name = "INTEGRASI")
@SequenceGenerator(name = "seq_integrasi", sequenceName = "seq_integrasi")
@NamedQueries({
    @NamedQuery(name = "Integrasi.findAll", query = "SELECT i FROM Integrasi i")})
public class Integrasi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_integrasi")
    @Column(name = "ID_INTEG")
    private Integer idInteg;
    @Column(name = "ID_ALIRAN_HANTAR")
    private String idAliranHantar;
    @Column(name = "ID_ALIRAN_Terima")
    private String idAliranTerima;
    @ManyToOne
    @JoinColumn(name = "kod_urusan")
    private KodUrusan kodUrusan;
    @Embedded
    private InfoAudit infoAudit;

    public Integrasi() {
    }

    public Integrasi(Integer idInteg) {
        this.idInteg = idInteg;
    }

    public Integer getIdInteg() {
        return idInteg;
    }

    public void setIdInteg(Integer idInteg) {
        this.idInteg = idInteg;
    }

    public String getIdAliranHantar() {
        return idAliranHantar;
    }

    public void setIdAliran(String idAliranHantar) {
        this.idAliranHantar = idAliranHantar;
    }

    public String getIdAliranTerima() {
        return idAliranTerima;
    }

    public void setIdAliranTerima(String idAliranTerima) {
        this.idAliranTerima = idAliranTerima;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
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
        if (!(object instanceof Integrasi)) {
            return false;
        }
        Integrasi other = (Integrasi) object;
        if ((this.idInteg == null && other.idInteg != null) || (this.idInteg != null && !this.idInteg.equals(other.idInteg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.dao.Integrasi[idInteg=" + idInteg + "]";
    }
}
