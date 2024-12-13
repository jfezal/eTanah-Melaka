package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "sej_kew_dokumen_bayar")
public class SejarahDokumenKewanganBayaran implements Serializable {

	@Id
	@Column (name = "id_sej_kdb")
	private long idKewanganBayaran;

	@ManyToOne
	@JoinColumn (name = "id_sej_kew_dok")
	private SejarahDokumenKewangan dokumenKewangan;

	@ManyToOne
	@JoinColumn (name = "id_sej_cara_bayar")
	private SejarahCaraBayaran caraBayaran;

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

	public SejarahDokumenKewangan getDokumenKewangan() {
		return dokumenKewangan;
	}

	public SejarahCaraBayaran getCaraBayaran() {
		return caraBayaran;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public char getAktif(){
		return aktif;
	}
	
	public Pengguna getDibatalOleh() {
		return dibatalOleh;
	}

	public Date getTarikhBatal() {
		return tarikhBatal;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

}
