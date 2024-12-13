package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "lapor_pulih_tanah")
@SequenceGenerator(name = "seq_lapor_pulih_tanah", sequenceName = "seq_lapor_pulih_tanah")
public class LaporanPemulihanTanah implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_lapor_pulih_tanah")
    @Column (name = "id_lapor")
    private int idLapor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mp")
    private PermohonanPihak permohonanPihak;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;


    @Column (name = "ada_rosak_tanah")
    private String adaKerosakanTanah;
    @Column (name = "keterangan_rosak_tanah")
    private String keteranganKerosakanTanah;
    @Column (name = "kos_rosak_tanah")
    private BigDecimal kosKerosakanTanah;

    @Column (name = "ada_rosak_bngn")
    private String adaKerosakanBangunan;
    @Column (name = "keterangan_rosak_bngn")
    private String keteranganKerosakanBangunan;
    @Column (name = "kos_rosak_bngn")
    private BigDecimal kosKerosakanBangunan;

    @Column (name = "ada_rosak_pokok")
    private String adaKerosakanPokok;
    @Column (name = "keterangan_rosak_pokok")
    private String keteranganKerosakanPokok;
    @Column (name = "kos_rosak_pokok")
    private BigDecimal kosKerosakanPokok;

    @Column (name = "ada_rosak_infra")
    private String adaKerosakanInfra;
    @Column (name = "keterangan_rosak_infra")
    private String keteranganKerosakanInfra;
    @Column (name = "kos_rosak_infra")
    private BigDecimal kosKerosakanInfra;

    @Column (name = "ada_rosak_lain")
    private String adaKerosakanLain;
    @Column (name = "keterangan_rosak_lain")
    private String keteranganKerosakanLain;
    @Column (name = "kos_rosak_lain")
    private BigDecimal kosKerosakanLain;


    @Column (name = "ada_cacat_tanah")
    private String adaKecacatanTanah;
    @Column (name = "keterangan_cacat_tanah")
    private String keteranganKecacatanTanah;
    @Column (name = "kos_cacat_tanah")
    private BigDecimal kosKecacatanTanah;

    @Column (name = "jenis_lapor")
    private String jenisLaporan;

    @Column (name = "nilai_tanah")
    private BigDecimal nilaiTanah;

    @Column (name = "trh_nilai")
    private Date tarikhNilai;
    
    @Column (name = "nilai_jpph_tanah")
    private BigDecimal nilaiTanahJpph;
    
    @Column (name = "nilai_jpph_bngn")
    private BigDecimal nilaiBngnJpph;
    
    @Column (name = "nilai_jpph_pokok")
    private BigDecimal nilaiPokokJpph;
    
    @Column (name = "nilai_jpph_infra")
    private BigDecimal nilaiInfraJpph;
    
    @Column (name = "nilai_jpph_lain")
    private BigDecimal nilaiLainJpph;
    
    @Column (name = "nilai_jpph_cacattanah")
    private BigDecimal nilaiCatatJpph;
    
    @Column (name = "syor")
    private String syor;
    
    @Column (name = "ulasan")
    private String ulasan;
    
    @Embedded
    InfoAudit infoAudit;

    public int getIdLapor() {
		return idLapor;
	}

	public void setIdLapor(int idLapor) {
		this.idLapor = idLapor;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

    public String getAdaKecacatanTanah() {
        return adaKecacatanTanah;
    }

    public void setAdaKecacatanTanah(String adaKecacatanTanah) {
        this.adaKecacatanTanah = adaKecacatanTanah;
    }

    public String getAdaKerosakanBangunan() {
        return adaKerosakanBangunan;
    }

    public void setAdaKerosakanBangunan(String adaKerosakanBangunan) {
        this.adaKerosakanBangunan = adaKerosakanBangunan;
    }

    public String getAdaKerosakanInfra() {
        return adaKerosakanInfra;
    }

    public void setAdaKerosakanInfra(String adaKerosakanInfra) {
        this.adaKerosakanInfra = adaKerosakanInfra;
    }

    public String getAdaKerosakanLain() {
        return adaKerosakanLain;
    }

    public void setAdaKerosakanLain(String adaKerosakanLain) {
        this.adaKerosakanLain = adaKerosakanLain;
    }

    public String getAdaKerosakanPokok() {
        return adaKerosakanPokok;
    }

    public void setAdaKerosakanPokok(String adaKerosakanPokok) {
        this.adaKerosakanPokok = adaKerosakanPokok;
    }

    public String getAdaKerosakanTanah() {
        return adaKerosakanTanah;
    }

    public void setAdaKerosakanTanah(String adaKerosakanTanah) {
        this.adaKerosakanTanah = adaKerosakanTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getKeteranganKecacatanTanah() {
        return keteranganKecacatanTanah;
    }

    public void setKeteranganKecacatanTanah(String keteranganKecacatanTanah) {
        this.keteranganKecacatanTanah = keteranganKecacatanTanah;
    }

    public String getKeteranganKerosakanBangunan() {
        return keteranganKerosakanBangunan;
    }

    public void setKeteranganKerosakanBangunan(String keteranganKerosakanBangunan) {
        this.keteranganKerosakanBangunan = keteranganKerosakanBangunan;
    }

    public String getKeteranganKerosakanInfra() {
        return keteranganKerosakanInfra;
    }

    public void setKeteranganKerosakanInfra(String keteranganKerosakanInfra) {
        this.keteranganKerosakanInfra = keteranganKerosakanInfra;
    }

    public String getKeteranganKerosakanLain() {
        return keteranganKerosakanLain;
    }

    public void setKeteranganKerosakanLain(String keteranganKerosakanLain) {
        this.keteranganKerosakanLain = keteranganKerosakanLain;
    }

    public String getKeteranganKerosakanPokok() {
        return keteranganKerosakanPokok;
    }

    public void setKeteranganKerosakanPokok(String keteranganKerosakanPokok) {
        this.keteranganKerosakanPokok = keteranganKerosakanPokok;
    }

    public String getKeteranganKerosakanTanah() {
        return keteranganKerosakanTanah;
    }

    public void setKeteranganKerosakanTanah(String keteranganKerosakanTanah) {
        this.keteranganKerosakanTanah = keteranganKerosakanTanah;
    }

    public BigDecimal getKosKecacatanTanah() {
        return kosKecacatanTanah;
    }

    public void setKosKecacatanTanah(BigDecimal kosKecacatanTanah) {
        this.kosKecacatanTanah = kosKecacatanTanah;
    }

    public String getJenisLaporan() {
        return jenisLaporan;
    }

    public void setJenisLaporan(String jenisLaporan) {
        this.jenisLaporan = jenisLaporan;
    }

    public BigDecimal getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(BigDecimal nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

    public Date getTarikhNilai() {
        return tarikhNilai;
    }

    public void setTarikhNilai(Date tarikhNilai) {
        this.tarikhNilai = tarikhNilai;
    }

    
    public BigDecimal getKosKerosakanBangunan() {
        return kosKerosakanBangunan;
    }

    public void setKosKerosakanBangunan(BigDecimal kosKerosakanBangunan) {
        this.kosKerosakanBangunan = kosKerosakanBangunan;
    }

    public BigDecimal getKosKerosakanInfra() {
        return kosKerosakanInfra;
    }

    public void setKosKerosakanInfra(BigDecimal kosKerosakanInfra) {
        this.kosKerosakanInfra = kosKerosakanInfra;
    }

    public BigDecimal getKosKerosakanLain() {
        return kosKerosakanLain;
    }

    public void setKosKerosakanLain(BigDecimal kosKerosakanLain) {
        this.kosKerosakanLain = kosKerosakanLain;
    }

    public BigDecimal getKosKerosakanPokok() {
        return kosKerosakanPokok;
    }

    public void setKosKerosakanPokok(BigDecimal kosKerosakanPokok) {
        this.kosKerosakanPokok = kosKerosakanPokok;
    }

    public BigDecimal getKosKerosakanTanah() {
        return kosKerosakanTanah;
    }

    public void setKosKerosakanTanah(BigDecimal kosKerosakanTanah) {
        this.kosKerosakanTanah = kosKerosakanTanah;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }


	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public BigDecimal getNilaiTanahJpph() {
        return nilaiTanahJpph;
    }

    public void setNilaiTanahJpph(BigDecimal nilaiTanahJpph) {
        this.nilaiTanahJpph = nilaiTanahJpph;
    }

    public BigDecimal getNilaiBngnJpph() {
        return nilaiBngnJpph;
    }

    public void setNilaiBngnJpph(BigDecimal nilaiBngnJpph) {
        this.nilaiBngnJpph = nilaiBngnJpph;
    }

    public BigDecimal getNilaiPokokJpph() {
        return nilaiPokokJpph;
    }

    public void setNilaiPokokJpph(BigDecimal nilaiPokokJpph) {
        this.nilaiPokokJpph = nilaiPokokJpph;
    }

    public BigDecimal getNilaiInfraJpph() {
        return nilaiInfraJpph;
    }

    public void setNilaiInfraJpph(BigDecimal nilaiInfraJpph) {
        this.nilaiInfraJpph = nilaiInfraJpph;
    }

    public BigDecimal getNilaiLainJpph() {
        return nilaiLainJpph;
    }

    public void setNilaiLainJpph(BigDecimal nilaiLainJpph) {
        this.nilaiLainJpph = nilaiLainJpph;
    }

    public BigDecimal getNilaiCatatJpph() {
        return nilaiCatatJpph;
    }

    public void setNilaiCatatJpph(BigDecimal nilaiCatatJpph) {
        this.nilaiCatatJpph = nilaiCatatJpph;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

}
