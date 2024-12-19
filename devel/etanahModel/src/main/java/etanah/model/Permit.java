/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "PERMIT")
@SequenceGenerator(name = "seq_permit", sequenceName = "seq_permit")
@NamedQueries({
    @NamedQuery(name = "Permit.findAll", query = "SELECT p FROM Permit p")})
public class Permit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permit")
    @Column(name = "ID_PERMIT")
    private Long idPermit;
    @Column(name = "NO_PERMIT")
    private String noPermit;
    @Column(name = "tarikh_permit")
    private Date tarikhPermit;
    @Column(name = "tarikh_permit_mula")
    private Date tarikhPermitMula;
    @Column(name = "tarikh_permit_akhir")
    private Date tarikhpermitAkhir;

    @ManyToOne
    @JoinColumn (name = "id_mpermit_btr")
    private PermohonanPermitButir permohonanPermitButir;

    @ManyToOne
    @JoinColumn (name = "kod_caw")
    private KodCawangan kodCawangan;

    @ManyToOne
    @JoinColumn (name = "jenis_permit")
    private KodJenisPermit kodJenisPermit;

    @ManyToOne
    @JoinColumn (name = "tempoh_sah_uom")
    private KodUOM tempohSahUom;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_pihak")
    private Pihak pihak;
    
    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "no_permit_baru")
    private String noPermitBaru;
    
    @Column(name = "peruntukan_tambah")
    private String peruntukanTambahan;
    @Column(name = "tempoh_sah")
    private Integer tempohSah;
    @Column(name = "thn_akhir")
    private Integer tahunAkhir;
    
    @Column(name = "nopermit_str")
    private Integer noPermitStrata;
    
    @Column(name = "KAEDAH")
    private String kaedah;
    
    @Column(name = "PERUNTUKAN_TAMBAH_LAIN")
    private String peruntukanTambahanLain;

    public Permit() {
    }

    public Permit(Long idPermit) {
        this.idPermit = idPermit;
    }

	@Embedded
	private InfoAudit infoAudit;

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Long getIdPermit() {
        return idPermit;
    }

    public void setIdPermit(Long idPermit) {
        this.idPermit = idPermit;
    }

    public String getNoPermit() {
        return noPermit;
    }

    public void setNoPermit(String noPermit) {
        this.noPermit = noPermit;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPermitButir getPermohonanPermitButir() {
        return permohonanPermitButir;
    }

    public void setPermohonanPermitButir(PermohonanPermitButir permohonanPermitButir) {
        this.permohonanPermitButir = permohonanPermitButir;
    }

    public Date getTarikhPermit() {
        return tarikhPermit;
    }

    public void setTarikhPermit(Date tarikhPermit) {
        this.tarikhPermit = tarikhPermit;
    }

    public Date getTarikhPermitMula() {
        return tarikhPermitMula;
    }

    public void setTarikhPermitMula(Date tarikhPermitMula) {
        this.tarikhPermitMula = tarikhPermitMula;
    }

    public Date getTarikhpermitAkhir() {
        return tarikhpermitAkhir;
    }

    public void setTarikhpermitAkhir(Date tarikhpermitAkhir) {
        this.tarikhpermitAkhir = tarikhpermitAkhir;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getPeruntukanTambahan() {
        return peruntukanTambahan;
    }

    public void setPeruntukanTambahan(String peruntukanTambahan) {
        this.peruntukanTambahan = peruntukanTambahan;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public KodJenisPermit getKodJenisPermit() {
        return kodJenisPermit;
    }

    public void setKodJenisPermit(KodJenisPermit kodJenisPermit) {
        this.kodJenisPermit = kodJenisPermit;
    }

    public Integer getTahunAkhir() {
        return tahunAkhir;
    }

    public void setTahunAkhir(Integer tahunAkhir) {
        this.tahunAkhir = tahunAkhir;
    }

    public Integer getTempohSah() {
        return tempohSah;
    }

    public void setTempohSah(Integer tempohSah) {
        this.tempohSah = tempohSah;
    }

    public KodUOM getTempohSahUom() {
        return tempohSahUom;
    }

    public void setTempohSahUom(KodUOM tempohSahUom) {
        this.tempohSahUom = tempohSahUom;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Integer getNoPermitStrata() {
        return noPermitStrata;
    }

    public void setNoPermitStrata(Integer noPermitStrata) {
        this.noPermitStrata = noPermitStrata;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermit != null ? idPermit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permit)) {
            return false;
        }
        Permit other = (Permit) object;
        if ((this.idPermit == null && other.idPermit != null) || (this.idPermit != null && !this.idPermit.equals(other.idPermit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.Permit[idPermit=" + idPermit + "]";
    }

    public String getKaedah() {
        return kaedah;
    }

    public void setKaedah(String kaedah) {
        this.kaedah = kaedah;
    }

    public String getPeruntukanTambahanLain() {
        return peruntukanTambahanLain;
    }

    public void setPeruntukanTambahanLain(String peruntukanTambahanLain) {
        this.peruntukanTambahanLain = peruntukanTambahanLain;
    }

    public String getNoPermitBaru() {
        return noPermitBaru;
    }

    public void setNoPermitBaru(String noPermitBaru) {
        this.noPermitBaru = noPermitBaru;
    }
    

}
