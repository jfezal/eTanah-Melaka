package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "mohon_ruj_luar_dok")
@SequenceGenerator(name = "seq_mohon_ruj_luar_dok", sequenceName = "seq_mohon_ruj_luar_dok")
public class PermohonanRujukanLuarDokumen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_ruj_luar_dok")
    @Column(name = "id_mohon_ruj_luar_dok")
    private long idMohonRujLuarDok;
    @ManyToOne
    @JoinColumn(name = "id_ruj")
    private PermohonanRujukanLuar permohonanRujukanLuar;
    @ManyToOne
    @JoinColumn(name = "id_dokumen")
    private Dokumen dokumen;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @Column(name = "haluan")
    private String haluan;
    @Embedded
    private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getHaluan() {
        return haluan;
    }

    public void setHaluan(String haluan) {
        this.haluan = haluan;
    }

    public long getIdMohonRujLuarDok() {
        return idMohonRujLuarDok;
    }

    public void setIdMohonRujLuarDok(long idMohonRujLuarDok) {
        this.idMohonRujLuarDok = idMohonRujLuarDok;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }


    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}
