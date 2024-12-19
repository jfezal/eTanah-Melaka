package etanah.model;

import java.io.Serializable;
import javax.persistence.*;
/**
 * Urusan yang menghalang / membatalkan urusan lain
 */
@Entity
@Table (name = "kod_urusan_halang")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodUrusanHalang implements Serializable {
    @Id
    @Column (name = "kod")
    private String kod;

    @ManyToOne
    @JoinColumn(name = "kod_urusan")
    private KodUrusan kodUrusan;

    @ManyToOne
    @JoinColumn(name = "kod_halang")
    private KodUrusan kodHalang;

    @Column(name = "tempoh_hari")
    private Integer tempohHari;

    @Column(name = "tempoh_bln")
    private Integer tempohBulan;

    @Column(name = "tempoh_thn")
    private Integer tempohTahun;

    @Column(name = "jenis_halang")
    private char jenisHalangan;

    @Embedded
    private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    /**
     *
     * @return 'H' menghalang, 'B' membatalkan
     */
    public char getJenisHalangan() {
        return jenisHalangan;
    }

    public void setJenisHalangan(char jenisHalangan) {
        this.jenisHalangan = jenisHalangan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public KodUrusan getKodHalang() {
        return kodHalang;
    }

    public void setKodHalang(KodUrusan kodHalang) {
        this.kodHalang = kodHalang;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Integer getTempohBulan() {
        return tempohBulan;
    }

    public void setTempohBulan(Integer tempohBulan) {
        this.tempohBulan = tempohBulan;
    }

    public Integer getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(Integer tempohHari) {
        this.tempohHari = tempohHari;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

}
