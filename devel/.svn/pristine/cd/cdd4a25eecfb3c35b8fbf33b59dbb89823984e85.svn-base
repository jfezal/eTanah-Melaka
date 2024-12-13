package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_lapor_cerun")
@SequenceGenerator(name = "seq_mohon_lapor_cerun", sequenceName = "seq_mohon_lapor_cerun")
public class PermohonanLaporanCerun implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_cerun")
	@Column (name = "id_lapor_cerun")
	private long idLaporCerun;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_cerun_tanah" )
	private KodKecerunanTanah kodCerunanTanah;

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


    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public long getIdLaporCerun() {
        return idLaporCerun;
    }

    public void setIdLaporCerun(long idLaporCerun) {
        this.idLaporCerun = idLaporCerun;
    }

    public KodKecerunanTanah getKodCerunanTanah() {
        return kodCerunanTanah;
    }

    public void setKodCerunanTanah(KodKecerunanTanah kodCerunanTanah) {
        this.kodCerunanTanah = kodCerunanTanah;
    }




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
