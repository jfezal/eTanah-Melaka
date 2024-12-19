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
@UrlBinding("/pembangunan/tukar_syarat_mmk") //before /TSMMK
public class TukarSyaratMMKActionBean extends AbleActionBean {

    @DefaultHandler
     public Resolution showSediaPU(){
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/TS_MMK/sedia_PU.jsp").addParameter("tab", "true");
    }
    public Resolution showSemakPU(){
        return new JSP("pembangunan/TS_MMK/sedia_PU.jsp").addParameter("tab", "true");
    }
    public Resolution showUlasanJabTeknikal(){
        return new JSP("pembangunan/TS_MMK/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }
     public Resolution showKelulusanBrg12BPelanTataturPraHitung(){
        return new JSP("pembangunan/TS_MMK/sedia_kelulusan_borang12B_pelanTatatur_pelanPraHitung.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}
