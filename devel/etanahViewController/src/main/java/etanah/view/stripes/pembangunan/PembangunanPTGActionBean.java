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
@UrlBinding("/pembangunan/MMK_PTG")
public class PembangunanPTGActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showPerakuanPTG(){
        return new JSP("pembangunan/pecahSempadan/dev_perakuan_MMK_PTG.jsp").addParameter("tab", "true");
    }
    public Resolution showKeputusanMMK(){
        return new JSP("pembangunan/pecahSempadan/dev_kemasukan_keputusan_MMK.jsp").addParameter("tab", "true");
    }
    public Resolution showHuraianSyorMMK(){
        return new JSP("pembangunan/pecahSempadan/dev_huraian_syor_MMK_PTG.jsp").addParameter("tab", "true");
    }
    public Resolution showCetakMMKSU(){
        return new JSP("pembangunan/pecahSempadan/dev_semak_MMK_SU.jsp").addParameter("tab", "true");
    }
    public Resolution showSemakMMK(){
        return new JSP("pembangunan/pecahSempadan/dev_semak_MMK_PTG.jsp").addParameter("tab", "true");
    }
    public Resolution showPaparMMKPTG(){
        return new JSP("pembangunan/pecahSempadan/dev_paparan_MMK_PTG.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}