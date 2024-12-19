package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "AGENSI_KUTIPAN_DOKUMEN")
@SequenceGenerator (name = "SEQ_AGENSI_KUTIPAN_DOK", sequenceName = "SEQ_AGENSI_KUTIPAN_DOK")
public class AgensiKutipanDokumen implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AGENSI_KUTIPAN_DOK")
    @Column (name = "ID_AKD")
    private long idAgensiKutipan;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "KOD_AGENSI_KUTIPAN")
    private KodAgensiKutipan kodAgensiKutipan;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "KOD_DOKUMEN")
    private KodDokumen kodDokumen;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "ID_DOKUMEN")
    private Dokumen idDokumen;
    
    @Column(name = "CATATAN")
    private String catatan;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdAgensiKutipan() {
        return idAgensiKutipan;
    }

    public void setIdAgensiKutipan(long idAgensiKutipan) {
        this.idAgensiKutipan = idAgensiKutipan;
    }

    public KodAgensiKutipan getKodAgensiKutipan() {
        return kodAgensiKutipan;
    }

    public void setKodAgensiKutipan(KodAgensiKutipan kodAgensiKutipan) {
        this.kodAgensiKutipan = kodAgensiKutipan;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
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

    public Dokumen getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Dokumen idDokumen) {
        this.idDokumen = idDokumen;
    }
}
