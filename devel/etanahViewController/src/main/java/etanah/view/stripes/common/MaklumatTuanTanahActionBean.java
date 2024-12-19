/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author md.nurfikri
 */
@UrlBinding("/common/maklumat_tuan_tanah")
public class MaklumatTuanTanahActionBean extends AbleActionBean{

    @Inject
    PermohonanDAO permohonanDAO;
    private List<Pihak> listTuanTanah;
    
    @DefaultHandler
    public Resolution showForm(){
        return new JSP("common/paparan_maklumat_tuan_tanah.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        if(idPermohonan != null){
            listTuanTanah = new LinkedList<Pihak>();
            Permohonan p = permohonanDAO.findById(idPermohonan);
            List<HakmilikPermohonan> l = p.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : l) {
                Hakmilik hk = hp.getHakmilik();
                List<HakmilikPihakBerkepentingan> lhpk = hk.getSenaraiPihakBerkepentingan();
                for (HakmilikPihakBerkepentingan hpk : lhpk) {
                    Pihak phk = hpk.getPihak();
                    listTuanTanah.add(phk);
                }
            }
        }
    }

    public List<Pihak> getListTuanTanah() {
        return listTuanTanah;
    }

    public void setListTuanTanah(List<Pihak> listTuanTanah) {
        this.listTuanTanah = listTuanTanah;
    }
}
