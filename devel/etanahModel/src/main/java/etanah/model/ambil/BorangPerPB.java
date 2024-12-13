/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNotis;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author zuraida
 *
 */
@Entity
@Table(name = "borang_per_pb")
@SequenceGenerator(name = "seq_borang_per_pb", sequenceName = "seq_borang_per_pb")
public class BorangPerPB implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_borang_per_pb")
    @Column(name = "id_borang_pb")
    private long id;

    @ManyToOne
    @JoinColumn(name = "kod_notis")
    private KodNotis kodNotis;
    
    @ManyToOne
    @JoinColumn(name = "id_dok")
    private Dokumen dokumen;
    
    @ManyToOne
    @JoinColumn(name = "id_borang_hm")
    private BorangPerHakmilik borangPerHakmilik;
    
    @Column (name = "ditandatangan")
    private String ditandatangan;
    
    @Column (name = "catatan")
    private String catatan;
    
    @Column (name = "trh_tt")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trh_tt;
    
     @Column (name = "jenis_kepentingan")
    private String jenis_kepentingan;
        
    @Column(name = "nama", length = 250)
    private String nama;
    @Column(name = "no_pengenalan", length = 20)
    private String noPengenalan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOD_PENGENALAN")
    private KodJenisPengenalan jenisPengenalan;
    
     @Column(name = "syer_pembilang", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPembilang;

    @Column(name = "syer_penyebut", precision = 14, scale = 0, columnDefinition = "number(14,0)")
    private Integer syerPenyebut;
    @OneToMany(mappedBy = "borangPerPB", fetch = FetchType.LAZY)
    private List<TuntutanPerPB> senaraiTuntutanPerPB;
    
      @ManyToOne
    @JoinColumn(name = "ID_BORANG_PERPB")
    private BorangPerPB borangPerPb;
     @Embedded
    private Alamat alamat;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public KodNotis getKodNotis() {
        return kodNotis;
    }

    public void setKodNotis(KodNotis kodNotis) {
        this.kodNotis = kodNotis;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getDitandatangan() {
        return ditandatangan;
    }

    public void setDitandatangan(String ditandatangan) {
        this.ditandatangan = ditandatangan;
    }

    public Date getTrh_tt() {
        return trh_tt;
    }

    public void setTrh_tt(Date trh_tt) {
        this.trh_tt = trh_tt;
    }
    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getJenis_kepentingan() {
        return jenis_kepentingan;
    }

    public void setJenis_kepentingan(String jenis_kepentingan) {
        this.jenis_kepentingan = jenis_kepentingan;
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

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public BorangPerHakmilik getBorangPerHakmilik() {
        return borangPerHakmilik;
    }

    public void setBorangPerHakmilik(BorangPerHakmilik borangPerHakmilik) {
        this.borangPerHakmilik = borangPerHakmilik;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public List<TuntutanPerPB> getSenaraiTuntutanPerPB() {
        return senaraiTuntutanPerPB;
    }

    public void setSenaraiTuntutanPerPB(List<TuntutanPerPB> senaraiTuntutanPerPB) {
        this.senaraiTuntutanPerPB = senaraiTuntutanPerPB;
    }

    public BorangPerPB getBorangPerPb() {
        return borangPerPb;
    }

    public void setBorangPerPb(BorangPerPB borangPerPb) {
        this.borangPerPb = borangPerPb;
    }
    
    

}
