/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
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
 * @author ubuntu
 */
@Entity
@Table(name = "pnmptn_psrta")
@SequenceGenerator(name = "seq_pnmptn_psrta", sequenceName = "seq_pnmptn_psrta")
public class PenempatanPeserta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pnmptn_psrta")
    @Column(name = "pnmptn_psrta_id")
    private Long idPenempatanPeserta;
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukim;
    @Column (name = "nama")
    private String nama;
    @Column (name = "no_kp")
    private String noPengenalan;
    @Column(name = "alamat_1", length = 40)
    private String alamat1;
    @Column(name = "alamat_2", length = 40)
    private String alamat2;
    @Column(name = "alamat_3", length = 40)
    private String alamat3;
    @Column(name = "alamat_4", length = 40)
    private String alamat4;
    @Column(name = "poskod", length = 5)
    private String poskod;   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_negeri")
    private KodNegeri negeri;
    @Column (name = "umur")
    private Integer umur;
    @Column (name = "mrkh_tmdga")
    private Integer markahTemuduga;
    @Column (name = "no_lot")
    private String noLot;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdPenempatanPeserta() {
        return idPenempatanPeserta;
    }

    public void setIdPenempatanPeserta(Long idPenempatanPeserta) {
        this.idPenempatanPeserta = idPenempatanPeserta;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
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

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Integer getMarkahTemuduga() {
        return markahTemuduga;
    }

    public void setMarkahTemuduga(Integer markahTemuduga) {
        this.markahTemuduga = markahTemuduga;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
