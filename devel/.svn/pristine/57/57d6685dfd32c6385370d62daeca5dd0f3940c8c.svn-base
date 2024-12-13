/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
 * @author ubuntu
 */
@Entity
@Table(name = "penyerah_brg_op")
@SequenceGenerator(name = "seq_penyerah_brg_op", sequenceName = "seq_penyerah_brg_op")
public class PenyerahBarangOperasi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_penyerah_brg_op")
    @Column(name = "id_penyerah_brg_op")
    private Long idPenyerahBarangOperasi;
    @ManyToOne
    @JoinColumn(name = "id_pguna")
    private Pengguna pengguna;
    @Column(name = "trh_serah")
    private Date tarikhPerserahan;
    @Column(name = "tpt_serah")
    private String tempatPerserahan;
    @Column(name = "no_serah")
    private String noPerserahan;
    @Column(name = "penerima_nama")
    private String namaPenerima;
    @ManyToOne
    @JoinColumn(name = "penerima_kod_pengenalan")
    private KodJenisPengenalan jenisPengenalanPenerima;
    @Column(name = "penerima_no_pengenalan")
    private String noPengenalanPenerima;
    @Column(name = "penerima_jawatan")
    private String jawatanPenerima;
    @Column(name = "penerima_alamat1")
    private String alamatPenerima1;
    @Column(name = "penerima_alamat2")
    private String alamatPenerima2;
    @Column(name = "penerima_alamat3")
    private String alamatPenerima3;
    @Column(name = "penerima_alamat4")
    private String alamatPenerima4;
    @Column(name = "penerima_poskod")
    private String poskodPenerima;
    @ManyToOne
    @JoinColumn(name = "penerima_kod_negeri")
    private KodNegeri negeriPenerima;
    @Column(name = "katg_penerima")
    private String kategoriPenerima;
    
    @ManyToOne
    @JoinColumn(name = "id_op")
    private OperasiPenguatkuasaan operasi;
    
    @ManyToOne
    @JoinColumn(name = "id_dok_tt")
    private PermohonanTandatanganDokumen dokumenTandatangan;
    
//    @ManyToOne
//    @JoinColumn(name = "id_op_brg")
//    private BarangRampasan barangRampasan;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdPenyerahBarangOperasi() {
        return idPenyerahBarangOperasi;
    }

    public void setIdPenyerahBarangOperasi(Long idPenyerahBarangOperasi) {
        this.idPenyerahBarangOperasi = idPenyerahBarangOperasi;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Date getTarikhPerserahan() {
        return tarikhPerserahan;
    }

    public void setTarikhPerserahan(Date tarikhPerserahan) {
        this.tarikhPerserahan = tarikhPerserahan;
    }

    public String getTempatPerserahan() {
        return tempatPerserahan;
    }

    public void setTempatPerserahan(String tempatPerserahan) {
        this.tempatPerserahan = tempatPerserahan;
    }

    public String getNoPerserahan() {
        return noPerserahan;
    }

    public void setNoPerserahan(String noPerserahan) {
        this.noPerserahan = noPerserahan;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public KodJenisPengenalan getJenisPengenalanPenerima() {
        return jenisPengenalanPenerima;
    }

    public void setJenisPengenalanPenerima(KodJenisPengenalan jenisPengenalanPenerima) {
        this.jenisPengenalanPenerima = jenisPengenalanPenerima;
    }

    public String getNoPengenalanPenerima() {
        return noPengenalanPenerima;
    }

    public void setNoPengenalanPenerima(String noPengenalanPenerima) {
        this.noPengenalanPenerima = noPengenalanPenerima;
    }

    public String getJawatanPenerima() {
        return jawatanPenerima;
    }

    public void setJawatanPenerima(String jawatanPenerima) {
        this.jawatanPenerima = jawatanPenerima;
    }

    public String getAlamatPenerima1() {
        return alamatPenerima1;
    }

    public void setAlamatPenerima1(String alamatPenerima1) {
        this.alamatPenerima1 = alamatPenerima1;
    }

    public String getAlamatPenerima2() {
        return alamatPenerima2;
    }

    public void setAlamatPenerima2(String alamatPenerima2) {
        this.alamatPenerima2 = alamatPenerima2;
    }

    public String getAlamatPenerima3() {
        return alamatPenerima3;
    }

    public void setAlamatPenerima3(String alamatPenerima3) {
        this.alamatPenerima3 = alamatPenerima3;
    }

    public String getAlamatPenerima4() {
        return alamatPenerima4;
    }

    public void setAlamatPenerima4(String alamatPenerima4) {
        this.alamatPenerima4 = alamatPenerima4;
    }

    public String getPoskodPenerima() {
        return poskodPenerima;
    }

    public void setPoskodPenerima(String poskodPenerima) {
        this.poskodPenerima = poskodPenerima;
    }

    public KodNegeri getNegeriPenerima() {
        return negeriPenerima;
    }

    public void setNegeriPenerima(KodNegeri negeriPenerima) {
        this.negeriPenerima = negeriPenerima;
    }

    public String getKategoriPenerima() {
        return kategoriPenerima;
    }

    public void setKategoriPenerima(String kategoriPenerima) {
        this.kategoriPenerima = kategoriPenerima;
    }

    public OperasiPenguatkuasaan getOperasi() {
        return operasi;
    }

    public void setOperasi(OperasiPenguatkuasaan operasi) {
        this.operasi = operasi;
    }

    public PermohonanTandatanganDokumen getDokumenTandatangan() {
        return dokumenTandatangan;
    }

    public void setDokumenTandatangan(PermohonanTandatanganDokumen dokumenTandatangan) {
        this.dokumenTandatangan = dokumenTandatangan;
    }

//    public BarangRampasan getBarangRampasan() {
//        return barangRampasan;
//    }
//
//    public void setBarangRampasan(BarangRampasan barangRampasan) {
//        this.barangRampasan = barangRampasan;
//    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
}
