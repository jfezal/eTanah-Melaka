package etanah.model.etapp;

import etanah.model.InfoAudit;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TBLINTPPTDERAFMMK")
@SequenceGenerator(name = "SEQ_TBLINTPPTDERAFMMK", sequenceName = "SEQ_TBLINTPPTDERAFMMK")
public class TBLINTPPTDERAFMMK implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLINTPPTDERAFMMK")
     @Column(name = "ID_TBLMMK")
    private Long idTblMMk;
    @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    
    @Column(name = "JENIS_MAKLUMAT_MMK")
    private String jenisMaklumatMMK;
    
    @Column(name = "KETERANGAN_MMK")
    private String keteranganMMK;
    
    @Column(name = "NO_ITEM_MMK")
    private String noItemMMK;
    
       @Column(name = "NO_MAIN_ITEM_MMK")
    private String noMainItemMMK;
      
    @Column(name = "TURUTAN")
    private String turut;
  @Embedded
    private InfoAudit infoAudit;

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

    public String getJenisMaklumatMMK() {
        return jenisMaklumatMMK;
    }

    public void setJenisMaklumatMMK(String jenisMaklumatMMK) {
        this.jenisMaklumatMMK = jenisMaklumatMMK;
    }

    public String getKeteranganMMK() {
        return keteranganMMK;
    }

    public void setKeteranganMMK(String keteranganMMK) {
        this.keteranganMMK = keteranganMMK;
    }

    public String getNoItemMMK() {
        return noItemMMK;
    }

    public void setNoItemMMK(String noItemMMK) {
        this.noItemMMK = noItemMMK;
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

    public Long getIdTblMMk() {
        return idTblMMk;
    }

    public void setIdTblMMk(Long idTblMMk) {
        this.idTblMMk = idTblMMk;
    }

    public String getNoMainItemMMK() {
        return noMainItemMMK;
    }

    public void setNoMainItemMMK(String noMainItemMMK) {
        this.noMainItemMMK = noMainItemMMK;
    }

  

        
    



   


}
