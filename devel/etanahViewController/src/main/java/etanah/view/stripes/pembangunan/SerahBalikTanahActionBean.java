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
@UrlBinding("/pembangunan/serahbalik_tanah") // before /SBST
public class SerahBalikTanahActionBean extends AbleActionBean{

    @DefaultHandler
     public Resolution showChartingLulusTolak(){
        return new JSP("pembangunan/SBST/charting_kelulusan_penolakan.jsp").addParameter("tab", "true");
    }
    public Resolution showTentukanHakmilik(){
        return new JSP("pembangunan/SBST/menentukan_hakmilik.jsp").addParameter("tab", "true");
    }
    public Resolution showCatitPembatalanPTLotIndeks(){
        return new JSP("pembangunan/SBST/catit_pembatalan_PT_lotIndeks.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}
