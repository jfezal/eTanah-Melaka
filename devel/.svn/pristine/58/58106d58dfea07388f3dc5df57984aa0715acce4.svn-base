package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_lapor_imej")
@SequenceGenerator(name = "seq_mohon_lapor_imej", sequenceName = "seq_mohon_lapor_imej")
public class ImejLaporan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_imej")
	@Column (name = "id_imej")
	private long idImej;

	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "id_lapor", nullable = false)
	private LaporanTanah laporanTanah;

	@OneToOne
	@JoinColumn (name = "id_dokumen", nullable = false)
	private Dokumen dokumen;

	@OneToOne
	@JoinColumn (name = "id_hakmilik")
	private Hakmilik hakmilik;

	@Column (name = "pndgn_imej")
	private char pandanganImej;

	@Column (name = "catatan")
	private String catatan;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_lapor_tanah_spdn" )
	private LaporanTanahSempadan laporanTanahSempadan;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdImej() {
		return idImej;
	}

	public void setIdImej(long idImej) {
		this.idImej = idImej;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public LaporanTanah getLaporanTanah() {
		return laporanTanah;
	}

	public void setLaporanTanah(LaporanTanah laporanTanah) {
		this.laporanTanah = laporanTanah;
	}

	public Dokumen getDokumen() {
		return dokumen;
	}

	public void setDokumen(Dokumen dokumen) {
		this.dokumen = dokumen;
	}

	public Hakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(Hakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public char getPandanganImej() {
		return pandanganImej;
	}

	public void setPandanganImej(char pandanganImej) {
		this.pandanganImej = pandanganImej;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

    public LaporanTanahSempadan getLaporanTanahSempadan() {
        return laporanTanahSempadan;
    }

    public void setLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        this.laporanTanahSempadan = laporanTanahSempadan;
    }



	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
