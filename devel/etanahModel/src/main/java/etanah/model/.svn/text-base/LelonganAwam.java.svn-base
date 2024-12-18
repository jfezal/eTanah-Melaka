/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table (name = "lelong_awam")
@SequenceGenerator(name = "seq_lelong_awam", sequenceName = "seq_lelong_awam")
public class LelonganAwam implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_lelong_awam")
    @Column(name = "ID_LEL_AWAM")
    private Long idLelonganAwam;
    
    @Column(name = "HRG_BYRN_BORANG")
    private BigDecimal bayaranBorang;
    
    @Column(name = "HRG_DEPOSIT")
    private BigDecimal deposit;
    
    @Column(name = "TRH_MULA_BORANG")
    private Date tarikhMulaBorang;
    
    @Column(name = "TRH_AKHIR_BORANG")
    private Date tarikhAkhirBorang;
    
    @Column(name = "TRH_LWTN_TAPAK")
    private Date tarikhLawatanTapak;
    
    @Column(name = "WKT_LWTN_TAPAK")
    private String waktuLawatanTapak;
    
    @Column(name = "TRH_TAMAT_DFTR")
    private Date tarikhTamatPendaftaran;
    
    @Column(name = "WKT_TAMAT_DFTR")
    private String waktuTamatPendaftaran;
    
    @Column(name = "TRH_LELONG")
    private Date tarikhLelongan;
    
    @Column(name = "WKT_LELONG")
    private String waktuLelongan;
     
    @Column(name = "TMPT_LELONG")
    private String tempatLelongan;
    
    @Column(name = "HRG_RIZAB")
    private BigDecimal hargaRizab;
    
    @Column(name = "HRG_BIDA")
    private BigDecimal hargaBidaan;
    
    @Column(name = "BYRN_DEPOSIT")
    private BigDecimal depositBayaran;
    
    @Column(name = "BYRN_BAKI")
    private BigDecimal bakiBayaran;

    @Embedded
    private InfoAudit infoAudit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PEMOHON")
    private Pemohon pemohon;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MOHON")
    private Permohonan permohonan;

    public Long getIdLelonganAwam() {
        return idLelonganAwam;
    }

    public void setIdLelonganAwam(Long idLelonganAwam) {
        this.idLelonganAwam = idLelonganAwam;
    }

    public BigDecimal getBayaranBorang() {
        return bayaranBorang;
    }

    public void setBayaranBorang(BigDecimal bayaranBorang) {
        this.bayaranBorang = bayaranBorang;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Date getTarikhMulaBorang() {
        return tarikhMulaBorang;
    }

    public void setTarikhMulaBorang(Date tarikhMulaBorang) {
        this.tarikhMulaBorang = tarikhMulaBorang;
    }

    public Date getTarikhAkhirBorang() {
        return tarikhAkhirBorang;
    }

    public void setTarikhAkhirBorang(Date tarikhAkhirBorang) {
        this.tarikhAkhirBorang = tarikhAkhirBorang;
    }

    public Date getTarikhLawatanTapak() {
        return tarikhLawatanTapak;
    }

    public void setTarikhLawatanTapak(Date tarikhLawatanTapak) {
        this.tarikhLawatanTapak = tarikhLawatanTapak;
    }

    public String getWaktuLawatanTapak() {
        return waktuLawatanTapak;
    }

    public void setWaktuLawatanTapak(String waktuLawatanTapak) {
        this.waktuLawatanTapak = waktuLawatanTapak;
    }

    public Date getTarikhTamatPendaftaran() {
        return tarikhTamatPendaftaran;
    }

    public void setTarikhTamatPendaftaran(Date tarikhTamatPendaftaran) {
        this.tarikhTamatPendaftaran = tarikhTamatPendaftaran;
    }

    public String getWaktuTamatPendaftaran() {
        return waktuTamatPendaftaran;
    }

    public void setWaktuTamatPendaftaran(String waktuTamatPendaftaran) {
        this.waktuTamatPendaftaran = waktuTamatPendaftaran;
    }

    public Date getTarikhLelongan() {
        return tarikhLelongan;
    }

    public void setTarikhLelongan(Date tarikhLelongan) {
        this.tarikhLelongan = tarikhLelongan;
    }

    public String getWaktuLelongan() {
        return waktuLelongan;
    }

    public void setWaktuLelongan(String waktuLelongan) {
        this.waktuLelongan = waktuLelongan;
    }

    public String getTempatLelongan() {
        return tempatLelongan;
    }

    public void setTempatLelongan(String tempatLelongan) {
        this.tempatLelongan = tempatLelongan;
    }

    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public BigDecimal getHargaBidaan() {
        return hargaBidaan;
    }

    public void setHargaBidaan(BigDecimal hargaBidaan) {
        this.hargaBidaan = hargaBidaan;
    }

    public BigDecimal getDepositBayaran() {
        return depositBayaran;
    }

    public void setDepositBayaran(BigDecimal depositBayaran) {
        this.depositBayaran = depositBayaran;
    }

    public BigDecimal getBakiBayaran() {
        return bakiBayaran;
    }

    public void setBakiBayaran(BigDecimal bakiBayaran) {
        this.bakiBayaran = bakiBayaran;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}
