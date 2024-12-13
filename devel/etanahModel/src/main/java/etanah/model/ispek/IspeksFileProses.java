/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.InfoAudit;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "ISPEKS_FILE_PROSES")
@SequenceGenerator(name = "SEQ_ISPEKS_FILE_PROSES", sequenceName = "SEQ_ISPEKS_FILE_PROSES")
public class IspeksFileProses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_FILE_PROSES")
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAMA_FAIL")
    String namaFail;
    @Column(name = "TARIKH_MUAT_TURUN")
    Date tarikhMuatTurun;
    @Column(name = "KATEGORI")
    String kategori;
    @Column(name = "STATUS")
    String status;
    @Embedded
    private InfoAudit infoAudit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaFail() {
        return namaFail;
    }

    public void setNamaFail(String namaFail) {
        this.namaFail = namaFail;
    }

    public Date getTarikhMuatTurun() {
        return tarikhMuatTurun;
    }

    public void setTarikhMuatTurun(Date tarikhMuatTurun) {
        this.tarikhMuatTurun = tarikhMuatTurun;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
