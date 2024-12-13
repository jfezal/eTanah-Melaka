/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pihak;
import etanah.service.common.PihakService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/common/pihak")
public class PihakActionBean extends AbleActionBean{

    @Inject
    PihakService pihakService;
    private List<Pihak> pihakList;
    private Pihak pihak;

    @DefaultHandler
    public Resolution showForms(){
        return new JSP("common/carian_pihak.jsp").addParameter("popup","true");
    }

    public Resolution searchPihak(){
        pihakList = pihakService.findAll(getContext().getRequest().getParameterMap());
        return new JSP("common/carian_pihak.jsp").addParameter("popup","true");
    }

    public List<Pihak> getPihakList() {
        return pihakList;
    }

    public void setPihakList(List<Pihak> pihakList) {
        this.pihakList = pihakList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }
}
