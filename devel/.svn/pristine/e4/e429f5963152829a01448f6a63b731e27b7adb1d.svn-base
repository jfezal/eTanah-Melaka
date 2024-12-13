package etanah.model.hasil;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;

@Entity
@Table (name = "tag_akaun")
public class TagAkaun implements Serializable {
	
	@Id
	@Column (name = "id_tag")
	private String idTag;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@Column (name = "nama", nullable = false)
	private String nama;
	
	@Column (name = "catatan")
	private String catatan;
	
	@OneToMany (mappedBy = "tagAkaun", fetch = FetchType.LAZY)
	private List<TagAkaunAhli> senaraiAhli;
	
	@Embedded
	private InfoAudit infoAudit;

	public String getIdTag() {
		return idTag;
	}

	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	
	public void setSenaraiAhli(List<TagAkaunAhli> senaraiAhli){
		this.senaraiAhli = senaraiAhli;
	}
	
	public List<TagAkaunAhli> getSenaraiAhli(){
		return senaraiAhli;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
