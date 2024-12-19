/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import java.math.BigDecimal;

/**
 *
 * @author mohd.faidzal
 */
public class TugasanIspeksForm {
    String id;
    String url;
    String peringkat;
    String kodPeringkat;
    String urusan;
    String jenisBayar;
    String tarikh;
    BigDecimal amaun;

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(String peringkat) {
        this.peringkat = peringkat;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getJenisBayar() {
        return jenisBayar;
    }

    public void setJenisBayar(String jenisBayar) {
        this.jenisBayar = jenisBayar;
    }

    public String getKodPeringkat() {
        return kodPeringkat;
    }

    public void setKodPeringkat(String kodPeringkat) {
        this.kodPeringkat = kodPeringkat;
    }
    
    
    
    
}
