package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "kod_urusan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodUrusan implements Serializable {

    @Id
    @Column(name = "kod", length = 5)
    private String kod;
    
    @Column(name = "nama", length = 128, nullable = false, unique = true)
    private String nama;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_jabatan")
    private KodJabatan jabatan;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_serah")
    private KodPerserahan kodPerserahan;
    
    @Column (name = "utama")
    private int utama;
    
    @Column (name = "sasar_bln")
    private Integer sasaranBulan;
    
    @Column (name = "ke_ptg")
    private char kePTG;
    
    @Column (name = "jelas_cukai")
    private char perluJelasCukai;
    
    @Column (name = "min_hakmilik")
    private Integer bilMinimumHakmilik;

    @Column (name = "maks_hakmilik")
    private Integer bilMaksimumHakmilik;
    
    // CARA PERSERAHAN/SUBMISSION
    
    @Column (name = "urusan_kntr")
    private char urusanKaunter;
    
    @Column (name = "urusan_dlm")
    private char urusanDalaman;

    @Column (name = "urusan_blkg")
    private char urusanBelakangKaunter;

    @Column (name = "urusan_bayar")
    private char urusanBayaran;
    
    @Column(name = "ruj_kanun", length = 256)
    private String rujukanKanun;
    
    @Column(name = "aktif")
    private char aktif;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_trans")
    private KodTransaksi kodTransaksi;
    
    @OneToMany(mappedBy = "kodUrusan", fetch = FetchType.LAZY)
    private List<UrusanDokumen> senaraiDokumen;
    
    @ManyToOne
    @JoinColumn (name = "kod_katg_bayar")
    private KodKategoriBayaran kategoriBayaran;
    
    @Column(name = "caj", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal caj;
    
//    @Column (name = "caj_tamb_daftar")
//    private BigDecimal cajTambahanHakmilikPendaftar;
    
    @Column(name = "id_workflow", length = 250)
    private String idWorkflow;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_urusan_slps")
    private KodUrusan kodUrusanSelepas;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "kod_rayuan")
    private KodUrusan kodUrusanRayuan;

    @OneToMany(mappedBy = "kodUrusan", fetch = FetchType.LAZY)
    private List<KodUrusanHalang> kodUrusanHalang;
    
    @Column(name = "id_workflow_integration", length = 250)
    private String idWorkflowIntegration;
    
    @Embedded
    private InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setJabatan(KodJabatan jabatan) {
        this.jabatan = jabatan;
    }

    public KodJabatan getJabatan() {
        return jabatan;
    }

    public void setKodPerserahan(KodPerserahan kodPerserahan) {
        this.kodPerserahan = kodPerserahan;
    }

    public KodPerserahan getKodPerserahan() {
        return kodPerserahan;
    }

    public void setUtama(int utama) {
		this.utama = utama;
	}

	public int getUtama() {
		return utama;
	}

	public void setSasaranBulan(Integer sasaranBulan) {
		this.sasaranBulan = sasaranBulan;
	}

	public Integer getSasaranBulan() {
		return sasaranBulan;
	}

	public void setKePTG(char kePTG) {
		this.kePTG = kePTG;
	}

	public char getKePTG() {
		return kePTG;
	}

	public void setPerluJelasCukai(char perluJelasCukai) {
		this.perluJelasCukai = perluJelasCukai;
    }

    public char getPerluJelasCukai() {
            return perluJelasCukai;
    }

    public Integer getBilMinimumHakmilik() {
		return bilMinimumHakmilik;
	}

	public void setBilMinimumHakmilik(Integer bilMinimumHakmilik) {
		this.bilMinimumHakmilik = bilMinimumHakmilik;
	}

	public Integer getBilMaksimumHakmilik() {
		return bilMaksimumHakmilik;
	}

	public void setBilMaksimumHakmilik(Integer bilMaksimumHakmilik) {
		this.bilMaksimumHakmilik = bilMaksimumHakmilik;
	}

	public char getUrusanKaunter() {
            return urusanKaunter;
    }

    public void setUrusanKaunter(char urusanKaunter) {
            this.urusanKaunter = urusanKaunter;
    }

    public char getUrusanDalaman() {
            return urusanDalaman;
    }

    public void setUrusanDalaman(char urusanDalaman) {
            this.urusanDalaman = urusanDalaman;
    }

    public char getUrusanBelakangKaunter() {
            return urusanBelakangKaunter;
    }

    public void setUrusanBelakangKaunter(char urusanBelakangKaunter) {
            this.urusanBelakangKaunter = urusanBelakangKaunter;
    }

    public char getUrusanBayaran() {
        return urusanBayaran;
    }

    public void setUrusanBayaran(char urusanBayaran) {
        this.urusanBayaran = urusanBayaran;
    }


	public void setRujukanKanun(String rujukanKanun) {
        this.rujukanKanun = rujukanKanun;
    }

    public String getRujukanKanun() {
        return rujukanKanun;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getAktif() {
        return aktif;
    }

    public List<UrusanDokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<UrusanDokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setKodTransaksi(KodTransaksi kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public KodTransaksi getKodTransaksi() {
        return kodTransaksi;
    }

    public void setKategoriBayaran(KodKategoriBayaran kategoriBayaran) {
		this.kategoriBayaran = kategoriBayaran;
	}

	public KodKategoriBayaran getKategoriBayaran() {
		return kategoriBayaran;
	}

	public BigDecimal getCaj() {
        return caj;
    }

    public void setCaj(BigDecimal caj) {
        this.caj = caj;
    }

//    public void setCajTambahanHakmilikPendaftar(
//			BigDecimal cajTambahanHakmilikPendaftar) {
//		this.cajTambahanHakmilikPendaftar = cajTambahanHakmilikPendaftar;
//	}
//
//	public BigDecimal getCajTambahanHakmilikPendaftar() {
//		return cajTambahanHakmilikPendaftar;
//	}

	public String getIdWorkflow() {
        return idWorkflow;
    }

    public void setIdWorkflow(String idWorkflow) {
        this.idWorkflow = idWorkflow;
    }
    
    public void setKodUrusanSelepas(KodUrusan kodUrusanSelepas) {
		this.kodUrusanSelepas = kodUrusanSelepas;
	}

	public KodUrusan getKodUrusanSelepas() {
		return kodUrusanSelepas;
	}

	public void setKodUrusanRayuan(KodUrusan kodUrusanRayuan) {
		this.kodUrusanRayuan = kodUrusanRayuan;
	}

	public KodUrusan getKodUrusanRayuan() {
		return kodUrusanRayuan;
	}

	@Transient
    public String getJabatanNama(){
    	if (jabatan != null){
    		return jabatan.getNama();
    	} else{
    		return null;
    	}
    }

    public List<KodUrusanHalang> getKodUrusanHalang() {
        return kodUrusanHalang;
    }

    public void setKodUrusanHalang(List<KodUrusanHalang> kodUrusanHalang) {
        this.kodUrusanHalang = kodUrusanHalang;
    }

    public String getIdWorkflowIntegration() {
        return idWorkflowIntegration;
    }

    public void setIdWorkflowIntegration(String idWorkflowIntegration) {
        this.idWorkflowIntegration = idWorkflowIntegration;
    }
}

