package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "kew_dokumen")
public class DokumenKewangan  implements Serializable{
    
    @Id
    @Column (name = "id_kew_dok", length = 16)
    private String idDokumenKewangan;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_dokumen")
    private KodDokumen kodDokumen;
    
    @Column (name = "id_kaunter")
    private String idKaunter;
    
    @ManyToOne
    @JoinColumn (name = "kod_kutip")
    private KodKutipan mod;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "no_akaun")
    private Akaun akaun;
    
    @Column (name = "amaun_bayar", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal amaunBayaran;
    
    @Column (name = "amaun_tunai", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal amaunTunai;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_status") 
    private KodStatusDokumenKewangan status;
    
    @OneToMany (mappedBy = "dokumenKewangan", cascade = CascadeType.ALL)
    List<Transaksi> senaraiTransaksi;
    
    @OneToMany (mappedBy = "dokumenKewangan", cascade = CascadeType.ALL)
    List<DokumenKewanganBayaran> senaraiBayaran;
    
    @Column (name = "isu_kpd")
    private String isuKepada;
    
    @Column (name = "isu_kpd_alamat1")
    private String isuKepadaAlamat1;
    
    @Column (name = "isu_kpd_alamat2")
    private String isuKepadaAlamat2;
    
    @Column (name = "isu_kpd_alamat3")
    private String isuKepadaAlamat3;
    
    @Column (name = "isu_kpd_alamat4")
    private String isuKepadaAlamat4;
            
    @Column (name = "isu_kpd_poskod")
    private String isuKepadaPoskod;
    
    @ManyToOne
    @JoinColumn (name = "isu_kpd_kod_negeri")
    private KodNegeri isuKepadaNegeri;
    
    @Column (name = "isu_kpd_tel1")
    private String isuKepadaNoTelefon1;
    
    @Column (name = "isu_kpd_email")
    private String isuKepadaEmail;

    @Column (name = "no_ruj", length = 50)
    private String noRujukan;
    
    @Column (name = "catatan")
    private String catatan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_sbb_batal")
    private KodBatalDokumenKewangan kodBatal;
    
    @ManyToOne
    @JoinColumn (name = "dibatal")
    private Pengguna dibatalOleh;
    
    @Column (name = "trh_batal")
    private Date tarikhBatal;
    
    @Column (name = "no_ruj_man", length = 20)
    private String noRujukanManual;
    
    @Column (name = "trh_trans")
    private Date tarikhTransaksi;
    
    @ManyToOne
    @JoinColumn (name = "kod_agensi_kutipan_caw")
    private KodAgensiKutipanCawangan agensiKutipan;
    
    @Column (name = "bil_cetak")
    private int bilanganCetak;


    @ManyToOne
    @JoinColumn (name = "bayar_kurang_dilulus")
    private Pengguna bayaranKurangDilulus;


    @Embedded
    private InfoAudit infoAudit;


    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setIdKaunter(String idKaunter) {
		this.idKaunter = idKaunter;
	}

	public String getIdKaunter() {
		return idKaunter;
	}

	public void setMod(KodKutipan mod) {
		this.mod = mod;
	}

	public KodKutipan getMod() {
		return mod;
	}

	public void setAkaun(Akaun akaun) {
		this.akaun = akaun;
	}

	public Akaun getAkaun() {
		return akaun;
	}

	public void setAmaunBayaran(BigDecimal amaunBayaran) {
        this.amaunBayaran = amaunBayaran;
    }

    public BigDecimal getAmaunBayaran() {
        return amaunBayaran;
    }

    public void setAmaunTunai(BigDecimal amaunTunai) {
        this.amaunTunai = amaunTunai;
    }

    public BigDecimal getAmaunTunai() {
        return amaunTunai;
    }
    
	public KodStatusDokumenKewangan getStatus() {
		return status;
	}

	public void setStatus(KodStatusDokumenKewangan status) {
		this.status = status;
	}
	
        public List<Transaksi> getSenaraiTransaksi() {
        return senaraiTransaksi;
    }

    public void setSenaraiTransaksi(List<Transaksi> senaraiTransaksi) {
        this.senaraiTransaksi = senaraiTransaksi;
    }

        public List<DokumenKewanganBayaran> getSenaraiBayaran() {
		return senaraiBayaran;
	}

	public void setSenaraiBayaran(List<DokumenKewanganBayaran> senaraiBayaran) {
		this.senaraiBayaran = senaraiBayaran;
	}

	public void setIsuKepada(String isuKepada) {
		this.isuKepada = isuKepada;
	}

	public String getIsuKepada() {
		return isuKepada;
	}

    public String getIsuKepadaAlamat1() {
        return isuKepadaAlamat1;
    }

    public void setIsuKepadaAlamat1(String isuKepadaAlamat1) {
        this.isuKepadaAlamat1 = isuKepadaAlamat1;
    }

    public String getIsuKepadaAlamat2() {
        return isuKepadaAlamat2;
    }

    public void setIsuKepadaAlamat2(String isuKepadaAlamat2) {
        this.isuKepadaAlamat2 = isuKepadaAlamat2;
    }

    public String getIsuKepadaAlamat3() {
        return isuKepadaAlamat3;
    }

    public void setIsuKepadaAlamat3(String isuKepadaAlamat3) {
        this.isuKepadaAlamat3 = isuKepadaAlamat3;
    }

    public String getIsuKepadaAlamat4() {
        return isuKepadaAlamat4;
    }

    public void setIsuKepadaAlamat4(String isuKepadaAlamat4) {
        this.isuKepadaAlamat4 = isuKepadaAlamat4;
    }

    public String getIsuKepadaPoskod() {
        return isuKepadaPoskod;
    }

    public void setIsuKepadaPoskod(String isuKepadaPoskod) {
        this.isuKepadaPoskod = isuKepadaPoskod;
    }

    public KodNegeri getIsuKepadaNegeri() {
        return isuKepadaNegeri;
    }

    public void setIsuKepadaNegeri(KodNegeri isuKepadaNegeri) {
        this.isuKepadaNegeri = isuKepadaNegeri;
    }

    public String getIsuKepadaNoTelefon1() {
        return isuKepadaNoTelefon1;
    }

    public void setIsuKepadaNoTelefon1(String isuKepadaNoTelefon1) {
        this.isuKepadaNoTelefon1 = isuKepadaNoTelefon1;
    }

    public String getIsuKepadaEmail() {
        return isuKepadaEmail;
    }

    public void setIsuKepadaEmail(String isuKepadaEmail) {
        this.isuKepadaEmail = isuKepadaEmail;
    }

    public void setNoRujukan(String noRujukan){
            this.noRujukan = noRujukan;
    }

    public String getNoRujukan(){
        return noRujukan;
    }

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public KodBatalDokumenKewangan getKodBatal() {
		return kodBatal;
	}

	public void setKodBatal(KodBatalDokumenKewangan kodBatal) {
		this.kodBatal = kodBatal;
	}

	public Pengguna getDibatalOleh() {
		return dibatalOleh;
	}

	public void setDibatalOleh(Pengguna dibatalOleh) {
		this.dibatalOleh = dibatalOleh;
	}

	public Date getTarikhBatal() {
		return tarikhBatal;
	}

	public void setTarikhBatal(Date tarikhBatal) {
		this.tarikhBatal = tarikhBatal;
	}

	public void setNoRujukanManual(String noRujukanManual) {
		this.noRujukanManual = noRujukanManual;
	}

	public String getNoRujukanManual() {
		return noRujukanManual;
	}

	public void setTarikhTransaksi(Date tarikhTransaksi) {
		this.tarikhTransaksi = tarikhTransaksi;
	}

	public Date getTarikhTransaksi() {
		return tarikhTransaksi;
	}
	
	public void setAgensiKutipan(KodAgensiKutipanCawangan agensiKutipan) {
		this.agensiKutipan = agensiKutipan;
	}

	public KodAgensiKutipanCawangan getAgensiKutipan() {
		return agensiKutipan;
	}

	public int getBilanganCetak(){
		return bilanganCetak;
	}
	
	public void setBilanganCetak(int bilanganCetak){
		this.bilanganCetak = bilanganCetak;
	}

    public Pengguna getBayaranKurangDilulus() {
        return bayaranKurangDilulus;
    }

    public void setBayaranKurangDilulus(Pengguna bayaranKurangDilulus) {
        this.bayaranKurangDilulus = bayaranKurangDilulus;
    }


        


	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
