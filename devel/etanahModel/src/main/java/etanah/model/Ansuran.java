package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "ansuran")
@SequenceGenerator(name = "seq_ansuran", sequenceName = "seq_ansuran")
public class Ansuran implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ansuran")
	@Column (name = "id_ansuran")
	private long idAnsuran;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_tuntut" )
	private PermohonanTuntutan permohonanTuntutan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon" )
	private Permohonan permohonan;

    @Column(name = "trh_akhir_bayar")
    private Date tarikhAkhirBayaran;
	

    @Column(name = "amaun")
    private BigDecimal amaun;


	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public PermohonanTuntutan getPermohonanTuntutan() {
        return permohonanTuntutan;
    }

    public void setPermohonanTuntutan(PermohonanTuntutan permohonanTuntutan) {
        this.permohonanTuntutan = permohonanTuntutan;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public long getIdAnsuran() {
        return idAnsuran;
    }

    public void setIdAnsuran(long idAnsuran) {
        this.idAnsuran = idAnsuran;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Date getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(Date tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }





	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
