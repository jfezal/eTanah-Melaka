package etanah.model.ambil;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;

@Entity
@Table (name = "ambil_hadir_nilai")
@SequenceGenerator(name = "seq_ambil_hadir_nilai", sequenceName = "seq_ambil_hadir_nilai")
public class Penilaian implements Serializable {
	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ambil_hadir_nilai")
	@Column (name = "id_nilai")
	private long idPenilaian;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_hadir", nullable = false)
	private PerbicaraanKehadiran kehadiran;
	
	@Column (name = "jenis")
	private char jenis;
	
	@Column (name = "item", nullable = false)
	private String item;
	
	@Column (name = "amaun", nullable = false)
	private BigDecimal amaun;
	
	@ManyToOne
	@JoinColumn (name = "kod_uom")
	private KodUOM perUnit;
	
	@Column (name = "ulasan")
	private String ulasan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdPenilaian() {
		return idPenilaian;
	}

	public void setIdPenilaian(long idPenilaian) {
		this.idPenilaian = idPenilaian;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public PerbicaraanKehadiran getKehadiran() {
		return kehadiran;
	}

	public void setKehadiran(PerbicaraanKehadiran kehadiran) {
		this.kehadiran = kehadiran;
	}

	public void setJenis(char jenis) {
		this.jenis = jenis;
	}

	public char getJenis() {
		return jenis;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public BigDecimal getAmaun() {
		return amaun;
	}

	public void setAmaun(BigDecimal amaun) {
		this.amaun = amaun;
	}

	public KodUOM getPerUnit() {
		return perUnit;
	}

	public void setPerUnit(KodUOM perUnit) {
		this.perUnit = perUnit;
	}

	public String getUlasan() {
		return ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
