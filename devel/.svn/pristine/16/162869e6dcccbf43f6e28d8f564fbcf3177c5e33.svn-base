/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "akaun_strata")
public class AkaunStrata {

    @Column(name = "id_strata")
    @Id
    private String idAkaunStrata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hakmilik", nullable = true)
    private Hakmilik hakmilik;

    @Column(name = "created_by")
    private String dimasukOleh;

    @Column(name = "created_date")
    private Date tarikhKemaskini;

    public String getIdAkaunStrata() {
        return idAkaunStrata;
    }

    public void setIdAkaunStrata(String idAkaunStrata) {
        this.idAkaunStrata = idAkaunStrata;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getDimasukOleh() {
        return dimasukOleh;
    }

    public void setDimasukOleh(String dimasukOleh) {
        this.dimasukOleh = dimasukOleh;
    }

    public Date getTarikhKemaskini() {
        return tarikhKemaskini;
    }

    public void setTarikhKemaskini(Date tarikhKemaskini) {
        this.tarikhKemaskini = tarikhKemaskini;
    }

  
}
