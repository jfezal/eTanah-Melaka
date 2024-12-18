/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.jompay;

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


@Entity
@Table(name = "JOMPAY_FAIL_MUATNAIK")
@SequenceGenerator(name = "SEQ_JOMPAY_FAIL_MUATNAIK", sequenceName = "SEQ_JOMPAY_FAIL_MUATNAIK")
public class JomPayFailMuatnaik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_JOMPAY_FAIL_MUATNAIK")
    @Column(name = "ID_JOMPAYUPLOAD")
    private Long idJomPayUpload;
    
    @Column(name = "NAMA_FAIL")
    String namaFail;
    
    @Column(name = "PATH_FAIL")
    String pathFail;
     
    @Column(name = "TARIKH_MUAT_NAIK")
    Date tarikhMuatTurun;
    
    @Column(name = "STATUS")
    String status;
    
     @Column(name = "FORMAT")
    String format;
     
    @Embedded
    private InfoAudit infoAudit;

    public Long getIdJomPayUpload() {
        return idJomPayUpload;
    }

    public void setIdJomPayUpload(Long idJomPayUpload) {
        this.idJomPayUpload = idJomPayUpload;
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

    public String getPathFail() {
        return pathFail;
    }

    public void setPathFail(String pathFail) {
        this.pathFail = pathFail;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    
    

    
}
