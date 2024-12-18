package etanah.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "mohon_rayuan")
@org.hibernate.annotations.Entity(selectBeforeUpdate = true,
dynamicInsert = true, dynamicUpdate = true)
@SequenceGenerator(name = "seq_mohon_rayuan", sequenceName = "seq_mohon_rayuan")
public class PermohonanRayuan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mohon_rayuan")
    @Column(name = "id_rayuan")
    private long idRayuan;
      
    @Column (name = "jenis_rayuan")
    private String jenisRayuan;
    
    @Column (name = "NoFail_Ruj_PTD")
    private String noRujFilePTD;
    
    @Column (name = "NoFail_Ruj_PTG")
    private String noRujFilePTG;
    
    @Column (name = "bil_Rayuan")
    private int bilRayuan;
      
    @ManyToOne
    @JoinColumn (name = "id_mohon")
    private Permohonan permohonan;
    
    @Column(name = "catatan", length = 1024, nullable = true)
    private String catatan;

     @Embedded
    private InfoAudit infoAudit;

    public long getIdRayuan() {
        return idRayuan;
    }

    public void setIdRayuan(long idRayuan) {
        this.idRayuan = idRayuan;
    }


    public String getJenisRayuan() {
        return jenisRayuan;
    }

    public void setJenisRayuan(String jenisRayuan) {
        this.jenisRayuan = jenisRayuan;
    }

    public String getNoRujFilePTD() {
        return noRujFilePTD;
    }

    public void setNoRujFilePTD(String noRujFilePTD) {
        this.noRujFilePTD = noRujFilePTD;
    }

    public String getNoRujFilePTG() {
        return noRujFilePTG;
    }

    public void setNoRujFilePTG(String noRujFilePTG) {
        this.noRujFilePTG = noRujFilePTG;
    }

    public int getBilRayuan() {
        return bilRayuan;
    }

    public void setBilRayuan(int bilRayuan) {
        this.bilRayuan = bilRayuan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public InfoAudit getInfoAudit() {
        return infoAudit;
    }

    public void setInfoAudit(InfoAudit infoAudit) {
        this.infoAudit = infoAudit;
    }
     
     
     
    
     
    
}

