package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "sej_kew_dokumen")
public class SejarahDokumenKewangan  implements Serializable{
    
    @Id
    @Column (name = "id_sej_kew_dok", length = 16)
    private String idDokumenKewangan;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_dokumen")
    private KodDokumen kodDokumen;
    
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
    List<SejarahDokumenKewanganBayaran> senaraiBayaran;
    
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
    
    @Column (name = "bil_cetak")
    private int bilanganCetak;
    
    @ManyToOne
    @JoinColumn (name = "KOD_AGENSI_KUTIPAN_CAW")
    private KodAgensiKutipanCawangan kodAgensiKutipanCaw;
    
    @Embedded
    private InfoAudit infoAudit;

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

	public KodCawangan getCawangan() {
		return cawangan;
	}

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

	public Akaun getAkaun() {
		return akaun;
	}

    public BigDecimal getAmaunBayaran() {
        return amaunBayaran;
    }

    public BigDecimal getAmaunTunai() {
        return amaunTunai;
    }
    
	public KodStatusDokumenKewangan getStatus() {
		return status;
	}

    public List<SejarahDokumenKewanganBayaran> getSenaraiBayaran() {
		return senaraiBayaran;
	}

	public String getIsuKepada() {
		return isuKepada;
	}

	public String getIsuKepadaAlamat1() {
        return isuKepadaAlamat1;
    }

    public String getIsuKepadaAlamat2() {
        return isuKepadaAlamat2;
    }

    public String getIsuKepadaAlamat3() {
        return isuKepadaAlamat3;
    }

    public String getIsuKepadaAlamat4() {
        return isuKepadaAlamat4;
    }

    public void setIsuKepadaPoskod(String isuKepadaPoskod) {
        this.isuKepadaPoskod = isuKepadaPoskod;
    }

    public String getIsuKepadaPoskod() {
        return isuKepadaPoskod;
    }

    public KodNegeri getIsuKepadaNegeri() {
        return isuKepadaNegeri;
    }

    public String getIsuKepadaEmail() {
        return isuKepadaEmail;
    }

    public void setIsuKepadaNoTelefon1(String isuKepadaNoTelefon1) {
        this.isuKepadaNoTelefon1 = isuKepadaNoTelefon1;
    }

    public String getIsuKepadaNoTelefon1() {
        return isuKepadaNoTelefon1;
    }

    public String getCatatan() {
		return catatan;
	}

	public KodBatalDokumenKewangan getKodBatal() {
		return kodBatal;
	}

	public Pengguna getDibatalOleh() {
		return dibatalOleh;
	}

	public Date getTarikhBatal() {
		return tarikhBatal;
	}

	public String getNoRujukanManual() {
		return noRujukanManual;
	}

	public Date getTarikhTransaksi() {
		return tarikhTransaksi;
	}
	
	public int getBilanganCetak(){
		return bilanganCetak;
	}

    public KodAgensiKutipanCawangan getKodAgensiKutipanCaw() {
        return kodAgensiKutipanCaw;
    }

    public void setKodAgensiKutipanCaw(KodAgensiKutipanCawangan kodAgensiKutipanCaw) {
        this.kodAgensiKutipanCaw = kodAgensiKutipanCaw;
    }
	

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
    
    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

    public void setAmaunBayaran(BigDecimal amaunBayaran) {
        this.amaunBayaran = amaunBayaran;
    }

    public void setAmaunTunai(BigDecimal amaunTunai) {
        this.amaunTunai = amaunTunai;
    }

    public void setStatus(KodStatusDokumenKewangan status) {
        this.status = status;
    }

    public void setIsuKepada(String isuKepada) {
        this.isuKepada = isuKepada;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public void setKodBatal(KodBatalDokumenKewangan kodBatal) {
        this.kodBatal = kodBatal;
    }

    public void setTarikhBatal(Date tarikhBatal) {
        this.tarikhBatal = tarikhBatal;
    }

    public void setNoRujukanManual(String noRujukanManual) {
        this.noRujukanManual = noRujukanManual;
    }

    public void setTarikhTransaksi(Date tarikhTransaksi) {
        this.tarikhTransaksi = tarikhTransaksi;
    }

    public void setBilanganCetak(int bilanganCetak) {
        this.bilanganCetak = bilanganCetak;
    }

    public void setDibatalOleh(Pengguna dibatalOleh) {
        this.dibatalOleh = dibatalOleh;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
