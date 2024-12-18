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
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/serah_sebahagian_tanah") //before /SBSBT
public class SerahSebhgnTanahActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showArahanBebasUpahUkur() {
        return new JSP("pembangunan/SBSBT/arahan_sedia_sijil_bebas_upah_ukur.jsp").addParameter("tab", "true");
    }
    public Resolution showSediaDrafSijil() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/SBSBT/sedia_draf_sijil.jsp").addParameter("tab", "true");
    }
    public Resolution showSemakDrafSijil() {
        return new JSP("pembangunan/SBSBT/sedia_draf_sijil.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}
