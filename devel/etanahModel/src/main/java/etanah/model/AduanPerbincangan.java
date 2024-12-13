/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author mohd.faidzal
 */
@Entity
@Table(name = "aduan_perbincangan")
@SequenceGenerator(name = "seq_aduan_perbincangan", sequenceName = "seq_aduan_perbincangan")
public class AduanPerbincangan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_perbincangan")
    @Column(name = "ID_PERBINCGN")
    private long idAduanBincang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @Column(name = "TRH_PERBINCGN")
    private Date tarikhPerbincgn;
    
    @Column(name = "TEMPAT_PERBINCGN")
    private String tempatPerbincgn;
    
    @Column(name = "MASA_PERBINCGN")
    private String masaPerbincgn;
    
    @Column(name = "KETERANGAN_PERBINCGN")
    private String ketPerbincgn;
    
    @Column(name = "STATUS")
    private String statusPerbincgn;

    @Embedded
    private InfoAudit infoAudit;

    public long getIdAduanBincang() {
        return idAduanBincang;
    }

    public void setIdAduanBincang(long idAduanBincang) {
        this.idAduanBincang = idAduanBincang;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Date getTarikhPerbincgn() {
        return tarikhPerbincgn;
    }

    public void setTarikhPerbincgn(Date tarikhPerbincgn) {
        this.tarikhPerbincgn = tarikhPerbincgn;
    }

    public String getTempatPerbincgn() {
        return tempatPerbincgn;
    }

    public void setTempatPerbincgn(String tempatPerbincgn) {
        this.tempatPerbincgn = tempatPerbincgn;
    }

    public String getMasaPerbincgn() {
        return masaPerbincgn;
    }

    public void setMasaPerbincgn(String masaPerbincgn) {
        this.masaPerbincgn = masaPerbincgn;
    }

    public String getKetPerbincgn() {
        return ketPerbincgn;
    }

    public void setKetPerbincgn(String ketPerbincgn) {
        this.ketPerbincgn = ketPerbincgn;
    }

    public String getStatusPerbincgn() {
        return statusPerbincgn;
    }

    public void setStatusPerbincgn(String statusPerbincgn) {
        this.statusPerbincgn = statusPerbincgn;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
