package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "aduan_pantau")
@SequenceGenerator(name = "seq_aduan_pantau", sequenceName = "seq_aduan_pantau")
public class AduanPemantauan implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_pantau")
        @Column (name = "id_pantau")
	private long idPemantauan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon")
	private Permohonan permohonan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_tndkn")
	private AduanTindakan tindakan;
	
	@Column (name = "trh_pantau", nullable = false)
	private Date tarikhPemantauan;
	
	/**Orang yang memantau jika bukan 
	 * pengguna yang memasukkan
	 */
	@ManyToOne
	@JoinColumn (name = "dipantau", nullable = true)
	private Pengguna dipantauOleh;
	
	@Column (name = "catatan", nullable = false)
	private String catatan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPemantauan() {
		return idPemantauan;
	}

	public void setIdPemantauan(long idPemantauan) {
		this.idPemantauan = idPemantauan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Date getTarikhPemantauan() {
		return tarikhPemantauan;
	}

	public void setTarikhPemantauan(Date tarikhPemantauan) {
		this.tarikhPemantauan = tarikhPemantauan;
	}

	public void setDipantauOleh(Pengguna dipantauOleh) {
		this.dipantauOleh = dipantauOleh;
	}

	public Pengguna getDipantauOleh() {
		return dipantauOleh;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
