/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Table (name = "kod_vot_dana")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodVotDana implements Serializable{
     @Id
    @Column(name = "ID_VOT", length = 5)
    private String id;
    @Column(name = "PERIHAL")
    private String perihal;
    @Column(name = "JENIS_DANA")
    private String jenisDana;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getJenisDana() {
        return jenisDana;
    }

    public void setJenisDana(String jenisDana) {
        this.jenisDana = jenisDana;
    }
            
}
