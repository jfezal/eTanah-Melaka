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
@UrlBinding("/pembangunan/rayuan_pertimbangan_semula") // before /RPS
public class RayuanPertimbanganSemulaActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showSediaSuratPembatalan(){
        return new JSP("pembangunan/RPS/sedia_surat_pembatalan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}
