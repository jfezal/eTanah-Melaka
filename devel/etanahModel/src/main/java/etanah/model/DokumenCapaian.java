package etanah.model;

import java.io.Serializable;

import javax.persistence.*;
/**
 * Rekod capaian untuk dokumen. Sama ada untuk Papar atau Cetak.
 * @author hishammk
 *
 */
@Entity
@Table (name = "dokumen_capai")
@SequenceGenerator (name = "seq_dokumen_capai", sequenceName = "seq_dokumen_capai")
public class DokumenCapaian implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_dokumen_capai")
	@Column (name = "id_capai")
	private long idCapaian;
	
	@ManyToOne
	@JoinColumn (name = "id_dokumen", nullable = false)
	private Dokumen dokumen;
	
	@ManyToOne
        @JoinColumn (name =  "atvt", nullable = false)
	private KodStatusDokumenCapai aktiviti;
	
	@Column (name = "alasan")
	private String alasan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdCapaian() {
		return idCapaian;
	}

	public void setIdCapaian(long idCapaian) {
		this.idCapaian = idCapaian;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public void setDokumen(Dokumen dokumen) {
		this.dokumen = dokumen;
	}

	public KodStatusDokumenCapai getAktiviti() {
		return aktiviti;
	}

	public void setAktiviti(KodStatusDokumenCapai aktiviti) {
		this.aktiviti = aktiviti;
	}
	
	public String getAlasan(){
		return alasan;
	}
	
	public void setAlasan(String alasan){
		this.alasan = alasan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
