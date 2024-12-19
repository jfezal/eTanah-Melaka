package etanah.model.etapp;

import etanah.model.InfoAudit;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "TBLINTPPTHAKMILIK")
@SequenceGenerator(name = "SEQ_TBLINTPPTHAKMILIK", sequenceName = "SEQ_TBLINTPPTHAKMILIK")
public class TBLINTPPTHAKMILIK implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLINTPPTHAKMILIK")
    @Column(name = "ID_TBLHMILIK")
    private Long idTblHMilik;
    @Column(name = "ID_HAKMILIK")
    private String idHakmilik;
    
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    
    @Column(name = "LUAS_ASAL")
    private String luas;
    
    @Column(name = "KOD_LUAS_ASAL")
    private String kod_luas;
    
    @Column(name = "LUAS_AMBIL")
    private String luasAmbil;
    
    @Column(name = "KOD_LUAS_AMBIL")
    private String kodLuasAmbil;
    
    @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    
    @Column(name = "NO_LOT")
    private String noLot;
        
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
    
    @Column(name = "TARIKH_BORANGK")
    @Temporal(javax.persistence.TemporalType.DATE)
	private Date tarikhBorangK;
    
    @Column(name = "STATUS_BORANGK")
    private String statusBorangK;
    
    
    @Column(name = "NO_LOT_BARU")
    private String noLotBaru;
    
    
    @Column(name = "NO_SYIT")
    private String noSyit;
    
    
    @Column(name = "NO_PA")
    private String noPA;
 
    @Column(name = "NO_PU")
    private String noPU;
    
    @Column(name = "LUAS_PA")
    private String luasPA;
    
    
    @Column(name = "KOD_LUAS_PA")
    private String kodLuasPA;
    
    
    @Column(name = "TURUTAN")
    private String turut;
    
    
 @Embedded
    private InfoAudit infoAudit;

    public Long getIdTblHMilik() {
        return idTblHMilik;
    }

    public void setIdTblHMilik(Long idTblHMilik) {
        this.idTblHMilik = idTblHMilik;
    }

   
    
//    @OneToMany (mappedBy = "etappPerintah", fetch = FetchType.LAZY)
//    private List<EtappHakmilik> listEtappHakmilik;
//    
//    

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




    public String getKod_luas() {
        return kod_luas;
    }

    public void setKod_luas(String kod_luas) {
        this.kod_luas = kod_luas;
    }

    public String getKodLuasAmbil() {
        return kodLuasAmbil;
    }

    public void setKodLuasAmbil(String kodLuasAmbil) {
        this.kodLuasAmbil = kodLuasAmbil;
    }

    public String getNoFailJKPTG() {
        return noFailJKPTG;
    }

    public void setNoFailJKPTG(String noFailJKPTG) {
        this.noFailJKPTG = noFailJKPTG;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
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

    

    public String getStatusBorangK() {
        return statusBorangK;
    }

    public void setStatusBorangK(String statusBorangK) {
        this.statusBorangK = statusBorangK;
    }

    public String getNoLotBaru() {
        return noLotBaru;
    }

    public void setNoLotBaru(String noLotBaru) {
        this.noLotBaru = noLotBaru;
    }

    public String getNoSyit() {
        return noSyit;
    }

    public void setNoSyit(String noSyit) {
        this.noSyit = noSyit;
    }

    public String getNoPA() {
        return noPA;
    }

    public void setNoPA(String noPA) {
        this.noPA = noPA;
    }

    public String getNoPU() {
        return noPU;
    }

    public void setNoPU(String noPU) {
        this.noPU = noPU;
    }

    public Date getTarikhBorangK() {
        return tarikhBorangK;
    }

    public void setTarikhBorangK(Date tarikhBorangK) {
        this.tarikhBorangK = tarikhBorangK;
    }

    public String getKodLuasPA() {
        return kodLuasPA;
    }

    public void setKodLuasPA(String kodLuasPA) {
        this.kodLuasPA = kodLuasPA;
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

    public String getLuas() {
        return luas;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public String getLuasAmbil() {
        return luasAmbil;
    }

    public void setLuasAmbil(String luasAmbil) {
        this.luasAmbil = luasAmbil;
    }

    public String getLuasPA() {
        return luasPA;
    }

    public void setLuasPA(String luasPA) {
        this.luasPA = luasPA;
    }
        
    



   


}
