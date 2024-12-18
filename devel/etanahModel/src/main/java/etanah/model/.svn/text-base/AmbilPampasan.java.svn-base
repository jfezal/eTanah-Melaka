/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import etanah.model.ambil.BorangPerPB;
import etanah.model.ambil.PerbicaraanKehadiran;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name = "AMBIL_PAMPASAN")
@SequenceGenerator(name = "seq_ambil_pampasan", sequenceName = "seq_ambil_pampasan")
@NamedQueries({
    @NamedQuery(name = "AmbilPampasan.findAll", query = "SELECT a FROM AmbilPampasan a")})
public class AmbilPampasan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ambil_pampasan")
    @Column(name = "ID_AMBIL_PAMPASAN")
    private Long idAmbilPampasan;

    @ManyToOne
    @JoinColumn(name = "id_borang_perpb")
    private BorangPerPB borangPerPb;
    @ManyToOne
    @JoinColumn(name = "id_hadir")
    private PerbicaraanKehadiran perbicaraanKehadiran;
    @Column(name = "JUM_TERIMA_PAMPASAN")
    private BigDecimal jumTerimaPampasan;

    @ManyToOne
    @JoinColumn(name = "KOD_CARA_BAYAR")
    private KodCaraBayaran kodCaraBayaran;

    @Column(name = "NO_DOK")
    private String noDok;
    @Column(name = "TARIKH_DOK")
    @Temporal(TemporalType.DATE)
    private Date tarikhDok;
    
    @Column(name = "TRH_TERIMA_CEK")
    @Temporal(TemporalType.DATE)
    private Date trhTerimaCek;

    @ManyToOne
    @JoinColumn(name = "KOD_BANK")
    private KodBank kodBank;

    @Column(name = "DOK_DIAMBIL")
    private Character dokDiambil;
    @Column(name = "TARIKH_DOK_DIAMBIL")
    @Temporal(TemporalType.DATE)
    private Date tarikhDokDiambil;

    @Column(name = "NO_RUJ")
    private String noRujukan;
    @Column(name = "NAMA")
    private String nama;
    @Column(name = "NOKP")
    private String noPengenalan;
//    @Column(name = "TRH_TERIMA_CEK")
//    private String trhTerimaCek;

    public AmbilPampasan() {
    }

    @Embedded
    private InfoAudit infoAudit;

    public AmbilPampasan(Long idAmbilPampasan) {
        this.idAmbilPampasan = idAmbilPampasan;
    }

    public Long getIdAmbilPampasan() {
        return idAmbilPampasan;
    }

    public void setIdAmbilPampasan(Long idAmbilPampasan) {
        this.idAmbilPampasan = idAmbilPampasan;
    }

    public BigDecimal getJumTerimaPampasan() {
        return jumTerimaPampasan;
    }

    public void setJumTerimaPampasan(BigDecimal jumTerimaPampasan) {
        this.jumTerimaPampasan = jumTerimaPampasan;
    }

    public KodCaraBayaran getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    public Date getTarikhDok() {
        return tarikhDok;
    }

    public void setTarikhDok(Date tarikhDok) {
        this.tarikhDok = tarikhDok;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

    public BorangPerPB getBorangPerPb() {
        return borangPerPb;
    }

    public void setBorangPerPb(BorangPerPB borangPerPb) {
        this.borangPerPb = borangPerPb;
    }

    public Character getDokDiambil() {
        return dokDiambil;
    }

    public void setDokDiambil(Character dokDiambil) {
        this.dokDiambil = dokDiambil;
    }

    public Date getTarikhDokDiambil() {
        return tarikhDokDiambil;
    }

    public void setTarikhDokDiambil(Date tarikhDokDiambil) {
        this.tarikhDokDiambil = tarikhDokDiambil;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }
    

    public Date getTrhTerimaCek() {
        return trhTerimaCek;
    }

    public void setTrhTerimaCek(Date trhTerimaCek) {
        this.trhTerimaCek = trhTerimaCek;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAmbilPampasan != null ? idAmbilPampasan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmbilPampasan)) {
            return false;
        }
        AmbilPampasan other = (AmbilPampasan) object;
        if ((this.idAmbilPampasan == null && other.idAmbilPampasan != null) || (this.idAmbilPampasan != null && !this.idAmbilPampasan.equals(other.idAmbilPampasan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.model.AmbilPampasan[idAmbilPampasan=" + idAmbilPampasan + "]";
    }

}
