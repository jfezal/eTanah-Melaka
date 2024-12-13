package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table (name = "nts_ptl_trans")
@SequenceGenerator(name = "seq_nts_ptl_trans", sequenceName = "seq_nts_ptl_trans")
public class NotisPortalTransaksi implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nts_ptl_trans")
	@Column (name = "id_nts_ptl_trans")
	private long idNtsPtlTrans;
	
        
        
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_nts_ptl_pguna" )
	private NotisPortalPengguna notisPortalPengguna;
        
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_hakmilik" )
	private Hakmilik hakmilik;
        
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "kod_trans" )
	private KodTransaksi kodTransaksi;


	@Column (name = "utk_thn")
	private Integer untukTahun;


	@Column (name = "amaun")
	private BigDecimal emel;

	
	
	@Embedded
	private InfoAudit infoAudit;

    public BigDecimal getEmel() {
        return emel;
    }

    public void setEmel(BigDecimal emel) {
        this.emel = emel;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public long getIdNtsPtlTrans() {
        return idNtsPtlTrans;
    }

    public void setIdNtsPtlTrans(long idNtsPtlTrans) {
        this.idNtsPtlTrans = idNtsPtlTrans;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public NotisPortalPengguna getNotisPortalPengguna() {
        return notisPortalPengguna;
    }

    public void setNotisPortalPengguna(NotisPortalPengguna notisPortalPengguna) {
        this.notisPortalPengguna = notisPortalPengguna;
    }

    public Integer getUntukTahun() {
        return untukTahun;
    }

    public void setUntukTahun(Integer untukTahun) {
        this.untukTahun = untukTahun;
    }

  
    
        

        



	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
	

	
}
