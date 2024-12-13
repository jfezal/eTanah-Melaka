package etanah.model.etapp;

import etanah.model.InfoAudit;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "TBLPPTENDORSAN")
@SequenceGenerator(name = "SEQ_TBLPPTENDORSAN", sequenceName = "SEQ_TBLPPTENDORSAN")
public class TBLPPTENDORSAN implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLPPTENDORSAN")
    @Column(name = "ID_TBLENDRSN")
    private Long idTblEndrsn;
     @Column(name = "ID_HAKMILIK")
    private String idHakmilik;
    
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    
    @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    
    @Column(name = "KOD_UNIT_HAKMILIK")
    private String kodUnitHakmilik;
    
    @Column(name = "NO_HAKMILIK")
    private String noHakmilik;
    
    
    @Column(name = "KOD_NEGERI")
    private String kodNegeri;
    
    
    @Column(name = "KOD_DAERAH")
    private String kodDaerah;
    
    
    @Column(name = "KOD_MUKIM")
    private String kodMukim;
    
   
    @Column(name = "TARIKH_ENDORSAN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhEndorsan;
    
    
    @Column(name = "NO_JILID")
    private String noJilid;
        
    @Column(name = "TURUTAN")
    private String turut;    
    

     @Embedded
    private InfoAudit infoAudit;
//    @OneToMany (mappedBy = "etappPerintah", fetch = FetchType.LAZY)
//    private List<EtappHakmilik> listEtappHakmilik;
//    
//    

    public Long getIdTblEndrsn() {
        return idTblEndrsn;
    }

    public void setIdTblEndrsn(Long idTblEndrsn) {
        this.idTblEndrsn = idTblEndrsn;
    }


    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getFlagProses() {
        return flagProses;
    }

    public void setFlagProses(String flagProses) {
        this.flagProses = flagProses;
    }

    public String getNoFailJKPTG() {
        return noFailJKPTG;
    }

    public void setNoFailJKPTG(String noFailJKPTG) {
        this.noFailJKPTG = noFailJKPTG;
    }

    public String getKodUnitHakmilik() {
        return kodUnitHakmilik;
    }

    public void setKodUnitHakmilik(String kodUnitHakmilik) {
        this.kodUnitHakmilik = kodUnitHakmilik;
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

    public Date getTarikhEndorsan() {
        return tarikhEndorsan;
    }

    public void setTarikhEndorsan(Date tarikhEndorsan) {
        this.tarikhEndorsan = tarikhEndorsan;
    }


    public String getNoJilid() {
        return noJilid;
    }

    public void setNoJilid(String noJilid) {
        this.noJilid = noJilid;
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
