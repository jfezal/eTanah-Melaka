package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "pguna_kaunter_log")
public class LogKaunter implements Serializable {

	@Id
	@Column (name = "id_log")
	private long idLog;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_pguna", nullable = false)
	private Pengguna pengguna;
	
	@Column (name = "id_kaunter", nullable = false)
	private String idKaunter;
	
	@ManyToOne
	@JoinColumn (name = "kod_kutip", nullable = false)
	private KodKutipan kodKutipan;
	
	@Column (name = "trh_dr")
	private Date tarikhDari;
	
	@Column (name = "trh_kpd")
	private Date tarikhHingga;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdLog() {
		return idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Pengguna getPengguna() {
		return pengguna;
	}

	public void setPengguna(Pengguna pengguna) {
		this.pengguna = pengguna;
	}

	public String getIdKaunter() {
		return idKaunter;
	}

	public void setIdKaunter(String idKaunter) {
		this.idKaunter = idKaunter;
	}

	public KodKutipan getKodKutipan() {
		return kodKutipan;
	}

	public void setKodKutipan(KodKutipan kodKutipan) {
		this.kodKutipan = kodKutipan;
	}

	public Date getTarikhDari() {
		return tarikhDari;
	}

	public void setTarikhDari(Date tarikhDari) {
		this.tarikhDari = tarikhDari;
	}

	public Date getTarikhHingga() {
		return tarikhHingga;
	}

	public void setTarikhHingga(Date tarikhHingga) {
		this.tarikhHingga = tarikhHingga;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
