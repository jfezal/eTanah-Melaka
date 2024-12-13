package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "mohon_ruj_luar_salinan")
@SequenceGenerator(name = "seq_mohon_ruj_luar_salinan", sequenceName = "seq_mohon_ruj_luar_salinan")
public class PermohonanRujukanLuarSalinan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_ruj_luar_salinan")
    @Column(name = "id_mohon_ruj_luar_salinan")
    private long idMohonRujLuarSalinan;
    @ManyToOne
    @JoinColumn(name = "id_ruj")
    private PermohonanRujukanLuar permohonanRujukanLuar;
    @ManyToOne
    @JoinColumn(name = "id_dokumen")
    private Dokumen dokumen;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne
    @JoinColumn(name = "kod_agensi")
    private KodAgensi kodAgensi;

    @Embedded
    private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public long getIdMohonRujLuarSalinan() {
        return idMohonRujLuarSalinan;
    }

    public void setIdMohonRujLuarSalinan(long idMohonRujLuarSalinan) {
        this.idMohonRujLuarSalinan = idMohonRujLuarSalinan;
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
