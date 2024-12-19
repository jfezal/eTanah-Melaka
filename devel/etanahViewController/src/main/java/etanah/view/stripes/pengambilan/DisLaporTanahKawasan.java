package etanah.view.stripes.pengambilan;

import etanah.model.KodRizab;

/**
 *
 * @author Shazwan 05032012 1 AM
 * purpose : Separate LaporTanahKawasan
 * 
 */

public class DisLaporTanahKawasan  {
   private KodRizab kodRizab;
   private String stringRizab;
   private String noWarta;
   private String tarikhWarta;
   private String pelanWarta;
   private String catatan;

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public KodRizab getKodRizab() {
        return kodRizab;
    }

    public void setKodRizab(KodRizab kodRizab) {
        this.kodRizab = kodRizab;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getPelanWarta() {
        return pelanWarta;
    }

    public void setPelanWarta(String pelanWarta) {
        this.pelanWarta = pelanWarta;
    }

    public String getStringRizab() {
        return stringRizab;
    }

    public void setStringRizab(String stringRizab) {
        this.stringRizab = stringRizab;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }
   
   
}
