package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "mohon_tuntut")
@SequenceGenerator(name = "seq_mohon_tuntut", sequenceName = "seq_mohon_tuntut")
public class PermohonanTuntutan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_tuntut")
	@Column (name = "id_tuntut")
	private long idTuntut;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mp", nullable = true)
	private PermohonanPihak pihak;
	
	@ManyToOne
	@JoinColumn (name = "kod_trans")
	private KodTransaksiTuntut kodTransaksiTuntut;


	
	@Column (name = "trh_tuntut", nullable = false)
	private Date tarikhTuntutan;

	@Column (name = "trh_akhir_bayar")
	private Date tarikhAkhirBayaran;


        @Column(name = "tempoh")
        private Integer tempoh;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "tempoh_uom" )
	private KodUOM tempohUom;

        @Column(name = "lanjut_bayar")
        private String lanjutBayaran;

        @Column(name = "ansuran")
        private String ansuran;

        @Column(name = "sts_bayar")
        private String statusBayaran;


    public KodTransaksiTuntut getKodTransaksiTuntut() {
        return kodTransaksiTuntut;
    }

    public void setKodTransaksiTuntut(KodTransaksiTuntut kodTransaksiTuntut) {
        this.kodTransaksiTuntut = kodTransaksiTuntut;
    }

    public Date getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(Date tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public Date getTarikhTuntutan() {
        return tarikhTuntutan;
    }

    public void setTarikhTuntutan(Date tarikhTuntutan) {
        this.tarikhTuntutan = tarikhTuntutan;
    }

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public KodUOM getTempohUom() {
        return tempohUom;
    }

    public void setTempohUom(KodUOM tempohUom) {
        this.tempohUom = tempohUom;
    }

    public String getAnsuran() {
        return ansuran;
    }

    public void setAnsuran(String ansuran) {
        this.ansuran = ansuran;
    }

    public String getLanjutBayaran() {
        return lanjutBayaran;
    }

    public void setLanjutBayaran(String lanjutBayaran) {
        this.lanjutBayaran = lanjutBayaran;
    }

    public String getStatusBayaran() {
        return statusBayaran;
    }

    public void setStatusBayaran(String statusBayaran) {
        this.statusBayaran = statusBayaran;
    }
	
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdTuntut() {
		return idTuntut;
	}

	public void setIdTuntut(long idTuntut) {
		this.idTuntut = idTuntut;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setPihak(PermohonanPihak pihak) {
		this.pihak = pihak;
	}

	public PermohonanPihak getPihak() {
		return pihak;
	}




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
