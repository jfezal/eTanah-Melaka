package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TBLPPTHMSAMBUNGAN")
@SequenceGenerator(name = "SEQ_TBLPPTHMSAMBUNGAN", sequenceName = "SEQ_TBLPPTHMSAMBUNGAN")
public class TBLPPTHMSAMBUNGAN implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLPPTHMSAMBUNGAN")
    @Column(name = "ID_TBLSMBGN")
    private Long idTblSmbng;
    @Column(name = "ID_HAKMILIK")
    private String idHakmilik;
    
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    
    @Column(name = "LUAS_ASAL")
    private Integer luas;
    
    @Column(name = "KOD_LUAS_ASAL")
    private String kod_luas;
    
    @Column(name = "LUAS_AMBIL")
    private Integer luasAmbil;
    
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
    
   
    @Column(name = "NO_LOT_BARU")
    private String noLotBaru;
    
    
    @Column(name = "NO_SYIT")
    private String noSyit;
    
    
    @Column(name = "NO_PA")
    private String noPA;
 
    @Column(name = "NO_PU")
    private String noPU;
    
    @Column(name = "LUAS_PA")
    private Integer luasPA;
    
    
    @Column(name = "KOD_LUAS_PA")
    private String kodLuasPA;
    
	@Column(name = "FLAG_HAKMILIK_DIDAFTARKAN")
    private String flagHakmilikDaftar;
	
    
    @Column(name = "TURUTAN")
    private String turut;    
    

     @Embedded
    private InfoAudit infoAudit;
//    @OneToMany (mappedBy = "etappPerintah", fetch = FetchType.LAZY)
//    private List<EtappHakmilik> listEtappHakmilik;
//    
//    

    public Long getIdTblSmbng() {
        return idTblSmbng;
    }

    public void setIdTblSmbng(Long idTblSmbng) {
        this.idTblSmbng = idTblSmbng;
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

    public Integer getLuas() {
        return luas;
    }

    public void setLuas(Integer luas) {
        this.luas = luas;
    }

    public String getKod_luas() {
        return kod_luas;
    }

    public void setKod_luas(String kod_luas) {
        this.kod_luas = kod_luas;
    }

    public Integer getLuasAmbil() {
        return luasAmbil;
    }

    public void setLuasAmbil(Integer luasAmbil) {
        this.luasAmbil = luasAmbil;
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

    public Integer getLuasPA() {
        return luasPA;
    }

    public void setLuasPA(Integer luasPA) {
        this.luasPA = luasPA;
    }

    public String getKodLuasPA() {
        return kodLuasPA;
    }

    public void setKodLuasPA(String kodLuasPA) {
        this.kodLuasPA = kodLuasPA;
    }

    public String getFlagHakmilikDaftar() {
        return flagHakmilikDaftar;
    }

    public void setFlagHakmilikDaftar(String flagHakmilikDaftar) {
        this.flagHakmilikDaftar = flagHakmilikDaftar;
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
