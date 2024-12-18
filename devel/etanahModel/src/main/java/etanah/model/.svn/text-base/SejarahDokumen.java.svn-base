package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "sej_dokumen")
public class SejarahDokumen implements Serializable {

	@Id
	@Column (name = "id_dokumen")
	private long idDokumen;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_induk", nullable = false)
	private Dokumen induk;
	
	@Column (name = "no_versi", nullable = false)
	private String noVersi;
	
	@ManyToOne
	@JoinColumn (name = "kod_dokumen")
	private KodDokumen kodDokumen;
	
	@Column (name = "format")
	private String format;
		
	@Column (name = "saiz")
	private long saiz;
	
	@Column (name = "nama_fizikal")
	private String namaFizikal;
	
	@Column (name = "nama_tt")
	private String namaTandatangan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdDokumen() {
		return idDokumen;
	}

	public void setIdDokumen(long idDokumen) {
		this.idDokumen = idDokumen;
	}

	public Dokumen getInduk() {
		return induk;
	}

	public void setInduk(Dokumen induk) {
		this.induk = induk;
	}

	public String getNoVersi() {
		return noVersi;
	}

	public void setNoVersi(String noVersi) {
		this.noVersi = noVersi;
	}

	public KodDokumen getKodDokumen() {
		return kodDokumen;
	}

	public void setKodDokumen(KodDokumen kodDokumen) {
		this.kodDokumen = kodDokumen;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public long getSaiz() {
		return saiz;
	}

	public void setSaiz(long saiz) {
		this.saiz = saiz;
	}

	public String getNamaFizikal() {
		return namaFizikal;
	}

	public void setNamaFizikal(String namaFizikal) {
		this.namaFizikal = namaFizikal;
	}

	public String getNamaTandatangan() {
		return namaTandatangan;
	}

	public void setNamaTandatangan(String namaTandatangan) {
		this.namaTandatangan = namaTandatangan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
