package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "slogan_surat")
@SequenceGenerator(name = "seq_slogan_surat", sequenceName = "seq_slogan_surat")
public class SloganSurat implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_slogan_surat")
	@Column (name = "id_slogan_surat")
	private long idSloganSurat;



    @Column(name = "slogan_surat")
    private String sloganSurat;

	
	
	
	@Embedded
	private InfoAudit infoAudit;

    public long getIdSloganSurat() {
        return idSloganSurat;
    }

    public void setIdSloganSurat(long idSloganSurat) {
        this.idSloganSurat = idSloganSurat;
    }

    public String getSloganSurat() {
        return sloganSurat;
    }

    public void setSloganSurat(String sloganSurat) {
        this.sloganSurat = sloganSurat;
    }







	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
