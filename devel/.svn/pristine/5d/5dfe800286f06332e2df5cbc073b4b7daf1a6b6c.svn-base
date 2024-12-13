package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "hakmilik_urusan_srt")
@SequenceGenerator (name = "seq_hakmilik_urusan_srt", sequenceName = "seq_hakmilik_urusan_srt")
public class HakmilikUrusanSurat implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_hakmilik_urusan_srt")
	@Column (name = "id_srt")
	private long idSurat;
	
	@ManyToOne
	@JoinColumn (name = "id_urusan", nullable = false)
	private HakmilikUrusan urusan;
		
	@ManyToOne
	@JoinColumn (name = "kod_dokumen", nullable = false)
	private KodDokumen kodDokumen;
	
	@Column (name = "no_srt", nullable = false)
	private String noSurat;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdSurat() {
		return idSurat;
	}

	public void setIdSurat(long idSurat) {
		this.idSurat = idSurat;
	}

	public HakmilikUrusan getUrusan() {
		return urusan;
	}

	public void setUrusan(HakmilikUrusan urusan) {
		this.urusan = urusan;
	}

	public KodDokumen getKodDokumen() {
		return kodDokumen;
	}

	public void setKodDokumen(KodDokumen kodDokumen) {
		this.kodDokumen = kodDokumen;
	}

	public String getNoSurat() {
		return noSurat;
	}

	public void setNoSurat(String noSurat) {
		this.noSurat = noSurat;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
