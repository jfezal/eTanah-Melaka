package etanah.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "lelong")
@SequenceGenerator (name = "seq_lelong", sequenceName = "seq_lelong")
public class Lelongan {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_lelong")
    @Column(name = "id_lelong")
    private long idLelong;

    @Column(name = "bil")
    private int bil;

    @ManyToOne
    @JoinColumn(name = "id_enkuiri")
    private Enkuiri enkuiri;


    @ManyToOne
    @JoinColumn(name = "id_syt_jlb")
    private SytJuruLelong sytJuruLelong;

    @ManyToOne
    @JoinColumn(name = "id_jlb")
    private JuruLelong jurulelong;

    @Column(name = "tmpt")
    private String tempat;

    @Column(name = "trh_lelong")
    //@Temporal(TemporalType.DATE) // no need this for all date
    private Date tarikhLelong;

    @ManyToOne
    @JoinColumn(name = "id_pihak")
    private Pihak pembida;

    @Column(name = "harga_rizab")
    private BigDecimal hargaRizab;

    @Column(name = "harga_bida")
    private BigDecimal hargaBidaan;

    @Column(name = "eja_rizab")
    private String ejaanHarga;

    @Column(name = "deposit")
    private BigDecimal deposit;

    @Column(name = "baki")
    private BigDecimal baki;

    @Column(name = "trh_akhir_byr")
    //@Temporal(TemporalType.DATE) // no need this for all date
    private Date tarikhAkhirBayaran;


    @Column(name = "trh_byr")
    //@Temporal(TemporalType.DATE) // no need this for all date
    private Date tarikhBayaran;

//    @Column (name = "kod_sumber")
//    private String kodSumber;

    @Column (name = "batal_lelong")
    private Character batalLelong;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon_batal")
    private Permohonan permohonanBatal;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon_tangguh")
    private Permohonan permohonanTangguh;   

    @Column (name = "syarat_tambahan")
    private Character syaratTambahan;

    
    @Column(name = "kom_jualan")
    private BigDecimal komisyenJualan;

    @Column(name = "baki_cukai_semasa")
    private BigDecimal bakiCukaiSemasa;


    @ManyToOne
    @JoinColumn (name = "kod_sts")
    private KodStatusLelongan kodStatusLelongan;


    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
    @ManyToOne
    @JoinColumn(name = "id_pguna2")
    private Pengguna pengguna;


    @Column(name = "perihal_tanah")
    private String perihalTanah;

    @Column(name = "perihal_tanah_bi")
    private String perihalTanahBahasaInggeris;

    @Embedded
    private InfoAudit infoAudit;

    public BigDecimal getHargaBidaan() {
        return hargaBidaan;
    }

    public void setHargaBidaan(BigDecimal amaun) {
        this.hargaBidaan = amaun;
    }

    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getEjaanHarga() {
        return ejaanHarga;
    }

    public void setEjaanHarga(String ejaanHarga) {
        this.ejaanHarga = ejaanHarga;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public long getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(long idLelong) {
        this.idLelong = idLelong;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public SytJuruLelong getSytJuruLelong() {
        return sytJuruLelong;
    }

    public void setSytJuruLelong(SytJuruLelong sytJuruLelong) {
        this.sytJuruLelong = sytJuruLelong;
    }



    public JuruLelong getJurulelong() {
        return jurulelong;
    }

    public void setJurulelong(JuruLelong jurulelong) {
        this.jurulelong = jurulelong;
    }

    public Pihak getPembida() {
        return pembida;
    }

    public void setPembida(Pihak pembida) {
        this.pembida = pembida;
    }

    public Date getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(Date tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public Date getTarikhBayaran() {
        return tarikhBayaran;
    }

    public void setTarikhBayaran(Date tarikhBayaran) {
        this.tarikhBayaran = tarikhBayaran;
    }

    
    public Date getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(Date tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getPerihalTanahBahasaInggeris() {
        return perihalTanahBahasaInggeris;
    }

    public void setPerihalTanahBahasaInggeris(String perihalTanahBahasaInggeris) {
        this.perihalTanahBahasaInggeris = perihalTanahBahasaInggeris;
    }

    public Permohonan getPermohonanBatal() {
        return permohonanBatal;
    }

    public void setPermohonanBatal(Permohonan permohonanBatal) {
        this.permohonanBatal = permohonanBatal;
    }
	
	public Permohonan getPermohonanTangguh() {
        return permohonanTangguh;
    }

    public void setPermohonanTangguh(Permohonan permohonanTangguh) {
        this.permohonanTangguh = permohonanTangguh;
    }


    public BigDecimal getBakiCukaiSemasa() {
        return bakiCukaiSemasa;
    }

    public void setBakiCukaiSemasa(BigDecimal bakiCukaiSemasa) {
        this.bakiCukaiSemasa = bakiCukaiSemasa;
    }

    public KodStatusLelongan getKodStatusLelongan() {
        return kodStatusLelongan;
    }

    public void setKodStatusLelongan(KodStatusLelongan kodStatusLelongan) {
        this.kodStatusLelongan = kodStatusLelongan;
    }

    public BigDecimal getKomisyenJualan() {
        return komisyenJualan;
    }

    public void setKomisyenJualan(BigDecimal komisyenJualan) {
        this.komisyenJualan = komisyenJualan;
    }

//    public void setKodSumber(String kodSumber) {
//        this.kodSumber = kodSumber;
//    }
//
//    public String getKodSumber() {
//        return kodSumber;
//    }

    public void setBatalLelong(Character batalLelong) {
        this.batalLelong = batalLelong;
    }

    public Character getBatalLelong() {
        return batalLelong;
    }

    public void setSyaratTambahan(Character syaratTambahan) {
        this.syaratTambahan = syaratTambahan;
    }

    public Character getSyaratTambahan() {
        return syaratTambahan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

}
