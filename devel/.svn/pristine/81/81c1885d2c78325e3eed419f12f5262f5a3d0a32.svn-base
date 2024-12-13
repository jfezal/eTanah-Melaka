/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;

import java.io.Serializable;
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
@Table(name = "V_IDD_BIL_CONTENT")
public class IddBilContent implements Serializable {

    @Column(name = "NO_BIL")
    private String noBil;
    @Id
    @Column(name = "IDD")
    private String idd;

    public String getNoBil() {
        return noBil;
    }

    public void setNoBil(String noBil) {
        this.noBil = noBil;
    }


    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

}
