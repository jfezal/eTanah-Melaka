/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Rohan
 */
@UrlBinding("/pembangunan/melaka/perakuan")
public class PerakuanActionBean extends AbleActionBean {

    private String perakuanPTGMelaka;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/perakuan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/melaka/perakuan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public String getPerakuanPTGMelaka() {
        return perakuanPTGMelaka;
    }

    public void setPerakuanPTGMelaka(String perakuanPTGMelaka) {
        this.perakuanPTGMelaka = perakuanPTGMelaka;
    }

}