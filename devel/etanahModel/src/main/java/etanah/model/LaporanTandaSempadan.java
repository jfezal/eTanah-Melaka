package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "lapor_tanda_sempadan")
@SequenceGenerator(name = "seq_lapor_tanda_sempadan", sequenceName = "seq_lapor_tanda_sempadan")
public class LaporanTandaSempadan implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_lapor_tanda_sempadan")
    @Column (name = "id_lapor")
    private int idLapor;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mp")
    private PermohonanPihak permohonanPihak;

    @Column (name = "piket_tanda", nullable = false)
    private char piketTanda;

    @Column (name = "offset", nullable = false)
    private char offsetBangunanPagar;

    @Column (name = "catatan")
    private String catatan;

    @Column (name = "ada_ikatan_batu")
    private String adaIkatanBatu;

    @Column (name = "nilai_tanah")
    private BigDecimal nilaiTanah;
    @Column (name = "keterangan_nilai_tanah")
    private String keteranganNilaiTanah;

    @Column (name = "nilai_bngn")
    private BigDecimal nilaiBangunan;
    @Column (name = "keterangan_nilai_bngn")
    private String keteranganNilaiBangunan;

    @Column (name = "nilai_pokok")
    private BigDecimal nilaiPokok;
    @Column (name = "keterangan_nilai_pokok")
    private String keteranganNilaiPokok;

    @Column (name = "nilai_infra")
    private BigDecimal nilaiInfra;
    @Column (name = "keterangan_nilai_infra")
    private String keteranganNilaiInfra;

    @Column (name = "nilai_lain")
    private BigDecimal nilaiLain;
    @Column (name = "keterangan_nilai_lain")
    private String keteranganNilaiLain;

    @Column (name = "sbb_pduduk")
    private String sebabPendudukan;

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

	public char getPiketTanda() {
		return piketTanda;
	}

	public void setPiketTanda(char piketTanda) {
		this.piketTanda = piketTanda;
	}

	public char getOffsetBangunanPagar() {
		return offsetBangunanPagar;
	}

	public void setOffsetBangunanPagar(char offsetBangunanPagar) {
		this.offsetBangunanPagar = offsetBangunanPagar;
	}

    public String getAdaIkatanBatu() {
        return adaIkatanBatu;
    }

    public void setAdaIkatanBatu(String adaIkatanBatu) {
        this.adaIkatanBatu = adaIkatanBatu;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getKeteranganNilaiBangunan() {
        return keteranganNilaiBangunan;
    }

    public void setKeteranganNilaiBangunan(String keteranganNilaiBangunan) {
        this.keteranganNilaiBangunan = keteranganNilaiBangunan;
    }

    public String getKeteranganNilaiInfra() {
        return keteranganNilaiInfra;
    }

    public void setKeteranganNilaiInfra(String keteranganNilaiInfra) {
        this.keteranganNilaiInfra = keteranganNilaiInfra;
    }

    public String getKeteranganNilaiLain() {
        return keteranganNilaiLain;
    }

    public void setKeteranganNilaiLain(String keteranganNilaiLain) {
        this.keteranganNilaiLain = keteranganNilaiLain;
    }

    public String getKeteranganNilaiPokok() {
        return keteranganNilaiPokok;
    }

    public void setKeteranganNilaiPokok(String keteranganNilaiPokok) {
        this.keteranganNilaiPokok = keteranganNilaiPokok;
    }

    public String getKeteranganNilaiTanah() {
        return keteranganNilaiTanah;
    }

    public void setKeteranganNilaiTanah(String keteranganNilaiTanah) {
        this.keteranganNilaiTanah = keteranganNilaiTanah;
    }

    public BigDecimal getNilaiBangunan() {
        return nilaiBangunan;
    }

    public void setNilaiBangunan(BigDecimal nilaiBangunan) {
        this.nilaiBangunan = nilaiBangunan;
    }

    public BigDecimal getNilaiInfra() {
        return nilaiInfra;
    }

    public void setNilaiInfra(BigDecimal nilaiInfra) {
        this.nilaiInfra = nilaiInfra;
    }

    public BigDecimal getNilaiLain() {
        return nilaiLain;
    }

    public void setNilaiLain(BigDecimal nilaiLain) {
        this.nilaiLain = nilaiLain;
    }

    public BigDecimal getNilaiPokok() {
        return nilaiPokok;
    }

    public void setNilaiPokok(BigDecimal nilaiPokok) {
        this.nilaiPokok = nilaiPokok;
    }

    public BigDecimal getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(BigDecimal nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getSebabPendudukan() {
        return sebabPendudukan;
    }

    public void setSebabPendudukan(String sebabPendudukan) {
        this.sebabPendudukan = sebabPendudukan;
    }

	public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
