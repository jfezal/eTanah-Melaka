package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "sej_cara_bayar")
public class SejarahCaraBayaran implements Serializable {

	@Id
	@Column (name = "id_sej_cara_bayar")
	private long idCaraBayaran;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "kod_cara_bayar")
	private KodCaraBayaran kodCaraBayaran; 
	
	@Column (name = "no_ruj", length = 32)
	private String noRujukan;
	
	@ManyToOne
	@JoinColumn (name = "kod_bank")
	private KodBank bank;
	
	@Column (name = "bank_caw", length = 50)
	private String bankCawangan;
	
	@Column (name = "amaun", precision = 12, scale = 2, columnDefinition="NUMBER(12,2)")
	private BigDecimal amaun;
	
	@Column (name = "aktif", length = 1)
	private char aktif;
	
	@Column (name = "no_ruj_lulus")
	private String noRujukanKelulusan;
	
	@OneToMany (mappedBy = "caraBayaran")
	private List<SejarahDokumenKewanganBayaran> senaraiDokumenKewanganBayaran;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "dibatal")
	private Pengguna dibatalOleh;
	
	@Column (name = "trh_batal")
	private Date tarikhBatal;

        @Column (name = "trh_cek")
	private Date tarikhCek;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdCaraBayaran() {
		return idCaraBayaran;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public KodCaraBayaran getKodCaraBayaran() {
		return kodCaraBayaran;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public KodBank getBank() {
		return bank;
	}

	public String getBankCawangan() {
		return bankCawangan;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public char getAktif(){
		return aktif;
	}
	
	public String getNoRujukanKelulusan() {
		return noRujukanKelulusan;
	}

	public List<SejarahDokumenKewanganBayaran> getSenaraiDokumenKewanganBayaran() {
		return senaraiDokumenKewanganBayaran;
	}

	public Pengguna getDibatalOleh() {
		return dibatalOleh;
	}

	public Date getTarikhBatal() {
		return tarikhBatal;
	}

	public Date getTarikhCek() {
		return tarikhCek;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

}
