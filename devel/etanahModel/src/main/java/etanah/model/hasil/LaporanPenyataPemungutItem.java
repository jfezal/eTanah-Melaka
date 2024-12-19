package etanah.model.hasil;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "lapor_pnyata_pmungut")
@SequenceGenerator (name = "seq_lapor_pnyata_pmungut", sequenceName = "seq_lapor_pnyata_pmungut")
public class LaporanPenyataPemungutItem implements Serializable {
	
	@Id
	@Column (name = "id_item")
	long idLaporan;
	
	@Column (name = "id_kew_dok", nullable = false)
	String idDokumenKewangan;
	
	@Column (name = "id_trans", nullable = false)
	long idTransaksi;
	
	@Column (name = "id_kdb", nullable = false)
	long idKewanganBayaran;
	
	@Column (name = "amaun", nullable = false)
	BigDecimal amaun;

        @Column (name = "sts")
	Character status;

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public long getIdKewanganBayaran() {
        return idKewanganBayaran;
    }

    public void setIdKewanganBayaran(long idKewanganBayaran) {
        this.idKewanganBayaran = idKewanganBayaran;
    }

    public long getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(long idLaporan) {
        this.idLaporan = idLaporan;
    }

    public long getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(long idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

}
