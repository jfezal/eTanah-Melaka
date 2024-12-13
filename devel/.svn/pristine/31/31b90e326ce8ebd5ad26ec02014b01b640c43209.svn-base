/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author NageswaraRao
 */

@UrlBinding("/strata/kuatkuasa/laporanSiasatan")
public class LaporanSiasatanActionBean extends BasicTabActionBean{
    private Permohonan permohonan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hp;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/kuatkuasa/laporanSiasatan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hp = new HakmilikPermohonan();
        if(!permohonan.getSenaraiHakmilik().isEmpty()){
            hp = permohonan.getSenaraiHakmilik().get(0);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        }
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

}
