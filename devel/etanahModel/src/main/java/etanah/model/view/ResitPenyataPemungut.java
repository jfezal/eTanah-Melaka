/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Immutable
@Table(name = "V_RESIT_PENYATA_PEMUNGUT")
public class ResitPenyataPemungut {

    @Id
    @Column(name = "id_kew_dok")
    private String idKewDok;
    @Column(name = "trh_resit")
    private Date trhResit;
    @Column(name = "amaun")
    private BigDecimal amaun;
    @Column(name = "jns_bayar")
    private String jnsBayar;
    @Column(name = "kod_caw")
    private String kodCaw;
    @Column(name = "ID_PENGGUNA_KAUNTER")
    private String idPenggunaKaunter;
    @Column(name = "NO_KAUNTER")
    private String noKaunter;
@Column(name = "MOD_BAYARAN")
    private String modBayaran;
    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }

    public Date getTrhResit() {
        return trhResit;
    }

    public void setTrhResit(Date trhResit) {
        this.trhResit = trhResit;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getJnsBayar() {
        return jnsBayar;
    }

    public void setJnsBayar(String jnsBayar) {
        this.jnsBayar = jnsBayar;
    }

    public String getKodCaw() {
        return kodCaw;
    }

    public void setKodCaw(String kodCaw) {
        this.kodCaw = kodCaw;
    }

    public String getIdPenggunaKaunter() {
        return idPenggunaKaunter;
    }

    public void setIdPenggunaKaunter(String idPenggunaKaunter) {
        this.idPenggunaKaunter = idPenggunaKaunter;
    }

    public String getNoKaunter() {
        return noKaunter;
    }

    public void setNoKaunter(String noKaunter) {
        this.noKaunter = noKaunter;
    }

    public String getModBayaran() {
        return modBayaran;
    }

    public void setModBayaran(String modBayaran) {
        this.modBayaran = modBayaran;
    }

}
