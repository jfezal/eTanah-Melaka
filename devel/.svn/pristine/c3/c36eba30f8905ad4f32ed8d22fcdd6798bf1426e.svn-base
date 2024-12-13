/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.service.RegService;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@UrlBinding("/daftar/sekatan")
public class Sekatan extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(Sekatan.class);
    @Inject
    RegService regService;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;

    @DefaultHandler
    public Resolution showMaklumatSekatan() {
        return new JSP("daftar/maklumat_sekatan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
    }

    public Resolution Simpan() {
        logger.debug("Saving sekatan");
        if (permohonan != null) {
            regService.simpanPermohonan(permohonan);
        }
        addSimpleMessage("Kemasukan Data Berjaya");
        return new JSP("daftar/maklumat_sekatan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}
