package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "folder_dok")
@SequenceGenerator (name = "seq_folder_dok", sequenceName = "seq_folder_dok")
public class KandunganFolder implements Serializable {

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_folder_dok")
	@Column (name = "id_folderdok")
	private long IdKandunganFolder;
	
	
	@ManyToOne
	@JoinColumn (name = "id_folder")
	private FolderDokumen folder;
	
	@ManyToOne
	@JoinColumn (name = "id_dokumen")
	private Dokumen dokumen;
	
	@Column (name = "catatan", length = 4000)
	private String catatan;
	
	@Embedded
	private InfoAudit infoAudit;
	

	public long getIdKandunganFolder() {
		return IdKandunganFolder;
	}

	public void setIdKandunganFolder(long idKandunganFolder) {
		IdKandunganFolder = idKandunganFolder;
	}

	public FolderDokumen getFolder() {
		return folder;
	}

	public void setFolder(FolderDokumen folder) {
		this.folder = folder;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public void setDokumen(Dokumen dokumen) {
		this.dokumen = dokumen;
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
