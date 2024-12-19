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

/**
 *
 * @author fikri
 */
@Entity
@Table(name = "mohon_pihak_pendeposit")
@SequenceGenerator(name = "seq_mohon_pihak_pendeposit", sequenceName = "seq_mohon_pihak_pendeposit")
public class PermohonanPihakPendeposit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_pihak_pendeposit")
    @Column(name = "ID_MPP")
    private Long idPermohonanPihakPendeposit;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @Column(name = "KATG_PIHAK")
    private String kategoriPihak;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pihak")
    private Pihak pihak;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_jantina")
    private KodJantina jantina;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    
    @Column(name = "no_pengenalan")
    private String noPengenalan;
    
    @Column(name = "nama")
    private String nama;
    
    @Column(name = "trh_bantah")
    private Date tarikhBantahan;
    
    @Column(name = "alamat1", length = 40)
    private String alamat1;
    
    @Column(name = "alamat2", length = 40)
    private String alamat2;
    
    @Column(name = "alamat3", length = 40)
    private String alamat3;
    
    @Column(name = "alamat4", length = 40)
    private String alamat4;
    
    @Column(name = "poskod", length = 5)
    private String poskod;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_negeri")
    private KodNegeri negeri;
    
    @Column (name = "no_tel1", length = 20)
    private String noTelefon1;

    @Column (name = "no_tel2", length = 20)
    private String noTelefon2;
        
    @Column (name  = "emel")
    private String emel;

    @Column (name  = "catatan")
    private String catatan;
    
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "cara_bayar")
    @Column (name  = "cara_bayar")
    private String caraBayaran;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_bank")
    private KodBank bank;
     
    @Column(name = "alamat_bank1", length = 40)
    private String alamatBank1;
    
    @Column(name = "alamat_bank2", length = 40)
    private String alamatBank2;
    
    @Column(name = "alamat_bank3", length = 40)
    private String alamatBank3;
    
    @Column(name = "alamat_bank4", length = 40)
    private String alamatBank4;
    
    @Column (name  = "NO_AKAUN_BANK")
    private String noAkaun;
     
    @Column(name = "amaun")
    private BigDecimal amaun;     
    
    
    @Column (name  = "kod_deposit_SPEKS")
    private String kodDepositSpeks;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdPermohonanPihakPendeposit() {
        return idPermohonanPihakPendeposit;
    }

    public void setIdPermohonanPihakPendeposit(Long idPermohonanPihakPendeposit) {
        this.idPermohonanPihakPendeposit = idPermohonanPihakPendeposit;
    }
    
    



    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getKategoriPihak() {
        return kategoriPihak;
    }

    public void setKategoriPihak(String kategoriPihak) {
        this.kategoriPihak = kategoriPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodJantina getJantina() {
        return jantina;
    }

    public void setJantina(KodJantina jantina) {
        this.jantina = jantina;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTarikhBantahan() {
        return tarikhBantahan;
    }

    public void setTarikhBantahan(Date tarikhBantahan) {
        this.tarikhBantahan = tarikhBantahan;
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

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getNoTelefon2() {
        return noTelefon2;
    }

    public void setNoTelefon2(String noTelefon2) {
        this.noTelefon2 = noTelefon2;
    }

    public String getEmel() {
        return emel;
    }

    public void setEmel(String emel) {
        this.emel = emel;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(String caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public KodBank getBank() {
        return bank;
    }

    public void setBank(KodBank bank) {
        this.bank = bank;
    }

    public String getAlamatBank1() {
        return alamatBank1;
    }

    public void setAlamatBank1(String alamatBank1) {
        this.alamatBank1 = alamatBank1;
    }

    public String getAlamatBank2() {
        return alamatBank2;
    }

    public void setAlamatBank2(String alamatBank2) {
        this.alamatBank2 = alamatBank2;
    }

    public String getAlamatBank3() {
        return alamatBank3;
    }

    public void setAlamatBank3(String alamatBank3) {
        this.alamatBank3 = alamatBank3;
    }

    public String getAlamatBank4() {
        return alamatBank4;
    }

    public void setAlamatBank4(String alamatBank4) {
        this.alamatBank4 = alamatBank4;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getKodDepositSpeks() {
        return kodDepositSpeks;
    }

    public void setKodDepositSpeks(String kodDepositSpeks) {
        this.kodDepositSpeks = kodDepositSpeks;
    }
    
}
