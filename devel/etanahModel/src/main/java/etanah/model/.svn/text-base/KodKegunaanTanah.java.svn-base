package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "kod_guna_tanah")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKegunaanTanah implements Serializable {

    @Id
    @Column(name = "kod")
    private String kod;
    @Column(name = "nama", nullable = false, unique = true)
    private String nama;
    @Column(name = "perihal")
    private String perihal;
    @Column(name = "aktif")
    private char aktif;
    @ManyToOne
    @JoinColumn(name = "kod_katg_tanah")
    private KodKategoriTanah kategoriTanah;
    @Column (name = "kod_myetapp")
    private String kodMyEtapp;
    @Column (name = "KOD_GUNA_TANAH_KOD_CUKAI")
    private String kodGunaTanahKodCkai;
    @Embedded
    InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public char getAktif() {
        return aktif;
    }

    public KodKategoriTanah getKategoriTanah() {
        return kategoriTanah;
    }

    public void setKategoriTanah(KodKategoriTanah kategoriTanah) {
        this.kategoriTanah = kategoriTanah;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public String getKodMyEtapp() {
        return kodMyEtapp;
    }

    public void setKodMyEtapp(String kodMyEtapp) {
        this.kodMyEtapp = kodMyEtapp;
    }    

    public String getKodGunaTanahKodCkai() {
        return kodGunaTanahKodCkai;
    }

    public void setKodGunaTanahKodCkai(String kodGunaTanahKodCkai) {
        this.kodGunaTanahKodCkai = kodGunaTanahKodCkai;
    }
}
