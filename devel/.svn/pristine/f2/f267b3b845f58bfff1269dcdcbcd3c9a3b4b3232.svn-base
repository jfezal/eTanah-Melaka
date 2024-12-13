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
@Table(name = "HAKMILIK_ALAMAT")
@NamedQueries({@NamedQuery(name = "HakmilikAlamat.findAll", query = "SELECT h FROM HakmilikAlamat h")})
public class HakmilikAlamat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_HAKMILIK")
    private String idHakmilik;
    @Basic(optional = false)
    @Column(name = "NAMA_INST")
    private String namaInst;
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

    @Embedded
    private InfoAudit infoAudit;

    public HakmilikAlamat() {
    }

    public HakmilikAlamat(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikAlamat(String idHakmilik, String namaInst, String alamat1, String poskod) {
        this.idHakmilik = idHakmilik;
        this.namaInst = namaInst;
        this.alamat1 = alamat1;
        this.poskod = poskod;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNamaInst() {
        return namaInst;
    }

    public void setNamaInst(String namaInst) {
        this.namaInst = namaInst;
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

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHakmilik != null ? idHakmilik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HakmilikAlamat)) {
            return false;
        }
        HakmilikAlamat other = (HakmilikAlamat) object;
        if ((this.idHakmilik == null && other.idHakmilik != null) || (this.idHakmilik != null && !this.idHakmilik.equals(other.idHakmilik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "etanah.dao.HakmilikAlamat[idHakmilik=" + idHakmilik + "]";
    }

}
