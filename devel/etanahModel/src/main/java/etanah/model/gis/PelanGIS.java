package etanah.model.gis;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table (name = "gis_pelan")
//@org.hibernate.annotations.Immutable
public class PelanGIS implements Serializable{

    @Id
    private PelanGISPK pelanGISPK;

    @Column(name = "pelan_tif", length = 30)
    private String pelanTif;

    @Column(name = "jenis_pelan", length = 2)
    private String jenisPelan;
    
    @Column(name = "NO_PT")
    private String noPt;
    
    @Column(name = "NO_PA")
    private String noPelanAkui;
    
    @Column(name = "unit")
    private String unitUkur;
    
    @Column(name = "luas")
    private BigDecimal luas;
    
    @Column (name = "TKH_KEMASKINI")
    private Date tarikhKemaskini;

    public String getPelanTif() {
        return pelanTif;
    }

    public void setPelanTif(String pelanTif) {
        this.pelanTif = pelanTif;
    }

    public String getJenisPelan() {
        return jenisPelan;
    }

    public void setJenisPelan(String jenisPelan) {
        this.jenisPelan = jenisPelan;
    }

    public PelanGISPK getPelanGISPK() {
        return pelanGISPK;
    }

    public void setPelanGISPK(PelanGISPK pelanGISPK) {
        this.pelanGISPK = pelanGISPK;
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

    public Date getTarikhKemaskini() {
        return tarikhKemaskini;
    }

    public void setTarikhKemaskini(Date tarikhKemaskini) {
        this.tarikhKemaskini = tarikhKemaskini;
    }
}
