package etanah.model;

import java.io.Serializable;

import javax.persistence.*;


@Embeddable
public class AlamatSurat implements Serializable{
    public AlamatSurat() {
        super();
    }

    @Column (name = "SURAT_ALAMAT1")    
    private String alamatSurat1;
    
    @Column (name = "SURAT_ALAMAT2")
    private String alamatSurat2;
    
    @Column (name = "SURAT_ALAMAT3")
    private String alamatSurat3;
    
    @Column (name = "SURAT_ALAMAT4")
    private String alamatSurat4;
    
    @Column (name = "SURAT_POSKOD")
    private String poskodSurat;
    
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "SURAT_KOD_NEGERI")
    private KodNegeri negeriSurat;

    public String getAlamatSurat1() {
        return alamatSurat1;
    }

    public void setAlamatSurat1(String alamatSurat1) {
        this.alamatSurat1 = alamatSurat1;
    }

    public String getAlamatSurat2() {
        return alamatSurat2;
    }

    public void setAlamatSurat2(String alamatSurat2) {
        this.alamatSurat2 = alamatSurat2;
    }

    public String getAlamatSurat3() {
        return alamatSurat3;
    }

    public void setAlamatSurat3(String alamatSurat3) {
        this.alamatSurat3 = alamatSurat3;
    }

    public String getAlamatSurat4() {
        return alamatSurat4;
    }

    public void setAlamatSurat4(String alamatSurat4) {
        this.alamatSurat4 = alamatSurat4;
    }

    public String getPoskodSurat() {
        return poskodSurat;
    }

    public void setPoskodSurat(String poskodSurat) {
        this.poskodSurat = poskodSurat;
    }

    public KodNegeri getNegeriSurat() {
        return negeriSurat;
    }

    public void setNegeriSurat(KodNegeri negeriSurat) {
        this.negeriSurat = negeriSurat;
    }
    
}
