package etanah.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "aduan_strata")
@SequenceGenerator(name = "seq_aduan_strata", sequenceName = "seq_aduan_strata")
public class AduanStrata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_strata")
    @Column(name = "id_aduan_strata")
    private long idAduanStrata;
    // tempat aduan dibuat
    @ManyToOne
    @JoinColumn(name = "kod_caw", nullable = false)
    private KodCawangan cawangan;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon", nullable = false)
    private Permohonan permohonan;

    @ManyToOne
    @JoinColumn(name = "kod_lot")
    private KodLot kodLot;
    
    @Column(name = "no_lot")
    private String noLot;

    @ManyToOne
    @JoinColumn(name = "kod_pbt")
    private KodPBT kodPbt;

    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @Column(name = "lokasi")
    private String lokasi;

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
    private KodNegeri kodNegeri;

    @Column(name = "nama_penjual")
    private String namaPenjual;

    @Column(name = "nama_pemaju")
    private String namaPemaju;

    @Column(name = "nama_pembeli")
    private String namaPembeli;

    @Column(name = "trh_tt_pjb")
    private Date tarikhTandatanganPerjanjianJualBeli;


    @Column(name = "skim_strata")
    private String skimStrata;

    @Column(name = "kenderaan_siasat")
    private String kenderaanSiasatan;

    @Column(name = "ulasan")
    private String ulasan;

    @Column(name = "cf_no_sijil")
    private String cfNoSijil;

    @Column(name = "cf_Trh_Keluar")
    private Date cfTarikhKeluar;

    @Column(name = "no_ruj_pelan_bngn")
    private String noRujukanPelanBangunan;

    @Column(name = "trh_siasat")
    private Date tarikhSiasatan;

    @Embedded
    private InfoAudit infoAudit;

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

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }


    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public long getIdAduanStrata() {
        return idAduanStrata;
    }

    public void setIdAduanStrata(long idAduanStrata) {
        this.idAduanStrata = idAduanStrata;
    }

    public String getKenderaanSiasatan() {
        return kenderaanSiasatan;
    }

    public void setKenderaanSiasatan(String kenderaanSiasatan) {
        this.kenderaanSiasatan = kenderaanSiasatan;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public KodPBT getKodPbt() {
        return kodPbt;
    }

    public void setKodPbt(KodPBT kodPbt) {
        this.kodPbt = kodPbt;
    }

    public String getNamaPemaju() {
        return namaPemaju;
    }

    public void setNamaPemaju(String namaPemaju) {
        this.namaPemaju = namaPemaju;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getNamaPenjual() {
        return namaPenjual;
    }

    public void setNamaPenjual(String namaPenjual) {
        this.namaPenjual = namaPenjual;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getSkimStrata() {
        return skimStrata;
    }

    public void setSkimStrata(String skimStrata) {
        this.skimStrata = skimStrata;
    }

    public Date getTarikhTandatanganPerjanjianJualBeli() {
        return tarikhTandatanganPerjanjianJualBeli;
    }

    public void setTarikhTandatanganPerjanjianJualBeli(Date tarikhTandatanganPerjanjianJualBeli) {
        this.tarikhTandatanganPerjanjianJualBeli = tarikhTandatanganPerjanjianJualBeli;
    }

    public String getCfNoSijil() {
        return cfNoSijil;
    }

    public void setCfNoSijil(String cfNoSijil) {
        this.cfNoSijil = cfNoSijil;
    }

    public Date getCfTarikhKeluar() {
        return cfTarikhKeluar;
    }

    public void setCfTarikhKeluar(Date cfTarikhKeluar) {
        this.cfTarikhKeluar = cfTarikhKeluar;
    }

    public String getNoRujukanPelanBangunan() {
        return noRujukanPelanBangunan;
    }

    public void setNoRujukanPelanBangunan(String noRujukanPelanBangunan) {
        this.noRujukanPelanBangunan = noRujukanPelanBangunan;
    }

    public Date getTarikhSiasatan() {
        return tarikhSiasatan;
    }

    public void setTarikhSiasatan(Date tarikhSiasatan) {
        this.tarikhSiasatan = tarikhSiasatan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    




    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
