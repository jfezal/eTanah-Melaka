/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

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
import etanah.model.HakmilikPermohonan;
import java.util.List;
import etanah.model.PermohonanRujukanLuar;
import etanah.dao.PermohonanRujukanLuarDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@UrlBinding("/daftar/common/maklumat_perserahan")
public class MaklumatPerserahanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(MaklumatPerserahanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    RegService regService;
//    @Inject
//    PermohonanRujukanLuarDAO mohonRujukLuarDAO;
    private Permohonan permohonan;
//    private PermohonanRujukanLuar mohonRujukLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    public String idHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/common/paparan_maklumat_perserahan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            if (hakmilikPermohonanList.size() > 0) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                logger.debug("idHakmilik :" + idHakmilik);
            }
//            mohonRujukLuar = permohonan.getSenaraiRujukanLuar().get(0);

        }
    }

    public Resolution simpanPermohonan() {
//        System.out.println("in method simpanPermohonan");
        regService.simpanPermohonan(permohonan);
        addSimpleMessage("Kemasukan Data Berjaya");
        return new JSP("daftar/common/paparan_maklumat_perserahan.jsp").addParameter("tab", "true");
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

//    public PermohonanRujukanLuar getMohonRujukLuar() {
//        return mohonRujukLuar;
//    }
//
//    public void setMohonRujukLuar(PermohonanRujukanLuar mohonRujukLuar) {
//        this.mohonRujukLuar = mohonRujukLuar;
//    }
//
    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }
}
