package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "aduan_lokasi")
@SequenceGenerator(name = "seq_aduan_lokasi", sequenceName = "seq_aduan_lokasi")
public class AduanLokasi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_lokasi")
    @Column(name = "id_aduan_lokasi")
    private long idAduanLokasi;
    // tempat aduan dibuat
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "kod_bpm", nullable = false)
    private KodBandarPekanMukim bandarPekanMukim;
    
    @Column(name = "no_lot")
    private String noLot;

    @ManyToOne
    @JoinColumn(name = "kod_milik")
    private KodPemilikan pemilikan;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_lot")
    private KodLot kodLot;
    
    @Column(name = "lokasi")
    private String lokasi;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdAduanLokasi() {
        return idAduanLokasi;
    }

    public void setIdAduanLokasi(long idAduanLokasi) {
        this.idAduanLokasi = idAduanLokasi;
    }

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

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    
    
    
    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
