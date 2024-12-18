/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import etanah.model.strata.BadanPengurusan;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Suhairi
 */
@Entity
@Table(name = "MOHON_SKIM")
@SequenceGenerator(name = "seq_mohon_skim", sequenceName = "seq_mohon_skim" , allocationSize = 1)
 
public class PermohonanSkim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_skim")
    @Column(name = "ID_SKIM")
    private Long idSkim;
    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;
    @Column(name = "NO_SKIM")
    private String noSkim;
    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;
    @Column(name = "NO_PA")
    private String noPa;
    @Column(name = "NO_FAIL_UKUR")
    private String noFailUkur;
    @Column(name = "NO_FAIL_PT")
    private String noFailPt;
    @Column(name = "NO_FAIL_PTG")
    private String noFailPtg;
    @Column(name = "DIUKUR")
    private String diukur;
    @ManyToOne
    @JoinColumn(name = "kod_pengenalan_pengukur")
    private KodJenisPengenalan jenisPengenalanPengukur;
    @Column(name = "NO_KP_PENGUKUR")
    private String noKpPengukur;

    @ManyToOne
    @JoinColumn(name = "kod_tujuan_ukur")
    private KodTujuanUkur kodTujuanUkur;

    @ManyToOne
    @JoinColumn(name = "id_bdn")
    private BadanPengurusan badanPengurusan;

    @Column(name = "TRH_SIAP")
    private Date trhSiap;

    @Column(name = "BIL_PETAK")
    private Long bilPetak;
    @Column(name = "BIL_AKSR")
    private Long bilAksr;
    @Column(name = "TRH_TERIMA_CF")
    private Date trhTerimaCf;
    @Column(name = "TRH_LULUS_CF")
    private Date trhLulusCf;
    @Column(name = "NAMA_PEMAJU")
    private String namaPemaju;
    @Column(name = "NAMA_PROJEK")
    private String namaProjek;
    @Column(name = "NO_RUJ_JUBL")
    private String noRujJubl;
    @Column(name = "NO_RUJ_LJT")
    private String noRujLjt;
    @Column(name = "NO_FAIL_UKUR_SEMULA")
    private String noFailUkurSemula;
    @Column(name = "JUMLAH_UNIT_SYER")
    private Long jumlahUnitSyer;
    @Embedded
    private InfoAudit infoAudit;

    public PermohonanSkim() {
    }

    public PermohonanSkim(Long idSkim) {
        this.idSkim = idSkim;
    }

    public Long getIdSkim() {
        return idSkim;
    }

    public void setIdSkim(Long idSkim) {
        this.idSkim = idSkim;
    }

    public String getNoSkim() {
        return noSkim;
    }

    public void setNoSkim(String noSkim) {
        this.noSkim = noSkim;
    }

    public String getNoPa() {
        return noPa;
    }

    public void setNoPa(String noPa) {
        this.noPa = noPa;
    }

    public String getNoFailUkur() {
        return noFailUkur;
    }

    public void setNoFailUkur(String noFailUkur) {
        this.noFailUkur = noFailUkur;
    }

    public String getNoFailPt() {
        return noFailPt;
    }

    public void setNoFailPt(String noFailPt) {
        this.noFailPt = noFailPt;
    }

    public String getNoFailPtg() {
        return noFailPtg;
    }

    public void setNoFailPtg(String noFailPtg) {
        this.noFailPtg = noFailPtg;
    }

    public String getDiukur() {
        return diukur;
    }

    public void setDiukur(String diukur) {
        this.diukur = diukur;
    }

    public String getNoKpPengukur() {
        return noKpPengukur;
    }

    public void setNoKpPengukur(String noKpPengukur) {
        this.noKpPengukur = noKpPengukur;
    }

    public Date getTrhSiap() {
        return trhSiap;
    }

    public void setTrhSiap(Date trhSiap) {
        this.trhSiap = trhSiap;
    }

    public Long getBilPetak() {
        return bilPetak;
    }

    public void setBilPetak(Long bilPetak) {
        this.bilPetak = bilPetak;
    }

    public Long getBilAksr() {
        return bilAksr;
    }

    public void setBilAksr(Long bilAksr) {
        this.bilAksr = bilAksr;
    }

    public Date getTrhTerimaCf() {
        return trhTerimaCf;
    }

    public void setTrhTerimaCf(Date trhTerimaCf) {
        this.trhTerimaCf = trhTerimaCf;
    }

    public Date getTrhLulusCf() {
        return trhLulusCf;
    }

    public void setTrhLulusCf(Date trhLulusCf) {
        this.trhLulusCf = trhLulusCf;
    }

    public String getNamaPemaju() {
        return namaPemaju;
    }

    public void setNamaPemaju(String namaPemaju) {
        this.namaPemaju = namaPemaju;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public String getNoRujJubl() {
        return noRujJubl;
    }

    public void setNoRujJubl(String noRujJubl) {
        this.noRujJubl = noRujJubl;
    }

    public String getNoRujLjt() {
        return noRujLjt;
    }

    public void setNoRujLjt(String noRujLjt) {
        this.noRujLjt = noRujLjt;
    }

    public String getNoFailUkurSemula() {
        return noFailUkurSemula;
    }

    public void setNoFailUkurSemula(String noFailUkurSemula) {
        this.noFailUkurSemula = noFailUkurSemula;
    }

    public Long getJumlahUnitSyer() {
        return jumlahUnitSyer;
    }

    public void setJumlahUnitSyer(Long jumlahUnitSyer) {
        this.jumlahUnitSyer = jumlahUnitSyer;
    }

    public BadanPengurusan getBadanPengurusan() {
        return badanPengurusan;
    }

    public void setBadanPengurusan(BadanPengurusan badanPengurusan) {
        this.badanPengurusan = badanPengurusan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodJenisPengenalan getJenisPengenalanPengukur() {
        return jenisPengenalanPengukur;
    }

    public void setJenisPengenalanPengukur(KodJenisPengenalan jenisPengenalanPengukur) {
        this.jenisPengenalanPengukur = jenisPengenalanPengukur;
    }

    public KodTujuanUkur getKodTujuanUkur() {
        return kodTujuanUkur;
    }

    public void setKodTujuanUkur(KodTujuanUkur kodTujuanUkur) {
        this.kodTujuanUkur = kodTujuanUkur;
    }


}
