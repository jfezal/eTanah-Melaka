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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table(name = "ISPEKS_RESIT_PP")
@SequenceGenerator(name = "seq_ispeks_resit_pp", sequenceName = "seq_ispeks_resit_pp")
public class PenyataResit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_ispeks_resit_pp")
    @Column(name = "ID")
    private Long id;
    @Column(name = "ID_KEW_DOK", nullable = false)
    private String idKewDok;
    @Column(name = "ID_PENYATA")
    private Long  idPenyata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdKewDok() {
        return idKewDok;
    }

    public void setIdKewDok(String idKewDok) {
        this.idKewDok = idKewDok;
    }


    public Long getIdPenyata() {
        return idPenyata;
    }

    public void setIdPenyata(Long idPenyata) {
        this.idPenyata = idPenyata;
    }


    
}
