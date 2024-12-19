/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author abu.mansur
 */
@Entity
@Table(name = "kod_sekolah_bantuan")
public class KodSekolahBantuanModal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kod")
    private String kod;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "ALAMAT1")
    private String alamat1;
    @Column(name = "ALAMAT2")
    private String alamat2;
    @Column(name = "ALAMAT3")
    private String alamat3;
    @Column(name = "ALAMAT4")
    private String alamat4;
    @Basic(optional = false)
    @Column(name = "POSKOD")
    private String poskod;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_negeri")
    private KodNegeri alamatNegeri;
    @Column(name = "NO_TEL")
    private String noTel;
    @Column(name = "NO_FAKS")
    private String noFaks;

    @Embedded
    private InfoAudit infoAudit;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public KodNegeri getAlamatNegeri() {
        return alamatNegeri;
    }

    public void setAlamatNegeri(KodNegeri alamatNegeri) {
        this.alamatNegeri = alamatNegeri;
    }

    public String getNoTel() {
        return noTel;
    }

    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    public String getNoFaks() {
        return noFaks;
    }

    public void setNoFaks(String noFaks) {
        this.noFaks = noFaks;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
}
