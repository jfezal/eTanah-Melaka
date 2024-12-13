/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
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
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_HARTA_BERSAMA")
@SequenceGenerator(name = "seq_mohon_harta_bersama", sequenceName = "seq_mohon_harta_bersama")

public class PermohonanHartaBersama implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_harta_bersama")
    @Column(name = "ID_HARTA_BSAMA")
    private Long idHartaBsama;


    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne
    @JoinColumn(name = "id_skim")
    private PermohonanSkim idSkim;

    @ManyToOne
    @JoinColumn(name = "jenis_harta_bersama")
    private KodHartaBersama jenisHartaBersama;

    @Column (name = "nama")
    private String nama;

    @Embedded
    private InfoAudit infoAudit;

    public PermohonanHartaBersama() {
    }

    public PermohonanHartaBersama(Long idHartaBsama) {
        this.idHartaBsama = idHartaBsama;
    }


    public Long getIdHartaBsama() {
        return idHartaBsama;
    }

    public void setIdHartaBsama(Long idHartaBsama) {
        this.idHartaBsama = idHartaBsama;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public PermohonanSkim getIdSkim() {
        return idSkim;
    }

    public void setIdSkim(PermohonanSkim idSkim) {
        this.idSkim = idSkim;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodHartaBersama getJenisHartaBersama() {
        return jenisHartaBersama;
    }

    public void setJenisHartaBersama(KodHartaBersama jenisHartaBersama) {
        this.jenisHartaBersama = jenisHartaBersama;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHartaBsama != null ? idHartaBsama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermohonanHartaBersama)) {
            return false;
        }
        PermohonanHartaBersama other = (PermohonanHartaBersama) object;
        if ((this.idHartaBsama == null && other.idHartaBsama != null) || (this.idHartaBsama != null && !this.idHartaBsama.equals(other.idHartaBsama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.PermohonanHartaBersama[idHartaBsama=" + idHartaBsama + "]";
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
