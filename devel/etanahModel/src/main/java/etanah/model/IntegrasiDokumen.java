/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name = "INTEGRASI_DOKUMEN")
@SequenceGenerator(name = "seq_integrasi_dokumen", sequenceName = "seq_integrasi_dokumen")
@NamedQueries({
    @NamedQuery(name = "IntegrasiDokumen.findAll", query = "SELECT i FROM IntegrasiDokumen i")})
public class IntegrasiDokumen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_integrasi_dokumen")
    @Column(name = "ID_INTEGDOK")
    private Integer idIntegdok;
    @Column(name = "ID_ALIRAN")
    private String idAliran;
    @Column(name = "DIPERLU_OLEH")
    private String diperluOleh;
    @Column(name = "DOK_HANTAR")
    private String dokHantar;
    @Column(name = "DOK_TERIMA")
    private String dokTerima;
    @ManyToOne
    @JoinColumn(name = "kod_urusan")
    private KodUrusan kodUrusan;
    @ManyToOne
    @JoinColumn(name = "kod_dokumen")
    private KodDokumen kodDokumen;
    @Embedded
    private InfoAudit infoAudit;

    public IntegrasiDokumen() {
    }

    public IntegrasiDokumen(Integer idIntegdok) {
        this.idIntegdok = idIntegdok;
    }

    public Integer getIdIntegdok() {
        return idIntegdok;
    }

    public void setIdIntegdok(Integer idIntegdok) {
        this.idIntegdok = idIntegdok;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public String getDiperluOleh() {
        return diperluOleh;
    }

    public void setDiperluOleh(String diperluOleh) {
        this.diperluOleh = diperluOleh;
    }

    public String getDokHantar() {
        return dokHantar;
    }

    public void setDokHantar(String dokHantar) {
        this.dokHantar = dokHantar;
    }

    public String getDokTerima() {
        return dokTerima;
    }

    public void setDokTerima(String dokTerima) {
        this.dokTerima = dokTerima;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
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
        hash += (idIntegdok != null ? idIntegdok.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntegrasiDokumen)) {
            return false;
        }
        IntegrasiDokumen other = (IntegrasiDokumen) object;
        if ((this.idIntegdok == null && other.idIntegdok != null) || (this.idIntegdok != null && !this.idIntegdok.equals(other.idIntegdok))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.dao.IntegrasiDokumen[idIntegdok=" + idIntegdok + "]";
    }
}
