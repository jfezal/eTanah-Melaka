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
 * @author fikri
 */
@Entity
@Table(name = "bongkar_hadir")
@SequenceGenerator(name = "seq_bongkar_hadir", sequenceName = "seq_bongkar_hadir")
public class BongkarKehadiran implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bongkar_hadir")
    @Column(name = "id_bongkar_hadir")
    private long idBongkarKehadiran;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_pengenalan")
    private KodJenisPengenalan jenisPengenalan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_oks")
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    
    @Column (name = "nama")
    private String nama;
    
    @Column (name = "no_pengenalan")
    private String noPengenalan;
    
    @Column (name = "hubungan")
    private String hubungan;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdBongkarKehadiran() {
        return idBongkarKehadiran;
    }

    public void setIdBongkarKehadiran(long idBongkarKehadiran) {
        this.idBongkarKehadiran = idBongkarKehadiran;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
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

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
}