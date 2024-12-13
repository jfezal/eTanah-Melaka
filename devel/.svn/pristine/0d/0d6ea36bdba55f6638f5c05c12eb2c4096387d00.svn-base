package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table (name = "mohon_ukur")
@SequenceGenerator(name = "seq_mohon_ukur", sequenceName = "seq_mohon_ukur")
public class PermohonanUkur implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_mohon_ukur")
    @Column (name = "id_mohon_ukur")
    private int idMohonUkur;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn (name = "kod_hakmilik")
    private KodHakmilik kodHakmilik;

    @Column (name = "bil_pej_tanah", nullable = false)
    private String noRujukanPejabatTanah;

    @Column (name = "bil_pej_ukur")
    private String noRujukanPejabatUkur;

    @Column (name = "no_pu", nullable = false)
    private String noPermohonanUkur;

    @Column (name = "borang_5b")
    private String perincianBorang5b;

    @Column (name = "borang_5c")
    private String perincianBorang5c;

    @Column (name = "borang_5d")
    private String perincianBorang5d;

    @Column (name = "borang_5e")
    private String perincianBorang5e;

    @Column (name = "pajakan_lombong")
    private String perincianPajakanLombong;

    @Column (name = "strata")
    private String perincianStrata;

    @Column (name = "status_suratan_hm")
    private char statusSuratanHakmilik;

    @Column (name = "status_suratan_hms")
    private char statusSuratanHakmilikSementara;

    @Column (name = "status_bayaran_ukur")
    private char statusBayaranUkur;

    @Column (name = "pengecualian_oleh")
    private String pemberiPengecualian;

    @Column (name = "perenggan_ktn")
    private String perengganKTN;

    @Column (name = "rujukan_ktn")
    private String rujukanKTN;

    @Column (name = "jum_pengecualian")
    private BigDecimal jumlahPengecualian;

    @Column (name = "jum_bayaran_ukur")
    private BigDecimal jumlahBayaranUkur;

    @Column (name = "no_resit")
    private String noResit;

    @Column(name = "trh_resit")
    private Date tarikhResit;

    @Column(name = "tarikh_perakuan")
    private Date tarikhPerakuan;

    @Column (name = "tujuan")
    private String tujuan;
    
    @Column (name = "no_fail_iso")
    private String noFailISO;
    
    @Column (name = "sebab_ukur", length = 4)
    private String sebabUkur;
    
    @Column (name = "lot", length = 5)
    private String lot;
    
    @Column (name = "bahagian", length = 20)
    private String bahagian;
    
    @Column(name = "Berimilik_tnh_kjaan")
    private String bermilikTanahKerajaan;
    
    @Column(name = "Pecahan_lot")
    private String pecahanLot;
    
    @Column(name = "Cantuman_lot")
    private String camtumanLot;
    
    @Column(name = "Cantuman_lot_dipecah")
    private String camtumanLotDipecah;
    
    @Column(name = "Serah_sbgn_lot")
    private String serahSebahagianLot;
    
    @Column(name = "Berimilik_semula_bahagian")
    private String berimilikSemulaBahagian;
    
    @Column(name = "Ambil_sbgn_lot")
    private String ambilSebahagianLot;
    
    @Column(name = "Ukur_semula_lot")
    private String UkurSemulaLot;
    
    @Column(name = "Borang_10c")
    private String borang10c;
    
    @Column(name = "Pengecualian_oleh_lihat")
    private String pengecualianOlehLihat;
    
    @Column(name = "Pengecualian_setakat")
    private String pengecualianSetakat;

    @Column(name = "Pengecualian_setakat_lihat")
    private String pengecualianSetakatLihat;
    
    @Column(name = "No_aturan")
    private String noAturan;
    
    @Column(name = "No_akaun")
    private String noAkaun;
    
    @Column (name = "Anggaran_luas")
    private BigDecimal anggaranLuas;
    
    @ManyToOne
    @JoinColumn (name = "Anggaran_luas_uom")
    private KodUOM anggaranLuatUOM;
   
    @ManyToOne
    @JoinColumn (name = "kod_katg_tanah")
    private KodKategoriTanah kategoriTanah;
    
    
    @Column (name = "bayar_ukur")
    private String bayaranUkur;
    
    @Column (name = "diukur_oleh")
    private String diUkurOleh;
    
    @Column (name = "pengecualian_oleh_b")
    private String pengencualian;
    
    @Column (name = "perenggan_ktn_b")
    private String perengganKtn;
    
    @Column (name = "no_resit_d")
    private String noResitD;
    
    @Column (name = "trh_resit_d")
    private Date tarikhResitD;
    
    @Column (name = "rujukan_ktn_b")
    private String rujukanKtn;
    
    
    
    @Embedded
    InfoAudit infoAudit;

    /**
	 * @return the perincianBorang5c
	 */
	public String getPerincianBorang5c() {
		return perincianBorang5c;
	}

	/**
	 * @param perincianBorang5c the perincianBorang5c to set
	 */
	public void setPerincianBorang5c(String perincianBorang5c) {
		this.perincianBorang5c = perincianBorang5c;
	}

	/**
	 * @return the perincianBorang5d
	 */
	public String getPerincianBorang5d() {
		return perincianBorang5d;
	}

	/**
	 * @param perincianBorang5d the perincianBorang5d to set
	 */
	public void setPerincianBorang5d(String perincianBorang5d) {
		this.perincianBorang5d = perincianBorang5d;
	}

	/**
	 * @return the perincianBorang5e
	 */
	public String getPerincianBorang5e() {
		return perincianBorang5e;
	}

	/**
	 * @param perincianBorang5e the perincianBorang5e to set
	 */
	public void setPerincianBorang5e(String perincianBorang5e) {
		this.perincianBorang5e = perincianBorang5e;
	}

	/**
	 * @return the perincianPajakanLombong
	 */
	public String getPerincianPajakanLombong() {
		return perincianPajakanLombong;
	}

	/**
	 * @param perincianPajakanLombong the perincianPajakanLombong to set
	 */
	public void setPerincianPajakanLombong(String perincianPajakanLombong) {
		this.perincianPajakanLombong = perincianPajakanLombong;
	}

	/**
	 * @return the perincianStrata
	 */
	public String getPerincianStrata() {
		return perincianStrata;
	}

	/**
	 * @param perincianStrata the perincianStrata to set
	 */
	public void setPerincianStrata(String perincianStrata) {
		this.perincianStrata = perincianStrata;
	}

	/**
	 * @return the statusSuratanHakmilik
	 */
	public char getStatusSuratanHakmilik() {
		return statusSuratanHakmilik;
	}

	/**
	 * @param statusSuratanHakmilik the statusSuratanHakmilik to set
	 */
	public void setStatusSuratanHakmilik(char statusSuratanHakmilik) {
		this.statusSuratanHakmilik = statusSuratanHakmilik;
	}

	/**
	 * @return the statusSuratanHakmilikSementara
	 */
	public char getStatusSuratanHakmilikSementara() {
		return statusSuratanHakmilikSementara;
	}

	/**
	 * @param statusSuratanHakmilikSementara the statusSuratanHakmilikSementara to set
	 */
	public void setStatusSuratanHakmilikSementara(
			char statusSuratanHakmilikSementara) {
		this.statusSuratanHakmilikSementara = statusSuratanHakmilikSementara;
	}

	/**
	 * @return the statusBayaranUkur
	 */
	public char getStatusBayaranUkur() {
		return statusBayaranUkur;
	}

	/**
	 * @param statusBayaranUkur the statusBayaranUkur to set
	 */
	public void setStatusBayaranUkur(char statusBayaranUkur) {
		this.statusBayaranUkur = statusBayaranUkur;
	}

	/**
	 * @return the pemberiPengecualian
	 */
	public String getPemberiPengecualian() {
		return pemberiPengecualian;
	}

	/**
	 * @param pemberiPengecualian the pemberiPengecualian to set
	 */
	public void setPemberiPengecualian(String pemberiPengecualian) {
		this.pemberiPengecualian = pemberiPengecualian;
	}

	/**
	 * @return the perengganKTN
	 */
	public String getPerengganKTN() {
		return perengganKTN;
	}

	/**
	 * @param perengganKTN the perengganKTN to set
	 */
	public void setPerengganKTN(String perengganKTN) {
		this.perengganKTN = perengganKTN;
	}

	/**
	 * @return the rujukanKTN
	 */
	public String getRujukanKTN() {
		return rujukanKTN;
	}

	/**
	 * @param rujukanKTN the rujukanKTN to set
	 */
	public void setRujukanKTN(String rujukanKTN) {
		this.rujukanKTN = rujukanKTN;
	}

	/**
	 * @return the jumlahPengecualian
	 */
	public BigDecimal getJumlahPengecualian() {
		return jumlahPengecualian;
	}

	/**
	 * @param jumlahPengecualian the jumlahPengecualian to set
	 */
	public void setJumlahPengecualian(BigDecimal jumlahPengecualian) {
		this.jumlahPengecualian = jumlahPengecualian;
	}

	/**
	 * @return the jumlahBayaranUkur
	 */
	public BigDecimal getJumlahBayaranUkur() {
		return jumlahBayaranUkur;
	}

	/**
	 * @param jumlahBayaranUkur the jumlahBayaranUkur to set
	 */
	public void setJumlahBayaranUkur(BigDecimal jumlahBayaranUkur) {
		this.jumlahBayaranUkur = jumlahBayaranUkur;
	}

	/**
	 * @return the noResit
	 */
	public String getNoResit() {
		return noResit;
	}

	/**
	 * @param noResit the noResit to set
	 */
	public void setNoResit(String noResit) {
		this.noResit = noResit;
	}

	/**
	 * @return the tarikhResit
	 */
	public Date getTarikhResit() {
		return tarikhResit;
	}

	/**
	 * @param tarikhResit the tarikhResit to set
	 */
	public void setTarikhResit(Date tarikhResit) {
		this.tarikhResit = tarikhResit;
	}

	public void setIdMohonUkur(int idMohonUkur) {
        this.idMohonUkur = idMohonUkur;
    }

    public int getIdMohonUkur() {
        return idMohonUkur;
    }

    public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}

	public void setNoRujukanPejabatTanah(String noRujukanPejabatTanah) {
        this.noRujukanPejabatTanah = noRujukanPejabatTanah;
    }

    public String getNoRujukanPejabatTanah() {
        return noRujukanPejabatTanah;
    }

    public void setNoRujukanPejabatUkur(String noRujukanPejabatUkur) {
        this.noRujukanPejabatUkur = noRujukanPejabatUkur;
    }

    public String getNoRujukanPejabatUkur() {
        return noRujukanPejabatUkur;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

	public void setNoPermohonanUkur(String noPermohonanUkur) {
		this.noPermohonanUkur = noPermohonanUkur;
	}

	public String getNoPermohonanUkur() {
		return noPermohonanUkur;
	}

	public void setPerincianBorang5b(String perincianBorang5b) {
		this.perincianBorang5b = perincianBorang5b;
	}

	public String getPerincianBorang5b() {
		return perincianBorang5b;
	}

    public Date getTarikhPerakuan() {
        return tarikhPerakuan;
    }

    public void setTarikhPerakuan(Date tarikhPerakuan) {
        this.tarikhPerakuan = tarikhPerakuan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getNoFailISO() {
        return noFailISO;
    }

    public void setNoFailISO(String noFailISO) {
        this.noFailISO = noFailISO;
    }

    public String getSebabUkur() {
        return sebabUkur;
    }

    public void setSebabUkur(String sebabUkur) {
        this.sebabUkur = sebabUkur;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getBahagian() {
        return bahagian;
    }

    public void setBahagian(String bahagian) {
        this.bahagian = bahagian;
    }

    public String getBermilikTanahKerajaan() {
        return bermilikTanahKerajaan;
    }

    public void setBermilikTanahKerajaan(String bermilikTanahKerajaan) {
        this.bermilikTanahKerajaan = bermilikTanahKerajaan;
    }

    public String getPecahanLot() {
        return pecahanLot;
    }

    public void setPecahanLot(String pecahanLot) {
        this.pecahanLot = pecahanLot;
    }

    public String getCamtumanLot() {
        return camtumanLot;
    }

    public void setCamtumanLot(String camtumanLot) {
        this.camtumanLot = camtumanLot;
    }

    public String getCamtumanLotDipecah() {
        return camtumanLotDipecah;
    }

    public void setCamtumanLotDipecah(String camtumanLotDipecah) {
        this.camtumanLotDipecah = camtumanLotDipecah;
    }

    public String getSerahSebahagianLot() {
        return serahSebahagianLot;
    }

    public void setSerahSebahagianLot(String serahSebahagianLot) {
        this.serahSebahagianLot = serahSebahagianLot;
    }

    public String getBerimilikSemulaBahagian() {
        return berimilikSemulaBahagian;
    }

    public void setBerimilikSemulaBahagian(String berimilikSemulaBahagian) {
        this.berimilikSemulaBahagian = berimilikSemulaBahagian;
    }

    public String getAmbilSebahagianLot() {
        return ambilSebahagianLot;
    }

    public void setAmbilSebahagianLot(String ambilSebahagianLot) {
        this.ambilSebahagianLot = ambilSebahagianLot;
    }

    public String getUkurSemulaLot() {
        return UkurSemulaLot;
    }

    public void setUkurSemulaLot(String UkurSemulaLot) {
        this.UkurSemulaLot = UkurSemulaLot;
    }

    public String getBorang10c() {
        return borang10c;
    }

    public void setBorang10c(String borang10c) {
        this.borang10c = borang10c;
    }

    public String getPengecualianOlehLihat() {
        return pengecualianOlehLihat;
    }

    public void setPengecualianOlehLihat(String pengecualianOlehLihat) {
        this.pengecualianOlehLihat = pengecualianOlehLihat;
    }

    public String getPengecualianSetakat() {
        return pengecualianSetakat;
    }

    public void setPengecualianSetakat(String pengecualianSetakat) {
        this.pengecualianSetakat = pengecualianSetakat;
    }

    public String getPengecualianSetakatLihat() {
        return pengecualianSetakatLihat;
    }

    public void setPengecualianSetakatLihat(String pengecualianSetakatLihat) {
        this.pengecualianSetakatLihat = pengecualianSetakatLihat;
    }

    public String getNoAturan() {
        return noAturan;
    }

    public void setNoAturan(String noAturan) {
        this.noAturan = noAturan;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public BigDecimal getAnggaranLuas() {
        return anggaranLuas;
    }

    public void setAnggaranLuas(BigDecimal anggaranLuas) {
        this.anggaranLuas = anggaranLuas;
    }

    public KodUOM getAnggaranLuatUOM() {
        return anggaranLuatUOM;
    }

    public void setAnggaranLuatUOM(KodUOM anggaranLuatUOM) {
        this.anggaranLuatUOM = anggaranLuatUOM;
    }

    public KodKategoriTanah getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(KodKategoriTanah kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public String getBayaranUkur() {
        return bayaranUkur;
    }

    public void setBayaranUkur(String bayaranUkur) {
        this.bayaranUkur = bayaranUkur;
    }

    public String getDiUkurOleh() {
        return diUkurOleh;
    }

    public void setDiUkurOleh(String diUkurOleh) {
        this.diUkurOleh = diUkurOleh;
    }

    public String getPengencualian() {
        return pengencualian;
    }

    public void setPengencualian(String pengencualian) {
        this.pengencualian = pengencualian;
    }

    public String getPerengganKtn() {
        return perengganKtn;
    }

    public void setPerengganKtn(String perengganKtn) {
        this.perengganKtn = perengganKtn;
    }

    public String getNoResitD() {
        return noResitD;
    }

    public void setNoResitD(String noResitD) {
        this.noResitD = noResitD;
    }

    public Date getTarikhResitD() {
        return tarikhResitD;
    }

    public void setTarikhResitD(Date tarikhResitD) {
        this.tarikhResitD = tarikhResitD;
    }

    public String getRujukanKtn() {
        return rujukanKtn;
    }

    public void setRujukanKtn(String rujukanKtn) {
        this.rujukanKtn = rujukanKtn;
    }
}
