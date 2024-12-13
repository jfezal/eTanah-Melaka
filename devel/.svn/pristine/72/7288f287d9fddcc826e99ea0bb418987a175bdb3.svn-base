/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.ispek;

/**
 *
 * @author mohd.faidzal
 */
import etanah.model.KodBank;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ISPEKS_BANK_AKAUN")
public class AkaunBank implements Serializable {

    @Id
    @Column(name = "KOD")
    private String kod;
    @Column(name = "NAMA_BANK")
    private String namaBank;
    @Column(name = "NO_AKAUN")
    private String noAkaun;
    @Column(name = "AKTIF")
    private String aktif;
    @ManyToOne
    @JoinColumn(name = "KOD_BANK")
    KodBank kodBank;
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getAktif() {
        return aktif;
    }

    public void setAktif(String aktif) {
        this.aktif = aktif;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

}
