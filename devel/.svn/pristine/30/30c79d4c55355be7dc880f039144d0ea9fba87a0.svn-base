package etanah.model.gis;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table (name = "gis_pelan")
//@org.hibernate.annotations.Immutable
public class GISPelan implements Serializable{

    @Id
    private GISPelanPK pelanGISPK;

    @Column(name = "pelan_tif", length = 30)
    private String pelanTif;



    @Column(name = "NO_PT")
    private String noPt;
    
    @Column(name = "NO_PA")
    private String noPelanAkui;
    
    @Column(name = "unit")
    private String unitUkur;
    
    @Column(name = "luas")
    private BigDecimal luas;
    @Column(name = "no_fail_ukur")
    private String noFailUkur;
    
    @Column(name = "TKH_KEMASKINI")
    private Date trhKemaskini;

    public String getNoFailUkur() {
        return noFailUkur;
    }

    public void setNoFailUkur(String noFailUkur) {
        this.noFailUkur = noFailUkur;
    }

    public Date getTrhKemaskini() {
        return trhKemaskini;
    }

    public void setTrhKemaskini(Date trhKemaskini) {
        this.trhKemaskini = trhKemaskini;
    }
 
    
    
    public String getPelanTif() {
        return pelanTif;
    }

    public void setPelanTif(String pelanTif) {
        this.pelanTif = pelanTif;
    }


    public String getNoPt() {
        return noPt;
    }

    public void setNoPt(String noPt) {
        this.noPt = noPt;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public String getNoPelanAkui() {
        return noPelanAkui;
    }

    public void setNoPelanAkui(String noPelanAkui) {
        this.noPelanAkui = noPelanAkui;
    }

    public String getUnitUkur() {
        return unitUkur;
    }

    public void setUnitUkur(String unitUkur) {
        this.unitUkur = unitUkur;
    }

    public GISPelanPK getPelanGISPK() {
        return pelanGISPK;
    }

    public void setPelanGISPK(GISPelanPK pelanGISPK) {
        this.pelanGISPK = pelanGISPK;
    }


}
