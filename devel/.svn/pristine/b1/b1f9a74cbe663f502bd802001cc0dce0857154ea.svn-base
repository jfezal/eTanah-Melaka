package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_uom")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodUOM implements Serializable {
    @Id
    @Column (name = "kod", length = 4)
    private String kod;

    @Column (name = "kump", length = 15)
    private String kump;
    
    @Column (name = "nama", length = 70)
    private String nama;
    
    @Column (name = "aktif")
    private char aktif;
    
       @Column (name = "KOD_MYETAPP")
    private String kodMyeTapp;
    
    @Embedded
    InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public String getKump() {
        return kump;
    }

    public void setKump(String kump) {
        this.kump = kump;
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

    public char getAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public String getKodMyeTapp() {
        return kodMyeTapp;
    }

    public void setKodMyeTapp(String kodMyeTapp) {
        this.kodMyeTapp = kodMyeTapp;
    }
    
    
}
