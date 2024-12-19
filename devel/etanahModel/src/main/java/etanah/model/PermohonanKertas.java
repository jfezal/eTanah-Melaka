package etanah.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "mohon_kertas")
@SequenceGenerator(name = "seq_mohon_kertas", sequenceName = "seq_mohon_kertas")
public class PermohonanKertas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_kertas")
    @Column(name = "id_kertas")
    private long idKertas;
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "kod_dokumen")
    private KodDokumen kodDokumen;
    @Column(name = "tajuk", nullable = false)
    private String tajuk;
    @OneToMany(mappedBy = "kertas")
    @OrderBy("bil")
    private List<PermohonanKertasKandungan> senaraiKandungan;
    @ManyToOne
    @JoinColumn(name = "id_dokumen")
    private Dokumen dokumen;

    @Column(name = "trh_sidang")
    private Date tarikhSidang;

    @Column(name = "tempat_sidang")
    private String tempatSidang;
    
    @Column(name = "penyedia_sidang")
    private String penyediaSidang;
    
    @Column(name = "wakil_sidang")
    private String wakilSidang;
    
    @Column(name = "no_tel1")
    private String noTelefon1;
    
    @Column(name = "no_faks")
    private String noFaks;
    
    @Column(name = "no_laporan")
    private String noLaporan;
    
    @Column(name = "alamat1_sidang", length = 40)
    private String sidangAlamat1;
    @Column(name = "alamat2_sidang", length = 40)
    private String sidangAlamat2;
    @Column(name = "alamat3_sidang", length = 40)
    private String sidangAlamat3;
    @Column(name = "alamat4_sidang", length = 40)
    private String sidangtAlamat4;
    @Column(name = "poskod_sidang", length = 5)
    private String sidangPoskod;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_negeri_sidang")
    private KodNegeri sidangNegeri;

    @Column(name = "no_ruj")
    private String nomborRujukan;
    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @Column(name = "trh_sah_kpsn")
    private Date tarikhSahKeputusan;

    @ManyToOne
    @JoinColumn(name = "id_mp")
    private PermohonanPihak permohonanPihak;
    
    @Column(name = "no_kertas")
    private Integer noKertas;
    
    @Column(name = "sts_kertas")
    private String statusKertas;
    
    @Column(name = "dnilai1")
    private String nilaiDalaman1;
    
    @ManyToOne
    @JoinColumn(name = "kod_hakmilik_lama")
    private KodHakmilik kodHakmilikLama;
    
    @ManyToOne
    @JoinColumn(name = "kod_hakmilik_baru")
    private KodHakmilik kodHakmilikBaru;
    
    @Column(name = "nama_pelulus")
    private String namaPelulus;
    

    @Embedded
    private InfoAudit infoAudit;

    public long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(long idKertas) {
        this.idKertas = idKertas;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getTempatSidang() {
        return tempatSidang;
    }

    public void setTempatSidang(String tempatSidang) {
        this.tempatSidang = tempatSidang;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Date getTarikhSahKeputusan() {
        return tarikhSahKeputusan;
    }

    public void setTarikhSahKeputusan(Date tarikhSahKeputusan) {
        this.tarikhSahKeputusan = tarikhSahKeputusan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    


    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getPenyediaSidang() {
        return penyediaSidang;
    }

    public void setPenyediaSidang(String penyediaSidang) {
        this.penyediaSidang = penyediaSidang;
    }

    public String getWakilSidang() {
        return wakilSidang;
    }

    public void setWakilSidang(String wakilSidang) {
        this.wakilSidang = wakilSidang;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getNoFaks() {
        return noFaks;
    }

    public void setNoFaks(String noFaks) {
        this.noFaks = noFaks;
    }

    public String getNoLaporan() {
        return noLaporan;
    }

    public void setNoLaporan(String noLaporan) {
        this.noLaporan = noLaporan;
    }

    public String getSidangAlamat1() {
        return sidangAlamat1;
    }

    public void setSidangAlamat1(String sidangAlamat1) {
        this.sidangAlamat1 = sidangAlamat1;
    }

    public String getSidangAlamat2() {
        return sidangAlamat2;
    }

    public void setSidangAlamat2(String sidangAlamat2) {
        this.sidangAlamat2 = sidangAlamat2;
    }

    public String getSidangAlamat3() {
        return sidangAlamat3;
    }

    public void setSidangAlamat3(String sidangAlamat3) {
        this.sidangAlamat3 = sidangAlamat3;
    }

    public String getSidangtAlamat4() {
        return sidangtAlamat4;
    }

    public void setSidangtAlamat4(String sidangtAlamat4) {
        this.sidangtAlamat4 = sidangtAlamat4;
    }

    public String getSidangPoskod() {
        return sidangPoskod;
    }

    public void setSidangPoskod(String sidangPoskod) {
        this.sidangPoskod = sidangPoskod;
    }

    public KodNegeri getSidangNegeri() {
        return sidangNegeri;
    }

    public void setSidangNegeri(KodNegeri sidangNegeri) {
        this.sidangNegeri = sidangNegeri;
    }

    public Integer getNoKertas() {
        return noKertas;
    }

    public void setNoKertas(Integer noKertas) {
        this.noKertas = noKertas;
    }

    public String getStatusKertas() {
        return statusKertas;
    }

    public void setStatusKertas(String statusKertas) {
        this.statusKertas = statusKertas;
    }

    public String getNilaiDalaman1() {
        return nilaiDalaman1;
    }

    public void setNilaiDalaman1(String nilaiDalaman1) {
        this.nilaiDalaman1 = nilaiDalaman1;
    }

    public KodHakmilik getKodHakmilikLama() {
        return kodHakmilikLama;
    }

    public void setKodHakmilikLama(KodHakmilik kodHakmilikLama) {
        this.kodHakmilikLama = kodHakmilikLama;
    }

    public KodHakmilik getKodHakmilikBaru() {
        return kodHakmilikBaru;
    }

    public void setKodHakmilikBaru(KodHakmilik kodHakmilikBaru) {
        this.kodHakmilikBaru = kodHakmilikBaru;
    }

    public String getNamaPelulus() {
        return namaPelulus;
    }

    public void setNamaPelulus(String namaPelulus) {
        this.namaPelulus = namaPelulus;
    }
    
    
}
