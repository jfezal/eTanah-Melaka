/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
//import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
/**
 *
 * @author sitifariza.hanim
 */
@UrlBinding("/pelupusan/draf_pertimbangan_mmk")
public class DrafPertimbanganMMKActionBean extends AbleActionBean {

    private String huraianPTD;
    private String syorPTD;
    private String huraianPTG;
    private String syorPTG;

     @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/draf_pertimbangan_mmk.jsp").addParameter("tab", "true");
    }

    public String getHuraianPTD() {
        return huraianPTD;
    }

    public void setHuraianPTD(String huraianPTD) {
        this.huraianPTD = huraianPTD;
    }

    public String getHuraianPTG() {
        return huraianPTG;
    }

    public void setHuraianPTG(String huraianPTG) {
        this.huraianPTG = huraianPTG;
    }

    public String getSyorPTD() {
        return syorPTD;
    }

    public void setSyorPTD(String syorPTD) {
        this.syorPTD = syorPTD;
    }

    public String getSyorPTG() {
        return syorPTG;
    }

    public void setSyorPTG(String syorPTG) {
        this.syorPTG = syorPTG;
    }

    
}

