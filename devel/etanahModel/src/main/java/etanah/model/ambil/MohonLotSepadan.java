/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodLot;
import javax.persistence.Column;
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
 * @author zuraida
 */
@Entity
@Table(name = "mohon_lot_spdn")
@SequenceGenerator(name = "seq_mohon_lot_spdn", sequenceName = "seq_mohon_lot_spdn")
public class MohonLotSepadan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_lot_spdn")
    @Column(name = "id_mohon_lot_spdn")
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_mh")
    private HakmilikPermohonan hakmilikPermohonan;

    @ManyToOne
    @JoinColumn(name = "id_dok")
    private Dokumen dokumen;

    @Column(name = "jenis_spdn")
    private String jenisSempadan;

    @Column(name = "milik_kjaan")
    private String milikKerajaan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kod_lot")
    private KodLot kodLot;

    @Column(name = "no_lot")
    private String noLot;

    @Column(name = "catatan_img")
    private String catatanImg;

    @Column(name = "catatan_ats_tnh")
    private String catatanAtsTnh;

    private InfoAudit infoAudit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public String getJenisSempadan() {
        return jenisSempadan;
    }

    public void setJenisSempadan(String jenisSempadan) {
        this.jenisSempadan = jenisSempadan;
    }

    public String getMilikKerajaan() {
        return milikKerajaan;
    }

    public void setMilikKerajaan(String milikKerajaan) {
        this.milikKerajaan = milikKerajaan;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getCatatanImg() {
        return catatanImg;
    }

    public void setCatatanImg(String catatanImg) {
        this.catatanImg = catatanImg;
    }

    public String getCatatanAtsTnh() {
        return catatanAtsTnh;
    }

    public void setCatatanAtsTnh(String catatanAtsTnh) {
        this.catatanAtsTnh = catatanAtsTnh;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

}
