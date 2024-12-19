package etanah.model.hasil;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "sej_pnyata_pmungut")
public class SejarahPenyataPemungutItem implements Serializable {
	
	@Id
	@Column (name = "id_sej_item")
	long idSejarah;
	
	@Column (name = "id_sej_kew_dok", nullable = false)
	String idSejarahDokumenKewangan;
	
	@Column (name = "id_sej_trans", nullable = false)
	long idSejarahTransaksi;
	
	@Column (name = "id_sej_kdb", nullable = false)
	long idSejarahKewanganBayaran;
	
	@Column (name = "amaun", nullable = false)
	BigDecimal amaun;

    @Column (name = "sts")
	Character status;

	public long getIdSejarah() {
		return idSejarah;
	}

	public void setIdSejarah(long idSejarah) {
		this.idSejarah = idSejarah;
	}

	public String getIdSejarahDokumenKewangan() {
		return idSejarahDokumenKewangan;
	}

	public void setIdSejarahDokumenKewangan(String idSejarahDokumenKewangan) {
		this.idSejarahDokumenKewangan = idSejarahDokumenKewangan;
	}

	public long getIdSejarahTransaksi() {
		return idSejarahTransaksi;
	}

	public void setIdSejarahTransaksi(long idSejarahTransaksi) {
		this.idSejarahTransaksi = idSejarahTransaksi;
	}

	public long getIdSejarahKewanganBayaran() {
		return idSejarahKewanganBayaran;
	}

	public void setIdSejarahKewanganBayaran(long idSejarahKewanganBayaran) {
		this.idSejarahKewanganBayaran = idSejarahKewanganBayaran;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
    
}
