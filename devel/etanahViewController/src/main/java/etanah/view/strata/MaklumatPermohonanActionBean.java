/* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;


import able.stripes.JSP;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author User
 */

@UrlBinding("/strata/maklumat_permohonan1")
public class MaklumatPermohonanActionBean extends BasicTabActionBean {

    private String nama;
    private String jenisPengenalan;
    private String nomborPengenalan;
    private String alamat;
    private String poskod;
    private String negeri;

    @DefaultHandler
    public Resolution showForm() {
        
        return new JSP("strata/Ruang_Udara/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(String jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }


}

