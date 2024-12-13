package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "bayaran_pendudukan")
public class BayaranPendudukan implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_bayaran_pendudukan")
    @Column (name = "id_bayaran")
    private int idBayaran;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;

	@Column (name = "amaun", nullable = false)
	private BigDecimal amaun;

	@ManyToOne
	@JoinColumn (name = "kod_cara_bayar", nullable = false)
	private KodCaraBayaran caraBayaran;

	@Column (name = "no_resit", length = 10)
	private String noResit;

    @Column(name = "trh_resit")
    @Temporal(TemporalType.DATE)
    private Date tarikhResit;

	@ManyToOne
	@JoinColumn (name = "kod_bank")
	private KodBank bank;

    @Embedded
    InfoAudit infoAudit;

    public int getIdBayaran() {
		return idBayaran;
	}

	public void setIdBayaran(int idBayaran) {
		this.idBayaran = idBayaran;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}

	public KodCaraBayaran getCaraBayaran() {
		return caraBayaran;
	}

	public void setCaraBayaran(KodCaraBayaran caraBayaran) {
		this.caraBayaran = caraBayaran;
	}

	public String getNoResit() {
		return noResit;
	}

	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	public Date getTarikhResit() {
		return tarikhResit;
	}

	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}

	public KodBank getBank() {
		return bank;
	}

	public void setBank(KodBank bank) {
		this.bank = bank;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
