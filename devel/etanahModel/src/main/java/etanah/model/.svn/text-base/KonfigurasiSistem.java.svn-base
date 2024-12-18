package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "sis_konfig")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class KonfigurasiSistem implements Serializable {
    
    @Id
    @Column (name = "nama")
    private String nama;


    @Column (name = "nilai")
    private String nilai;

    @Column (name = "keterangan")
    private String keterangan;
    
    @Column (name = "aktif")
    private char aktif;
    
     @Column (name = "kump")
    private String kumpulan;
    
    
    @Embedded
    InfoAudit infoAudit;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }


    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setAktif(char aktif) {
        this.aktif =aktif;
    }

    public char isAktif() {
        return aktif;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public String getKumpulan() {
        return kumpulan;
    }

    public void setKumpulan(String kumpulan) {
        this.kumpulan = kumpulan;
    }


    
    
}
