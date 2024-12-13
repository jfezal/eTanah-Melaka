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
 * @author afham
 */
@Entity
@Table(name = "aduan_portal")
@SequenceGenerator(name = "seq_aduan_portal", sequenceName = "seq_aduan_portal")
public class AduanPortal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_portal")
    @Column(name = "id_aduan")
    private long idAduan;
    
    @Column(name = "nama", length = 200)
    private String nama;
    
    @Column(name = "no_pengenalan", length = 20)
    private String noPengenalan;
    
    @Column(name = "no_tel")
    private String noTelefon;
        
    @Column(name = "email")
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jenis")
    private KodAduanPortal kodAduanPortal;
            
    @Column(name = "tarikh_aduan")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhAduan;
             
    @Column(name = "penerangan")
    private String penerangan;
    
    @Column(name = "officer")
    private String officer;
    
    @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "status")
    private KodStatusPortal kodStatusPortal;
     
     @Column(name = "maklumblas")
    private String maklumBalas;
     
     @Column(name = "tarikh_selesai")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhSelesai;
     
     
    @Embedded
    private InfoAudit infoAudit;

    public long getIdAduan() {
        return idAduan;
    }

    public void setIdAduan(long idAduan) {
        this.idAduan = idAduan;
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

    public String getNoTelefon() {
        return noTelefon;
    }

    public void setNoTelefon(String noTelefon) {
        this.noTelefon = noTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTarikhAduan() {
        return tarikhAduan;
    }

    public void setTarikhAduan(Date tarikhAduan) {
        this.tarikhAduan = tarikhAduan;
    }

    public String getPenerangan() {
        return penerangan;
    }

    public void setPenerangan(String penerangan) {
        this.penerangan = penerangan;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public String getMaklumBalas() {
        return maklumBalas;
    }

    public void setMaklumBalas(String maklumBalas) {
        this.maklumBalas = maklumBalas;
    }

    public Date getTarikhSelesai() {
        return tarikhSelesai;
    }

    public void setTarikhSelesai(Date tarikhSelesai) {
        this.tarikhSelesai = tarikhSelesai;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodAduanPortal getKodAduanPortal() {
        return kodAduanPortal;
    }

    public void setKodAduanPortal(KodAduanPortal kodAduanPortal) {
        this.kodAduanPortal = kodAduanPortal;
    }

    public KodStatusPortal getKodStatusPortal() {
        return kodStatusPortal;
    }

    public void setKodStatusPortal(KodStatusPortal kodStatusPortal) {
        this.kodStatusPortal = kodStatusPortal;
    }

    
    
    
}
