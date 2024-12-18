package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_notis")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodNotis implements Serializable {

    @Id
    @Column (name = "kod", length = 3, columnDefinition = "char(3)")
    private String kod;

    @Column (name = "nama", length = 50, unique = true)
    private String nama;
    
    @Column (name = "perihal")
    private String perihal;

    @Column (name = "tempoh_bln")
    private Integer tempohBulan;

    @Column (name = "tempoh_hari")
    private Integer tempohHari;

    @ManyToOne
    @JoinColumn(name = "urusan_baru", nullable = true)
    private KodUrusan kodUrusanBaru;

    @Column (name = "AKTIF")
    private char aktif;

    @Embedded
    private InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public KodUrusan getKodUrusanBaru() {
        return kodUrusanBaru;
    }

    public void setKodUrusanBaru(KodUrusan kodUrusanBaru) {
        this.kodUrusanBaru = kodUrusanBaru;
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

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public void setPerihal(String perihal) {
		this.perihal = perihal;
	}

	public String getPerihal() {
		return perihal;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
	
}
