/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
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
 * @author fikri
 */
@Entity
@Table(name = "projek")
@SequenceGenerator(name = "SEQ_PROJEK", sequenceName = "SEQ_PROJEK" , allocationSize = 1)
public class Projek implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROJEK")
    @Column(name = "ID_PROJEK")
    private Long idProjek;
    
    @Column(name = "PEMAJU")
    private String namaPemaju;
    
    @Column(name = "JENIS")
    private String jenisProjek;
    
    @Column(name = "JUM_SEMUA_UNIT")
    private Integer jumlahSemuaUnit;
    
    @Column(name = "JUM_UNIT_SEMENTARA")
    private String jumlahUnitSementara;
    
    @Column(name = "UNIT_MAKS")
    private String maksimumUnit;
    
    @Column(name = "UNIT_DIJUAL")
    private Integer unitDiJual;
    
    @Column(name = "NO_RUJ_PROJEK")
    private String noRujukanProjek;
    
    @Column(name = "ID_MOHON")
    private String idPermohonan;
    
    @Column(name = "ID_HAKMILIK")
    private String idHakmilik;
    
    @Column(name = "AKTIF")
    private String aktif;
    
    @Column(name = "PEMILIK")
    private String pemilik;
    
    @Column(name = "NO_RUJ_FAIL")
    private String noRujFail;
    
    @Column(name = "FORMULA")
    private String formula;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GUNA_BNGN")
    private KodKegunaanBangunan gunaBngn;
    
    @Column (name = "TRH_LULUS")
    private Date tarikhLulus;
    
    @Column(name = "LAIN")
    private String kegunaanLain;
    
    @Column(name = "ARKITEK")
    private String arkitek;
    
    @Column(name = "BIL_AKSR")
    private Integer bilAksr;
    
    @Column (name = "TRH_LULUS_BNGN")
    private Date tarikhLulusBngn;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdProjek() {
        return idProjek;
    }

    public void setIdProjek(Long idProjek) {
        this.idProjek = idProjek;
    }

    public String getNamaPemaju() {
        return namaPemaju;
    }

    public void setNamaPemaju(String namaPemaju) {
        this.namaPemaju = namaPemaju;
    }

    public String getJenisProjek() {
        return jenisProjek;
    }

    public void setJenisProjek(String jenisProjek) {
        this.jenisProjek = jenisProjek;
    }

    public Integer getJumlahSemuaUnit() {
        return jumlahSemuaUnit;
    }

    public void setJumlahSemuaUnit(Integer jumlahSemuaUnit) {
        this.jumlahSemuaUnit = jumlahSemuaUnit;
    }
    
    public String getJumlahUnitSementara() {
        return jumlahUnitSementara;
    }

    public void setJumlahUnitSementara(String jumlahUnitSementara) {
        this.jumlahUnitSementara = jumlahUnitSementara;
    }

    public String getMaksimumUnit() {
        return maksimumUnit;
    }

    public void setMaksimumUnit(String maksimumUnit) {
        this.maksimumUnit = maksimumUnit;
    }

    public Integer getUnitDiJual() {
        return unitDiJual;
    }

    public void setUnitDiJual(Integer unitDiJual) {
        this.unitDiJual = unitDiJual;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNoRujukanProjek() {
        return noRujukanProjek;
    }

    public void setNoRujukanProjek(String noRujukanProjek) {
        this.noRujukanProjek = noRujukanProjek;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getNoRujFail() {
        return noRujFail;
    }

    public void setNoRujFail(String noRujFail) {
        this.noRujFail = noRujFail;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public KodKegunaanBangunan getGunaBngn() {
        return gunaBngn;
    }

    public void setGunaBngn(KodKegunaanBangunan gunaBngn) {
        this.gunaBngn = gunaBngn;
    }

    public Date getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(Date tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }

    public String getKegunaanLain() {
        return kegunaanLain;
    }

    public void setKegunaanLain(String kegunaanLain) {
        this.kegunaanLain = kegunaanLain;
    }

    public Integer getBilAksr() {
        return bilAksr;
    }

    public void setBilAksr(Integer bilAksr) {
        this.bilAksr = bilAksr;
    }

    public Date getTarikhLulusBngn() {
        return tarikhLulusBngn;
    }

    public void setTarikhLulusBngn(Date tarikhLulusBngn) {
        this.tarikhLulusBngn = tarikhLulusBngn;
    }

    public String getArkitek() {
        return arkitek;
    }

    public void setArkitek(String arkitek) {
        this.arkitek = arkitek;
    }
}
