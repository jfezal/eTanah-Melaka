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
public class InfoAuditBaru implements Serializable{
    
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "dikkini_fix")
    private Pengguna dikemaskiniOlehBaru;

    @Column (name = "trh_kkini_fix")
    private Date tarikhKemaskiniBaru;

    
    public Pengguna getDikemaskiniOlehBaru() {
        return dikemaskiniOlehBaru;
    }

    public void setDikemaskiniOlehBaru(Pengguna dikemaskiniOlehBaru) {
        this.dikemaskiniOlehBaru = dikemaskiniOlehBaru;
    }

    public Date getTarikhKemaskiniBaru() {
        return tarikhKemaskiniBaru;
    }

    public void setTarikhKemaskiniBaru(Date tarikhKemaskiniBaru) {
        this.tarikhKemaskiniBaru = tarikhKemaskiniBaru;
    }

}
