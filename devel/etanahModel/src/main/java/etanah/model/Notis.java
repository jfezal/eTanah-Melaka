package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Notis-notis bagi permohonan
 */
@Entity
@Table(name = "notis")
@SequenceGenerator (name = "seq_notis", sequenceName = "seq_notis")
public class Notis implements Serializable {

    @Id
    @Column(name = "id_notis")
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_notis")
    private long idNotis;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable=false)
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn (name = "kod_jabatan", nullable = false)
    private KodJabatan jabatan;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_mohon_pihak")
    private PermohonanPihak pihak;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_hp")
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;

    @ManyToOne
    @JoinColumn(name = "id_dokumen", nullable=false)
    private Dokumen dokumenNotis;

    @Column(name = "bil")
    private String bilangan;

    @Column(name = "no_ruj")
    private String noRujukan;

    @Column(name = "tempoh_bln")
    private int tempohBulan;

    @Column(name = "tempoh_hari")
    private int tempohHari;

    @ManyToOne
    @JoinColumn(name = "kod_notis")
    private KodNotis kodNotis;

    @ManyToOne
    @JoinColumn(name = "kod_hantar")
    private KodCaraPenghantaran kodCaraPenghantaran;

    @ManyToOne
    @JoinColumn(name = "id_warta")
    private PermohonanRujukanLuar warta;

    @Column(name = "tempat_hantar1")
    private String tempatHantar1;

    @Column(name = "tempat_hantar2")
    private String tempatHantar2;

    @Column(name = "tempat_hantar3")
    private String tempatHantar3;

    @Column(name = "tempat_hantar4")
    private String tempatHantar4;

    @Column(name = "tempat_hantar5")
    private String tempatHantar5;

    @Column(name = "tempat_hantar6")
    private String tempatHantar6;

    @Column(name = "tempat_hantar7")
    private String tempatHantar7;

    @Column(name = "tempat_hantar8")
    private String tempatHantar8;

//    @Temporal(TemporalType.DATE)
    @Column(name = "trh_notis")
    private Date tarikhNotis;

//    @Temporal(TemporalType.DATE)
    @Column(name = "trh_hantar")
    private Date tarikhHantar;

    @ManyToOne
    @JoinColumn(name = "penghantar_notis")
    private PenghantarNotis penghantarNotis;


//    @Temporal(TemporalType.DATE)
    @Column(name = "trh_tampal")
    private Date tarikhTampal;

    @ManyToOne
    @JoinColumn(name = "kod_sts_terima")
    private KodStatusTerima kodStatusTerima;


