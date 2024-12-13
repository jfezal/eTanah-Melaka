package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name = "lanjut_tempoh")
@SequenceGenerator(name = "seq_lanjut_tempoh", sequenceName = "seq_lanjut_tempoh")
public class LanjutanTempoh implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lanjut_tempoh")
	@Column (name = "id_lanjut_tempoh")
	private long idLanjutTempoh;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mh" )
	private HakmilikPermohonan hakmilikPermohonan;

    @Column(name = "trh_akhir_pohon")
    private Date tarikhAkhirDipohon;
	

    @Column(name = "trh_akhir_lulus")
    private Date tarikhAkhirDilulus;


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

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public long getIdLanjutTempoh() {
        return idLanjutTempoh;
    }

    public void setIdLanjutTempoh(long idLanjutTempoh) {
        this.idLanjutTempoh = idLanjutTempoh;
    }

    public Date getTarikhAkhirDilulus() {
        return tarikhAkhirDilulus;
    }

    public void setTarikhAkhirDilulus(Date tarikhAkhirDilulus) {
        this.tarikhAkhirDilulus = tarikhAkhirDilulus;
    }

    public Date getTarikhAkhirDipohon() {
        return tarikhAkhirDipohon;
    }

    public void setTarikhAkhirDipohon(Date tarikhAkhirDipohon) {
        this.tarikhAkhirDipohon = tarikhAkhirDipohon;
    }








	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
