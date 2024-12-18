package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "mohon_dok_tt")
@SequenceGenerator(name = "seq_mohon_dok_tt", sequenceName = "seq_mohon_dok_tt")
public class PermohonanTandatanganDokumen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_dok_tt")
    @Column(name = "id_dok_tt")
    private long idDokTt;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "kod_dokumen")
    private KodDokumen kodDokumen;
    @Column(name = "ditandatangan")
    private String diTandatangan;
    @Column(name = "trh_tt")
    private Date trhTt;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }


    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }



    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getDiTandatangan() {
        return diTandatangan;
    }

    public void setDiTandatangan(String diTandatangan) {
        this.diTandatangan = diTandatangan;
    }

    public long getIdDokTt() {
        return idDokTt;
    }

    public void setIdDokTt(long idDokTt) {
        this.idDokTt = idDokTt;
    }

    public Date getTrhTt() {
        return trhTt;
    }

    public void setTrhTt(Date trhTt) {
        this.trhTt = trhTt;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    
    @Embedded
    private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
