/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "endors_b4k_cukai")
public class EndorsCukaiB4K implements Serializable {

    
    @Id
    @Column(name = "id_hakmilik")
    private String idHakmilik;
    
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Hakmilik hakmilik;

    @Column(name = "trh_daftar")
    private Date tarikhDaftar;

    @Column(name = "no_versi")
    private Integer noVersi;

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public Integer getNoVersi() {
        return noVersi;
    }

    public void setNoVersi(Integer noVersi) {
        this.noVersi = noVersi;
    }
    
    

}
