package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "kod_kadar_premium")
@SequenceGenerator (name = "seq_kod_kadar_premium", sequenceName = "seq_kod_kadar_premium", allocationSize = 1)
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKadarPremium implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kod_kadar_premium")
    @Column(name = "id_kod_kadar_premium")
    private long idKodKadarPremium;

    @Column (name = "nama")
    private String nama;

    @Column (name = "peratus_kadar")
    private BigDecimal peratusKadar;

    @Column (name = "aktif")
    private char aktif;

	@ManyToOne
	@JoinColumn (name = "kod_tanah" )
	private KodTanah kodTanah;

	@ManyToOne
	@JoinColumn (name = "kod_guna_tanah" )
	private KodKegunaanTanah kodKegunaanTanah;

    @Embedded
    InfoAudit infoAudit;

    public long getIdKodKadarPremium() {
        return idKodKadarPremium;
    }

    public void setIdKodKadarPremium(long idKodKadarPremium) {
        this.idKodKadarPremium = idKodKadarPremium;
    }

    public KodKegunaanTanah getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(KodKegunaanTanah kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public KodTanah getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(KodTanah kodTanah) {
        this.kodTanah = kodTanah;
    }


    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public BigDecimal getPeratusKadar() {
        return peratusKadar;
    }

    public void setPeratusKadar(BigDecimal peratusKadar) {
        this.peratusKadar = peratusKadar;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
    
    public char getAktif() {
        return aktif;
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
