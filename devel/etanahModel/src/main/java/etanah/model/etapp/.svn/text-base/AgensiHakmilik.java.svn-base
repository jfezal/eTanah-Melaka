/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author faidzal
 */
@Entity
@Table(name = "AGENSI_HAKMILIK")
@SequenceGenerator(name = "seq_agensi_hakmilik", sequenceName = "seq_agensi_hakmilik")
public class AgensiHakmilik implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agensi_hakmilik")
    @Column(name = "ID_AGENSI_HAKMILIK")
    private long idAgensiHakmilik;
    @ManyToOne
    @JoinColumn(name = "ID_HAKMILIK", nullable = false)
    private Hakmilik hakmilik;
    @Column(name = "NAMA_AGENSI")
    private String namaAgensi;
    @Column(name = "KEGUNAAN_TANAH")
    private String kegunaanTanah;
    @Column(name = "KOD_KEMENTERIAN")
    private String kodKementerian;
    @Column(name = "KOD_AGENSI")
    private String kodAgensi;
    @Column(name = "NAMA_KEMENTERIAN")
    private String namaKementerian;
    @Column(name = "AKTIF")
    private char aktif;
    @Embedded
    private InfoAudit infoAudit;

    public long getIdAgensiHakmilik() {
        return idAgensiHakmilik;
    }

    public void setIdAgensiHakmilik(long idAgensiHakmilik) {
        this.idAgensiHakmilik = idAgensiHakmilik;
    }
    
    



    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getKegunaanTanah() {
        return kegunaanTanah;
    }

    public void setKegunaanTanah(String kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public String getKodKementerian() {
        return kodKementerian;
    }

    public void setKodKementerian(String kodKementerian) {
        this.kodKementerian = kodKementerian;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public char getAktif() {
        return aktif;
    }

    public void setAktif(char aktif) {
        this.aktif = aktif;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public String getNamaKementerian() {
        return namaKementerian;
    }

    public void setNamaKementerian(String namaKementerian) {
        this.namaKementerian = namaKementerian;
    }
    
    
}
