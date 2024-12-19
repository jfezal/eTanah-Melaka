package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TBLINTPPTDOKUMEN")
@SequenceGenerator(name = "SEQ_TBLINTPPTDOKUMEN", sequenceName = "SEQ_TBLINTPPTDOKUMEN")
public class TBLINTPPTDOKUMEN implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLINTPPTDOKUMEN")
    @Column(name = "ID_TBLDOK")
    private Long idTblDok;
            @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    
    @Column(name = "NAMA_DOKUMEN")
    private String namaDokumen;
    
    @Column(name = "TAJUK_DOKUMEN")
    private String tajukDokumen;
    
    
    @Column(name = "CONTENT")
    private String Kandungan;
    
    @Column(name = "JENIS_FAIL")
    private String jenisFail;
    
    @Column(name = "JENIS_FORMAT_FAIL")
    private String jenisFormatFail;
        
    @Column(name = "ID_HAKMILIK")
    private String idHakmilik;
    
    @Column(name = "NO_HAKMILIK")
    private String noHakmilik;
    
    
    @Column(name = "KOD_NEGERI")
    private String kodNegeri;
    
    
    @Column(name = "KOD_DAERAH")
    private String kodDaerah;
    
    
    @Column(name = "KOD_MUKIM")
    private String kodMukim;
    
	
    @Column(name = "TURUTAN")
    private String turut;
    
    
  @Embedded
    private InfoAudit infoAudit;

    public Long getIdTblDok() {
        return idTblDok;
    }

    public void setIdTblDok(Long idTblDok) {
        this.idTblDok = idTblDok;
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

    public String getNamaDokumen() {
        return namaDokumen;
    }

    public void setNamaDokumen(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public String getTajukDokumen() {
        return tajukDokumen;
    }

    public void setTajukDokumen(String tajukDokumen) {
        this.tajukDokumen = tajukDokumen;
    }


    public String getKandungan() {
        return Kandungan;
    }

    public void setKandungan(String Kandungan) {
        this.Kandungan = Kandungan;
    }

    public String getJenisFail() {
        return jenisFail;
    }

    public void setJenisFail(String jenisFail) {
        this.jenisFail = jenisFail;
    }

    public String getJenisFormatFail() {
        return jenisFormatFail;
    }

    public void setJenisFormatFail(String jenisFormatFail) {
        this.jenisFormatFail = jenisFormatFail;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodMukim() {
        return kodMukim;
    }

    public void setKodMukim(String kodMukim) {
        this.kodMukim = kodMukim;
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
