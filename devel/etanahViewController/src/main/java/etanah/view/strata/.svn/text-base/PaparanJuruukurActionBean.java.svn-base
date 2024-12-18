package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanJUBLDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanJUBL;
import etanah.view.BasicTabActionBean;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

@UrlBinding("/strata/paparanjuruukur")
public class PaparanJuruukurActionBean extends BasicTabActionBean{

    @Inject
    PermohonanJUBLDAO mohonJUBLDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan = new Permohonan();
    private PermohonanJUBL mohonJUBL = new PermohonanJUBL();

    public PermohonanJUBL getMohonJUBL() {
        return mohonJUBL;
    }

    public void setMohonJUBL(PermohonanJUBL mohonJUBL) {
        this.mohonJUBL = mohonJUBL;
    }

    public PermohonanJUBLDAO getMohonJUBLDAO() {
        return mohonJUBLDAO;
    }

    public void setMohonJUBLDAO(PermohonanJUBLDAO mohonJUBLDAO) {
        this.mohonJUBLDAO = mohonJUBLDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/pecahPetak/paparanJuruukurberlesen.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String[] name = {"permohonan"};
        Object[] object = {permohonan};
        List<PermohonanJUBL> listMohonJUBL = mohonJUBLDAO.findByEqualCriterias(name, object, null);
        if (listMohonJUBL.size() > 0) {
            mohonJUBL = listMohonJUBL.get(0);
        }
    }
}

