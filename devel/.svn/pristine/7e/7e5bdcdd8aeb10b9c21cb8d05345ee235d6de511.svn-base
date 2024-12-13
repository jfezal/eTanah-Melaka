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
@Table(name = "V_IDD_BIL_HEADER")
public class IddBilHeader implements Serializable {

    @Id
    @Column(name = "ID_BIL")
    private Long idBil;
    @Column(name = "IDD")
    private String idd;

    public Long getIdBil() {
        return idBil;
    }

    public void setIdBil(Long idBil) {
        this.idBil = idBil;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }

}
