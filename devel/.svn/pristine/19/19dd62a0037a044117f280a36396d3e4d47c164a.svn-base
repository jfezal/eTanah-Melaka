package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "mohon_tuntut_butir")
@SequenceGenerator(name = "seq_mohon_tuntut_butir", sequenceName = "seq_mohon_tuntut_butir")
public class PermohonanTuntutanButiran implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_tuntut_butir")
	@Column (name = "id_butir")
	private long idTuntut;
	
	@ManyToOne
	@JoinColumn (name = "id_tuntut", nullable = true)
	private PermohonanTuntutan permohonanTuntutan;

	@ManyToOne
	@JoinColumn (name = "id_kos")
	private PermohonanTuntutanKos permohonanTuntutanKos;


	
	
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdTuntut() {
		return idTuntut;
	}

	public void setIdTuntut(long idTuntut) {
		this.idTuntut = idTuntut;
	}

    public PermohonanTuntutan getPermohonanTuntutan() {
        return permohonanTuntutan;
    }

    public void setPermohonanTuntutan(PermohonanTuntutan permohonanTuntutan) {
        this.permohonanTuntutan = permohonanTuntutan;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }





	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
