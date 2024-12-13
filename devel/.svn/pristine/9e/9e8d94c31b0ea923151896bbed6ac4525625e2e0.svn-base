/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ambil;

import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUOM;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "mohon_ambil_hakmilik")
@SequenceGenerator(name = "seq_mohon_ambil_hakmilik", sequenceName = "seq_mohon_ambil_hakmilik")
public class PermohonanPengambilanHakmilik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_ambil_hakmilik")
    @Column(name = "id_ah")
    private long idAh;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mh", nullable = false)
    private HakmilikPermohonan hakmilikPermohonan;
    
    @Column(name = "luas_diambil", nullable = true)
    private BigDecimal luasAmbil;

    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;
    
     @Column(name = "JUM_KELUARAN_HM_BARU", nullable = true)
    private Integer jumlahHakmilik;
     
      @Column(name = "FLAG_AMBIL_SEBAHAGIAN", length = 2)
    private String flagAmbilSbgn;
    @Column(name = "FLAG_BEZA_LUAS", length = 2)
    private String flagBezaLuas;
    @Embedded
	private InfoAudit infoAudit;

    public long getIdAh() {
        return idAh;
    }

    public void setIdAh(long idAh) {
        this.idAh = idAh;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public BigDecimal getLuasAmbil() {
        return luasAmbil;
    }

    public void setLuasAmbil(BigDecimal luasAmbil) {
        this.luasAmbil = luasAmbil;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public Integer getJumlahHakmilik() {
        return jumlahHakmilik;
    }

    public void setJumlahHakmilik(Integer jumlahHakmilik) {
        this.jumlahHakmilik = jumlahHakmilik;
    }

    public String getFlagAmbilSbgn() {
        return flagAmbilSbgn;
    }

    public void setFlagAmbilSbgn(String flagAmbilSbgn) {
        this.flagAmbilSbgn = flagAmbilSbgn;
    }

    public String getFlagBezaLuas() {
        return flagBezaLuas;
    }

    public void setFlagBezaLuas(String flagBezaLuas) {
        this.flagBezaLuas = flagBezaLuas;
    }
    
    

}
