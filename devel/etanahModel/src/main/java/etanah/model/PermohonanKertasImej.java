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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fikri
 */
@Entity
@Table(name = "mohon_kertas_imej")
@SequenceGenerator(name = "seq_mohon_kertas_imej", sequenceName = "seq_mohon_kertas_imej")
public class PermohonanKertasImej implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_kertas_imej")
    @Column(name = "id_imej")
    private long idImej;
    
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @ManyToOne
    @JoinColumn(name = "id_kand", nullable = false)
    private PermohonanKertasKandungan kertasKandungan;
    
    @OneToOne
    @JoinColumn(name = "id_dokumen", nullable = false)
    private Dokumen dokumen;
    
    @Column(name = "catatan")
    private String catatan;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdImej() {
        return idImej;
    }

    public void setIdImej(long idImej) {
        this.idImej = idImej;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
}
