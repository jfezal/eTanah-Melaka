package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "lanjut_bayar")
@SequenceGenerator(name = "seq_lanjut_bayar", sequenceName = "seq_lanjut_bayar")
public class LanjutanBayaran implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lanjut_bayar")
	@Column (name = "id_lanjut_bayar")
	private long idLanjutBayar;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_tuntut" )
	private PermohonanTuntutan permohonanTuntutan;

    @Column(name = "trh_byr_pohon")
    private Date tarikhBayaranDipohon;
	

    @Column(name = "trh_byr_lulus")
    private Date tarikhBayaranDilulus;


	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon" )
	private Permohonan permohonan;

        @Column(name = "tempoh")
        private Integer tempoh;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "tempoh_uom" )
	private KodUOM tempohUom;

	@Embedded
	private InfoAudit infoAudit;


    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public KodUOM getTempohUom() {
        return tempohUom;
    }

    public void setTempohUom(KodUOM tempohUom) {
        this.tempohUom = tempohUom;
    }

    public long getIdLanjutBayar() {
        return idLanjutBayar;
    }

    public void setIdLanjutBayar(long idLanjutBayar) {
        this.idLanjutBayar = idLanjutBayar;
    }

    public PermohonanTuntutan getPermohonanTuntutan() {
        return permohonanTuntutan;
    }

    public void setPermohonanTuntutan(PermohonanTuntutan permohonanTuntutan) {
        this.permohonanTuntutan = permohonanTuntutan;
    }

    public Date getTarikhBayaranDilulus() {
        return tarikhBayaranDilulus;
    }

    public void setTarikhBayaranDilulus(Date tarikhBayaranDilulus) {
        this.tarikhBayaranDilulus = tarikhBayaranDilulus;
    }

    public Date getTarikhBayaranDipohon() {
        return tarikhBayaranDipohon;
    }

    public void setTarikhBayaranDipohon(Date tarikhBayaranDipohon) {
        this.tarikhBayaranDipohon = tarikhBayaranDipohon;
    }





	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
