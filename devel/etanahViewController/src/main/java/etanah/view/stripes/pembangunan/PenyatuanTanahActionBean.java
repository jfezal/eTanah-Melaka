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
@UrlBinding("/pembangunan/penyatuanTanah")
public class PenyatuanTanahActionBean extends AbleActionBean{

    @DefaultHandler
    public Resolution showPerakuanPTDKeluasan4hktr() {
        return new JSP("pembangunan/penyatuanTanah/perakuanPTD_keluasan_4hktr.jsp").addParameter("tab", "true");
    }
     public Resolution showHakmilikKeluasanSyarat() {
        return new JSP("pembangunan/penyatuanTanah/menentukan_hakmilik_keluasan_syarat.jsp").addParameter("tab", "true");
    }
      public Resolution showKeputusanPenyatuanTnh() {
        return new JSP("pembangunan/penyatuanTanah/keputusan_penyatuan_tanah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}
