package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "kod_bpm")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodBandarPekanMukim implements Serializable {

    @Id
    @Column(name = "KOD")
    private int kod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOD_DAERAH")
    private KodDaerah daerah;

    @Column(name = "BPM", length = 3)
    private String bandarPekanMukim;

    @Column(name = "nama", nullable = true, length = 50)
    private String nama;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOD_TANAH")
    private KodTanah kodTanah;

    @OneToMany(mappedBy = "kodBandarPekanMukim")
    private List<KodSeksyen> senaraiSeksyen;

    @Column(name = "AKTIF", length = 1)
    private char aktif;
    
    @Column(name = "kod_notis")
    private String kodNotis;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @Embedded
    private InfoAudit infoAudit;

    public void setKod(int kod) {
        this.kod = kod;
    }

    public int getKod() {
        return kod;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getbandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setKodTanah(KodTanah kodTanah) {
        this.kodTanah = kodTanah;
    }

    public KodTanah getKodTanah() {
        return kodTanah;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public List<KodSeksyen> getSenaraiSeksyen() {
        return senaraiSeksyen;
    }

    public void setSenaraiSeksyen(List<KodSeksyen> senaraiSeksyen) {
        this.senaraiSeksyen = senaraiSeksyen;
    }

    public String getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(String kodNotis) {
        this.kodNotis = kodNotis;
    }
    
    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }
    
    
}
