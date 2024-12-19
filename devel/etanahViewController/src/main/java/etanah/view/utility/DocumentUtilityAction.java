/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fikri
 */
@UrlBinding("/utiliti/dokumen_util")
public class DocumentUtilityAction extends AbleActionBean{

    private Permohonan permohonan;

    private String idPermohonan;

    @Inject PermohonanDAO permohonanDAO;

    @DefaultHandler
    public Resolution showForm() {
        resetAll();
        return new JSP("utiliti/dokumen_utiliti.jsp");
    }

    private void resetAll() {
        this.idPermohonan = "";
        this.permohonan = null;
    }

    public Resolution searchPermohonan(){
        if (StringUtils.isBlank(idPermohonan)){
            idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        }
        if (StringUtils.isBlank(idPermohonan)){
            addSimpleError("Sila masukan idPermohonan.");
        } else {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan == null) addSimpleError("Permohonan tidak dijumpai.");            
        }
        return new JSP("utiliti/dokumen_utiliti.jsp");
    }

    public Permohonan getPermohonan(){
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
}
