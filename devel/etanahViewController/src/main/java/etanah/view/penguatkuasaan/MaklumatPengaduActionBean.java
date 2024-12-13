/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.KodCaraPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.service.EnforceService;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_aduan")
public class MaklumatPengaduActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatPengaduActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    private Permohonan permohonan;
    private KodCaraPermohonan caraPermohonan;


    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/maklumat_pengadu.jsp").addParameter("tab", "true");
    }
    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_pengadu_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            }
            logger.debug("idPermohonan" + idPermohonan);
        }
    }

    public Resolution simpan() {

        permohonan.setCaraPermohonan(caraPermohonan);
        enforceService.simpanPermohonan(permohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
         return new JSP("penguatkuasaan/maklumat_pengadu.jsp").addParameter("tab", "true");
      //  return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_pengadu.jsp").addParameter("tab", "true");
    }
}
