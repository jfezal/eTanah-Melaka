/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.PembangunanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Noor Hanan
 */
@UrlBinding("/pembangunan/hakmilikUmpukan")
public class PermohonanPembangunanActionBean extends AbleActionBean {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private PembangunanService devService;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPlotPelan> lpp;

    //Pecah Sempadan
    @DefaultHandler
    public Resolution tentuHakmilikUmpukanForm() {
        return new JSP("pembangunan/pecahBahagian/menentukan_hakmilik_umpukan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idPermohonan != null) {
            lpp = devService.findPermohonanPlotPelanPemilikanByIdPermohonan(idPermohonan);
        }
    }

    public List<PermohonanPlotPelan> getLpp() {
        return lpp;
    }

    public void setLpp(List<PermohonanPlotPelan> lpp) {
        this.lpp = lpp;
    }
}
