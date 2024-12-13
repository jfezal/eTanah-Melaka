package etanah.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Maklumat audit yg terdapat dalam (hampir) semua table.
 */

@Embeddable
public class InfoAudit implements Serializable{
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "dimasuk", updatable = false)
    private Pengguna dimasukOleh;

    @Column (name = "trh_masuk", updatable = false)
    private Date tarikhMasuk;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "dikkini")
    private Pengguna dikemaskiniOleh;

    @Column (name = "trh_kkini")
    private Date tarikhKemaskini;

    public void setDimasukOleh(Pengguna dimasukOleh) {
        this.dimasukOleh = dimasukOleh;
    }

    public Pengguna getDimasukOleh() {
        return dimasukOleh;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setDiKemaskiniOleh(Pengguna diKemaskiniOleh) {
        this.dikemaskiniOleh = diKemaskiniOleh;
    }

    public Pengguna getDikemaskiniOleh() {
        return dikemaskiniOleh;
    }

    public void setTarikhKemaskini(Date tarikhKemaskini) {
        this.tarikhKemaskini = tarikhKemaskini;
    }

    public Date getTarikhKemaskini() {
        return tarikhKemaskini;
    }
}
