/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_PERMIT")
@SequenceGenerator(name = "seq_mohon_permit", sequenceName = "seq_mohon_permit")

@NamedQueries({
    @NamedQuery(name = "PermohonanPermit.findAll", query = "SELECT m FROM PermohonanPermit m")})
public class PermohonanPermit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_permit")
    @Column(name = "ID_MPERMIT")
    private Long idMpermit;
    @Column(name = "ID_HAKMILIK")
    private String idHakmilik;
    @Column(name = "TARIKH_SIASATAN")
    @Temporal(TemporalType.DATE)
    private Date tarikhSiasatan;
    @Column(name = "JARAK_DARI_BANDAR")
    private BigDecimal jarakDariBandar;
    @Column(name = "JENIS_LOT_SPDN_UTARA")
    private String jenisLotSpdnUtara;
    @Column(name = "NO_LOT_UTARA")
    private String noLotUtara;
    @Column(name = "GUNA_LOT_SPDN_UTARA")
    private String gunaLotSpdnUtara;
    @Column(name = "JENIS_LOT_SPDN_SELATAN")
    private String jenisLotSpdnSelatan;
    @Column(name = "NO_LOT_SELATAN")
    private String noLotSelatan;
    @Column(name = "GUNA_LOT_SPDN_SELATAN")
    private String gunaLotSpdnSelatan;
    @Column(name = "JENIS_LOT_SPDN_TIMUR")
    private String jenisLotSpdnTimur;
    @Column(name = "NO_LOT_TIMUR")
    private String noLotTimur;
    @Column(name = "GUNA_LOT_SPDN_TIMUR")
    private String gunaLotSpdnTimur;
    @Column(name = "JENIS_LOT_SPDN_BARAT")
    private String jenisLotSpdnBarat;
    @Column(name = "NO_LOT_BARAT")
    private String noLotBarat;
    @Column(name = "GUNA_LOT_SPDN_BARAT")
    private String gunaLotSpdnBarat;
    @Column(name = "ADA_BGN")
    private Character adaBgn;
    @Column(name = "JENIS_PREMIS")
    private String jenisPremis;
    @Column(name = "KEADAAN_BGN")
    private String keadaanBgn;
    @Column(name = "STRUK_TAMBAH_JENIS")
    private String strukTambahJenis;
    @Column(name = "STRUK_TAMBAH_KED")
    private String strukTambahKed;
    @Column(name = "STRUK_TAMBAH_KED_TERLUNJUR")
    private String strukTambahKedTerlunjur;
    @Column(name = "CATATAN")
    private String catatan;
    @Column(name = "TARIKH_PERMIT_KELUAR")
    @Temporal(TemporalType.DATE)
    private Date tarikhPermitKeluar;
    @Column(name = "TARIKH_PERMIT_MULA")
    @Temporal(TemporalType.DATE)
    private Date tarikhPermitMula;
    @Column(name = "TARIKH_PERMIT_AKHIR")
    @Temporal(TemporalType.DATE)
    private Date tarikhPermitAkhir;


	@Embedded
	private InfoAudit infoAudit;

    public PermohonanPermit() {
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public PermohonanPermit(Long idMpermit) {
        this.idMpermit = idMpermit;
    }


    public Long getIdMpermit() {
        return idMpermit;
    }

    public void setIdMpermit(Long idMpermit) {
        this.idMpermit = idMpermit;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Date getTarikhSiasatan() {
        return tarikhSiasatan;
    }

    public void setTarikhSiasatan(Date tarikhSiasatan) {
        this.tarikhSiasatan = tarikhSiasatan;
    }

    public BigDecimal getJarakDariBandar() {
        return jarakDariBandar;
    }

    public void setJarakDariBandar(BigDecimal jarakDariBandar) {
        this.jarakDariBandar = jarakDariBandar;
    }

    public String getJenisLotSpdnUtara() {
        return jenisLotSpdnUtara;
    }

    public void setJenisLotSpdnUtara(String jenisLotSpdnUtara) {
        this.jenisLotSpdnUtara = jenisLotSpdnUtara;
    }

    public String getNoLotUtara() {
        return noLotUtara;
    }

    public void setNoLotUtara(String noLotUtara) {
        this.noLotUtara = noLotUtara;
    }

    public String getGunaLotSpdnUtara() {
        return gunaLotSpdnUtara;
    }

    public void setGunaLotSpdnUtara(String gunaLotSpdnUtara) {
        this.gunaLotSpdnUtara = gunaLotSpdnUtara;
    }

    public String getJenisLotSpdnSelatan() {
        return jenisLotSpdnSelatan;
    }

    public void setJenisLotSpdnSelatan(String jenisLotSpdnSelatan) {
        this.jenisLotSpdnSelatan = jenisLotSpdnSelatan;
    }

    public String getNoLotSelatan() {
        return noLotSelatan;
    }

    public void setNoLotSelatan(String noLotSelatan) {
        this.noLotSelatan = noLotSelatan;
    }

    public String getGunaLotSpdnSelatan() {
        return gunaLotSpdnSelatan;
    }

    public void setGunaLotSpdnSelatan(String gunaLotSpdnSelatan) {
        this.gunaLotSpdnSelatan = gunaLotSpdnSelatan;
    }

    public String getJenisLotSpdnTimur() {
        return jenisLotSpdnTimur;
    }

    public void setJenisLotSpdnTimur(String jenisLotSpdnTimur) {
        this.jenisLotSpdnTimur = jenisLotSpdnTimur;
    }

    public String getNoLotTimur() {
        return noLotTimur;
    }

    public void setNoLotTimur(String noLotTimur) {
        this.noLotTimur = noLotTimur;
    }

    public String getGunaLotSpdnTimur() {
        return gunaLotSpdnTimur;
    }

    public void setGunaLotSpdnTimur(String gunaLotSpdnTimur) {
        this.gunaLotSpdnTimur = gunaLotSpdnTimur;
    }

    public String getJenisLotSpdnBarat() {
        return jenisLotSpdnBarat;
    }

    public void setJenisLotSpdnBarat(String jenisLotSpdnBarat) {
        this.jenisLotSpdnBarat = jenisLotSpdnBarat;
    }

    public String getNoLotBarat() {
        return noLotBarat;
    }

    public void setNoLotBarat(String noLotBarat) {
        this.noLotBarat = noLotBarat;
    }

    public String getGunaLotSpdnBarat() {
        return gunaLotSpdnBarat;
    }

    public void setGunaLotSpdnBarat(String gunaLotSpdnBarat) {
        this.gunaLotSpdnBarat = gunaLotSpdnBarat;
    }

    public Character getAdaBgn() {
        return adaBgn;
    }

    public void setAdaBgn(Character adaBgn) {
        this.adaBgn = adaBgn;
    }

    public String getJenisPremis() {
        return jenisPremis;
    }

    public void setJenisPremis(String jenisPremis) {
        this.jenisPremis = jenisPremis;
    }

    public String getKeadaanBgn() {
        return keadaanBgn;
    }

    public void setKeadaanBgn(String keadaanBgn) {
        this.keadaanBgn = keadaanBgn;
    }

    public String getStrukTambahJenis() {
        return strukTambahJenis;
    }

    public void setStrukTambahJenis(String strukTambahJenis) {
        this.strukTambahJenis = strukTambahJenis;
    }

    public String getStrukTambahKed() {
        return strukTambahKed;
    }

    public void setStrukTambahKed(String strukTambahKed) {
        this.strukTambahKed = strukTambahKed;
    }

    public String getStrukTambahKedTerlunjur() {
        return strukTambahKedTerlunjur;
    }

    public void setStrukTambahKedTerlunjur(String strukTambahKedTerlunjur) {
        this.strukTambahKedTerlunjur = strukTambahKedTerlunjur;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Date getTarikhPermitKeluar() {
        return tarikhPermitKeluar;
    }

    public void setTarikhPermitKeluar(Date tarikhPermitKeluar) {
        this.tarikhPermitKeluar = tarikhPermitKeluar;
    }

    public Date getTarikhPermitMula() {
        return tarikhPermitMula;
    }

    public void setTarikhPermitMula(Date tarikhPermitMula) {
        this.tarikhPermitMula = tarikhPermitMula;
    }

    public Date getTarikhPermitAkhir() {
        return tarikhPermitAkhir;
    }

    public void setTarikhPermitAkhir(Date tarikhPermitAkhir) {
        this.tarikhPermitAkhir = tarikhPermitAkhir;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMpermit != null ? idMpermit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermohonanPermit)) {
            return false;
        }
        PermohonanPermit other = (PermohonanPermit) object;
        if ((this.idMpermit == null && other.idMpermit != null) || (this.idMpermit != null && !this.idMpermit.equals(other.idMpermit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.MohonPermit[idMpermit=" + idMpermit + "]";
    }

}
