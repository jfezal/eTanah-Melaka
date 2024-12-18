package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "sej_kew_trans")
@SequenceGenerator (name = "seq_sej_kew_trans", sequenceName = "seq_sej_kew_trans")
public class SejarahTransaksi implements Serializable{
    
    @Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_sej_kew_trans")
    @Column (name = "id_trans")
    private long idTransaksi;
    
    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;
    
    @ManyToOne
    @JoinColumn (name = "kod_trans")
    private KodTransaksi kodTransaksi;
    
    @Column (name = "utk_thn", nullable = false)
    private int untukTahun;

    @Column (name = "thn_kew")
    private int tahunKewangan;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "no_akaun_dt")
    private Akaun akaunDebit;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "no_akaun_kr")
    private Akaun akaunKredit;

    @Column (name = "id_mohon")
    private String idPermohonan;
    
    @Column (name = "kntt")
    private int kuantiti;
    
    @Column (name = "amaun", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal amaun;
    
    @ManyToOne
    @JoinColumn (name = "id_sej_kew_dok")
    private SejarahDokumenKewangan dokumenKewangan;
    
    @ManyToOne
    @JoinColumn (name = "kod_status")
    private KodStatusTransaksiKewangan status;
    
   @ManyToOne
   @JoinColumn (name = "kod_batal")
   private KodBatalDokumenKewangan kodBatal;
       
    @Embedded
    private InfoAudit infoAudit;

    public void setIdTransaksi(long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public long getIdTransaksi() {
        return idTransaksi;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setUntukTahun(int untukTahun) {
		this.untukTahun = untukTahun;
	}

	public int getUntukTahun() {
		return untukTahun;
	}

    public int getTahunKewangan() {
        return tahunKewangan;
    }

    public void setTahunKewangan(int tahunKewangan) {
        this.tahunKewangan = tahunKewangan;
    }

	public void setAkaunDebit(Akaun akaun) {
        this.akaunDebit = akaun;
    }

    public Akaun getAkaunDebit() {
        return akaunDebit;
    }

    public void setAkaunKredit(Akaun akaun) {
        this.akaunKredit = akaun;
    }

    public Akaun getAkaunKredit() {
        return akaunKredit;
    }

    public String getIdPermohonan() {
		return idPermohonan;
	}

	public void setPermohonan(String idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public KodStatusTransaksiKewangan getStatus() {
		return status;
	}

	public void setStatus(KodStatusTransaksiKewangan status) {
		this.status = status;
	}

	public void setKuantiti(int kuantiti) {
		this.kuantiti = kuantiti;
	}

	public int getKuantiti() {
		return kuantiti;
	}

	public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setDokumenKewangan(SejarahDokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public SejarahDokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setKodBatal(KodBatalDokumenKewangan kodBatal) {
		this.kodBatal = kodBatal;
	}

	public KodBatalDokumenKewangan getKodBatal() {
		return kodBatal;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
