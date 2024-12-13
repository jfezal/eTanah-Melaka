package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "enkuiri")
@SequenceGenerator (name = "seq_enkuiri", sequenceName = "seq_enkuiri")
public class Enkuiri implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_enkuiri")
    @Column(name = "id_enkuiri")
    private long idEnkuiri;

    @ManyToOne
    @JoinColumn (name = "kod_caw", nullable = false)
    private KodCawangan cawangan;

    //change to nullable =  need set to true, follow DB
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable=false)
    private Permohonan permohonan;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pguna")
    private Pengguna pengguna;

    @ManyToOne
    @JoinColumn (name = "kod_sts", nullable = true)
    private KodStatusEnkuiri status;

    @Column(name = "bil_kes")
    private Integer bilanganKes;

    @Column(name = "trh_enkuiri", nullable=true)
//    @Temporal(TemporalType.DATE) // no need this for all date
    private Date tarikhEnkuiri;

    @Column(name = "trh_akhir_byr", nullable=true)
//    @Temporal(TemporalType.DATE) // no need this for all date
    private Date tarikhAkhirBayaran;

    @Column(name = "tmpt")
    private String tempat;

    @Column(name = "cara_lelong")
    private String caraLelong;

    @Column(name = "perkara")
    private String perkara;

    //change to nullable =  need set to true, follow DB
    @Column(name = "gadai_amaun", nullable=true)
    private BigDecimal amaunGadaian;

    @Column(name = "harga_rizab", nullable=true)
    private BigDecimal hargaRizab;
    @Column(name = "harga_bida", nullable=true)
    private BigDecimal hargaBida;

    @Column(name = "deposit", nullable=true)
    private BigDecimal deposit;

    @Column(name = "baki", nullable=true)
    private BigDecimal baki;


    @Column(name = "gadai_tujuan")
    private String tujuanGadaian;

    @Column(name = "gadai_trh")
   // @Temporal(TemporalType.DATE)
    private Date tarikhGadaian;

    @Column(name = "gadai_faedah")
    private String kadarFaedahGadaian;

    @Column(name = "gadai_tempoh")
    private Integer tempohGadaian;

    @Column(name = "gadai_byr_balik")
    private BigDecimal kadarBayaranBalik;

    @Column(name = "gadai_baki")
    private BigDecimal bakiGadaian;

    @Column(name = "gadai_langsai")
    private BigDecimal amaunGadaianDilangsai;

    @Column(name = "tunggak_amaun")
    private BigDecimal amaunTunggakan;

    @Column(name = "tunggak_trh")
    //@Temporal(TemporalType.DATE)
    private Date tarikhMulaTunggakan;

    @Column(name = "tunggak_sbb")
    private String sebabTunggakan;

    @Column(name = "tunggak_tempoh")
    private Integer tempohTunggakan;

    @Column(name = "tunggak_remedi")
    private Integer tempohRemedi;

    @Column(name = "ulasan_pgadai")
    private String ulasanPenggadai;

    @Column(name = "ulasan_pemilik")
    private String ulasanPemilik;

    @Column(name = "rayuan_pemilik")
    private String rayuanPemilik;

    @Column(name = "ulasan_pgawai")
    private String ulasanPegawai;

    @Column(name = "byr_pbn")
    private BigDecimal bayaranPihakBerkuasaNegeri;

    @Column(name = "byr_pbt")
    private BigDecimal bayaranPBT;

    @Column(name = "byr_pemajak")
    private BigDecimal bayaranPemajak;

    @Column(name = "byr_urus")
    private BigDecimal bayaranPengurusan;

    @Column(name = "byr_gadai")
    private BigDecimal bayaranPemegangGadai;

    @Column(name = "byr_anuiti")
    private BigDecimal bayaranAnuiti;

    @Column(name = "byr_kemudian")
    private BigDecimal bayaranTerkemudian;

    @Column(name = "byr_jumlah")
    private BigDecimal jumlahBayaran;

    @Column(name = "kos_kehadiran")
    private BigDecimal kosKehadiran;

    @OneToMany(mappedBy = "enkuiri", cascade = CascadeType.ALL)
    private List<Kehadiran> senaraiKehadiran;

    @OneToMany(mappedBy = "enkuiri", cascade = CascadeType.ALL)
    private List<Lelongan> senaraiLelongan;


    @Column(name = "nama_peminjam")
    private String namaPeminjam;

    @Column(name = "tujuan_pinjam")
    private String tujuanPinjam;

    @Column(name = "trh_mula_bayar_pinjam")
    private Date tarikhMulaBayarPinjaman;

    @Column(name = "trh_warta")
    private Date tarikhWarta;

    @Column(name = "perihal_tanah")
    private String perihalTanah;

    @Column(name = "perihal_tanah_bi")
    private String perihalTanahBI;

    @Embedded
    private InfoAudit infoAudit;

    public BigDecimal getAmaunGadaian() {
        return amaunGadaian;
    }

    public void setAmaunGadaian(BigDecimal amaunGadaian) {
        this.amaunGadaian = amaunGadaian;
    }

    public BigDecimal getAmaunGadaianDilangsai() {
        return amaunGadaianDilangsai;
    }

    public void setAmaunGadaianDilangsai(BigDecimal amaunGadaianDilangsai) {
        this.amaunGadaianDilangsai = amaunGadaianDilangsai;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public BigDecimal getBakiGadaian() {
        return bakiGadaian;
    }

    public void setBakiGadaian(BigDecimal bakiGadaian) {
        this.bakiGadaian = bakiGadaian;
    }

    public BigDecimal getBayaranAnuiti() {
        return bayaranAnuiti;
    }

    public void setBayaranAnuiti(BigDecimal bayaranAnuiti) {
        this.bayaranAnuiti = bayaranAnuiti;
    }

    public BigDecimal getBayaranPBT() {
        return bayaranPBT;
    }

    public void setBayaranPBT(BigDecimal bayaranPBT) {
        this.bayaranPBT = bayaranPBT;
    }

    public BigDecimal getBayaranPemajak() {
        return bayaranPemajak;
    }

    public void setBayaranPemajak(BigDecimal bayaranPemajak) {
        this.bayaranPemajak = bayaranPemajak;
    }

    public BigDecimal getBayaranPemegangGadai() {
        return bayaranPemegangGadai;
    }

    public void setBayaranPemegangGadai(BigDecimal bayaranPemegangGadai) {
        this.bayaranPemegangGadai = bayaranPemegangGadai;
    }

    public BigDecimal getBayaranPengurusan() {
        return bayaranPengurusan;
    }

    public void setBayaranPengurusan(BigDecimal bayaranPengurusan) {
        this.bayaranPengurusan = bayaranPengurusan;
    }

    public BigDecimal getBayaranPihakBerkuasaNegeri() {
        return bayaranPihakBerkuasaNegeri;
    }

    public void setBayaranPihakBerkuasaNegeri(BigDecimal bayaranPihakBerkuasaNegeri) {
        this.bayaranPihakBerkuasaNegeri = bayaranPihakBerkuasaNegeri;
    }

    public BigDecimal getBayaranTerkemudian() {
        return bayaranTerkemudian;
    }

    public void setBayaranTerkemudian(BigDecimal bayaranTerkemudian) {
        this.bayaranTerkemudian = bayaranTerkemudian;
    }

    public Integer getBilanganKes() {
        return bilanganKes;
    }

    public void setBilanganKes(Integer bilanganKes) {
        this.bilanganKes = bilanganKes;
    }
  
    public long getIdEnkuiri() {
        return idEnkuiri;
    }

    public void setIdEnkuiri(long idEnkuiri) {
        this.idEnkuiri = idEnkuiri;
    }

    public void setCawangan(KodCawangan cawangan) {
		this.cawangan = cawangan;
	}

	public KodCawangan getCawangan() {
		return cawangan;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public BigDecimal getKadarBayaranBalik() {
        return kadarBayaranBalik;
    }

    public void setKadarBayaranBalik(BigDecimal kadarBayaranBalik) {
        this.kadarBayaranBalik = kadarBayaranBalik;
    }

    public String getKadarFaedahGadaian() {
        return kadarFaedahGadaian;
    }

    public void setKadarFaedahGadaian(String kadarFaedahGadaian) {
        this.kadarFaedahGadaian = kadarFaedahGadaian;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public void setStatus(KodStatusEnkuiri status) {
		this.status = status;
	}

	public KodStatusEnkuiri getStatus() {
		return status;
	}

	public String getRayuanPemilik() {
        return rayuanPemilik;
    }

    public void setRayuanPemilik(String rayuanPemilik) {
        this.rayuanPemilik = rayuanPemilik;
    }

    public String getSebabTunggakan() {
        return sebabTunggakan;
    }

    public void setSebabTunggakan(String sebabTunggakan) {
        this.sebabTunggakan = sebabTunggakan;
    }

    public List<Kehadiran> getSenaraiKehadiran() {
        return senaraiKehadiran;
    }

    public void setSenaraiKehadiran(List<Kehadiran> senaraiKehadiran) {
        this.senaraiKehadiran = senaraiKehadiran;
    }

    public Date getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(Date tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public Date getTarikhGadaian() {
        return tarikhGadaian;
    }

    public void setTarikhGadaian(Date tarikhGadaian) {
        this.tarikhGadaian = tarikhGadaian;
    }

    public Date getTarikhMulaTunggakan() {
        return tarikhMulaTunggakan;
    }

    public void setTarikhMulaTunggakan(Date tarikhMulaTunggakan) {
        this.tarikhMulaTunggakan = tarikhMulaTunggakan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Integer getTempohGadaian() {
        return tempohGadaian;
    }

    public void setTempohGadaian(Integer tempohGadaian) {
        this.tempohGadaian = tempohGadaian;
    }

    public Integer getTempohRemedi() {
        return tempohRemedi;
    }

    public void setTempohRemedi(Integer tempohRemedi) {
        this.tempohRemedi = tempohRemedi;
    }

    public String getTujuanGadaian() {
        return tujuanGadaian;
    }

    public void setTujuanGadaian(String tujuanGadaian) {
        this.tujuanGadaian = tujuanGadaian;
    }

    public String getUlasanPemilik() {
        return ulasanPemilik;
    }

    public void setUlasanPemilik(String ulasanPemilik) {
        this.ulasanPemilik = ulasanPemilik;
    }

    public String getUlasanPenggadai() {
        return ulasanPenggadai;
    }

    public void setUlasanPenggadai(String ulasanPenggadai) {
        this.ulasanPenggadai = ulasanPenggadai;
    }

    public String getUlasanPegawai() {
        return ulasanPegawai;
    }

    public void setUlasanPegawai(String ulasanPegawai) {
        this.ulasanPegawai = ulasanPegawai;
    }

    public Integer getTempohTunggakan() {
        return tempohTunggakan;
    }

    public void setTempohTunggakan(Integer tempohTunggakan) {
        this.tempohTunggakan = tempohTunggakan;
    }

    public BigDecimal getJumlahBayaran() {
        return jumlahBayaran;
    }

    public void setJumlahBayaran(BigDecimal jumlahBayaran) {
        this.jumlahBayaran = jumlahBayaran;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public void setPerkara(String perkara) {
        this.perkara = perkara;
    }

    public String getPerkara() {
        return this.perkara;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public String getCaraLelong() {
        return caraLelong;
    }

    public void setCaraLelong(String caraLelong) {
        this.caraLelong = caraLelong;
    }

    public BigDecimal getHargaBida() {
        return hargaBida;
    }

    public void setHargaBida(BigDecimal hargaBida) {
        this.hargaBida = hargaBida;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public Date getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(Date tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public BigDecimal getKosKehadiran() {
        return kosKehadiran;
    }

    public void setKosKehadiran(BigDecimal kosKehadiran) {
        this.kosKehadiran = kosKehadiran;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public Date getTarikhMulaBayarPinjaman() {
        return tarikhMulaBayarPinjaman;
    }

    public void setTarikhMulaBayarPinjaman(Date tarikhMulaBayarPinjaman) {
        this.tarikhMulaBayarPinjaman = tarikhMulaBayarPinjaman;
    }

    public Date getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(Date tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getPerihalTanahBI() {
        return perihalTanahBI;
    }

    public void setPerihalTanahBI(String perihalTanahBI) {
        this.perihalTanahBI = perihalTanahBI;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getTujuanPinjam() {
        return tujuanPinjam;
    }

    public void setTujuanPinjam(String tujuanPinjam) {
        this.tujuanPinjam = tujuanPinjam;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    

}
