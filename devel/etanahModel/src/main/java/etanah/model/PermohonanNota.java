package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_nota")
@SequenceGenerator(name = "seq_mohon_nota", sequenceName = "seq_mohon_nota")
public class PermohonanNota implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_nota")
    @Column(name = "id_mohon_nota")
    private long idMohonNota;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;


    @Column(name = "id_aliran")
    private String idAliran;

    @Column(name = "nota")
    private String nota;
    
    @Column(name = "status_nota")
    private char statusNota;

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

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public long getIdMohonNota() {
        return idMohonNota;
    }

    public void setIdMohonNota(long idMohonNota) {
        this.idMohonNota = idMohonNota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public char getStatusNota() {
        return statusNota;
    }

    public void setStatusNota(char statusNota) {
        this.statusNota = statusNota;
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
