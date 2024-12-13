package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TBLPPTINTDERAFMMKTAJUK")
@SequenceGenerator(name = "SEQ_TBLPPTINTDERAFMMKTAJUK", sequenceName = "SEQ_TBLPPTINTDERAFMMKTAJUK")
public class TBLPPTINTDERAFMMKTAJUK implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLPPTINTDERAFMMKTAJUK")
    @Column(name = "ID_TBLTAJUK")
    private Long idTblTajuk;
    @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    
    @Column(name = "KETERANGAN_SIDANG")
    private String keteranganSidang;
    
    @Column(name = "WAKTU_SIDANG")
    private String WaktuSidang;
    
    @Column(name = "JENIS_WAKTU_SIDANG")
    private String JenisWaktuSidang;
    
    @Column(name = "WAKTU_SIDANG_KETERANGAN")
    private String waktuSidang_keterangan;
    
    @Column(name = "TARIKH_SIDANG")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhSidang;
    
    @Column(name = "TEMPAT_SIDANG")
    private String tempatSidang;
        
    @Column(name = "TAJUK")
    private String tajuk;
    
    @Column(name = "TURUTAN")
    private String turut;
    
     @Embedded
    private InfoAudit infoAudit;

    public Long getIdTblTajuk() {
        return idTblTajuk;
    }

    public void setIdTblTajuk(Long idTblTajuk) {
        this.idTblTajuk = idTblTajuk;
    }



    
//    @OneToMany (mappedBy = "etappPerintah", fetch = FetchType.LAZY)
//    private List<EtappHakmilik> listEtappHakmilik;
//    
//    

    public String getNoFailJKPTG() {
        return noFailJKPTG;
    }

    public void setNoFailJKPTG(String noFailJKPTG) {
        this.noFailJKPTG = noFailJKPTG;
    }

    public String getFlagProses() {
        return flagProses;
    }

    public void setFlagProses(String flagProses) {
        this.flagProses = flagProses;
    }

    public String getKeteranganSidang() {
        return keteranganSidang;
    }

    public void setKeteranganSidang(String keteranganSidang) {
        this.keteranganSidang = keteranganSidang;
    }
    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public String getWaktuSidang() {
        return WaktuSidang;
    }

    public void setWaktuSidang(String WaktuSidang) {
        this.WaktuSidang = WaktuSidang;
    }

    public String getJenisWaktuSidang() {
        return JenisWaktuSidang;
    }

    public void setJenisWaktuSidang(String JenisWaktuSidang) {
        this.JenisWaktuSidang = JenisWaktuSidang;
    }

    public String getWaktuSidang_keterangan() {
        return waktuSidang_keterangan;
    }

    public void setWaktuSidang_keterangan(String waktuSidang_keterangan) {
        this.waktuSidang_keterangan = waktuSidang_keterangan;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getTempatSidang() {
        return tempatSidang;
    }

    public void setTempatSidang(String tempatSidang) {
        this.tempatSidang = tempatSidang;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTurut() {
        return turut;
    }

    public void setTurut(String turut) {
        this.turut = turut;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }



   


}
