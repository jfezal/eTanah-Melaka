/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/percakapandalamsoalan")
public class PercakapanDalamSoalanActionBean extends AbleActionBean{
     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/kuatkuasa/percakapandalamsoalan.jsp").addParameter("tab", "true");
    }
}
