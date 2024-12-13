package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_dok_iringan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodDokumenIringan implements Serializable {
    
    @Id
    @Column (name = "kod_dokumen")
    private String kodDokumen;
   // @Id
   // @OneToOne (fetch = FetchType.LAZY)
   // @PrimaryKeyJoinColumn
   // private KodDokumen kodDokumen;



    @Column (name = "aktif")
    private char aktif;
    
    @Embedded
    InfoAudit infoAudit;

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
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
