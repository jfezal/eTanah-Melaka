/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "mohon_hakmilik_b1")
@SequenceGenerator(name = "seq_mohon_hakmilik_b1", sequenceName = "seq_mohon_hakmilik_b1")
public class HakmilikPermohonanB1 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_mohon_hakmilik_b1")
    @Column(name = "id_mh_b1")
    private long idMhB1;
    @ManyToOne
    @JoinColumn(name = "id_mohon")
    private Permohonan permohonan;
    @ManyToOne
    @JoinColumn(name = "id_hakmilik")
    private Hakmilik hakmilik;
    @Column(name = "luas", nullable = true)
    private BigDecimal luasTerlibat;
    @Column(name = "no_lot", nullable = true)
    private String noLot;
    @Column(name = "no_pelan_akui", nullable = true)
    private String noPelanAkui;
    @ManyToOne
    @JoinColumn(name = "kod_uom")
    private KodUOM kodUnitLuas;

    @ManyToOne
    @JoinColumn(name = "kod_hakmilik")
    private KodHakmilik kodHakmilik;

    public long getIdMhB1() {
        return idMhB1;
    }

    public void setIdMhB1(long idMhB1) {
        this.idMhB1 = idMhB1;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public BigDecimal getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(BigDecimal luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoPelanAkui() {
        return noPelanAkui;
    }

    public void setNoPelanAkui(String noPelanAkui) {
        this.noPelanAkui = noPelanAkui;
    }

}
