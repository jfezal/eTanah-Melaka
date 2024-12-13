package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "kkuasa_notis")
public class NotisPenguatkuasaan implements Serializable {
	
	@Id
	@Column (name = "id_notis")
	private long idNotis;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;
		
	@Column (name = "no_ruj", length = 20)
	private String noRujukan;
	
	/**
	 * Tempoh notis/peringatan
	 */
	@Column (name = "tempoh_bln")
	private int tempohBulan;
	@Column (name = "tempoh_hari")
	private int tempohHari;
	
	@Column (name = "trh_notis")
	private Date tarikhNotis;
	
	@ManyToOne
	@JoinColumn (name = "kod_notis")
	private KodNotis notis;
	
	@ManyToOne
	@JoinColumn (name = "kod_hantar")
	private KodCaraPenghantaran caraPenghantaran;
	
	@Column (name = "trh_hantar")
	private Date tarikhDihantar;
	
	// TEMPAT2 PENGHANTARAN NOTIS
	
	@Column (name = "tempat_hantar")
	private String tempatHantar;
	
	@ManyToOne
	@JoinColumn (name = "id_dokumen")
	private Dokumen dokumen;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdNotis() {
		return idNotis;
	}

	public void setIdNotis(long idNotis) {
		this.idNotis = idNotis;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public void setTempohBulan(int tempohBulan) {
		this.tempohBulan = tempohBulan;
	}

	public int getTempohBulan() {
		return tempohBulan;
	}

	public void setTempohHari(int tempohHari) {
		this.tempohHari = tempohHari;
	}

	public int getTempohHari() {
		return tempohHari;
	}

	public Date getTarikhNotis() {
		return tarikhNotis;
	}

	public void setTarikhNotis(Date tarikhNotis) {
		this.tarikhNotis = tarikhNotis;
	}

	public KodNotis getNotis() {
		return notis;
	}

	public void setNotis(KodNotis notis) {
		this.notis = notis;
	}

	public KodCaraPenghantaran getCaraPenghantaran() {
		return caraPenghantaran;
	}

	public void setCaraPenghantaran(KodCaraPenghantaran caraPenghantaran) {
		this.caraPenghantaran = caraPenghantaran;
	}

	public Date getTarikhDihantar() {
		return tarikhDihantar;
	}

	public void setTarikhDihantar(Date tarikhDihantar) {
		this.tarikhDihantar = tarikhDihantar;
	}

	public String getTempatHantar() {
		return tempatHantar;
	}

	public void setTempatHantar(String tempatHantar) {
		this.tempatHantar = tempatHantar;
	}

	public void setDokumen(Dokumen dokumen) {
		this.dokumen = dokumen;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}	

}
