package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "kod_petak_aksr")
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKegunaanPetakAksesori implements Serializable {

    @Id
    @Column(name = "kod")
    private String kod;

    @Column(name = "nama")
    private String nama;

    @Column(name = "perihal")
    private String perihal;

    @Column(name = "aktif")
    private String aktif;

    @Embedded
    private InfoAudit infoAudit;

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

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

}
