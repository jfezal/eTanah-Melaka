/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.etapp;

import etanah.model.InfoAudit;
import java.io.Serializable;
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
 * @author faidzal
 */
/*
 ETAPP_LOG

 ID_ETAPP_LOG
 MODUL
 URUSAN
 STATUS

 DIMASUK
 TRH_MASUK
 DIKKINI
 TRK_KKINI
 */
@Entity
@Table(name = "etapp_log")
@SequenceGenerator(name = "seq_ETAPP_LOG", sequenceName = "seq_ETAPP_LOG")
public class EtappLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ETAPP_LOG")
    @Column(name = "id_etapp_log")
    private Long id_etapp_log;
    
    @Column(name = "modul")
    private String modul;
    
    @Column(name = "urusan")
    private String urusan;
    
    @Column(name = "status")
    private String status;
    
    @Column(name="JSON")
    private String json;
    
        @Column(name="flag_hantar")
    private String flagHantar;
    
    @Embedded
    private InfoAudit infoAudit;

    public Long getId_etapp_log() {
        return id_etapp_log;
    }

    public void setId_etapp_log(Long id_etapp_log) {
        this.id_etapp_log = id_etapp_log;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getFlagHantar() {
        return flagHantar;
    }

    public void setFlagHantar(String flagHantar) {
        this.flagHantar = flagHantar;
    }
    
    
}
