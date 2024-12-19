package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "kod_cukai")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKadarCukai implements Serializable {

    @Id
    @Column(name = "kod")
    private int kod;
    // NULL if applicable to all
    @ManyToOne
    @JoinColumn(name = "kod_bpm")
    private KodBandarPekanMukim bandarPekanMukim;
    // NULL if applicable to others
    @ManyToOne
    @JoinColumn(name = "kod_guna_tanah")
    private KodKegunaanTanah kegunaanTanah;
    
    @ManyToOne
    @JoinColumn(name = "KOD_SYARAT_NYATA")
    private KodSyaratNyata syaratNyata;
    
    
    @Column(name = "kadar_mp", columnDefinition = "number(12,2)", nullable = false)
    private BigDecimal kadarMeterPersegi;
    @Column(name = "minima", columnDefinition = "number(12,2)", nullable = false)
    private BigDecimal kadarMinimum;
    @Column(name = "luas", columnDefinition = "number(12,2)")
    private BigDecimal luas;
    @Column(name = "aktif")
    private char aktif;
    @Embedded
    private InfoAudit infoAudit;

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public void setKegunaanTanah(KodKegunaanTanah kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public KodKegunaanTanah getKegunaanTanah() {
        return kegunaanTanah;
    }

    public BigDecimal getKadarMeterPersegi() {
        return kadarMeterPersegi;
    }

    public void setKadarMeterPersegi(BigDecimal kadarMeterPersegi) {
        this.kadarMeterPersegi = kadarMeterPersegi;
    }

    public BigDecimal getKadarMinimum() {
        return kadarMinimum;
    }

    public void setKadarMinimum(BigDecimal kadarMinimum) {
        this.kadarMinimum = kadarMinimum;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }
    
    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public KodSyaratNyata getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(KodSyaratNyata syaratNyata) {
        this.syaratNyata = syaratNyata;
    }
}
