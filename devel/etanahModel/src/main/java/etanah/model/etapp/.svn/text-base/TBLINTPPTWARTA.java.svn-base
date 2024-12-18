package etanah.model.etapp;

import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TBLINTPPTWARTA")
@SequenceGenerator(name = "SEQ_TBLINTPPTWARTA", sequenceName = "SEQ_TBLINTPPTWARTA")
public class TBLINTPPTWARTA implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TBLINTPPTWARTA")
    @Column(name = "ID_TBLWARTA")
    private Long idTblWarta;
    @Column(name = "NO_FAIL_JKPTG")
    private String noFailJKPTG;
    @Column(name = "NO_WARTA")
    private String noWarta;
    
    @Column(name = "TARIKH_WARTA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tarikhWarta;

     
    @Column(name = "TURUTAN")
    private String turut;
    
     @Embedded
    private InfoAudit infoAudit;

    public Long getIdTblWarta() {
        return idTblWarta;
    }

    public void setIdTblWarta(Long idTblWarta) {
        this.idTblWarta = idTblWarta;
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

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public Date getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(Date tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
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
