package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "kew_akaun_log")
@SequenceGenerator(name = "seq_kew_akaun_log", sequenceName = "seq_kew_akaun_log")
public class LogAkaunKewangan implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kew_akaun_log")
	@Column (name = "id_log")
	private long idLog;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "no_akaun", nullable = false)
	private Akaun akaun;
	
	@Column (name = "perkara", nullable = false)
	private String perkara;
	
	@Column (name = "baru")
	private String dataBaru;
	
	@Column (name = "lama")
	private String dataLama;
	
	@Column (name = "catatan")
	private String catatan;
	
	@ManyToOne
	@JoinColumn (name = "dimasuk", nullable = false)
	private Pengguna pengguna;
	
	@Column (name = "trh_masuk", nullable = false)
	private Date tarikhMasuk;

	public long getIdLog() {
		return idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	public Akaun getAkaun() {
		return akaun;
	}

	public void setAkaun(Akaun akaun) {
		this.akaun = akaun;
	}

	public String getPerkara() {
		return perkara;
	}

	public void setPerkara(String perkara) {
		this.perkara = perkara;
	}

	public String getDataBaru() {
		return dataBaru;
	}

	public void setDataBaru(String dataBaru) {
		this.dataBaru = dataBaru;
	}

	public String getDataLama() {
		return dataLama;
	}

	public void setDataLama(String dataLama) {
		this.dataLama = dataLama;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

}
