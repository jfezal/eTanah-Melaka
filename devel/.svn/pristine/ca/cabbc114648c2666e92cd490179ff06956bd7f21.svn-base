/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author nns
 */
@UrlBinding("/strata/PKBK")
public class PermohonanStrataPKBKActionBean extends AbleActionBean {

    @Inject
    etanah.Configuration conf;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strataService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    CommonService comm;
    List<HakmilikPihakBerkepentingan> listPihak = new ArrayList();
    List<HakmilikPermohonan> listHakmilikP = new ArrayList();
    String idPermohonan = new String();
    private static final Logger LOG = Logger.getLogger(PermohonanStrataPKKRActionBean.class);

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution paparsuratPKBK() {
        return new JSP("strata/bngn_khas/paparsuratPKBK.jsp").addParameter("tab", true);
    }

    public List<HakmilikPihakBerkepentingan> getListPihak() {
        return listPihak;
    }

    public void setListPihak(List<HakmilikPihakBerkepentingan> listPihak) {
        this.listPihak = listPihak;
    }

    public List<HakmilikPermohonan> getListHakmilikP() {
        return listHakmilikP;
    }

    public void setListHakmilikP(List<HakmilikPermohonan> listHakmilikP) {
        this.listHakmilikP = listHakmilikP;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
}
