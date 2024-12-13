package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "senarai_kod_ruj")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class SenaraiKodRujukan implements Serializable{
    
    @Id
    @Column (name = "nama_jadual")
    private String nama_jadual;

    @Column (name = "nama" )
    private String nama;
    
    @Column (name = "uri" )
    private String uri;

    @Column (name = "keterangan" )
    private String keterangan;


    @Column (name = "AKTIF", length = 1)
    private char aktif;
    
    @Embedded
    private InfoAudit infoAudit;

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNama_jadual() {
        return nama_jadual;
    }

    public void setNama_jadual(String nama_jadual) {
        this.nama_jadual = nama_jadual;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


 


    public void setAktif(char aktif) {
        this.aktif = aktif;
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
}
