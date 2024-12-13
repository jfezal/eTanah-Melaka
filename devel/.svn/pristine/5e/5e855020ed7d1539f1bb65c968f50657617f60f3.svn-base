package etanah.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table (name = "mohon_fasa_gis")
@org.hibernate.annotations.Cache (usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class PermohonanFasaGis implements Serializable{
    
    @Id
    @Column (name = "id_mohon")
    private String id_mohon;

    @Column (name = "id_aliran" )
    private String idAliran;


    @Embedded
    private InfoAudit infoAudit;

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public String getId_mohon() {
        return id_mohon;
    }

    public void setId_mohon(String id_mohon) {
        this.id_mohon = id_mohon;
    }



 


    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }
}
