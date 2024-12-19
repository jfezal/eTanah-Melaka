package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "aduan_siasat")
@SequenceGenerator(name = "seq_aduan_siasat", sequenceName = "seq_aduan_siasat")
public class AduanSiasatan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_siasat")
        @Column(name = "id_siasat")
    private long idSiasatan;

    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @Column(name = "trh_siasat")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhSiasat;
    // tempat aduan
    @ManyToOne
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukim;
    
    @Column (name = "tanah")
    private String laporanTanah;
    
    @Column(name = "lokasi")
    private String laporanLokasi;
    @Column(name = "geog")
    private String laporanGeografi;
    @Column(name = "jln")
    private String laporanJalan;
    @Column(name = "kemudahan")
    private String kemudahanAsas;
    @Column(name = "atvt")
    private String aktiviti;
    @Column(name = "ada_pembngn")
    private Character adaPembangunan;
    // LAPORAN SEMPADAN
    @Column(name = "spdn_utara_kjaan", length = 1)
    private Character sempadanUtaraMilikKerajaan;
    @Column(name = "spdn_utara_no_lot", length = 10)
    private String sempadanUtaraNoLot;
    @Column(name = "spdn_utara_guna", length = 50)
    private String sempadanUtaraKegunaan;
    @Column(name = "spdn_selatan_kjaan", length = 1)
    private Character sempadanSelatanMilikKerajaan;
    @Column(name = "spdn_selatan_no_lot", length = 10)
    private String sempadanSelatanNoLot;
    @Column(name = "spdn_selatan_guna", length = 50)
    private String sempadanSelatanKegunaan;
    @Column(name = "spdn_timur_kjaan", length = 1)
    private Character sempadanTimurMilikKerajaan;
    @Column(name = "spdn_timur_no_lot", length = 10)
    private String sempadanTimurNoLot;
    @Column(name = "spdn_timur_guna", length = 50)
    private String sempadanTimurKegunaan;
    @Column(name = "spdn_barat_kjaan", length = 1)
    private Character sempadanBaratMilikKerajaan;
    @Column(name = "spdn_barat_no_lot", length = 10)
    private String sempadanBaratNoLot;
    @Column(name = "spdn_barat_guna", length = 50)
    private String sempadanBaratKegunaan;

    @Column (name = "catatan")
    private String catatan;

    @Embedded
    private InfoAudit infoAudit;

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonan(){
        return permohonan;
    }

    public Aduan getAduan(){
        return getPermohonan().getAduan();
    }

    public Character getAdaPembangunan() {
        return adaPembangunan;
    }

    public void setAdaPembangunan(Character adaPembangunan) {
        this.adaPembangunan = adaPembangunan;
    }

    public String getAktiviti() {
        return aktiviti;
    }

    public void setAktiviti(String aktiviti) {
        this.aktiviti = aktiviti;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public void setLaporanTanah(String laporanTanah) {
		this.laporanTanah = laporanTanah;
	}

	public String getLaporanTanah() {
		return laporanTanah;
	}

	public long getIdSiasatan() {
        return idSiasatan;
    }

    public void setIdSiasatan(long idSiasatan) {
        this.idSiasatan = idSiasatan;
    }

    public String getKemudahanAsas() {
        return kemudahanAsas;
    }

    public void setKemudahanAsas(String kemudahanAsas) {
        this.kemudahanAsas = kemudahanAsas;
    }

    public String getLaporanGeografi() {
        return laporanGeografi;
    }

    public void setLaporanGeografi(String laporanGeografi) {
        this.laporanGeografi = laporanGeografi;
    }

    public String getLaporanJalan() {
        return laporanJalan;
    }

    public void setLaporanJalan(String laporanJalan) {
        this.laporanJalan = laporanJalan;
    }

    public String getLaporanLokasi() {
        return laporanLokasi;
    }

    public void setLaporanLokasi(String laporanLokasi) {
        this.laporanLokasi = laporanLokasi;
    }

    public Date getTarikhSiasat() {
        return tarikhSiasat;
    }

    public void setTarikhSiasat(Date tarikhSiasat) {
        this.tarikhSiasat = tarikhSiasat;
    }

    public Character getSempadanUtaraMilikKerajaan() {
        return sempadanUtaraMilikKerajaan;
    }

    public void setSempadanUtaraMilikKerajaan(Character sempadanUtaraMilikKerajaan) {
        this.sempadanUtaraMilikKerajaan = sempadanUtaraMilikKerajaan;
    }

    public String getSempadanUtaraNoLot() {
        return sempadanUtaraNoLot;
    }

    public void setSempadanUtaraNoLot(String sempadanUtaraNoLot) {
        this.sempadanUtaraNoLot = sempadanUtaraNoLot;
    }

    public String getSempadanUtaraKegunaan() {
        return sempadanUtaraKegunaan;
    }

    public void setSempadanUtaraKegunaan(String sempadanUtaraKegunaan) {
        this.sempadanUtaraKegunaan = sempadanUtaraKegunaan;
    }

    public Character getSempadanSelatanMilikKerajaan() {
        return sempadanSelatanMilikKerajaan;
    }

    public void setSempadanSelatanMilikKerajaan(
            Character sempadanSelatanMilikKerajaan) {
        this.sempadanSelatanMilikKerajaan = sempadanSelatanMilikKerajaan;
    }

    public String getSempadanSelatanNoLot() {
        return sempadanSelatanNoLot;
    }

    public void setSempadanSelatanNoLot(String sempadanSelatanNoLot) {
        this.sempadanSelatanNoLot = sempadanSelatanNoLot;
    }

    public String getSempadanSelatanKegunaan() {
        return sempadanSelatanKegunaan;
    }

    public void setSempadanSelatanKegunaan(String sempadanSelatanKegunaan) {
        this.sempadanSelatanKegunaan = sempadanSelatanKegunaan;
    }

    public Character getSempadanTimurMilikKerajaan() {
        return sempadanTimurMilikKerajaan;
    }

    public void setSempadanTimurMilikKerajaan(Character sempadanTimurMilikKerajaan) {
        this.sempadanTimurMilikKerajaan = sempadanTimurMilikKerajaan;
    }

    public String getSempadanTimurNoLot() {
        return sempadanTimurNoLot;
    }

    public void setSempadanTimurNoLot(String sempadanTimurNoLot) {
        this.sempadanTimurNoLot = sempadanTimurNoLot;
    }

    public String getSempadanTimurKegunaan() {
        return sempadanTimurKegunaan;
    }

    public void setSempadanTimurKegunaan(String sempadanTimurKegunaan) {
        this.sempadanTimurKegunaan = sempadanTimurKegunaan;
    }

    public Character getSempadanBaratMilikKerajaan() {
        return sempadanBaratMilikKerajaan;
    }

    public void setSempadanBaratMilikKerajaan(Character sempadanBaratMilikKerajaan) {
        this.sempadanBaratMilikKerajaan = sempadanBaratMilikKerajaan;
    }

    public String getSempadanBaratNoLot() {
        return sempadanBaratNoLot;
    }

    public void setSempadanBaratNoLot(String sempadanBaratNoLot) {
        this.sempadanBaratNoLot = sempadanBaratNoLot;
    }

    public String getSempadanBaratKegunaan() {
        return sempadanBaratKegunaan;
    }

    public void setSempadanBaratKegunaan(String sempadanBaratKegunaan) {
        this.sempadanBaratKegunaan = sempadanBaratKegunaan;
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
