package etanah.model;

import java.io.Serializable;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "hakmilik_pihak")
@SequenceGenerator(name = "seq_hakmilik_pihak", sequenceName = "seq_hakmilik_pihak")
public class HakmilikPihakBerkepentingan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hakmilik_pihak")
    @Column(name = "id_hp")
    private long idHakmilikPihakBerkepentingan;

    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pihak")
    private Pihak pihak;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pihak_kongsi")
    private Pihak pihakKongsiBersama;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pihak_caw", nullable = true)
    private PihakCawangan pihakCawangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_urusan")
    private HakmilikUrusan perserahan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_urusan_batal")
    private HakmilikUrusan perserahanBatal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_pb")
    private KodJenisPihakBerkepentingan jenis;

    @Column(name = "syer_pembilang", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPembilang;

    @Column(name = "syer_penyebut", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPenyebut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kpd_pihak")
    private HakmilikPihakBerkepentingan kepadaPihak;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_urusan_kaveat", nullable = true)
    private HakmilikUrusan perserahanKaveat;

    @Column(name = "kaveat_aktif")
    private char kaveatAktif;

    // Pihak info to appear on title (if differ from SPTB)
    @Column(name = "nama")
    private String nama;

    @ManyToOne
    @JoinColumn(name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    
//    @ManyToOne
    @Column(name = "id_mohon")
    private String idPermohonan;

    @Column(name = "no_pengenalan")
    private String noPengenalan;

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

    @Column(name = "aktif")
    private char aktif;

    @ManyToOne
    @JoinColumn(name = "kod_sykt_tubuh")
    private KodPenubuhanSyarikat penubuhanSyarikat;

    @ManyToOne
    @JoinColumn(name = "kod_warganegara")
    private KodWarganegara wargaNegara;

    @Column(name = "JUM_PENYEBUT", precision = 22, scale = 0, columnDefinition = "number(22,0)")
    private Integer jumlahPenyebut;

    @Column(name = "JUM_PEMBILANG", precision = 22, scale = 0, columnDefinition = "number(22,0)")
    private Integer jumlahPembilang;

    @Embedded
    private AlamatSurat alamatSurat;

    //added by mohd.fairul
    @Column(name = "no_salinan")
    private int noSalinan;

    @OneToMany(mappedBy = "pemegangAmanah")
    private List<HakmilikWaris> senaraiWaris;

    @Column(name = "NO_PENGENALAN_LAMA")
    private String noPengenalanLama;

    @ManyToOne
    @JoinColumn(name = "kod_bangsa")
    private KodBangsa bangsa;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdHakmilikPihakBerkepentingan() {
        return idHakmilikPihakBerkepentingan;
    }

    public void setIdHakmilikPihakBerkepentingan(
            long idHakmilikPihakBerkepentingan) {
        this.idHakmilikPihakBerkepentingan = idHakmilikPihakBerkepentingan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public void setPihakCawangan(PihakCawangan pihakCawangan) {
        this.pihakCawangan = pihakCawangan;
    }

    public PihakCawangan getPihakCawangan() {
        return pihakCawangan;
    }

    public KodJenisPihakBerkepentingan getJenis() {
        return jenis;
    }

    public void setJenis(KodJenisPihakBerkepentingan jenis) {
        this.jenis = jenis;
    }

    public HakmilikUrusan getPerserahan() {
        return perserahan;
    }

    public void setPerserahan(HakmilikUrusan perserahan) {
        this.perserahan = perserahan;
    }

    public HakmilikUrusan getPerserahanBatal() {
        return perserahanBatal;
    }

    public void setPerserahanBatal(HakmilikUrusan perserahanBatal) {
        this.perserahanBatal = perserahanBatal;
    }

    public Integer getSyerPembilang() {
        return syerPembilang;
    }

    public void setSyerPembilang(Integer syerPembilang) {
        this.syerPembilang = syerPembilang;
    }

    public Integer getSyerPenyebut() {
        return syerPenyebut;
    }

    public void setSyerPenyebut(Integer syerPenyebut) {
        this.syerPenyebut = syerPenyebut;
    }

    public void setKepadaPihak(HakmilikPihakBerkepentingan kepadaPihak) {
        this.kepadaPihak = kepadaPihak;
    }

    public HakmilikPihakBerkepentingan getKepadaPihak() {
        return kepadaPihak;
    }

    public void setPerserahanKaveat(HakmilikUrusan perserahanKaveat) {
        this.perserahanKaveat = perserahanKaveat;
    }

    public HakmilikUrusan getPerserahanKaveat() {
        return perserahanKaveat;
    }

    public void setKaveatAktif(char kaveatAktif) {
        this.kaveatAktif = kaveatAktif;
    }

    public char getKaveatAktif() {
        return kaveatAktif;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
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

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public List<HakmilikWaris> getSenaraiWaris() {
        return senaraiWaris;
    }

    public void setSenaraiWaris(List<HakmilikWaris> senaraiWaris) {
        this.senaraiWaris = senaraiWaris;
    }

    public int getNoSalinan() {
        return noSalinan;
    }

    public void setNoSalinan(int noSalinan) {
        this.noSalinan = noSalinan;
    }

    public Pihak getPihakKongsiBersama() {
        return pihakKongsiBersama;
    }

    public void setPihakKongsiBersama(Pihak pihakKongsiBersama) {
        this.pihakKongsiBersama = pihakKongsiBersama;
    }

    public KodPenubuhanSyarikat getPenubuhanSyarikat() {
        return penubuhanSyarikat;
    }

    public void setPenubuhanSyarikat(KodPenubuhanSyarikat penubuhanSyarikat) {
        this.penubuhanSyarikat = penubuhanSyarikat;
    }

    public KodWarganegara getWargaNegara() {
        return wargaNegara;
    }

    public void setWargaNegara(KodWarganegara wargaNegara) {
        this.wargaNegara = wargaNegara;
    }

    public Integer getJumlahPenyebut() {
        return jumlahPenyebut;
    }

    public void setJumlahPenyebut(Integer jumlahPenyebut) {
        this.jumlahPenyebut = jumlahPenyebut;
    }

    public Integer getJumlahPembilang() {
        return jumlahPembilang;
    }

    public void setJumlahPembilang(Integer jumlahPembilang) {
        this.jumlahPembilang = jumlahPembilang;
    }

    public AlamatSurat getAlamatSurat() {
        return alamatSurat;
    }

    public void setAlamatSurat(AlamatSurat alamatSurat) {
        this.alamatSurat = alamatSurat;
    }

    public String getNoPengenalanLama() {
        return noPengenalanLama;
    }

    public void setNoPengenalanLama(String noPengenalanLama) {
        this.noPengenalanLama = noPengenalanLama;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public KodBangsa getBangsa() {
        return bangsa;
    }

    public void setBangsa(KodBangsa bangsa) {
        this.bangsa = bangsa;
    }
    
    

}
