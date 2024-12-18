package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "mohon_aduan")
@SequenceGenerator(name = "seq_mohon_aduan", sequenceName = "seq_mohon_aduan")
public class PermohonanAduan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_aduan")
    @Column(name = "id_mohon_aduan")
    private long idMohonAduan;
    // tempat aduan dibuat
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukim;
    

    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @Column(name = "perihal")
    private String perihal;

    @Embedded
    private InfoAudit infoAudit;



    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public long getIdMohonAduan() {
        return idMohonAduan;
    }

    public void setIdMohonAduan(long idMohonAduan) {
        this.idMohonAduan = idMohonAduan;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }
  

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
