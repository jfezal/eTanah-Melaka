package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "jtek_dok_terima")
@SequenceGenerator(name = "seq_jtek_dok_terima", sequenceName = "seq_jtek_dok_terima")
public class JteknikalDokumenTerima implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jtek_dok_terima")
    @Column(name = "id_jtek_dok_terima")
    private long idJtekDokTerima;
    @ManyToOne
    @JoinColumn(name = "id_jtek_ulas")
    private UlasanJabatanTeknikal ulasanJabatanTeknikal;
    @ManyToOne
    @JoinColumn(name = "kod_dok")
    private KodDokumen kodDokumen;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @ManyToOne
    @JoinColumn(name = "kod_klas")
    private KodKlasifikasi kodKlasifikasi;
    @Column(name = "perihal")
    private String perihal;
    @Column(name = "nama_fizikal")
    private String namaFizikal;
     @ManyToOne
    @JoinColumn(name = "id_dok")
    private Dokumen dokumen;
     
    @Embedded
    private InfoAudit infoAudit;

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public long getIdJtekDokTerima() {
        return idJtekDokTerima;
    }

    public void setIdJtekDokTerima(long idJtekDokTerima) {
        this.idJtekDokTerima = idJtekDokTerima;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public KodKlasifikasi getKodKlasifikasi() {
        return kodKlasifikasi;
    }

    public void setKodKlasifikasi(KodKlasifikasi kodKlasifikasi) {
        this.kodKlasifikasi = kodKlasifikasi;
    }

    public String getNamaFizikal() {
        return namaFizikal;
    }

    public void setNamaFizikal(String namaFizikal) {
        this.namaFizikal = namaFizikal;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public UlasanJabatanTeknikal getUlasanJabatanTeknikal() {
        return ulasanJabatanTeknikal;
    }

    public void setUlasanJabatanTeknikal(UlasanJabatanTeknikal ulasanJabatanTeknikal) {
        this.ulasanJabatanTeknikal = ulasanJabatanTeknikal;
    }
 

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }
    
    
}
