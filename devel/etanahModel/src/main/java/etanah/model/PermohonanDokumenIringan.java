package etanah.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table (name = "mohon_dok_iringan")
@SequenceGenerator(name = "seq_mohon_dok_iringan", sequenceName = "seq_mohon_dok_iringan")
public class PermohonanDokumenIringan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_dok_iringan")
	@Column (name = "id_mohon_dok_iringan")
	private long idMohonDokIringan;
	
	@ManyToOne
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;


	@ManyToOne
	@JoinColumn (name = "kod_dokumen" )
	private KodDokumen kodDokumen;

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

    public long getIdMohonDokIringan() {
        return idMohonDokIringan;
    }

    public void setIdMohonDokIringan(long idMohonDokIringan) {
        this.idMohonDokIringan = idMohonDokIringan;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }



	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
