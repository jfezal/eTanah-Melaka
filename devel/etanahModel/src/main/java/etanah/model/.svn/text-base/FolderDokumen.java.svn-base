package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "folder")
@SequenceGenerator (name = "seq_folder", sequenceName = "seq_folder")
public class FolderDokumen implements Serializable{

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_folder")
	@Column (name = "id_folder")
	private long folderId;
	
	@Column (name = "tajuk", nullable = false)
	private String tajuk;
	
	@Column (name = "perihal", nullable = true)
	private String perihal;
	
	@Column (name = "no_jilid")
	private String noJilid;
	
	@Column (name = "no_folio")
	private String noFolio;
	
	@OneToMany (mappedBy = "folder", cascade = CascadeType.ALL)
//        @javax.persistence.OrderBy("infoAudit.tarikhMasuk desc")
        @org.hibernate.annotations.OrderBy(clause="trh_masuk desc")
	private List<KandunganFolder> senaraiKandungan;
	
	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public String getTajuk() {
		return tajuk;
	}

	public void setTajuk(String tajuk) {
		this.tajuk = tajuk;
	}

	public String getPerihal() {
		return perihal;
	}

	public void setPerihal(String perihal) {
		this.perihal = perihal;
	}
	
	public String getNoJilid() {
		return noJilid;
	}

	public void setNoJilid(String noJilid) {
		this.noJilid = noJilid;
	}

	public String getNoFolio() {
		return noFolio;
	}

	public void setNoFolio(String noFolio) {
		this.noFolio = noFolio;
	}

	public List<KandunganFolder> getSenaraiKandungan(){
		return senaraiKandungan;
	}
	
	public void setSenaraiKandungan(List<KandunganFolder> kandungan){
		this.senaraiKandungan = kandungan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

	@Embedded
	private InfoAudit infoAudit;
	
}
