package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "kew_dokumen_bayar")
@SequenceGenerator (name = "seq_kew_dokumen_bayar", sequenceName = "seq_kew_dokumen_bayar")
public class DokumenKewanganBayaran implements Serializable {

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_kew_dokumen_bayar")
	@Column (name = "id_kdb")
	private long idKewanganBayaran;

	@ManyToOne
	@JoinColumn (name = "id_kew_dok")
	private DokumenKewangan dokumenKewangan;

	@ManyToOne
	@JoinColumn (name = "id_cara_bayar")
	private CaraBayaran caraBayaran;

	@Column (name = "amaun", nullable = false, precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
	private BigDecimal amaun;

	@Column (name = "aktif")
	private char aktif;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "dibatal")
	private Pengguna dibatalOleh;
	
	@Column (name = "trh_batal")
	private Date tarikhBatal;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdKewanganBayaran() {
		return idKewanganBayaran;
	}

	public void setIdKewanganBayaran(long idKewanganBayaran) {
		this.idKewanganBayaran = idKewanganBayaran;
	}

	public DokumenKewangan getDokumenKewangan() {
		return dokumenKewangan;
	}

	public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
		this.dokumenKewangan = dokumenKewangan;
	}

	public CaraBayaran getCaraBayaran() {
		return caraBayaran;
	}

	public void setCaraBayaran(CaraBayaran caraBayaran) {
		this.caraBayaran = caraBayaran;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}
	
	public char getAktif(){
		return aktif;
	}
	
	public void setAktif(char aktif){
		this.aktif = aktif;
	}

	public Pengguna getDibatalOleh() {
		return dibatalOleh;
	}

	public void setDibatalOleh(Pengguna dibatalOleh) {
		this.dibatalOleh = dibatalOleh;
	}

	public Date getTarikhBatal() {
		return tarikhBatal;
	}

	public void setTarikhBatal(Date tarikhBatal) {
		this.tarikhBatal = tarikhBatal;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
