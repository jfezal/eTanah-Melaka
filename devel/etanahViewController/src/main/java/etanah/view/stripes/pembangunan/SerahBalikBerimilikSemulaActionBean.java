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
@UrlBinding("/pembangunan/serahbalik_berimiliksemula") // before /SBMS
public class SerahBalikBerimilikSemulaActionBean extends AbleActionBean{

    @DefaultHandler
     
     public Resolution showSediaCetakSuratMakluman(){
        return new JSP("pembangunan/SBMS/dev_sedia_cetak_surat_makluman.jsp").addParameter("tab", "true");
    }
      public Resolution showSediaLaporanPenilaian(){
        return new JSP("pembangunan/SBMS/sedia_laporan_penilaian.jsp").addParameter("tab", "true");
    }
       public Resolution showSahPelanTanah(){
        return new JSP("pembangunan/SBMS/pengesahan_pelan.jsp").addParameter("tab", "true");
    }
       
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}
