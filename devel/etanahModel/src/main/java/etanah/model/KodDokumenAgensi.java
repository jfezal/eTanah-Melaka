package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_dok_agensi")
@SequenceGenerator(name = "seq_kod_dok_agensi", sequenceName = "seq_kod_dok_agensi")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodDokumenAgensi implements Serializable{
    
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kod_dok_agensi")
	@Column (name = "id_kod_dok_agensi")
	private long idKodDokAgensi;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_kod_urusan_agensi" )
	private KodUrusanAgensi kodUrusanAgensi;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_dokumen" )
	private KodDokumen kodDokumen;

    @Column (name = "AKTIF")
    private String aktif;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdKodDokAgensi() {
        return idKodDokAgensi;
    }

    public void setIdKodDokAgensi(long idKodDokAgensi) {
        this.idKodDokAgensi = idKodDokAgensi;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public KodUrusanAgensi getKodUrusanAgensi() {
        return kodUrusanAgensi;
    }

    public void setKodUrusanAgensi(KodUrusanAgensi kodUrusanAgensi) {
        this.kodUrusanAgensi = kodUrusanAgensi;
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
