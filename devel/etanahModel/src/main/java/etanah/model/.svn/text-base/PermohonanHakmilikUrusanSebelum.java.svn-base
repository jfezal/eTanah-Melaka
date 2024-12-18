package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_hm_urusansblm")
@SequenceGenerator(name = "seq_mohon_hm_urusansblm", sequenceName = "seq_mohon_hm_urusansblm")
public class PermohonanHakmilikUrusanSebelum implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_hm_urusansblm")
	@Column (name = "id_mohon_hm_urusansblm")
	private long idMohonPerserahan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_urusan" )
	private HakmilikUrusan hakmilikUrusan;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mh" )
	private HakmilikPermohonan hakmilikPermohonan;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }


    public long getIdMohonPerserahan() {
        return idMohonPerserahan;
    }

    public void setIdMohonPerserahan(long idMohonPerserahan) {
        this.idMohonPerserahan = idMohonPerserahan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
