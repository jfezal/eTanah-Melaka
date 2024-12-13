package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kkuasa_op")
@SequenceGenerator(name = "seq_op_kkuasa", sequenceName = "seq_op_kkuasa")
public class OperasiPenguatkuasaan implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_op_kkuasa")
        @Column (name = "id_op")
	private long idOperasi;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_mohon", nullable = false)
	private Permohonan permohonan;
	
	/**
	 * Either lokasiAduan or lokasi need to be filled.
	 */
	@ManyToOne
	@JoinColumn (name = "id_aduan_lokasi")
	private AduanLokasi lokasiAduan;
	
	@Column (name = "lokasi")
	private String lokasi;
	
	@Column (name = "trh_op")
	private Date tarikhOperasi;

	@Column (name = "trh_tahan")
	private Date tarikhPenahanan;
	
	@Column (name = "catatan")
	private String catatan;
	
	// TEMPAT BERKUMPUL 
	@Column (name = "tmpt_kumpul")
	private String tempatBerkumpul;
	
	@Column (name = "trh_kumpul")
	private Date tarikhBerkumpul;
	
	@OneToMany (mappedBy = "operasi")
	private List<OperasiAgensi> senaraiAgensi;
	
	@OneToMany (mappedBy = "operasi")
	private List<BarangRampasan> senaraiBarangRampasan;
        
        @OneToMany (mappedBy = "operasiPenguatkuasaan")
	private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;  
        
        @OneToMany (mappedBy = "operasiPenguatkuasaan")
	private List<PermohonanSaksi> senaraiPermohonanSaksi;  
        
        @OneToMany (mappedBy = "operasiPenguatkuasaan")
	private List<PegawaiPenyiasat> senaraiPegawaiPenyiasat;  
        
        @OneToMany(mappedBy="operasi")
        private List<PenyerahBarangOperasi> senaraiPenyerahBarangOperasi;
        
          @OneToMany(mappedBy="operasi")
        private List<OperasiBarangPenguatkuasaan> senaraiBarangOperasi;

	@Column (name = "jns_tindakan")
	private String jenisTindakan;
	@Column (name = "kenderaan")
	private String kenderaan;

	@Column (name = "jumlah_tangkap")
	private Integer jumlahTangkapan;

	@Column (name = "disah")
	private String diSah;

	@Column (name = "trh_sah")
	private Date tarikhSah;

	@Column (name = "nilai_kecurian")
	private BigDecimal nilaiKecurian;

        @Column (name = "katg_tindakan")
	private String kategoriTindakan;
        
        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "jns_tangkap_uom", nullable = false)
	private KodUOM jenisTangkapan;

	@Embedded
	private InfoAudit infoAudit;

	public long getIdOperasi() {
		return idOperasi;
	}

	public void setIdOperasi(long idOperasi) {
		this.idOperasi = idOperasi;
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

	public AduanLokasi getLokasiAduan() {
		return lokasiAduan;
	}

	public void setLokasiAduan(AduanLokasi lokasiAduan) {
		this.lokasiAduan = lokasiAduan;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

    public Date getTarikhPenahanan() {
        return tarikhPenahanan;
    }

    public void setTarikhPenahanan(Date tarikhPenahanan) {
        this.tarikhPenahanan = tarikhPenahanan;
    }

	public Date getTarikhOperasi() {
		return tarikhOperasi;
	}

	public void setTarikhOperasi(Date tarikhOperasi) {
		this.tarikhOperasi = tarikhOperasi;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public String getTempatBerkumpul() {
		return tempatBerkumpul;
	}

	public void setTempatBerkumpul(String tempatBerkumpul) {
		this.tempatBerkumpul = tempatBerkumpul;
	}

	public Date getTarikhBerkumpul() {
		return tarikhBerkumpul;
	}

	public void setTarikhBerkumpul(Date tarikhBerkumpul) {
		this.tarikhBerkumpul = tarikhBerkumpul;
	}

    public String getDiSah() {
        return diSah;
    }

    public void setDiSah(String diSah) {
        this.diSah = diSah;
    }

    public String getJenisTindakan() {
        return jenisTindakan;
    }

    public void setJenisTindakan(String jenisTindakan) {
        this.jenisTindakan = jenisTindakan;
    }

    public String getKenderaan() {
        return kenderaan;
    }

    public void setKenderaan(String kenderaan) {
        this.kenderaan = kenderaan;
    }

    public Integer getJumlahTangkapan() {
        return jumlahTangkapan;
    }

    public void setJumlahTangkapan(Integer jumlahTangkapan) {
        this.jumlahTangkapan = jumlahTangkapan;
    }

    public Date getTarikhSah() {
        return tarikhSah;
    }

    public void setTarikhSah(Date tarikhSah) {
        this.tarikhSah = tarikhSah;
    }

    public BigDecimal getNilaiKecurian() {
        return nilaiKecurian;
    }

    public void setNilaiKecurian(BigDecimal nilaiKecurian) {
        this.nilaiKecurian = nilaiKecurian;
    }



	public void setSenaraiAgensi(List<OperasiAgensi> senaraiAgensi) {
		this.senaraiAgensi = senaraiAgensi;
	}

    public String getKategoriTindakan() {
        return kategoriTindakan;
    }

    public void setKategoriTindakan(String kategoriTindakan) {
        this.kategoriTindakan = kategoriTindakan;
    }

    public List<AduanOrangKenaSyak> getSenaraiAduanOrangKenaSyak() {
        return senaraiAduanOrangKenaSyak;
    }

    public void setSenaraiAduanOrangKenaSyak(List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak) {
        this.senaraiAduanOrangKenaSyak = senaraiAduanOrangKenaSyak;
    }
	public List<OperasiAgensi> getSenaraiAgensi() {
		return senaraiAgensi;
	}

	public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
		this.senaraiBarangRampasan = senaraiBarangRampasan;
	}

	public List<BarangRampasan> getSenaraiBarangRampasan() {
		return senaraiBarangRampasan;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

    public List<PermohonanSaksi> getSenaraiPermohonanSaksi() {
        return senaraiPermohonanSaksi;
    }

    public void setSenaraiPermohonanSaksi(List<PermohonanSaksi> senaraiPermohonanSaksi) {
        this.senaraiPermohonanSaksi = senaraiPermohonanSaksi;
    }

    public List<PegawaiPenyiasat> getSenaraiPegawaiPenyiasat() {
        return senaraiPegawaiPenyiasat;
    }

    public void setSenaraiPegawaiPenyiasat(List<PegawaiPenyiasat> senaraiPegawaiPenyiasat) {
        this.senaraiPegawaiPenyiasat = senaraiPegawaiPenyiasat;
    }

    public List<PenyerahBarangOperasi> getSenaraiPenyerahBarangOperasi() {
        return senaraiPenyerahBarangOperasi;
    }

    public void setSenaraiPenyerahBarangOperasi(List<PenyerahBarangOperasi> senaraiPenyerahBarangOperasi) {
        this.senaraiPenyerahBarangOperasi = senaraiPenyerahBarangOperasi;
    }

    public List<OperasiBarangPenguatkuasaan> getSenaraiBarangOperasi() {
        return senaraiBarangOperasi;
    }

    public void setSenaraiBarangOperasi(List<OperasiBarangPenguatkuasaan> senaraiBarangOperasi) {
        this.senaraiBarangOperasi = senaraiBarangOperasi;
    }

    public KodUOM getJenisTangkapan() {
        return jenisTangkapan;
    }

    public void setJenisTangkapan(KodUOM jenisTangkapan) {
        this.jenisTangkapan = jenisTangkapan;
    }

}
