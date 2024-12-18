/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author john
 */
@Entity
@Table(name = "kod_peringkat_peranan")
@SequenceGenerator(name = "seq_kod_peringkat_peranan", sequenceName = "seq_kod_peringkat_peranan")
public class KodPeringkatPeranan implements Serializable {

    @Id
    @Column(name = "id_pp")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_kod_peringkat_peranan")
    private long idPp;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_peringkat")
    private KodPeringkat kodPeringkat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kod_peranan")
    private KodPeranan kodPeranan;

    public long getIdPp() {
        return idPp;
    }

    public void setIdPp(long idPp) {
        this.idPp = idPp;
    }

    public KodPeringkat getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(KodPeringkat kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }

    public KodPeranan getKodPeranan() {
        return kodPeranan;
    }

    public void setKodPeranan(KodPeranan kodPeranan) {
        this.kodPeranan = kodPeranan;
    }
    
    

}
