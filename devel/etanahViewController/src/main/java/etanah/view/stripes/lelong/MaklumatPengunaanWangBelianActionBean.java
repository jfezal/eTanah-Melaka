/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.Permohonan;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/maklumat_pengunaan_wang_belian")
public class MaklumatPengunaanWangBelianActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    LelongService lelongService;
    private Enkuiri enkuiri;
    private Permohonan permohonan;
    @Inject
    EnkuiriService enkuiriService;
    private boolean flag = false;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/surat_baki.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("lelong/surat_baki.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

        }
        
        List<Enkuiri> enkuiriList = lelongService.getEnkuiri(idPermohonan);
        enkuiri = enkuiriList.get(0);


    }

    public Resolution simpan() {

        enkuiriService.saveOrUpdate(enkuiri);
        setFlag(true);
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("lelong/surat_baki.jsp").addParameter("tab", "true");
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
