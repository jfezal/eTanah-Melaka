/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pguna_app_log")
@SequenceGenerator (name = "seq_pguna_app_log", sequenceName = "seq_pguna_app_log")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class LogPenggunaApplikasi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "seq_pguna_app_log")
    @Column(name = "id_pguna_app_log")
    private long idPgunaAppLog;

    @Column(name = "id_pguna")
    private String idPguna;

    @Column(name = "trh_masuk")
    private Date tarikhMasuk;
     

    @Column(name = "url")
    private String url;
    
    private String param;

    public LogPenggunaApplikasi() {
    }

    public String getIdPguna() {
        return idPguna;
    }

    public void setIdPguna(String idPguna) {
        this.idPguna = idPguna;
    }

    public long getIdPgunaAppLog() {
        return idPgunaAppLog;
    }

    public void setIdPgunaAppLog(long idPgunaAppLog) {
        this.idPgunaAppLog = idPgunaAppLog;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameter() {
        return param;
    }

    public void setParameter(String parameter) {
        this.param = parameter;
    }

}
