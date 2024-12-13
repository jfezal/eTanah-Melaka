/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import etanah.model.ambil.HakmilikPerbicaraan;
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
 * @author ubuntu
 */
@Entity
@Table(name = "mohon_pihak_wakil")
@SequenceGenerator(name = "seq_pihak_wakil", sequenceName = "seq_pihak_wakil")
public class PermohonanPihakWakil implements Serializable {

    @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pihak_wakil")
    @Column(name = "ID_MPW")
    private long idWakilPermohonanPihak;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MH")
    private HakmilikPermohonan hakmilikPermohonan;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_BICARA")
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    @ManyToOne
    @JoinColumn(name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    @Column(name = "nama")
    private String nama;
    @Column(name = "no_pengenalan")
    private String noPengenalan;
    @ManyToOne
    @JoinColumn(name = "KOD_WARNAKP")
    private KodWarnaKP warnaKadPengenalan;
    @ManyToOne
    @JoinColumn(name = "KOD_BANGSA")
    private KodBangsa bangsa;
    @ManyToOne
    @JoinColumn(name = "KOD_JANTINA")
    private KodJantina jantina;
    @ManyToOne
    @JoinColumn(name = "KOD_WARGANEGARA")
    private KodWarganegara warganegara;
    @Column(name = "alamat1")
    private String alamat1;
    @Column(name = "alamat2")
    private String alamat2;
    @Column(name = "alamat3")
    private String alamat3;
    @Column(name = "alamat4")
    private String alamat4;
    @Column(name = "poskod")
    private String poskod;
    @ManyToOne
    @JoinColumn(name = "kod_negeri")
    private KodNegeri negeri;
    @Column(name = "SURAT_ALAMAT1")
    private String suratAlamat1;
    @Column(name = "SURAT_ALAMAT2")
    private String suratAlamat2;
    @Column(name = "SURAT_ALAMAT3")
    private String suratAlamat3;
    @Column(name = "SURAT_ALAMAT4")
    private String suratAlamat4;
    @Column(name = "SURAT_POSKOD")
    private String suratPoskod;
    @ManyToOne
    @JoinColumn(name = "SURAT_KOD_NEGERI")
    private KodNegeri suratNegeri;
    @Column(name = "NO_TEL1")
    private String telefon1;
    @Column(name = "NO_TEL2")
    private String telefon2;
    @Column(name = "NO_BIMBIT")
    private String telefonBimbit;
    @Column(name = "NO_FAX")
    private String fax;
    @Column(name = "EMAIL")
    private String email;
    @ManyToOne
    @JoinColumn(name = "KOD_SUKU")
    private KodSuku suku;
    @Column(name = "TRH_LAHIR")
    private Date tarikhLahir;
    @Column(name = "TEMPAT_LAHIR")
    private String tempatDilahirkan;
    @ManyToOne
    @JoinColumn(name = "KOD_NEGERI_LAHIR")
    private KodNegeri negeriDilahirkan;
    @ManyToOne
    @JoinColumn(name = "KOD_BANK")
    private KodBank bank;
    @Column(name = "NO_AKAUN_BANK")
    private String noAkaunBank;
    @Column(name = "NO_PENGENALAN_LAIN")
    private String noPengenalanLain;
    @Column(name = "KETURUNAN")
    private String keturunan;
    @Column(name = "SUB_SUKU")
    private String subSuku;
    @ManyToOne
    @JoinColumn (name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdWakilPermohonanPihak() {
        return idWakilPermohonanPihak;
    }

    public void setIdWakilPermohonanPihak(long idWakilPermohonanPihak) {
        this.idWakilPermohonanPihak = idWakilPermohonanPihak;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
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

    public KodWarnaKP getWarnaKadPengenalan() {
        return warnaKadPengenalan;
    }

    public void setWarnaKadPengenalan(KodWarnaKP warnaKadPengenalan) {
        this.warnaKadPengenalan = warnaKadPengenalan;
    }

    public KodBangsa getBangsa() {
        return bangsa;
    }

    public void setBangsa(KodBangsa bangsa) {
        this.bangsa = bangsa;
    }

    public KodJantina getJantina() {
        return jantina;
    }

    public void setJantina(KodJantina jantina) {
        this.jantina = jantina;
    }

    public KodWarganegara getWarganegara() {
        return warganegara;
    }

    public void setWarganegara(KodWarganegara warganegara) {
        this.warganegara = warganegara;
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

    public String getSuratAlamat1() {
        return suratAlamat1;
    }

    public void setSuratAlamat1(String suratAlamat1) {
        this.suratAlamat1 = suratAlamat1;
    }

    public String getSuratAlamat2() {
        return suratAlamat2;
    }

    public void setSuratAlamat2(String suratAlamat2) {
        this.suratAlamat2 = suratAlamat2;
    }

    public String getSuratAlamat3() {
        return suratAlamat3;
    }

    public void setSuratAlamat3(String suratAlamat3) {
        this.suratAlamat3 = suratAlamat3;
    }

    public String getSuratAlamat4() {
        return suratAlamat4;
    }

    public void setSuratAlamat4(String suratAlamat4) {
        this.suratAlamat4 = suratAlamat4;
    }

    public String getSuratPoskod() {
        return suratPoskod;
    }

    public void setSuratPoskod(String suratPoskod) {
        this.suratPoskod = suratPoskod;
    }

    public KodNegeri getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(KodNegeri suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon1) {
        this.telefon1 = telefon1;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getTelefonBimbit() {
        return telefonBimbit;
    }

    public void setTelefonBimbit(String telefonBimbit) {
        this.telefonBimbit = telefonBimbit;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public KodSuku getSuku() {
        return suku;
    }

    public void setSuku(KodSuku suku) {
        this.suku = suku;
    }

    public Date getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(Date tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public String getTempatDilahirkan() {
        return tempatDilahirkan;
    }

    public void setTempatDilahirkan(String tempatDilahirkan) {
        this.tempatDilahirkan = tempatDilahirkan;
    }

    public KodNegeri getNegeriDilahirkan() {
        return negeriDilahirkan;
    }

    public void setNegeriDilahirkan(KodNegeri negeriDilahirkan) {
        this.negeriDilahirkan = negeriDilahirkan;
    }

    public KodBank getBank() {
        return bank;
    }

    public void setBank(KodBank bank) {
        this.bank = bank;
    }

    public String getNoAkaunBank() {
        return noAkaunBank;
    }

    public void setNoAkaunBank(String noAkaunBank) {
        this.noAkaunBank = noAkaunBank;
    }

    public String getNoPengenalanLain() {
        return noPengenalanLain;
    }

    public void setNoPengenalanLain(String noPengenalanLain) {
        this.noPengenalanLain = noPengenalanLain;
    }

    public String getKeturunan() {
        return keturunan;
    }

    public void setKeturunan(String keturunan) {
        this.keturunan = keturunan;
    }

    public String getSubSuku() {
        return subSuku;
    }

    public void setSubSuku(String subSuku) {
        this.subSuku = subSuku;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
    
    
}
