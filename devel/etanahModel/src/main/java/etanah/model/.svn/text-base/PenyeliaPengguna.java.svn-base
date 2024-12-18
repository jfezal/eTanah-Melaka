/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "pguna_penyelia")
@SequenceGenerator (name = "seq_pguna_penyelia", sequenceName = "seq_pguna_penyelia")
public class PenyeliaPengguna implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pguna_penyelia")
    @Column(name = "id_pgguna_penyelia")
    private long idPenyeliaPengguna;
    
    @Column(name = "kod_pgguna")
    private Integer pgguna;
   
    @Column(name = "kod_penyelia")
    private Integer penyelia;
    
    @Column(name = "ptg")
    private char ptg;
   
    @Embedded
    private InfoAudit infoAudit;

    public long getIdPenyeliaPengguna() {
        return idPenyeliaPengguna;
    }

    public void setIdPenyeliaPengguna(long idPenyeliaPengguna) {
        this.idPenyeliaPengguna = idPenyeliaPengguna;
    }

    public Integer getPgguna() {
        return pgguna;
    }

    public void setPgguna(Integer pgguna) {
        this.pgguna = pgguna;
    }

    public Integer getPenyelia() {
        return penyelia;
    }

    public void setPenyelia(Integer penyelia) {
        this.penyelia = penyelia;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public char getPtg() {
        return ptg;
    }

    public void setPtg(char ptg) {
        this.ptg = ptg;
    }
    
    
}
