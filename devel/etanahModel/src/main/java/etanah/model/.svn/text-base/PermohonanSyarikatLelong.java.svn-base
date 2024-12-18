package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "mohon_syt_lelong")
@SequenceGenerator(name = "seq_mohon_syt_lelong", sequenceName = "seq_mohon_syt_lelong")
public class PermohonanSyarikatLelong implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_syt_lelong")
	@Column (name = "id_mohon_syt_lelong")
	private long idMohonSytLelong;
	
	@ManyToOne
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;

	@ManyToOne
	@JoinColumn (name = "id_syt_jlb" )
	private SytJuruLelong sytJuruLelong;


	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;



	@Column (name = "trh_lantik")
	private Date tarikhLantikan;

	
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Date getTarikhLantikan() {
        return tarikhLantikan;
    }

    public void setTarikhLantikan(Date tarikhLantikan) {
        this.tarikhLantikan = tarikhLantikan;
    }


    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public long getIdMohonSytLelong() {
        return idMohonSytLelong;
    }

    public void setIdMohonSytLelong(long idMohonSytLelong) {
        this.idMohonSytLelong = idMohonSytLelong;
    }

    public SytJuruLelong getSytJuruLelong() {
        return sytJuruLelong;
    }

    public void setSytJuruLelong(SytJuruLelong sytJuruLelong) {
        this.sytJuruLelong = sytJuruLelong;
    }
 


	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
