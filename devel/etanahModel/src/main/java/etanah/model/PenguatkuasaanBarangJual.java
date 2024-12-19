/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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

/**
 *
 * @author nimda
 */
@Entity
@Table(name = "KKUASA_BRG_JUAL")
@SequenceGenerator(name = "SEQ_KKUASA_BRG_JUAL", sequenceName = "SEQ_KKUASA_BRG_JUAL")
public class PenguatkuasaanBarangJual implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_KKUASA_BRG_JUAL")
    @Column(name = "id_brg_jual")
    private Long idBarangJual;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_op")
    private OperasiPenguatkuasaan operasi;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_brg")
    private BarangRampasan rampasan;
    
    @Column(name = "kntt")
    private int kuantiti;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kntt_unit")
    private KodUOM unitKuantiti;
    
    @Column (name = "amaun", precision = 12, scale = 2, columnDefinition = "NUMBER(12,2)")
    private BigDecimal amaun;
    
    @Column (name = "trh_bayar")
    private Date tarikhBayaran;
    
    @Column(name = "id_kew_dok")
    private String idDokumenKewangan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kos")
    private PermohonanTuntutanKos kos;  
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pemohon")
    private Pemohon pemohon;  
    
    @Column(name = "catatan")
    private String catatan;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdBarangJual() {
        return idBarangJual;
    }

    public void setIdBarangJual(Long idBarangJual) {
        this.idBarangJual = idBarangJual;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public OperasiPenguatkuasaan getOperasi() {
        return operasi;
    }

    public void setOperasi(OperasiPenguatkuasaan operasi) {
        this.operasi = operasi;
    }

    public BarangRampasan getRampasan() {
        return rampasan;
    }

    public void setRampasan(BarangRampasan rampasan) {
        this.rampasan = rampasan;
    }

    public int getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public KodUOM getUnitKuantiti() {
        return unitKuantiti;
    }

    public void setUnitKuantiti(KodUOM unitKuantiti) {
        this.unitKuantiti = unitKuantiti;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public Date getTarikhBayaran() {
        return tarikhBayaran;
    }

    public void setTarikhBayaran(Date tarikhBayaran) {
        this.tarikhBayaran = tarikhBayaran;
    }

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public PermohonanTuntutanKos getKos() {
        return kos;
    }

    public void setKos(PermohonanTuntutanKos kos) {
        this.kos = kos;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
