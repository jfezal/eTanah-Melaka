package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "mohon_waran_item")
@SequenceGenerator(name = "seq_mohon_waran_item", sequenceName = "seq_mohon_waran_item")
public class PermohonanWaranItem implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_waran_item")
	@Column (name = "id_mohon_waran_item")
	private long idMohonWaranItem;
	
	@ManyToOne
	@JoinColumn (name = "id_mohon" )
	private Permohonan idPermohonan;

	@ManyToOne
	@JoinColumn (name = "id_waran" )
	private Waran waran;

	@ManyToOne
	@JoinColumn (name = "id_mh" )
	private HakmilikPermohonan hakmilikPermohonan;

	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;



    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "amaun")
    private BigDecimal amaun;
	
	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Waran getWaran() {
        return waran;
    }

    public void setWaran(Waran waran) {
        this.waran = waran;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public long getIdMohonWaranItem() {
        return idMohonWaranItem;
    }

    public void setIdMohonWaranItem(long idMohonWaranItem) {
        this.idMohonWaranItem = idMohonWaranItem;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    

    public Permohonan getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(Permohonan idPermohonan) {
        this.idPermohonan = idPermohonan;
    }




	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
