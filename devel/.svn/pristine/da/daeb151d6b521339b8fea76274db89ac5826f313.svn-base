/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author fikri
 */
@Entity
@Table (name = "mohon_lampiran_perintah")
@SequenceGenerator(name = "seq_mohon_lampiran_perintah", sequenceName = "seq_mohon_lampiran_perintah")
public class PermohonanLampiranPerintah implements Serializable{
    
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lampiran_perintah")
    @Column (name = "id_mohon_perintah")
    private long idPermohonanPerintah;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    
    @Column (name = "jenis_laporan")
    private String jenisLaporan;
    
    @OneToMany (mappedBy = "permohonanLampiranPerintah", fetch = FetchType.LAZY)
    private List<LampiranPerintah> senaraiLampiranPerintah;
    
    @Embedded
    InfoAudit infoAudit;

    public long getIdPermohonanPerintah() {
        return idPermohonanPerintah;
    }

    public void setIdPermohonanPerintah(long idPermohonanPerintah) {
        this.idPermohonanPerintah = idPermohonanPerintah;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<LampiranPerintah> getSenaraiLampiranPerintah() {
        return senaraiLampiranPerintah;
    }

    public void setSenaraiLampiranPerintah(List<LampiranPerintah> senaraiLampiranPerintah) {
        this.senaraiLampiranPerintah = senaraiLampiranPerintah;
    }
    
}
