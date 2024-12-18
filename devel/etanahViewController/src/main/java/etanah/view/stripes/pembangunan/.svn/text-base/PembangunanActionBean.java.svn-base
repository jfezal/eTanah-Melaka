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


@UrlBinding("/pembangunan/pembangunan_MMK")
public class PembangunanActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showSediaDrafMMK() {
        return new JSP("pembangunan/pecahSempadan/dev_sedia_draf_MMK.jsp").addParameter("tab", "true");
    }
    public Resolution showPaparDrafMMK(){
        return new JSP("pembangunan/pecahSempadan/dev_paparan_draf_MMK.jsp").addParameter("tab", "true");
    }
    public Resolution showSemakDrafMMK(){
        return new JSP("pembangunan/pecahSempadan/dev_sedia_draf_MMK.jsp").addParameter("tab", "true");
    }
    public Resolution showHuraiDrafMMK(){
        return new JSP("pembangunan/pecahSempadan/dev_huraian_syor_draf_MMK.jsp").addParameter("tab", "true");
    }
    public Resolution showPerakuanPTD(){
        return new JSP("pembangunan/pecahSempadan/dev_perakuan_MMK_PTD.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}