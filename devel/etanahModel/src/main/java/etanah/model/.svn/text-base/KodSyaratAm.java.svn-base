package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_syarat_am")
@SequenceGenerator(name = "seq_kod_syarat_am", sequenceName = "seq_kod_syarat_am")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodSyaratAm implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_kod_syarat_am")
    @Column (name = "kod")
    private Integer kod;
    
    @Column (name = "nama")
    private String nama;
    
    @Column (name = "aktif")
    private char aktif;
    
    @Embedded
    InfoAudit infoAudit;

    public Integer getKod() {
        return kod;
    }

    public void setKod(Integer kod) {
        this.kod = kod;
    }


    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setAktif(char aktif) {
        this.aktif =aktif;
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
