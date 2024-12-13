package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "permit_ruang_udara")
public class PermitRuangUdara implements Serializable {
	
	@Id
	@Column (name = "id_permit")
	private long idPermit;
	
	@ManyToOne 
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@ManyToOne
	@JoinColumn (name = "id_hakmilik", nullable = false)
	private Hakmilik hakmilik;
	
	@Column (name = "tempoh_thn", nullable = false)
	private int tempohTahun;
	
	@Column (name = "trh_tamat", nullable = false)
	private Date tarikhTamat;
	
	@Column (name = "kadar", nullable = false)
	private BigDecimal kadar;
	
	@ManyToOne
	@JoinColumn (name = "kod_sts_permit", nullable = false)
	private KodStatusPermit status;
	
	@ManyToOne
	@JoinColumn (name = "id_kew_dok")
	private DokumenKewangan resit;
	
	@Embedded
	private InfoAudit infoAudit;
	
	

}
