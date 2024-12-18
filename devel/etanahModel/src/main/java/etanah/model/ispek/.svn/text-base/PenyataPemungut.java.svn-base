/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "ISPEKS_PENYATA_PEMUNGUT")
@SequenceGenerator(name = "seq_ispeks_penyata_pemungut", sequenceName = "seq_ispeks_penyata_pemungut")
public class PenyataPemungut implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ispeks_penyata_pemungut")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NO_PENYATA", nullable = false)
    private String noPenyata;
    @Column(name = "NO_SLIP_BANK")
    private String noSlipBank;
    @Column(name = "TRH_JANA")
    private Date tarikhJana;
    @Column(name = "PENYEDIA")
    private String penyedia;
    @Column(name = "TRH_SEDIA")
    private Date tarikhSedia;
    @Column(name = "PENYEMAK")
    private String penyemak;
    @Column(name = "TRH_SEMAK")
    private Date tarikhSemak;
    @Column(name = "PELULUS")
    private String pelulus;
    @Column(name = "TRH_LULUS")
    private Date tarikhLulus;
    @Column(name = "NAMA_BANK")
    private String namaBank;
    @Column(name = "NO_AKAUN_BANK")
    private String noAkaunBank;
    @Column(name = "TRH_MULA")
    private Date tarikhMula;
    @Column(name = "TRH_AKHIR")
    private Date tarikhAkhir;
    @Column(name = "CARA_BAYAR")
    private String caraBayar;
    @Column(name = "TRH_HANTAR")
    private Date tarikhHantar;
    @Column(name = "TRH_BANK")
    private Date tarikhBank;
    @Column(name = "JAW_PENYEDIA")
    private String jawatanPenyedia;
    @Column(name = "JAW_PENYEMAK")
    private String jawatanPenyemak;
    @Column(name = "JAW_PELULUS")
    private String jawatanPelulus;
    @ManyToOne
    @JoinColumn(name = "AKAUN_BANK")
    AkaunBank akaunBank;
       @ManyToOne
    @JoinColumn(name = "KOD_CAW")
       KodCawangan kodCaw;
       @ManyToOne
    @JoinColumn(name = "DI_JANA_OLEH")
       Pengguna diJanaOleh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoPenyata() {
        return noPenyata;
    }

    public void setNoPenyata(String noPenyata) {
        this.noPenyata = noPenyata;
    }

    public String getNoSlipBank() {
        return noSlipBank;
    }

    public void setNoSlipBank(String noSlipBank) {
        this.noSlipBank = noSlipBank;
    }

    public Date getTarikhJana() {
        return tarikhJana;
    }

    public void setTarikhJana(Date tarikhJana) {
        this.tarikhJana = tarikhJana;
    }

    public String getPenyedia() {
        return penyedia;
    }

    public void setPenyedia(String penyedia) {
        this.penyedia = penyedia;
    }

    public Date getTarikhSedia() {
        return tarikhSedia;
    }

    public void setTarikhSedia(Date tarikhSedia) {
        this.tarikhSedia = tarikhSedia;
    }

    public String getPenyemak() {
        return penyemak;
    }

    public void setPenyemak(String penyemak) {
        this.penyemak = penyemak;
    }

    public Date getTarikhSemak() {
        return tarikhSemak;
    }

    public void setTarikhSemak(Date tarikhSemak) {
        this.tarikhSemak = tarikhSemak;
    }

    public String getPelulus() {
        return pelulus;
    }

    public void setPelulus(String pelulus) {
        this.pelulus = pelulus;
    }

    public Date getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(Date tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNoAkaunBank() {
        return noAkaunBank;
    }

    public void setNoAkaunBank(String noAkaunBank) {
        this.noAkaunBank = noAkaunBank;
    }

    public Date getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(Date tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public Date getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(Date tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

    public String getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(String caraBayar) {
        this.caraBayar = caraBayar;
    }

    public Date getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(Date tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public Date getTarikhBank() {
        return tarikhBank;
    }

    public void setTarikhBank(Date tarikhBank) {
        this.tarikhBank = tarikhBank;
    }

    public String getJawatanPenyedia() {
        return jawatanPenyedia;
    }

    public void setJawatanPenyedia(String jawatanPenyedia) {
        this.jawatanPenyedia = jawatanPenyedia;
    }

    public String getJawatanPenyemak() {
        return jawatanPenyemak;
    }

    public void setJawatanPenyemak(String jawatanPenyemak) {
        this.jawatanPenyemak = jawatanPenyemak;
    }

    public String getJawatanPelulus() {
        return jawatanPelulus;
    }

    public void setJawatanPelulus(String jawatanPelulus) {
        this.jawatanPelulus = jawatanPelulus;
    }

    public AkaunBank getAkaunBank() {
        return akaunBank;
    }

    public void setAkaunBank(AkaunBank akaunBank) {
        this.akaunBank = akaunBank;
    }

    public KodCawangan getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(KodCawangan kodCaw) {
        this.kodCaw = kodCaw;
    }

    public Pengguna getDiJanaOleh() {
        return diJanaOleh;
    }

    public void setDiJanaOleh(Pengguna diJanaOleh) {
        this.diJanaOleh = diJanaOleh;
    }
    
    
    
    
}
