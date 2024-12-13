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
@Table(name = "V_PENYATA_PEMUNGUT_C_DB")
public class PenyataPemungutCekBankDraft implements Serializable {

    @Id
    private PPPKCekBankDraft pPPKCekBankDraft;
    @Column(name = "cara_bayar")
    private String caraBayar;
    @Column(name = "amaun")
    private BigDecimal amaun;
           @Column (name = "cb_caw")
    private String cbCaw;

    public String getCbCaw() {
        return cbCaw;
    }

    public void setCbCaw(String cbCaw) {
        this.cbCaw = cbCaw;
    }

    public PPPKCekBankDraft getpPPKCekBankDraft() {
        return pPPKCekBankDraft;
    }

    public void setpPPKCekBankDraft(PPPKCekBankDraft pPPKCekBankDraft) {
        this.pPPKCekBankDraft = pPPKCekBankDraft;
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
