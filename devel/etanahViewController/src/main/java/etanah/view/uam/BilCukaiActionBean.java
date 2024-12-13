/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.service.ws.StatusSemakanAkaunService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author zahidaziz
 */
@HttpCache(allow = false)
@UrlBinding("/uam/bil_cukai")
public class BilCukaiActionBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BilCukaiActionBean.class);
    List<BilCukaiForm> senaraiBilCukai = new ArrayList<BilCukaiForm>();
    String caw;
    String tarikhHingga;
    String tarikhDari;

    @Inject
    StatusSemakanAkaunService statusSemakanAkaunService;

    @DefaultHandler
    public Resolution showForm() {
        senaraiBilCukai = statusSemakanAkaunService.findSenarai(caw, tarikhDari, tarikhHingga);
        return new JSP("uam/bil_cukai_statistik.jsp");
    }

    public Resolution cari() {
        senaraiBilCukai = statusSemakanAkaunService.findSenarai(caw, tarikhDari, tarikhHingga);
        return new JSP("uam/bil_cukai_statistik.jsp");
    }

    public List<BilCukaiForm> getSenaraiBilCukai() {
        return senaraiBilCukai;
    }

    public void setSenaraiBilCukai(List<BilCukaiForm> senaraiBilCukai) {
        this.senaraiBilCukai = senaraiBilCukai;
    }

    public String getCaw() {
        return caw;
    }

    public void setCaw(String caw) {
        this.caw = caw;
    }

    public String getTarikhHingga() {
        return tarikhHingga;
    }

    public void setTarikhHingga(String tarikhHingga) {
        this.tarikhHingga = tarikhHingga;
    }

    public String getTarikhDari() {
        return tarikhDari;
    }

    public void setTarikhDari(String tarikhDari) {
        this.tarikhDari = tarikhDari;
    }

}
