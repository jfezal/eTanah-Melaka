package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodUOM;
import etanah.model.Notis;
import etanah.model.PermohonanPihak;

@Entity
@Table (name = "mohon_bicara_hadir")
@SequenceGenerator(name = "seq_mohon_bicara_hadir", sequenceName = "seq_mohon_bicara_hadir")
public class PermohonanPerbicaraanKehadiran implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_bicara_hadir")
	@Column (name = "id_hadir")
	private long idKehadiran;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_bicara", nullable = false)
	private PermohonanPerbicaraan perbicaraan;
	
	/**
	 * Pihak yg terlibat sebagai pemilik.
	 */
	@ManyToOne
	@JoinColumn (name = "id_mp")
	private PermohonanPihak pihak;
	
	/**
	 * Maklumat kehadiran jika tidak terlibat dgn hakmilik dibicarakan
	 */
	@Column (name = "nama")
	private String nama;
	
	@ManyToOne
	@JoinColumn (name = "kod_pengenalan")
	private KodJenisPengenalan jenisPengenalan;
	
	@Column (name = "no_pengenalan")
	private String noPengenalan;
	
	@Column (name = "jawatan")
	private String jawatan;
	
	// penghantaran notis
	@ManyToOne
	@JoinColumn (name = "id_phantar_notis")
	private PenghantarNotis penghantarNotis;
	
	@ManyToOne
	@JoinColumn (name = "kod_hantar")
	private KodCaraPenghantaran caraPenghantaran;
	
	@Column (name = "trh_hantar")
	private Date tarikhHantar;
	
	// penerimaan notis kehadiran
	@ManyToOne
	@JoinColumn (name = "kod_sts_terima")
	private KodStatusTerima statusTerima;
	
	@Column (name = "trh_terima")
	private Date tarikhTerima;
	
	@Column (name = "terima_catatan")
	private String catatanTerima;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_bukti")
	private Dokumen buktiTerima;
		
	@Column (name = "hadir")
	private Character hadir;
	
	// wakil jika tidak hadir
	@ManyToOne
	@JoinColumn (name = "wakil_id_lantikan")
	private Dokumen wakilDokumenLantikan;
	
	@ManyToOne
	@JoinColumn (name = "wakil_kod_pengenalan")
	private KodJenisPengenalan wakilKodPengenalan;
	
	@Column (name = "wakil_no_pengenalan")
	private String wakilNoPengenalan;
	
	@Column (name = "wakil_nama")
	private String wakilNama;
		
	@Column (name = "keterangan")
	private String keterangan;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdKehadiran() {
		return idKehadiran;
	}

	public void setIdKehadiran(long idKehadiran) {
		this.idKehadiran = idKehadiran;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setPerbicaraan(PermohonanPerbicaraan perbicaraan) {
		this.perbicaraan = perbicaraan;
	}

	public PermohonanPerbicaraan getPerbicaraan() {
		return perbicaraan;
	}

	public PermohonanPihak getPihak() {
		return pihak;
	}

	public void setPihak(PermohonanPihak pihak) {
		this.pihak = pihak;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public KodJenisPengenalan getJenisPengenalan() {
		return jenisPengenalan;
	}

	public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
		this.jenisPengenalan = jenisPengenalan;
	}

	public String getNoPengenalan() {
		return noPengenalan;
	}

	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}

	public String getJawatan() {
		return jawatan;
	}

	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public PenghantarNotis getPenghantarNotis() {
		return penghantarNotis;
	}

	public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
		this.penghantarNotis = penghantarNotis;
	}

	public KodCaraPenghantaran getCaraPenghantaran() {
		return caraPenghantaran;
	}

	public void setCaraPenghantaran(KodCaraPenghantaran caraPenghantaran) {
		this.caraPenghantaran = caraPenghantaran;
	}

	public Date getTarikhHantar() {
		return tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}

	public KodStatusTerima getStatusTerima() {
		return statusTerima;
	}

	public void setStatusTerima(KodStatusTerima statusTerima) {
		this.statusTerima = statusTerima;
	}

	public Date getTarikhTerima() {
		return tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public String getCatatanTerima() {
		return catatanTerima;
	}

	public void setCatatanTerima(String catatanTerima) {
		this.catatanTerima = catatanTerima;
	}

	public Dokumen getBuktiTerima() {
		return buktiTerima;
	}

	public void setBuktiTerima(Dokumen buktiTerima) {
		this.buktiTerima = buktiTerima;
	}

	public Character getHadir() {
		return hadir;
	}

	public void setHadir(Character hadir) {
		this.hadir = hadir;
	}

	public Dokumen getWakilDokumenLantikan() {
		return wakilDokumenLantikan;
	}

	public void setWakilDokumenLantikan(Dokumen wakilDokumenLantikan) {
		this.wakilDokumenLantikan = wakilDokumenLantikan;
	}

	public KodJenisPengenalan getWakilKodPengenalan() {
		return wakilKodPengenalan;
	}

	public void setWakilKodPengenalan(KodJenisPengenalan wakilKodPengenalan) {
		this.wakilKodPengenalan = wakilKodPengenalan;
	}

	public String getWakilNoPengenalan() {
		return wakilNoPengenalan;
	}

	public void setWakilNoPengenalan(String wakilNoPengenalan) {
		this.wakilNoPengenalan = wakilNoPengenalan;
	}

	public String getWakilNama() {
		return wakilNama;
	}

	public void setWakilNama(String wakilNama) {
		this.wakilNama = wakilNama;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
