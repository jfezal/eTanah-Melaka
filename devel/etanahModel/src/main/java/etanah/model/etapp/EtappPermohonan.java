package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.Permohonan;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ETAPP_PERMOHONAN")
@SequenceGenerator(name = "seq_ETAPP_PERMOHONAN", sequenceName = "seq_ETAPP_PERMOHONAN")
public class EtappPermohonan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ETAPP_PERMOHONAN")
    @Column(name = "ID_ETAPP_MOHON")
    private Long idEtMohon;
    @ManyToOne
    @JoinColumn(name = "ID_MOHON", nullable = false)
    private Permohonan mohon;
    @Column(name = "NO_FAIL")
    private String noFail;
    @ManyToOne
    @JoinColumn(name = "KOD_DAERAH", nullable = false)
    private KodDaerah daerah;
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdEtMohon() {
        return idEtMohon;
    }

    public void setIdEtMohon(Long idEtMohon) {
        this.idEtMohon = idEtMohon;
    }


  
    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }
}
