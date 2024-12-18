/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author mohd.faidzal
 */
@Entity
@Immutable
@Table(name = "V_IDD_JOURNAL_CONTENT")
public class IddJurnalContent implements Serializable{
        @Column(name = "NO_JOURNAL")
    private String noJournal;
     @Id
        @Column(name = "IDD")
    private String idd;

    public String getNoJournal() {
        return noJournal;
    }

    public void setNoJournal(String noJournal) {
        this.noJournal = noJournal;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }
     
     
}
