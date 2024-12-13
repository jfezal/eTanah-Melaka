package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "cara_bayar")
@SequenceGenerator (name = "seq_cara_bayar", sequenceName = "seq_cara_bayar")
public class CaraBayaran implements Serializable {

	@Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_cara_bayar")
	@Column (name = "id_cara_bayar")
	private long idCaraBayaran;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw")
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "kod_cara_bayar")
	private KodCaraBayaran kodCaraBayaran; 
	
	@Column (name = "no_ruj", length = 32)
	private String noRujukan;
        
        @Column (name = "no_akaun_cek", length = 32)
	private String noAkaunCek;
	
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
	
	@OneToMany (mappedBy = "caraBayaran", cascade = CascadeType.ALL)
	private List<DokumenKewanganBayaran> senaraiDokumenKewanganBayaran;
	
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

	public void setIdCaraBayaran(long idCaraBayaran) {
		this.idCaraBayaran = idCaraBayaran;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCaraBayaran getKodCaraBayaran() {
		return kodCaraBayaran;
	}

	public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
		this.kodCaraBayaran = kodCaraBayaran;
	}

	public String getNoRujukan() {
		return noRujukan;
	}

	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}

	public KodBank getBank() {
		return bank;
	}

	public void setBank(KodBank bank) {
		this.bank = bank;
	}

	public void setBankCawangan(String bankCawangan) {
		this.bankCawangan = bankCawangan;
	}

	public String getBankCawangan() {
		return bankCawangan;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}
	
	public char getAktif(){
		return aktif;
	}
	
	public void setAktif(char aktif){
		this.aktif = aktif;
	}

	public void setNoRujukanKelulusan(String noRujukanKelulusan) {
		this.noRujukanKelulusan = noRujukanKelulusan;
	}

	public String getNoRujukanKelulusan() {
		return noRujukanKelulusan;
	}

	public void setSenaraiDokumenKewanganBayaran(
			List<DokumenKewanganBayaran> senaraiDokumenKewanganBayaran) {
		this.senaraiDokumenKewanganBayaran = senaraiDokumenKewanganBayaran;
	}

	public List<DokumenKewanganBayaran> getSenaraiDokumenKewanganBayaran() {
		return senaraiDokumenKewanganBayaran;
	}

	public void setDibatalOleh(Pengguna dibatalOleh) {
		this.dibatalOleh = dibatalOleh;
	}

	public Pengguna getDibatalOleh() {
		return dibatalOleh;
	}

	public void setTarikhBatal(Date tarikhBatal) {
		this.tarikhBatal = tarikhBatal;
	}

	public Date getTarikhBatal() {
		return tarikhBatal;
	}

	public void setTarikhCek(Date tarikhCek) {
		this.tarikhCek = tarikhCek;
	}

	public Date getTarikhCek() {
		return tarikhCek;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public String getNoAkaunCek() {
        return noAkaunCek;
    }

    public void setNoAkaunCek(String noAkaunCek) {
        this.noAkaunCek = noAkaunCek;
    }
	
}
