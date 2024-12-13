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
@UrlBinding("/pembangunan/TSPchSempadan")
public class TSPchSempadanActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showPilihJabatanTeknikal(){
        return new JSP("pembangunan/TSPSS/pilih_jabatan_teknikal.jsp").addParameter("tab", "true");
    }
    public Resolution showPilihanJabatanTeknikal(){
        return new JSP("pembangunan/TSPSS/masuk_pilihan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }
     public Resolution showSediaCetakSuratBorang7G(){
        return new JSP("pembangunan/TSPSS/sedia_surat_kelulusan_borang7G.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }
}
