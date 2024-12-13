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
 * @author faidzal
 */
@Entity
@Table(name = "mohon_kelompok")
//@SequenceGenerator(name = "seq_mohon_kelompok", sequenceName = "seq_mohon_kelompok")
public class PermohonanKelompok implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_kelompok")
    @Column(name = "id_mohon_kelompok")
    private String idMohonKelompok;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mohon_induk")
    private Permohonan permohonanInduk;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
    @Column(name="jenis_kelompok")
    private String jenisKelopok;
    
    	@Embedded
    private InfoAudit infoAudit;

    public String getIdMohonKelompok() {
        return idMohonKelompok;
    }

    public void setIdMohonKelompok(String idMohonKelompok) {
        this.idMohonKelompok = idMohonKelompok;
    }

    public Permohonan getPermohonanInduk() {
        return permohonanInduk;
    }

    public void setPermohonanInduk(Permohonan permohonanInduk) {
        this.permohonanInduk = permohonanInduk;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getJenisKelopok() {
        return jenisKelopok;
    }

    public void setJenisKelopok(String jenisKelopok) {
        this.jenisKelopok = jenisKelopok;
    }
    
    
}
