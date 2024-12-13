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
@Table(name = "MOHON_PERMIT_BUTIR")
@SequenceGenerator(name = "seq_mohon_permit_butir", sequenceName = "seq_mohon_permit_butir")
@NamedQueries({
    @NamedQuery(name = "PermohonanPermitButir.findAll", query = "SELECT m FROM PermohonanPermitButir m")})
public class PermohonanPermitButir implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_permit_butir")
    @Column(name = "ID_MPERMIT_BTR")
    private Long idMpermitBtr;


    @ManyToOne
    @JoinColumn (name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @Column(name = "NO_BLOK")
    private String noBlok;
    @Column(name = "NO_TINGKAT")
    private String noTingkat;
    @Column(name = "NO_PETAK")
    private String noPetak;
    @Column(name = "JENIS_STRUK_TAMBAH")
    private String jenisStrukTambah;
    @Column(name = "BIL_STRUK_TAMBAH")
    private Integer bilStrukTambah;
    @Column(name = "DIBINA_OLEH")
    private String dibinaOleh;
    @Column(name = "KEDUDUKAN_STRUK_TAMBAH")
    private String kedudukanStrukTambah;
    @Column(name = "BIL_PERMIT")
    private Integer bilPermit;
    @Column(name = "HARGA_PERMIT")
    private BigDecimal hargaPermit;
    @Column(name = "JUM_BAYARAN")
    private BigDecimal jumBayaran;
    
    @Column (name = "TARIKH_MULA")
    private Date tarikhMula;
    @Column (name = "TARIKH_AKHIR")
    private Date tarikhAkhir;
    @Column(name = "JUMLAH_KK")
    private String jumlahKk;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "ID_HAKMILIK")
    private Hakmilik hakmilik;
    @Column(name = "NO_FAIL")
    private String noFail;
    @Column(name = "LAIN")
    private String lain;


    public PermohonanPermitButir() {
    }

	@Embedded
	private InfoAudit infoAudit;

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PermohonanPermitButir(Long idMpermitBtr) {
        this.idMpermitBtr = idMpermitBtr;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }


    public Long getIdMpermitBtr() {
        return idMpermitBtr;
    }

    public void setIdMpermitBtr(Long idMpermitBtr) {
        this.idMpermitBtr = idMpermitBtr;
    }

    public String getNoBlok() {
        return noBlok;
    }

    public void setNoBlok(String noBlok) {
        this.noBlok = noBlok;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public String getJenisStrukTambah() {
        return jenisStrukTambah;
    }

    public void setJenisStrukTambah(String jenisStrukTambah) {
        this.jenisStrukTambah = jenisStrukTambah;
    }

    public Integer getBilStrukTambah() {
        return bilStrukTambah;
    }

    public void setBilStrukTambah(Integer bilStrukTambah) {
        this.bilStrukTambah = bilStrukTambah;
    }

    public String getDibinaOleh() {
        return dibinaOleh;
    }

    public void setDibinaOleh(String dibinaOleh) {
        this.dibinaOleh = dibinaOleh;
    }

    public String getKedudukanStrukTambah() {
        return kedudukanStrukTambah;
    }

    public void setKedudukanStrukTambah(String kedudukanStrukTambah) {
        this.kedudukanStrukTambah = kedudukanStrukTambah;
    }

    public Integer getBilPermit() {
        return bilPermit;
    }

    public void setBilPermit(Integer bilPermit) {
        this.bilPermit = bilPermit;
    }

    public BigDecimal getHargaPermit() {
        return hargaPermit;
    }

    public void setHargaPermit(BigDecimal hargaPermit) {
        this.hargaPermit = hargaPermit;
    }

    public BigDecimal getJumBayaran() {
        return jumBayaran;
    }

    public void setJumBayaran(BigDecimal jumBayaran) {
        this.jumBayaran = jumBayaran;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Date getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(Date tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

    public String getJumlahKk() {
        return jumlahKk;
    }

    public void setJumlahKk(String jumlahKk) {
        this.jumlahKk = jumlahKk;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getLain() {
        return lain;
    }

    public void setLain(String lain) {
        this.lain = lain;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMpermitBtr != null ? idMpermitBtr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermohonanPermitButir)) {
            return false;
        }
        PermohonanPermitButir other = (PermohonanPermitButir) object;
        if ((this.idMpermitBtr == null && other.idMpermitBtr != null) || (this.idMpermitBtr != null && !this.idMpermitBtr.equals(other.idMpermitBtr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.MohonPermitButir[idMpermitBtr=" + idMpermitBtr + "]";
    }

}
