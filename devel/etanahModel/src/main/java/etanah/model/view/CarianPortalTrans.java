/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.model.view;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "V_CARIAN_PORTAL_TRANS")
public class CarianPortalTrans implements Serializable {

    @Id
    @Column(name = "id_portal_trans")
    private String idPortalTrans;
    
    @Column(name = "id_carian")
    private String idCarian;
    
    @Column(name = "id_hakmilik")
    private String idHakmilik;
    
    @Column(name = "no_resit")
    private String noResit;
    
    @Column(name = "trh_resit")
    private Date trhResit;
    
    @Column(name = "id_dokumen")
    private String idDokumen;

    public String getIdPortalTrans() {
        return idPortalTrans;
    }

    public void setIdPortalTrans(String idPortalTrans) {
        this.idPortalTrans = idPortalTrans;
    }

    public String getIdCarian() {
        return idCarian;
    }

    public void setIdCarian(String idCarian) {
        this.idCarian = idCarian;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getNoResit() {
        return noResit;
    }

    public void setNoResit(String noResit) {
        this.noResit = noResit;
    }

    public Date getTrhResit() {
        return trhResit;
    }

    public void setTrhResit(Date trhResit) {
        this.trhResit = trhResit;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }
    
    
    
    
    
    

}
