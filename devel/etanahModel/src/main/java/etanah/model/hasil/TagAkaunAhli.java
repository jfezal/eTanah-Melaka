package etanah.model.hasil;

import etanah.model.Akaun;
import java.io.Serializable;
import javax.persistence.*;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;

@Entity
@Table (name = "tag_akaun_ahli")
@SequenceGenerator (name = "seq_tag_akaun_ahli", sequenceName = "seq_tag_akaun_ahli")
public class TagAkaunAhli implements Serializable {
	
	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_tag_akaun_ahli")
	@Column (name = "id_ahli")
	private long idAhli;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_tag", nullable = false)
	private TagAkaun tagAkaun;

        @ManyToOne
	@JoinColumn (name = "no_akaun", nullable = false)
	private Akaun akaun;
	
	@Column (name = "catatan")
        private String catatan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdAhli() {
		return idAhli;
	}

	public void setIdAhli(long idAhli) {
		this.idAhli = idAhli;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public TagAkaun getTagAkaun() {
		return tagAkaun;
	}

	public void setTagAkaun(TagAkaun tagAkaun) {
		this.tagAkaun = tagAkaun;
	}

        public Akaun getAkaun() {
            return akaun;
        }

        public void setAkaun(Akaun akaun) {
            this.akaun = akaun;
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
