/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import org.apache.log4j.Logger;
import etanah.service.RegService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/maklumatPermohonanPengambilan")
public class MaklumatPermohonanPengambilanActionBean extends AbleActionBean {

    private static final Logger LOGGER = Logger.getLogger(MaklumatPermohonanPengambilanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    RegService regService;
    @Inject
    DokumenService dokumenService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    RegService o_regService;
    @Inject
    PembetulanService pService;
    @Inject
    HakmilikPermohonanService hpService;
    private Permohonan permohonan;
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/maklumat_Permohonan_Pengambilan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = hpService.findIdHakmilikSort(permohonan.getIdPermohonan());

            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            }

            if (permohonan.getSenaraiHakmilik().size() > 0) {
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik() != null) {
                    idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                }
            }

        }
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
