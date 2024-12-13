package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "hakmilik_petak_aksr")
@SequenceGenerator(name = "seq_hakmilik_petak_aksr", sequenceName = "seq_hakmilik_petak_aksr", allocationSize = 1)
public class HakmilikPetakAksesori implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_hakmilik_petak_aksr")
    @Column(name = "id_aksr")
    private long idAksesori;

    @ManyToOne
    @JoinColumn(name = "kod_caw")
    private KodCawangan cawangan;

    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;

    @Column(name = "upi")
    private String UPI;

    @Column(name = "nama")
    private String nama;

    @ManyToOne
    @JoinColumn(name = "kod_petak_aksr")
    private KodKegunaanPetakAksesori kegunaaanPetak;

    @ManyToOne
    @JoinColumn(name = "sts")
    private KodStatusHakmilik statusHakmilik;

    @Column(name = "trh_batal")
    private Date tarikhBatal;

    @Column(name = "dibatal")
    private String diBatalOleh;

    @Column(name = "lokasi")
    private String lokasi;

    @Column(name = "PTKSANGKUT")
    private String petakSangkut;

    @Column(name = "NO_PELAN_AKSR")
    private String noPelan;

    @Column(name = "luas")
    private BigDecimal luas;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdAksesori() {
        return idAksesori;
    }

    public void setIdAksesori(long idAksesori) {
        this.idAksesori = idAksesori;
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

    public void setUPI(String uPI) {
        UPI = uPI;
    }

    public String getUPI() {
        return UPI;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDiBatalOleh() {
        return diBatalOleh;
    }

    public void setDiBatalOleh(String diBatalOleh) {
        this.diBatalOleh = diBatalOleh;
    }

    public KodStatusHakmilik getStatusHakmilik() {
        return statusHakmilik;
    }

    public void setStatusHakmilik(KodStatusHakmilik statusHakmilik) {
        this.statusHakmilik = statusHakmilik;
    }

    public Date getTarikhBatal() {
        return tarikhBatal;
    }

    public void setTarikhBatal(Date tarikhBatal) {
        this.tarikhBatal = tarikhBatal;
    }

    public KodKegunaanPetakAksesori getKegunaaanPetak() {
        return kegunaaanPetak;
    }

    public void setKegunaaanPetak(KodKegunaanPetakAksesori kegunaaanPetak) {
        this.kegunaaanPetak = kegunaaanPetak;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getPetakSangkut() {
        return petakSangkut;
    }

    public void setPetakSangkut(String petakSangkut) {
        this.petakSangkut = petakSangkut;
    }

    public String getNoPelan() {
        return noPelan;
    }

    public void setNoPelan(String noPelan) {
        this.noPelan = noPelan;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }
    
}
