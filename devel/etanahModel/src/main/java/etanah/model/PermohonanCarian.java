package etanah.model;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "carian")
public class PermohonanCarian implements Serializable {

	@Id
	@Column (name = "id_carian")
	private String idCarian;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_caw", nullable = false)
	private KodCawangan cawangan;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "kod_urusan", nullable = false)
	private KodUrusan urusan;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_kew_dok")
	private DokumenKewangan resit;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_trans")
	private Transaksi trans;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_trans_pengecualian")
	private Transaksi transPengecualian;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_trans_pelepasan")
	private Transaksi transPelepasan;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_trans_notis")
	private Transaksi transNotis;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_trans_fail")
	private Transaksi transFail;

        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "id_trans_lain")
	private Transaksi transLain;

        @ManyToOne (fetch = FetchType.LAZY)
        @JoinColumn (name = "id_folder")
        private FolderDokumen folderDokumen;

	@Column (name = "catatan")
	private String catatan;
	
	@Column (name = "id_penyerah")
	private Integer idPenyerah;
	
	@Column (name = "penyerah_nama")
	private String penyerahNama;
	
	@Column (name = "penyerah_alamat1")
	private String penyerahAlamat1;
	
	@Column (name = "penyerah_alamat2")
	private String penyerahAlamat2;

	@Column (name = "penyerah_alamat3")
	private String penyerahAlamat3;
	
	@Column (name = "penyerah_alamat4")
	private String penyerahAlamat4;

        @Column (name = "penyerah_poskod")
	private String penyerahPoskod;
		
	@ManyToOne
	@JoinColumn (name = "penyerah_kod_negeri")
	private KodNegeri penyerahNegeri;
	
	@ManyToOne
	@JoinColumn (name = "disah")
	private Pengguna disahOleh;
	
	@Column (name = "trh_sah")
	private Date tarikhDisah;
	
	@Column (name = "trh_kutip")
	private Date tarikhKutip;
	
	@OneToMany (mappedBy = "permohonanCarian", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CarianHakmilik> senaraiHakmilik;
	
	@Embedded
	private InfoAudit infoAudit;

	public String getIdCarian() {
		return idCarian;
	}

	public void setIdCarian(String idCarian) {
		this.idCarian = idCarian;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodUrusan getUrusan() {
		return urusan;
	}

	public void setUrusan(KodUrusan urusan) {
		this.urusan = urusan;
	}

	public DokumenKewangan getResit() {
		return resit;
	}

	public void setResit(DokumenKewangan resit) {
		this.resit = resit;
	}

        public FolderDokumen getFolderDokumen() {
            return folderDokumen;
        }

        public void setFolderDokumen(FolderDokumen folderDokumen) {
            this.folderDokumen = folderDokumen;
        }

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Integer getIdPenyerah() {
		return idPenyerah;
	}

	public void setIdPenyerah(Integer idPenyerah) {
		this.idPenyerah = idPenyerah;
	}

	public String getPenyerahNama() {
		return penyerahNama;
	}

	public void setPenyerahNama(String penyerahNama) {
		this.penyerahNama = penyerahNama;
	}

	public String getPenyerahAlamat1() {
		return penyerahAlamat1;
	}

	public void setPenyerahAlamat1(String penyerahAlamat1) {
		this.penyerahAlamat1 = penyerahAlamat1;
	}

	public String getPenyerahAlamat2() {
		return penyerahAlamat2;
	}

	public void setPenyerahAlamat2(String penyerahAlamat2) {
		this.penyerahAlamat2 = penyerahAlamat2;
	}

	public String getPenyerahAlamat3() {
		return penyerahAlamat3;
	}

	public void setPenyerahAlamat3(String penyerahAlamat3) {
		this.penyerahAlamat3 = penyerahAlamat3;
	}

	public String getPenyerahAlamat4() {
		return penyerahAlamat4;
	}

	public void setPenyerahAlamat4(String penyerahAlamat4) {
		this.penyerahAlamat4 = penyerahAlamat4;
	}

	public KodNegeri getPenyerahNegeri() {
		return penyerahNegeri;
	}

	public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
		this.penyerahNegeri = penyerahNegeri;
	}

	public Pengguna getDisahOleh() {
		return disahOleh;
	}

	public void setDisahOleh(Pengguna disahOleh) {
		this.disahOleh = disahOleh;
	}

	public Date getTarikhDisah() {
		return tarikhDisah;
	}

	public void setTarikhDisah(Date tarikhDisah) {
		this.tarikhDisah = tarikhDisah;
	}

	public Date getTarikhKutip() {
		return tarikhKutip;
	}

	public void setTarikhKutip(Date tarikhKutip) {
		this.tarikhKutip = tarikhKutip;
	}

	public List<CarianHakmilik> getSenaraiHakmilik() {
		return senaraiHakmilik;
	}

	public void setSenaraiHakmilik(List<CarianHakmilik> senaraiHakmilik) {
		this.senaraiHakmilik = senaraiHakmilik;
	}

	public InfoAudit getInfoAudit() {
		return infoAudit;
	}

	public void setInfoAudit(InfoAudit infoAudit) {
		this.infoAudit = infoAudit;
	}

        public Transaksi getTrans() {
            return trans;
        }

        public void setTrans(Transaksi trans) {
            this.trans = trans;
        }

    public Transaksi getTransFail() {
        return transFail;
    }

    public void setTransFail(Transaksi transFail) {
        this.transFail = transFail;
    }

    public Transaksi getTransLain() {
        return transLain;
    }

    public void setTransLain(Transaksi transLain) {
        this.transLain = transLain;
    }

    public Transaksi getTransNotis() {
        return transNotis;
    }

    public void setTransNotis(Transaksi transNotis) {
        this.transNotis = transNotis;
    }

    public Transaksi getTransPelepasan() {
        return transPelepasan;
    }

    public void setTransPelepasan(Transaksi transPelepasan) {
        this.transPelepasan = transPelepasan;
    }

    public Transaksi getTransPengecualian() {
        return transPengecualian;
    }

    public void setTransPengecualian(Transaksi transPengecualian) {
        this.transPengecualian = transPengecualian;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }
	
}
