/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

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
 * @author mohd.faidzal
 */
@Entity
@Table(name = "LOG_WS")
@SequenceGenerator(name = "SEQ_LOG_WS", sequenceName = "SEQ_LOG_WS")
public class LogWS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_LOG_WS")
    @Column(name = "ID")
    private Long id;
    @Column(name = "JENIS_WS")
    private String jenisWs;
    @Column(name = "MODUL")
    private String modul;
    @Column(name = "LOG_INFO")
    private String logInfo;
    @Column(name = "JSON_REQ")
    private String jsonReq;
    @Embedded
    private InfoAudit infoAudit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJenisWs() {
        return jenisWs;
    }

    public void setJenisWs(String jenisWs) {
        this.jenisWs = jenisWs;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public String getJsonReq() {
        return jsonReq;
    }

    public void setJsonReq(String jsonReq) {
        this.jsonReq = jsonReq;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    
}
