/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "aduan_surat")
@SequenceGenerator(name = "seq_aduan_surat", sequenceName = "seq_aduan_surat")
public class AduanSurat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aduan_surat")
    @Column(name = "id_aduan_lokasi")
    private long idAduanSurat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;

    @Column(name = "seq_aduan_surat")
    private Date tarikhTerima;

    @Column(name = "kod_caw")
    private KodCawangan cawangan;

    @Column(name = "keterangan_surat")
    private String ketSurat;

    @Column(name = "trh_terima")
    private String tarikhTerimaBayaran;

    @Column(name = "no_rujukan")
    private String noRujukan;

   
    @Column(name = "id_hakmilik")
    private Hakmilik idHakmilik;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdAduanSurat() {
        return idAduanSurat;
    }

    public void setIdAduanSurat(long idAduanSurat) {
        this.idAduanSurat = idAduanSurat;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Date getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(Date tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getKetSurat() {
        return ketSurat;
    }

    public void setKetSurat(String ketSurat) {
        this.ketSurat = ketSurat;
    }

    public String getTarikhTerimaBayaran() {
        return tarikhTerimaBayaran;
    }

    public void setTarikhTerimaBayaran(String tarikhTerimaBayaran) {
        this.tarikhTerimaBayaran = tarikhTerimaBayaran;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Hakmilik getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(Hakmilik idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    

}
