/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.InfoAudit;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "kod_perihal_tanah")
@SequenceGenerator(name = "seq_kod_perihal_tanah", sequenceName = "seq_kod_perihal_tanah")
public class KodPerihalTanah {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_kod_perihal_tanah")
    @Column(name = "kod_perihal")
    private String kod;
   
    @Column(name = "nama")
    private String nama;

    @Column(name = "status")
    private String status;
   
    @Embedded
    InfoAudit infoAudit;

    


    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }



  
    
}
