package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_aliran")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodAliran implements Serializable {
    
    @Id
    @Column (name = "kod")
    private String kod;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_urusan", nullable = false)
    private KodUrusan kodUrusan;



    @Column (name = "id_aliran")
    private String idAliran;

    @Column (name = "nama")
    private String nama;

    @Column (name = "keterangan_ringkas")
    private String keteranganRingkas;
    
    @Column (name = "aktif")
    private char aktif;
    
    @Embedded
    InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public String getKeteranganRingkas() {
        return keteranganRingkas;
    }

    public void setKeteranganRingkas(String keteranganRingkas) {
        this.keteranganRingkas = keteranganRingkas;
    }

 

    public void setAktif(char aktif) {
        this.aktif =aktif;
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
}
