/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Izam
 */

// the AuditDataId is the composite id (idAudit & namaMedan)
@Entity
@Table(name = "audit_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AuditData implements Serializable {
    @Id
    private AuditDataId auditDataId;

    @Column(name = "primary", length = 2000)
    private String primaryKey;

    @Column(name = "masa")
    private Date masa;
    
    @Column(name = "nama_table", length = 30)
    private String namaTable;

    @Column(name = "data_lama", length = 4000)
    private String dataLama;

    @Column(name = "data_baru", length = 4000)
    private String dataBaru;

    @Column(name = "sessionid")
    private int idSesi;

    @Column(name = "os_user", length = 2000)
    private String penggunaOS;

    @Column(name = "username", length = 2000)
    private String username;

    @Column(name = "machine", length = 2000)
    private String machine;

    @Column(name = "nama_program", length = 2000)
    private String namaProgram;

    @Column(name = "aktiviti", length = 200)
    private String aktiviti;
    
    @Column(name = "pguna")
    private String pengguna;

    public AuditDataId getAuditDataId() {
        return auditDataId;
    }

    public void setAuditDataId(AuditDataId auditDataId) {
        this.auditDataId = auditDataId;
    }

    public String getAktiviti() {
        return aktiviti;
    }

    public void setAktiviti(String aktiviti) {
        this.aktiviti = aktiviti;
    }

    public String getDataBaru() {
        return dataBaru;
    }

    public void setDataBaru(String dataBaru) {
        this.dataBaru = dataBaru;
    }

    public String getDataLama() {
        return dataLama;
    }

    public void setDataLama(String dataLama) {
        this.dataLama = dataLama;
    }

    public int getIdSesi() {
        return idSesi;
    }

    public void setIdSesi(int idSesi) {
        this.idSesi = idSesi;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public Date getMasa() {
        return masa;
    }

    public void setMasa(Date masa) {
        this.masa = masa;
    }


    public String getNamaProgram() {
        return namaProgram;
    }

    public void setNamaProgram(String namaProgram) {
        this.namaProgram = namaProgram;
    }

    public String getNamaTable() {
        return namaTable;
    }

    public void setNamaTable(String namaTable) {
        this.namaTable = namaTable;
    }

    public String getPenggunaOS() {
        return penggunaOS;
    }

    public void setPenggunaOS(String penggunaOS) {
        this.penggunaOS = penggunaOS;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPengguna() {
        return pengguna;
    }

    public void setPengguna(String pengguna) {
        this.pengguna = pengguna;
    }

 
        
    
}
