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
@Table(name = "MOHON_INTEG_DOKUMEN")
@SequenceGenerator(name = "seq_mohon_integ_dokumen", sequenceName = "seq_mohon_integ_dokumen")
@NamedQueries({
    @NamedQuery(name = "IntegrasiPermohonanDokumen.findAll", query = "SELECT i FROM IntegrasiPermohonanDokumen i")})
public class IntegrasiPermohonanDokumen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_integ_dokumen")
    @Column(name = "ID_INTEGDOK")
    private Long idIntegdok;



    @ManyToOne
    @JoinColumn (name = "ID_BUTIR")
    private IntegrasiPermohonanButir integrasiPermohonanButir;

    @ManyToOne
    @JoinColumn (name = "KOD_DOKUMEN")
    private KodDokumen kodDokumen;

    @Column(name = "NAMA_FIZIKAL")
    private String namaFizikal;
    
    @Embedded
    private InfoAudit infoAudit;


    public IntegrasiPermohonanDokumen() {
    }

    public IntegrasiPermohonanDokumen(Long idIntegdok) {
        this.idIntegdok = idIntegdok;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public IntegrasiPermohonanButir getIntegrasiPermohonanButir() {
        return integrasiPermohonanButir;
    }

    public void setIntegrasiPermohonanButir(IntegrasiPermohonanButir integrasiPermohonanButir) {
        this.integrasiPermohonanButir = integrasiPermohonanButir;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }



    public Long getIdIntegdok() {
        return idIntegdok;
    }

    public void setIdIntegdok(Long idIntegdok) {
        this.idIntegdok = idIntegdok;
    }



    public String getNamaFizikal() {
        return namaFizikal;
    }

    public void setNamaFizikal(String namaFizikal) {
        this.namaFizikal = namaFizikal;
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
        if (!(object instanceof IntegrasiPermohonanDokumen)) {
            return false;
        }
        IntegrasiPermohonanDokumen other = (IntegrasiPermohonanDokumen) object;
        if ((this.idIntegdok == null && other.idIntegdok != null) || (this.idIntegdok != null && !this.idIntegdok.equals(other.idIntegdok))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.IntegrasiPermohonanDokumen[idIntegdok=" + idIntegdok + "]";
    }

}
