package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_strata_imej")
@SequenceGenerator(name = "seq_mohon_strata_imej", sequenceName = "seq_mohon_strata_imej")
public class ImejLaporanStrata implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_strata_imej")
	@Column (name = "id_imej")
	private long idImej;

	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

    @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_strata", nullable = false)
	private PermohonanStrata permohonanStrata;

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

	public PermohonanStrata getPermohonanStrata() {
		return permohonanStrata;
	}

	public void setPermohonanStrata(PermohonanStrata permohonanStrata) {
		this.permohonanStrata = permohonanStrata;
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

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

}
