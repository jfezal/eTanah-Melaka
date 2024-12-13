/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author Aziz
 */
@HttpCache(allow = false)
@UrlBinding("/uam/pengguna_Details")
public class ViewPenggunaDetailsBean extends AbleActionBean {

    private Pengguna vPguna;
    @Inject
    private UamService service;
    @Inject
    private etanah.Configuration conf;
    private boolean melaka = false;

    @DefaultHandler
    public Resolution showForm() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        return new JSP("uam/viewPenggunaDetails.jsp");
    }

    public Resolution viewPenggunaDetails() throws Exception {
        String idPguna = getContext().getRequest().getParameter("idPengguna");
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
        vPguna = service.getPenggunaDetails(idPguna);

        return new JSP("uam/viewPenggunaDetails.jsp").addParameter("popup", true);
//        return new ForwardResolution("/WEB-INF/jsp/uam/viewPenggunaDetails.jsp");
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }

    public Pengguna getvPguna() {
        return vPguna;
    }

    public void setvPguna(Pengguna vPguna) {
        this.vPguna = vPguna;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }
    
    
}
