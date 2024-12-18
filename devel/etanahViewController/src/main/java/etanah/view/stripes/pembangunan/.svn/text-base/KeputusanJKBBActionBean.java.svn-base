/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import etanah.dao.PermohonanDAO;
//import etanah.model.Permohonan;
import etanah.model.FasaPermohonan;
import etanah.service.PembangunanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
//import net.sourceforge.stripes.action.ErrorResolution;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */

@UrlBinding("/pembangunan/keputusanJKBB")
public class KeputusanJKBBActionBean extends AbleActionBean{
    private static final Logger logger = Logger.getLogger(MaklumatPermohonanActionBean.class);
//    @Inject
//    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    private String stageId;
//    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/melaka/keputusan_jkbb.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("rehydrate start.");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);

        stageId = "sediajkbbrekodkeputusan";
        fasaPermohonan = devService.findFasaPermohonanByIdAliran(idPermohonan, stageId);
        logger.info("rehydrate end");
    }
}
