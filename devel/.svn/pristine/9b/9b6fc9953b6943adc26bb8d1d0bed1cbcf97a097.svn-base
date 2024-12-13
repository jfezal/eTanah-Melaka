package etanah.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "bngn_tgkt")
@SequenceGenerator (name = "seq_bngn_tgkt", sequenceName = "seq_bngn_tgkt")
public class BangunanTingkat implements Serializable {
	
	@Id
        @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_bngn_tgkt")
	@Column (name = "id_tgkt")
	private long idTingkat;
	
	@ManyToOne
	@JoinColumn (name = "id_bngn", nullable = false)
	private PermohonanBangunan bangunan;
	
	@Column (name = "nama")
	private String nama;
	
	@Column (name = "tgkt", nullable = false)
	private int tingkat;
	
	@Column (name = "bil_petak", nullable = false)
	private int bilanganPetak;

    @Column (name = "bil_petak_aksr", nullable = true)
    private Integer bilanganPetakAksesori;
	
	@OneToMany (mappedBy = "tingkat")
        @OrderBy ("nama")
	private List<BangunanPetak> senaraiPetak;
        
        
        @Column (name = "mezanin")
	private String mezanin;
        
        @Column (name = "lain")
	private String lain;
	
	@Embedded
	private InfoAudit infoAudit;

	public long getIdTingkat() {
		return idTingkat;
	}

	public void setIdTingkat(long idTingkat) {
		this.idTingkat = idTingkat;
	}

	public PermohonanBangunan getBangunan() {
		return bangunan;
	}

	public void setBangunan(PermohonanBangunan bangunan) {
		this.bangunan = bangunan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getTingkat() {
		return tingkat;
	}

	public void setTingkat(int tingkat) {
		this.tingkat = tingkat;
	}

	public int getBilanganPetak() {
		return bilanganPetak;
	}

	public void setBilanganPetak(int bilanganPetak) {
		this.bilanganPetak = bilanganPetak;
	}

	public void setSenaraiPetak(List<BangunanPetak> senaraiPetak) {
		this.senaraiPetak = senaraiPetak;
	}

	public List<BangunanPetak> getSenaraiPetak() {
		return senaraiPetak;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public Integer getBilanganPetakAksesori() {
        return bilanganPetakAksesori;
    }

    public void setBilanganPetakAksesori(Integer bilanganPetakAksesori) {
        this.bilanganPetakAksesori = bilanganPetakAksesori;
    }

    public String getMezanin() {
        return mezanin;
    }

    public void setMezanin(String mezanin) {
        this.mezanin = mezanin;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }
}
