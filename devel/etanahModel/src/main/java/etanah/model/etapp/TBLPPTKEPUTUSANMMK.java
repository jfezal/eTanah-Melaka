package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TBLPPTKEPUTUSANMMK")
@SequenceGenerator(name = "SEQ_TBLPPTKEPUTUSANMMK", sequenceName = "SEQ_TBLPPTKEPUTUSANMMK")
public class TBLPPTKEPUTUSANMMK implements Serializable {


   @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLPPTKEPUTUSANMMK")
   @Column(name = "ID_TBLKPSNMMK")
    private Long idTblKpsn;
   
   @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    
    @Column(name = "FLAG_PROSES")
    private String flagProses;
    
    @Column(name = "FLAG_KEPUTUSAN_MMK")
    private String flagKeputusanMMK;
    
    @Column(name = "KETERANGAN_KEPUTUSAN_MMK")
    private String keteranganKeputusanMMK;
    
     
    @Column(name = "TURUTAN")
    private String turut;
    
     @Embedded
    private InfoAudit infoAudit;

    public Long getIdTblKpsn() {
        return idTblKpsn;
    }

    public void setIdTblKpsn(Long idTblKpsn) {
        this.idTblKpsn = idTblKpsn;
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

    public String getFlagKeputusanMMK() {
        return flagKeputusanMMK;
    }

    public void setFlagKeputusanMMK(String flagKeputusanMMK) {
        this.flagKeputusanMMK = flagKeputusanMMK;
    }

    public String getKeteranganKeputusanMMK() {
        return keteranganKeputusanMMK;
    }

    public void setKeteranganKeputusanMMK(String keteranganKeputusanMMK) {
        this.keteranganKeputusanMMK = keteranganKeputusanMMK;
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
