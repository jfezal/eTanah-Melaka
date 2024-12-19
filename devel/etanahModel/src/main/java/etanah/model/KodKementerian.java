package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_kementerian")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKementerian implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "KOD")
    private Integer kod;

    @Column(name = "NAMA")
    private String nama;

    @Column(name = "ALAMAT1")
    private String alamat1;

    @Column(name = "ALAMAT2")
    private String alamat2;

    @Column(name = "ALAMAT3")
    private String alamat3;

    @Column(name = "ALAMAT4")
    private String alamat4;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "KOD_NEGERI")
    private KodNegeri kodNegeri;

    @Column(name = "POSKOD")
    private String poskod;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;

    
    @Embedded
    private InfoAudit infoAudit;

    public Integer getKod() {
        return kod;
    }

    public void setKod(Integer kod) {
        this.kod = kod;
    }


    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
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

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }


	
}
