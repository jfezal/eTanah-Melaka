/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import java.io.Serializable;
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
 * @author mohd.faidzal
 */
@Entity
@Table(name = "ISPEKS_BIL_DETAIL")
@SequenceGenerator(name = "SEQ_ISPEKS_BIL_DETAIL", sequenceName = "SEQ_ISPEKS_BIL_DETAIL")
public class BilDetail implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ISPEKS_BIL_DETAIL")
    @Column(name = "ID")
    private Long id;
   @ManyToOne
    @JoinColumn(name = "ID_BIL")
    private Bil bil;
     @Column(name = "KOD_TRANS")
    private String kodTrans;
     @Column(name = "AMAUN")
    private Long amaun;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public String getKodTrans() {
        return kodTrans;
    }

    public void setKodTrans(String kodTrans) {
        this.kodTrans = kodTrans;
    }

    public Long getAmaun() {
        return amaun;
    }

    public void setAmaun(Long amaun) {
        this.amaun = amaun;
    }


}