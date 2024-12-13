package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_trizab_jnsrizab")
@SequenceGenerator(name = "seq_mohon_trizab_jnsrizab", sequenceName = "seq_mohon_trizab_jnsrizab")
public class TanahRizabPermohonanJenisRizab implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_trizab_jnsrizab")
	@Column (name = "id_jnsrizab")
	private long idJnsRizab;
	
	@ManyToOne
	@JoinColumn (name = "id_mtr", nullable = true)
	private TanahRizabPermohonan tanahRizabPermohonan;

	@ManyToOne
	@JoinColumn (name = "kod_rizab")
	private KodRizab kodRizab;
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	
	
	
	@Embedded
	private InfoAudit infoAudit;

    public long getIdJnsRizab() {
        return idJnsRizab;
    }

    public void setIdJnsRizab(long idJnsRizab) {
        this.idJnsRizab = idJnsRizab;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public TanahRizabPermohonan getTanahRizabPermohonan() {
        return tanahRizabPermohonan;
    }

    public void setTanahRizabPermohonan(TanahRizabPermohonan tanahRizabPermohonan) {
        this.tanahRizabPermohonan = tanahRizabPermohonan;
    }




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
