/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.service.RegService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@UrlBinding("/pendaftaran/maklumat_fail")
public class MaklumatFailActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(MaklumatFailActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    RegService regService;

    private Permohonan permohonan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/maklumat_fail.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug("IdPermohonan:"+idPermohonan);
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
        }
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
     public Resolution simpanPermohonan() {
//        System.out.println("in method simpanPermohonan");
        regService.simpanPermohonan(permohonan);
        addSimpleMessage("Kemasukan Data Berjaya");
        return new JSP("daftar/maklumat_fail.jsp").addParameter("tab", "true");
    }



}
