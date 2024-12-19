/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Permohonan;
import javax.persistence.CascadeType;
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

@Entity
@Table(name = "mohon_agensi_tanah")
@SequenceGenerator(name = "seq_mohon_agensi_tanah", sequenceName = "seq_mohon_agensi_tanah")
public class MohonAgensiTanah {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_agensi_tanah")
    @Column(name = "id_mat")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

  @ManyToOne
    @JoinColumn(name = "KOD_AGENSI", nullable = false)
    private KodAgensi kodAgensi;

    @Embedded
    InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    

}
