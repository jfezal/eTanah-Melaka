package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_atas_pihak")
@SequenceGenerator(name = "seq_mohon_atas_pihak", sequenceName = "seq_mohon_atas_pihak")
public class PermohonanAtasPihakBerkepentingan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_atas_pihak")
    @Column(name = "id_atas_pihak")
    private long idAtasPihak;

    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pemohon")
    private Pemohon pemohon;

    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hp")
    private HakmilikPihakBerkepentingan pihakBerkepentingan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pihak_baru")
    private PermohonanAtasPihakBerkepentingan pihakBerkepentinganBaru;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mp")
    private PermohonanPihak permohonanPihak;

    @OneToMany(mappedBy = "atasPihakBerkepentingan")
    private List<PermohonanPihakKemaskini> senaraiKemaskini;

    @Column(name = "catatan")
    private String catatan;
   
    @Column(name = "JENIS_TUGASAN")
    private String jenisTugasan;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdAtasPihak() {
        return idAtasPihak;
    }

    public void setIdAtasPihak(long idAtasPihak) {
        this.idAtasPihak = idAtasPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public HakmilikPihakBerkepentingan getPihakBerkepentingan() {
        return pihakBerkepentingan;
    }

    public void setPihakBerkepentingan(
            HakmilikPihakBerkepentingan pihakBerkepentingan) {
        this.pihakBerkepentingan = pihakBerkepentingan;
    }

    public PermohonanAtasPihakBerkepentingan getPihakBerkepentinganBaru() {
        return pihakBerkepentinganBaru;
    }

    public void setPihakBerkepentinganBaru(
            PermohonanAtasPihakBerkepentingan pihakBerkepentinganBaru) {
        this.pihakBerkepentinganBaru = pihakBerkepentinganBaru;
    }

    public void setSenaraiKemaskini(List<PermohonanPihakKemaskini> senaraiKemaskini) {
        this.senaraiKemaskini = senaraiKemaskini;
    }

    public List<PermohonanPihakKemaskini> getSenaraiKemaskini() {
        return senaraiKemaskini;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getJenisTugasan() {
        return jenisTugasan;
    }

    public void setJenisTugasan(String jenisTugasan) {
        this.jenisTugasan = jenisTugasan;
    }
    

}
