package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_urusan_agensi")
@SequenceGenerator(name = "seq_kod_urusan_agensi", sequenceName = "seq_kod_urusan_agensi")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodUrusanAgensi implements Serializable{
    
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kod_urusan_agensi")
	@Column (name = "id_kod_urusan_agensi")
	private long idKodUrusanAgensi;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_urusan" )
	private KodUrusan kodUrusan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_agensi" )
	private KodAgensi kodAgensi;

    @Column (name = "AKTIF")
    private String aktif;

    @Embedded
    private InfoAudit infoAudit;



    public long getIdKodUrusanAgensi() {
        return idKodUrusanAgensi;
    }

    public void setIdKodUrusanAgensi(long idKodUrusanAgensi) {
        this.idKodUrusanAgensi = idKodUrusanAgensi;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }
 



    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
