package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "mohon_lapor_pduduk")
@SequenceGenerator(name = "seq_mohon_lapor_pduduk", sequenceName = "seq_mohon_lapor_pduduk")
public class PermohonanLaporanPendudukan implements Serializable {

	@Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_lapor_pduduk")
	@Column (name = "id_lapor_pduduk")
	private long idLaporan;
	
	@ManyToOne
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne
	@JoinColumn (name = "id_mh", nullable = false)
	private HakmilikPermohonan permohonanHakmilik;

	@ManyToOne
	@JoinColumn (name = "jenis_pduduk")
	private KodJenisPendudukan jenisPendudukan;
	
	@Column (name = "bil_bngn")
	private Integer bilBangunan;
	
	@Column (name = "bil_klga")
	private Integer bilKeluarga;
	
	@Column (name = "catatan")
	private String catatan;
	@Column (name = "nama")
	private String nama;
	@Column (name = "alamat1")
	private String alamat1;
	@Column (name = "alamat2")
	private String alamat2;
	@Column (name = "alamat3")
	private String alamat3;
	@Column (name = "alamat4")
	private String alamat4;
	@Column (name = "poskod")
	private String poskod;


	@ManyToOne
	@JoinColumn (name = "kod_negeri")
	private KodNegeri kodNegeri;


	@Column (name = "luas")
	private BigDecimal luas;

	@ManyToOne
	@JoinColumn (name = "luas_uom")
	private KodUOM luasUom;
        
    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }


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

    public Integer getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(Integer bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public Integer getBilKeluarga() {
        return bilKeluarga;
    }

    public void setBilKeluarga(Integer bilKeluarga) {
        this.bilKeluarga = bilKeluarga;
    }

    public KodJenisPendudukan getJenisPendudukan() {
        return jenisPendudukan;
    }

    public void setJenisPendudukan(KodJenisPendudukan jenisPendudukan) {
        this.jenisPendudukan = jenisPendudukan;
    }

    public HakmilikPermohonan getPermohonanHakmilik() {
        return permohonanHakmilik;
    }

    public void setPermohonanHakmilik(HakmilikPermohonan permohonanHakmilik) {
        this.permohonanHakmilik = permohonanHakmilik;
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
