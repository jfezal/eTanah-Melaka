package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "akuan_terima_deposit")
@SequenceGenerator (name = "seq_akuan_terima_deposit", sequenceName = "seq_akuan_terima_deposit")
public class AkuanTerimaDeposit implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_akuan_terima_deposit")
    @Column (name = "id_akuan")
    private int idAkuan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;

	@Column (name = "jenis_deposit", nullable = false)
	private char jenisDeposit;

	@Column (name = "amaun", nullable = false)
	private BigDecimal amaun;

	@ManyToOne
	@JoinColumn (name = "kod_cara_bayar", nullable = false)
	private KodCaraBayaran caraBayaran;

	@Column (name = "no_resit", length = 10)
	private String noResit;

	@Column (name = "id_ruj")
	private String idRuj;

	@Column (name = "trh_terima")
	private Date tarikhTerima;
        
        @Column (name = "aktif")
	private String aktif;



	@Column (name = "no_dok_bayar")
	private String noDokumenBayar;


    @Column(name = "trh_resit")
    @Temporal(TemporalType.DATE)
    private Date tarikhResit;

	@ManyToOne
	@JoinColumn (name = "kod_bank")
	private KodBank bank;


	@ManyToOne
	@JoinColumn (name = "id_mp", nullable = false)
	private PermohonanPihak permohonanPihak;


    @Embedded
    InfoAudit infoAudit;

    public int getIdAkuan() {
		return idAkuan;
	}

	public void setIdAkuan(int idAkuan) {
		this.idAkuan = idAkuan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setJenisDeposit(char jenisDeposit) {
		this.jenisDeposit = jenisDeposit;
	}

	public char getJenisDeposit() {
		return jenisDeposit;
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

    public String getIdRuj() {
        return idRuj;
    }

    public void setIdRuj(String idRuj) {
        this.idRuj = idRuj;
    }

    public String getNoDokumenBayar() {
        return noDokumenBayar;
    }

    public void setNoDokumenBayar(String noDokumenBayar) {
        this.noDokumenBayar = noDokumenBayar;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

	public KodBank getBank() {
		return bank;
	}

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
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

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

}
