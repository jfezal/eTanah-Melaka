package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "kew_trans_hdpn")
@SequenceGenerator (name = "seq_kew_trans_hdpn", sequenceName = "seq_kew_trans_hdpn")
public class TransaksiHadapan implements Serializable {

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kew_trans_hdpn")
	@Column(name = "id_trans")
	private long idTransaksi;

	@ManyToOne
	@JoinColumn(name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn(name = "kod_trans", nullable = false)
	private KodTransaksi kodTransaksi;

	@Column(name = "utk_thn", nullable = false)
	private int untukTahun;

	@ManyToOne
	@JoinColumn(name = "no_akaun_dt")
	private Akaun akaunDebit;

	@ManyToOne
	@JoinColumn(name = "no_akaun_kr")
	private Akaun akaunKredit;

	@Column(name = "kntt")
	private int kuantiti;

	@Column(name = "amaun")
	private BigDecimal amaun;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mohon")
	private Permohonan permohonan;

	@Column(name = "catatan")
	private String catatan;

	@ManyToOne
	@JoinColumn(name = "kod_status")
	private KodStatusTransaksiKewangan status;

	@ManyToOne
	@JoinColumn(name = "kod_batal")
	private KodBatalDokumenKewangan kodBatal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pos")
	private Transaksi transaksiPos;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdTransaksi() {
		return idTransaksi;
	}

	public void setIdTransaksi(long idTransaksi) {
		this.idTransaksi = idTransaksi;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodTransaksi getKodTransaksi() {
		return kodTransaksi;
	}

	public void setKodTransaksi(KodTransaksi kodTransaksi) {
		this.kodTransaksi = kodTransaksi;
	}

	public int getUntukTahun() {
		return untukTahun;
	}

	public void setUntukTahun(int untukTahun) {
		this.untukTahun = untukTahun;
	}

	public Akaun getAkaunDebit() {
		return akaunDebit;
	}

	public void setAkaunDebit(Akaun akaunDebit) {
		this.akaunDebit = akaunDebit;
	}

	public Akaun getAkaunKredit() {
		return akaunKredit;
	}

	public void setAkaunKredit(Akaun akaunKredit) {
		this.akaunKredit = akaunKredit;
	}

	public int getKuantiti() {
		return kuantiti;
	}

	public void setKuantiti(int kuantiti) {
		this.kuantiti = kuantiti;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public KodStatusTransaksiKewangan getStatus() {
		return status;
	}

	public void setStatus(KodStatusTransaksiKewangan status) {
		this.status = status;
	}

	public KodBatalDokumenKewangan getKodBatal() {
		return kodBatal;
	}

	public void setKodBatal(KodBatalDokumenKewangan kodBatal) {
		this.kodBatal = kodBatal;
	}

	public Transaksi getTransaksiPos() {
		return transaksiPos;
	}

	public void setTransaksiPos(Transaksi transaksiPos) {
		this.transaksiPos = transaksiPos;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
