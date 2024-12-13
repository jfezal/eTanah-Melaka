/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Permohonan;
import etanah.service.common.PermohonanService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/dokumenTamb")
public class DokumenTambActionBean extends AbleActionBean{

    private List<Permohonan> permohonanList = new ArrayList<Permohonan>();
    @Inject
    private PermohonanService permohonanService;

    public List<Permohonan> getPermohonanList() {
        return permohonanList;
    }

    public void setPermohonanList(List<Permohonan> permohonanList) {
        this.permohonanList = permohonanList;
    }

    @DefaultHandler
    public Resolution searchPermohonanGantung(){
        permohonanList = permohonanService.searchPermohonanGantung(getContext().getRequest().getParameterMap());
        return new JSP("daftar/carian_reg_gantung.jsp");
    }

}
