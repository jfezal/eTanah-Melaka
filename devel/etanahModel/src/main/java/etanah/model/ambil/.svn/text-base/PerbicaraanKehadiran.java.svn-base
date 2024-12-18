package etanah.model.ambil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Notis;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.model.PermohonanPihakWakil;
import etanah.model.WakilPihak;

@Entity
@Table (name = "ambil_bicara_hadir")
@SequenceGenerator(name = "seq_ambil_bicara_hadir", sequenceName = "seq_ambil_bicara_hadir")
public class PerbicaraanKehadiran implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ambil_bicara_hadir")
	@Column (name = "id_hadir")
	private long idKehadiran;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_bicara", nullable = false)
	private HakmilikPerbicaraan perbicaraan;
	
	/**
	 * Pihak yg terlibat sebagai pemilik.
	 */
	@ManyToOne
	@JoinColumn (name = "id_mp")
	private PermohonanPihak pihak;
        
        @ManyToOne
	@JoinColumn (name = "ID_MPW")
	private PermohonanPihakWakil wakilPermohonanPihak;
	
	/**
	 * Maklumat kahadiran jika tidak terlibat dgn hakmilik dibicarakan
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
	
	@Column (name = "penilai_trh")
	private Date penilaiTarikh;

	@Column (name = "penilai_ulasan")
	private String penilaiUlasan;
	
	@Column (name = "penilai_catatan")
	private String penilaiCatatan;
	
	@OneToMany (mappedBy = "kehadiran", fetch = FetchType.LAZY)
	private List<Penilaian> senaraiPenilaian;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_notis")
	private Notis notis;
	
	@Column (name = "hadir")
	private Character hadir;
	
	@Column (name = "keterangan")
	private String keterangan;

	@Column (name = "catatan")
	private String catatan;

	@Column (name = "tempoh_pengosongan")
	private Integer tempohPengosongan;
	
        
    @Column(name = "keterangan_penuntut")
    private String keteranganPenuntut;

    
    @Column(name = "penuntut")
    private String penuntut;
    
    @Column(name = "surat_alamat1")
    private String suratAlamat1;
    
    @Column(name = "surat_alamat2")
    private String suratAlamat2;
    
    @Column(name = "surat_alamat3")
    private String suratAlamat3;
    
    @Column(name = "surat_alamat4")
    private String suratAlamat4;
    
    @Column(name = "surat_poskod")
    private String suratPoskod;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surat_kod_negeri")
    private KodNegeri kodNegeri;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mpt")
    private PermohonanPihakTidakBerkepentingan permohonanPihakTidakBerkepentingan;
    
    @Column(name = "no_tel1")
    private String nomborTalipon1;
    
    @Column(name = "no_tel2")
    private String nomborTalipon2;
    
    @Column(name = "no_fax")
    private String nomborFax;
    
    @Column(name = "emel")
    private String emel;
    
    @Column(name = "bantah_elektrik")
    private String bantahElektrik;
    
    @Column(name = "alamat_elektrik")
    private String alamatElektrik;
    
    @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_wakil")
	private WakilPihak wakilPihak;
        
        
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

	public HakmilikPerbicaraan getPerbicaraan() {
		return perbicaraan;
	}

	public void setPerbicaraan(HakmilikPerbicaraan perbicaraan) {
		this.perbicaraan = perbicaraan;
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

	public Date getPenilaiTarikh() {
		return penilaiTarikh;
	}

	public void setPenilaiTarikh(Date penilaiTarikh) {
		this.penilaiTarikh = penilaiTarikh;
	}

    public String getPenilaiUlasan() {
        return penilaiUlasan;
    }

    public void setPenilaiUlasan(String penilaiUlasan) {
        this.penilaiUlasan = penilaiUlasan;
    }

	public String getPenilaiCatatan() {
		return penilaiCatatan;
	}

	public void setPenilaiCatatan(String penilaiCatatan) {
		this.penilaiCatatan = penilaiCatatan;
	}

    public Integer getTempohPengosongan() {
        return tempohPengosongan;
    }

    public void setTempohPengosongan(Integer tempohPengosongan) {
        this.tempohPengosongan = tempohPengosongan;
    }

  


	public void setSenaraiPenilaian(List<Penilaian> senaraiPenilaian) {
		this.senaraiPenilaian = senaraiPenilaian;
	}

	public List<Penilaian> getSenaraiPenilaian() {
		return senaraiPenilaian;
	}

	public void setNotis(Notis notis) {
		this.notis = notis;
	}

	public Notis getNotis() {
		return notis;
	}

	public Character getHadir() {
		return hadir;
	}

	public void setHadir(Character hadir) {
		this.hadir = hadir;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public String getKeteranganPenuntut() {
        return keteranganPenuntut;
    }

    public void setKeteranganPenuntut(String keteranganPenuntut) {
        this.keteranganPenuntut = keteranganPenuntut;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNomborFax() {
        return nomborFax;
    }

    public void setNomborFax(String nomborFax) {
        this.nomborFax = nomborFax;
    }

    public String getNomborTalipon1() {
        return nomborTalipon1;
    }

    public void setNomborTalipon1(String nomborTalipon1) {
        this.nomborTalipon1 = nomborTalipon1;
    }

    public String getNomborTalipon2() {
        return nomborTalipon2;
    }

    public void setNomborTalipon2(String nomborTalipon2) {
        this.nomborTalipon2 = nomborTalipon2;
    }

    public String getPenuntut() {
        return penuntut;
    }

    public void setPenuntut(String penuntut) {
        this.penuntut = penuntut;
    }

    public String getSuratAlamat1() {
        return suratAlamat1;
    }

    public void setSuratAlamat1(String suratAlamat1) {
        this.suratAlamat1 = suratAlamat1;
    }

    public String getSuratAlamat2() {
        return suratAlamat2;
    }

    public void setSuratAlamat2(String suratAlamat2) {
        this.suratAlamat2 = suratAlamat2;
    }

    public String getSuratAlamat3() {
        return suratAlamat3;
    }

    public void setSuratAlamat3(String suratAlamat3) {
        this.suratAlamat3 = suratAlamat3;
    }

    public String getSuratAlamat4() {
        return suratAlamat4;
    }

    public void setSuratAlamat4(String suratAlamat4) {
        this.suratAlamat4 = suratAlamat4;
    }

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public PermohonanPihakTidakBerkepentingan getPermohonanPihakTidakBerkepentingan() {
        return permohonanPihakTidakBerkepentingan;
    }

    public void setPermohonanPihakTidakBerkepentingan(PermohonanPihakTidakBerkepentingan permohonanPihakTidakBerkepentingan) {
        this.permohonanPihakTidakBerkepentingan = permohonanPihakTidakBerkepentingan;
    }

    public PermohonanPihakWakil getWakilPermohonanPihak() {
        return wakilPermohonanPihak;
    }

    public void setWakilPermohonanPihak(PermohonanPihakWakil wakilPermohonanPihak) {
        this.wakilPermohonanPihak = wakilPermohonanPihak;
    }

    public String getAlamatElektrik() {
        return alamatElektrik;
    }

    public void setAlamatElektrik(String alamatElektrik) {
        this.alamatElektrik = alamatElektrik;
    }

    public String getBantahElektrik() {
        return bantahElektrik;
    }

    public void setBantahElektrik(String bantahElektrik) {
        this.bantahElektrik = bantahElektrik;
    }

    public WakilPihak getWakilPihak() {
        return wakilPihak;
    }

    public void setWakilPihak(WakilPihak wakilPihak) {
        this.wakilPihak = wakilPihak;
    }
    
	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}
	
}
