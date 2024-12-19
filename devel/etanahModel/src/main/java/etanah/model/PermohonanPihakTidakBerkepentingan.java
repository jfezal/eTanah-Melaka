package etanah.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import javax.persistence.ManyToOne;

@Entity
@Table(name = "mohon_pihak_tdkpenting")
@SequenceGenerator(name = "seq_mohon_pihak_tdkpenting", sequenceName = "seq_mohon_pihak_tdkpenting")
public class PermohonanPihakTidakBerkepentingan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_pihak_tdkpenting")
    @Column(name = "id_mpt")
    private long idPermohonanPhkTdkBerptg;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;
    
    @ManyToOne
    @JoinColumn (name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KOD_PENGENALAN")
    private KodJenisPengenalan kodJenisPengenalan;
    
    
    
    @Column (name = "nama")
    private String nama;
    
    
    @Column (name = "NO_PENGENALAN")
    private String nomborPengenalan;
    
    
    
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_WARNAKP")
	private KodWarnaKP kodWarnaKP;
        
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_BANGSA")
	private KodBangsa kodBangsa;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_JANTINA")
	private KodJantina kodJantina;
        

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_WARGANEGARA")
	private KodWarganegara kodWarganegara;
        
        @ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_MP")
	private PermohonanPihak mohonPihak;

    @Column (name = "ALAMAT1")
    private String alamat1;
    
    @Column (name = "ALAMAT2")
    private String alamat2;
    
    @Column (name = "ALAMAT3")
    private String alamat3;
    
    @Column (name = "ALAMAT4")
    private String alamat4;
    
    @Column (name = "poskod")
    private String poskod;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_NEGERI")
	private KodNegeri kodNegeri;
        
        
        
        
    @Column (name = "SURAT_ALAMAT1")
    private String menyuratAlamat1;
    
    @Column (name = "SURAT_ALAMAT2")
    private String menyuratAlamat2;
    
    @Column (name = "SURAT_ALAMAT3")
    private String menyuratAlamat3;
    
    @Column (name = "SURAT_ALAMAT4")
    private String menyuratAlamat4;
    
    @Column (name = "SURAT_POSKOD")
    private String menyuratPoskod;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "SURAT_KOD_NEGERI")
	private KodNegeri menyuratKodNegeri;
        
    @Column (name = "NO_TEL1")
    private String nomborTelefon1;
    
    @Column (name = "NO_TEL2")
    private String nomborTelefon2;
    
    @Column (name = "NO_BIMBIT")
    private String nomborTelefonBimbit;
        
    @Column (name = "NO_FAX")
    private String nomborFax;
    
    @Column (name = "EMAIL")
    private String email;
    
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_SUKU")
	private KodSuku kodSuku;
    
    @Column (name = "TRH_LAHIR")
    private Date tarikhLahir;
    
    @Column (name = "TEMPAT_LAHIR")
    private String tempatLahir;
    
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_NEGERI_LAHIR")
	private KodNegeri lahirKodNegeri;
        
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "KOD_BANK")
	private KodBank kodBank;
        
    
    @Column (name = "NO_AKAUN_BANK")
    private String nomborAkaunBank;
    
    @Column (name = "NO_PENGENALAN_LAIN")
    private String nomborPengenalanLain;
    
    @Column (name = "TEMPAT_SUKU")
    private String tempatSuku;
    
    @Column (name = "KETURUNAN")
    private String keturunan;
    
    @Column (name = "SUB_SUKU")
    private String subSuku;
    
    @Embedded
    private InfoAudit infoAudit;

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

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public long getIdPermohonanPhkTdkBerptg() {
        return idPermohonanPhkTdkBerptg;
    }

    public void setIdPermohonanPhkTdkBerptg(long idPermohonanPhkTdkBerptg) {
        this.idPermohonanPhkTdkBerptg = idPermohonanPhkTdkBerptg;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getKeturunan() {
        return keturunan;
    }

    public void setKeturunan(String keturunan) {
        this.keturunan = keturunan;
    }

    public KodBangsa getKodBangsa() {
        return kodBangsa;
    }

    public void setKodBangsa(KodBangsa kodBangsa) {
        this.kodBangsa = kodBangsa;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

    public KodJantina getKodJantina() {
        return kodJantina;
    }

    public void setKodJantina(KodJantina kodJantina) {
        this.kodJantina = kodJantina;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public KodSuku getKodSuku() {
        return kodSuku;
    }

    public void setKodSuku(KodSuku kodSuku) {
        this.kodSuku = kodSuku;
    }

    public KodWarganegara getKodWarganegara() {
        return kodWarganegara;
    }

    public void setKodWarganegara(KodWarganegara kodWarganegara) {
        this.kodWarganegara = kodWarganegara;
    }

    public KodWarnaKP getKodWarnaKP() {
        return kodWarnaKP;
    }

    public void setKodWarnaKP(KodWarnaKP kodWarnaKP) {
        this.kodWarnaKP = kodWarnaKP;
    }

    public KodNegeri getLahirKodNegeri() {
        return lahirKodNegeri;
    }

    public void setLahirKodNegeri(KodNegeri lahirKodNegeri) {
        this.lahirKodNegeri = lahirKodNegeri;
    }

    public String getMenyuratAlamat1() {
        return menyuratAlamat1;
    }

    public void setMenyuratAlamat1(String menyuratAlamat1) {
        this.menyuratAlamat1 = menyuratAlamat1;
    }

    public String getMenyuratAlamat2() {
        return menyuratAlamat2;
    }

    public void setMenyuratAlamat2(String menyuratAlamat2) {
        this.menyuratAlamat2 = menyuratAlamat2;
    }

    public String getMenyuratAlamat3() {
        return menyuratAlamat3;
    }

    public void setMenyuratAlamat3(String menyuratAlamat3) {
        this.menyuratAlamat3 = menyuratAlamat3;
    }

    public String getMenyuratAlamat4() {
        return menyuratAlamat4;
    }

    public void setMenyuratAlamat4(String menyuratAlamat4) {
        this.menyuratAlamat4 = menyuratAlamat4;
    }

    public KodNegeri getMenyuratKodNegeri() {
        return menyuratKodNegeri;
    }

    public void setMenyuratKodNegeri(KodNegeri menyuratKodNegeri) {
        this.menyuratKodNegeri = menyuratKodNegeri;
    }

    public String getMenyuratPoskod() {
        return menyuratPoskod;
    }

    public void setMenyuratPoskod(String menyuratPoskod) {
        this.menyuratPoskod = menyuratPoskod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomborAkaunBank() {
        return nomborAkaunBank;
    }

    public void setNomborAkaunBank(String nomborAkaunBank) {
        this.nomborAkaunBank = nomborAkaunBank;
    }

    public String getNomborFax() {
        return nomborFax;
    }

    public void setNomborFax(String nomborFax) {
        this.nomborFax = nomborFax;
    }

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public String getNomborPengenalanLain() {
        return nomborPengenalanLain;
    }

    public void setNomborPengenalanLain(String nomborPengenalanLain) {
        this.nomborPengenalanLain = nomborPengenalanLain;
    }

    public String getNomborTelefon1() {
        return nomborTelefon1;
    }

    public void setNomborTelefon1(String nomborTelefon1) {
        this.nomborTelefon1 = nomborTelefon1;
    }

    public String getNomborTelefon2() {
        return nomborTelefon2;
    }

    public void setNomborTelefon2(String nomborTelefon2) {
        this.nomborTelefon2 = nomborTelefon2;
    }

    public String getNomborTelefonBimbit() {
        return nomborTelefonBimbit;
    }

    public void setNomborTelefonBimbit(String nomborTelefonBimbit) {
        this.nomborTelefonBimbit = nomborTelefonBimbit;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getSubSuku() {
        return subSuku;
    }

    public void setSubSuku(String subSuku) {
        this.subSuku = subSuku;
    }

    public Date getTarikhLahir() {
        return tarikhLahir;
    }

    public void setTarikhLahir(Date tarikhLahir) {
        this.tarikhLahir = tarikhLahir;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTempatSuku() {
        return tempatSuku;
    }

    public void setTempatSuku(String tempatSuku) {
        this.tempatSuku = tempatSuku;
    }

    public PermohonanPihak getMohonPihak() {
        return mohonPihak;
    }

    public void setMohonPihak(PermohonanPihak mohonPihak) {
        this.mohonPihak = mohonPihak;
    }

    
    
    

}
