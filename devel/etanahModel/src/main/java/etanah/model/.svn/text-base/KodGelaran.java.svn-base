package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_gelaran")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodGelaran implements Serializable{
    
    @Id
    @Column (name = "KOD")
    private String kod;

    @Column (name = "nama", nullable = false )
    private String nama;


    @Column (name = "AKTIF", length = 1)
    private char aktif;
    
    @ManyToOne
    @JoinColumn (name = "kod_panggilan")
    private KodPanggilan kodPanggilan;
    
    @Embedded
    private InfoAudit infoAudit;



    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
 
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
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

    public KodPanggilan getKodPanggilan() {
        return kodPanggilan;
    }

    public void setKodPanggilan(KodPanggilan kodPanggilan) {
        this.kodPanggilan = kodPanggilan;
    }
    
}
