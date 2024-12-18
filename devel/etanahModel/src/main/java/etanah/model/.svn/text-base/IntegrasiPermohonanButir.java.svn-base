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
@Table(name = "MOHON_INTEG_BUTIR")
@SequenceGenerator(name = "seq_mohon_integ_butir", sequenceName = "seq_mohon_integ_butir")
@NamedQueries({
    @NamedQuery(name = "IntegrasiPermohonanButir.findAll", query = "SELECT i FROM IntegrasiPermohonanButir i")})
public class IntegrasiPermohonanButir implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_integ_butir")
    @Column(name = "ID_BUTIR")
    private Long idButir;

    
    @ManyToOne
    @JoinColumn (name = "ID_INTEG")
    private IntegrasiPermohonan integrasiPermohonan;

    @Column(name = "NAMA_FOLDER_HANTAR")
    private String namaFolderHantar;
    @Column(name = "STS_HANTAR")
    private String stsHantar;
    @Column(name = "TRH_HANTAR")
   // @Temporal(TemporalType.DATE)
    private Date trhHantar;
    @Column(name = "NAMA_FOLDER_TERIMA")
    private String namaFolderTerima;
    @Column(name = "STS_TERIMA")
    private String stsTerima;
    @Column(name = "TRH_TERIMA")
   // @Temporal(TemporalType.DATE)
    private Date trhTerima;

    @Embedded
    private InfoAudit infoAudit;



    public IntegrasiPermohonanButir() {
    }

    public IntegrasiPermohonanButir(Long idButir) {
        this.idButir = idButir;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public IntegrasiPermohonan getIntegrasiPermohonan() {
        return integrasiPermohonan;
    }

    public void setIntegrasiPermohonan(IntegrasiPermohonan integrasiPermohonan) {
        this.integrasiPermohonan = integrasiPermohonan;
    }

   

    public Long getIdButir() {
        return idButir;
    }

    public void setIdButir(Long idButir) {
        this.idButir = idButir;
    }

    public String getNamaFolderHantar() {
        return namaFolderHantar;
    }

    public void setNamaFolderHantar(String namaFolderHantar) {
        this.namaFolderHantar = namaFolderHantar;
    }

    public String getStsHantar() {
        return stsHantar;
    }

    public void setStsHantar(String stsHantar) {
        this.stsHantar = stsHantar;
    }

    public Date getTrhHantar() {
        return trhHantar;
    }

    public void setTrhHantar(Date trhHantar) {
        this.trhHantar = trhHantar;
    }

    public String getNamaFolderTerima() {
        return namaFolderTerima;
    }

    public void setNamaFolderTerima(String namaFolderTerima) {
        this.namaFolderTerima = namaFolderTerima;
    }

    public String getStsTerima() {
        return stsTerima;
    }

    public void setStsTerima(String stsTerima) {
        this.stsTerima = stsTerima;
    }

    public Date getTrhTerima() {
        return trhTerima;
    }

    public void setTrhTerima(Date trhTerima) {
        this.trhTerima = trhTerima;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idButir != null ? idButir.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntegrasiPermohonanButir)) {
            return false;
        }
        IntegrasiPermohonanButir other = (IntegrasiPermohonanButir) object;
        if ((this.idButir == null && other.idButir != null) || (this.idButir != null && !this.idButir.equals(other.idButir))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.IntegrasiPermohonanButir[idButir=" + idButir + "]";
    }

}
