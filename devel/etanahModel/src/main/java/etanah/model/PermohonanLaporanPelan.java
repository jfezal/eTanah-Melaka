package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_lapor_pelan")
@SequenceGenerator(name = "seq_mohon_lapor_pelan", sequenceName = "seq_mohon_lapor_pelan")
public class PermohonanLaporanPelan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_pelan")
	@Column (name = "id_lapor")
	private long idLaporan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;

	@ManyToOne
	@JoinColumn (name = "kod_tanah")
	private KodTanah kodTanah;


	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	@Column (name = "no_litho")
	private String noLitho;
	
	@Column (name = "projek_kjaan")
	private String projekKerajaan;
	
	@Column (name = "catatan")
	private String catatan;

	@Column (name = "syor")
	private String syor;

	@Column (name = "no_warta")
	private String noWarta;

	@Column (name = "kws_pkuasa")
	private String kawasanPihakBerkuasa;

	@Column (name = "no_warta_pkuasa")
	private String noWartaPihakBerkuasa;

	@Column (name = "nama_pkuasa")
	private String namaPihakBerkuasa;
        
        
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "kod_kwsn")
        private KodBandarPekanMukim kawasan;        
        
	
        
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mh", nullable = false)
	private HakmilikPermohonan hakmilikPermohonan;
	
        
	@Embedded
	private InfoAudit infoAudit;

	public long getIdLaporan() {
		return idLaporan;
	}

	public void setIdLaporan(long idLaporan) {
		this.idLaporan = idLaporan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public void setNoLitho(String noLitho) {
		this.noLitho = noLitho;
	}

	public String getNoLitho() {
		return noLitho;
	}

	public String getProjekKerajaan() {
		return projekKerajaan;
	}

	public void setProjekKerajaan(String projekKerajaan) {
		this.projekKerajaan = projekKerajaan;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getKawasanPihakBerkuasa() {
        return kawasanPihakBerkuasa;
    }

    public void setKawasanPihakBerkuasa(String kawasanPihakBerkuasa) {
        this.kawasanPihakBerkuasa = kawasanPihakBerkuasa;
    }

    public KodTanah getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(KodTanah kodTanah) {
        this.kodTanah = kodTanah;
    }

    public String getNamaPihakBerkuasa() {
        return namaPihakBerkuasa;
    }

    public void setNamaPihakBerkuasa(String namaPihakBerkuasa) {
        this.namaPihakBerkuasa = namaPihakBerkuasa;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getNoWartaPihakBerkuasa() {
        return noWartaPihakBerkuasa;
    }

    public void setNoWartaPihakBerkuasa(String noWartaPihakBerkuasa) {
        this.noWartaPihakBerkuasa = noWartaPihakBerkuasa;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    
    
    
    
	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public KodBandarPekanMukim getKawasan() {
        return kawasan;
    }

    public void setKawasan(KodBandarPekanMukim kawasan) {
        this.kawasan = kawasan;
    }

   
}
