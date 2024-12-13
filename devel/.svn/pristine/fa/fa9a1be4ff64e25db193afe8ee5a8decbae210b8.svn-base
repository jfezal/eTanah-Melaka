/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.model.PermohonanPihakWakil;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ubuntu
 */
@Entity
@Table(name = "ambil_bicara_tuntutan")
public class PengambilanBicaraTuntutan implements Serializable {

    @Id
    @Column(name = "ID_ABT")
    private long idPengambilanBicaraTuntutan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MP")
    private PermohonanPihak permohonanPihak;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MPT")
    private PermohonanPihakTidakBerkepentingan permohonanPihakTidakPenting;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MPW")
    private PermohonanPihakWakil wakilPermohonanPihak;    
    @Column(name="JUMLAH")
    private BigDecimal jumlah;
    @ManyToOne
    @JoinColumn(name = "KOD_BANK")
    private KodBank bank;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdPengambilanBicaraTuntutan() {
        return idPengambilanBicaraTuntutan;
    }

    public void setIdPengambilanBicaraTuntutan(long idPengambilanBicaraTuntutan) {
        this.idPengambilanBicaraTuntutan = idPengambilanBicaraTuntutan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanPihakTidakBerkepentingan getPermohonanPihakTidakPenting() {
        return permohonanPihakTidakPenting;
    }

    public void setPermohonanPihakTidakPenting(PermohonanPihakTidakBerkepentingan permohonanPihakTidakPenting) {
        this.permohonanPihakTidakPenting = permohonanPihakTidakPenting;
    }

    public PermohonanPihakWakil getWakilPermohonanPihak() {
        return wakilPermohonanPihak;
    }

    public void setWakilPermohonanPihak(PermohonanPihakWakil wakilPermohonanPihak) {
        this.wakilPermohonanPihak = wakilPermohonanPihak;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public KodBank getBank() {
        return bank;
    }

    public void setBank(KodBank bank) {
        this.bank = bank;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
}
