package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "pemohon_tanah")
@SequenceGenerator(name = "seq_pemohon_tanah", sequenceName = "seq_pemohon_tanah")
public class PemohonTanah implements Serializable {

	@Id
	@Column (name = "id_tanah")
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_pemohon_tanah")
	private long idTanah;
	
	@ManyToOne
	@JoinColumn(name = "id_pemohon", nullable = false)
	private Pemohon pemohon;
	
	@ManyToOne
	@JoinColumn (name = "kod_negeri", nullable = false)
	private KodNegeri negeri;
	
	@Column (name = "daerah", nullable = false)
	private String daerah;
	
	@Column (name = "bpm", nullable = false)
	private String bandarPekanMukim;
	
	@Column (name = "no_lot")
	private String noLot;
	
	@Column (name = "geran")
	private String jenisGeran;
	
	@Column (name = "luas")
	private BigDecimal luas;

    @ManyToOne
    @JoinColumn(name = "luas_uom")
    private KodUOM luasUom;

	@Column (name = "usaha")
	private String pengusahaan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdTanah() {
		return idTanah;
	}

	public void setIdTanah(long idTanah) {
		this.idTanah = idTanah;
	}

	public Pemohon getPemohon() {
		return pemohon;
	}

	public void setPemohon(Pemohon pemohon) {
		this.pemohon = pemohon;
	}

	public KodNegeri getNegeri() {
		return negeri;
	}

	public void setNegeri(KodNegeri negeri) {
		this.negeri = negeri;
	}

	public String getDaerah() {
		return daerah;
	}

	public void setDaerah(String daerah) {
		this.daerah = daerah;
	}

	public String getBandarPekanMukim() {
		return bandarPekanMukim;
	}

	public void setBandarPekanMukim(String bandarPekanMukim) {
		this.bandarPekanMukim = bandarPekanMukim;
	}

	public String getNoLot() {
		return noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
	}

	public String getJenisGeran() {
		return jenisGeran;
	}

	public void setJenisGeran(String jenisGeran) {
		this.jenisGeran = jenisGeran;
	}

	public BigDecimal getLuas() {
		return luas;
	}

	public void setLuas(BigDecimal luas) {
		this.luas = luas;
	}

    public KodUOM getLuasUom() {
        return luasUom;
    }

    public void setLuasUom(KodUOM luasUom) {
        this.luasUom = luasUom;
    }

	public String getPengusahaan() {
		return pengusahaan;
	}

	public void setPengusahaan(String pengusahaan) {
		this.pengusahaan = pengusahaan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
