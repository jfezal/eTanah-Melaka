/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_aduan_portal")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodAduanPortal implements Serializable{
    
     @Id
    @Column(name = "kod")
    private Integer kod;
       
    @Column(name = "nama")
    private String jenisAduan;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;
    
     @Embedded
    private InfoAudit infoAudit;

    public Integer getKod() {
        return kod;
    }

    public void setKod(Integer kod) {
        this.kod = kod;
    }

    public String getJenisAduan() {
        return jenisAduan;
    }

    public void setJenisAduan(String jenisAduan) {
        this.jenisAduan = jenisAduan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
     
     
    
}
