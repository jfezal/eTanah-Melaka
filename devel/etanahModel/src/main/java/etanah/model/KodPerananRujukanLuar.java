package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "kod_peranan_ruj_luar")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodPerananRujukanLuar implements Serializable {

    @Id
    @Column(name = "kod")
    private String kod;
    @Column(name = "nama")
    private String nama;


	@ManyToOne
	@JoinColumn (name = "katg_agensi" )
	private KodKategoriAgensi kodKategoriAgensi;

    @Column(name = "aktif")
    private char aktif;
    @Embedded
    InfoAudit infoAudit;

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

    public KodKategoriAgensi getKodKategoriAgensi() {
        return kodKategoriAgensi;
    }

    public void setKodKategoriAgensi(KodKategoriAgensi kodKategoriAgensi) {
        this.kodKategoriAgensi = kodKategoriAgensi;
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
}
