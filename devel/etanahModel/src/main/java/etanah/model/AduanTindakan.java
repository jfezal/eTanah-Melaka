package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "aduan_tndkn")
@SequenceGenerator(name = "seq_aduan_tndkn", sequenceName = "seq_aduan_tndkn")
public class AduanTindakan implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_tndkn")
        @Column (name = "id_tndkn")
	private long idTindakan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@ManyToOne
	@JoinColumn (name = "id_enkuiri")
	private EnkuiriPenguatkuasaan enkuiri;
	
	@ManyToOne
	@JoinColumn (name = "kod_tndkn", nullable = false)
	private KodTindakanPenguatkuasaan tindakan;
	
	@Column (name = "trh_mula")
	private Date tarikhMula;
	
	@Column (name = "tempoh_bln")
	private int tempohBulan;
	
	@Column (name = "tempoh_hari")
	private int tempohHari;
	
	@Column (name = "trh_tamat")
	private Date tarikhTamat;
	
	@Column (name = "amaun")
	private BigDecimal amaun;
	
	@Column (name = "catatan")
	private String catatan;
	
	@ManyToOne
	@JoinColumn (name = "id_dokumen")
	private Dokumen dokumen;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdTindakan() {
		return idTindakan;
	}

	public void setIdTindakan(long idTindakan) {
		this.idTindakan = idTindakan;
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

	public void setEnkuiri(EnkuiriPenguatkuasaan enkuiri) {
		this.enkuiri = enkuiri;
	}

	public EnkuiriPenguatkuasaan getEnkuiri() {
		return enkuiri;
	}

	public KodTindakanPenguatkuasaan getTindakan() {
		return tindakan;
	}

	public void setTindakan(KodTindakanPenguatkuasaan tindakan) {
		this.tindakan = tindakan;
	}

	public Date getTarikhMula() {
		return tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public int getTempohBulan() {
		return tempohBulan;
	}

	public void setTempohBulan(int tempohBulan) {
		this.tempohBulan = tempohBulan;
	}

	public int getTempohHari() {
		return tempohHari;
	}

	public void setTempohHari(int tempohHari) {
		this.tempohHari = tempohHari;
	}

	public Date getTarikhTamat() {
		return tarikhTamat;
	}

	public void setTarikhTamat(Date tarikhTamat) {
		this.tarikhTamat = tarikhTamat;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public void setDokumen(Dokumen dokumen) {
		this.dokumen = dokumen;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
