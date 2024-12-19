/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "kkuasa_brg_op")
@SequenceGenerator(name = "seq_kkuasa_brg_op", sequenceName = "seq_kkuasa_brg_op")
public class OperasiBarangPenguatkuasaan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kkuasa_brg_op")
    @Column(name = "id_kkuasa_brg_op")
    private Long idBarangOperasi;
    
    @Column(name = "nama")
    private String nama;
    
    @Column(name = "jenis")
    private String jenis;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "kntt")
    private int kntt;
    
    @Column(name = "katg_brg_op")
    private char kategoriBarangOperasi;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_op")
    private OperasiPenguatkuasaan operasi;
    
     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pguna")
    private Pengguna pengguna;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdBarangOperasi() {
        return idBarangOperasi;
    }

    public void setIdBarangOperasi(Long idBarangOperasi) {
        this.idBarangOperasi = idBarangOperasi;
    }    

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKntt() {
        return kntt;
    }

    public void setKntt(int kntt) {
        this.kntt = kntt;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public OperasiPenguatkuasaan getOperasi() {
        return operasi;
    }

    public void setOperasi(OperasiPenguatkuasaan operasi) {
        this.operasi = operasi;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public char getKategoriBarangOperasi() {
        return kategoriBarangOperasi;
    }

    public void setKategoriBarangOperasi(char kategoriBarangOperasi) {
        this.kategoriBarangOperasi = kategoriBarangOperasi;
    } 
}
