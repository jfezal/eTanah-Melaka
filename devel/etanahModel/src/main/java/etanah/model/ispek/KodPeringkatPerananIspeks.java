/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

import etanah.model.KodPeranan;
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
@Table(name = "ISPEKS_KOD_PERINGKAT_PERANAN")
@SequenceGenerator(name = "I_KOD_PERINGKAT_PERANAN", sequenceName = "I_KOD_PERINGKAT_PERANAN")
public class KodPeringkatPerananIspeks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "I_KOD_PERINGKAT_PERANAN")
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "KOD_PERINGKAT")
    KodPeringkatIspeks kodPeringkat;
    @ManyToOne
    @JoinColumn(name = "KOD_PERANAN")
    KodPeranan kodPeranan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KodPeringkatIspeks getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(KodPeringkatIspeks kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }

    public KodPeranan getKodPeranan() {
        return kodPeranan;
    }

    public void setKodPeranan(KodPeranan kodPeranan) {
        this.kodPeranan = kodPeranan;
    }

}
