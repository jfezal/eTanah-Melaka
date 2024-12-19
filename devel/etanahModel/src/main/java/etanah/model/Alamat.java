package etanah.model;

import java.io.Serializable;

import javax.persistence.*;


@Embeddable
public class Alamat implements Serializable{
    public Alamat() {
        super();
    }
    
    @Column (name = "alamat1")    
    private String alamat1;
    
    @Column (name = "alamat2")
    private String alamat2;
    
    @Column (name = "alamat3")
    private String alamat3;
    
    @Column (name = "alamat4")
    private String alamat4;
    
    @Column (name = "poskod")
    private String poskod;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "kod_negeri")
    private KodNegeri negeri;


    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setNegeri(KodNegeri negeri) {
        this.negeri = negeri;
    }

    public KodNegeri getNegeri() {
        return negeri;
    }
}