//    @Temporal(TemporalType.DATE)
    @Column(name = "terima_trh")
    private Date tarikhTerima;

    @Column(name = "terima_catatan")
    private String catatanPenerimaan;

    @ManyToOne
    @JoinColumn(name = "terima_bukti")
    private Dokumen buktiPenerimaan;

    @Column(name = "dicetak")
    private String dicetakOleh;

    @Column(name = "trh_cetak")
    private Date tarikhCetak;
    
    @Column(name = "trh_tamat")
    private Date tarikhTamat;

    @ManyToOne
    @JoinColumn(name = "id_penasihat")
    private PenasihatUndang penasihatUndang;

    @Column (name = "tempat_tampal")
    private String tempatTampal;
    
    @Column (name = "nama_tampal")
    private String namaTampal;

    @Column (name = "penerima_notis")
    private String penerimaNotis;

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;
    
    @Column (name = "ulasan")
    private String ulasan;
    
    @Embedded
    private InfoAudit infoAudit;

    public String getBilangan() {
        return bilangan;
    }

    public void setBilangan(String bilangan) {
        this.bilangan = bilangan;
    }

    public Dokumen getBuktiPenerimaan() {
        return buktiPenerimaan;
    }

    public void setBuktiPenerimaan(Dokumen buktiPenerimaan) {
        this.buktiPenerimaan = buktiPenerimaan;
    }

    public String getDicetakOleh() {
        return dicetakOleh;
    }

    public void setDicetakOleh(String dicetakOleh) {
        this.dicetakOleh = dicetakOleh;
    }

    public Date getTarikhCetak() {
        return tarikhCetak;
    }

    public void setTarikhCetak(Date tarikhCetak) {
        this.tarikhCetak = tarikhCetak;
    }

    public String getCatatanPenerimaan() {
        return catatanPenerimaan;
    }

    public void setCatatanPenerimaan(String catatanPenerimaan) {
        this.catatanPenerimaan = catatanPenerimaan;
    }

    public Dokumen getDokumenNotis() {
        return dokumenNotis;
    }

    public void setDokumenNotis(Dokumen dokumenNotis) {
        this.dokumenNotis = dokumenNotis;
    }

    public long getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(long idNotis) {
        this.idNotis = idNotis;
    }

    public void setJabatan(KodJabatan jabatan) {
		this.jabatan = jabatan;
	}

	public KodJabatan getJabatan() {
		return jabatan;
	}

	public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodCaraPenghantaran getKodCaraPenghantaran() {
        return kodCaraPenghantaran;
    }

    public void setKodCaraPenghantaran(KodCaraPenghantaran kodCaraPenghantaran) {
        this.kodCaraPenghantaran = kodCaraPenghantaran;
    }

    public KodNotis getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(KodNotis kodNotis) {
        this.kodNotis = kodNotis;
    }

    public KodStatusTerima getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(KodStatusTerima kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public void setPihak(PermohonanPihak pihak) {
		this.pihak = pihak;
	}

	public PermohonanPihak getPihak() {
		return pihak;
	}

	public Date getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(Date tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public Date getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(Date tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public Date getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(Date tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getTempatHantar1() {
        return tempatHantar1;
    }

    public void setTempatHantar1(String tempatHantar1) {
        this.tempatHantar1 = tempatHantar1;
    }

    public String getTempatHantar2() {
        return tempatHantar2;
    }

    public void setTempatHantar2(String tempatHantar2) {
        this.tempatHantar2 = tempatHantar2;
    }

    public String getTempatHantar3() {
        return tempatHantar3;
    }

    public void setTempatHantar3(String tempatHantar3) {
        this.tempatHantar3 = tempatHantar3;
    }

    public String getTempatHantar4() {
        return tempatHantar4;
    }

    public void setTempatHantar4(String tempatHantar4) {
        this.tempatHantar4 = tempatHantar4;
    }

    public String getTempatHantar5() {
        return tempatHantar5;
    }

    public void setTempatHantar5(String tempatHantar5) {
        this.tempatHantar5 = tempatHantar5;
    }

    public String getTempatHantar6() {
        return tempatHantar6;
    }

    public void setTempatHantar6(String tempatHantar6) {
        this.tempatHantar6 = tempatHantar6;
    }

    public String getTempatHantar7() {
        return tempatHantar7;
    }

    public void setTempatHantar7(String tempatHantar7) {
        this.tempatHantar7 = tempatHantar7;
    }

    public String getTempatHantar8() {
        return tempatHantar8;
    }

    public void setTempatHantar8(String tempatHantar8) {
        this.tempatHantar8 = tempatHantar8;
    }

    public int getTempohBulan() {
        return tempohBulan;
    }

    public void setTempohBulan(int tempohBulan) {
        this.tempohBulan = tempohBulan;
    }

    public int getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(int tempohHari) {
        this.tempohHari = tempohHari;
    }

    public PermohonanRujukanLuar getWarta() {
        return warta;
    }

    public void setWarta(PermohonanRujukanLuar warta) {
        this.warta = warta;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }
 
    public PenasihatUndang getPenasihatUndang() {
        return penasihatUndang;
    }

    public void setPenasihatUndang(PenasihatUndang penasihatUndang) {
        this.penasihatUndang = penasihatUndang;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public String getNamaTampal() {
        return namaTampal;
    }

    public void setNamaTampal(String namaTampal) {
        this.namaTampal = namaTampal;
    }

    public String getTempatTampal() {
        return tempatTampal;
    }

    public void setTempatTampal(String tempatTampal) {
        this.tempatTampal = tempatTampal;
    }

    public String getPenerimaNotis() {
        return penerimaNotis;
    }

    public void setPenerimaNotis(String penerimaNotis) {
        this.penerimaNotis = penerimaNotis;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Date getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(Date tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }
    
    

}