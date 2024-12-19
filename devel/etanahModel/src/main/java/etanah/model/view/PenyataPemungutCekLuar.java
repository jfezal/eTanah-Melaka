/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;

/**
 *
 * @author mohd.faidzal
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_PENYATA_PEMUNGUT_T")
public class PenyataPemungutCekLuar implements Serializable {

    @Id
    private PPPKTunai pKTunai;
    @Column(name = "cara_bayar")
    private String caraBayar;
    @Column(name = "amaun")
    private BigDecimal amaun;

    public PPPKTunai getpKTunai() {
        return pKTunai;
    }

    public void setpKTunai(PPPKTunai pKTunai) {
        this.pKTunai = pKTunai;
    }

    public String getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(String caraBayar) {
        this.caraBayar = caraBayar;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

}
