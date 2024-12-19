package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "kod_keadaan_tanah")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KodKeadaanTanah implements Serializable {
	   
    @Id
    @Column (name = "kod")
    private String kod;
    
    @Column (name = "nama")
    private String nama;
    
    @Column (name  = "aktif", length = 1)
    private char aktif;
    
    @Column (name = "jenis_keadaan")
    private String jenisKeadaan;
    
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

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }
 

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public String getJenisKeadaan() {
        return jenisKeadaan;
    }

    public void setJenisKeadaan(String jenisKeadaan) {
        this.jenisKeadaan = jenisKeadaan;
    }
    
    

}
