package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_lps_ambil")
@SequenceGenerator(name = "seq_mohon_lps_ambil", sequenceName = "seq_mohon_lps_ambil")
public class StatusTanahLepasPengambilan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lps_ambil")
	@Column (name = "id_lps_ambil")
	private long idLpsAmbil;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;



	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;


	@ManyToOne
	@JoinColumn (name = "kod_lps_ambil")
	private KodStatusTanahLepasPengambilan kodStatusTanahLepasPengambilan;

	
	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }


    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public long getIdLpsAmbil() {
        return idLpsAmbil;
    }

    public void setIdLpsAmbil(long idLpsAmbil) {
        this.idLpsAmbil = idLpsAmbil;
    }

    public KodStatusTanahLepasPengambilan getKodStatusTanahLepasPengambilan() {
        return kodStatusTanahLepasPengambilan;
    }

    public void setKodStatusTanahLepasPengambilan(KodStatusTanahLepasPengambilan kodStatusTanahLepasPengambilan) {
        this.kodStatusTanahLepasPengambilan = kodStatusTanahLepasPengambilan;
    }






	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
