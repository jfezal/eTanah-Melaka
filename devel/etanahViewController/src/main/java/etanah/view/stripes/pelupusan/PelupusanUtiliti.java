//    Document   : DrafMMKNActionBean.java
//    Created on : May 04, 2010, 4:40:36 PM
//    Author     : murali
package etanah.view.stripes.pelupusan;

import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;

public class PelupusanUtiliti{
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private String agensi;
    private PermohonanRujukanLuar permohonanRujukanLuar;

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
    //Add by shazwan 8/7/2011 1210PM
    public String convertStringtoInitCap(String val){
        StringBuilder ff = new StringBuilder(); 
        //String inputString = "this is a sentence";

        for(String f: val.split(" ")) { 
        if(ff.length()>0) { 
            ff.append(" "); 
        } 
        if(!("").equals(f))
            ff.append(f.substring(0,1).toUpperCase()).append(f.substring(1,f.length()).toLowerCase()); 
        }
        String resultVal = ff.toString();
        return resultVal;
    }
    //Add by shazwan 8/7/2011 1210PM
    public String convertStringtoInitCapOnlyOnce(String val){
        StringBuilder ff = new StringBuilder(); 
        //String inputString = "this is a sentence";
        loop:
        for(String f: val.split(" ")) { 
        if(ff.length()>0) { 
            ff.append(" "); 
        } 
        if(!("").equals(f))
            ff.append(f.substring(0,1).toUpperCase()).append(f.substring(1,f.length()).toLowerCase()); 
            break loop;
        }
        String resultVal = ff.toString();
        return resultVal;
    }
}