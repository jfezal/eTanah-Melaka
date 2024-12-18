/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/maklumat_hakmilik_permohonan")
public class MaklumatHakmilikPermohonanActionBean extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private int size = 0;

    @DefaultHandler
    public Resolution showForm() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }
    @HttpCache(allow=false)
    public Resolution showForm2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
        return new JSP("daftar/senarai_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);
//       Permohonan p = ((etanahActionBeanContext)getContext()).getPermohonan();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (p != null) {
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            size = hakmilikPermohonanList.size();
        }
    }

        public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
