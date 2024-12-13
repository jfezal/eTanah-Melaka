package etanah.model;

import etanah.dao.KodJenisPengenalanDAO;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_jabatan")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodJabatan implements Serializable{
    
    @Id
    @Column (name = "kod", length = 8)
    private String kod;
    
    @Column (name = "nama", length = 256, nullable = false, unique = true)
    private String nama;

    @Column (name = "warna")
    private String warnaModul;
    
    @Column (name = "akronim", length = 3)
    private String akronim;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;
    
    @Embedded
    private InfoAudit infoAudit;

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
    
    public void setAkronim(String akronim){
    	this.akronim = akronim;
    }
    
    public String getAkronim(){
    	return akronim;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public char getAktif() {
        return aktif;
    }

    public String getWarnaModul() {
        return warnaModul;
    }

    public void setWarnaModul(String warnaModul) {
        this.warnaModul = warnaModul;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    
}
