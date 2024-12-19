/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.etapp.ws.form;

/**
 *
 * @author mohd.faidzal
 */
public class DokumenForm {
       protected String jenisDokumen;
    protected String jenisMime;
    protected String namaDokumen;
    protected String doContent;

    public String getJenisDokumen() {
        return jenisDokumen;
    }

    public void setJenisDokumen(String jenisDokumen) {
        this.jenisDokumen = jenisDokumen;
    }

    public String getJenisMime() {
        return jenisMime;
    }

    public void setJenisMime(String jenisMime) {
        this.jenisMime = jenisMime;
    }

    public String getNamaDokumen() {
        return namaDokumen;
    }

    public void setNamaDokumen(String namaDokumen) {
        this.namaDokumen = namaDokumen;
    }

    public String getDoContent() {
        return doContent;
    }

    public void setDoContent(String doContent) {
        this.doContent = doContent;
    }
    
    
}
