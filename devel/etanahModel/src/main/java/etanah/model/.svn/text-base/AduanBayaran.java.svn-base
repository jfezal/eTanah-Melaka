/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "aduan_bayaran")
@SequenceGenerator(name = "seq_aduan_bayaran", sequenceName = "seq_aduan_bayaran")
public class AduanBayaran implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_aduan_bayaran")
    @Column(name = "id_bayaran")
    private long idAduanBayaran;
    
    @Column(name = "amaun")
    private BigDecimal amaunTerima;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KOD_CARA_BAYAR")
    private KodCaraBayaran kodCaraBayar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    
    @Column(name = "NO_RUJUKAN")
    private String noRujukan;
    
    @Column(name = "NAMA_PENERIMA_CEK")
    private String namaPenerimaCek;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SURAT")
    private AduanSurat aduanSurat;
    
    @Embedded
    private InfoAudit infoAudit;

    public long getIdAduanBayaran() {
        return idAduanBayaran;
    }

    public void setIdAduanBayaran(long idAduanBayaran) {
        this.idAduanBayaran = idAduanBayaran;
    }

    public BigDecimal getAmaunTerima() {
        return amaunTerima;
    }

    public void setAmaunTerima(BigDecimal amaunTerima) {
        this.amaunTerima = amaunTerima;
    }

    public KodCaraBayaran getKodCaraBayar() {
        return kodCaraBayar;
    }

    public void setKodCaraBayar(KodCaraBayaran kodCaraBayar) {
        this.kodCaraBayar = kodCaraBayar;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNamaPenerimaCek() {
        return namaPenerimaCek;
    }

    public void setNamaPenerimaCek(String namaPenerimaCek) {
        this.namaPenerimaCek = namaPenerimaCek;
    }

    public AduanSurat getAduanSurat() {
        return aduanSurat;
    }

    public void setAduanSurat(AduanSurat aduanSurat) {
        this.aduanSurat = aduanSurat;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
