package etanah.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "kod_status_kew_trans")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodStatusTransaksiKewangan implements Serializable{
    @Id
    @Column (name = "kod")
    private char kod;
    
    @Column (name = "nama")
    private String nama;
    
    @Column (name = "aktif")
    private char aktif;
    
    @Embedded
    InfoAudit infoAudit;

    public void setKod(char kod) {
        this.kod = kod;
    }

    public char getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

}
