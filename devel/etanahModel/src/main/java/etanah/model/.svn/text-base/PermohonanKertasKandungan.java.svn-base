package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_kertas_kand")
@SequenceGenerator(name = "seq_mohon_kertas_kand", sequenceName = "seq_mohon_kertas_kand")
public class PermohonanKertasKandungan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_kertas_kand")
    @Column(name = "id_kand")
    private long idKandungan;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "kod_agensi", nullable = true)
    private KodAgensi agensi;
    @ManyToOne
    @JoinColumn(name = "id_ruj", nullable = true)
    private PermohonanRujukanLuar rujukan;
    @ManyToOne
    @JoinColumn(name = "id_kertas", nullable = false)
    private PermohonanKertas kertas;
    @Column(name = "trh_butir")
    private Date tarikhButiran;
    @Column(name = "bil")
    private int bil;
    @Column(name = "bil_sblm")
    private Integer bilSebelum;
    @Column(name = "subtajuk")
    private String subtajuk;
    @Column(name = "kand", nullable = false)
    private String kandungan;
    @Column (name = "aktif")
    private char aktif;
    @Embedded
    private InfoAudit infoAudit;
    
     @OneToMany(mappedBy = "kertasKandungan")
    private List<PermohonanKertasImej> senaraiKertasImej;

    public long getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(long idKandungan) {
        this.idKandungan = idKandungan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public Date getTarikhButiran() {
        return tarikhButiran;
    }

    public void setTarikhButiran(Date tarikhButiran) {
        this.tarikhButiran = tarikhButiran;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public Integer getBilSebelum() {
        return bilSebelum;
    }

    public void setBilSebelum(Integer bilSebelum) {
        this.bilSebelum = bilSebelum;
    }

    public String getSubtajuk() {
        return subtajuk;
    }

    public void setSubtajuk(String subtajuk) {
        this.subtajuk = subtajuk;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public List<PermohonanKertasImej> getSenaraiKertasImej() {
        return senaraiKertasImej;
    }

    public void setSenaraiKertasImej(List<PermohonanKertasImej> senaraiKertasImej) {
        this.senaraiKertasImej = senaraiKertasImej;
    }

    public KodAgensi getAgensi() {
        return agensi;
    }

    public void setAgensi(KodAgensi agensi) {
        this.agensi = agensi;
    }

    public PermohonanRujukanLuar getRujukan() {
        return rujukan;
    }

    public void setRujukan(PermohonanRujukanLuar rujukan) {
        this.rujukan = rujukan;
    }

 
}
