/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/SyaratSuratKelulusanTanah")
public class SyaratSuratKelulusanTanahActionBean extends AbleActionBean {

    private String suratAkuan;
    private String tempohPermit;
    private int kuantiti;
    private int royalti;
    private int deposit;
    private String syaratkuatkuasa;
    private String syaratTambahan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/syarat_kelulusan_pasir_sungai.jsp").addParameter("tab", "true");

        }

    public Resolution showForm1() {
        return new JSP("pelupusan/syarat_kelulusan_bbrumput.jsp").addParameter("tab", "true");

        }
      
    public int getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public int getRoyalti() {
        return royalti;
    }

    public void setRoyalti(int royalti) {
        this.royalti = royalti;
    }

    public String getSuratAkuan() {
        return suratAkuan;
    }

    public void setSuratAkuan(String suratAkuan) {
        this.suratAkuan = suratAkuan;
    }

    public String getTempohPermit() {
        return tempohPermit;
    }

    public void setTempohPermit(String tempohPermit) {
        this.tempohPermit = tempohPermit;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getSyaratkuatkuasa() {
        return syaratkuatkuasa;
    }

    public void setSyaratkuatkuasa(String syaratkuatkuasa) {
        this.syaratkuatkuasa = syaratkuatkuasa;
    }

    public String getSyaratTambahan() {
        return syaratTambahan;
    }

    public void setSyaratTambahan(String syaratTambahan) {
        this.syaratTambahan = syaratTambahan;
    }
    

}
